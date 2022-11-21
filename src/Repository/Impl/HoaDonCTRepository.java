/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Repository.IHoaDonChiTietRepository;
import Ultilities.DBConnection;
import ViewModel.QLHoaDonCT;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tuane_nluzcuo
 */
public class HoaDonCTRepository implements IHoaDonChiTietRepository{

    Connection conn = DBConnection.getConnection();
    @Override
    public ArrayList<QLHoaDonCT> getlistHDCT(String idHD) {
        ArrayList<QLHoaDonCT> HDCTList = new ArrayList<>();
        String query = "SELECT dbo.HoaDon.MaHD, dbo.HoaDonChiTiet.IdHoaDonChiTiet, dbo.SanPham.MaSP, dbo.SanPham.Ten, dbo.HoaDonChiTiet.SoLuong, dbo.ChiTietSP.GiaBan FROM dbo.HoaDonChiTiet INNER JOIN dbo.ChiTietSP ON dbo.HoaDonChiTiet.IdCTSP = dbo.ChiTietSP.IdCTSP INNER JOIN dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.IdSP INNER JOIN dbo.HoaDon ON dbo.HoaDonChiTiet.IdHoaDon = dbo.HoaDon.IdHoaDon WHERE HoaDon.IdHoaDon = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, idHD);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                HDCTList.add(new QLHoaDonCT(rs.getString("MaSP"), rs.getString("Ten"), rs.getInt("SoLuong"), rs.getInt("GiaBan"), 0));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return  HDCTList;
    }

   public String getIDByMaHD (String maHD){
       String query = "SELECT IdHoaDon FROM dbo.HoaDon WHERE MaHD = ?";
       String id = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, maHD);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {  
                id = rs.getString("IdHoaDon");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return id;
   }
    
}
