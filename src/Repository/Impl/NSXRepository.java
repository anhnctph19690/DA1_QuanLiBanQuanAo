/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Models.NSX;
import Repository.INSXRepository;
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
public class NSXRepository implements INSXRepository {

    Connection conn = DBConnection.getConnection();

    @Override
    public List<NSX> getAll() {

        List<NSX> ListNSX = new ArrayList<>();
        try {

            String query = "SELECT * FROM NSX";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("IdNSX");
                String ma = rs.getString("Ma");
                String ten = rs.getString("Ten");

                NSX nsx = new NSX(id, ma, ten);
                ListNSX.add(nsx);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListNSX;
    }

    @Override
    public String getIDByNSX(String tenNSX) {
        String query = "SELECT * FROM NSX WHERE Ten = ?";
        String IdNSX = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tenNSX);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                IdNSX = rs.getString("IdNSX");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return IdNSX;
    }

    @Override
    public boolean addCbbNSX(NSX nsx) {
        int check = 0;
        String query = "insert into NSX(ma,ten) values (?,?)";
        try ( PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setObject(1, nsx.getMaNSX());
            ps.setObject(2, nsx.getTenNSX());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public static void main(String[] args) {
        NSX nsx = new NSX();
        nsx.setMaNSX("nsx123");
        nsx.setTenNSX("N10928");
        new NSXRepository().addCbbNSX(nsx);
    }
}
