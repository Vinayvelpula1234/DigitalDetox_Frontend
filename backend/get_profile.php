<?php
include __DIR__ . "/db.php";

$user_id = $_GET['user_id'];
$res = $conn->query("SELECT name,email,language FROM users WHERE id=$user_id");

echo json_encode($res->fetch_assoc());

