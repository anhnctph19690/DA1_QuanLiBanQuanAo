/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import Models.HoaDon;
import Repository.IHoaDonRepository;
import Repository.Impl.HoaDonRepository;
import Repository.Impl.NhanVienRepository;
import Services.IHoaDonService;
import ViewModel.QLHoaDon;
import ViewModel.QLNhanVien;
import java.util.*;

/**
 *
 * @author tuane_nluzcuo
 */
public class HoaDonService implements IHoaDonService {

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    @Override
    public String add(HoaDon hd) {
        if (hoaDonRepository.add(hd)) {
            return "Tạo thành công";
        }
        return "Tạo thất bại";
    }

    @Override
    public List<QLHoaDon> getAll() {
        return hoaDonRepository.getAll();
    }

    @Override
    public void uppdateTrangThai(String id, int trangThai) {
        hoaDonRepository.updateTrangThai(id, trangThai);
    }

}
