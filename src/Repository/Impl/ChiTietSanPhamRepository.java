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

}
