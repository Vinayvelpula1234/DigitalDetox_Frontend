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
$email = $_POST["email"] ?? "";
$password = $_POST["password"] ?? "";

if (empty($email) || empty($password)) {
    echo json_encode(["status" => "error", "message" => "Email and password required"]);
    exit;
}

// Query user
$sql = "SELECT id, name, password FROM users WHERE email = ? LIMIT 1";
$stmt = $conn->prepare($sql);

if ($stmt === false) {
    echo json_encode(["status" => "error", "message" => "Database error"]);
    exit;
}

$stmt->bind_param("s", $email);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows === 0) {
    echo json_encode(["status" => "error", "message" => "Invalid email or password"]);
    exit;
}

$user = $result->fetch_assoc();

if (password_verify($password, $user["password"])) {
    echo json_encode([
        "status" => "success",
        "message" => "Login successful",
        "user" => [
            "id" => (int)$user["id"],
            "name" => $user["name"]
        ]
    ]);
} else {
    echo json_encode(["status" => "error", "message" => "Invalid email or password"]);
}

$stmt->close();
$conn->close();
