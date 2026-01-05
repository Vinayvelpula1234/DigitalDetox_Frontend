<?php
include __DIR__ . "/db.php";

$user_id = $_POST['user_id'];
$minutes = $_POST['minutes'];

$conn->query("INSERT INTO screen_time(user_id,date,minutes)
              VALUES($user_id,CURDATE(),$minutes)");

echo json_encode(["status"=>"saved"]);

