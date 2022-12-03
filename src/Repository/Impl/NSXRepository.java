/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModels.NSX;
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
      final String InsertNSX= "{call procThemNSX(?)}";

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

  
    public static void main(String[] args) {
        NSX nsx = new NSX();
    
    }

    @Override
    public NSX addCbb(String name) {
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(InsertNSX);

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NSX nsx = new NSX();
                nsx.setMaNSX(rs.getString(1));
                return nsx;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
