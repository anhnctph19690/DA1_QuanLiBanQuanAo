/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servicer;

import ViewModel.QLSanPham;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public interface ISanPhamService {
     public List<QLSanPham> getAll();

    public void insert(QLSanPham qlSP);

    public void update(QLSanPham qlSP,String id);

    public void delete(String id);
}
