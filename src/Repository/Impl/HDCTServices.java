/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Services.IHDCTServices;
import ViewModel.QLHoaDonCT;
import java.util.ArrayList;

/**
 *
 * @author tuane_nluzcuo
 */
public class HDCTServices implements IHDCTServices{

    HoaDonCTRepository hoaDonCTRepository = new HoaDonCTRepository();
    @Override
    public ArrayList<QLHoaDonCT> getlistHDCT(String maHD) {
        return this.hoaDonCTRepository.getlistHDCT(maHD);
    }
    
}
