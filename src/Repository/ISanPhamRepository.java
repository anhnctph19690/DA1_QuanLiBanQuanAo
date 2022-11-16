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
    public List<SanPham> getAll();
    
     public void insert(SanPham sp);

    public void update(SanPham sp, String id);

    public void delete(String id);
}
