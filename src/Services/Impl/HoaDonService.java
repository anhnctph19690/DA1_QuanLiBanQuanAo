/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import Models.HoaDon;
import Repository.IHoaDonRepository;
import Repository.Impl.HoaDonRepository;
import Repository.Impl.NhanVienRepository;
import Services.IHoaDonServices;
import ViewModel.QLHoaDon;
import ViewModel.QLNhanVien;
import java.util.*;

/**
 *
 * @author tuane_nluzcuo
 */
public class HoaDonService implements IHoaDonServices {

    HoaDonRepository hoaDonRepository = new HoaDonRepository();
    NhanVienRepository nhanVienRepository = new NhanVienRepository();

    public String getTenNhanVien(String IdNhanVien) {
        List<QLNhanVien> NVList = this.nhanVienRepository.getAll();
        String Ten = null;
        for (QLNhanVien o : NVList) {
            if (IdNhanVien.equals(o.getIdNhanVien())) {
                Ten = o.getTenNV();
            }
        }
        return Ten;
    }

    @Override
    public ArrayList<QLHoaDon> getHoaDonAlls() {
        return this.hoaDonRepository.getHoaDonAlls();
    }

    @Override
    public String add(HoaDon hd) {
        if (hoaDonRepository.add(hd)) {
            return "Tạo thành công";
        }
        return "Tạo thất bại";
    }
    
    public boolean uppdateTrangThai(String IDHoaDon, int trangThai){
        return this.uppdateTrangThai(IDHoaDon, trangThai);
    }

}
