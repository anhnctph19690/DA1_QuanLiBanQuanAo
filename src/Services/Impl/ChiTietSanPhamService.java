/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import Models.ChiTietSanPham;
import Repository.IChiTietSanPhamRepository;
import Repository.Impl.ChiTietSanPhamRepository;
import Services.IChiTietSanPhamService;
import ViewModel.QLChiTietSanPham;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ChiTietSanPhamService implements IChiTietSanPhamService {

    private IChiTietSanPhamRepository _iChiTietSanPhamRepository = new ChiTietSanPhamRepository();
    private int index = 0;

    @Override
    public List<QLChiTietSanPham> getAll() {
        return _iChiTietSanPhamRepository.getAll();
    }

    @Override
    public String add(ChiTietSanPham ctsp) {
        if (_iChiTietSanPhamRepository.add(ctsp)) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public boolean delete(String id) {
        if (_iChiTietSanPhamRepository.delete(id)) {
            return true;
        }
        return false;
    }

}
