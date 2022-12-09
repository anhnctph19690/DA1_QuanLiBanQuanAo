/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.GiamGia;
import Repository.Impl.KhuyenMaiRepository;
import java.util.ArrayList;

/**
 *
 * @author tuane_nluzcuo
 */
public class KhuyenMaiService {
    KhuyenMaiRepository khuyenMaiRepository = new  KhuyenMaiRepository();
    
    public void khuyenMaiInsert(GiamGia gg) {
        this.khuyenMaiRepository.khuyenMaiInsert(gg);
    }
    
    public ArrayList<GiamGia> getAlls(){
        return this.khuyenMaiRepository.getAlls();
    }
    
    public GiamGia getOneByMa(String maKM){
        return khuyenMaiRepository.getOneByMa(maKM);
    }
    
    public void updateStatus(int status, String maGiamGia){
        this.khuyenMaiRepository.updateStatus(status, maGiamGia);
    }
}
