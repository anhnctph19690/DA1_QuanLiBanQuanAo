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
public class GiamGia {
    private String IdGiamGia;
    private String maGiamGia;
    private String tenGiamGia;
    private String ngayBatDau;
    private String ngayKetThuc;
    private String dieuKienGiamGia;
    private Double mucGiamGia;
    private int trangThai;
    private String loaiGiamGia;

    public GiamGia() {
    }

    public GiamGia(String IdGiamGia, String maGiamGia, String tenGiamGia, String ngayBatDau, String ngayKetThuc, String dieuKienGiamGia, Double mucGiamGia, int trangThai, String loaiGiamGia) {
        this.IdGiamGia = IdGiamGia;
        this.maGiamGia = maGiamGia;
        this.tenGiamGia = tenGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.dieuKienGiamGia = dieuKienGiamGia;
        this.mucGiamGia = mucGiamGia;
        this.trangThai = trangThai;
        this.loaiGiamGia = loaiGiamGia;
    }

    public String getDieuKienGiamGia() {
        return dieuKienGiamGia;
    }

    public void setDieuKienGiamGia(String dieuKienGiamGia) {
        this.dieuKienGiamGia = dieuKienGiamGia;
    }

    

    public String getIdGiamGia() {
        return IdGiamGia;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public String getTenGiamGia() {
        return tenGiamGia;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public Double getMucGiamGia() {
        return mucGiamGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public String getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setIdGiamGia(String IdGiamGia) {
        this.IdGiamGia = IdGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public void setTenGiamGia(String tenGiamGia) {
        this.tenGiamGia = tenGiamGia;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public void setMucGiamGia(Double mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public void setLoaiGiamGia(String loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }
    
    public String loadTrangThaiString (){
        if (this.trangThai == 1) {
            return "Đang Hoạt Động";
        } else if (this.trangThai == 0) {
            return "Chờ Hoạt Động";
        } else {
            return "Dừng Hoạt Động";
        }
    }

    @Override
    public String toString() {
        return "GiamGia{" + "IdGiamGia=" + IdGiamGia + ", maGiamGia=" + maGiamGia + ", tenGiamGia=" + tenGiamGia + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", dieuKienGiamGia=" + dieuKienGiamGia + ", mucGiamGia=" + mucGiamGia + ", trangThai=" + trangThai + ", loaiGiamGia=" + loaiGiamGia + '}';
    }
    
    
    
}
