/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Models.ChiTietSanPham;
import Repository.IChiTietSanPhamRepository;
import Ultilities.DBConnection;
import ViewModel.QLChiTietSanPham;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ChiTietSanPhamRepository implements IChiTietSanPhamRepository {

    Connection conn = DBConnection.getConnection();
    final String SELECT_ALL = "select sp.MaSP, sp.Ten, nsx.Ten, ms.TenMauSac, lsp.TenLoaiSP, cl.TenChatLieu, th.TenThuongHieu, s.SoSize, ctsp.SoLuong, ctsp.GiaNhap, ctsp.GiaBan, ctsp.MoTa, ctsp.TrangThai\n"
            + "from SanPham sp, ChiTietSP ctsp, NSX nsx, MauSac ms, LoaiSanPham lsp, ChatLieu cl, ThuongHieu th, Size s\n"
            + "where sp.IdSP = ctsp.IdSP\n"
            + "and ctsp.IdNSX = nsx.IdNSX\n"
            + "and ctsp.IdMauSac = ms.IdMauSac\n"
            + "and ctsp.IdLoaiSP = lsp.IdLoaiSP\n"
            + "and ctsp.IdChatLieu = cl.IdChatLieu\n"
            + "and ctsp.IdThuongHieu = th.IdThuongHieu\n"
            + "and ctsp.IdSize = s.IdSize";

    final String INSERT_SQL = "INSERT INTO dbo.ChiTietSP(IdSP,IdNSX,IdMauSac,IdLoaiSP,IdChatLieu,IdThuongHieu,IdSize,SoLuong,GiaNhap,GiaBan,MoTa,TrangThai)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<QLChiTietSanPham> getAll() {

        List<QLChiTietSanPham> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                QLChiTietSanPham qlctsp = new QLChiTietSanPham();
                qlctsp.setMaSanPham(rs.getString(1));
                qlctsp.setTenSanPham(rs.getString(2));
                qlctsp.setTenNhaSanXuat(rs.getString(3));
                qlctsp.setTenMauSac(rs.getString(4));
                qlctsp.setTenLoai(rs.getString(5));
                qlctsp.setTenChatLieu(rs.getString(6));
                qlctsp.setTenThuongHieu(rs.getString(7));
                qlctsp.setSoSize(rs.getString(8));
                qlctsp.setSoLuongTonKho(rs.getInt(9));
                qlctsp.setGiaNhap(rs.getBigDecimal(10));
                qlctsp.setGiaBan(rs.getBigDecimal(11));
                qlctsp.setMoTa(rs.getString(12));
                qlctsp.setTrangThai(rs.getInt(13));
                list.add(qlctsp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;

    }

    @Override
    public boolean add(ChiTietSanPham chiTietSanPham) {
        int check = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
            ps.setObject(1, chiTietSanPham.getIdSanPham());
            ps.setObject(2, chiTietSanPham.getIdNhaSanXuat());
            ps.setObject(3, chiTietSanPham.getIdMauSac());
            ps.setObject(4, chiTietSanPham.getIdLoaiSanPham());
            ps.setObject(5, chiTietSanPham.getIdChatLieu());
            ps.setObject(6, chiTietSanPham.getIdThuongHieu());
            ps.setObject(7, chiTietSanPham.getIdSize());
            ps.setObject(8, chiTietSanPham.getSoLuong());
            ps.setObject(9, chiTietSanPham.getGiaNhap());
            ps.setObject(10, chiTietSanPham.getGiaBan());
            ps.setObject(11, chiTietSanPham.getMoTa());
            ps.setObject(12, chiTietSanPham.getTrangThai());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return check > 0;
    }

    public static void main(String[] args) {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setIdSanPham("B4081F38-285D-493D-A541-052B60B31B17");
        ctsp.setIdNhaSanXuat("7148A7A0-B6D5-4391-B869-6722C16F3A00");
        ctsp.setIdMauSac("4EB92ECE-823F-4CC6-B0CA-12CD149085A1");
        ctsp.setIdLoaiSanPham("9B050835-A580-4517-B9AC-38356925ABC5");
        ctsp.setIdChatLieu("F4B9662E-444C-4736-8FFD-0079BEB402FC");
        ctsp.setIdThuongHieu("E5A2228F-39F6-4E92-975F-1779A7029492");
        ctsp.setIdSize("7BA55748-FE9E-4CB2-B45E-0581068A6195");
        ctsp.setSoLuong(100);
        ctsp.setTrangThai(922);
        ctsp.setGiaNhap(null);
        ctsp.setGiaBan(null);
        ctsp.setMoTa(null);

        ChiTietSanPham ctsp1 = new ChiTietSanPham();
        ctsp1.setIdSanPham("4C87DB70-ACEE-4F12-A09E-2B044B308AFE");
        ctsp1.setTrangThai(933);
        ctsp1.setIdSanPham("B4081F38-285D-493D-A541-052B60B31B17");
        ctsp1.setIdNhaSanXuat("7148A7A0-B6D5-4391-B869-6722C16F3A00");
        ctsp1.setIdMauSac("4EB92ECE-823F-4CC6-B0CA-12CD149085A1");
        ctsp1.setIdLoaiSanPham("9B050835-A580-4517-B9AC-38356925ABC5");
        ctsp1.setIdChatLieu("F4B9662E-444C-4736-8FFD-0079BEB402FC");
        ctsp1.setIdThuongHieu("E5A2228F-39F6-4E92-975F-1779A7029492");
        ctsp1.setIdSize("7BA55748-FE9E-4CB2-B45E-0581068A6195");
        ctsp1.setSoLuong(100);
        ctsp1.setGiaNhap(null);
        ctsp1.setGiaBan(null);
        ctsp1.setMoTa(null);

        new ChiTietSanPhamRepository().add(ctsp);
        new ChiTietSanPhamRepository().add(ctsp1);
    }
}
