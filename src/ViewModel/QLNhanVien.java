package ViewModel;

import java.util.Date;

public class QLNhanVien {

    private String idNhanVien;
    private String maNV;
    private String tenNV;
    private String tenChucVu;
    private String diaChi;
    private String sDT;
    private String gioiTinh;
    private String ngaySinh;
    private String matKhau;
    private Integer trangThai;

    public QLNhanVien() {
    }

    public QLNhanVien(String idNhanVien, String maNV, String tenNV, String tenChucVu, String diaChi, String sDT, String gioiTinh, String ngaySinh, String matKhau, Integer trangThai) {
        this.idNhanVien = idNhanVien;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenChucVu = tenChucVu;
        this.diaChi = diaChi;
        this.sDT = sDT;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }



}
