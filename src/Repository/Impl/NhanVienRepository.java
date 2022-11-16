package Repository.Impl;

import Models.NhanVien;
import Repository.INhanVienRepository;
import Ultilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NhanVienRepository implements INhanVienRepository {

    @Override
    public List<NhanVien> getAll() {
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM NhanVien";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next() == true) {
                String id = rs.getString("IdNV");
                String ma = rs.getString("MaNV");
                String ten = rs.getString("TenNV");
                String diaChi = rs.getString("DiaChi");
                String sDT = rs.getString("Sdt");
                String gioiTinh = rs.getString("GioiTinh");
                String NgaySinh = rs.getString("NgaySinh");
                String matKhau = rs.getString("MatKhau");
                Integer trangThai = rs.getInt("TrangThai");
                String idCV = rs.getString("IdCV");

                NhanVien nv = new NhanVien(id, ma, ten,
                        diaChi, sDT, gioiTinh, NgaySinh, matKhau, trangThai, idCV);
                listNhanVien.add(nv);
            }

            System.out.println("Select ok");
        } catch (Exception e) {
            System.out.println("Select X");
            e.printStackTrace();
        }
        return listNhanVien;
    }
    

    @Override
    public void insert(NhanVien nv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(NhanVien nv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(NhanVien nv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
