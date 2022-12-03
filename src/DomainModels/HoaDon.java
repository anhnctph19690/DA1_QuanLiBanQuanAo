/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

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
    private Date NgayThanhToan;
    private Date NgayHen;
    private Date NgayShip;
    private Date NgayNhan;
    private String tenNguoiNhan;
    private String diaChi;
    private String sDT;
    private int TrangThai;

    public HoaDon() {
    }

    public HoaDon(String IdHoaDon, String IdKhachHang, String IdNhanVien, String IdPTTT, String maHoaDon, Date NgayTao, Date NgayThanhToan, Date NgayHen, Date NgayShip, Date NgayNhan, String tenNguoiNhan, String diaChi, String sDT, int TrangThai) {
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

    public void setIdHoaDon(String IdHoaDon) {
        this.IdHoaDon = IdHoaDon;
    }

    public String getIdKhachHang() {
        return IdKhachHang;
    }

    public void setIdKhachHang(String IdKhachHang) {
        this.IdKhachHang = IdKhachHang;
    }

    public String getIdNhanVien() {
        return IdNhanVien;
    }

    public void setIdNhanVien(String IdNhanVien) {
        this.IdNhanVien = IdNhanVien;
    }

    public String getIdPTTT() {
        return IdPTTT;
    }

    public void setIdPTTT(String IdPTTT) {
        this.IdPTTT = IdPTTT;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public Date getNgayHen() {
        return NgayHen;
    }

    public void setNgayHen(Date NgayHen) {
        this.NgayHen = NgayHen;
    }

    public Date getNgayShip() {
        return NgayShip;
    }

    public void setNgayShip(Date NgayShip) {
        this.NgayShip = NgayShip;
    }

    public Date getNgayNhan() {
        return NgayNhan;
    }

    public void setNgayNhan(Date NgayNhan) {
        this.NgayNhan = NgayNhan;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "IdHoaDon=" + IdHoaDon + ", IdKhachHang=" + IdKhachHang + ", IdNhanVien=" + IdNhanVien + ", IdPTTT=" + IdPTTT + ", maHoaDon=" + maHoaDon + ", NgayTao=" + NgayTao + ", NgayThanhToan=" + NgayThanhToan + ", NgayHen=" + NgayHen + ", NgayShip=" + NgayShip + ", NgayNhan=" + NgayNhan + ", tenNguoiNhan=" + tenNguoiNhan + ", diaChi=" + diaChi + ", sDT=" + sDT + ", TrangThai=" + TrangThai + '}';
    }

}
