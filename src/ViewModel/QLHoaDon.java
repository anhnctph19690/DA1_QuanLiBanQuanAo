/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author tuane_nluzcuo
 */
public class QLHoaDon {
    private String maHoaDon;
    private String ngayTao;
    private String tenNhanVien;
    private int trangThai;

    public QLHoaDon() {
    }

    public QLHoaDon(String maHoaDon, String ngayTao, String tenNhanVien, int trangThai) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.tenNhanVien = tenNhanVien;
        this.trangThai = trangThai;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
//    public Object[] toDataRow(){
        
//    }
    
    
}
