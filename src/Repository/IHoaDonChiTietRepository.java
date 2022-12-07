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

    List<QLHoaDonChiTiet> getAllInvoiceDetails(String id);

    boolean addListInvoiceDetails(List<QLHoaDonChiTiet> list);

    boolean updateListInvoiceDetails(String idCTSP, String idHoaDon);

    public int doanhthu();
    
     List<QLHoaDonChiTiet> getAllHDCT();
    


}
