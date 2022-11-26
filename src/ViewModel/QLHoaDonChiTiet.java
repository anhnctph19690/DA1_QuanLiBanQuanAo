/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.math.BigDecimal;

/**
 *
 * @author tuane_nluzcuo
 */
public class QLHoaDonChiTiet {

    private String idHoaDon;
    private String idCTSP;
    private String maSP;
    private String tenSP;
    private int soLuongMua;
    private BigDecimal donGia;
    private double thanhTien;

    public QLHoaDonChiTiet() {
    }

    public QLHoaDonChiTiet(String idHoaDon, String idCTSP, String maSP, String tenSP, int soLuongMua, BigDecimal donGia) {
        this.idHoaDon = idHoaDon;
        this.idCTSP = idCTSP;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuongMua = soLuongMua;
        this.donGia = donGia;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(String idCTSP) {
        this.idCTSP = idCTSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Double getTotal() {
        return this.soLuongMua * this.donGia.doubleValue();
    }

    public Object[] toDataRow() {
        return new Object[]{maSP, tenSP, soLuongMua, donGia, getTotal()};
    }

}
