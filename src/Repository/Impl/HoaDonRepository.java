/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Models.HoaDon;
import Repository.IHoaDonRepository;
import Ultilities.DBConnection;
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
    public ArrayList<HoaDon> getHoaDonAlls() {

        ArrayList<HoaDon> HDList = new ArrayList<>();
        String query = "SELECT * FROM HoaDon";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                HDList.add(new HoaDon(rs.getString("IdHoaDon"), rs.getString("IdKH"), rs.getString("IdNV"), rs.getString("IdPTTT"), rs.getString("MaHD"), rs.getString("NgayTao"), rs.getString("NgayThanhToan"), rs.getString("NgayHen"), rs.getString("NgayShip"), rs.getString("NgayNhan"), rs.getString("TenNguoiNhan"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getInt("TrangThai")));

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


