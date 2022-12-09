/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Impl;

import Ultilities.DBConnection;
import ViewModel.QLThongKeHD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Repository.IThongKeHdReposittory;

/**
 *
 * @author ADMIN
 */
public class ThongKeHdRepository implements IThongKeHdReposittory{
   final String SELECT_ALL = "select ROW_NUMBER() OVER (ORDER BY hd.mahd), hd.MaHD, hd.NgayTao,nv.TenNV\n"
            + "             from HoaDon hd, NhanVien nv\n"
            + "             where nv.IdNV = hd.IdNV";
    
   
    

    @Override
    public List<QLThongKeHD> getALLThongKeHDs() {
        List<QLThongKeHD> list = new ArrayList<>();
        try ( Connection conn = DBConnection.getConnection();) {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                QLThongKeHD tkhd = new QLThongKeHD();
                tkhd.setStt(rs.getInt(1));
                tkhd.setMahd(rs.getString(2));
                tkhd.setNgayTao(rs.getDate(3));
                tkhd.setTennv(rs.getString(4));
//                qlctsp.setTenNhaSanXuat(rs.getString(5));
//                qlctsp.setTenMauSac(rs.getString(6));
//                qlctsp.setTenLoai(rs.getString(7));
//                qlctsp.setTenChatLieu(rs.getString(8));
//                qlctsp.setTenThuongHieu(rs.getString(9));
//                qlctsp.setSoSize(rs.getString(10));
//                qlctsp.setSoLuongTonKho(rs.getInt(11));
//                qlctsp.setGiaNhap(rs.getBigDecimal(12));
//                qlctsp.setGiaBan(rs.getBigDecimal(13));
//                qlctsp.setMoTa(rs.getString(14));
//                qlctsp.setTrangThai(rs.getInt(15));
                list.add(tkhd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    @Override
    public List<QLThongKeHD> thongKeALL(String thongKeTheo, String SapXepTheo) {
        List<QLThongKeHD> listQLThongKeHd = new ArrayList<>();
        try ( Connection conn = DBConnection.getConnection();) {
            String sql
                    = "Select HoaDon.MaHD, HoaDon.NgayTao, NhanVien.TenNV from HoaDon join NhanVien on HoaDon.IdNV = NhanVien.IdNV\n"
                    + "GROUP BY HoaDon.MaHD,HoaDon.NgayTao, NhanVien.TenNV\n"
                    + "order by  " + thongKeTheo + "  " + SapXepTheo;

            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setString(1, thongKeTheo);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                QLThongKeHD qlTKHD = new QLThongKeHD();

                qlTKHD.setStt(0);
                qlTKHD.setMahd(rs.getString(1));
                qlTKHD.setNgayTao(rs.getDate(2));
                qlTKHD.setTennv(rs.getString(3));
                

                listQLThongKeHd.add(qlTKHD);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listQLThongKeHd;
    }

    

    
}

