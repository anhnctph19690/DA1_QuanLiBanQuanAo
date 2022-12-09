/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.ChiTietSanPham;
import ViewModel.QLChiTietSanPham;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface IChiTietSanPhamService {

    List<QLChiTietSanPham> getAll();

    String add(ChiTietSanPham ctsp);

    boolean delete(String id);

    boolean update(ChiTietSanPham ctsp, String id);

    int checkSoLuong(String id);

    String getIdSP(String id);

    void uppdateSoLuong(Map<QLChiTietSanPham, Integer> ctsp);

    public int demSoLuongSPCH();

    public int demSoLuongSPHH();

    public int TongSP();
    
    List<QLChiTietSanPham> searchByName(String name);
    
    List<QLChiTietSanPham> searchByMa(String ma);
}
