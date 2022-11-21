/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import Models.HoaDon;
import Ultilities.DBConnection;
import ViewModel.QLHoaDon;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author tuane_nluzcuo
 */
public interface IHoaDonRepository {
    Connection conn = DBConnection.getConnection();
    
    public ArrayList<QLHoaDon> getHoaDonAlls();

    boolean add(HoaDon hoaDon);
}
