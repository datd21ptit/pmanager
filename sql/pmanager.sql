-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2024 at 04:11 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pmanager`
--

-- --------------------------------------------------------

--
-- Table structure for table `cmment`
--

CREATE TABLE `cmment` (
  `id_cm` int(11) NOT NULL,
  `id_of` int(11) NOT NULL,
  `id_jb` int(11) NOT NULL,
  `content` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE `jobs` (
  `id_jb` int(11) NOT NULL,
  `id_pj` int(11) NOT NULL,
  `id_do` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `info` varchar(100) NOT NULL,
  `ddline` varchar(10) NOT NULL,
  `isfinish` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`id_jb`, `id_pj`, `id_do`, `name`, `info`, `ddline`, `isfinish`) VALUES
(1, 10, 44, 'Front-end', 'Lam front-end cho trang web', '25/04/2024', 0),
(3, 11, 32, 'Front-ent', 'Hello pc', '29/04/2024', 0),
(4, 12, 56, 'jdnjs', 'Hello', '27/04/2024', 0),
(5, 10, 55, 'Backend', 'Làm backend', '29/04/2024', 0),
(6, 10, 56, ' Requriement flow', 'Pha lấy yêu cầu', '01/05/2024', 0),
(7, 10, 54, 'Testing flow', 'Test s p', '30/06/2024', 1),
(8, 11, 54, 'Module tinh cong', 'Cham cong cho nhan vien', '28/04/2024', 0),
(9, 25, 61, 'Test module 1', 'Tabv', '30/04/2024', 1),
(10, 24, 61, 'Test module 5', 'tsabma', '31/05/2024', 1),
(11, 10, 29, 'hả', 'Hello', '31/05/2024', 1),
(12, 12, 29, 'wegerhgf', 'sfegds', '30/04/2024', 1),
(13, 10, 29, 'wev', 'fsdefss', '27/04/2024', 1);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id_us` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id_us`, `username`, `password`) VALUES
(54, 'bdk3osk', '185c52780fe843bbc2ccea78e373e594'),
(29, 'dat1', '827ccb0eea8a706c4c34a16891f84e7b'),
(32, 'dat12', '827ccb0eea8a706c4c34a16891f84e7b'),
(44, 'dat123', '827ccb0eea8a706c4c34a16891f84e7b'),
(50, 'dat1234', '827ccb0eea8a706c4c34a16891f84e7b'),
(52, 'datt1', '827ccb0eea8a706c4c34a16891f84e7b'),
(57, 'datt12', '827ccb0eea8a706c4c34a1689'),
(60, 'datt123', '827ccb0eea8a706c4c34a16891f84e7b'),
(56, 'jdkwlsk', 'f44e8d37380d6c4975924a6e398c03b7'),
(55, 'jznznsk', '818a113c59cfa60113bcda36c407ee89'),
(61, 'user1', '24c9e15e52afc47c225b757e7bee1f9d');

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `id_pj` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `id_creator` int(11) NOT NULL,
  `info` varchar(100) NOT NULL,
  `deadline` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`id_pj`, `name`, `id_creator`, `info`, `deadline`) VALUES
(10, 'Web ban hang', 29, 'Xay dung web ban hang cho anh N', '15/12/2024'),
(11, 'Quản lý nhân sự', 29, 'App quản lý nhân sự cho AAA', '29/05/2024'),
(12, 'Chấm thi', 29, 'Xây dựng phần mềm chấm thi trắc nghiệm tự động', '29/04/2024'),
(13, 'ABCCC', 29, 'Dự án gì đó mô tả vân vân', '05/05/2024'),
(14, 'EFG', 32, 'Dự án gì đó mô tả vân vân', '05/05/2024'),
(15, 'FGH', 32, 'Mô tả dự án gì gì đó làm gì đó vân vân mây mây', '02/045/202'),
(16, 'name', 29, 'asc', 'deadline]'),
(17, 'name', 29, 'asc', 'deadline]'),
(18, 'Web bán hàng', 29, 'Xây dựng web bán hàng cho thương hiệu X_Y', '25/04/2024'),
(19, 'name', 29, 'asc', 'deadline]'),
(20, 'hrllo', 29, 'hello', '20/04/2024'),
(21, 'Hi', 29, 'Hello', '28/04/2024'),
(22, 'Quản lý sinh viên', 29, 'Ứng dụng quản lý sinh viên bằng java jframe', '24/04/2024'),
(23, 'Quản lý bán hàng', 29, 'Ứng dụng quản lý bán hàng', '26/04/2024'),
(24, 'Face ID', 29, 'Nhận diện gương mặt với CNN', '19/04/2024'),
(25, 'Web shop giày', 29, 'Web bán hàng cho hãng giày abc', '28/04/2024');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` int(11) NOT NULL,
  `mota` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sbmit`
--

CREATE TABLE `sbmit` (
  `id_sm` int(11) NOT NULL,
  `id_jb` int(11) NOT NULL,
  `file` varchar(100) NOT NULL,
  `time` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fullname` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `role` varchar(25) NOT NULL,
  `image` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fullname`, `email`, `role`, `image`) VALUES
(29, 'Nguyen tran dat', '123', 'Project Manager', '20240321_1348111712932797165.jpg'),
(32, 'Nguyen tran dat', '1234', 'MANAGER', 'localhost/serverTTCS/image/20240229_13242517092168'),
(44, 'Nguyen tran dat', '12345', 'MANAGER', NULL),
(50, 'Nguyen tran dat', '123456', 'MANAGER', NULL),
(52, 'Nguyen tran dat', 'a123456', 'MANAGER', NULL),
(54, 'dajr', 'ndnekdk', 'jsnrk', NULL),
(55, 'fajsk', 'hdbrjdk', 'jdjekk', NULL),
(56, 'datjekd', 'jdneksk', 'idkwnsn', NULL),
(57, 'Nguyen tran dat', 'a12345assc6', 'MANAGER', NULL),
(60, 'Nguyen tran dat', 'datmail', 'MANAGER', NULL),
(61, 'Nguyễn Văn A', 'dat@gmail.com', 'Tester', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_project_role`
--

