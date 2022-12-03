/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModels.SanPham;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public interface ISanPhamRepository {

    SanPham add(String name);

    boolean update(String name, String id);

    boolean delete(String id);

    SanPham getOne(String id);

}
