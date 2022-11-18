/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Models.ChatLieu;
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
    public boolean addcbbChatLieu(ChatLieu cl) {
        int check = 0;
        String query = "insert into ChatLieu(ma,tenChatLieu) values (?,?)";
        try (PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setObject(1, cl.getMa());
            ps.setObject(2, cl.getTenChatLieu());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public static void main(String[] args) {
        ChatLieu cl = new ChatLieu();
        cl.setMa("CL123456");
        cl.setTenChatLieu("CLCLCL012");
        new ChatLieuRepository().addcbbChatLieu(cl);
    }

}
