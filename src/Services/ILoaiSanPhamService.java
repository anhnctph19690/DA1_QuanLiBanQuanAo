/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.LoaiSanPham;
import ViewModel.QLLoaiSanPham;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public interface ILoaiSanPhamService {
    public List<QLLoaiSanPham> getAll();
   
}
