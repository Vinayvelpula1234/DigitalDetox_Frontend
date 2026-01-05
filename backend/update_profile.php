<?php
include __DIR__ . "/db.php";

$user_id = $_POST['user_id'];
$name = $_POST['name'];
$language = $_POST['language'];

$conn->query("UPDATE users SET name='$name', language='$language' WHERE id=$user_id");

echo json_encode(["status"=>"profile_updated"]);

