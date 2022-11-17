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

    public void insert(NhanVien NV) {

        this.NhanVienRepo.insert(NV);
    }

    public void delete(String IdNV) {
        this.NhanVienRepo.delete(IdNV);
    }

    @Override
    public void update(NhanVien nV, String IdNV) {
        this.NhanVienRepo.update(nV, IdNV);
    }

}
