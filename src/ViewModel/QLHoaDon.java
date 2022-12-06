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
    private String sdtKhachHang;
    private String tenNguoiNhan;
    private String diaChiNguoiNhan;
    private String sdtNguoiNhan;
    private BigDecimal tienCoc;
    private BigDecimal tienShip;
    private BigDecimal tongTien;

    public QLHoaDon() {
    }

    public QLHoaDon(int soThuTu, String idHoaDon, String maHoaDon, Date ngayTao, String tenNhanVien, int trangThai, String tenKhachHang, String sdtKhachHang, String tenNguoiNhan, String diaChiNguoiNhan, String sdtNguoiNhan, BigDecimal tienCoc, BigDecimal tienShip, BigDecimal tongTien) {
        this.soThuTu = soThuTu;
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.tenNhanVien = tenNhanVien;
        this.trangThai = trangThai;
        this.tenKhachHang = tenKhachHang;
        this.sdtKhachHang = sdtKhachHang;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChiNguoiNhan = diaChiNguoiNhan;
        this.sdtNguoiNhan = sdtNguoiNhan;
        this.tienCoc = tienCoc;
        this.tienShip = tienShip;
        this.tongTien = tongTien;
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

    public String getSdtKhachHang() {
        return sdtKhachHang;
    }

    public void setSdtKhachHang(String sdtKhachHang) {
        this.sdtKhachHang = sdtKhachHang;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getDiaChiNguoiNhan() {
        return diaChiNguoiNhan;
    }

    public void setDiaChiNguoiNhan(String diaChiNguoiNhan) {
        this.diaChiNguoiNhan = diaChiNguoiNhan;
    }

    public String getSdtNguoiNhan() {
        return sdtNguoiNhan;
    }

    public void setSdtNguoiNhan(String sdtNguoiNhan) {
        this.sdtNguoiNhan = sdtNguoiNhan;
    }

    public BigDecimal getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(BigDecimal tienCoc) {
        this.tienCoc = tienCoc;
    }

    public BigDecimal getTienShip() {
        return tienShip;
    }

    public void setTienShip(BigDecimal tienShip) {
        this.tienShip = tienShip;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Object[] toDataRow() {
        return new Object[]{soThuTu, maHoaDon, ngayTao, tenNhanVien, tenKhachHang == null ? "Khách lẻ" : tenKhachHang, trangThai == 0 ? "Chờ thanh toán" : trangThai == 1 ? "Đã thanh toán" : trangThai == 2 ? "Đang tạo" : trangThai == 3 ? "Đang giao" : trangThai == 4 ? "Đã giao" : "Đã hủy"};
    }

}
