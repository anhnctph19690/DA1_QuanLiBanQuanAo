/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Models.LoaiSanPham;
import Repository.ILoaiSanPhamRepository;
import java.util.ArrayList;
import java.util.List;
import Ultilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


/**
 *
 * @author PC- ASUS
 */
public class LoaiSanPhamRepository implements ILoaiSanPhamRepository{

    @Override
    public List<LoaiSanPham> getAll() {
         List<LoaiSanPham> ListLSP = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM LoaiSanPham";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
               String id = rs.getString("IdLoaiSP");
              
                String ten = rs.getString("TenLoaiSP");
                
               LoaiSanPham lsp = new LoaiSanPham(id, ten);
                ListLSP.add(lsp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListLSP;
    }
    
}
