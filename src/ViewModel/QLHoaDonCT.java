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
public class QLHoaDonCT {

    private int STT;
    private String idHoaDon;
    private String idCTSP;
    private String maSP;
    private String tenSP;
    private int soLuongMua;
    private BigDecimal donGia;
    private double thanhTien;

    public QLHoaDonCT() {
    }

    public QLHoaDonCT(int STT, String idHoaDon, String idCTSP, String maSP, String tenSP, int soLuongMua, BigDecimal donGia) {
        this.STT = STT;
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

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
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

    public Double getTotal(int soLuongMua, BigDecimal donGia) {
        return soLuongMua * donGia.doubleValue();
    }

    @Override
    public String toString() {
        return "QLHoaDonCT{" + "STT=" + STT + ", idHoaDon=" + idHoaDon + ", idCTSP=" + idCTSP + ", maSP=" + maSP + ", tenSP=" + tenSP + ", soLuongMua=" + soLuongMua + ", donGia=" + donGia + ", thanhTien=" + thanhTien + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{STT, maSP, tenSP, soLuongMua, donGia, getTotal(soLuongMua, donGia)};
    }

}
