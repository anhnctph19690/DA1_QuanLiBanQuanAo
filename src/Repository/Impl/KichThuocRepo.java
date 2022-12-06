/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModels.KichThuoc;
import Ultilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Repository.IKichThuocRepo;

/**
 *
 * @author tuane_nluzcuo
 */
public class KichThuocRepo implements IKichThuocRepo {

    Connection conn = DBConnection.getConnection();
    final String InsertKichThuoc = "{call procThemSIZE(?)}";
    @Override
    public ArrayList<KichThuoc> getAllsKichThuoc() {
        String query = "SELECT * FROM dbo.Size";
        ArrayList<KichThuoc> KTList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                KTList.add(new KichThuoc(rs.getString("IdSize"), rs.getString("SoSize")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return KTList;
    }

    @Override
    public String getIDBySize(String tenSize) {
        String query = "SELECT * FROM Size WHERE SoSize = ?";
        String IdSize = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tenSize);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                IdSize = rs.getString("IdSize");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return IdSize;
    }

   
    public static void main(String[] args) {
       
    }

    @Override
    public KichThuoc addCbb(String name) {
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(InsertKichThuoc);

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichThuoc kt = new KichThuoc();
                kt.setMa(rs.getString(1));
                return kt;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
