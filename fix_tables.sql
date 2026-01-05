-- Run this in PHPMyAdmin to fix the users table
-- First disable foreign key checks

SET FOREIGN_KEY_CHECKS = 0;

USE new_digital_detox;

-- Now we can drop tables safely
DROP TABLE IF EXISTS user_detox_plan;
DROP TABLE IF EXISTS user_challenge_progress;
DROP TABLE IF EXISTS alerts;
DROP TABLE IF EXISTS reminders;
DROP TABLE IF EXISTS rewards;
DROP TABLE IF EXISTS blocked_apps;
DROP TABLE IF EXISTS blocked_websites;
DROP TABLE IF EXISTS screen_time;
DROP TABLE IF EXISTS users;

-- Recreate users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    language VARCHAR(10) DEFAULT 'EN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Recreate rewards table
CREATE TABLE rewards (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    streak_days INT DEFAULT 0,
    points INT DEFAULT 0,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Verify
SELECT 'Tables created successfully!' AS Status;
