/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import ViewModel.QLHoaDonCT;
import java.util.ArrayList;

/**
 *
 * @author tuane_nluzcuo
 */
public interface IHoaDonChiTietRepository {
    public ArrayList<QLHoaDonCT> getlistHDCT(String idmaHD);
}
