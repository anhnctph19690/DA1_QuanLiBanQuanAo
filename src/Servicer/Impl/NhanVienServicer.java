package Servicer.Impl;

import Models.NhanVien;
import Repository.INhanVienRepository;
import Repository.Impl.NhanVienRepository;
import Servicer.INhanVienServicer;
import ViewModel.QLNhanVien;
import java.util.ArrayList;
import java.util.List;

public class NhanVienServicer implements INhanVienServicer {

    INhanVienRepository NhanVienRepo;

    public NhanVienServicer() {

        NhanVienRepo = new NhanVienRepository();
    }

    public List<QLNhanVien> getList() {

        List<QLNhanVien> listNV = this.NhanVienRepo.getAll();

        return listNV;

    }

    public void insert(QLNhanVien qlNV) {
       

       // NhanVienRepo.insert(NV);
    }

    public void update(QLNhanVien qlNV) {
//        NhanVien NV = new NhanVien(qlNV.getIdNhanVien(), qlNV.getMaNV(),
//                qlNV.getTenNV(), qlNV.getDiaChi(), qlNV.getsDT(),
//                qlNV.getGioiTinh(), qlNV.getNgaySinh(),
//                qlNV.getMatKhau(), qlNV.getTrangThai(), qlNV.getIdCV());
//
//        NhanVienRepo.update(NV);
    }

    public void delete(QLNhanVien qlNV) {
//        NhanVien NV = new NhanVien(qlNV.getIdNhanVien(), qlNV.getMaNV(),
//                qlNV.getTenNV(), qlNV.getDiaChi(), qlNV.getsDT(),
//                qlNV.getGioiTinh(), qlNV.getNgaySinh(),
//                qlNV.getMatKhau(), qlNV.getTrangThai(), qlNV.getIdCV());
//
//        NhanVienRepo.delete(NV);
    }

}
