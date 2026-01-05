<?php
include __DIR__ . "/db.php";

$user_id = $_POST['user_id'];
$app = $_POST['app_name'];
$category = $_POST['category'];

$conn->query("INSERT INTO blocked_apps(user_id,app_name,category)
              VALUES($user_id,'$app','$category')");

echo json_encode(["status"=>"app_blocked"]);

