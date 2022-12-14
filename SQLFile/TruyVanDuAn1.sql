<<<<<<< HEAD
﻿ 
CREATE DATABASE DuAn1_QuanLiBanQuanAo
GO
USE DuAn1_QuanLiBanQuanAo
GO 
---ChucVu
CREATE TABLE ChucVu (
IdCV UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
MaChucVu VARCHAR(20) UNIQUE,
ChucVu NVARCHAR(50) DEFAULT NULL,
TrangThai INT DEFAULT 1,
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
QuocGia NVARCHAR(50) DEFAULT NULL,
TrangThai INT DEFAULT 1,
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
NgayHen DATE DEFAULT NULL,
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
TrangThai INT DEFAULT 1,
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
-- LoaiHinhTT
CREATE TABLE LoaiHinhTT(
IdLHTT UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
TenLHTT NVARCHAR(50) DEFAULT NULL,
TrangThai INT DEFAULT 1,
)
-- PTTT
CREATE TABLE PTTT(
IdHoaDon UNIQUEIDENTIFIER,
IdLHTT UNIQUEIDENTIFIER,
SoTien MONEY NULL,
CONSTRAINT PK_PTTT PRIMARY KEY (IdHoaDon,IdLHTT),
CONSTRAINT FK_PTTT_LHTT FOREIGN KEY(IdLHTT) REFERENCES dbo.LoaiHinhTT(IdLHTT),
CONSTRAINT FK_PTTT_HoaDon FOREIGN KEY(IdHoaDon) REFERENCES dbo.HoaDon(IdHoaDon),
)
GO
--TẠO QUAN HỆ GIỮA CÁC BẢNG
--NhanVien - ChucVu
ALTER TABLE NhanVien ADD FOREIGN KEY (IdCV) REFERENCES dbo.ChucVu(IdCV)
--HoaDon - NhanVien
ALTER TABLE dbo.HoaDon ADD FOREIGN KEY (IdNV) REFERENCES dbo.NhanVien(IdNV)
--HoaDon - KhachHang
ALTER TABLE dbo.HoaDon ADD FOREIGN KEY (IdKH) REFERENCES dbo.KhachHang(IdKH)
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



------Update 1.1
-----Insert Các bảng
ALTER TABLE dbo.Size
ALTER COLUMN SoSize NVARCHAR(50);
-----Insert Các bảng
-----Insert Bảng Chất Liệu
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL01' ,N'Vải Cotton' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL02' ,N'Vải Kaki' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL03' ,N'Vải Jeans' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL04' ,N'Vải Kate' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL05' ,N'Vải Nỉ' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL06' ,N'Vải Len' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL07' ,N'Vải Thô' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL08' ,N'Vải Voan' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL09' ,N'Vải Lanh' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL10' ,N'Vải Đũi' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL11' ,N'Vải Ren' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL13' ,N'Vải FE' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL14' ,N'Vải Nỉ Lông' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL15' ,N'Vải Spandex' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL16' ,N'Vải Hoa Văn' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL17' ,N'Vải Thun Lạnh' )
-----Insert Bảng NSX
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N001', N'Công Ty Cổ Phần May Đồng Na')

-----Insert Bảng Size
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'XXS')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'XS')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'S')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'M')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'L')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'XL')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'XXL')

-----Insert Bảng Thương Hiệu
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT01',N'Jody')
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT02',N'Việt Tiến')
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT03',N'May10')
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT04',N'Owen')
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT05',N'CoolMate')
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT06',N'Top4Man')
-----Insert Bảng Chức Vụ
INSERT INTO dbo.ChucVu (IdCV,MaChucVu,ChucVu,TrangThai) VALUES (DEFAULT,N'CV01',N'Admin',1)
INSERT INTO dbo.ChucVu (IdCV,MaChucVu,ChucVu,TrangThai) VALUES (DEFAULT,N'CV02',N'NhanVien',1)
SELECT IdCV FROM dbo.ChucVu WHERE ChucVu = N'NhanVien'

------Update 1.2
-----Sửa tên bảng thương hiệu
ALTER TABLE dbo.ThuongHieu
DROP COLUMN TenChatLieu
ALTER TABLE dbo.ThuongHieu
ADD  TenThuongHieu NVARCHAR(50);
--- Insert TenthuongHieu
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'Jody' WHERE Ma = N'TT01'
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'Việt Tiến' WHERE Ma = N'TT02'
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'May10' WHERE Ma = N'TT03'
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'Owen' WHERE Ma = N'TT04'
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'CoolMate' WHERE Ma = N'TT05'
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'Top4Man' WHERE Ma = N'TT06'
---Insert bảng NSX
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N002', N'Công Ty Cổ Phần Dệt 10/10')
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N003', N'Công Ty Cổ Phần May Sông Hồng')
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N004', N'Công ty cổ phần Dệt-May 29/3')
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N005', N'Tập đoàn dệt may Việt Nam')
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N006', N'Tổng công ty dệt may Hà Nội')
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N007', N'Công ty cổ phần may Việt Tiến')
SELECT * FROM dbo.NhanVien
UPDATE dbo.NhanVien SET NgaySinh = '05-12-2003'

--- Update 1.3
---Insert bảng mầu sắc
INSERT iNTO dbo.MauSac VALUES (DEFAULT,'M001',N'Xanh')
INSERT iNTO dbo.MauSac VALUES (DEFAULT,'M002',N'Vàng')
INSERT iNTO dbo.MauSac VALUES (DEFAULT,'M003',N'Nâu')
---Insert bảng Loại SP
INSERT iNTO dbo.LoaiSanPham VALUES (DEFAULT,'Quần Dài ')
INSERT iNTO dbo.LoaiSanPham VALUES (DEFAULT,'Áo Thu Đông')
INSERT iNTO dbo.LoaiSanPham VALUES (DEFAULT,'Áo Ngắn Tay')

