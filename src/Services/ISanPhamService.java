/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Models.SanPham;
import ViewModel.QLSanPham;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public interface ISanPhamService {

    SanPham add(String name);

    void updateTenSanPham(String name, String id);

    SanPham getOne(String id);

    String delete(String id);
}
