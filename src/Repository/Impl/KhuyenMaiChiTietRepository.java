package Repository.Impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author tuane_nluzcuo
 */
import DomainModels.KhuyenMaiChiTiet;
import Ultilities.DBConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhuyenMaiChiTietRepository {

    private Connection conn = DBConnection.getConnection();

    public ArrayList<KhuyenMaiChiTiet> getAlls() {
        String query = "SELECT * FROM dbo.SPGiamGia";
        ArrayList<KhuyenMaiChiTiet> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                list.add(new KhuyenMaiChiTiet(rs.getString("Id"), rs.getString("IdSP"), rs.getString("IdGiamGia"), rs.getBigDecimal("DonGia"), rs.getBigDecimal("SoTienConLai"), rs.getInt("TrangThai")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public ArrayList<KhuyenMaiChiTiet> getListByIdKhuyenMai(String IdGiamGia) {
        String query = "SELECT * FROM dbo.SPGiamGia WHERE IdGiamGia = ? ";
        ArrayList<KhuyenMaiChiTiet> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, IdGiamGia);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                list.add(new KhuyenMaiChiTiet(rs.getString("Id"), rs.getString("IdSP"), rs.getString("IdGiamGia"), rs.getBigDecimal("DonGia"), rs.getBigDecimal("SoTienConLai"), rs.getInt("TrangThai")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public void insert(KhuyenMaiChiTiet kmct) {
        String query = "INSERT INTO dbo.SPGiamGia ( Id,IdGiamGia,IdSP,DonGia,SoTienConLai,TrangThai ) VALUES (   DEFAULT, ?,    ?,    ?,    ?,    ?  )";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, kmct.getIdGiamGia());
            ps.setString(2, kmct.getIdSanPham());
            ps.setBigDecimal(3, kmct.getDonGia());
            ps.setBigDecimal(4, kmct.getSoTienConLai());
            ps.setInt(5, kmct.getTrangThai());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateStatusSPGiamGia(int status, String IdGiamGia) {
        String query = "UPDATE dbo.SPGiamGia SET  TrangThai = ? WHERE IdGiamGia = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, status);
            ps.setString(2, IdGiamGia);

            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(String IdKM) {
        String query = "DELETE FROM dbo.SPGiamGia WHERE IdGiamGia = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, IdKM);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    public void updateKMCT(String IdKM, double PhanTramGG){
        
       String query = "UPDATE dbo.SPGiamGia SET SoTienConLai = DonGia - (DonGia * ? /100) WHERE IdGiamGia = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, PhanTramGG);
            ps.setString(2, IdKM);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        KhuyenMaiChiTietRepository chiTietRepository = new KhuyenMaiChiTietRepository();
//        chiTietRepository.insert(new KhuyenMaiChiTiet("", "AE436EEA-C99C-4832-89F3-4240363473D2", "7745C39D-B287-4C13-9C96-CA674E4BAE7C", BigDecimal.valueOf(100), BigDecimal.valueOf(50), 1));
//        chiTietRepository.insert(new KhuyenMaiChiTiet("", "AE436EEA-C99C-4832-89F3-4240363473D2", "7745C39D-B287-4C13-9C96-CA674E4BAE7C", BigDecimal.valueOf(100), BigDecimal.valueOf(50), 1));
//    }
}
