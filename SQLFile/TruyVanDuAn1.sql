﻿CREATE DATABASE DuAn1_QuanLiBanQuanAo
GO
USE DuAn1_QuanLiBanQuanAo
---ChucVu
CREATE TABLE ChucVu (
IdCV UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
MaChucVu VARCHAR(20) UNIQUE,
ChucVu NVARCHAR(50) DEFAULT NULL
)
GO
-- NhanVien
CREATE TABLE NhanVien(
IdNV UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
MaNV VARCHAR(20) UNIQUE,
TenNV NVARCHAR(30) DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
GioiTinh NVARCHAR(10) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
MatKhau VARCHAR(MAX) DEFAULT NULL,
TrangThai INT DEFAULT 0,
IdCV UNIQUEIDENTIFIER
)
GO
-- KhachHang
CREATE TABLE KhachHang(
IdKH UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
MaKH VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
TenDem NVARCHAR(30) DEFAULT NULL,
Ho NVARCHAR(30) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
ThanhPho NVARCHAR(50) DEFAULT NULL,
QuocGia NVARCHAR(50) DEFAULT NULL
)
GO
--HoaDon
CREATE TABLE HoaDon(
IdHoaDon UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdKH UNIQUEIDENTIFIER,
IdNV UNIQUEIDENTIFIER,
IdPTTT UNIQUEIDENTIFIER,
MaHD VARCHAR(20) UNIQUE,
NgayTao DATE DEFAULT NULL,
NgayThanhToan DATE DEFAULT NULL,
NgayShip DATE DEFAULT NULL,
NgayNhan DATE DEFAULT NULL,
TenNguoiNhan NVARCHAR(50) DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
TrangThai INT DEFAULT 0,
Sdt VARCHAR(30) DEFAULT NULL,
)
GO
-- SanPham
CREATE TABLE SanPham(
IdSP UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
MaSP VARCHAR(20) UNIQUE,
Ten NVARCHAR(30)
)
GO
-- NSX
CREATE TABLE NSX(
IdNSX UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30)
)
GO
-- MauSac
CREATE TABLE MauSac(
IdMauSac UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
TenMauSac NVARCHAR(30)
)
GO
-- ChatLieu
CREATE TABLE ChatLieu(
IdChatLieu UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
TenChatLieu NVARCHAR(30)
)
GO
-- ThuongHieu
CREATE TABLE ThuongHieu(
IdThuongHieu UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
TenChatLieu NVARCHAR(30)
)
GO
-- Size
CREATE TABLE Size(
IdSize UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
SoSize INT
)
GO
-- LoaiSanPham
CREATE TABLE LoaiSanPham(
IdLoaiSP UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
TenLoaiSP NVARCHAR(30)
)

GO
-- ChiTietSP
CREATE TABLE ChiTietSP(
IdCTSP UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdSP UNIQUEIDENTIFIER,
IdNSX UNIQUEIDENTIFIER,
IdMauSac UNIQUEIDENTIFIER,
IdLoaiSP UNIQUEIDENTIFIER,
IdChatLieu UNIQUEIDENTIFIER,
IdThuongHieu UNIQUEIDENTIFIER,
IdSize UNIQUEIDENTIFIER,
SoLuong INT,
GiaNhap DECIMAL(20,0) DEFAULT 0,
GiaBan DECIMAL(20,0) DEFAULT 0,
MoTa NVARCHAR(50) DEFAULT NULL,
)
GO

GO
-- HoaDonChiTiet
CREATE TABLE HoaDonChiTiet(
IdHoaDon UNIQUEIDENTIFIER,
IdCTSP UNIQUEIDENTIFIER,
SoLuong INT,
DonGia DECIMAL(20,0) DEFAULT 0,
CONSTRAINT PK_HoaDonCT PRIMARY KEY (IdHoaDon,IdCTSP),
CONSTRAINT FK1 FOREIGN KEY(IdHoaDon) REFERENCES HoaDon(IdHoaDon),
CONSTRAINT FK2 FOREIGN KEY(IdCTSP) REFERENCES ChiTietSP(IdCTSP),
)
GO
-- PTTT
CREATE TABLE PTTT(
IdPTTT UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdLoaiHinhTT UNIQUEIDENTIFIER NOT NULL,
TenPTTT NVARCHAR(50) DEFAULT NULL,
SoTien MONEY NULL
)

-- LoaiHinhTT
CREATE TABLE LoaiHinhTT(
IdLHTT UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
TenLHTT NVARCHAR(50) DEFAULT NULL
)

GO
--TẠO QUAN HỆ GIỮA CÁC BẢNG
--NhanVien - ChucVu
ALTER TABLE NhanVien ADD FOREIGN KEY (IdCV) REFERENCES dbo.ChucVu(IdCV)
--HoaDon - NhanVien
ALTER TABLE dbo.HoaDon ADD FOREIGN KEY (IdNV) REFERENCES dbo.NhanVien(IdNV)
--HoaDon - KhachHang
ALTER TABLE dbo.HoaDon ADD FOREIGN KEY (IdKH) REFERENCES dbo.KhachHang(IdKH)
--HoaDon - PTTT
ALTER TABLE dbo.HoaDon ADD FOREIGN KEY (IdPTTT) REFERENCES dbo.PTTT(IdPTTT)
--PTTT - LoaiHinhThanhToan
ALTER TABLE dbo.PTTT ADD FOREIGN KEY (IdPTTT) REFERENCES dbo.LoaiHinhTT(IdLHTT)
--PTTT - LoaiHinhThanhToan
--CTSP - Thuong Hieu
ALTER TABLE dbo.ChiTietSP ADD FOREIGN KEY (IdThuongHieu) REFERENCES dbo.ThuongHieu(IdThuongHieu)
--CTSP - Size
ALTER TABLE dbo.ChiTietSP ADD FOREIGN KEY (IdSize) REFERENCES dbo.Size(IdSize)
--CTSP - LoaiSP
ALTER TABLE dbo.ChiTietSP ADD FOREIGN KEY (IdLoaiSP) REFERENCES dbo.LoaiSanPham(IdLoaiSP)
--CTSP - ChatLieu
ALTER TABLE dbo.ChiTietSP ADD FOREIGN KEY (IdChatLieu) REFERENCES dbo.ChatLieu(IdChatLieu)
--CTSP - NSX
ALTER TABLE dbo.ChiTietSP ADD FOREIGN KEY (IdNSX) REFERENCES dbo.NSX(IdNSX)
--CTSP - MauSac
ALTER TABLE dbo.ChiTietSP ADD FOREIGN KEY (IdMauSac) REFERENCES dbo.MauSac(IdMauSac)
--CTSP - SanPham
ALTER TABLE dbo.ChiTietSP ADD FOREIGN KEY (IdSP) REFERENCES dbo.SanPham(IdSP)