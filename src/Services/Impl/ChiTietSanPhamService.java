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
    public List<QLChiTietSanPham> searchByName(List<QLChiTietSanPham> list, String name) {
        List<QLChiTietSanPham> listFound = new ArrayList<>();
        for (QLChiTietSanPham sp : list) {
            if (sp.getTenSanPham().toLowerCase().contains(name.toLowerCase())) {
                listFound.add(sp);
            }
        }
        return listFound;
    }

    @Override
    public List<QLChiTietSanPham> searchByMa(List<QLChiTietSanPham> list, String ma) {
        List<QLChiTietSanPham> listFound = new ArrayList<>();
        for (QLChiTietSanPham sp : list) {
            if (sp.getMaSanPham().toLowerCase().contains(ma.toLowerCase())) {
                listFound.add(sp);
            }
        }
        return listFound;
    }

    @Override
    public List<QLChiTietSanPham> searchSanPhamFromInput(List<QLChiTietSanPham> _listChiTietSanPham, String searchData) {
        List<QLChiTietSanPham> listNull = new ArrayList<>();
        List<QLChiTietSanPham> listSanPhamByName = this.searchByName(_listChiTietSanPham, searchData);
        List<QLChiTietSanPham> listSanPhamByMa = this.searchByMa(_listChiTietSanPham, searchData);
        if (!listSanPhamByName.isEmpty()) {
            return listSanPhamByName;
        } else if (!listSanPhamByMa.isEmpty()) {
            return listSanPhamByMa;
        }
        return listNull;
    }

    @Override
    public QLChiTietSanPham getProductByMa(List<QLChiTietSanPham> list, String ma) {
        for (QLChiTietSanPham sp : list) {
            if (sp.getMaSanPham().equals(ma)) {
                return sp;
            }
        }
        return null;
    }

    @Override
    public void updateSoLuongProductKhiRemove(List<QLChiTietSanPham> list, String maSP, int soLuong) {
        for (QLChiTietSanPham ctsp : list) {
            if (ctsp.getMaSanPham().equals(maSP)) {
                ctsp.setSoLuongTonKho(ctsp.getSoLuongTonKho() + soLuong);
            }
        }
    }

    public Integer getSoLuongProduct(List<QLChiTietSanPham> list, String ma) {
        for (QLChiTietSanPham ctsp : list) {
            if (ctsp.getMaSanPham().equals(ma)) {
                return ctsp.getSoLuongTonKho();
            }
        }
        return null;
    }
}
