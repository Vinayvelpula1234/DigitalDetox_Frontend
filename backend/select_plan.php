<?php
include __DIR__ . "/db.php";

$user_id = $_POST['user_id'];
$plan_id = $_POST['plan_id'];

$conn->query("INSERT INTO user_detox_plan(user_id,plan_id,start_date,status)
              VALUES($user_id,$plan_id,CURDATE(),'active')");

echo json_encode(["status"=>"plan_selected"]);

