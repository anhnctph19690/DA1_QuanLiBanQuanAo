/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Models.HoaDonChiTiet;
import Repository.IHoaDonChiTietRepository;
import Ultilities.DBConnection;
import ViewModel.QLHoaDonChiTiet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuane_nluzcuo
 */
public class HoaDonChiTietRepository implements IHoaDonChiTietRepository {

    String INSERT_SQL = "insert into HoaDonChiTiet (IdHoaDon, IdCTSP, Soluong, DonGia) values (?, ?, ?, ?)";

    @Override
    public List<QLHoaDonChiTiet> getAllInvoices(String id) {
        ArrayList<QLHoaDonChiTiet> HDCTList = new ArrayList<>();
        String query = "	SELECT HoaDonChiTiet.IdHoaDon, HoaDonChiTiet.IdCTSP, dbo.SanPham.MaSP, dbo.SanPham.Ten, dbo.HoaDonChiTiet.SoLuong, dbo.ChiTietSP.GiaBan FROM dbo.HoaDonChiTiet INNER JOIN dbo.ChiTietSP ON dbo.HoaDonChiTiet.IdCTSP = dbo.ChiTietSP.IdCTSP INNER JOIN dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.IdSP INNER JOIN dbo.HoaDon ON dbo.HoaDonChiTiet.IdHoaDon = dbo.HoaDon.IdHoaDon where HoaDonChiTiet.IdHoaDon = ?";
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HDCTList.add(new QLHoaDonChiTiet(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBigDecimal(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return HDCTList;
    }

    public String getIDByMaHD(String maHD) {
        String query = "SELECT IdHoaDon FROM dbo.HoaDon WHERE MaHD = ?";
        String id = null;
        try ( Connection conn = DBConnection.getConnection()) {
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

    @Override
    public boolean addListInvoice(List<QLHoaDonChiTiet> list) {
        int[] arr = {};
        try ( Connection conn = DBConnection.getConnection();) {
            PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
            for (QLHoaDonChiTiet hoaDonChiTiet : list) {
                ps.setObject(1, hoaDonChiTiet.getIdHoaDon());
                ps.setObject(2, hoaDonChiTiet.getIdCTSP());
                ps.setObject(3, hoaDonChiTiet.getSoLuongMua());
                ps.setObject(4, hoaDonChiTiet.getDonGia());

                ps.addBatch();
            }

            arr = ps.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr.length > 0;
    }

    public static void main(String[] args) {
        new HoaDonChiTietRepository().getAllInvoices("7096376D-C3F7-48F6-86DD-9EBB2A70DFA6").forEach(s -> System.out.println(s.toString()));

    }
}
