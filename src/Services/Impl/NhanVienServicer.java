package Services.Impl;

import Models.NhanVien;
import Repository.INhanVienRepository;
import Repository.Impl.NhanVienRepository;
import Services.INhanVienServicer;
import ViewModel.QLNhanVien;
import java.util.ArrayList;
import java.util.List;

public class NhanVienServicer implements INhanVienServicer{

    INhanVienRepository nhanVienRepo;

    public NhanVienServicer() {

        nhanVienRepo = new NhanVienRepository();
    }

    @Override
    public List<QLNhanVien> getList() {

        List<QLNhanVien> listNV = this.nhanVienRepo.getAll();

        return listNV;

    }

    @Override
    public void insert(NhanVien NV) {

        this.nhanVienRepo.insert(NV);
    }

    @Override
    public void delete(String IdNV) {
        this.nhanVienRepo.delete(IdNV);
    }

   
    @Override
    public void update(NhanVien nV, String IdNV) {
        this.nhanVienRepo.update(nV, IdNV);
    }

}
