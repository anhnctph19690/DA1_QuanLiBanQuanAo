/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Models.ChiTietSanPham;
import Models.HoaDonChiTiet;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLThongKe;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface IChiTietSanPhamRepository {

    List<QLChiTietSanPham> getAll();

    boolean add(ChiTietSanPham chiTietSanPham);

    boolean update(ChiTietSanPham chiTietSanPham, String id);

    boolean delete(String id);

    String getIdSP(String id);

    boolean uppdateSoLuong(String IdCTSP, int soLuong);

    int checkSoLuong(String id);

    public int demSoLuongSPCH();

    public int demSoLuongSPHH();

    public int TongSP();

   // public List<QLThongKe> thongKeAll(String thongKeTheo,String SapXepTheo);

}
