/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.KhachHang;
import ViewModel.QLHoaDon;
import ViewModel.QLKhachHang;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface IKhachHangService {

    List<QLKhachHang> getAll();

    KhachHang add(KhachHang kh);

    List<QLKhachHang> searchByName(List<QLKhachHang> list, String name);

    List<QLKhachHang> searchByPhone(List<QLKhachHang> list, String sdt);

    List<QLKhachHang> searchKhachHangFromInput(List<QLKhachHang> list, String searchData);

    QLKhachHang getCustomerByPhone(String dataCustomer, List<QLKhachHang> list);

    QLKhachHang checkPhoneCustomer(List<QLKhachHang> list, String phone);

}
