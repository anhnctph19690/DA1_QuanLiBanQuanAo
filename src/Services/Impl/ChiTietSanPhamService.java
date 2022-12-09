/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.ChiTietSanPham;
import Repository.IChiTietSanPhamRepository;
import Repository.Impl.ChiTietSanPhamRepository;
import Services.IChiTietSanPhamService;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLThongKe;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ChiTietSanPhamService implements IChiTietSanPhamService {

    private IChiTietSanPhamRepository _iChiTietSanPhamRepository = new ChiTietSanPhamRepository();
    ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
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

    @Override
    public boolean update(ChiTietSanPham ctsp, String id) {
        if (_iChiTietSanPhamRepository.update(ctsp, id)) {
            return true;
        }
        return false;
    }

    @Override
    public void uppdateSoLuong(Map<QLChiTietSanPham, Integer> ctsp) {
        ctsp.forEach(
                (k, v) -> {
                    _iChiTietSanPhamRepository.uppdateSoLuong(k.getIdCTSP(), v);
                }
        );
    }

    @Override
    public int checkSoLuong(String id) {
        return _iChiTietSanPhamRepository.checkSoLuong(id);
    }

    @Override
    public String getIdSP(String id) {
        return _iChiTietSanPhamRepository.getIdSP(id);
    }

    @Override
    public int demSoLuongSPCH() {
        return _iChiTietSanPhamRepository.demSoLuongSPCH();
    }

    @Override
    public int demSoLuongSPHH() {
        return _iChiTietSanPhamRepository.demSoLuongSPHH();
    }

    @Override
    public int TongSP() {
        return _iChiTietSanPhamRepository.TongSP();
    }

    public List<QLThongKe> thongKeALL(String thongKeTheo, String SapXepTheo) {
        return _iChiTietSanPhamRepository.thongKeALL(thongKeTheo, SapXepTheo);
    }

    @Override
    public List<QLChiTietSanPham> searchByName(String name) {
        return _iChiTietSanPhamRepository.searchByName(name);
    }

    @Override
    public List<QLChiTietSanPham> searchByMa(String ma) {
        return _iChiTietSanPhamRepository.searchByMa(ma);
    }
    
    public ArrayList<QLChiTietSanPham> getListByLoaiSP(String loaiSP){
        return this.chiTietSanPhamRepository.getListByLoaiSP(loaiSP);
    }
    
    public List<QLChiTietSanPham> getListSPByKhoangGia(double min, double max){
        return this.chiTietSanPhamRepository.getListSPByKhoangGia(min, max);
    }
    
     public String getMabyId(String maCTSP){
         return this.chiTietSanPhamRepository.getMabyId(maCTSP);
     }
     
     
}
