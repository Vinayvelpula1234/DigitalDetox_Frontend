<?php
include __DIR__ . "/db.php";

$user_id = $_POST['user_id'];
$website = $_POST['website'];

$conn->query("INSERT INTO blocked_websites(user_id,website)
              VALUES($user_id,'$website')");

echo json_encode(["status"=>"website_blocked"]);

