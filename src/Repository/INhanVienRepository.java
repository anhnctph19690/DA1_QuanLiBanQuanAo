package Repository;

import Models.NhanVien;
import ViewModel.QLNhanVien;
import java.util.List;

public interface INhanVienRepository {

    public List<QLNhanVien> getAll();
    public String getIDChucVu (String tenCV);

    public void insert(NhanVien nv);

    public void update(NhanVien nv , String IdNV);

    public void delete(String maNv);
}
