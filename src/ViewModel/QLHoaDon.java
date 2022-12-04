/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author tuane_nluzcuo
 */
public class QLHoaDon {

    private int soThuTu;
    private String idHoaDon;
    private String maHoaDon;
    private Date ngayTao;
    private String tenNhanVien;
    private int trangThai;
    private String tenKhachHang;
    private String sdt;

    public QLHoaDon() {
    }

    public QLHoaDon(int soThuTu, String idHoaDon, String maHoaDon, Date ngayTao, String tenNhanVien, int trangThai, String tenKhachHang, String sdt) {
        this.soThuTu = soThuTu;
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.tenNhanVien = tenNhanVien;
        this.trangThai = trangThai;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;

    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Object[] toDataRow() {
        return new Object[]{soThuTu, maHoaDon, ngayTao, tenNhanVien, tenKhachHang == null ? "Khách lẻ" : tenKhachHang, trangThai == 0 ? "Chờ thanh toán" : trangThai == 1 ? "Đã thanh toán" : trangThai == 2 ? "Đang tạo" : trangThai == 3 ? "Đang giao" : "Đã giao"};
    }

    public Object[] dataRow() {
        return new Object[]{soThuTu, maHoaDon, tenNhanVien, ngayTao, trangThai == 0 ? "Chờ thanh toán" : trangThai == 1 ? "Đã thanh toán" : trangThai == 2 ? "Đang tạo" : trangThai == 3 ? "Đang giao" : "Đã giao"};
    }

    @Override
    public String toString() {
        return "QLHoaDon{" + "soThuTu=" + soThuTu + ", idHoaDon=" + idHoaDon + ", maHoaDon=" + maHoaDon + ", ngayTao=" + ngayTao + ", tenNhanVien=" + tenNhanVien + ", trangThai=" + trangThai + ", tenKhachHang=" + tenKhachHang + ", sdt=" + sdt + '}';
    }

    public String getTrangThai1() {
        if (trangThai == 0) {
            return "Chờ thanh toán";
        }
        if (trangThai == 1) {
            return "Đã thanh toán";
        }
        if (trangThai == 2) {
            return "Đang tạo";
        }
        if (trangThai == 3) {
            return "Đang giao";
        }
        return "Đã giao";
    }

}
