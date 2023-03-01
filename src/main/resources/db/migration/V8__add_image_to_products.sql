ALTER TABLE products ADD COLUMN image VARCHAR(255) NOT NULL DEFAULT '';

UPDATE products SET image = '/images/Moton Suspension 1-Way Coilovers.png' WHERE id = 1;
UPDATE products SET image = '/images/HPT F3 7675 BILLET BALL BEARING TURBO 1350HP.jpg' WHERE id = 2;
UPDATE products SET image = '/images/StopTech Sport Big Brake Kit - Front.png' WHERE id = 3;
UPDATE products SET image = '/images/Castrol Edge Supercar Engine Oil.jpg' WHERE id = 4;
UPDATE products SET image = '/images/Bosch S4 Car Batt.jpg' WHERE id = 5;
