/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModels.HoaDon;
import DomainModels.KhachHang;
import Repository.IHoaDonRepository;
import Repository.IKhachHangRepository;
import Ultilities.DBConnection;
import ViewModel.QLHoaDon;
import ViewModel.QLKhachHang;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tuane_nluzcuo
 */
public class KhachHangRepository implements IKhachHangRepository {

    @Override
    public List<QLKhachHang> getAll() {

        String query = "select IdKH, MaKH, Ten, NgaySinh, Sdt, DiaChi, TrangThai\n"
                + "	from KhachHang";
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<QLKhachHang> list = new ArrayList<>();
            while (rs.next()) {
                QLKhachHang kh = new QLKhachHang();
                kh.setIdKhachHang(rs.getString(1));
                kh.setMaKhachHang(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setNgaySinh(rs.getDate(4));
                kh.setSdt(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setTrangThai(rs.getInt(7));
                list.add(kh);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public KhachHang add(KhachHang kh) {
        String query = "{call procKH(?, ?)}";
        try ( Connection conn = DBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, kh.getHoTen());
            ps.setObject(2, kh.getSdt());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                kh.setMaKhachHang(rs.getString(1));
                return kh;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        new KhachHangRepository().getAll().forEach(s -> System.out.println(s.toString()));
    }

    @Override
    public boolean update(String id, int trangThai) {
        return true;
    }

}
