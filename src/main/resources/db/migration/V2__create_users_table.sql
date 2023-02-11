CREATE TABLE IF NOT EXISTS `users` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20) NOT NULL,
    `username` varchar(20) NOT NULL UNIQUE,
    `email` varchar(50) NOT NULL UNIQUE,
    `password` varchar(64) NOT NULL,
    `roles` varchar(24) NOT NULL
    

)