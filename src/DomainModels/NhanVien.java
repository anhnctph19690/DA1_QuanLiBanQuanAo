package DomainModels;

import java.util.Date;

public class NhanVien {

    private String idNhanVien;
    private String maNV;
    private String tenNV;
    private String diaChi;
    private String sDT;
    private String gioiTinh;
    private String ngaySinh;
    private String matKhau;
    private Integer trangThai;
    private String idCV;

    public NhanVien() {
    }

    public NhanVien(String idNhanVien, String maNV, String tenNV, String diaChi, String sDT, String gioiTinh, String ngaySinh, String matKhau, Integer trangThai, String idCV) {
        this.idNhanVien = idNhanVien;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.sDT = sDT;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.idCV = idCV;
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

    public String getIdCV() {
        return idCV;
    }

    public void setIdCV(String idCV) {
        this.idCV = idCV;
    }

}
