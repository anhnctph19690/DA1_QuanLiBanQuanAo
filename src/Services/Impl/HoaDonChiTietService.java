/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import Repository.IHoaDonChiTietRepository;
import Repository.Impl.HoaDonChiTietRepository;
import Services.IHoaDonChiTietService;
import ViewModel.QLHoaDonChiTiet;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class HoaDonChiTietService implements IHoaDonChiTietService {

    private IHoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();

    @Override
    public List<QLHoaDonChiTiet> getListInvoices(String id) {
        return hoaDonChiTietRepository.getAllInvoices(id);
    }

    @Override
    public String addListInvoice(List<QLHoaDonChiTiet> list) {
        if (hoaDonChiTietRepository.addListInvoice(list)) {
            return "Thanh toán thành công";
        }
        return "Thanh toán thất bại";
    }

    @Override
    public Double totalMoneyOfInvoice(List<QLHoaDonChiTiet> list) {
        Double total = 0.0;
        for (QLHoaDonChiTiet hdct : list) {
            total = total + hdct.getTotal();
        }
        return total;
    }

    @Override
    public int doanhthu() {
        return hoaDonChiTietRepository.doanhthu();
    }

}
