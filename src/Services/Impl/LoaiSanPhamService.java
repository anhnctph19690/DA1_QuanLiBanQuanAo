/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.LoaiSanPham;
import Repository.Impl.LoaiSanPhamRepository;
import Services.ILoaiSanPhamService;
import ViewModel.QLLoaiSanPham;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public class LoaiSanPhamService implements ILoaiSanPhamService{
    public LoaiSanPhamRepository loaiSanPhamRepository = new LoaiSanPhamRepository();
    @Override
    public List<QLLoaiSanPham> getAll() {
           List<QLLoaiSanPham> ds = new ArrayList<>();
        
       List<LoaiSanPham> list = this.loaiSanPhamRepository.getAll();
       
        for (LoaiSanPham lsp : list) {
            QLLoaiSanPham dsLSP = new QLLoaiSanPham(lsp.getTenLSP());
            
            ds.add(dsLSP);
        }
        return ds;
    }

  
   }
    

