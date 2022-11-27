/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Models.HoaDon;
import Repository.IHoaDonRepository;
import Ultilities.DBConnection;
import ViewModel.QLHoaDon;
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
    public List<QLHoaDon> getAll() {

        String query = "select ROW_NUMBER() OVER (ORDER BY hd.Mahd DESC) , hd.IdHoaDon, hd.MaHD, hd.NgayTao, nv.TenNV, hd.TrangThai, kh.Ten, kh.Sdt\n"
                + "from NhanVien nv\n"
                + "join HoaDon hd on hd.IdNV = nv.IdNV\n"
                + "left join KhachHang kh on hd.IdKH = kh.IdKH";
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
                hoaDon.setSdt(rs.getString(8));
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
        new HoaDonRepository().getAll().forEach(s -> System.out.println(s.getIdHoaDon()));
       
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
        String query = "UPDATE dbo.HoaDon SET TenNguoiNhan = ?, DiaChi = ?, SDT = ? WHERE IdHoaDon = ?";
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, hd.getTenNguoiNhan());
            ps.setObject(2, hd.getDiaChi());
            ps.setObject(3, hd.getsDT());
            ps.setObject(4, id);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }
    
    public int demSoLuongHoaDonDTT(){
        int soluonghoadon = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(MaHD) FROM HoaDon WHERE TrangThai = 1 ";
       
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
           
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
           while(rs.next() == true){ 
               soluonghoadon = rs.getInt(1);
           }
            
        } catch (Exception e) {
        }
        return soluonghoadon;
        
    } 
    
    public int demSoLuongHoaDonCTT(){
        int soluonghoadon = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(MaHD) AS N'Hóa đơn chưa thanh toán' FROM HoaDon WHERE TrangThai = 0 ";
       
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();
            
            ResultSet rs = ps.getResultSet();
           while(rs.next() == true){ 
               soluonghoadon = rs.getInt(1);
           }
            
        } catch (Exception e) {
        }
        return soluonghoadon;
        
    } 
    
    
     public int TongHD(){
        int Tonghoadon = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(MaHD) FROM HoaDon  ";
       
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();
            
            ResultSet rs = ps.getResultSet();
           while(rs.next() == true){ 
               Tonghoadon = rs.getInt(1);
           }
            
        } catch (Exception e) {
        }
        return Tonghoadon;
        
    } 
    
}


