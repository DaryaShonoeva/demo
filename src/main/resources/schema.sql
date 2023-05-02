CREATE TABLE IF NOT EXISTS clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    age INT,
    group_id VARCHAR(255),
    phone VARCHAR(255),
    date DATE
);