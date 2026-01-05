<?php
include __DIR__ . "/db.php";

$user_id = $_GET['user_id'];

$sql = "SELECT d.plan_name,d.level,d.max_screen_time
        FROM user_detox_plan u
        JOIN detox_plan_master d ON u.plan_id=d.id
        WHERE u.user_id=$user_id AND u.status='active'";

$res = $conn->query($sql);
echo json_encode($res->fetch_assoc());

