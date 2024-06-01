-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 01, 2024 at 08:45 AM
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
-- Database: `dbminimarket`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblaporankeluar`
--

CREATE TABLE `tblaporankeluar` (
  `idBarangKeluar` int(11) NOT NULL,
  `jenisBarangKeluar` text NOT NULL,
  `namaBarangKeluar` text NOT NULL,
  `jumlahBarangKeluar` int(11) NOT NULL,
  `tanggalKeluar` date NOT NULL,
  `keteranganBarangKeluar` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tblaporankeluar`
--

INSERT INTO `tblaporankeluar` (`idBarangKeluar`, `jenisBarangKeluar`, `namaBarangKeluar`, `jumlahBarangKeluar`, `tanggalKeluar`, `keteranganBarangKeluar`) VALUES
(1, 'Minuman', 'le mineral', 24, '2023-11-12', ''),
(2, 'Minuman', 'sikat gigi', 9, '2024-10-12', 'retur'),
(3, 'Minuman', 'test', 13, '2024-05-15', 'barang retur');

-- --------------------------------------------------------

--
-- Table structure for table `tblaporanmasuk`
--

CREATE TABLE `tblaporanmasuk` (
  `idBarangMasuk` int(11) NOT NULL,
  `jenisBarangMasuk` text NOT NULL,
  `namaBarangMasuk` text NOT NULL,
  `jumlahBarangMasuk` int(11) NOT NULL,
  `tanggalMasuk` date NOT NULL,
  `statusBarangMasuk` text NOT NULL,
  `keteranganBarangMasuk` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tblaporanmasuk`
--

INSERT INTO `tblaporanmasuk` (`idBarangMasuk`, `jenisBarangMasuk`, `namaBarangMasuk`, `jumlahBarangMasuk`, `tanggalMasuk`, `statusBarangMasuk`, `keteranganBarangMasuk`) VALUES
(1, 'Makanan', 'addsads', 12, '2023-12-12', 'diterima', ''),
(2, 'Minuman', 'le mineral', 12, '2023-12-22', 'ditolak', 'barang rusak'),
(3, 'Makanan', 'chitatos', 12, '2023-12-12', 'diterima', ''),
(4, 'Makanan', 'sela', 34, '2023-09-12', 'diterima', ''),
(5, 'Makanan', 'aal', -1, '2024-01-01', 'diterima', ''),
(6, 'Makanan', 'aal', 23, '2024-02-15', 'diterima', ''),
(7, 'Makanan', 'aaa', 12, '2020-12-12', 'diterima', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbmakanan`
--

CREATE TABLE `tbmakanan` (
  `idMakanan` int(11) NOT NULL,
  `namaMakanan` text NOT NULL,
  `stokMakanan` int(11) NOT NULL,
  `hargaMakanan` int(11) NOT NULL,
  `kadaluarsaMakanan` date NOT NULL,
  `jenisMakanan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbmakanan`
--

INSERT INTO `tbmakanan` (`idMakanan`, `namaMakanan`, `stokMakanan`, `hargaMakanan`, `kadaluarsaMakanan`, `jenisMakanan`) VALUES
(1, 'Sayur Bayam', 20, 5000, '2024-06-30', 'Makanan Segar'),
(2, 'Sosis Gulung', 15, 7500, '2024-06-30', 'Makanan Olahan'),
(3, 'Keripik Kentang', 25, 10000, '2024-06-30', 'Makanan Ringan'),
(4, 'Apel', 30, 8000, '2024-06-30', 'Makanan Segar'),
(5, 'Bakso', 12, 20000, '2024-12-12', 'Makanan Olahan'),
(7, 'Coki coki', 12, 2000, '2026-07-01', 'Makanan Ringan');

-- --------------------------------------------------------

--
-- Table structure for table `tbminuman`
--

CREATE TABLE `tbminuman` (
  `idMinuman` int(11) NOT NULL,
  `namaMinuman` text NOT NULL,
  `stokMinuman` int(11) NOT NULL,
  `hargaMinuman` int(11) NOT NULL,
  `kadaluarsaMinuman` date NOT NULL,
  `jenisMinuman` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbminuman`
--

INSERT INTO `tbminuman` (`idMinuman`, `namaMinuman`, `stokMinuman`, `hargaMinuman`, `kadaluarsaMinuman`, `jenisMinuman`) VALUES
(1, 'Milo', 50, 12000, '2026-02-04', 'Minuman Botol'),
(2, 'aaa', 12, 12, '2029-12-12', 'Minuman Botol');

-- --------------------------------------------------------

--
-- Table structure for table `tbperabotan`
--

CREATE TABLE `tbperabotan` (
  `idPerabotan` int(11) NOT NULL,
  `jenisPerabotan` text NOT NULL,
  `namaPerabotan` text NOT NULL,
  `stokPerabotan` int(11) NOT NULL,
  `hargaPerabotan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbperabotan`
--

INSERT INTO `tbperabotan` (`idPerabotan`, `jenisPerabotan`, `namaPerabotan`, `stokPerabotan`, `hargaPerabotan`) VALUES
(1, 'Peralatan Rumah Tangga', 'aa', 12, 20200);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblaporankeluar`
--
ALTER TABLE `tblaporankeluar`
  ADD PRIMARY KEY (`idBarangKeluar`);

--
-- Indexes for table `tblaporanmasuk`
--
ALTER TABLE `tblaporanmasuk`
  ADD PRIMARY KEY (`idBarangMasuk`);

--
-- Indexes for table `tbmakanan`
--
ALTER TABLE `tbmakanan`
  ADD PRIMARY KEY (`idMakanan`);

--
-- Indexes for table `tbminuman`
--
ALTER TABLE `tbminuman`
  ADD PRIMARY KEY (`idMinuman`);

--
-- Indexes for table `tbperabotan`
--
ALTER TABLE `tbperabotan`
  ADD PRIMARY KEY (`idPerabotan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblaporankeluar`
--
ALTER TABLE `tblaporankeluar`
  MODIFY `idBarangKeluar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tblaporanmasuk`
--
ALTER TABLE `tblaporanmasuk`
  MODIFY `idBarangMasuk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tbmakanan`
--
ALTER TABLE `tbmakanan`
  MODIFY `idMakanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbminuman`
--
ALTER TABLE `tbminuman`
  MODIFY `idMinuman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbperabotan`
--
ALTER TABLE `tbperabotan`
  MODIFY `idPerabotan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
