<?php
header("Content-Type: application/json");
include __DIR__ . "/db.php";

if (!isset($conn) || $conn->connect_error) {
    echo json_encode(["streak_days" => 0, "points" => 0]);
    exit;
}

$user_id = $_GET['user_id'] ?? 0;

if ($user_id <= 0) {
    echo json_encode(["streak_days" => 0, "points" => 0]);
    exit;
}

$sql = "SELECT streak_days, points FROM rewards WHERE user_id = ? LIMIT 1";
$stmt = $conn->prepare($sql);

if ($stmt === false) {
    echo json_encode(["streak_days" => 0, "points" => 0]);
    exit;
}

$stmt->bind_param("i", $user_id);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    echo json_encode([
        "streak_days" => (int)$row["streak_days"],
        "points" => (int)$row["points"]
    ]);
} else {
    // Return default values if no rewards record exists
    echo json_encode(["streak_days" => 0, "points" => 0]);
}

$stmt->close();
$conn->close();
