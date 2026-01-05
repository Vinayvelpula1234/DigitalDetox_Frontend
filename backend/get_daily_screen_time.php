<?php
include __DIR__ . "/db.php";

$user_id = $_GET['user_id'];
$res = $conn->query("SELECT date,minutes FROM screen_time WHERE user_id=$user_id");

$data=[];
while($r=$res->fetch_assoc()){
    $data[]=$r;
}
echo json_encode($data);

