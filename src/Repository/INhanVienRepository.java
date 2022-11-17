package Repository;

import Models.NhanVien;
import ViewModel.QLNhanVien;
import java.util.List;

public interface INhanVienRepository {

    public List<QLNhanVien> getAll();

    public void insert(NhanVien nv);

    public void update(NhanVien nv);

    public void delete(NhanVien nv);
}
