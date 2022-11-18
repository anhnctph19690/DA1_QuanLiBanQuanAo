/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import Models.SanPham;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public interface ISanPhamRepository {

    boolean add(SanPham sp);

    boolean updateTenSanPham(String name, String id);

    boolean delete(String id);

    SanPham getOne(String id);
}
