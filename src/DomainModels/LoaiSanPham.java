/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author PC- ASUS
 */
public class LoaiSanPham {
    private String IdLSP;
    private String ma;
    private String TenLSP;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String IdLSP, String ma, String TenLSP) {
        this.IdLSP = IdLSP;
        this.ma = ma;
        this.TenLSP = TenLSP;
    }

    public LoaiSanPham(String IdLSP, String TenLSP) {
        this.IdLSP = IdLSP;
        this.TenLSP = TenLSP;
    }
    

    public String getIdLSP() {
        return IdLSP;
    }

    public void setIdLSP(String IdLSP) {
        this.IdLSP = IdLSP;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenLSP() {
        return TenLSP;
    }

    public void setTenLSP(String TenLSP) {
        this.TenLSP = TenLSP;
    }

    

  
    
    
}
