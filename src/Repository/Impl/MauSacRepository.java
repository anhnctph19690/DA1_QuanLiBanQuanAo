/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Models.MauSac;
import Repository.*;
import Ultilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuane_nluzcuo
 */
public class MauSacRepository implements IMauSacRepository {

    Connection conn = DBConnection.getConnection();

    @Override
    public String getIdMauSac(String tenMauSac) {
        String query = "SELECT IdMauSac FROM MauSac WHERE TenMauSac = ?";
        String IdMauSac = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tenMauSac);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {                
                IdMauSac = rs.getString("IdMauSac");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return IdMauSac;
    }

    @Override
    public ArrayList<MauSac> getAllsMauSac() {
        String query = "SELECT * FROM MauSac";
        ArrayList<MauSac> listMauSac = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                listMauSac.add(new MauSac(rs.getString("IdMauSac"), rs.getString("Ma"), rs.getString("TenMauSac")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listMauSac;
    }

    @Override
    public boolean addCbbMauSac(MauSac ms) {
        int check = 0;
        String query = "insert into MauSac(ma,tenmausac) values (?,?)";
        try (PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setObject(1, ms.getMaMauSac());
            ps.setObject(2, ms.getTenMauSac());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

}
