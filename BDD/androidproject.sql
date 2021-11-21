-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2021 at 10:45 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `androidproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `clientID` int(10) UNSIGNED NOT NULL,
  `fName` varchar(100) DEFAULT NULL,
  `lName` varchar(100) DEFAULT NULL,
  `birthDate` timestamp NOT NULL DEFAULT '1980-08-03 00:00:00',
  `photo` varchar(50) DEFAULT NULL,
  `identityDoc` enum('CINE','EPORT','SEJOUR','') DEFAULT NULL,
  `identityNumber` varchar(30) DEFAULT NULL,
  `inscriptionDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `ensurenceValidity` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `licenceValidity` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `clientPhone` varchar(15) DEFAULT NULL,
  `priceRate` smallint(5) UNSIGNED NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1,
  `notes` mediumtext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`clientID`, `fName`, `lName`, `birthDate`, `photo`, `identityDoc`, `identityNumber`, `inscriptionDate`, `ensurenceValidity`, `licenceValidity`, `email`, `password`, `clientPhone`, `priceRate`, `isActive`, `notes`) VALUES
(1, 'Mohammed', 'BELLAMINE', '2000-09-13 00:00:00', 'Mohammed-BELLAMINE-1.jpeg', 'CINE', 'AB15243', '2020-09-12 23:00:00', '2021-09-12 00:00:00', '0000-00-00 00:00:00', 'mohammed.belatar@gmail.com', 'password', '+212661661661', 100, 1, ''),
(2, 'Hassan', 'Abouabdellah', '1992-09-13 00:00:00', 'Hassan-Abouabdellah-2.jpeg', 'CINE', 'AB213636', '2020-09-13 07:13:30', '2020-11-15 00:00:00', '0000-00-00 00:00:00', 'mohammed.belatar1@gmail.com', 'password', '+212661661661', 100, 1, ''),
(6, 'Ayoub', 'BELKHADIR', '1988-09-13 00:00:00', 'default.jpg', 'CINE', 'AB213636', '2020-09-13 07:13:30', '2020-11-15 00:00:00', '0000-00-00 00:00:00', 'mohammed.belatar2@gmail.com', 'password', '+212661661661', 100, 1, ''),
(5, 'Hassan', 'RAHMOUNI', '1982-09-13 09:54:15', 'default.jpg', 'CINE', 'AB15243', '2020-09-13 07:13:30', '2021-09-12 23:00:00', '0000-00-00 00:00:00', 'mohammed.belatar3@gmail.com', 'password', '+212661661661', 100, 1, ''),
(7, 'Sofia', 'KAMALI', '1990-09-13 00:00:00', 'Sofia-KAMALI-7.jpeg', 'CINE', 'AB213636', '2020-09-13 07:13:30', '2020-11-15 00:00:00', '0000-00-00 00:00:00', 'mohammed.belatar4@gmail.com', 'password', '+212661661661', 100, 1, ''),
(8, 'Hanae', 'BENZINE', '1992-09-13 09:54:15', 'default.jpg', 'CINE', 'AB15243', '2020-09-13 07:13:30', '2021-09-12 23:00:00', '0000-00-00 00:00:00', 'mohammed.belatar5@gmail.com', 'password', '+212661661661', 100, 1, ''),
(9, 'Fadwa', 'KOUROUKOU', '1992-09-13 09:54:15', 'default.jpg', 'CINE', 'AB15243', '2020-09-13 07:13:30', '2021-09-12 23:00:00', '0000-00-00 00:00:00', 'mohammed.belatar6@gmail.com', 'password', '+212661661661', 100, 1, ''),
(10, 'omar', 'bailal', '1992-09-19 00:00:00', 'default.jpg', '', 'AB15243sss', '2020-09-13 11:40:43', '2020-09-18 23:00:00', '2020-10-31 00:00:00', 'omarbailal45@gmail.com', 'password', '+212661661661', 1001, 1, 'sz'),
(11, 'zineb', 'belabadi', '1980-08-03 00:00:00', NULL, NULL, NULL, '2021-06-02 15:01:55', '2021-06-02 14:01:55', '2021-06-02 14:01:55', 'zineb.belabadi@gmail.com', 'password', '0666666666', 1, 1, 'test'),
(13, 'amin', 'aminox', '1980-08-03 00:00:00', NULL, NULL, NULL, '2021-06-03 18:39:44', '2021-06-03 17:39:44', '2021-06-03 17:39:44', 'laminox@gmail.com', 'password', '07000000', 1, 1, 'test'),
(14, 'ahmed', 'laghmari', '1980-08-03 00:00:00', NULL, NULL, NULL, '2021-06-03 19:48:30', '2021-06-03 18:48:30', '2021-06-03 18:48:30', 'email@gmail.com', 'password', '0700000', 1, 1, 'test'),
(15, 'amjad', 'MJIDO', '1980-08-03 00:00:00', NULL, NULL, NULL, '2021-06-06 19:47:13', '2021-06-06 18:47:13', '2021-06-06 18:47:13', 'amad@gmail.com', 'password', '07000000', 1, 1, 'test'),
(16, 'amine', 'achouham', '1980-08-03 00:00:00', NULL, NULL, NULL, '2021-06-06 20:26:01', '2021-06-06 19:26:01', '2021-06-06 19:26:01', 'amin@gmail.com', 'pssweor', '06000000', 1, 1, 'test');

