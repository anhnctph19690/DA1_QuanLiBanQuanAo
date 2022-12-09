/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModels.ChatLieu;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IChatLieuRepository {

    List<ChatLieu> getAll();

    public String getIDByChatLieu(String tenChatLieu);

  
    
    ChatLieu addCbb(String name);
}
