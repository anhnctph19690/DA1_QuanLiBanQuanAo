/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicer.Impl;

import Models.ChatLieu;
import Repository.IChatLieuRepository;
import Repository.Impl.ChatLieuRepository;
import Servicer.IChatLieuService;
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
          List<QLChatLieu> listChatLieu = new ArrayList<>();
        
        List<ChatLieu> ListRepo = this.chatLieuRepo.getAll();
        
        for (ChatLieu o : ListRepo) {
          listChatLieu.add(new QLChatLieu(o.getMa()));
        }
        
        return listChatLieu;
    }
    
}
