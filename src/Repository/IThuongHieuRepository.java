/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DomainModels.ThuongHieu;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IThuongHieuRepository {

    public List<ThuongHieu> getAll();

    public String getIDByThuongHieu(String tenThuongHieu);

    ThuongHieu addCbb(String name);
}
