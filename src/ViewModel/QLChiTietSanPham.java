/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.math.BigDecimal;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class QLChiTietSanPham {

    private int STT;
    private String idCTSP;
    private String maSanPham;
    private String tenSanPham;
    private String tenNhaSanXuat;
    private String tenMauSac;
    private String tenLoai;
    private String tenChatLieu;
    private String tenThuongHieu;
    private String soSize;
    private int soLuongTonKho;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private String moTa;
    private int trangThai;

    public QLChiTietSanPham() {
    }

    public QLChiTietSanPham(int STT, String idCTSP, String maSanPham, String tenSanPham, String tenNhaSanXuat, String tenMauSac, String tenLoai, String tenChatLieu, String tenThuongHieu, String soSize, int soLuongTonKho, BigDecimal giaNhap, BigDecimal giaBan, String moTa, int trangThai) {
        this.STT = STT;
        this.idCTSP = idCTSP;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenNhaSanXuat = tenNhaSanXuat;
        this.tenMauSac = tenMauSac;
        this.tenLoai = tenLoai;
        this.tenChatLieu = tenChatLieu;
        this.tenThuongHieu = tenThuongHieu;
        this.soSize = soSize;
        this.soLuongTonKho = soLuongTonKho;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

   
    

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(String idCTSP) {
        this.idCTSP = idCTSP;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenNhaSanXuat() {
        return tenNhaSanXuat;
    }

    public void setTenNhaSanXuat(String tenNhaSanXuat) {
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getSoSize() {
        return soSize;
    }

    public void setSoSize(String soSize) {
        this.soSize = soSize;
    }

    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "QLChiTietSanPham{" + "STT=" + STT + ", idCTSP=" + idCTSP + ", maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", tenNhaSanXuat=" + tenNhaSanXuat + ", tenMauSac=" + tenMauSac + ", tenLoai=" + tenLoai + ", tenChatLieu=" + tenChatLieu + ", tenThuongHieu=" + tenThuongHieu + ", soSize=" + soSize + ", soLuongTonKho=" + soLuongTonKho + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", moTa=" + moTa + ", trangThai=" + trangThai + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{STT, maSanPham, tenSanPham, tenNhaSanXuat, tenMauSac, tenLoai, tenChatLieu, tenThuongHieu, soSize, soLuongTonKho, giaNhap, giaBan, moTa, trangThai == 1 ? "Còn hàng" : "Hết hàng"};
    }

    public Object[] toDataRowBanHang() {
        return new Object[]{STT, maSanPham, tenSanPham, soLuongTonKho, giaBan, soSize, tenMauSac};
    }
}
