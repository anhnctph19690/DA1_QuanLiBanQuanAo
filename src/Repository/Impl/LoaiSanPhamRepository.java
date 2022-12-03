/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModels.KichThuoc;
import DomainModels.LoaiSanPham;
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

    Connection conn = DBConnection.getConnection();
    final String InsertLoaiSP= "{call procThemLoaiSanPham(?)}";
    @Override
    public List<LoaiSanPham> getAll() {
         List<LoaiSanPham> ListLSP = new ArrayList<>();
        try {
            
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

    @Override
    public String getIDByLoaiSP(String tenLoaiSP) {
        String query = "SELECT * FROM LoaiSanPham WHERE TenLoaiSP = ?";
        String idLoaiSP = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tenLoaiSP);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                idLoaiSP = rs.getString("IdLoaiSP");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return idLoaiSP;
    }

 
    @Override
    public LoaiSanPham addCbb(String name) {
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(InsertLoaiSP);

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setMa(rs.getString(1));
                return lsp;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
}
