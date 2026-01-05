<?php
// Prevent any output before JSON
ob_start();
header("Content-Type: application/json; charset=UTF-8");

include __DIR__ . "/db.php";

// Clear any accidental output
ob_clean();

// Check DB connection
if (!isset($conn) || $conn->connect_error) {
    echo json_encode(["status" => "error", "message" => "Database connection failed"]);
    exit;
}

// Only allow POST
if ($_SERVER["REQUEST_METHOD"] !== "POST") {
    echo json_encode(["status" => "error", "message" => "POST required"]);
    exit;
}

// Get data
$name = $_POST["name"] ?? "";
$email = $_POST["email"] ?? "";
$password = $_POST["password"] ?? "";

if (empty($name) || empty($email) || empty($password)) {
    echo json_encode(["status" => "error", "message" => "All fields required"]);
    exit;
}

// Hash password
$hash = password_hash($password, PASSWORD_BCRYPT);

// Insert user
$sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
$stmt = $conn->prepare($sql);

if ($stmt === false) {
    echo json_encode(["status" => "error", "message" => "Database error: " . $conn->error]);
    exit;
}

$stmt->bind_param("sss", $name, $email, $hash);

if ($stmt->execute()) {
    $user_id = $conn->insert_id;
    echo json_encode([
        "status" => "success",
        "message" => "Registered",
        "user" => [
            "id" => (int)$user_id,
            "name" => $name
        ]
    ]);
} else {
    if (strpos($stmt->error, 'Duplicate') !== false) {
        echo json_encode(["status" => "error", "message" => "Email already exists"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Registration failed"]);
    }
}

$stmt->close();
$conn->close();
