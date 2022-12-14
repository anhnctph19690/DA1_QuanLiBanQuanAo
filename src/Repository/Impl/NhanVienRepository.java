package Repository.Impl;

import DomainModels.NhanVien;
import Repository.INhanVienRepository;
import Ultilities.DBConnection;
import ViewModel.QLNhanVien;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.NhanVienVIew;

public class NhanVienRepository implements INhanVienRepository {

    Connection conn = DBConnection.getConnection();

    @Override
    public List<QLNhanVien> getAll() {
        ArrayList<QLNhanVien> listQLNhanVien = new ArrayList<>();

        try {

            String query = "SELECT *FROM   dbo.NhanVien INNER JOIN dbo.ChucVu ON dbo.NhanVien.IdCV = dbo.ChucVu.IdCV";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next() == true) {
                String id = rs.getString("IdNV");
                String ma = rs.getString("MaNV");
                String ten = rs.getString("TenNV");
                String tenCV = rs.getString("ChucVu");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("Sdt");
                String gioiTinh = rs.getString("GioiTinh");
                String NgaySinh = rs.getString("NgaySinh");
                String matKhau = rs.getString("MatKhau");
                Integer trangThai = rs.getInt("TrangThai");

                QLNhanVien qlNV = new QLNhanVien(id, ma, ten, tenCV,
                        diaChi, sDT, gioiTinh, NgaySinh, matKhau, trangThai);
                listQLNhanVien.add(qlNV);
            }

            System.out.println("Select ok");
        } catch (Exception e) {
            System.out.println("Select X");
            e.printStackTrace();
        }
        return listQLNhanVien;
    }

    @Override
    public void insert(NhanVien nv) {
        try {

            String query = "{call procThemNV(?, ?, ?, ?, ?, ?, ?, ?)}";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getDiaChi());
            ps.setString(3, nv.getsDT());
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getNgaySinh());
            ps.setString(6, nv.getMatKhau());
            ps.setInt(7, nv.getTrangThai());
            ps.setString(8, nv.getIdCV());

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//    public static void main(String[] args) {
//        NhanVienRepository nhanVienRepository = new NhanVienRepository();
//        nhanVienRepository.insert(new NhanVien("", "", "Tuan Anh", "uhdsfuds", "gfsdgs", "Nam", "2022-12-12", "frsgfs", 1, "D1C3E9A0-A6A8-4AA7-A6EA-D5A08874AE75"));
//    }

    @Override
    public void update(NhanVien nv, String maNV) {
        try {

            String query = "UPDATE NhanVien SET TenNV =?, DiaChi =?, Sdt =?, Gioitinh =?, NgaySinh =?, MatKhau =?,TrangThai = ?, IdCV = ? "
                    + " Where MaNV = ? ";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getDiaChi());
            ps.setString(3, nv.getsDT());
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getNgaySinh());
            ps.setString(6, nv.getMatKhau());
            ps.setInt(7, nv.getTrangThai());
            ps.setString(8, nv.getIdCV());
            ps.setString(9, maNV);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean deleteBoolean(String maNv) {
        NhanVienVIew nvv = new NhanVienVIew();
        try {
            String query = "DELETE NhanVien Where maNV = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, maNv);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("loi xoa nv");
            return false;

        }
    }

    @Override
    public String getIDChucVu(String tenCV) {
        String query = "SELECT * FROM dbo.ChucVu WHERE ChucVu = ?";
        String idCV = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tenCV);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                idCV = rs.getString("IdCV");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idCV;
    }

    public void updateStatusLogin(String user, int status) {
        String query = "UPDATE NhanVien SET TrangThai = ? WHERE MANV = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(2, user);
            ps.setInt(1, status);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public NhanVien getNhanVienByStatus(int status) {
        String query = "SELECT * FROM NhanVien WHERE TrangThai = ?";
        NhanVien nv = null;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, status);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String id = rs.getString("IdNV");
                String ma = rs.getString("MaNV");
                String ten = rs.getString("TenNV");
                String idCV = rs.getString("IdCV");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("Sdt");
                String gioiTinh = rs.getString("GioiTinh");
                String NgaySinh = rs.getString("NgaySinh");
                String matKhau = rs.getString("MatKhau");
                Integer trangThai = rs.getInt("TrangThai");

                nv = new NhanVien(id, ma, ten, diaChi, sDT, gioiTinh, NgaySinh, matKhau, trangThai, idCV);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return nv;
    }

}
