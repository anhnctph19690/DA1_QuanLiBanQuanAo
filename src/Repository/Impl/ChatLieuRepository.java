/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModels.ChatLieu;
import Repository.IChatLieuRepository;
import Ultilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class ChatLieuRepository implements IChatLieuRepository {

    Connection conn = DBConnection.getConnection();
    final String InsertChatLieu= "{call procThemChatLieu(?)}";
    @Override
    public List<ChatLieu> getAll() {
        List<ChatLieu> ListChatLieu = new ArrayList<>();
        try {

            String query = "SELECT * FROM dbo.ChatLieu";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("IdChatLieu");
                String ma = rs.getString("Ma");
                String ten = rs.getString("TenChatlieu");

                ChatLieu ct = new ChatLieu(id, ma, ten);
                ListChatLieu.add(ct);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListChatLieu;
    }

    @Override
    public String getIDByChatLieu(String tenChatLieu) {
        String query = "SELECT * FROM ChatLieu WHERE TenChatLieu = ?";
        String IdChatLieu = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tenChatLieu);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                IdChatLieu = rs.getString("IdChatLieu");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return IdChatLieu;
    }



    @Override
    public ChatLieu addCbb(String name) {
         try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(InsertChatLieu);

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setMa(rs.getString(1));
                return cl;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        String name = "U";
        new ChatLieuRepository().addCbb(name);
    }

}
