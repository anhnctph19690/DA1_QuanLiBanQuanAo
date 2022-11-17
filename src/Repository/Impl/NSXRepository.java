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
public class NSXRepository implements INSXRepository{

    @Override
    public List<NSX> getAll() {
        
         List<NSX> ListNSX = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
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

  
}
