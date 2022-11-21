/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import Models.HoaDonChiTiet;
import ViewModel.QLHoaDonCT;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuane_nluzcuo
 */
public interface IHoaDonChiTietRepository {

    public List<QLHoaDonCT> getlistHDCT(String idmaHD);

    boolean addListSanPham(List<QLHoaDonCT> list);

}
