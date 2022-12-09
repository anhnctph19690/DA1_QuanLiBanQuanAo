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
public class QLHoaDonThongKe {
    private String idHoaDon;
    private String maHoaDon;
    private Date ngayTao;
    private String tenNhanVien;
    private int tongSP;
    private Double tongTien;
    private int trangThai;

    public QLHoaDonThongKe(String idHoaDon, String maHoaDon, Date ngayTao, String tenNhanVien, int tongSP, Double tongTien, int trangThai) {
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.tenNhanVien = tenNhanVien;
        this.tongSP = tongSP;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public QLHoaDonThongKe() {
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public int getTongSP() {
        return tongSP;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setTongSP(int tongSP) {
        this.tongSP = tongSP;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
    
}