-----Ô Hà Muốn insert được SP phải thay đổi Các ID 
INSERT INTO dbo.ChiTietSP(IdCTSP,IdSP,IdNSX,IdMauSac,IdLoaiSP,IdChatLieu,IdThuongHieu,IdSize,SoLuong,GiaNhap,GiaBan,MoTa,TrangThai)
VALUES
(DEFAULT, '318A8FCB-C228-405B-B5B5-2B2C59042345', '0D6CA241-D929-489A-A48E-0951D155F06B',    '794CF330-45E0-4FF0-B321-149EFDCFD0B7',    'FC146770-C845-4CCF-9B58-037A4A33B499',   '3A1635E1-901B-42B1-9479-004301E5120E',    'E056C589-54CA-4BA1-B05F-199B11F71A79',    'FC63351D-350E-43F0-AA8B-3C28ACD50D25',    100,    100000, 150000, N'Mô Tả 1', 1)


---Update 1.4
ALTER TABLE dbo.HoaDonChiTiet
DROP CONSTRAINT PK_HoaDonCT
ALTER TABLE dbo.HoaDonChiTiet
ADD IdHoaDonChiTiet UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID()
ALTER TABLE dbo.HoaDonChiTiet
DROP CONSTRAINT DF__HoaDonChi__DonGi__6C190EBB --- xoá constraint đơn giá
ALTER TABLE dbo.HoaDonChiTiet
DROP COLUMN DonGia
---thêm Hoá Đơn CT

SELECT * FROM dbo.HoaDon
SELECT * FROM dbo.ChiTietSP
SELECT * FROM dbo.HoaDonChiTiet

--- lan 1
INSERT INTO dbo.HoaDonChiTiet (IdHoaDon,IdCTSP,SoLuong,IdHoaDonChiTiet)VALUES('5B057D3F-0D56-4F23-85D8-29F44948EC04','67E20EC6-60A3-471F-A98F-0AB7F9531A99',10, DEFAULT)
INSERT INTO dbo.HoaDonChiTiet (IdHoaDon,IdCTSP,SoLuong,IdHoaDonChiTiet)VALUES('5B057D3F-0D56-4F23-85D8-29F44948EC04','67E20EC6-60A3-471F-A98F-0AB7F9531A99',9, DEFAULT)
INSERT INTO dbo.HoaDonChiTiet (IdHoaDon,IdCTSP,SoLuong,IdHoaDonChiTiet)VALUES('5B057D3F-0D56-4F23-85D8-29F44948EC04','67E20EC6-60A3-471F-A98F-0AB7F9531A99',23, DEFAULT)
---Lan 2
INSERT INTO dbo.HoaDonChiTiet (IdHoaDon,IdCTSP,SoLuong,IdHoaDonChiTiet)VALUES('B5266C11-439A-4FFE-A700-5649E199B744','67E20EC6-60A3-471F-A98F-0AB7F9531A99',14, DEFAULT)
INSERT INTO dbo.HoaDonChiTiet (IdHoaDon,IdCTSP,SoLuong,IdHoaDonChiTiet)VALUES('B5266C11-439A-4FFE-A700-5649E199B744','67E20EC6-60A3-471F-A98F-0AB7F9531A99',8, DEFAULT)


SELECT * FROM dbo.NhanVien
select RIGHT(MAX(MaHD),5) from HoaDon


----UPDATE 1.5
---Insert Hoa Don
create proc procThemHdTaiQuay
		@NgayTao date,
		@IdNV UNIQUEIDENTIFIER,
		
		@TrangThai int
	as
	begin
		Declare @MaHD char(7)
		if not exists (select * from HoaDon) 
			Set @MaHD=1
		else
			Set @MaHD=(select RIGHT(MAX(MaHD),5) from HoaDon)+1
		Set @MaHD='HD'+REPLICATE('0',5-LEN(@MaHD))+@MaHD
		insert into HoaDon(MaHD, NgayTao, IdNV, TrangThai) values(@MaHD,@NgayTao, @IdNV, @TrangThai)
		
	END
    
