/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import Models.SanPham;
import Repository.Impl.SanPhamRepository;
import Services.ISanPhamService;
import ViewModel.QLSanPham;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public class SanPhamService implements ISanPhamService {

    public SanPhamRepository _iSanPhamRepository;

    public SanPhamService() {
        _iSanPhamRepository = new SanPhamRepository();
    }

    @Override
    public SanPham add(String name) {
        return _iSanPhamRepository.add(name);
    }

    @Override
    public void updateTenSanPham(String name, String id) {
        _iSanPhamRepository.update(name, id);
    }

    @Override
    public SanPham getOne(String id) {
        return _iSanPhamRepository.getOne(id);
    }

    @Override
    public String delete(String id) {
        if (_iSanPhamRepository.delete(id)) {
            return "Xóa thành công";
        }
        return "Xóa thất bại";
    }

}
