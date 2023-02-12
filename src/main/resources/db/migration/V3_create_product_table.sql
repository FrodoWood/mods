CREATE TABLE IF NOT EXISTS `users` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20) NOT NULL,
    'rating' int NOT NULL,
    'description' varchar(256) NOT NULL
)