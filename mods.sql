-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2023 at 05:08 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mods`
--

-- --------------------------------------------------------

--
-- Table structure for table `basket`
--

CREATE TABLE `basket` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `basket`
--

INSERT INTO `basket` (`id`, `user_id`) VALUES
(65, 11);

-- --------------------------------------------------------

--
-- Table structure for table `basket_products`
--

CREATE TABLE `basket_products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `basket_id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `flyway_schema_history`
--

CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `flyway_schema_history`
--

INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1, '1', 'create users table', 'SQL', 'V1__create_users_table.sql', -1313381774, 'root', '2023-02-26 01:07:03', 13, 1),
(2, '2', 'create products table', 'SQL', 'V2__create_products_table.sql', -673063660, 'root', '2023-02-26 01:07:03', 20, 1),
(3, '3', 'create basket table', 'SQL', 'V3__create_basket_table.sql', -466918180, 'root', '2023-02-26 01:07:03', 12, 1),
(4, '4', 'create basket products table', 'SQL', 'V4__create_basket_products_table.sql', -695935268, 'root', '2023-02-26 01:07:03', 15, 1),
(5, '5', 'create orders table', 'SQL', 'V5__create_orders_table.sql', -637071262, 'root', '2023-02-26 01:07:03', 40, 1),
(6, '6', 'create order products table', 'SQL', 'V6__create_order_products_table.sql', -535239675, 'root', '2023-02-26 01:07:03', 16, 1),
(7, '7', 'add products to products table', 'SQL', 'V7__add_products_to_products_table.sql', 1925076441, 'root', '2023-02-26 01:07:03', 3, 1),
(8, '8', 'add image to products', 'SQL', 'V8__add_image_to_products.sql', -522955657, 'root', '2023-03-01 02:43:13', 15, 1),
(9, '9', 'create reviews table', 'SQL', 'V9__create_reviews_table.sql', -452798332, 'root', '2023-03-18 04:25:14', 34, 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_fulfilled` datetime DEFAULT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `status` enum('ORDERED','DELIVERED','CANCELLED') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `date_created`, `date_fulfilled`, `total_price`, `status`) VALUES
(2, 1, '2023-01-11 01:55:21', NULL, '3401.75', 'DELIVERED'),
(3, 1, '2023-02-26 03:24:18', NULL, '1595.95', 'DELIVERED'),
(4, 1, '2023-02-26 03:24:59', NULL, '6399.80', 'DELIVERED'),
(5, 1, '2023-02-27 01:07:20', NULL, '14790.85', 'DELIVERED'),
(6, 1, '2023-02-27 23:45:44', NULL, '4519.75', 'ORDERED'),
(14, 1, '2023-02-28 23:49:57', NULL, '2499.75', 'CANCELLED'),
(15, 1, '2023-02-28 23:52:01', NULL, '2499.75', 'ORDERED'),
(16, 1, '2023-03-01 02:53:46', NULL, '4791.85', 'CANCELLED'),
(17, 1, '2023-03-08 23:53:44', NULL, '2919.90', 'DELIVERED'),
(18, 1, '2023-03-15 00:11:41', NULL, '139.90', 'DELIVERED'),
(19, 1, '2023-03-15 00:11:54', NULL, '1459.95', 'DELIVERED'),
(20, 1, '2023-03-18 23:42:06', NULL, '499.95', 'DELIVERED'),
(21, 2, '2023-03-19 16:30:16', NULL, '1459.95', 'DELIVERED'),
(22, 4, '2023-03-20 01:28:13', NULL, '1595.95', 'ORDERED'),
(24, 4, '2023-03-24 02:28:47', NULL, '499.95', 'CANCELLED'),
(25, 2, '2023-03-25 04:13:23', NULL, '4791.85', 'CANCELLED'),
(26, 2, '2023-03-25 18:57:17', NULL, '139.90', 'CANCELLED'),
(27, 4, '2023-03-28 16:31:41', NULL, '1669.90', 'CANCELLED'),
(28, 10, '2023-03-28 21:15:05', NULL, '197.99', 'DELIVERED'),
(29, 10, '2023-03-29 01:26:42', NULL, '1101.99', 'ORDERED'),
(30, 10, '2023-03-29 01:27:52', NULL, '1299.99', 'ORDERED'),
(31, 10, '2023-03-29 01:28:58', NULL, '499.95', 'ORDERED'),
(32, 11, '2023-03-29 10:37:54', NULL, '3600.00', 'ORDERED');

