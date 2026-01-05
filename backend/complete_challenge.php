<?php
include __DIR__ . "/db.php";

$user_id = $_POST['user_id'];
$challenge_id = $_POST['challenge_id'];
$points = $_POST['points'];

$conn->query("INSERT INTO user_challenge_progress(user_id,challenge_id,status)
              VALUES($user_id,$challenge_id,'completed')");

$conn->query("UPDATE rewards SET points = points + $points WHERE user_id=$user_id");

echo json_encode(["status"=>"challenge_completed"]);

