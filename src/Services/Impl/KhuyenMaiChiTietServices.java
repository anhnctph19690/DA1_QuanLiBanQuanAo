/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import Repository.Impl.KhuyenMaiChiTietRepository;
import DomainModels.KhuyenMaiChiTiet;
import java.util.ArrayList;

/**
 *
 * @author tuane_nluzcuo
 */
public class KhuyenMaiChiTietServices {
    KhuyenMaiChiTietRepository khuyenMaiChiTietRepository = new KhuyenMaiChiTietRepository();
    
    public ArrayList<KhuyenMaiChiTiet> getListByIdKhuyenMai(String IdGiamGia){
        
        return khuyenMaiChiTietRepository.getListByIdKhuyenMai(IdGiamGia);
    }
    
     public ArrayList<KhuyenMaiChiTiet> getAlls(){
         return khuyenMaiChiTietRepository.getAlls();
     }
     
     
     public void insert(KhuyenMaiChiTiet kmct){
         khuyenMaiChiTietRepository.insert(kmct);
     }
     
     public void updateStatusSPGiamGia(int status, String IdGiamGia){
         this.khuyenMaiChiTietRepository.updateStatusSPGiamGia(status, IdGiamGia);
     }
     public void delete(String IdKM) {
        this.khuyenMaiChiTietRepository.delete(IdKM);
    }
}
