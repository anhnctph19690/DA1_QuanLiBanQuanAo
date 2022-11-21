/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author tuane_nluzcuo
 */
public class HoaDon {

    private String IdHoaDon;
    private String IdKhachHang;
    private String IdNhanVien;
    private String IdPTTT;
    private String maHoaDon;
    private Date NgayTao;
    private String NgayThanhToan;
    private String NgayHen;
    private String NgayShip;
    private String NgayNhan;
    private String tenNguoiNhan;
    private String diaChi;
    private String sDT;
    private int TrangThai;

    public HoaDon() {
    }

    public HoaDon(String IdHoaDon, String IdKhachHang, String IdNhanVien, String IdPTTT, String maHoaDon, Date NgayTao, String NgayThanhToan, String NgayHen, String NgayShip, String NgayNhan, String tenNguoiNhan, String diaChi, String sDT, int TrangThai) {
        this.IdHoaDon = IdHoaDon;
        this.IdKhachHang = IdKhachHang;
        this.IdNhanVien = IdNhanVien;
        this.IdPTTT = IdPTTT;
        this.maHoaDon = maHoaDon;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.NgayHen = NgayHen;
        this.NgayShip = NgayShip;
        this.NgayNhan = NgayNhan;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChi = diaChi;
        this.sDT = sDT;
        this.TrangThai = TrangThai;
    }

    public String getIdHoaDon() {
        return IdHoaDon;
    }

    public String getIdKhachHang() {
        return IdKhachHang;
    }

    public String getIdNhanVien() {
        return IdNhanVien;
    }

    public String getIdPTTT() {
        return IdPTTT;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    public String getNgayHen() {
        return NgayHen;
    }

    public String getNgayShip() {
        return NgayShip;
    }

    public String getNgayNhan() {
        return NgayNhan;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getsDT() {
        return sDT;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setIdHoaDon(String IdHoaDon) {
        this.IdHoaDon = IdHoaDon;
    }

    public void setIdKhachHang(String IdKhachHang) {
        this.IdKhachHang = IdKhachHang;
    }

    public void setIdNhanVien(String IdNhanVien) {
        this.IdNhanVien = IdNhanVien;
    }

    public void setIdPTTT(String IdPTTT) {
        this.IdPTTT = IdPTTT;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setNgayThanhToan(String NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public void setNgayHen(String NgayHen) {
        this.NgayHen = NgayHen;
    }

    public void setNgayShip(String NgayShip) {
        this.NgayShip = NgayShip;
    }

    public void setNgayNhan(String NgayNhan) {
        this.NgayNhan = NgayNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "IdHoaDon=" + IdHoaDon + ", IdKhachHang=" + IdKhachHang + ", IdNhanVien=" + IdNhanVien + ", IdPTTT=" + IdPTTT + ", maHoaDon=" + maHoaDon + ", NgayTao=" + NgayTao + ", NgayThanhToan=" + NgayThanhToan + ", NgayHen=" + NgayHen + ", NgayShip=" + NgayShip + ", NgayNhan=" + NgayNhan + ", tenNguoiNhan=" + tenNguoiNhan + ", diaChi=" + diaChi + ", sDT=" + sDT + ", TrangThai=" + TrangThai + '}';
    }

}
