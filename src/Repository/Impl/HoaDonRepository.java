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

/**
 *
 * @author tuane_nluzcuo
 */
public class HoaDonRepository implements IHoaDonRepository {

    Connection conn = DBConnection.getConnection();

    @Override
    public ArrayList<QLHoaDon> getHoaDonAlls() {

        ArrayList<QLHoaDon> HDList = new ArrayList<>();
        String query = "SELECT dbo.HoaDon.*, dbo.HoaDon.MaHD AS Expr1, dbo.HoaDon.NgayTao AS Expr2, dbo.NhanVien.TenNV FROM dbo.HoaDon INNER JOIN dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.IdNV";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                HDList.add(new QLHoaDon(rs.getString("MaHD"), rs.getString("NgayTao"), rs.getString("TenNV"), rs.getInt("TrangThai")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return HDList;
    }

    public String getIDKHBMaKH(String MaKH) {
        String query = "SELECT IdKH FROM dbo.KhachHang WHERE MaKH = ?";
        String IDKH = null;
        try {
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
        String query = "{call procThemHdTaiQuay(?, ?, ?, ?)}";
        try ( Connection conn = DBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, hoaDon.getNgayTao());
            ps.setObject(2, hoaDon.getIdNhanVien());
            ps.setObject(3, hoaDon.getIdKhachHang());
            ps.setObject(4, hoaDon.getTrangThai());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }
    
    public boolean uppdateTrangThai(String IDHoaDon, int trangThai){
        int check = 0;
        String query = "UPDATE dbo.HoaDon SET TrangThai = ? WHERE IdHoaDon = ?";
        try {
            PreparedStatement ps = conn.prepareCall(query);
            ps.setInt(1, trangThai);
            ps.setString(2, IDHoaDon);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check > 0;
    }
    
    

    public static void main(String[] args) {
        HoaDon hd = new HoaDon();
        hd.setNgayTao(new Date());
        hd.setIdNhanVien("2F94B972-79D2-4581-BD54-A1C4E72292A7");
        hd.setIdKhachHang("E0BFE464-AC4A-40AD-A4A3-899879FB9566");
        hd.setTrangThai(0);

        new HoaDonRepository().add(hd);
    }
}
