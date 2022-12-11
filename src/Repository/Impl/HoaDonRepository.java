/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModels.HoaDon;
import Repository.IHoaDonRepository;
import Ultilities.DBConnection;
import ViewModel.QLHoaDon;
import ViewModel.QLHoaDonThongKe;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tuane_nluzcuo
 */
public class HoaDonRepository implements IHoaDonRepository {

    @Override
    public List<QLHoaDon> getAllHoaDonCho(int trangThai) {

        String query = "select ROW_NUMBER() OVER (ORDER BY hd.Mahd DESC) , hd.IdHoaDon, hd.MaHD, hd.NgayTao, nv.TenNV, hd.TrangThai, kh.Ten, kh.Sdt\n"
                + "    from NhanVien nv\n"
                + "	join HoaDon hd on hd.IdNV = nv.IdNV\n"
                + "    left join KhachHang kh on hd.IdKH = kh.IdKH\n"
                + "	where hd.TrangThai = ?";
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, trangThai);
            ResultSet rs = ps.executeQuery();
            List<QLHoaDon> list = new ArrayList<>();
            while (rs.next()) {
                QLHoaDon hoaDon = new QLHoaDon();
                hoaDon.setSoThuTu(rs.getInt(1));
                hoaDon.setIdHoaDon(rs.getString(2));
                hoaDon.setMaHoaDon(rs.getString(3));
                hoaDon.setNgayTao(rs.getDate(4));
                hoaDon.setTenNhanVien(rs.getString(5));
                hoaDon.setTrangThai(rs.getInt(6));
                hoaDon.setTenKhachHang(rs.getString(7));
                hoaDon.setSdtKhachHang(rs.getString(8));
                list.add(hoaDon);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public String getIDKHBMaKH(String MaKH) {
        String query = "SELECT IdKH FROM dbo.KhachHang WHERE MaKH = ?";
        String IDKH = null;
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, MaKH);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (true) {
                IDKH = rs.getString("IdKH");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return IDKH;
    }

    @Override
    public boolean add(HoaDon hoaDon) {
        int check = 0;
        String query = "{call procThemHdTaiQuay(?, ?, ?)}";
        try ( Connection conn = DBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, hoaDon.getNgayTao());
            ps.setObject(2, hoaDon.getIdNhanVien());
            ps.setObject(3, hoaDon.getTrangThai());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    public static void main(String[] args) {
        //new HoaDonRepository().getAllHoaDonCho(1).forEach(s -> System.out.println(s.toString()));
//        HoaDonRepository hdr = new HoaDonRepository();

//          String ngayBatDau = "2022-11-29";
//          String ngayKetThuc = "2022-12-02";
//          for (QLHoaDon x : new HoaDonRepository().getNgayTao(ngayBatDau, ngayKetThuc)) {
//              System.out.println(x.toString());
//  
//        }
//     for (QLHoaDon x: new HoaDonRepository().getAllHD()) {
//                  System.out.println(x.toString());
//              }
   
       
    }

    @Override
    public boolean updateTrangThai(String id, int trangThai) {
        int check = 0;
        String query = "UPDATE dbo.HoaDon SET TrangThai = ? WHERE IdHoaDon = ?";
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, trangThai);
            ps.setString(2, id);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean updateHoaDonGiaoHang(HoaDon hd, String id) {
        int check = 0;
        String query = "UPDATE dbo.HoaDon SET TenNguoiNhan = ?, DiaChi = ?, SDT = ?, ngayThanhToan = ?, ngayShip = ?, ngayNhan = ?, ngayHen = ? WHERE IdHoaDon = ?";
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, hd.getTenNguoiNhan());
            ps.setObject(2, hd.getDiaChi());
            ps.setObject(3, hd.getsDT());
            ps.setObject(4, hd.getNgayThanhToan());
            ps.setObject(5, hd.getNgayShip());
            ps.setObject(6, hd.getNgayNhan());
            ps.setObject(7, hd.getNgayHen());
            ps.setObject(8, id);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<QLHoaDon> getAllHoaDonDatHang() {
        String query = "select ROW_NUMBER() OVER (ORDER BY hd.Mahd DESC) , hd.IdHoaDon, hd.MaHD, hd.NgayTao, nv.TenNV, hd.TrangThai, kh.Ten, kh.Sdt\n"
                + "    from NhanVien nv\n"
                + "	join HoaDon hd on hd.IdNV = nv.IdNV\n"
                + "    left join KhachHang kh on hd.IdKH = kh.IdKH\n"
                + "	where hd.TrangThai = 2";
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<QLHoaDon> list = new ArrayList<>();
            while (rs.next()) {
                QLHoaDon hoaDon = new QLHoaDon();
                hoaDon.setSoThuTu(rs.getInt(1));
                hoaDon.setIdHoaDon(rs.getString(2));
                hoaDon.setMaHoaDon(rs.getString(3));
                hoaDon.setNgayTao(rs.getDate(4));
                hoaDon.setTenNhanVien(rs.getString(5));
                hoaDon.setTrangThai(rs.getInt(6));
                hoaDon.setTenKhachHang(rs.getString(7));
                hoaDon.setSdtKhachHang(rs.getString(8));
                list.add(hoaDon);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateHoaDon(HoaDon hd, String id) {
        int check = 0;
        String query = "UPDATE dbo.HoaDon SET NgayThanhToan = ? , IdKH = ? WHERE IdHoaDon = ?";
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, hd.getNgayThanhToan());
            ps.setObject(2, hd.getIdKhachHang());

            ps.setObject(3, id);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean updateNgayNhan(HoaDon hd, String id) {
        int check = 0;
        String query = "UPDATE dbo.HoaDon SET ngayNhan = ? WHERE IdHoaDon = ?";
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, hd.getNgayNhan());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    public int demSoLuongHoaDonDTT() {
        int soluonghoadon = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(MaHD) FROM HoaDon WHERE TrangThai = 1 ";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                soluonghoadon = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return soluonghoadon;

    }

    public int demSoLuongHoaDonCTT() {
        int soluonghoadon = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(MaHD) AS N'Hóa đơn chưa thanh toán' FROM HoaDon WHERE TrangThai = 0 ";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                soluonghoadon = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return soluonghoadon;

    }

    public int TongHD() {
        int Tonghoadon = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(MaHD) FROM HoaDon  ";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                Tonghoadon = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return Tonghoadon;

    }

    public int doanhThuNgay() {
        int doanhThuNgay = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT  dbo.HoaDon.NgayTao, dbo.HoaDonChiTiet.SoLuong, dbo.HoaDonChiTiet.donGia ,SUM(SoLuong * donGia) As DoanhThu "
                + "FROM   dbo.HoaDon INNER JOIN dbo.HoaDonChiTiet ON dbo.HoaDon.IdHoaDon = dbo.HoaDonChiTiet.IdHoaDon\n"
                + "where Day(NgayTao) = day(getDate())\n"
                + "group by ngaytao,soLuong,DonGia ";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                doanhThuNgay = rs.getInt("DoanhThu");
            }

        } catch (Exception e) {
        }
        return doanhThuNgay;

    }

    public int doanhThuQuy() {
        int doanhThuQuy = 0;
        int quy = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT   Sum(SoLuong * donGia) As DoanhThuQuy "
                + "FROM   dbo.HoaDon INNER JOIN\n"
                + "  dbo.HoaDonChiTiet ON dbo.HoaDon.IdHoaDon = dbo.HoaDonChiTiet.IdHoaDon "
                + " where DATEPART(quarter, NgayTao) = DATEPART(quarter, getdate())";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                doanhThuQuy = rs.getInt("DoanhThuQuy");
                quy = rs.getInt("Quy");
            }

        } catch (Exception e) {
        }
        return doanhThuQuy;

    }

