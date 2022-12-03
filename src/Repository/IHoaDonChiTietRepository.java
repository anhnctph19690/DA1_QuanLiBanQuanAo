/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModels.HoaDonChiTiet;
import ViewModel.QLHoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuane_nluzcuo
 */
public interface IHoaDonChiTietRepository {

    List<QLHoaDonChiTiet> getAllInvoices(String id);

    boolean addListInvoice(List<QLHoaDonChiTiet> list);
    
    public int doanhthu();
    
    List<QLHoaDonChiTiet> getAllHDCT();
    
    List<QLHoaDonChiTiet> getFilters(String ngayBatDau,String ngayKetThuc);

}