-- --------------------------------------------------------

--
-- Table structure for table `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uuid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2021_04_26_173559_create_clients_table', 0),
(2, '2021_04_26_173559_create_seances_table', 0),
(3, '2021_04_26_173559_create_tasks_table', 0),
(4, '2021_04_26_173559_create_user_table', 0),
(5, '2014_10_12_000000_create_users_table', 1),
(6, '2014_10_12_100000_create_password_resets_table', 1),
(7, '2019_08_19_000000_create_failed_jobs_table', 1),
(8, '2016_06_01_000001_create_oauth_auth_codes_table', 2),
(9, '2016_06_01_000002_create_oauth_access_tokens_table', 2),
(10, '2016_06_01_000003_create_oauth_refresh_tokens_table', 2),
(11, '2016_06_01_000004_create_oauth_clients_table', 2),
(12, '2016_06_01_000005_create_oauth_personal_access_clients_table', 2),
(13, '2021_05_10_033317_create_clients_table', 0),
(14, '2021_05_10_033317_create_failed_jobs_table', 0),
(15, '2021_05_10_033317_create_oauth_access_tokens_table', 0),
(16, '2021_05_10_033317_create_oauth_auth_codes_table', 0),
(17, '2021_05_10_033317_create_oauth_clients_table', 0),
(18, '2021_05_10_033317_create_oauth_personal_access_clients_table', 0),
(19, '2021_05_10_033317_create_oauth_refresh_tokens_table', 0),
(20, '2021_05_10_033317_create_password_resets_table', 0),
(21, '2021_05_10_033317_create_seances_table', 0),
(22, '2021_05_10_033317_create_tasks_table', 0),
(23, '2021_05_10_033317_create_user_table', 0),
(24, '2021_05_10_033317_create_users_table', 0);

-- --------------------------------------------------------

--
-- Table structure for table `oauth_access_tokens`
--

CREATE TABLE `oauth_access_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_auth_codes`
--

CREATE TABLE `oauth_auth_codes` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_clients`
--

