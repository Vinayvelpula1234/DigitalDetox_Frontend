<?php
// Prevent any output before JSON
ob_start();
header("Content-Type: application/json; charset=UTF-8");

// Database credentials
$DB_HOST = "localhost";
$DB_USER = "root";
$DB_PASS = "";
$DB_NAME = "new_digital_detox";

// Clear any accidental output
ob_clean();

// Create connection
$conn = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME);

// Check connection - don't output anything here, let the calling script handle it
if ($conn->connect_error) {
    // Connection failed, but don't echo here - let the calling script check $conn
}

// Set charset
$conn->set_charset("utf8mb4");
