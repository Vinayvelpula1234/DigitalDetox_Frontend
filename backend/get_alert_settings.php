<?php
include __DIR__ . "/db.php";

$user_id = $_GET['user_id'];
$res = $conn->query("SELECT * FROM alerts WHERE user_id=$user_id");

$data=[];
while($r=$res->fetch_assoc()){
    $data[]=$r;
}
echo json_encode($data);

