/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Models.ThuongHieu;
import Repository.IThuongHieuRepository;
import Ultilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ThuongHieuRepository implements IThuongHieuRepository{
    Connection conn = DBConnection.getConnection();
    
    @Override
    public ArrayList<ThuongHieu> getAll() {
        String query = "SELECT idThuongHieu,Ma ,TenThuongHieu FROM dbo.ThuongHieu";
        ArrayList<ThuongHieu> ThList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                ThList.add(new ThuongHieu(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return ThList;
    }

    @Override
    public String getIDByThuongHieu(String tenThuongHieu) {
        String query = "SELECT * FROM ThuongHieu WHERE TenthuongHieu = ?";
        String IdThuongHieu = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tenThuongHieu);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                IdThuongHieu = rs.getString("IdThuongHieu");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return IdThuongHieu;
    }
    
}