----- Insert Chat Lieu
create proc procThemChatLieu
		@TenCL nvarchar(50)
	as
	begin
		Declare @MaCL char(7)
		if not exists (select * from ChatLieu) 
			Set @MaCL=1
		else
			Set @MaCL=(select RIGHT(MAX(MA),5) from ChatLieu)+1
		Set @MaCL='CL'+REPLICATE('0',5-LEN(@MaCL))+@MaCL
		insert into ChatLieu(Ma, TenChatLieu) values(@MaCL,@TenCL)
		select ma 
		from ChatLieu
		where ma = @MaCL
		return
	END
    ----Insert Thuong Hieu
	create proc procThemThuongHieu
		@Tenth nvarchar(50)
	as
	begin
		Declare @MaTH char(7)
		if not exists (select * from ThuongHieu) 
			Set @MaTH=1
		else
			Set @MaTH=(select RIGHT(MAX(MA),5) from ThuongHieu)+1
		Set @MaTH='TH'+REPLICATE('0',5-LEN(@MaTH))+@MaTH
		insert into ThuongHieu(Ma, TenThuongHieu) values(@MaTH,@Tenth)
		select ma 
		from ThuongHieu
		where ma = @MaTH
		return
	end
	-----Insert MauSac
	create proc procThemMauSac
		@TenMauSac nvarchar(50)
	as
	begin
		Declare @MaMauSac char(7)
		if not exists (select * from MauSac) 
			Set @MaMauSac=1
		else
			Set @MaMauSac=(select RIGHT(MAX(MA),5) from MauSac)+1
		Set @MaMauSac='MS'+REPLICATE('0',5-LEN(@MaMauSac))+@MaMauSac
		insert into MauSac(Ma, TenMauSac) values(@MaMauSac,@TenMauSac)
		select ma 
		from MauSac
		where ma = @MaMauSac
		return
	END
    

	-----Insert NSX
	create proc procThemNSX
		@TenNSX nvarchar(50)
	as
	begin
		Declare @MaNSX char(7)
		if not exists (select * from NSX) 
			Set @MaNSX=1
		else
			Set @MaNSX=(select RIGHT(MAX(MA),5) from NSX)+1
		Set @MaNSX='NSX'+REPLICATE('0',5-LEN(@MaNSX))+@MaNSX
		insert into NSX(Ma, Ten) values(@MaNSX,@TenNSX)
		select ma 
		from NSX
		where ma = @MaNSX
		return
	END
    
    --- Add MaLoaiSP
	ALTER TABLE dbo.LoaiSanPham
	ADD Ma NVARCHAR(10)
    
	----Insert Loai SP
	create proc procThemLoaiSanPham
		@TenLSP nvarchar(50)
	as
	begin
		Declare @MaLSP char(7)
		if not exists (select * from LoaiSanPham) 
			Set @MaLSP=1
		else
			Set @MaLSP=(select RIGHT(MAX(MA),5) from LoaiSanPham)+1
		Set @MaLSP='LSP'+REPLICATE('0',5-LEN(@MaLSP))+@MaLSP
		insert into LoaiSanPham(Ma, TenLoaiSP) values(@MaLSP,@TenLSP)
		select ma 
		from LoaiSanPham
		where ma = @MaLSP
		return
	end


	SELECT * FROM dbo.NhanVien

	DELETE FROM dbo.KhachHang
	DELETE FROM dbo.HoaDonChiTiet
	EXEC dbo.procThemNV @TenNV = N'fbvdf',             -- nvarchar(50)
	                    @DiaChi = N'bdfbdf',            -- nvarchar(50)
	                    @SDT = '56786474',                -- varchar(20)
	                    @GioiTinh = N'Nam',          -- nvarchar(50)
	                    @NgaySinh = '2022-11-27', -- date
	                    @MatKhau = N'vjrv',           -- nvarchar(30)
	                    @TrangThai = 1,           -- int
	                    @IdCV = 'D1C3E9A0-A6A8-4AA7-A6EA-D5A08874AE75'              -- uniqueidentifier
	SELECT * FROM dbo.ChucVu

	create proc procKH
		@TenKH nvarchar(50),
		@SDT varchar(30)
	as
	begin
		Declare @MaKH char(7)
		if not exists (select * from KhachHang) 
			Set @MaKH=1
		else
			Set @MaKH=(select RIGHT(MAX(MAKH),5) from KhachHang)+1
		Set @MaKH='KH'+REPLICATE('0',5-LEN(@MaKH))+@MaKH
		insert into KhachHang(MaKH, Ten, Sdt, TrangThai) values(@MaKH,@TenKH, @SDT, 1)
		select MAKH
		from KhachHang
		where MaKH = @MaKH
		return
	end


	----- Thêm thuộc tính bảng Hóa Đơn
	ALTER TABLE dbo.HoaDonChiTiet
	ADD TienThua DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDonChiTiet
	ADD TienGiamGia DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDonChiTiet
	ADD TrangThai INT DEFAULT 1
	------Thêm Tiền Cọc
	ALTER TABLE dbo.HoaDon
	ADD TienCoc DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDon
	ADD TienShip DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDon
	ADD TenNguoiShip NVARCHAR(50)
	ALTER TABLE dbo.HoaDon
	ADD SDTNguoiShip DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDon
	ADD SDTNguoiNhan DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDon
	ADD TongTien DECIMAL(20,0) DEFAULT 0
	----- Chức Năng Giảm Giá


	------ Proc themSIze
	USE [DuAn1_QuanLiBanQuanAo]
