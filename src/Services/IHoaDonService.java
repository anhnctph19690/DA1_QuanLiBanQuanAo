/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Models.HoaDon;
import ViewModel.QLHoaDon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tuane_nluzcuo
 */
public interface IHoaDonService {

    List<QLHoaDon> getAll();

    String add(HoaDon hd);

    void uppdateTrangThai(String id, int trangThai);

    void updateHoaDonGiaoHang(HoaDon hd, String id);

}