CREATE TABLE `oauth_clients` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `secret` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `provider` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `redirect` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `personal_access_client` tinyint(1) NOT NULL,
  `password_client` tinyint(1) NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_clients`
--

INSERT INTO `oauth_clients` (`id`, `user_id`, `name`, `secret`, `provider`, `redirect`, `personal_access_client`, `password_client`, `revoked`, `created_at`, `updated_at`) VALUES
(1, NULL, 'Laravel Personal Access Client', 'U8DmOBRtqoQ7HbKi9qLny6OOEKM6nSB8voBkbAP1', NULL, 'http://localhost', 1, 0, 0, '2021-04-27 21:43:17', '2021-04-27 21:43:17'),
(2, NULL, 'Laravel Password Grant Client', 'FqzQkRKhwW1RHmNDpG8RpRNJtqqqDdZeIMF90zPw', 'users', 'http://localhost', 0, 1, 0, '2021-04-27 21:43:17', '2021-04-27 21:43:17');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_personal_access_clients`
--

CREATE TABLE `oauth_personal_access_clients` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_personal_access_clients`
--

INSERT INTO `oauth_personal_access_clients` (`id`, `client_id`, `created_at`, `updated_at`) VALUES
(1, 1, '2021-04-27 21:43:17', '2021-04-27 21:43:17');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_refresh_tokens`
--

CREATE TABLE `oauth_refresh_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `access_token_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `seances`
--