GO
/****** Object:  StoredProcedure [dbo].[procThemSIZE]    Script Date: 01/12/2022 11:40:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc procThemSIZE
		@TenSoSize nvarchar(50)
	as
	begin
		Declare @MaSize char(7)
		if not exists (select * from Size) 
			Set @MaSize=1
		else
			Set @MaSize=(select RIGHT(MAX(MA),5) from Size)+1
		Set @MaSize='S'+REPLICATE('0',5-LEN(@MaSize))+@MaSize
		insert into Size(Ma, SoSize) values(@MaSize,@TenSoSize)
		select ma 
		from Size
		where ma = @MaSize
		return
	END
    
	---- Proc newProduct
	CREATE PROC [dbo].[InsertNewProduct] (
@Ten NVARCHAR(MAX)
)
AS
BEGIN
	DECLARE @getIndexProduct int;
	DECLARE @defaultCode NVARCHAR(10) = N'SP';
	DECLARE @newProductCode NVARCHAR(25);
	
	SELECT @getIndexProduct = ISNULL(Count(IdSP), 0) + 1 FROM SanPham
	SELECT @newProductCode = @defaultCode + N'0000' + CAST(@getIndexProduct AS NVARCHAR(5))

	INSERT INTO SanPham (IdSP, MaSP, Ten) VALUES (newId(), @newProductCode, @Ten)
	SELECT MaSP 
	From SanPham
	Where MaSP = @newProductCode
	RETURN
END

---- Proc KH
CREATE proc [dbo].[procKH]
		@TenKH nvarchar(50),
		@SDT varchar(30)
	as
	begin
		Declare @MaKH char(7)
		if not exists (select * from KhachHang) 
			Set @MaKH=1
		else
			Set @MaKH=(select RIGHT(MAX(MAKH),5) from KhachHang)+1
		Set @MaKH='KH'+REPLICATE('0',5-LEN(@MaKH))+@MaKH
		insert into KhachHang(MaKH, Ten, Sdt, TrangThai) values(@MaKH,@TenKH, @SDT, 1)
		select MAKH
		from KhachHang
		where MaKH = @MaKH
		return
	end

---- Update DB 1.6
DROP TABLE dbo.PTTT
DROP TABLE dbo.LoaiHinhTT
CREATE TABLE HinhThucThanhToan(
IdHTTT UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdHoaDon UNIQUEIDENTIFIER ,
MaHTTT VARCHAR(20) UNIQUE,
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
LoaiHinhThanhToan VARCHAR(20),
TongTienThanhToan DECIMAL(20,0),
TrangThai INT DEFAULT 1,
CONSTRAINT FK_HTTT_HoaDon FOREIGN KEY(IdHoaDon) REFERENCES HoaDon(IdHoaDon),
)

ALTER TABLE dbo.HoaDon 
DROP COLUMN IdPTTT


ALTER TABLE dbo.HoaDon 
ADD IdHTTT UNIQUEIDENTIFIER
----thêm cột tổng tiền trong Hóa Đơn
ALTER TABLE dbo.HoaDon
ADD TongTien DECIMAL(20,0)



CREATE TABLE GiamGia(
IdGiamGia UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
MaGiamGia VARCHAR(20) UNIQUE,
TenGiamGia NVARCHAR(20),
NgayBatDau DATE DEFAULT NULL,
NgayKetThuc DATE DEFAULT NULL,
MucGiamGiaPhanTram DATE DEFAULT NULL,
DieuKienGiamGia NVARCHAR(50) NULL,
TrangThai INT DEFAULT 1,
LoaiGiamGia NVARCHAR
)


CREATE TABLE SPGiamGia(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdGiamGia UNIQUEIDENTIFIER,
IdHoaDon UNIQUEIDENTIFIER,
DonGia DECIMAL(20,0),
SoTienConLai DECIMAL(20,0),
TrangThai INT DEFAULT 1,
CONSTRAINT FK_GiamGia FOREIGN KEY(IdGiamGia) REFERENCES dbo.GiamGia(IdGiamGia),
CONSTRAINT FK_IdHoaDon FOREIGN KEY(IdHoaDon) REFERENCES dbo.HoaDon(IdHoaDon),
)

=======
﻿ 
CREATE DATABASE DuAn1_QuanLiBanQuanAo
GO
USE DuAn1_QuanLiBanQuanAo
GO 
---ChucVu
CREATE TABLE ChucVu (
IdCV UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
MaChucVu VARCHAR(20) UNIQUE,
ChucVu NVARCHAR(50) DEFAULT NULL,
TrangThai INT DEFAULT 1,
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
QuocGia NVARCHAR(50) DEFAULT NULL,
TrangThai INT DEFAULT 1,
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
NgayHen DATE DEFAULT NULL,
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
TrangThai INT DEFAULT 1,
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
-- LoaiHinhTT
CREATE TABLE LoaiHinhTT(
IdLHTT UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
TenLHTT NVARCHAR(50) DEFAULT NULL,
TrangThai INT DEFAULT 1,
)
-- PTTT
CREATE TABLE PTTT(
IdHoaDon UNIQUEIDENTIFIER,
IdLHTT UNIQUEIDENTIFIER,
SoTien MONEY NULL,
CONSTRAINT PK_PTTT PRIMARY KEY (IdHoaDon,IdLHTT),
CONSTRAINT FK_PTTT_LHTT FOREIGN KEY(IdLHTT) REFERENCES dbo.LoaiHinhTT(IdLHTT),
CONSTRAINT FK_PTTT_HoaDon FOREIGN KEY(IdHoaDon) REFERENCES dbo.HoaDon(IdHoaDon),
)
GO
--TẠO QUAN HỆ GIỮA CÁC BẢNG
--NhanVien - ChucVu
ALTER TABLE NhanVien ADD FOREIGN KEY (IdCV) REFERENCES dbo.ChucVu(IdCV)
--HoaDon - NhanVien
ALTER TABLE dbo.HoaDon ADD FOREIGN KEY (IdNV) REFERENCES dbo.NhanVien(IdNV)
--HoaDon - KhachHang
ALTER TABLE dbo.HoaDon ADD FOREIGN KEY (IdKH) REFERENCES dbo.KhachHang(IdKH)
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



------Update 1.1
-----Insert Các bảng
ALTER TABLE dbo.Size
ALTER COLUMN SoSize NVARCHAR(50);
-----Insert Các bảng
-----Insert Bảng Chất Liệu
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL01' ,N'Vải Cotton' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL02' ,N'Vải Kaki' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL03' ,N'Vải Jeans' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL04' ,N'Vải Kate' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL05' ,N'Vải Nỉ' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL06' ,N'Vải Len' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL07' ,N'Vải Thô' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL08' ,N'Vải Voan' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL09' ,N'Vải Lanh' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL10' ,N'Vải Đũi' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL11' ,N'Vải Ren' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL13' ,N'Vải FE' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL14' ,N'Vải Nỉ Lông' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL15' ,N'Vải Spandex' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL16' ,N'Vải Hoa Văn' )
INSERT INTO dbo.ChatLieu (IdChatLieu,Ma,TenChatLieu) VALUES (   DEFAULT, N'CL17' ,N'Vải Thun Lạnh' )
-----Insert Bảng NSX
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N001', N'Công Ty Cổ Phần May Đồng Na')

-----Insert Bảng Size
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'XXS')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'XS')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'S')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'M')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'L')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'XL')
INSERT INTO dbo.Size (IdSize,SoSize) VALUES (DEFAULT, N'XXL')

-----Insert Bảng Thương Hiệu
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT01',N'Jody')
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT02',N'Việt Tiến')
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT03',N'May10')
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT04',N'Owen')
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT05',N'CoolMate')
INSERT INTO dbo.ThuongHieu (IdThuongHieu,Ma,TenChatLieu) VALUES (DEFAULT,N'TT06',N'Top4Man')
-----Insert Bảng Chức Vụ
INSERT INTO dbo.ChucVu (IdCV,MaChucVu,ChucVu,TrangThai) VALUES (DEFAULT,N'CV01',N'Admin',1)
INSERT INTO dbo.ChucVu (IdCV,MaChucVu,ChucVu,TrangThai) VALUES (DEFAULT,N'CV02',N'NhanVien',1)
SELECT IdCV FROM dbo.ChucVu WHERE ChucVu = N'NhanVien'

------Update 1.2
-----Sửa tên bảng thương hiệu
ALTER TABLE dbo.ThuongHieu
DROP COLUMN TenChatLieu
ALTER TABLE dbo.ThuongHieu
ADD  TenThuongHieu NVARCHAR(50);
--- Insert TenthuongHieu
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'Jody' WHERE Ma = N'TT01'
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'Việt Tiến' WHERE Ma = N'TT02'
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'May10' WHERE Ma = N'TT03'
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'Owen' WHERE Ma = N'TT04'
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'CoolMate' WHERE Ma = N'TT05'
UPDATE dbo.ThuongHieu SET TenThuongHieu = N'Top4Man' WHERE Ma = N'TT06'
---Insert bảng NSX
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N002', N'Công Ty Cổ Phần Dệt 10/10')
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N003', N'Công Ty Cổ Phần May Sông Hồng')
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N004', N'Công ty cổ phần Dệt-May 29/3')
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N005', N'Tập đoàn dệt may Việt Nam')
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N006', N'Tổng công ty dệt may Hà Nội')
INSERT INTO dbo.NSX (IdNSX,Ma,Ten) VALUES ( DEFAULT, N'N007', N'Công ty cổ phần may Việt Tiến')
SELECT * FROM dbo.NhanVien
UPDATE dbo.NhanVien SET NgaySinh = '05-12-2003'

--- Update 1.3
---Insert bảng mầu sắc
INSERT iNTO dbo.MauSac VALUES (DEFAULT,'M001',N'Xanh')
INSERT iNTO dbo.MauSac VALUES (DEFAULT,'M002',N'Vàng')
INSERT iNTO dbo.MauSac VALUES (DEFAULT,'M003',N'Nâu')
---Insert bảng Loại SP
INSERT iNTO dbo.LoaiSanPham VALUES (DEFAULT,'Quần Dài ')
INSERT iNTO dbo.LoaiSanPham VALUES (DEFAULT,'Áo Thu Đông')
INSERT iNTO dbo.LoaiSanPham VALUES (DEFAULT,'Áo Ngắn Tay')

-----Ô Hà Muốn insert được SP phải thay đổi Các ID 
INSERT INTO dbo.ChiTietSP(IdCTSP,IdSP,IdNSX,IdMauSac,IdLoaiSP,IdChatLieu,IdThuongHieu,IdSize,SoLuong,GiaNhap,GiaBan,MoTa,TrangThai)
VALUES
(DEFAULT, '318A8FCB-C228-405B-B5B5-2B2C59042345', '0D6CA241-D929-489A-A48E-0951D155F06B',    '794CF330-45E0-4FF0-B321-149EFDCFD0B7',    'FC146770-C845-4CCF-9B58-037A4A33B499',   '3A1635E1-901B-42B1-9479-004301E5120E',    'E056C589-54CA-4BA1-B05F-199B11F71A79',    'FC63351D-350E-43F0-AA8B-3C28ACD50D25',    100,    100000, 150000, N'Mô Tả 1', 1)


---Update 1.4
ALTER TABLE dbo.HoaDonChiTiet
DROP CONSTRAINT PK_HoaDonCT
ALTER TABLE dbo.HoaDonChiTiet
ADD IdHoaDonChiTiet UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID()
ALTER TABLE dbo.HoaDonChiTiet
DROP CONSTRAINT DF__HoaDonChi__DonGi__6C190EBB --- xoá constraint đơn giá
ALTER TABLE dbo.HoaDonChiTiet
DROP COLUMN DonGia
---thêm Hoá Đơn CT

SELECT * FROM dbo.HoaDon
SELECT * FROM dbo.ChiTietSP
SELECT * FROM dbo.HoaDonChiTiet

--- lan 1
INSERT INTO dbo.HoaDonChiTiet (IdHoaDon,IdCTSP,SoLuong,IdHoaDonChiTiet)VALUES('5B057D3F-0D56-4F23-85D8-29F44948EC04','67E20EC6-60A3-471F-A98F-0AB7F9531A99',10, DEFAULT)
INSERT INTO dbo.HoaDonChiTiet (IdHoaDon,IdCTSP,SoLuong,IdHoaDonChiTiet)VALUES('5B057D3F-0D56-4F23-85D8-29F44948EC04','67E20EC6-60A3-471F-A98F-0AB7F9531A99',9, DEFAULT)
INSERT INTO dbo.HoaDonChiTiet (IdHoaDon,IdCTSP,SoLuong,IdHoaDonChiTiet)VALUES('5B057D3F-0D56-4F23-85D8-29F44948EC04','67E20EC6-60A3-471F-A98F-0AB7F9531A99',23, DEFAULT)
---Lan 2
INSERT INTO dbo.HoaDonChiTiet (IdHoaDon,IdCTSP,SoLuong,IdHoaDonChiTiet)VALUES('B5266C11-439A-4FFE-A700-5649E199B744','67E20EC6-60A3-471F-A98F-0AB7F9531A99',14, DEFAULT)
INSERT INTO dbo.HoaDonChiTiet (IdHoaDon,IdCTSP,SoLuong,IdHoaDonChiTiet)VALUES('B5266C11-439A-4FFE-A700-5649E199B744','67E20EC6-60A3-471F-A98F-0AB7F9531A99',8, DEFAULT)


SELECT * FROM dbo.NhanVien
select RIGHT(MAX(MaHD),5) from HoaDon


----UPDATE 1.5
---Insert Hoa Don
create proc procThemHdTaiQuay
		@NgayTao date,
		@IdNV UNIQUEIDENTIFIER,
		
		@TrangThai int
	as
	begin
		Declare @MaHD char(7)
		if not exists (select * from HoaDon) 
			Set @MaHD=1
		else
			Set @MaHD=(select RIGHT(MAX(MaHD),5) from HoaDon)+1
		Set @MaHD='HD'+REPLICATE('0',5-LEN(@MaHD))+@MaHD
		insert into HoaDon(MaHD, NgayTao, IdNV, TrangThai) values(@MaHD,@NgayTao, @IdNV, @TrangThai)
		
	END
    
----- Insert Chat Lieu
create proc procThemChatLieu
		@TenCL nvarchar(50)
	as
	begin
		Declare @MaCL char(7)
		if not exists (select * from ChatLieu) 
			Set @MaCL=1
		else
			Set @MaCL=(select RIGHT(MAX(MA),5) from ChatLieu)+1
		Set @MaCL='CL'+REPLICATE('0',5-LEN(@MaCL))+@MaCL
		insert into ChatLieu(Ma, TenChatLieu) values(@MaCL,@TenCL)
		select ma 
		from ChatLieu
		where ma = @MaCL
		return
	END
    ----Insert Thuong Hieu
	create proc procThemThuongHieu
		@Tenth nvarchar(50)
	as
	begin
		Declare @MaTH char(7)
		if not exists (select * from ThuongHieu) 
			Set @MaTH=1
		else
			Set @MaTH=(select RIGHT(MAX(MA),5) from ThuongHieu)+1
		Set @MaTH='TH'+REPLICATE('0',5-LEN(@MaTH))+@MaTH
		insert into ThuongHieu(Ma, TenThuongHieu) values(@MaTH,@Tenth)
		select ma 
		from ThuongHieu
		where ma = @MaTH
		return
	end
	-----Insert MauSac
	create proc procThemMauSac
		@TenMauSac nvarchar(50)
	as
	begin
		Declare @MaMauSac char(7)
		if not exists (select * from MauSac) 
			Set @MaMauSac=1
		else
			Set @MaMauSac=(select RIGHT(MAX(MA),5) from MauSac)+1
		Set @MaMauSac='MS'+REPLICATE('0',5-LEN(@MaMauSac))+@MaMauSac
		insert into MauSac(Ma, TenMauSac) values(@MaMauSac,@TenMauSac)
		select ma 
		from MauSac
		where ma = @MaMauSac
		return
	END
    

	-----Insert NSX
	create proc procThemNSX
		@TenNSX nvarchar(50)
	as
	begin
		Declare @MaNSX char(7)
		if not exists (select * from NSX) 
			Set @MaNSX=1
		else
			Set @MaNSX=(select RIGHT(MAX(MA),5) from NSX)+1
		Set @MaNSX='NSX'+REPLICATE('0',5-LEN(@MaNSX))+@MaNSX
		insert into NSX(Ma, Ten) values(@MaNSX,@TenNSX)
		select ma 
		from NSX
		where ma = @MaNSX
		return
	END
    
    --- Add MaLoaiSP
	ALTER TABLE dbo.LoaiSanPham
	ADD Ma NVARCHAR(10)
    
	----Insert Loai SP
	create proc procThemLoaiSanPham
		@TenLSP nvarchar(50)
	as
	begin
		Declare @MaLSP char(7)
		if not exists (select * from LoaiSanPham) 
			Set @MaLSP=1
		else
			Set @MaLSP=(select RIGHT(MAX(MA),5) from LoaiSanPham)+1
		Set @MaLSP='LSP'+REPLICATE('0',5-LEN(@MaLSP))+@MaLSP
		insert into LoaiSanPham(Ma, TenLoaiSP) values(@MaLSP,@TenLSP)
		select ma 
		from LoaiSanPham
		where ma = @MaLSP
		return
	end


	SELECT * FROM dbo.NhanVien

	DELETE FROM dbo.KhachHang
	DELETE FROM dbo.HoaDonChiTiet
	EXEC dbo.procThemNV @TenNV = N'fbvdf',             -- nvarchar(50)
	                    @DiaChi = N'bdfbdf',            -- nvarchar(50)
	                    @SDT = '56786474',                -- varchar(20)
	                    @GioiTinh = N'Nam',          -- nvarchar(50)
	                    @NgaySinh = '2022-11-27', -- date
	                    @MatKhau = N'vjrv',           -- nvarchar(30)
	                    @TrangThai = 1,           -- int
	                    @IdCV = 'D1C3E9A0-A6A8-4AA7-A6EA-D5A08874AE75'              -- uniqueidentifier
	SELECT * FROM dbo.ChucVu

	create proc procKH
		@TenKH nvarchar(50),
		@SDT varchar(30)
	as
	begin
		Declare @MaKH char(7)
		if not exists (select * from KhachHang) 
			Set @MaKH=1
		else
			Set @MaKH=(select RIGHT(MAX(MAKH),5) from KhachHang)+1
		Set @MaKH='KH'+REPLICATE('0',5-LEN(@MaKH))+@MaKH
		insert into KhachHang(MaKH, Ten, Sdt, TrangThai) values(@MaKH,@TenKH, @SDT, 1)
		select MAKH
		from KhachHang
		where MaKH = @MaKH
		return
	end


	----- Thêm thuộc tính bảng Hóa Đơn
	ALTER TABLE dbo.HoaDonChiTiet
	ADD TienThua DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDonChiTiet
	ADD TienGiamGia DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDonChiTiet
	ADD TrangThai INT DEFAULT 1
	------Thêm Tiền Cọc
	ALTER TABLE dbo.HoaDon
	ADD TienCoc DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDon
	ADD TienShip DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDon
	ADD TenNguoiShip NVARCHAR(50)
	ALTER TABLE dbo.HoaDon
	ADD SDTNguoiShip DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDon
	ADD SDTNguoiNhan DECIMAL(20,0) DEFAULT 0
	ALTER TABLE dbo.HoaDon
	ADD TongTien DECIMAL(20,0) DEFAULT 0
	----- Chức Năng Giảm Giá


	------ Proc themSIze
	USE [DuAn1_QuanLiBanQuanAo]
GO
/****** Object:  StoredProcedure [dbo].[procThemSIZE]    Script Date: 01/12/2022 11:40:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc procThemSIZE
		@TenSoSize nvarchar(50)
	as
	begin
		Declare @MaSize char(7)
		if not exists (select * from Size) 
			Set @MaSize=1
		else
			Set @MaSize=(select RIGHT(MAX(MA),5) from Size)+1
		Set @MaSize='S'+REPLICATE('0',5-LEN(@MaSize))+@MaSize
		insert into Size(Ma, SoSize) values(@MaSize,@TenSoSize)
		select ma 
		from Size
		where ma = @MaSize
		return
	END
    
	---- Proc newProduct
	CREATE PROC [dbo].[InsertNewProduct] (
@Ten NVARCHAR(MAX)
)
AS
BEGIN
	DECLARE @getIndexProduct int;
	DECLARE @defaultCode NVARCHAR(10) = N'SP';
	DECLARE @newProductCode NVARCHAR(25);
	
	SELECT @getIndexProduct = ISNULL(Count(IdSP), 0) + 1 FROM SanPham
	SELECT @newProductCode = @defaultCode + N'0000' + CAST(@getIndexProduct AS NVARCHAR(5))

	INSERT INTO SanPham (IdSP, MaSP, Ten) VALUES (newId(), @newProductCode, @Ten)
	SELECT MaSP 
	From SanPham
	Where MaSP = @newProductCode
	RETURN
END

---- Proc KH
CREATE proc [dbo].[procKH]
		@TenKH nvarchar(50),
		@SDT varchar(30)
	as
	begin
		Declare @MaKH char(7)
		if not exists (select * from KhachHang) 
			Set @MaKH=1
		else
			Set @MaKH=(select RIGHT(MAX(MAKH),5) from KhachHang)+1
		Set @MaKH='KH'+REPLICATE('0',5-LEN(@MaKH))+@MaKH
		insert into KhachHang(MaKH, Ten, Sdt, TrangThai) values(@MaKH,@TenKH, @SDT, 1)
		select MAKH
		from KhachHang
		where MaKH = @MaKH
		return
	end

---- Update DB 1.6
DROP TABLE dbo.PTTT
DROP TABLE dbo.LoaiHinhTT
CREATE TABLE HinhThucThanhToan(
IdHTTT UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdHoaDon UNIQUEIDENTIFIER ,
MaHTTT VARCHAR(20) UNIQUE,
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL,
LoaiHinhThanhToan VARCHAR(20),
TongTienThanhToan DECIMAL(20,0),
TrangThai INT DEFAULT 1,
CONSTRAINT FK_HTTT_HoaDon FOREIGN KEY(IdHoaDon) REFERENCES HoaDon(IdHoaDon),
)

ALTER TABLE dbo.HoaDon 
DROP COLUMN IdPTTT


ALTER TABLE dbo.HoaDon 
ADD IdHTTT UNIQUEIDENTIFIER
----thêm cột tổng tiền trong Hóa Đơn
ALTER TABLE dbo.HoaDon
ADD TongTien DECIMAL(20,0)



CREATE TABLE GiamGia(
IdGiamGia UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
MaGiamGia VARCHAR(20) UNIQUE,
TenGiamGia NVARCHAR(20),
NgayBatDau DATE DEFAULT NULL,
NgayKetThuc DATE DEFAULT NULL,
MucGiamGiaPhanTram DATE DEFAULT NULL,
DieuKienGiamGia NVARCHAR(50) NULL,
TrangThai INT DEFAULT 1,
LoaiGiamGia NVARCHAR
)


CREATE TABLE SPGiamGia(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdGiamGia UNIQUEIDENTIFIER,
IdHoaDon UNIQUEIDENTIFIER,
DonGia DECIMAL(20,0),
SoTienConLai DECIMAL(20,0),
TrangThai INT DEFAULT 1,
CONSTRAINT FK_GiamGia FOREIGN KEY(IdGiamGia) REFERENCES dbo.GiamGia(IdGiamGia),
CONSTRAINT FK_IdHoaDon FOREIGN KEY(IdHoaDon) REFERENCES dbo.HoaDon(IdHoaDon),
)

SELECT * FROM dbo.HoaDonChiTiet
SELECT * FROM dbo.HoaDon

sel


SELECT        dbo.SanPham.MaSP, dbo.SanPham.Ten, dbo.Size.SoSize, dbo.ChatLieu.TenChatLieu, dbo.ChiTietSP.GiaBan, dbo.ChiTietSP.TrangThai, dbo.LoaiSanPham.TenLoaiSP, dbo.MauSac.TenMauSac
FROM            dbo.ChatLieu INNER JOIN
                         dbo.ChiTietSP ON dbo.ChatLieu.IdChatLieu = dbo.ChiTietSP.IdChatLieu INNER JOIN
                         dbo.LoaiSanPham ON dbo.ChiTietSP.IdLoaiSP = dbo.LoaiSanPham.IdLoaiSP INNER JOIN
                         dbo.MauSac ON dbo.ChiTietSP.IdMauSac = dbo.MauSac.IdMauSac INNER JOIN
                         dbo.Size ON dbo.ChiTietSP.IdSize = dbo.Size.IdSize INNER JOIN
                         dbo.ThuongHieu ON dbo.ChiTietSP.IdThuongHieu = dbo.ThuongHieu.IdThuongHieu INNER JOIN
                         dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.IdSP
						 WHERE GiaBan BETWEEN 0 AND 100000


						 SELECT * FROM dbo.ChiTietSP WHERE GiaBan BETWEEN 0 AND 100000

						 INSERT INTO dbo.GiamGia
						 (
						     IdGiamGia,
						     MaGiamGia,
						     TenGiamGia,
						     NgayBatDau,
						     NgayKetThuc,
						     MucGiamGiaPhanTram,
						     DieuKienGiamGia,
						     TrangThai,
						     LoaiGiamGia
						 )
						 VALUES
						 (   DEFAULT, -- IdGiamGia - uniqueidentifier
						     NULL,    -- MaGiamGia - varchar(20)
						     NULL,    -- TenGiamGia - nvarchar(20)
						     DEFAULT, -- NgayBatDau - date
						     DEFAULT, -- NgayKetThuc - date
						     DEFAULT, -- MucGiamGiaPhanTram - date
						     NULL,    -- DieuKienGiamGia - nvarchar(50)
						     DEFAULT, -- TrangThai - int
						     NULL     -- LoaiGiamGia - nvarchar(1)
						     )

							 DROP PROC dbo.insertKhuyenMai
							 CREATE PROC insertKhuyenMai
    @TenGiamGia NVARCHAR(50),
    @NgayBatDau DATE,
	@NgayKetThuc DATE,
	@MucGiam FLOAT,
	@DieuKienGiamGia NVARCHAR(50),
	@TrangThai INT,
	@LoaiGiamGia NVARCHAR(50)
AS
BEGIN
    DECLARE @MaGiamGgia CHAR(10);
    IF NOT EXISTS (SELECT * FROM dbo.GiamGia)
        SET @MaGiamGgia = 1;
    ELSE
        SET @MaGiamGgia =
        (
            SELECT RIGHT(MAX(MaGiamGia), 5)FROM dbo.GiamGia
        ) + 1;
    SET @MaGiamGgia = 'GG' + REPLICATE('0', 5 - LEN(@MaGiamGgia)) + @MaGiamGgia;
    INSERT INTO dbo.GiamGia
    (
        IdGiamGia,
        MaGiamGia,
        TenGiamGia,
        NgayBatDau,
        NgayKetThuc,
        MucGiamGiaPhanTram,
        DieuKienGiamGia,
        TrangThai,
        LoaiGiamGia
    )
    VALUES
    (   DEFAULT, -- IdGiamGia - uniqueidentifier
        @MaGiamGgia,    -- MaGiamGia - varchar(20)
        @TenGiamGia,    -- TenGiamGia - nvarchar(20)
        @NgayBatDau, -- NgayBatDau - date
        @NgayKetThuc, -- NgayKetThuc - date
        @MucGiam, -- MucGiamGiaPhanTram - date
        @DieuKienGiamGia,    -- DieuKienGiamGia - nvarchar(50)
        @TrangThai, -- TrangThai - int
        @LoaiGiamGia     -- LoaiGiamGia - nvarchar(50)
        );

		SELECT * FROM dbo.GiamGia;
    RETURN;
END;

EXEC dbo.insertKhuyenMai @TenGiamGia = N'Combo1',           -- nvarchar(50)
                         @NgayBatDau = '2022-12-05',  -- date
                         @NgayKetThuc = '2022-12-05', -- date
                         @MucGiam = 30,              -- float
                         @DieuKienGiamGia = N'KO biet',      -- nvarchar(50)
                         @TrangThai = 1,              -- int
                         @LoaiGiamGia = N'ko Biet'           -- nvarchar(50)


			
						 DECLARE @MaGiamGgia CHAR(10);
    IF NOT EXISTS (SELECT * FROM dbo.GiamGia)
        SET @MaGiamGgia = 1;
    ELSE
        SET @MaGiamGgia =
        (
            SELECT RIGHT(MAX(MaGiamGia), 5)FROM dbo.GiamGia
        ) + 1;
    SET @MaGiamGgia = 'GG' + REPLICATE('0', 5 - LEN(@MaGiamGgia)) + @MaGiamGgia;
	PRINT @MaGiamGgia

	ALTER TABLE dbo.SPGiamGia
	DROP CONSTRAINT FK_IdSP
	ALTER TABLE dbo.SPGiamGia
	ADD CONSTRAINT FK_IdSP FOREIGN KEY(IdSP) REFERENCES dbo.ChiTietSP(IdCTSP)

	DELETE FROM HoaDon
	DELETE FROM HoaDonChiTiet

	select * from NhanVien
	update ChiTietSP set SoLuong = 10000 where SoLuong = 0


	drop proc procThemHdTaiQuay
	create proc procThemHdTaiQuay
		@NgayTao date,
		@IdNV UNIQUEIDENTIFIER,
		@TrangThai int
	as
	begin
		Declare @MaHD char(7)
		if not exists (select * from HoaDon) 
			Set @MaHD=1
		else
			Set @MaHD=(select RIGHT(MAX(MaHD),5) from HoaDon)+1
		Set @MaHD='HD'+REPLICATE('0',5-LEN(@MaHD))+@MaHD
		insert into HoaDon(MaHD, NgayTao, IdNV, TrangThai) values(@MaHD,@NgayTao, @IdNV, @TrangThai)
		select IdHoaDon ,MaHD
		from HoaDon
		where MaHD = @MaHD
		return
	end

	

									
	SELECT * FROM dbo.GiamGia
	SELECT * FROM dbo.SPGiamGia
	UPDATE dbo.SPGiamGia SET SoTienConLai = DonGia - (DonGia * 1 /100) WHERE IdGiamGia = '7036AB5D-C054-4089-8B45-4AA10C56AF43'
					UPDATE dbo.GiamGia SET TenGiamGia = '', NgayBatDau = '', NgayKetThuc = '', MucGiamGiaPhanTram = '', TrangThai = ''	WHERE IdGiamGia
						

						