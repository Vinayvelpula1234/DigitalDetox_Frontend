<?php
include __DIR__ . "/db.php";

$user_id = $_POST['user_id'];
$type = $_POST['alert_type'];
$enabled = $_POST['enabled'];

$conn->query("INSERT INTO alerts(user_id,alert_type,enabled)
              VALUES($user_id,'$type',$enabled)");

echo json_encode(["status"=>"alert_saved"]);


