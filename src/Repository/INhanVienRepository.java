package Repository;

import Models.NhanVien;
import java.util.List;

public interface INhanVienRepository {

    public List<NhanVien> getAll();

    public void insert(NhanVien nv);

    public void update(NhanVien nv);

    public void delete(NhanVien nv);
}
