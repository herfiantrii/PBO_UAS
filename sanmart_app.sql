-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 05, 2024 at 09:08 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sanmart_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `question` varchar(100) NOT NULL,
  `answer` varchar(100) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `username`, `password`, `question`, `answer`, `date`) VALUES
(1, 'kiyya', 'kiyya123', 'what is your birth date?', '2 april', '2024-01-05'),
(2, 'rara', 'rara1234', 'What is your favorite Color?', 'black', '2024-01-05'),
(3, 'Akbar', 'akbar123', 'What is your favorite Color?', 'blue', '2024-01-05');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `question` varchar(100) NOT NULL,
  `answer` varchar(100) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `username`, `password`, `question`, `answer`, `date`) VALUES
(1, 'staff1', 'staff123', 'What is your favorite Color?', 'Orange', '2024-01-05'),
(2, 'amira', '0987654321', 'What is your favorite Color?', 'blue', '2024-01-05');

-- --------------------------------------------------------

--
-- Table structure for table `history_customer`
--

CREATE TABLE `history_customer` (
  `id` int(11) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `prod_id` varchar(100) NOT NULL,
  `prod_name` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` double NOT NULL,
  `date` date DEFAULT NULL,
  `image` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `history_customer`
--

INSERT INTO `history_customer` (`id`, `customer_id`, `prod_id`, `prod_name`, `type`, `quantity`, `price`, `date`, `image`) VALUES
(3, 1, 'P-05', 'Chemic Clean', 'Fluid', 1, 30000, '2024-01-05', 'C:\\\\\\\\Users\\\\\\\\H M NUR FATTAH\\\\\\\\OneDrive\\\\\\\\Gambar\\\\\\\\img_sanit\\\\\\\\chemic_clean.jpg'),
(4, 2, 'P-04', 'Carpet Extract', 'Tool', 1, 500000, '2024-01-05', 'C:\\\\\\\\Users\\\\\\\\H M NUR FATTAH\\\\\\\\OneDrive\\\\\\\\Gambar\\\\\\\\img_sanit\\\\\\\\carpet_extract.png'),
(6, 3, 'P-02', 'Vacuum Cleaner', 'Tool', 1, 60000, '2024-01-05', 'C:\\\\\\\\Users\\\\\\\\H M NUR FATTAH\\\\\\\\OneDrive\\\\\\\\Gambar\\\\\\\\img_sanit\\\\\\\\vacuum_clean.png'),
(7, 3, 'P-03', 'Hand Glove', 'Tool', 1, 10000, '2024-01-05', 'C:\\\\\\\\Users\\\\\\\\H M NUR FATTAH\\\\\\\\OneDrive\\\\\\\\Gambar\\\\\\\\img_sanit\\\\\\\\sarung_tangan.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `prod_id` varchar(100) NOT NULL,
  `prod_name` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `stock` int(100) NOT NULL,
  `price` double NOT NULL,
  `status` varchar(100) NOT NULL,
  `image` varchar(1000) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `prod_id`, `prod_name`, `type`, `stock`, `price`, `status`, `image`, `date`) VALUES
(1, 'P-01', 'Hand Sanitizer', 'Fluid', 196, 15000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\hand_sanit.jpg', '2024-01-05'),
(2, 'P-02', 'Vacuum Cleaner', 'Tool', 249, 60000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\vacuum_clean.png', '2024-01-05'),
(3, 'P-03', 'Hand Glove', 'Tool', 499, 10000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\sarung_tangan.jpg', '2024-01-05'),
(4, 'P-04', 'Carpet Extract', 'Tool', 149, 500000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\carpet_extract.png', '2024-01-05'),
(7, 'P-05', 'Chemic Clean', 'Fluid', 399, 30000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\chemic_clean.jpg', '2024-01-05'),
(8, 'P-06', 'Prokleen', 'Fluid', 700, 25000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\prokleen.png', '2024-01-05'),
(9, 'P-07', 'Flatmop', 'Tool', 600, 30000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\mop.jpg', '2024-01-05'),
(10, 'P-8', 'Q-SAN Disenfectant', 'Fluid', 800, 20000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\q-san.png', '2024-01-05'),
(12, 'P-9', 'Antiseptic  Wipes', 'Tool', 700, 15000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\tisu basah.jpg', '2024-01-05'),
(13, 'P-10', 'UV Light Strerilizer', 'Tool', 600, 80000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\UV Light strerilizer.jpg', '2024-01-05'),
(14, 'P-11', 'Air Furifier', 'Tool', 650, 10000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\Air furifier.jpg', '2024-01-05'),
(15, 'P-12', 'Broom', 'Tool', 500, 10000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\sapu.jpg', '2024-01-05'),
(16, 'P-13', 'Plunger', 'Tool', 500, 20000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\plunger.jpg', '2024-01-05'),
(18, 'P-14', 'Toilet Brush', 'Tool', 100, 10000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\TOILET BRASH.png', '2024-01-05'),
(19, 'P-15', 'Hand Wash', 'Fluid', 200, 20000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\HANDWASH.jpg', '2024-01-05'),
(20, 'P-16', 'Insect Cather UV', 'Tool', 200, 50000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\uv.jpg', '2024-01-05'),
(21, 'P-17', 'Window squeegee', 'Tool', 200, 20000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\Window squeegee.jpg', '2024-01-05'),
(22, 'P-18', 'Botol Sprayer', 'Tool', 200, 20000, 'Available', 'C:\\\\Users\\\\H M NUR FATTAH\\\\OneDrive\\\\Gambar\\\\img_sanit\\\\Botol Sprayer.png', '2024-01-05');

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `id` int(11) NOT NULL,
  `customer_id` int(100) NOT NULL,
  `prod_name` varchar(100) NOT NULL,
  `total` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`id`, `customer_id`, `prod_name`, `total`, `date`) VALUES
(1, 1, 'Hand Sanitizer', 15000, '2024-01-05'),
(2, 1, 'Chemic Clean', 30000, '2024-01-05'),
(3, 2, 'Carpet Extract', 500000, '2024-01-05'),
(4, 3, 'Hand Sanitizer', 30000, '2024-01-05'),
(5, 3, 'Vacuum Cleaner, Hand Glove', 70000, '2024-01-05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `history_customer`
--
ALTER TABLE `history_customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `history_customer`
--
ALTER TABLE `history_customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