    @Override
    public List<QLHoaDon> getAllHD() {

        String query = "select ROW_NUMBER() OVER (ORDER BY hd.Mahd DESC) , hd.IdHoaDon, hd.MaHD, hd.NgayTao, nv.TenNV, hd.TrangThai, kh.Ten, kh.Sdt\n"
                + "    from NhanVien nv\n"
                + "	join HoaDon hd on hd.IdNV = nv.IdNV\n"
                + "    left join KhachHang kh on hd.IdKH = kh.IdKH\n";

        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            List<QLHoaDon> list = new ArrayList<>();
            while (rs.next()) {
                QLHoaDon hoaDon = new QLHoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));
                list.add(hoaDon);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;

    }


    @Override
    public List<QLHoaDon> getNgayTao(String ngayBatDau, String ngayKetThuc) {
         String query = "select ROW_NUMBER() OVER (ORDER BY hd.Mahd DESC) , hd.IdHoaDon, hd.MaHD, hd.NgayTao, nv.TenNV, hd.TrangThai, kh.Ten, kh.Sdt\n"
                + "    from NhanVien nv\n"
                + "	join HoaDon hd on hd.IdNV = nv.IdNV\n"
                + "    left join KhachHang kh on hd.IdKH = kh.IdKH\n"
                + "    where hd.ngaytao between ? and ? ";

        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, ngayBatDau);
            ps.setObject(2, ngayKetThuc);

            ResultSet rs = ps.executeQuery();
            List<QLHoaDon> list = new ArrayList<>();
            while (rs.next()) {
                QLHoaDon hoaDon = new QLHoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));
                list.add(hoaDon);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List<QLHoaDon> getByName(String name) {
         String sql = "select ROW_NUMBER() OVER (ORDER BY hd.Mahd DESC) , hd.IdHoaDon, hd.MaHD, hd.NgayTao, nv.TenNV, hd.TrangThai, kh.Ten, kh.Sdt\n"
                + "                 from NhanVien nv\n"
                + "                	join HoaDon hd on hd.IdNV = nv.IdNV\n"
                + "                    left join KhachHang kh on hd.IdKH = kh.IdKH\n"
                + "                    where nv.TenNV like ?";
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            List<QLHoaDon> list = new ArrayList<>();
            while (rs.next()) {
                QLHoaDon hoaDon = new QLHoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));
                list.add(hoaDon);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }



    

}