CREATE TABLE `seances` (
  `id` int(10) UNSIGNED NOT NULL,
  `seanceGrpID` int(10) UNSIGNED DEFAULT NULL,
  `clientID` int(10) UNSIGNED NOT NULL,
  `monitorID` smallint(5) UNSIGNED NOT NULL,
  `startDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `durationMinut` tinyint(3) UNSIGNED NOT NULL DEFAULT 120,
  `isDone` int(1) NOT NULL DEFAULT 0,
  `paymentID` int(10) UNSIGNED DEFAULT 0,
  `comments` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `seances`
--

INSERT INTO `seances` (`id`, `seanceGrpID`, `clientID`, `monitorID`, `startDate`, `durationMinut`, `isDone`, `paymentID`, `comments`) VALUES
(1, 1, 1, 2, '2020-09-14 10:00:00', 120, 1, 1, ''),
(2, 1, 2, 2, '2020-09-14 09:00:00', 120, 1, 0, ''),
(3, 1, 6, 2, '2020-09-14 09:00:00', 120, 1, 0, ''),
(4, 1, 4, 3, '2020-09-14 09:00:00', 120, 0, 0, ''),
(5, 1, 1, 2, '2020-09-21 09:00:00', 120, 1, 1, ''),
(6, 1, 1, 2, '2020-09-28 09:00:00', 120, 1, 0, ''),
(158, 2, 2, 2, '2020-10-26 09:00:00', 150, 1, 0, ''),
(157, 2, 2, 2, '2020-10-19 09:00:00', 150, 1, 0, ''),
(156, 2, 2, 2, '2020-10-12 09:00:00', 150, 1, 0, ''),
(287, NULL, 13, 1, '2021-06-11 09:00:00', 120, 1, 0, NULL),
(159, 2, 10, 2, '2020-10-05 09:00:00', 150, 1, 0, ''),
(160, 2, 10, 2, '2020-10-12 09:00:00', 150, 1, 0, ''),
(161, 2, 10, 2, '2020-10-19 09:00:00', 150, 1, 0, ''),
(162, 2, 10, 2, '2020-10-26 09:00:00', 150, 1, 0, ''),
(163, 2, 9, 2, '2020-10-05 09:00:00', 150, 1, 0, ''),
(164, 2, 9, 2, '2020-10-12 09:00:00', 150, 1, 0, ''),
(165, 2, 9, 2, '2020-10-19 09:00:00', 150, 1, 0, ''),
(166, 2, 9, 2, '2020-10-26 09:00:00', 150, 1, 0, ''),
(167, 3, 2, 1, '2020-10-17 09:00:00', 120, 0, 0, ''),
(168, 3, 2, 1, '2020-10-24 09:00:00', 120, 0, 0, ''),
(169, 3, 2, 1, '2020-10-30 10:00:00', 120, 1, 0, ''),
(170, 3, 2, 1, '2020-11-06 10:00:00', 120, 1, 0, ''),
(171, 3, 2, 1, '2020-11-13 10:00:00', 120, 1, 0, ''),
(174, 3, 8, 4, '2020-10-30 10:00:00', 120, 1, 0, ''),
(175, 3, 8, 4, '2020-11-06 10:00:00', 120, 0, 0, ''),
(176, 3, 8, 4, '2020-11-13 10:00:00', 120, 0, 0, ''),
(177, 3, 9, 4, '2020-10-17 09:00:00', 120, 0, 0, ''),
(178, 3, 9, 4, '2020-10-24 09:00:00', 120, 0, 0, ''),
(179, 3, 9, 4, '2020-10-30 10:00:00', 120, 0, 0, ''),
(180, 3, 9, 4, '2020-11-06 10:00:00', 120, 0, 0, ''),
(181, 3, 9, 4, '2020-11-13 10:00:00', 120, 0, 0, ''),
(182, 4, 6, 2, '2020-10-31 14:00:00', 120, 1, 0, ''),
(183, 4, 6, 2, '2020-11-07 14:00:00', 120, 1, 0, ''),
(184, 4, 6, 2, '2020-11-14 14:00:00', 120, 1, 0, ''),
(185, 4, 6, 2, '2020-11-21 14:00:00', 120, 1, 0, ''),
(186, 4, 6, 2, '2020-11-28 14:00:00', 120, 1, 0, ''),
(187, 4, 1, 2, '2020-10-31 14:00:00', 120, 1, 0, ''),
(188, 4, 1, 2, '2020-11-07 14:00:00', 120, 1, 0, ''),
(189, 4, 1, 2, '2020-11-14 14:00:00', 120, 1, 0, ''),
(190, 4, 1, 2, '2020-11-21 14:00:00', 120, 1, 0, ''),
(191, 4, 1, 2, '2020-11-28 14:00:00', 120, 1, 0, ''),
(192, 4, 7, 2, '2020-10-31 14:00:00', 120, 1, 0, ''),
(193, 4, 7, 2, '2020-11-07 14:00:00', 120, 1, 0, ''),
(194, 4, 7, 2, '2020-11-14 14:00:00', 120, 1, 0, ''),
(195, 4, 7, 2, '2020-11-21 14:00:00', 120, 1, 0, ''),
(196, 4, 7, 2, '2020-11-28 14:00:00', 120, 1, 0, ''),
(197, 4, 5, 2, '2020-10-31 14:00:00', 120, 1, 0, ''),
(198, 4, 5, 2, '2020-11-07 14:00:00', 120, 1, 0, ''),
(199, 4, 5, 2, '2020-11-14 14:00:00', 120, 1, 0, ''),
(200, 4, 5, 2, '2020-11-21 14:00:00', 120, 1, 0, ''),
(201, 4, 5, 2, '2020-11-28 14:00:00', 120, 1, 0, ''),
(202, 5, 6, 6, '2020-11-01 10:00:00', 120, 0, 0, ''),
(203, 5, 6, 6, '2020-11-08 10:00:00', 120, 0, 0, ''),
(204, 5, 6, 6, '2020-11-15 10:00:00', 120, 0, 0, ''),
(205, 5, 6, 6, '2020-11-22 10:00:00', 120, 0, 0, ''),
(206, 5, 1, 6, '2020-11-01 10:00:00', 120, 0, 0, ''),
(207, 5, 1, 6, '2020-11-08 10:00:00', 120, 0, 0, ''),
(208, 5, 1, 6, '2020-11-15 10:00:00', 120, 0, 0, ''),
(209, 5, 1, 6, '2020-11-22 10:00:00', 120, 0, 0, ''),
(210, 5, 7, 6, '2020-11-01 10:00:00', 120, 0, 0, ''),
(211, 5, 7, 6, '2020-11-08 10:00:00', 120, 0, 0, ''),
(212, 5, 7, 6, '2020-11-15 10:00:00', 120, 0, 0, ''),
(213, 5, 7, 6, '2020-11-22 10:00:00', 120, 0, 0, ''),
(214, 6, 6, 6, '2020-10-31 10:00:00', 120, 0, 0, ''),
(215, 6, 6, 6, '2020-11-07 10:00:00', 120, 0, 0, ''),
(216, 6, 6, 6, '2020-11-14 10:00:00', 120, 0, 0, ''),
(217, 6, 6, 6, '2020-11-21 10:00:00', 120, 0, 0, ''),
(218, 6, 1, 6, '2020-10-31 10:00:00', 120, 0, 0, ''),
(219, 6, 1, 6, '2020-11-07 10:00:00', 120, 0, 0, ''),
(220, 6, 1, 6, '2020-11-14 10:00:00', 120, 0, 0, ''),
(221, 6, 1, 6, '2020-11-21 10:00:00', 120, 0, 0, ''),
(222, 7, 2, 2, '2020-12-19 14:00:00', 120, 1, 0, ''),
(223, 7, 2, 2, '2020-12-26 14:00:00', 120, 1, 0, ''),
(224, 7, 2, 2, '2021-01-02 14:00:00', 120, 1, 0, ''),
(225, 7, 2, 2, '2021-01-09 14:00:00', 120, 1, 0, ''),
(226, 7, 8, 2, '2020-12-19 14:00:00', 120, 1, 0, ''),
(227, 7, 8, 2, '2020-12-26 14:00:00', 120, 1, 0, ''),
(228, 7, 8, 2, '2021-01-02 14:00:00', 120, 1, 0, ''),
(229, 7, 8, 2, '2021-01-09 14:00:00', 120, 1, 0, ''),
(230, 7, 9, 2, '2020-12-19 14:00:00', 120, 1, 0, ''),
(231, 7, 9, 2, '2020-12-26 14:00:00', 120, 1, 0, ''),
(232, 7, 9, 2, '2021-01-02 14:00:00', 120, 1, 0, ''),
(233, 7, 9, 2, '2021-01-09 14:00:00', 120, 1, 0, ''),
(234, 8, 6, 4, '2020-12-21 14:00:00', 120, 0, 0, ''),
(235, 8, 6, 4, '2020-12-28 14:00:00', 120, 0, 0, ''),
(236, 8, 6, 4, '2021-01-04 14:00:00', 120, 0, 0, ''),
(237, 8, 1, 4, '2020-12-21 14:00:00', 120, 0, 0, ''),
(238, 8, 1, 4, '2020-12-28 14:00:00', 120, 0, 0, ''),
(239, 8, 1, 4, '2021-01-04 14:00:00', 120, 0, 0, ''),
(240, 9, 8, 4, '2020-12-25 09:00:00', 120, 0, 0, ''),
(241, 9, 8, 4, '2021-01-01 09:00:00', 120, 0, 0, ''),
(242, 9, 8, 4, '2021-01-08 09:00:00', 120, 0, 0, ''),
(243, 9, 9, 4, '2020-12-25 09:00:00', 120, 0, 0, ''),
(244, 9, 9, 4, '2021-01-01 09:00:00', 120, 0, 0, ''),
(245, 9, 9, 4, '2021-01-08 09:00:00', 120, 0, 0, ''),
(246, 10, 6, 2, '2021-01-07 09:15:00', 120, 1, 0, ''),
(247, 11, 2, 6, '2021-01-03 09:00:00', 120, 0, 0, ''),
(248, 11, 2, 6, '2021-01-10 09:00:00', 120, 0, 0, ''),
(249, 11, 2, 6, '2021-01-17 09:00:00', 120, 0, 0, ''),
(250, 11, 2, 6, '2021-01-24 09:00:00', 120, 0, 0, ''),
(251, 11, 2, 6, '2021-01-31 09:00:00', 120, 0, 0, ''),
(252, 11, 2, 6, '2021-02-07 09:00:00', 120, 0, 0, ''),
(253, 11, 2, 6, '2021-02-14 09:00:00', 120, 0, 0, ''),
(254, 11, 8, 6, '2021-01-03 09:00:00', 120, 0, 0, ''),
(255, 11, 8, 6, '2021-01-10 09:00:00', 120, 0, 0, ''),
(256, 11, 8, 6, '2021-01-17 09:00:00', 120, 0, 0, ''),
(257, 11, 8, 6, '2021-01-24 09:00:00', 120, 0, 0, ''),
(258, 11, 8, 6, '2021-01-31 09:00:00', 120, 0, 0, ''),
(259, 11, 8, 6, '2021-02-07 09:00:00', 120, 0, 0, ''),
(260, 11, 8, 6, '2021-02-14 09:00:00', 120, 0, 0, ''),
(261, 12, 2, 2, '2021-07-16 10:00:00', 120, 0, 0, ''),
(262, 12, 2, 2, '2021-07-30 12:00:00', 120, 0, 0, ''),
(263, 12, 2, 2, '2021-08-06 12:00:00', 120, 0, 0, ''),
(264, 12, 2, 2, '2021-08-13 12:00:00', 120, 0, 0, ''),
(265, 12, 10, 2, '2021-07-16 10:00:00', 120, 1, 0, ''),
(266, 12, 10, 2, '2021-07-30 12:00:00', 120, 1, 0, ''),
(267, 12, 10, 2, '2021-08-06 12:00:00', 120, 0, 0, ''),
(268, 12, 10, 2, '2021-08-13 12:00:00', 120, 1, 0, ''),
(269, 12, 6, 2, '2021-07-16 10:00:00', 120, 0, 0, ''),
(270, 12, 6, 2, '2021-07-30 12:00:00', 120, 0, 0, ''),
(271, 12, 6, 2, '2021-08-06 12:00:00', 120, 0, 0, ''),
(272, 12, 6, 2, '2021-08-13 12:00:00', 120, 0, 0, ''),
(286, NULL, 13, 1, '2021-06-04 09:00:00', 120, 1, 0, NULL),
(281, NULL, 10, 1, '2021-09-10 09:00:00', 120, 0, 0, NULL),
(275, NULL, 10, 1, '2021-09-03 09:00:00', 120, 0, 0, NULL),
(276, NULL, 10, 1, '2021-10-01 09:00:00', 120, 0, 0, NULL),
(277, NULL, 10, 1, '2021-10-08 09:00:00', 120, 0, 0, NULL),
(278, NULL, 10, 1, '2021-10-15 09:00:00', 120, 0, 0, NULL),
(279, NULL, 10, 1, '2021-10-22 09:00:00', 120, 0, 0, NULL),
(280, NULL, 10, 1, '2021-10-29 09:00:00', 120, 0, 0, NULL),
(282, NULL, 10, 1, '2021-09-17 09:00:00', 120, 0, 0, NULL),
(283, NULL, 10, 1, '2021-09-24 09:00:00', 120, 0, 0, NULL),
(288, NULL, 13, 1, '2021-06-18 09:00:00', 120, 0, 0, NULL),
(289, NULL, 13, 1, '2021-06-25 09:00:00', 120, 0, 0, NULL),
(290, NULL, 14, 1, '2021-06-04 09:00:00', 120, 1, 0, NULL),
(291, NULL, 14, 1, '2021-06-11 09:00:00', 120, 1, 0, NULL),
(292, NULL, 14, 1, '2021-06-18 09:00:00', 120, 0, 0, NULL),
(293, NULL, 14, 1, '2021-06-25 09:00:00', 120, 0, 0, NULL),
(294, NULL, 14, 1, '2021-07-02 09:00:00', 120, 0, 0, NULL),
(295, NULL, 14, 1, '2021-07-09 09:00:00', 120, 0, 0, NULL),
(296, NULL, 14, 1, '2021-07-16 09:00:00', 120, 0, 0, NULL),
(297, NULL, 14, 1, '2021-07-23 09:00:00', 120, 0, 0, NULL),
(298, NULL, 9, 1, '2021-07-02 09:00:00', 120, 0, 0, NULL),
(299, NULL, 9, 1, '2021-07-09 09:00:00', 120, 0, 0, NULL),
(300, NULL, 9, 1, '2021-07-16 09:00:00', 120, 0, 0, NULL),
(301, NULL, 9, 1, '2021-07-23 09:00:00', 120, 0, 0, NULL),
(302, NULL, 9, 1, '2021-07-30 09:00:00', 120, 0, 0, NULL),
(303, NULL, 10, 1, '2021-06-11 09:00:00', 120, 1, 0, NULL),
(304, NULL, 10, 1, '2021-06-18 09:00:00', 120, 0, 0, NULL),
(305, NULL, 10, 1, '2021-06-25 09:00:00', 120, 0, 0, NULL),
(306, NULL, 10, 1, '2021-11-06 09:00:00', 120, 0, 0, NULL),
(307, NULL, 10, 1, '2021-11-12 09:00:00', 120, 0, 0, NULL),
(308, NULL, 10, 1, '2021-11-19 09:00:00', 120, 0, 0, NULL),
(309, NULL, 10, 1, '2021-11-26 09:00:00', 120, 0, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `id` int(10) UNSIGNED NOT NULL,
  `startDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `durationMinut` tinyint(3) UNSIGNED NOT NULL DEFAULT 60,
  `title` varchar(100) NOT NULL,
  `detail` varchar(250) NOT NULL,
  `isDone` tinyint(1) NOT NULL DEFAULT 0,
  `user_Fk` smallint(5) UNSIGNED NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `startDate`, `durationMinut`, `title`, `detail`, `isDone`, `user_Fk`) VALUES
