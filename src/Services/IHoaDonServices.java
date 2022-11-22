/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Models.HoaDon;
import ViewModel.QLHoaDon;
import java.util.ArrayList;


/**
 *
 * @author tuane_nluzcuo
 */
public interface IHoaDonServices {
    public ArrayList<QLHoaDon> getHoaDonAlls();

    String add(HoaDon hd);

    void uppdateTrangThai(String IDHoaDon, int trangThai);

}
