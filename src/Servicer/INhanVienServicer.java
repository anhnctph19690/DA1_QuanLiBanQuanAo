package Servicer;

import ViewModel.QLNhanVien;
import java.util.List;

public interface INhanVienServicer {

    public List<QLNhanVien> getList();

    public void insert(QLNhanVien qlNV);

    public void update(QLNhanVien qlNV);

    public void delete(QLNhanVien qlNV);

}
