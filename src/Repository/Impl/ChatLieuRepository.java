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

    @Override
    public List<ChatLieu> getAll() {       
        List<ChatLieu> ListChatLieu = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM dbo.ChatLieu";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
               String id = rs.getString("IdChatLieu,Ma,TenChatlieu");
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


}
