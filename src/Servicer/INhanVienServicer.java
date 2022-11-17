package Servicer;

import Models.NhanVien;
import ViewModel.QLNhanVien;
import java.util.List;

public interface INhanVienServicer {

    public List<QLNhanVien> getList();

    public void insert(NhanVien NV);

    public void update(NhanVien nv, String IdNV);

    public void delete(String IdNV);

}
