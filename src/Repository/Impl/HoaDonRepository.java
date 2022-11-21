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
    
    
    public String getIDKHBMaKH(String MaKH){
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
}


