package Repository.Impl;

import Models.NhanVien;
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
    public void update(NhanVien nv, String IdNV) {
        try {

            String query = "UPDATE NhanVien SET TenNV =?, MaNV = ?, DiaChi =?, Sdt =?, Gioitinh =?, NgaySinh =?, MatKhau =?,TrangThai = ?,idCV = ? "
                    + " Where IdNV = ? ";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getMaNV());
            ps.setString(3, nv.getDiaChi());
            ps.setString(4, nv.getsDT());
            ps.setString(5, nv.getGioiTinh());
            ps.setString(6, nv.getNgaySinh());
            ps.setString(7, nv.getMatKhau());
            ps.setInt(8, nv.getTrangThai());
            ps.setString(9, nv.getIdCV());
            ps.setString(10, IdNV);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String maNv) {
        try {

            String query = "DELETE NhanVien Where IdNV = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, maNv);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();

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


}
