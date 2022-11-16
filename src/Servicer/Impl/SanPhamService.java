/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicer.Impl;

import Models.SanPham;
import Repository.Impl.SanPhamRepository;
import Servicer.ISanPhamService;
import ViewModel.QLSanPham;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public class SanPhamService implements ISanPhamService{

    public SanPhamRepository sanPhamRepository ;
    
    public SanPhamService(){
        this.sanPhamRepository = new SanPhamRepository();
                
    }
    @Override
    public List<QLSanPham> getAll() {
        ArrayList<QLSanPham> ds = new ArrayList<>();
        List<SanPham> list = this.sanPhamRepository.getAll();
        for (SanPham sp : list) {
            
            QLSanPham dssp = new QLSanPham(sp.getIdSanPham(),sp.getMaSP(),sp.getTenSP());
            
            ds.add(dssp);
        }
        return ds;
    }

    @Override
    public void insert(QLSanPham qlSP) {
        SanPham spd = new SanPham("", qlSP.getMaSP(), qlSP.getTenSP());
        this.sanPhamRepository.insert(spd);
    }

    @Override
    public void update(QLSanPham qlSP, String id) {
   SanPham spd = new SanPham("", qlSP.getMaSP(), qlSP.getTenSP());
        this.sanPhamRepository.update(spd, id);
    }

    @Override
    public void delete(String id) {
        this.sanPhamRepository.delete(id);
    }

   
    
}
