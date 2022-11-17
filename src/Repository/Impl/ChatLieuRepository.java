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
        String query = "SELECT idChatLieu,ma,tenChatLieu FROM dbo.ChatLieu";
        List<ChatLieu> ListChatLieu = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                ListChatLieu.add(new ChatLieu(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListChatLieu;
    }


}
