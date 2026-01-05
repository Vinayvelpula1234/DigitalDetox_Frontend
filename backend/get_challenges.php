<?php
include __DIR__ . "/db.php";

$level = $_GET['level'];

$res = $conn->query("SELECT * FROM challenges WHERE plan_level='$level'");
$data = [];

while ($row = $res->fetch_assoc()) {
    $data[] = $row;
}

echo json_encode($data);

