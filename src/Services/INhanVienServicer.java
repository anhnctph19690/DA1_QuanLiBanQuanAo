package Services;

import DomainModels.NhanVien;
import ViewModel.QLNhanVien;
import java.util.List;

public interface INhanVienServicer {

    public List<QLNhanVien> getList();

    public void insert(NhanVien NV);

    public void update(NhanVien nv, String maNV);

    public boolean deleteBoolean(String IdNV);

}
