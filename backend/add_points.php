<?php
include __DIR__ . "/db.php";

$user_id = $_POST['user_id'];
$points = $_POST['points'];

$conn->query("UPDATE rewards SET points = points + $points WHERE user_id=$user_id");

echo json_encode(["status"=>"points_added"]);

