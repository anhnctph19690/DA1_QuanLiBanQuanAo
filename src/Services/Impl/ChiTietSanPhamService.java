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
import Ultilities.DBConnection;
import ViewModel.QLThongKe;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ChiTietSanPhamService implements IChiTietSanPhamService {

    private IChiTietSanPhamRepository _iChiTietSanPhamRepository = new ChiTietSanPhamRepository();
    
    private ChiTietSanPhamRepository ChiTietSanPhamRepository = new ChiTietSanPhamRepository();
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

    public boolean uppdateSoLuong(String IdCTSP, int soLuong) {
        return _iChiTietSanPhamRepository.uppdateSoLuong(IdCTSP, soLuong);
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
    
    
    public List<QLThongKe> thongKeALL(String thongKeTheo,String SapXepTheo){
      return ChiTietSanPhamRepository.thongKeAll( thongKeTheo, SapXepTheo);
        
    }
    public static void main(String[] args) {
        ChiTietSanPhamService ctsp = new ChiTietSanPhamService();
        
      
       
    }
    

    
   
    
    

    
}
