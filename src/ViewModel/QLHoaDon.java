/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

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
        return new Object[]{soThuTu, maHoaDon, ngayTao, tenNhanVien, tenKhachHang, trangThai == 1 ? "Đã thanh toán" : "Chờ thanh toán"};
    }

    @Override
    public String toString() {
        return "QLHoaDon{" + "soThuTu=" + soThuTu + ", idHoaDon=" + idHoaDon + ", maHoaDon=" + maHoaDon + ", ngayTao=" + ngayTao + ", tenNhanVien=" + tenNhanVien + ", trangThai=" + trangThai + ", tenKhachHang=" + tenKhachHang + ", sdt=" + sdt + '}';
    }

}
