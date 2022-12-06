/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModels.LoaiSanPham;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public interface ILoaiSanPhamRepository {

    public List<LoaiSanPham> getAll();

    public String getIDByLoaiSP(String tenLoaiSP);

    LoaiSanPham addCbb(String name);
}
