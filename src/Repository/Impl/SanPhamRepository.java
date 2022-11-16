/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Models.SanPham;
import Repository.ISanPhamRepository;
import Ultilities.DBConnection;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author PC- ASUS
 */
public class SanPhamRepository implements ISanPhamRepository{

    @Override
    public List<SanPham> getAll() {
        ArrayList<SanPham> ListSanPham = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String puery = "SELECT * FROM SanPham";
            
            PreparedStatement ps = conn.prepareStatement(puery);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while(rs.next()== true){
            String id = rs.getString("IdSP");
                String ma = rs.getString("MaSP");
                String ten = rs.getString("Ten");
                
                SanPham sp = new SanPham(id, ma, ten);
                ListSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return ListSanPham;
    }

    @Override
    public void insert(SanPham sp) {
         try {
            Connection conn = DBConnection.getConnection();
            String sql = "insert into SanPham "
                    + "(MaSP,Ten)"
                    + "VALUES(?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());

            ps.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void update(SanPham sp, String id) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM SanPham SET"
                    + "MaSP =?,Ten=? WHERE IdSP =?";
                    

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());
            ps.setString(3, id);
            
            ps.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void delete(String id) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE SanPham "
                    + " WHERE IdSP =? ";
                    

            PreparedStatement ps = conn.prepareStatement(sql);
           
            ps.setString(1, id);
            
            ps.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
    
}
