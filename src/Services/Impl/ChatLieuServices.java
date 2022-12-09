/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.ChatLieu;
import Repository.IChatLieuRepository;
import Repository.Impl.ChatLieuRepository;
import Services.IChatLieuService;
import ViewModel.QLChatLieu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChatLieuServices implements IChatLieuService{
    ChatLieuRepository chatLieuRepo = new ChatLieuRepository();
    @Override
    public List<QLChatLieu> getAll() {
          List<QLChatLieu> ds = new ArrayList<>();
        
       List<ChatLieu> list = this.chatLieuRepo.getAll();
       
        for (ChatLieu ct : list) {
            QLChatLieu dsct = new QLChatLieu(ct.getTenChatLieu());
            
            ds.add(dsct);
        }
        return ds;
    }

  
    
}
