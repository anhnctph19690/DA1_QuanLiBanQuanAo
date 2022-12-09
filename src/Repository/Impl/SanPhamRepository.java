/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModels.SanPham;
import Repository.ISanPhamRepository;
import Ultilities.DBConnection;
import ViewModel.QLNhanVien;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC- ASUS
 */
public class SanPhamRepository implements ISanPhamRepository {

    final String SELECT_BY = "select idSP from sanpham where maSP = ?";
    final String sql = "insert into SanPham "
            + "(MaSP,Ten)"
            + "VALUES(?,?)";
    final String InsertNewProduct = "{call InsertNewProduct(?)}";

    
    
    @Override
    public SanPham add(String name) {
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(InsertNewProduct);

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString(1));
                return sp;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(String name, String id) {
        int check = 0;
        try ( Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE SanPham SET "
                    + "Ten=? WHERE IdSP = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, id);

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;

    }

    @Override
    public boolean delete(String id) {
        int check = 0;
        try ( Connection conn = DBConnection.getConnection();) {
            String sql = "DELETE SanPham "
                    + " WHERE IdSP =? ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public SanPham getOne(String id) {
        try ( Connection conn = DBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(SELECT_BY)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setIdSanPham(rs.getString(1));
                return sanPham;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    

}