(1, '2020-11-12 14:00:00', 60, 'Longer chevaux', 'Sortir la jument Chehrazad pour la faire travailler aux obstacles de 1m30', 1, 2),
(2, '2020-11-03 15:00:00', 60, 'Longer chevaux', 'Sortir la jument Chehrazad pour la faire travailler aux obstacles de 1m30', 0, 1),
(3, '2020-10-30 13:30:00', 240, 'Travailler cheveaux', 'Monter et travailler les chevaux qui travaillent peu', 1, 2),
(4, '2020-11-06 13:30:00', 240, 'Travailler cheveaux', 'Monter et travailler les chevaux qui travaillent peu', 1, 2),
(5, '2020-11-13 13:30:00', 240, 'Travailler cheveaux', 'Monter et travailler les chevaux qui travaillent peu', 1, 2),
(6, '2020-11-20 13:30:00', 240, 'Travailler cheveaux', 'Monter et travailler les chevaux qui travaillent peu', 1, 2),
(7, '2020-11-27 13:30:00', 240, 'Travailler cheveaux', 'Monter et travailler les chevaux qui travaillent peu', 1, 2),
(8, '2020-12-04 13:30:00', 240, 'Travailler cheveaux', 'Monter et travailler les chevaux qui travaillent peu', 1, 2),
(9, '2020-12-11 13:30:00', 240, 'Travailler cheveaux', 'Monter et travailler les chevaux qui travaillent peu', 1, 2),
(10, '2020-12-18 13:30:00', 240, 'Travailler cheveaux', 'Monter et travailler les chevaux qui travaillent peu', 1, 2),
(11, '2020-12-25 13:30:00', 240, 'Travailler cheveaux', 'Monter et travailler les chevaux qui travaillent peu', 1, 2),
(12, '2021-01-01 13:30:00', 240, 'Travailler cheveaux', 'Monter et travailler les chevaux qui travaillent peu', 1, 2),
(13, '2021-07-25 10:30:00', 120, 'débourrage', 'débourrage des juments 1,2,,3...', 0, 2),
(14, '2021-08-01 10:30:00', 120, 'débourrage', 'débourrage des juments 1,2,,3...', 0, 2),
(15, '2021-08-08 10:30:00', 120, 'débourrage', 'débourrage des juments 1,2,,3...', 0, 2),
(16, '2021-08-15 10:30:00', 120, 'débourrage', 'débourrage des juments 1,2,,3...', 0, 2),
(30, '2021-06-07 09:00:00', 120, 'title', 'jblknlknm onkmkm', 1, 1),
(27, '2021-06-12 09:00:00', 120, 'test', 'tess tessst tesstt', 1, 1),
(28, '2021-06-16 09:00:00', 120, 'test', 'tess tessst tesstt', 1, 1),
(29, '2021-06-11 09:00:00', 120, 'title', 'test test etst uvjkn', 0, 1),
(23, '2021-06-04 09:00:00', 120, 'test', 'test test test', 1, 6),
(24, '2021-06-11 09:00:00', 120, 'test', 'test test test', 0, 6),
(33, '2021-06-09 09:00:00', 120, 'titre', 'bla bla bla', 0, 1),
(34, '2021-06-16 09:00:00', 120, 'titre', 'bla bla bla', 0, 1),
(35, '2021-06-30 09:00:00', 120, 'titre', 'bla bla bla', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` smallint(5) UNSIGNED NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `adminLevel` tinyint(3) UNSIGNED DEFAULT NULL,
  `lastLoginTime` timestamp NOT NULL DEFAULT current_timestamp(),
  `isActive` tinyint(1) NOT NULL DEFAULT 1,
  `userFName` varchar(100) NOT NULL,
  `userLName` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `userType` enum('ADMIN','MONITOR','GUARD','SERVICE','OTHER','COMPTA') NOT NULL,
  `userphoto` varchar(50) NOT NULL DEFAULT 'default.jpg',
  `contractDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `userPhone` varchar(15) NOT NULL,
  `displayColor` varchar(7) NOT NULL DEFAULT '#0000FF'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `email`, `password`, `adminLevel`, `lastLoginTime`, `isActive`, `userFName`, `userLName`, `description`, `userType`, `userphoto`, `contractDate`, `userPhone`, `displayColor`) VALUES
