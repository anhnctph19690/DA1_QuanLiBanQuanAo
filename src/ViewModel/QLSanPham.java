/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author PC- ASUS
 */
public class QLSanPham {
    private String idSanPham;
    private String MaSP;
    private String tenSP;

    public QLSanPham() {
    }

    public QLSanPham(String idSanPham, String MaSP, String tenSP) {
        this.idSanPham = idSanPham;
        this.MaSP = MaSP;
        this.tenSP = tenSP;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    
}
