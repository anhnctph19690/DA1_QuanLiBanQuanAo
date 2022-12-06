/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.KhachHang;
import Repository.IKhachHangRepository;
import Repository.Impl.KhachHangRepository;
import Services.IKhachHangService;
import ViewModel.QLKhachHang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class KhachHangService implements IKhachHangService {

    private IKhachHangRepository khachHangRepo = new KhachHangRepository();

    @Override
    public KhachHang add(KhachHang kh) {
        return khachHangRepo.add(kh);
    }

    @Override
    public List<QLKhachHang> getAll() {
        return khachHangRepo.getAll();
    }

    @Override
    public List<QLKhachHang> searchByName(List<QLKhachHang> list, String name) {
        List<QLKhachHang> listFound = new ArrayList<>();
        for (QLKhachHang kh : list) {
            if (kh.getTen().toLowerCase().contains(name.toLowerCase())) {
                listFound.add(kh);
            }
        }
        return listFound;
    }

    @Override
    public List<QLKhachHang> searchByPhone(List<QLKhachHang> list, String sdt) {
        List<QLKhachHang> listFound = new ArrayList<>();
        for (QLKhachHang kh : list) {
            if (kh.getSdt().toLowerCase().contains(sdt.toLowerCase())) {
                listFound.add(kh);
            }
        }
        return listFound;
    }

    @Override
    public List<QLKhachHang> searchKhachHangFromInput(List<QLKhachHang> list, String searchData) {
        List<QLKhachHang> listSanPhamByName = this.searchByName(list, searchData);
        List<QLKhachHang> listSanPhamByPhone = this.searchByPhone(list, searchData);
        if (!listSanPhamByName.isEmpty()) {
            return listSanPhamByName;
        } else if (!listSanPhamByPhone.isEmpty()) {
            return listSanPhamByPhone;
        }
        return null;
    }

    @Override
    public QLKhachHang getCustomerByPhone(String dataCustomer, List<QLKhachHang> list) {
        for (QLKhachHang kh : list) {
            if (kh.getSdt().equalsIgnoreCase(dataCustomer)) {
                return kh;
            }
        }
        return null;
    }

    @Override
    public QLKhachHang checkPhoneCustomer(List<QLKhachHang> list, String phone) {
        for (QLKhachHang kh : list) {
            if (kh.getSdt().equals(phone)) {
                return kh;
            }
        }
        return null;
    }
}
