/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import DomainModels.ChiTietSanPham;
import Repository.IChiTietSanPhamRepository;
import Services.Impl.ChiTietSanPhamService;
import Ultilities.DBConnection;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLThongKe;
import java.math.BigDecimal;
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
    final String SELECT_ALL = "select ROW_NUMBER() OVER (ORDER BY sp.MaSP), ctsp.IdCTSP, sp.MaSP, sp.Ten, nsx.Ten, ms.TenMauSac, lsp.TenLoaiSP, cl.TenChatLieu, th.TenThuongHieu, s.SoSize, ctsp.SoLuong, ctsp.GiaNhap, ctsp.GiaBan, ctsp.MoTa, ctsp.TrangThai\n"
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

    final String SEARCH_SQL_BY_NAME = "select ROW_NUMBER() OVER (ORDER BY sp.MaSP), ctsp.IdCTSP, sp.MaSP, sp.Ten, nsx.Ten, ms.TenMauSac, lsp.TenLoaiSP, cl.TenChatLieu, th.TenThuongHieu, s.SoSize, ctsp.SoLuong, ctsp.GiaNhap, ctsp.GiaBan, ctsp.MoTa, ctsp.TrangThai\n"
            + "from SanPham sp, ChiTietSP ctsp, NSX nsx, MauSac ms, LoaiSanPham lsp, ChatLieu cl, ThuongHieu th, Size s \n"
            + "where sp.IdSP = ctsp.IdSP\n"
            + "and ctsp.IdNSX = nsx.IdNSX\n"
            + "and ctsp.IdMauSac = ms.IdMauSac\n"
            + "and ctsp.IdLoaiSP = lsp.IdLoaiSP\n"
            + "and ctsp.IdChatLieu = cl.IdChatLieu\n"
            + "and ctsp.IdThuongHieu = th.IdThuongHieu\n"
            + "and ctsp.IdSize = s.IdSize and sp.Ten like ?";
    final String SEARCH_SQL_BY_MA = "select ROW_NUMBER() OVER (ORDER BY sp.MaSP), ctsp.IdCTSP, sp.MaSP, sp.Ten, nsx.Ten, ms.TenMauSac, lsp.TenLoaiSP, cl.TenChatLieu, th.TenThuongHieu, s.SoSize, ctsp.SoLuong, ctsp.GiaNhap, ctsp.GiaBan, ctsp.MoTa, ctsp.TrangThai\n"
            + "from SanPham sp, ChiTietSP ctsp, NSX nsx, MauSac ms, LoaiSanPham lsp, ChatLieu cl, ThuongHieu th, Size s \n"
            + "where sp.IdSP = ctsp.IdSP\n"
            + "and ctsp.IdNSX = nsx.IdNSX\n"
            + "and ctsp.IdMauSac = ms.IdMauSac\n"
            + "and ctsp.IdLoaiSP = lsp.IdLoaiSP\n"
            + "and ctsp.IdChatLieu = cl.IdChatLieu\n"
            + "and ctsp.IdThuongHieu = th.IdThuongHieu\n"
            + "and ctsp.IdSize = s.IdSize and sp.MaSP like ?";

    @Override
    public List<QLChiTietSanPham> getAll() {

        List<QLChiTietSanPham> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();) {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                QLChiTietSanPham qlctsp = new QLChiTietSanPham();
                qlctsp.setSTT(rs.getInt(1));
                qlctsp.setIdCTSP(rs.getString(2));
                qlctsp.setMaSanPham(rs.getString(3));
                qlctsp.setTenSanPham(rs.getString(4));
                qlctsp.setTenNhaSanXuat(rs.getString(5));
                qlctsp.setTenMauSac(rs.getString(6));
                qlctsp.setTenLoai(rs.getString(7));
                qlctsp.setTenChatLieu(rs.getString(8));
                qlctsp.setTenThuongHieu(rs.getString(9));
                qlctsp.setSoSize(rs.getString(10));
                qlctsp.setSoLuongTonKho(rs.getInt(11));
                qlctsp.setGiaNhap(rs.getBigDecimal(12));
                qlctsp.setGiaBan(rs.getBigDecimal(13));
                qlctsp.setMoTa(rs.getString(14));
                qlctsp.setTrangThai(rs.getInt(15));
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
        try (Connection conn = DBConnection.getConnection();) {
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
        try (Connection conn = DBConnection.getConnection();) {
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
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID);
            ps.setObject(1, id);

            check = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public String getIdSP(String id) {
        String query = "SELECT IdSP FROM dbo.SanPham WHERE MaSP = ?";
        String IdSP = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IdSP = rs.getString("IdSP");
                return IdSP;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean uppdateSoLuong(String IdCTSP, int soLuong) {
        int check = 0;
        String query = "UPDATE dbo.ChiTietSP SET SoLuong = ? WHERE IdCTSP = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, soLuong);
            ps.setString(2, IdCTSP);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public int checkSoLuong(String id) {
        String query = "SELECT SoLuong FROM dbo.ChiTietSP WHERE IdSP = ?";
        int soLuong = 0;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt("SoLuong");
                return soLuong;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<QLChiTietSanPham> searchByName(String name) {
        List<QLChiTietSanPham> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();) {
            PreparedStatement ps = conn.prepareStatement(SEARCH_SQL_BY_NAME);
            ps.setObject(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLChiTietSanPham qlctsp = new QLChiTietSanPham();
                qlctsp.setSTT(rs.getInt(1));
                qlctsp.setIdCTSP(rs.getString(2));
                qlctsp.setMaSanPham(rs.getString(3));
                qlctsp.setTenSanPham(rs.getString(4));
                qlctsp.setTenNhaSanXuat(rs.getString(5));
                qlctsp.setTenMauSac(rs.getString(6));
                qlctsp.setTenLoai(rs.getString(7));
                qlctsp.setTenChatLieu(rs.getString(8));
                qlctsp.setTenThuongHieu(rs.getString(9));
                qlctsp.setSoSize(rs.getString(10));
                qlctsp.setSoLuongTonKho(rs.getInt(11));
                qlctsp.setGiaNhap(rs.getBigDecimal(12));
                qlctsp.setGiaBan(rs.getBigDecimal(13));
                qlctsp.setMoTa(rs.getString(14));
                qlctsp.setTrangThai(rs.getInt(15));
                list.add(qlctsp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
        ArrayList<QLChiTietSanPham> list = chiTietSanPhamRepository.getListByLoaiSP("Quần");
        for (QLChiTietSanPham o : list) {
            o.toString();
        }
    }

    @Override
    public List<QLChiTietSanPham> searchByMa(String ma) {
        List<QLChiTietSanPham> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();) {
            PreparedStatement ps = conn.prepareStatement(SEARCH_SQL_BY_MA);
            ps.setObject(1, "%" + ma + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QLChiTietSanPham qlctsp = new QLChiTietSanPham();
                qlctsp.setSTT(rs.getInt(1));
                qlctsp.setIdCTSP(rs.getString(2));
                qlctsp.setMaSanPham(rs.getString(3));
                qlctsp.setTenSanPham(rs.getString(4));
                qlctsp.setTenNhaSanXuat(rs.getString(5));
                qlctsp.setTenMauSac(rs.getString(6));
                qlctsp.setTenLoai(rs.getString(7));
                qlctsp.setTenChatLieu(rs.getString(8));
                qlctsp.setTenThuongHieu(rs.getString(9));
                qlctsp.setSoSize(rs.getString(10));
                qlctsp.setSoLuongTonKho(rs.getInt(11));
                qlctsp.setGiaNhap(rs.getBigDecimal(12));
                qlctsp.setGiaBan(rs.getBigDecimal(13));
                qlctsp.setMoTa(rs.getString(14));
                qlctsp.setTrangThai(rs.getInt(15));
                list.add(qlctsp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public int demSoLuongSanPham() {
        int soluonghoadon = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(MaHD) AS N'Hóa đơn chưa thanh toán' FROM HoaDon WHERE TrangThai = ?  ";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, soluonghoadon);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                soluonghoadon = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return soluonghoadon;

    }

    @Override
    public int TongSP() {
        int soluongcon = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "select count(IdCTSP) from ChiTietSP ";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                soluongcon = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return soluongcon;

    }

    @Override
    public int demSoLuongSPCH() {
        int soluongcon = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "select count(IdCTSP) from ChiTietSP where TrangThai = 1";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                soluongcon = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return soluongcon;
    }

    @Override
    public int demSoLuongSPHH() {
        int soluonghet = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "select count(IdCTSP) from ChiTietSP where TrangThai = 0";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {
                soluonghet = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return soluonghet;
    }

    @Override
    public List<QLThongKe> thongKeALL(String thongKeTheo, String SapXepTheo) {

        List<QLThongKe> listQLThongKe = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();) {
            String sql
                    = "SELECT dbo.SanPham.MaSP, dbo.SanPham.Ten, dbo.ChatLieu.TenChatLieu, dbo.MauSac.TenMauSac, dbo.Size.SoSize, dbo.ThuongHieu.TenThuongHieu, dbo.NSX.Ten as TenNSX, dbo.ChiTietSP.SoLuong, dbo.ChiTietSP.GiaNhap, "
                    + " dbo.ChiTietSP.GiaBan "
                    + "FROM dbo.ChiTietSP INNER JOIN "
                    + " dbo.ChatLieu ON dbo.ChiTietSP.IdChatLieu = dbo.ChatLieu.IdChatLieu INNER JOIN "
                    + " dbo.MauSac ON dbo.ChiTietSP.IdMauSac = dbo.MauSac.IdMauSac INNER JOIN "
                    + " dbo.ThuongHieu ON dbo.ChiTietSP.IdThuongHieu = dbo.ThuongHieu.IdThuongHieu INNER JOIN "
                    + " dbo.NSX ON dbo.ChiTietSP.IdNSX = dbo.NSX.IdNSX INNER JOIN "
                    + " dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.IdSP INNER JOIN "
                    + " dbo.Size ON dbo.ChiTietSP.IdSize = dbo.Size.IdSize "
                    + " GROUP BY dbo.SanPham.MaSP, dbo.SanPham.Ten, dbo.ChatLieu.TenChatLieu, dbo.MauSac.TenMauSac, dbo.Size.SoSize, dbo.ThuongHieu.TenThuongHieu, dbo.NSX.Ten, dbo.ChiTietSP.SoLuong, dbo.ChiTietSP.GiaNhap, "
                    + " dbo.ChiTietSP.GiaBan "
                    + "ORDER BY " + thongKeTheo + "  " + SapXepTheo;

            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setString(1, thongKeTheo);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                QLThongKe qlTK = new QLThongKe();

                qlTK.setStt(0);
                qlTK.setMaSP(rs.getString("MaSP"));
                qlTK.setTenSP(rs.getString("Ten"));
                qlTK.setChatLieu(rs.getString("TenChatLieu"));
                qlTK.setMauSac(rs.getString("TenMauSac"));
                qlTK.setKichThuoc(rs.getString("SoSize"));
                qlTK.setThuongHieu(rs.getString("TenThuongHieu"));
                qlTK.setNsx(rs.getString("TenNSX"));
                qlTK.setSoLuong(rs.getInt("giaNhap"));
                qlTK.setGiaNhap(rs.getDouble("GiaNhap"));
                qlTK.setGiaBan(rs.getDouble("GiaBan"));

                listQLThongKe.add(qlTK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listQLThongKe;
    }

    public ArrayList<QLChiTietSanPham> getListByLoaiSP(String loaiSP) {
         ArrayList<QLChiTietSanPham> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT        dbo.SanPham.MaSP, dbo.SanPham.Ten, dbo.Size.SoSize, dbo.ChatLieu.TenChatLieu, dbo.ChiTietSP.GiaBan, dbo.ChiTietSP.TrangThai, dbo.LoaiSanPham.TenLoaiSP, dbo.MauSac.TenMauSac,dbo.NSX.Ten AS TenNSX,dbo.ThuongHieu.TenThuongHieu\n"
                + "FROM            dbo.ChatLieu INNER JOIN\n"
                + "                         dbo.ChiTietSP ON dbo.ChatLieu.IdChatLieu = dbo.ChiTietSP.IdChatLieu INNER JOIN\n"
                + "                         dbo.LoaiSanPham ON dbo.ChiTietSP.IdLoaiSP = dbo.LoaiSanPham.IdLoaiSP INNER JOIN\n"
                + "                         dbo.MauSac ON dbo.ChiTietSP.IdMauSac = dbo.MauSac.IdMauSac INNER JOIN\n"
                + "                         dbo.Size ON dbo.ChiTietSP.IdSize = dbo.Size.IdSize INNER JOIN\n"
                + "                         dbo.ThuongHieu ON dbo.ChiTietSP.IdThuongHieu = dbo.ThuongHieu.IdThuongHieu INNER JOIN \n"
                + "                         dbo.ThuongHieu ON dbo.ChiTietSP.IdThuongHieu = dbo.ThuongHieu.IdThuongHieu INNER JOIN\n"
                + "                         dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.IdSP\n"
                + "                         dbo.NSX ON dbo.ChiTietSP.IdNSX = dbo.NSX.IdNSX\n"
                + "						 WHERE TenLoaiSP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, loaiSP);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {

                QLChiTietSanPham qlctsp = new QLChiTietSanPham();

                qlctsp.setMaSanPham(rs.getString("MaSP"));
                qlctsp.setTenSanPham(rs.getString("SanPham.Ten"));
                qlctsp.setTenNhaSanXuat(rs.getString("TenNSX"));
                qlctsp.setTenMauSac(rs.getString("TenMauSac"));
                qlctsp.setTenLoai(rs.getString("TenLoaiSP"));
                qlctsp.setTenChatLieu(rs.getString("TenChatLieu"));
                qlctsp.setTenThuongHieu(rs.getString("TenThuongHieu"));
                qlctsp.setSoSize(rs.getString("SoSize"));
                
                qlctsp.setGiaBan(rs.getBigDecimal("GiaBan"));
             
                qlctsp.setTrangThai(rs.getInt("TrangThai"));
                list.add(qlctsp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<QLChiTietSanPham> getListSPByKhoangGia(double min, double max) {
        String query = "SELECT        dbo.SanPham.MaSP, dbo.SanPham.Ten, dbo.Size.SoSize, dbo.ChatLieu.TenChatLieu, dbo.ChiTietSP.GiaBan, dbo.ChiTietSP.TrangThai, dbo.LoaiSanPham.TenLoaiSP, dbo.MauSac.TenMauSac,dbo.NSX.Ten AS TenNSX,dbo.ThuongHieu.TenThuongHieu\n"
                + "FROM            dbo.ChatLieu INNER JOIN\n"
                + "                         dbo.ChiTietSP ON dbo.ChatLieu.IdChatLieu = dbo.ChiTietSP.IdChatLieu INNER JOIN\n"
                + "                         dbo.LoaiSanPham ON dbo.ChiTietSP.IdLoaiSP = dbo.LoaiSanPham.IdLoaiSP INNER JOIN\n"
                + "                         dbo.MauSac ON dbo.ChiTietSP.IdMauSac = dbo.MauSac.IdMauSac INNER JOIN\n"
                + "                         dbo.Size ON dbo.ChiTietSP.IdSize = dbo.Size.IdSize INNER JOIN\n"
                + "                         dbo.ThuongHieu ON dbo.ChiTietSP.IdThuongHieu = dbo.ThuongHieu.IdThuongHieu INNER JOIN \n"
                + "                         dbo.ThuongHieu ON dbo.ChiTietSP.IdThuongHieu = dbo.ThuongHieu.IdThuongHieu INNER JOIN\n"
                + "                         dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.IdSP\n"
                + "                         dbo.NSX ON dbo.ChiTietSP.IdNSX = dbo.NSX.IdNSX\n"
                + "						 WHERE  GiaBan BETWEEN ? AND ?";
        List<QLChiTietSanPham> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                QLChiTietSanPham qlctsp = new QLChiTietSanPham();

                qlctsp.setMaSanPham(rs.getString("MaSP"));
                qlctsp.setTenSanPham(rs.getString("SanPham.Ten"));
                qlctsp.setTenNhaSanXuat(rs.getString("TenNSX"));
                qlctsp.setTenMauSac(rs.getString("TenMauSac"));
                qlctsp.setTenLoai(rs.getString("TenLoaiSP"));
                qlctsp.setTenChatLieu(rs.getString("TenChatLieu"));
                qlctsp.setTenThuongHieu(rs.getString("TenThuongHieu"));
                qlctsp.setSoSize(rs.getString("SoSize"));

                qlctsp.setGiaBan(rs.getBigDecimal("GiaBan"));

//                qlctsp.setTrangThai(rs.getInt("TrangThai"));
                list.add(qlctsp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    
    public String getMabyId(String maCTSP){
        String query = "SELECT MaSP FROM dbo.ChiTietSP JOIN dbo.SanPham ON SanPham.IdSP = ChiTietSP.IdSP WHERE IdCTSP = ? ";
        String maSP = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, maCTSP);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                maSP = rs.getString("MaSP");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return maSP;
        
    }
    
   
}
