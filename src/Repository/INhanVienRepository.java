package Repository;

import DomainModels.NhanVien;
import ViewModel.QLNhanVien;
import java.util.List;

public interface INhanVienRepository {

    public List<QLNhanVien> getAll();
    public String getIDChucVu (String tenCV);

    public void insert(NhanVien nv);

    public void update(NhanVien nv , String maNV);

    public boolean deleteBoolean(String maNv);
}
