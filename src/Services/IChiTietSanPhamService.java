/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.ChiTietSanPham;
import ViewModel.QLChiTietSanPham;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface IChiTietSanPhamService {

    List<QLChiTietSanPham> getAll();

    String add(ChiTietSanPham ctsp);

    boolean delete(String id);
}
