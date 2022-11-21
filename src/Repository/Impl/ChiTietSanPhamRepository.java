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

    final String SELECT_ALL = "select ROW_NUMBER() OVER (ORDER BY sp.MaSP), sp.MaSP, sp.Ten, nsx.Ten, ms.TenMauSac, lsp.TenLoaiSP, cl.TenChatLieu, th.TenThuongHieu, s.SoSize, ctsp.SoLuong, ctsp.GiaNhap, ctsp.GiaBan, ctsp.MoTa, ctsp.TrangThai\n"
            + "from SanPham sp, ChiTietSP ctsp, NSX nsx, MauSac ms, LoaiSanPham lsp, ChatLieu cl, ThuongHieu th, Size s \n"
            + "where sp.IdSP = ctsp.IdSP\n"
            + "and ctsp.IdNSX = nsx.IdNSX\n"
            + "and ctsp.IdMauSac = ms.IdMauSac\n"
            + "and ctsp.IdLoaiSP = lsp.IdLoaiSP\n"
            + "and ctsp.IdChatLieu = cl.IdChatLieu\n"
            + "and ctsp.IdThuongHieu = th.IdThuongHieu\n"
            + "and ctsp.IdSize = s.IdSize";

    final String INSERT_SQL = "INSERT INTO dbo.ChiTietSP(IdSP,IdNSX,IdMauSac,IdLoaiSP,IdChatLieu,IdThuongHieu,IdSize,SoLuong,GiaNhap,GiaBan,MoTa,TrangThai)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    final String DELETE_BY_ID = "delete from ChiTietSP where IdSP = ?";

    final String UPDATE_SQL = "	update ChiTietSP set IdNSX = ?, IdMauSac = ?, IdLoaiSP = ?, IdChatLieu = ?, IdThuongHieu = ?, IdSize = ?, SoLuong = ?, GiaNhap = ?, GiaBan = ?, MoTa = ?, TrangThai = ? where IdSP = ?";

    @Override
    public List<QLChiTietSanPham> getAll() {

        List<QLChiTietSanPham> list = new ArrayList<>();
        try ( Connection conn = DBConnection.getConnection();) {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                QLChiTietSanPham qlctsp = new QLChiTietSanPham();
                qlctsp.setSTT(rs.getInt(1));
                qlctsp.setMaSanPham(rs.getString(2));
                qlctsp.setTenSanPham(rs.getString(3));
                qlctsp.setTenNhaSanXuat(rs.getString(4));
                qlctsp.setTenMauSac(rs.getString(5));
                qlctsp.setTenLoai(rs.getString(6));
                qlctsp.setTenChatLieu(rs.getString(7));
                qlctsp.setTenThuongHieu(rs.getString(8));
                qlctsp.setSoSize(rs.getString(9));
                qlctsp.setSoLuongTonKho(rs.getInt(10));
                qlctsp.setGiaNhap(rs.getBigDecimal(11));
                qlctsp.setGiaBan(rs.getBigDecimal(12));
                qlctsp.setMoTa(rs.getString(13));
                qlctsp.setTrangThai(rs.getInt(14));
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
        try ( Connection conn = DBConnection.getConnection();) {
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

    @Override
    public boolean update(ChiTietSanPham chiTietSanPham, String id) {
        int check = 0;
        try ( Connection conn = DBConnection.getConnection();) {
            PreparedStatement ps = conn.prepareStatement(UPDATE_SQL);
            ps.setObject(1, chiTietSanPham.getIdNhaSanXuat());
            ps.setObject(2, chiTietSanPham.getIdMauSac());
            ps.setObject(3, chiTietSanPham.getIdLoaiSanPham());
            ps.setObject(4, chiTietSanPham.getIdChatLieu());
            ps.setObject(5, chiTietSanPham.getIdThuongHieu());
            ps.setObject(6, chiTietSanPham.getIdSize());
            ps.setObject(7, chiTietSanPham.getSoLuong());
            ps.setObject(8, chiTietSanPham.getGiaNhap());
            ps.setObject(9, chiTietSanPham.getGiaBan());
            ps.setObject(10, chiTietSanPham.getMoTa());
            ps.setObject(11, chiTietSanPham.getTrangThai());
            ps.setObject(12, id);

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return check > 0;
    }

    @Override
    public boolean delete(String id) {
        int check = 0;
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID);
            ps.setObject(1, id);

            check = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    Connection conn = DBConnection.getConnection();
    public String getIDCTSP (String IDSP){
        String query = "SELECT IdCTSP FROM dbo.ChiTietSP WHERE IdSP = ?";
        String IdCTSP = null;
        try {
            PreparedStatement ps = conn.prepareCall(query);
            ps.setString(1, IDSP);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                IdCTSP = rs.getString("IdCTSP");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return IdCTSP;
    }
    
    public boolean uppdateSoLuong(String IdCTSP, int soLuong){
        int check = 0;
        String query = "UPDATE dbo.ChiTietSP SET SoLuong = ? WHERE IdCTSP = ?";
        try {
            PreparedStatement ps = conn.prepareCall(query);
            ps.setInt(1, soLuong);
            ps.setString(2, IdCTSP);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    public static void main(String[] args) {
//        new ChiTietSanPhamRepository().getAll().forEach(s -> System.out.println(s.toString()));
          ChiTietSanPham ctsp = new ChiTietSanPham();
          ctsp.setTrangThai(999);
          new ChiTietSanPhamRepository().update(ctsp, "7BBBA41E-C3BD-45FC-B192-086D9F37C516");
    }
}
