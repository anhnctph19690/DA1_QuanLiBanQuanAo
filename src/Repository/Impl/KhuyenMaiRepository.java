/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModels.GiamGia;
import DomainModels.KhuyenMaiChiTiet;
import Services.Impl.KhuyenMaiService;
import Ultilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuane_nluzcuo
 */
public class KhuyenMaiRepository {

    Connection conn = DBConnection.getConnection();

    public void khuyenMaiInsert(GiamGia gg) {
        String query = "{call insertKhuyenMai(?,?,?,?,?,?,?)}";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, gg.getTenGiamGia());
            ps.setString(2, gg.getNgayBatDau());
            ps.setString(3, gg.getNgayKetThuc());
            ps.setDouble(4, gg.getMucGiamGia());
            ps.setString(5, gg.getDieuKienGiamGia());
            ps.setInt(6, gg.getTrangThai());
            ps.setString(7, gg.getLoaiGiamGia());
            ps.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    
    public ArrayList<GiamGia> getAlls(){
        ArrayList<GiamGia> khuyenMaiList = new ArrayList<>();
        String query = "SELECT * FROM dbo.GiamGia";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                khuyenMaiList.add(new GiamGia(rs.getString("IdGiamGia"), rs.getString("MaGiamGia"), rs.getString("TenGiamGia"), rs.getString("NgayBatDau"), rs.getString("NgayKetThuc"), rs.getString("DieuKienGiamGia"), rs.getDouble("MucGiamGiaPhanTram"), rs.getInt("TrangThai"), rs.getString("LoaiGiamGia")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return khuyenMaiList;
    }
    
    
    
    public GiamGia getOneByMa(String maKM){
        String query = "SELECT * FROM dbo.GiamGia WHERE MaGiamGia = ? ";
        GiamGia giamGia = new GiamGia();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, maKM);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {     
                giamGia.setIdGiamGia(rs.getString("IdGiamGia"));
                giamGia.setMaGiamGia(rs.getString("MaGiamGia"));
                giamGia.setTenGiamGia(rs.getString("TenGiamGia"));
                giamGia.setNgayBatDau(rs.getString("NgayBatDau"));
                giamGia.setNgayKetThuc(rs.getString("NgayKetThuc"));
                giamGia.setMucGiamGia(rs.getDouble("MucGiamGiaPhanTram"));
                giamGia.setDieuKienGiamGia(rs.getString("DieuKienGiamGia"));
                giamGia.setTrangThai(rs.getInt("TrangThai"));
                giamGia.setLoaiGiamGia(rs.getString("LoaiGiamGia"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return giamGia;
    }
    
    public void updateStatus(int status, String IdGiamGia){
        String query = "UPDATE dbo.GiamGia SET TrangThai = ? WHERE IdGiamGia = ?";
        try {
            PreparedStatement ps =conn.prepareStatement(query);
            ps.setInt(1, status);
            ps.setString(2, IdGiamGia);
           
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void delete(String IdKM) {
        String query = "DELETE FROM dbo.GiamGia WHERE IdGiamGia = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, IdKM);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    
    public void updateKM(GiamGia gg){
        
       String query = "UPDATE dbo.GiamGia SET TenGiamGia = ?, NgayBatDau = ?, NgayKetThuc = ?, MucGiamGiaPhanTram = ?WHERE IdGiamGia = ?	";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, gg.getTenGiamGia());
            ps.setString(2, gg.getNgayBatDau());
            ps.setString(3, gg.getNgayKetThuc());
            ps.setDouble(4, gg.getMucGiamGia());
            ps.setString(5, gg.getIdGiamGia());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        KhuyenMaiRepository khuyenMaiRepository = new KhuyenMaiRepository();
//        khuyenMaiRepository.updateStatus(0, "GG00006");
//    }
}
