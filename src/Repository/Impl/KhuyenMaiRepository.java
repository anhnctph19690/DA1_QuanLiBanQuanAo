/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModels.GiamGia;
import Ultilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuane_nluzcuo
 */
public class KhuyenMaiRepository {

    Connection conn = DBConnection.getConnection();

    public void khuyenMaiInsert(GiamGia gg) {
        String query = "{call insertKhuyenMai(?,?,?,?,?,?,?)}";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, gg.getTenGiamGia());
            ps.setString(2, gg.getNgayBatDau());
            ps.setString(3, gg.getNgayKetThuc());
            ps.setDouble(4, gg.getMucGiamGia());
            ps.setString(5, gg.getDieuKienGiamGia());
            ps.setInt(6, gg.getTrangThai());
            ps.setString(7, gg.getLoaiGiamGia());
            ps.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    
    public ArrayList<GiamGia> getAlls(){
        ArrayList<GiamGia> khuyenMaiList = new ArrayList<>();
        String query = "SELECT * FROM dbo.GiamGia";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                khuyenMaiList.add(new GiamGia(rs.getString("IdGiamGia"), rs.getString("MaGiamGia"), rs.getString("TenGiamGia"), rs.getString("NgayBatDau"), rs.getString("NgayKetThuc"), rs.getString("DieuKienGiamGia"), rs.getDouble("MucGiamGiaPhanTram"), rs.getInt("TrangThai"), rs.getString("LoaiGiamGia")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return khuyenMaiList;
    }
}
