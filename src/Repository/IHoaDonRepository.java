/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModels.HoaDon;
import Ultilities.DBConnection;
import ViewModel.QLHoaDon;
import java.sql.*;
import java.util.List;

/**
 *
 * @author tuane_nluzcuo
 */
public interface IHoaDonRepository {

    List<QLHoaDon> getAllHoaDonCho(int trangThai);

    List<QLHoaDon> getAllHoaDonDatHang();

    boolean add(HoaDon hoaDon);

    boolean updateTrangThai(String id, int trangThai);

    boolean updateHoaDonGiaoHang(HoaDon hd, String id);

    boolean updateNgayNhan(HoaDon hd, String id);

    boolean updateHoaDon(HoaDon hd, String id);

    List<QLHoaDon> getAllHD();

    List<QLHoaDon> getNgayTao(String ngayBatDau,String ngayKetThuc);
    
    List<QLHoaDon> getByName(String name);
 
}