CREATE TABLE `user_project_role` (
  `id_pj` int(11) NOT NULL,
  `id_us` int(11) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cmment`
--
ALTER TABLE `cmment`
  ADD PRIMARY KEY (`id_cm`),
  ADD KEY `id_of` (`id_of`),
  ADD KEY `id_jb` (`id_jb`);

--
-- Indexes for table `jobs`
--
ALTER TABLE `jobs`
  ADD PRIMARY KEY (`id_jb`),
  ADD KEY `id_pj` (`id_pj`),
  ADD KEY `id_do` (`id_do`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `id_us` (`id_us`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`id_pj`),
  ADD KEY `id_creator` (`id_creator`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sbmit`
--
ALTER TABLE `sbmit`
  ADD PRIMARY KEY (`id_sm`),
  ADD KEY `id_jb` (`id_jb`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_project_role`
--
ALTER TABLE `user_project_role`
  ADD KEY `id_pj` (`id_pj`),
  ADD KEY `id_role` (`id_role`),
  ADD KEY `id_us` (`id_us`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cmment`
--
ALTER TABLE `cmment`
  MODIFY `id_cm` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `jobs`
--
ALTER TABLE `jobs`
  MODIFY `id_jb` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `id_pj` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sbmit`
--
ALTER TABLE `sbmit`
  MODIFY `id_sm` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cmment`
--
ALTER TABLE `cmment`
  ADD CONSTRAINT `cmment_ibfk_1` FOREIGN KEY (`id_of`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `cmment_ibfk_2` FOREIGN KEY (`id_jb`) REFERENCES `jobs` (`id_jb`);

--
-- Constraints for table `jobs`
--
ALTER TABLE `jobs`
  ADD CONSTRAINT `jobs_ibfk_1` FOREIGN KEY (`id_pj`) REFERENCES `project` (`id_pj`),
  ADD CONSTRAINT `jobs_ibfk_2` FOREIGN KEY (`id_do`) REFERENCES `users` (`id`);

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`id_us`) REFERENCES `users` (`id`);

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `project_ibfk_1` FOREIGN KEY (`id_creator`) REFERENCES `users` (`id`);

--
-- Constraints for table `sbmit`
--
ALTER TABLE `sbmit`
  ADD CONSTRAINT `sbmit_ibfk_1` FOREIGN KEY (`id_jb`) REFERENCES `users` (`id`);

--
-- Constraints for table `user_project_role`
--
ALTER TABLE `user_project_role`
  ADD CONSTRAINT `user_project_role_ibfk_1` FOREIGN KEY (`id_pj`) REFERENCES `project` (`id_pj`),
  ADD CONSTRAINT `user_project_role_ibfk_2` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `user_project_role_ibfk_3` FOREIGN KEY (`id_us`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
