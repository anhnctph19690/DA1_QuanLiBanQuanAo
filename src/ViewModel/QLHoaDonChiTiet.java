/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

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
    private BigDecimal giaBanSauKhiGiam;
    private double thanhTien;
    private Date ngayTao;
    private String maHD;

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

    public QLHoaDonChiTiet(String idHoaDon, String idCTSP, String maSP, String tenSP, int soLuongMua, BigDecimal donGia, Date ngayTao, String maHD) {
        this.idHoaDon = idHoaDon;
        this.idCTSP = idCTSP;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuongMua = soLuongMua;
        this.donGia = donGia;

        this.ngayTao = ngayTao;
        this.maHD = maHD;
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

    public Double getTotalSale() {
        if (this.giaBanSauKhiGiam != null) {
            return this.soLuongMua * this.giaBanSauKhiGiam.doubleValue();
        }
        return getTotal();
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public BigDecimal getGiaBanSauKhiGiam() {
        return giaBanSauKhiGiam;
    }

    public void setGiaBanSauKhiGiam(BigDecimal giaBanSauKhiGiam) {
        this.giaBanSauKhiGiam = giaBanSauKhiGiam;
    }

    @Override
    public String toString() {
        return "QLHoaDonChiTiet{" + "idHoaDon=" + idHoaDon + ", idCTSP=" + idCTSP + ", maSP=" + maSP + ", tenSP=" + tenSP + ", soLuongMua=" + soLuongMua + ", donGia=" + donGia + ", giaBanSauKhiGiam=" + giaBanSauKhiGiam + ", thanhTien=" + thanhTien + ", ngayTao=" + ngayTao + ", maHD=" + maHD + '}';
    }
    
    
    public Object[] toDataRow() {
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return new Object[]{maSP, tenSP, soLuongMua, formatter.format(donGia), formatter.format(getTotal())};
    }

}