-- --------------------------------------------------------

--
-- Table structure for table `order_products`
--

CREATE TABLE `order_products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `order_id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_products`
--

INSERT INTO `order_products` (`id`, `order_id`, `product_id`, `quantity`) VALUES
(2, 2, 3, 2),
(3, 2, 4, 3),
(4, 3, 3, 1),
(5, 4, 5, 4),
(6, 5, 3, 2),
(7, 5, 5, 1),
(9, 6, 2, 3),
(10, 6, 4, 2),
(19, 14, 1, 5),
(20, 15, 1, 5),
(21, 16, 3, 2),
(22, 16, 5, 1),
(23, 17, 2, 2),
(24, 18, 4, 2),
(25, 19, 2, 1),
(26, 20, 1, 1),
(27, 21, 2, 1),
(28, 22, 3, 1),
(30, 24, 1, 1),
(31, 25, 3, 2),
(32, 25, 5, 1),
(33, 26, 4, 2),
(34, 27, 4, 1),
(35, 27, 5, 1),
(36, 28, 69, 1),
(37, 29, 40, 1),
(38, 30, 59, 1),
(39, 31, 1, 1),
(40, 32, 74, 1);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `rating` float NOT NULL,
  `description` text NOT NULL,
  `model` varchar(255) NOT NULL,
  `make` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `image` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `rating`, `description`, `model`, `make`, `category`, `price`, `stock`, `image`) VALUES
(1, 'BC Racing V1-Series Racing Coilovers', 3.8, 'Spax RSX Coilover Suspension Kits are height and damping adjustable coilover kits. Offering a terrific feature-set at a great price these a great balance of performance, features, and value.', 'A5', 'Audi', 'Coilovers', '499.95', 3, '/images/Moton Suspension 1-Way Coilovers.png'),
(2, 'Precision 6470 Next Gen R Billet Ball Bearing Turbo', 4, 'An EBC Brakes Turbo Groove Brake Discs and Yellowstuff Pads Kit is a great value brake upgrade. Combining EBC Turbo Groove Brake Discs with their own high-performance Yellowstuff Brake Pads, this kit offers enhanced braking performance on hard-driven road cars.\r\n', 'Z4', 'BMW', 'Turbo', '1459.95', 6, '/images/HPT F3 7675 BILLET BALL BEARING TURBO 1350HP.jpg'),
(3, 'StopTech Sport Big Brake Kit - Front', 4.5, 'Tarox Big Brake Kits are complete, ready to fit, big brake kits are suitable for high performance road and trackday use. They offer the highest level of braking for road and trackday cars. Upgrading to a Tarox brake kit will give you amazing braking performance and total confidence in your vehicle.\r\n', 'A5', 'Audi', 'Brakes', '1595.95', 0, '/images/StopTech Sport Big Brake Kit - Front.png'),
(4, 'Castrol Edge Supercar 10W-60', 2.5, 'The Castrol A747 is a castor/synthetic engine oil specifically designed for use in high performance 2-stroke engines. A747 is only recommended for engine that use premixed fuels because the viscosity is too high for use in oil injection systems.\r\n', 'Z4', 'BMW', 'Oils', '69.95', 9, '/images/Castrol Edge Supercar Engine Oil.jpg'),
(5, 'SportTech Tropy Sport Big Brake Kit', 0, 'Braille Xcel-Lite is a range of ultra lightweight, high performance lithium batteries which provide huge cranking currents compared to their size and weight. Braille batteries have been successfully tried and tested in top level Motorsport including wins in F1, Le Mans 24hr, Daytona 500 and Indy 500. Xcel-Lite batteries deliver the safest, most reliable high performance upgrade from traditional lead acid batteries for starting applications.', 'A5', 'Audi', 'Batteries', '1599.95', 3, '/images/Bosch S4 Car Batt.jpg'),
(39, 'No image test', 0, 'When the admin doesn\'t include an image, a default image is shown.', 'M4', 'BMW', 'Brakes', '234.00', 5, '/images/image-missing-stars-mods.jpg'),
(40, 'ST Suspension ST XTA Coilover Kit', 3, 'With the ST-XTA for the first time on a fast-road coilover kit, KW are offering a complete solution with coilover suspension struts and fully adjustable top mounts. Previously only offering this level of specification on high end club-sport coilovers, this is a huge amount of tweekability on a road coilovers.', 'A5', 'Audi', 'Coilovers', '1101.99', 0, '/images/1.jpg'),
(41, 'EBC Brakes Turbo', 0, 'An EBC Brakes Turbo Groove Brake Discs and Yellowstuff Pads Kit is a great value brake upgrade. Combining EBC Turbo Groove Brake Discs with their own high-performance Yellowstuff Brake Pads, this kit offers enhanced braking performance on hard-driven road cars.\r\n', 'Q5', 'Audi', 'Brakes', '259.99', 2, '/images/2.jpg'),
(42, 'EBC Brakes OE', 0, 'An EBC Brakes OE Replacement Discs and Ultimax Pads Kit saves you money on a great combination of high-quality OE replacement brake discs and pads. This combination will give you brakes that perform exactly as the original components do.\r\n', 'S5', 'Audi', 'Brakes', '229.99', 4, '/images/3.jpg'),
(43, 'EBC Brakes BSD Performance', 0, 'An EBC Brakes BSD Performance Discs and Yellowstuff Pads Kit saves you money on a great combination of slotted brake disc and EBCs newest fast-road pad compound. This combination will give you brakes that stop harder, and work well for longer under sustained or harsh braking.\r\n', 'Z4', 'BMW', 'Brakes', '199.99', 3, '/images/4.jpg'),
(44, 'KW Suspension V4 Coilover Kit', 0, 'KW Clubsport Coilover Kits are an adjustable ride height system developed from a motorsport pedigree and designed for track performance, featuring state of the art independently adjustable bump (compression) and rebound damping, allowing fine-tuning of the dampers to suit your personal preferences.\r\n', 'X5', 'BMW', 'Coilovers', '9849.99', 2, '/images/5.jpg'),
(45, 'KW Suspension V5 Coilover Kit', 0, 'KW Suspension V5 Coilovers are the best of the best for the ultimate handling on some of the best performance cars in the world. Built from technologies used in KW\'s successful history in motorsport, the Variant 5 assures precise damping without delay and the shocks and valves react directly even with small steering movements.\r\n', 'M4', 'BMW', 'Coilovers', '7999.99', 6, '/images/6.jpg'),
(46, 'Sapx RSX Coilover Suspension Kit', 0, 'Spax RSX Coilover Suspension Kits are height and damping adjustable coilover kits. Offering a terrific feature-set at a great price these a great balance of performance, features, and value.\r\n', 'A5', 'Audi', 'Coilovers', '599.99', 4, '/images/7.jpg'),
(47, 'KW Suspension V2 Coilover Kit', 0, 'KW Suspension V2 Coilovers are high-performance twin-tube adjustable Coilovers for fast road use. Offering all the advantages of the renowned KW INOX V1 range with the additional feature of user-adjustable rebound damping.\r\n', 'Q5', 'Audi', 'Coilovers', '455.99', 10, '/images/8.jpg'),
(48, 'Bilstein B14 PSS Coilover Suspension Kit', 0, 'Bilstein B14 PSS Ride Height Adjustable Kits are an adjustable ride height system front and rear, with factory tuned, fixed rate damping. Bilstein\'s extensive motorsport experience and Original Equipment damper manufacturing has enabled them to produce a range of coilover suspension kits that are specifically designed for each application and suited perfectly for performance driving on the road.\r\n', 'Q5', 'Audi', 'Coilovers', '799.99', 7, '/images/9.jpg'),
(49, 'AP Suspension Coilover Kit', 0, 'AP Coilover Suspension Kit -Adjustable Ride Height, Fixed Rate Damping\r\n', 'S5', 'Audi', 'Coilovers', '339.95', 5, '/images/10.jpg'),
(50, 'Odessy Extreme Power AGM Battery', 0, 'Odyssey Extreme Power & Motorsport racing batteries offer phenomenal starting power and massive deep cycle reserve power. Some other batteries provide excellent cranking power, others reserve power. The revolutionary Odyssey Extreme Racing batteries are designed for both! This is possible thanks to the plates being made from 99.99% pure virgin lead. Pure lead plates can be made thinner, so more can be fitted into the battery. More surface area means more power, up to twice as much as conventional batteries.\r\n', 'Z4', 'BMW', 'Batteries', '99.95', 5, '/images/11.jpg'),
(51, 'Varley Lithium RT900 Battery', 0, 'These Varley RT900 Lithium batteries offer a weight saving of 70% over a Varley Red Top 30 with a much higher cranking current. Weighing just 2.5kg the RT900 lithium battery is ideal for reducing the weight of your race car without compromise. The battery can be recharged quickly using a suitable lithium battery charger and is tested to UN regulations for transportation.\r\n', 'X5', 'BMW', 'Batteries', '139.99', 7, '/images/12.jpg'),
(52, 'Aliant Lithium Battery', 0, 'The Aliant Lithium Batteries are an ultra-compact product which has been developed specifically for high cracking application such as motorcycles, ATVs and Quads.\r\n', 'M4', 'BMW', 'Batteries', '130.00', 7, '/images/13.jpg'),
(53, 'Millers Oils CRO 10W40 Running In Oil', 0, 'CRO 10W40 is a motorsport oil designed to specifically to aid the running in process of rebuilt engines by providing full protection of highly stressed components whilst allowing optimum bedding in process for piston rings to the cylinder bore. It is not recommended that synthetic or semi synthetic oils are used for bedding in as they can be too slippery.\r\n', 'A5', 'Audi', 'Oils', '7.00', 18, '/images/14.jpg'),
(54, 'Miller Oils CFS NT Plus Nanodrive 5W40 Synthetic Motorsport Engine Oil', 0, 'CFS NT+ is a flagship synthetic engine oil from Millers Oils which has been developed specifically to satisfy for the demands of engines used in Motorsport. It features the highest level of Nanodrive technology found in any Millers Oils products resulting in a huge reduction in friction to enable maximum engine power output and efficiency with minimal wear. The 5W40 viscosity is suited to fast road/street modified engines, race, rally, sprints and hill climbs. Ideal for engines with hydraulic tappets such as Ford Zetec.\r\n', 'Q5', 'Audi', 'Oils', '13.80', 15, '/images/15.jpg'),
(55, 'Castrol Power 1 A747 2 Stroke Engine Oil', 0, 'The Castrol A747 is a castor/synthetic engine oil specifically designed for use in high performance 2-stroke engines. A747 is only recommended for engine that use premixed fuels because the viscosity is too high for use in oil injection systems.\r\n', 'S5', 'Audi', 'Oils', '16.99', 42, '/images/16.jpg'),
(56, 'Motul 300V Competition Synthetic Motorsport Engine Oil ', 0, 'This latest version of Motul\'s 300V synthetic Motorsport engine oil is the culmination of over 50 years of research and development. The result is an oil which allows for maximum engine performance with maximum reliability. 300V Competition is available in mid viscosity grades which are ideal for applications where a balance of power and reliability is required. Recommended for most mid to long distance race and rally applications.\r\n', 'Z4', 'BMW', 'Oils', '37.99', 22, '/images/17.jpg'),
(57, 'Valvoline VR1 Racing Oil', 0, 'With over 100 years of motor racing innovation and experience, Valvoline VR1 Racing lubricants are the leading edge of racing oil technology. Formulated to increase horsepower and protect high performance engines under race conditions, it provides the competitive advantage professional racers and enthusiasts seek.\r\n', 'X5', 'BMW', 'Oils', '8.39', 34, '/images/18.jpg'),
(58, 'Driven Racing Oil HR-1 15W50 Engine Oil', 0, 'HR-1 is a 15W50 engine oil by Driven Racing Oils (Joe Gibbs) which is suitable for older style pushrod (OHV) engines and has been developed to provide increased protection for camshafts. HR-1 also includes additives which protect against rust and corrosion even when the engine isn\'t running. This makes it ideal for classic cars which aren\'t used as daily drivers.\r\n', 'M4', 'BMW', 'Oils', '9.99', 64, '/images/19.jpg'),
(59, 'Garrett GTX2867R Gen II Turbocharger Assembly Kit', 4, 'Garrett turbocharger assembly kits are supplied as a super core consisting of a centre housing rotating assembly (CHRA) with a compressor housing attached. A turbine housing is supplied but not fitted. Gaskets are included to complete the assembly. \r\n', 'A5', 'Audi', 'Turbo', '1299.99', 2, '/images/20.jpg'),
(60, 'Garrett GT2871RS Turbocharger', 0, 'The GT2871RS is a Garrett turbocharger suited to engines with a capacity of 1.8-3.0L and is capable of producing between 250-360 Bhp. The turbo features dual 8mm ceramic ball bearing cartridges designed for faster spooling with high efficiency. The GT2871RS turbo utilises an Inconel turbine wheel which is able to withstand the harshest of conditions. An internal wastegate is used and the turbo unit is supplied complete with an adjustable wastegate actuator. Fits T25 exhaust flange.\r\n', 'Q5', 'Audi', 'Turbo', '995.99', 5, '/images/21.jpg'),
(61, 'Garrett GT2860R Turbocharger', 0, 'The GT2860R is a Garrett turbocharger suited to engines with a capacity of 1.8-3.0L and is capable of producing between 250-360 Bhp. The turbo features dual 8mm ceramic ball bearing cartridges designed for faster spooling with high efficiency. The GT2860R turbo utilises an Inconel cast turbine wheel capable of withstanding the harshest of conditions. An internal wastegate is used and the turbo unit is supplied complete with a wastegate actuator. Fits T25 exhaust flange.\r\n', 'S5', 'Audi', 'Turbo', '720.99', 6, '/images/22.jpg'),
(62, 'Turbo Technics Hybrid Turbo Charger', 0, 'Turbo Technics Hybrid Performance Turbos are a direct fit upgrade that offers increased performance gains over your vehicles stock turbo, using precision-engineered components and modifications that improve all aspects of the OE construction and ensure your turbo performs better and lasts longer than any other.\r\n', 'Z4', 'BMW', 'Turbo', '889.00', 5, '/images/23.jpg'),
(63, 'Revo KO4 Turbo Kit EA888', 0, 'Revo Technik take power very seriously as should be evident from this K04 upgrade kit. Volkswagen group specified two families of turbo on their 2.0 litre turbocharged petrol engines - the smaller turbos found on most models and the larger K04 on the \"hottest\" models. This kit allows you to upgrade 2.0 litre petrol engines with the smaller turbo to the bigger K04 turbo.\r\n', 'X5', 'BMW', 'Turbo', '1545.99', 7, '/images/24.jpg'),
(64, 'HKS GT3-RS Sports Turbine Kit', 0, 'HKS\' GT III Turbo is a new generation of turbo combining MHI brand CHRA with HKS original housings designed from the long term know-how HKS has accumulated over many years.\r\n', 'A5', 'Audi', 'Turbo', '1750.00', 2, '/images/25.jpg'),
(65, 'Garrett GT2560R Turbocharger', 0, 'The GT2560R is a Garrett turbocharger which is fitted as OEM on Nissan SR20DET powered vehicles but is also suitable for fitment to other engines with a capacity of 1.4 to 2.2 litre. It is an ideal upgrade for a GT2554R turbocharger and is capable of producing up to 270 Bhp. The GT2560R features ball bearings for improved spooling and efficiency and has an internal wastegate. The unit is supplied complete with a wastegate actuator.\r\n', 'Q5', 'Audi', 'Turbo', '634.99', 6, '/images/26.jpg'),
(66, 'Garrett GT2860RS Turbocharger', 0, 'The GT860RS is a Garrett turbocharger suited to engines with a capacity of 1.8-3.0L and is capable of producing between 250-360 Bhp. The turbo features dual 8mm ceramic ball bearing cartridges designed for faster spooling with high efficiency. The GT2860RS turbo utilises an Inconel turbine wheel which is able to withstand the harshest of conditions. An internal wastegate is used and the turbo unit is supplied complete with a wastegate actuator. Fits T25 exhaust flange.\r\n', 'Q5', 'Audi', 'Turbo', '751.99', 3, '/images/27.jpg'),
(67, 'Super B Mason 12V25AH Lithium Ion Battery With Integral BMS', 0, 'Super B Mason 12V25AH (formerly SP25P-JC) batteries are based on Lithium Ion technology which enables them to generate a large power output with the fraction of the weight of a traditional lead acid battery. The technology used by Super B makes the batteries the safest Lithium Ion versions available. This particular model features an integral battery management system (BMS) which ensures the highest level of safety during use.\r\n', 'S5', 'Audi', 'Batteries', '1110.99', 5, '/images/28.jpg'),
(68, 'Braille Xcel-Lite Lithium Battery', 0, 'Braille Xcel-Lite is a range of ultra lightweight, high performance lithium batteries which provide huge cranking currents compared to their size and weight. Braille batteries have been successfully tried and tested in top level Motorsport including wins in F1, Le Mans 24hr, Daytona 500 and Indy 500. Xcel-Lite batteries deliver the safest, most reliable high performance upgrade from traditional lead acid batteries for starting applications.\r\n', 'Z4', 'BMW', 'Batteries', '450.99', 6, '/images/29.jpg'),
(69, 'Exide Maxxima 900DC Battery', 3, 'Exide\'s Revolutionary Orbital Grid Technology takes batteries to a whole new level! The Exide Maxxima 900DC high performance battery is developed for applications where high cold cranking currents and shockproof construction are demanded.\r\n', 'X5', 'BMW', 'Batteries', '197.99', 5, '/images/30.jpg'),
(70, 'Motul Ford / Jaguar / Mazda Specific 948B 5W20 Synthetic Engine Oil', 0, 'Ford - 100% Synthetic Fuel Economy Engine Oil specially formulated to lubricate the latest generation of Ford gasoline engines, apart from exceptions. Meets Ford WSS M2C 948-B specification which is especially required for the 1.0L EcoBoost 3-cylinder engines, but is also fully compatible with some other Ford petrol engines.\r\n', 'M4', 'BMW', 'Oils', '149.99', 7, '/images/31.jpg'),
(71, 'Red Line 20WT Race Engine Oil', 0, 'This 20WT race engine oil from Red Line is a fully synthetic multi-grade oil with an SAE viscosity of 5W20. Red Line race engine oils are a \'no-compromise\' oil specifically designed specifically to provide the ultimate in protection and power release for Motorsport engines. They are not required to meet manufacturer approvals for emissions, economy or drain intervals so the additives package is purely for maximum performance and protection for engines under extreme conditions.\r\n', 'A5', 'Audi', 'Oils', '89.99', 8, '/images/32.jpg'),
(72, 'Fuchs Titan Race Pro R 10W40 Engine Oil', 0, 'Fuchs Titan Race Pro R 10W40 is a synthetic engine oil developed to provide excellent levels of protection for highly stressed engines such as those found in high performance road cars or competition cars. Suitable for use in petrol engines Titan Race Pro R offers maximum fuel efficiency and reduced emissions while maximising engine performance.\r\n', 'Q5', 'Audi', 'Oils', '49.99', 6, '/images/33.jpg'),
(73, 'AP Racing Factory Competition Brake Kits', 0, 'AP Racing are the worlds number one brake specialists and have applied their unrivaled experience into developing a range of brake kits suitable for competition use. Designed as a direct bolt on replacement to the original brake set up, however larger aftermarket wheels are usually required for clearance.\r\n', 'S5', 'Audi', 'Brakes', '2959.99', 1, '/images/34.jpg'),
(74, 'Tarox Grande Sport Big Brake Kit w/Strada Brake Pads & F2000 Discs', 4, 'Tarox Big Brake Kits are complete, ready to fit, big brake kits are suitable for high performance road and trackday use. They offer the highest level of braking for road and trackday cars. Upgrading to a Tarox brake kit will give you amazing braking performance and total confidence in your vehicle.\r\n', 'Z4', 'BMW', 'Brakes', '3600.00', 0, '/images/35.jpg'),
(75, 'AP Racing Factory Big Brake Kits', 0, 'AP Racing Factory Big Brake Kits are complete, ready to fit, brake kits suitable for high performance road and trackday use. By utilising high performance 4 or 6 piston alloy calipers and larger diameter 2 piece discs they offer the highest level of braking for road and trackday cars.\r\n', 'X5', 'BMW', 'Brakes', '3139.99', 2, '/images/36.jpg'),
(77, 'demo', 0, 'sdaf', 'Q5', 'Audi', 'Turbo', '234.00', 2, '/images/image-missing-stars-mods.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `comment` text NOT NULL,
  `hidden` tinyint(1) NOT NULL DEFAULT 0,
  `date_created` datetime NOT NULL,
  `rating` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `user_id`, `product_id`, `comment`, `hidden`, `date_created`, `rating`) VALUES
(1, 1, 1, 'asdf', 0, '2023-03-18 23:54:36', 4),
(2, 1, 1, 'Fantastic product!', 1, '2023-03-19 01:26:57', 5),
(3, 1, 1, 'Meh, could have been easier to set up.', 0, '2023-03-19 01:27:58', 2),
(4, 1, 1, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. In aspernatur iste nesciunt quaerat est possimus saepe, doloribus error quia, quibusdam ipsum maiores nemo voluptas sunt deleniti nihil placeat! Dolores, sint.', 0, '2023-03-19 02:11:54', 3),
(5, 2, 2, 'Decent product, could have been better!', 1, '2023-03-19 16:32:41', 3),
(6, 1, 3, 'asdf', 1, '2023-03-20 17:15:02', 5),
(7, 2, 3, 'What a great product this was! Absolutely amazing 🤩', 0, '2023-03-25 17:26:14', 4),
(8, 2, 4, 'Bad product!', 1, '2023-03-25 18:58:46', 1),
(9, 4, 4, 'What a fantastic product!', 0, '2023-03-28 16:34:33', 4),
(10, 10, 69, 'Great product, absolutely love it!', 0, '2023-03-29 01:26:31', 3),
(11, 10, 40, 'I found these really easy to install, but the quality could have been better.', 0, '2023-03-29 01:27:25', 3),
(12, 10, 59, 'Fantastic product, love it!', 0, '2023-03-29 01:28:09', 4),
(13, 10, 1, 'Brilliant stuff!', 0, '2023-03-29 01:29:10', 5),
(14, 2, 2, 'Had a blast with it! Would definitely recommend!', 0, '2023-03-29 01:30:17', 4),
(15, 1, 2, 'Very high quality product!', 0, '2023-03-29 01:31:00', 5),
(16, 11, 74, 'Amazing', 1, '2023-03-29 10:38:20', 4);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `roles` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `email`, `password`, `roles`) VALUES
(1, 'jas', 'jas', 'jas@gmail.com', '$2a$10$dYV1i.tvnNiknXECBMxc9.9OJ/7m8bWX27x3nVvDeg7yVrhyz69CO', 'ROLE_USER'),
(2, 'zed', 'zed', 'zed@gmail.com', '$2a$10$zuTmmyQjIamufcQi8XGGauiuKz9iphAcqq1IxQI8aH0IFD.fIxpUu', 'ROLE_USER'),
(3, 'lux', 'lux', 'lux@gmail.com', '$2a$10$rt1S4d9p1NtBgB89jMnt8u5uRHo9s7udWgF6MFHCnvVef7x7HOyeq', 'ROLE_USER'),
(4, 'admin', 'admin', 'admin@gmail.com', '$2a$10$Ys0nbDIA2EoRkqSJ3sug6uq5TRrd3MqoD8SQWwJH.vMvGNth.tfha', 'ROLE_ADMIN'),
(5, 'bilbo', 'bilbo', 'bilbo@gmail.com', '$2a$10$WmgvpzA4RUUVYeQQSkvG3esGyJq8NVp0rqCUY79t2iESSSJyXgSTW', 'ROLE_USER'),
(8, 'qwer', 'qwer', 'qwer@qwer', '$2a$10$Kfu7XWOB6Rn.JaqVMeVye.ucLIMZWTdp3aq1thYflNjUJ5Zj77O0S', 'ROLE_USER'),
(10, 'Frodo', 'Frodo', 'frodo@gmail.com', '$2a$10$ehUmQDvRffa/C4dQlfJlvOePSlQUiQlBp5EGg90Ak6/XGSTKWdFiq', 'ROLE_USER'),
(11, 'demo', 'Demo', 'demo@gmail.com', '$2a$10$OgealxonOJ9SC9G8h/QFqekLfUOxWvrE4BjuKUFxxGkrvBMQ4KV9q', 'ROLE_USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `basket`
--
ALTER TABLE `basket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `basket_products`
--
ALTER TABLE `basket_products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `basket_id` (`basket_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `flyway_schema_history`
--
ALTER TABLE `flyway_schema_history`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `flyway_schema_history_s_idx` (`success`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_id` (`user_id`);

--
-- Indexes for table `order_products`
--
ALTER TABLE `order_products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `basket`
--
ALTER TABLE `basket`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `basket_products`
--
ALTER TABLE `basket_products`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `order_products`
--
ALTER TABLE `order_products`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `basket`
--
ALTER TABLE `basket`
  ADD CONSTRAINT `basket_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `basket_products`
--
ALTER TABLE `basket_products`
  ADD CONSTRAINT `basket_products_ibfk_1` FOREIGN KEY (`basket_id`) REFERENCES `basket` (`id`),
  ADD CONSTRAINT `basket_products_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `order_products`
--
ALTER TABLE `order_products`
  ADD CONSTRAINT `order_products_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_products_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
