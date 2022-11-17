package Repository.Impl;

import Models.NhanVien;
import Repository.INhanVienRepository;
import Ultilities.DBConnection;
import ViewModel.QLNhanVien;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NhanVienRepository implements INhanVienRepository {

    @Override
    public List<QLNhanVien> getAll() {
        ArrayList<QLNhanVien> listQLNhanVien = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
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
            Connection conn = DBConnection.getConnection();
            String query = "insert into NhanVien"
                    + "(MaNV,tenNV,DiaChi,Sdt,gioitinh,NgaySinh,MatKhau,trangThai,idCV) values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getTenNV());
            ps.setString(3, nv.getDiaChi());
            ps.setString(4, nv.getsDT());
            ps.setString(5, nv.getGioiTinh());
            ps.setString(6, nv.getNgaySinh());
            ps.setString(7, nv.getMatKhau());
            ps.setInt(8, nv.getTrangThai());
            ps.setString(9, nv.getIdCV());

            ps.execute();
            System.out.println("Insert OK");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Insert X");
        }

    }

    @Override
    public void update(NhanVien nv) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "UPDATE NhanVien SET tenNV =?,DiaChi =?,Sdt =?,gioitinh =?,NgaySinh =?,MatKhau =?,trangThai =?,idCV =? "
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
            ps.setString(9, nv.getMaNV());

            ps.execute();
            System.out.println("Update ok");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(NhanVien nv) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE NhanVien Where idNV = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nv.getIdNhanVien());
            ps.execute();
            System.out.println("delete ok");

        } catch (Exception e) {
            System.out.println("delete X");
            e.printStackTrace();

        }
    }

}
