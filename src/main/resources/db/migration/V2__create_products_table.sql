CREATE TABLE IF NOT EXISTS products (
  id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE KEY,
  rating FLOAT NOT NULL,
  description TEXT NOT NULL,
  model VARCHAR(255) NOT NULL,
  make VARCHAR(255) NOT NULL,
  category VARCHAR(255) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  stock INT NOT NULL
);
