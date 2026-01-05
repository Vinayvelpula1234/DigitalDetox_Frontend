-- DigitalDetox Database Schema for XAMPP

CREATE DATABASE IF NOT EXISTS new_digital_detox;
USE new_digital_detox;

-- Table: users
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: screen_time
CREATE TABLE IF NOT EXISTS screen_time (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    date DATE NOT NULL,
    total_minutes INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Table: rewards
CREATE TABLE IF NOT EXISTS rewards (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    streak_days INT DEFAULT 0,
    points INT DEFAULT 0,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Table: reminders
CREATE TABLE IF NOT EXISTS reminders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    reminder_time TIME NOT NULL,
    message VARCHAR(255) NOT NULL,
    enabled TINYINT(1) DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Table: blocked_apps
CREATE TABLE IF NOT EXISTS blocked_apps (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    app_name VARCHAR(255) NOT NULL,
    category VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Table: blocked_websites
CREATE TABLE IF NOT EXISTS blocked_websites (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    website VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Table: challenges
CREATE TABLE IF NOT EXISTS challenges (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    points INT DEFAULT 0
);

-- Table: user_challenge_progress
CREATE TABLE IF NOT EXISTS user_challenge_progress (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    challenge_id INT NOT NULL,
    status ENUM('active', 'completed') DEFAULT 'active',
    completed_at TIMESTAMP NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (challenge_id) REFERENCES challenges(id) ON DELETE CASCADE
);

-- Table: detox_plan_master
CREATE TABLE IF NOT EXISTS detox_plan_master (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plan_name VARCHAR(255) NOT NULL,
    description TEXT,
    duration_days INT NOT NULL
);

-- Table: user_detox_plan
CREATE TABLE IF NOT EXISTS user_detox_plan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    plan_id INT NOT NULL,
    start_date DATE NOT NULL,
    status ENUM('active', 'completed', 'cancelled') DEFAULT 'active',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (plan_id) REFERENCES detox_plan_master(id) ON DELETE CASCADE
);

-- Table: alerts
CREATE TABLE IF NOT EXISTS alerts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    alert_type VARCHAR(255) NOT NULL,
    enabled TINYINT(1) DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Insert some dummy data for challenges and plans
INSERT INTO detox_plan_master (plan_name, description, duration_days) VALUES 
('Beginner', '7 days of light detox', 7),
('Intermediate', '14 days of balanced detox', 14),
('Advanced', '30 days of intensive detox', 30);

INSERT INTO challenges (title, description, points) VALUES 
('No Phone during Dinner', 'Keep your phone away during meal time.', 50),
('Evening Walk', 'Go for a 30-minute walk without your phone.', 30),
('Reading Time', 'Read a physical book for 1 hour.', 40);