(1, 'mohammed.belatar@gmail.com', 'password', 100, '2019-10-11 00:39:06', 1, 'Mohammed', 'BELATAR', 'Mohammed BELATAR', 'ADMIN', 'Mohammed-BELATAR-1.png', '2018-09-18 23:00:00', '+212661661661', '#280300'),
(2, 'it@t-t.ma', 'password', 100, '2018-09-12 13:33:16', 1, 'Hassan', 'CHENNOUFI', 'Mohammed BELATAR', 'MONITOR', 'default.jpg', '2018-09-11 18:46:02', '+212661661661', '#0000FF'),
(6, 'mouna@t-t.ma', 'password', 100, '2018-11-03 19:02:31', 1, 'Mouna', 'BENKHRABA', '', 'ADMIN', 'default.jpg', '2018-11-01 00:00:00', '+212661661661', '#ba00a2');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`clientID`);

--
-- Indexes for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `oauth_access_tokens`
--
ALTER TABLE `oauth_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_access_tokens_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_auth_codes`
--
ALTER TABLE `oauth_auth_codes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_auth_codes_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_clients`
--
ALTER TABLE `oauth_clients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_clients_user_id_index` (`user_id`);

--
-- Indexes for table `oauth_personal_access_clients`
--
ALTER TABLE `oauth_personal_access_clients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `oauth_refresh_tokens`
--
ALTER TABLE `oauth_refresh_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `oauth_refresh_tokens_access_token_id_index` (`access_token_id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexes for table `seances`
--
ALTER TABLE `seances`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userID_FKY` (`monitorID`),
  ADD KEY `clientID` (`clientID`),
  ADD KEY `startDate` (`startDate`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_Fk` (`user_Fk`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `clientID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `oauth_clients`
--
ALTER TABLE `oauth_clients`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `oauth_personal_access_clients`
--
ALTER TABLE `oauth_personal_access_clients`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `seances`
--
ALTER TABLE `seances`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=310;

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
