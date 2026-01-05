<?php
include __DIR__ . "/db.php";

$user=$_POST['user_id'];
$plan=$_POST['plan_id'];

$conn->query("INSERT INTO user_detox_plan(user_id,plan_id,start_date,status)
VALUES($user,$plan,CURDATE(),'active')");

echo json_encode(["status"=>"plan_selected"]);

