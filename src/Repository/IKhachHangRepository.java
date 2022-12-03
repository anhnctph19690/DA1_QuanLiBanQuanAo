/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModels.HoaDon;
import DomainModels.KhachHang;
import Ultilities.DBConnection;
import ViewModel.QLHoaDon;
import ViewModel.QLKhachHang;
import java.sql.*;
import java.util.List;

/**
 *
 * @author tuane_nluzcuo
 */
public interface IKhachHangRepository {

    List<QLKhachHang> getAll();

    KhachHang add(KhachHang kh);

    boolean update(String id, int trangThai);

}
