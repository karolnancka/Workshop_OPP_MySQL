CREATE TABLE IF NOT EXISTS users
(
    id          INT(11) PRIMARY KEY AUTO_INCREMENT,
    email        VARCHAR(255) UNIQUE,
    username VARCHAR(255),
    passwors      VARCHAR (60)
    );

