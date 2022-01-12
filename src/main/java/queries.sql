CREATE TABLE IF NOT EXISTS users
(
    id          INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    email        VARCHAR(255) UNIQUE,
    username VARCHAR(255),
    password      VARCHAR (60)
    );

