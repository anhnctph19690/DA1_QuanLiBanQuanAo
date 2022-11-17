/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicer.Impl;

import Models.ThuongHieu;
import Repository.Impl.ThuongHieuRepository;
import ViewModel.QLThuongHieu;
import java.util.ArrayList;
import java.util.List;
import Repository.IChatLieuRepository;
import Servicer.IThuongHieuServicer;





/**
 *
 * @author ADMIN
 */
public class ThuongHieuService implements IThuongHieuServicer{
    ThuongHieuRepository thuongHieurs = new ThuongHieuRepository();
    
    @Override
    public List<QLThuongHieu> getAll() {
          List<QLThuongHieu> listThuongHieu = new ArrayList<>();
        
        List<ThuongHieu> ListRepo = this.thuongHieurs.getAll();
        
        for (ThuongHieu th : ListRepo) {
          listThuongHieu.add(new QLThuongHieu(th.getMa()));
        }
        
        return listThuongHieu;
    }
}