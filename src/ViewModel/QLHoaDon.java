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
    private Date ngayHen;
    private String maNV;
    private String tenNhanVien;
    private int trangThai;
    private String tenKhachHang;
    private String sdtKhachHang;
    private String tenNguoiNhan;
    private String diaChiNguoiNhan;
    private String sdtNguoiNhan;
    private BigDecimal tongTien;
    private BigDecimal tongTienSauKhiGiamGiaSanPham;
    private BigDecimal giamGia;
    private BigDecimal tienThua;
    private BigDecimal tienCoc;
    private BigDecimal tienShip;
    private String ghiChu;
    private BigDecimal khachCanTra;

    public QLHoaDon() {
    }

    public QLHoaDon(int soThuTu, String idHoaDon, String maHoaDon, Date ngayTao, String tenNhanVien, int trangThai, String tenKhachHang, String sdtKhachHang) {
        this.soThuTu = soThuTu;
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.tenNhanVien = tenNhanVien;
        this.trangThai = trangThai;
        this.tenKhachHang = tenKhachHang;
        this.sdtKhachHang = sdtKhachHang;
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

    public BigDecimal getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(BigDecimal giamGia) {
        this.giamGia = giamGia;
    }

    public Date getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(Date ngayHen) {
        this.ngayHen = ngayHen;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public BigDecimal getTongTienSauKhiGiamGiaSanPham() {
        return tongTienSauKhiGiamGiaSanPham;
    }

    public void setTongTienSauKhiGiamGiaSanPham(BigDecimal tongTienSauKhiGiamGiaSanPham) {
        this.tongTienSauKhiGiamGiaSanPham = tongTienSauKhiGiamGiaSanPham;
    }

    public BigDecimal getTienThua() {
        return tienThua;
    }

    public void setTienThua(BigDecimal tienThua) {
        this.tienThua = tienThua;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public BigDecimal getKhachCanTra() {
        return khachCanTra;
    }

    public void setKhachCanTra(BigDecimal khachCanTra) {
        this.khachCanTra = khachCanTra;
    }

    public Object[] toDataRow() {
        return new Object[]{soThuTu, maHoaDon, ngayTao, maNV, tenKhachHang == null && tenNguoiNhan == null ? "Khách lẻ" : tenKhachHang == null ? tenNguoiNhan : tenKhachHang, trangThai == 0 ? "Chờ thanh toán" : trangThai == 1 ? "Đã thanh toán" : trangThai == 2 ? "Chờ cọc" : trangThai == 3 ? "Chờ giao hàng" : trangThai == 4 ? "Đang giao" : trangThai == 5 ? "Đã giao" : trangThai == 6 ? "Đã hủy" : ""};
    }

    public Object[] dataRow() {
        return new Object[]{soThuTu, maHoaDon, tenNhanVien, ngayTao, trangThai == 0 ? "Chờ thanh toán" : trangThai == 1 ? "Đã thanh toán" : trangThai == 2 ? "Chờ cọc" : trangThai == 3 ? "Chờ giao hàng" : trangThai == 4 ? "Đang giao" : trangThai == 5 ? "Đã giao" : trangThai == 6 ? "Đã hủy" : ""};
    }

    @Override
    public String toString() {
        return "QLHoaDon{" + "soThuTu=" + soThuTu + ", idHoaDon=" + idHoaDon + ", maHoaDon=" + maHoaDon + ", ngayTao=" + ngayTao + ", ngayHen=" + ngayHen + ", maNV=" + maNV + ", tenNhanVien=" + tenNhanVien + ", trangThai=" + trangThai + ", tenKhachHang=" + tenKhachHang + ", sdtKhachHang=" + sdtKhachHang + ", tenNguoiNhan=" + tenNguoiNhan + ", diaChiNguoiNhan=" + diaChiNguoiNhan + ", sdtNguoiNhan=" + sdtNguoiNhan + ", tongTien=" + tongTien + ", tongTienSauKhiGiamGiaSanPham=" + tongTienSauKhiGiamGiaSanPham + ", giamGia=" + giamGia + ", tienThua=" + tienThua + ", tienCoc=" + tienCoc + ", tienShip=" + tienShip + ", ghiChu=" + ghiChu + '}';
    }

}
