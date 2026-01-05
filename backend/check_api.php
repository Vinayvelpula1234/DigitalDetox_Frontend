<?php
/**
 * DigitalDetox Diagnostic Tool
 * Run this in your browser: http://192.168.1.16/Digital_detox/backend/check_api.php
 */
header("Content-Type: application/json");
include __DIR__ . "/db.php";

$report = [
    "status" => "diagnostic_running",
    "timestamp" => date("Y-m-d H:i:s"),
    "checks" => []
];

// 1. Test Data Connection
if (isset($conn) && !$conn->connect_error) {
    $report["checks"]["db_connection"] = [
        "result" => "SUCCESS",
        "database" => $DB_NAME,
        "host" => $DB_HOST
    ];
    
    // 2. Check for 'users' table
    $result = $conn->query("SHOW TABLES LIKE 'users'");
    if ($result->num_rows > 0) {
        $report["checks"]["users_table"] = ["result" => "EXISTS"];
        
        // 3. Check columns in 'users'
        $columns = [];
        $res = $conn->query("DESCRIBE users");
        while($row = $res->fetch_assoc()) {
            $columns[] = $row['Field'];
        }
        $report["checks"]["users_columns"] = $columns;
        
        // Required columns for the app
        $required = ["id", "name", "email", "password"];
        $missing = array_diff($required, $columns);
        
        if (empty($missing)) {
             $report["checks"]["schema_check"] = ["result" => "VALID"];
        } else {
             $report["checks"]["schema_check"] = ["result" => "INVALID", "missing" => $missing];
        }

    } else {
        $report["checks"]["users_table"] = ["result" => "MISSING", "error" => "Table 'users' not found in $DB_NAME"];
    }
} else {
    $report["checks"]["db_connection"] = [
        "result" => "FAILED",
        "error" => $conn->connect_error ?? "Database connection object failed to initialize. Check db.php"
    ];
}

echo json_encode($report, JSON_PRETTY_PRINT);

