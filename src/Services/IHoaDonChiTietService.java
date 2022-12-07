/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import ViewModel.QLHoaDonChiTiet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tuane_nluzcuo
 */
public interface IHoaDonChiTietService {

    List<QLHoaDonChiTiet> getListInvoices(String id);

    String addListInvoice(List<QLHoaDonChiTiet> list);

    Double totalMoneyOfInvoice(List<QLHoaDonChiTiet> list);

    QLHoaDonChiTiet getHoaDonChiTietByMa(String ma, List<QLHoaDonChiTiet> list);

    void update(Map<QLHoaDonChiTiet, String> hdct);

    public int doanhthu();
    
      List<QLHoaDonChiTiet> getAllHDCT();
     

}
