/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import Models.KichThuoc;
import java.util.ArrayList;
import Repository.IKichThuocRepo;
import Repository.Impl.KichThuocRepo;
import Services.IKichThuocServices;
import ViewModel.QLKichThuoc;

/**
 *
 * @author tuane_nluzcuo
 */
public class KichThuocService implements IKichThuocServices{

    KichThuocRepo kichThuocRepo = new KichThuocRepo();
    
    @Override
    public ArrayList<QLKichThuoc> getAllsKichThuoc() {
        ArrayList<QLKichThuoc> kTList = new ArrayList<>();
        
        ArrayList<KichThuoc> ListRepo = this.kichThuocRepo.getAllsKichThuoc();
        
        for (KichThuoc o : ListRepo) {
            kTList.add(new QLKichThuoc(o.getSoSize()));
        }
        
        return kTList;
    }

    @Override
    public String addCbbKichThuoc(KichThuoc kt) {
         boolean add =kichThuocRepo.addCbbKichThuoc(kt);
        if (add) {
            return "Thanh cong";
        }return "That bai";
    }


    
}
