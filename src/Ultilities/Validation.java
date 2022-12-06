/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultilities;

import Services.IKhachHangService;
import Services.Impl.KhachHangService;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLKhachHang;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Validation {

    private IKhachHangService _iKhachHangService = new KhachHangService();

    public String validateProduct(String soLuong, QLChiTietSanPham sp) {
        if (soLuong == null || soLuong.isEmpty()) {
            return "Đã hủy";
        }
        if (!soLuong.matches("\\d++")) {
            return "Số lượng phải là số";
        }
        if (sp.getSoLuongTonKho() <= 0) {
            return "Sản phẩm này đã hết hàng";
        }
        if (Integer.valueOf(soLuong) <= 0) {
            return "Số lượng mua phải lớn hơn 0";
        }
        if (Integer.valueOf(soLuong) > sp.getSoLuongTonKho()) {
            return "Số lượng sản phẩm chỉ còn lại: " + sp.getSoLuongTonKho();
        }
        return null;
    }

    public String validateCustomer(String name, String phone) {
        if (!name.isEmpty() && phone.isEmpty()) {
            return "Vui lòng nhập số điện thoại";
        } else if (name.isEmpty() && !phone.isEmpty()) {
            return "Vui lòng nhập tên khách hàng";
        } else if (!name.isEmpty() && !phone.isEmpty()) {
            return "NewCustomer";
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Validation().validateProduct("2", null));
    }

}
