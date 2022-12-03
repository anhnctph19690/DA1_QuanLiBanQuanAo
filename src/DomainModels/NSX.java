/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author PC- ASUS
 */
public class NSX {
    private String IdNSX;
    private String MaNSX;
    private String TenNSX;

    public NSX() {
    }

    public NSX(String IdNSX, String MaNSX, String TenNSX) {
        this.IdNSX = IdNSX;
        this.MaNSX = MaNSX;
        this.TenNSX = TenNSX;
    }

    public String getIdNSX() {
        return IdNSX;
    }

    public void setIdNSX(String IdNSX) {
        this.IdNSX = IdNSX;
    }

    public String getMaNSX() {
        return MaNSX;
    }

    public void setMaNSX(String MaNSX) {
        this.MaNSX = MaNSX;
    }

    public String getTenNSX() {
        return TenNSX;
    }

    public void setTenNSX(String TenNSX) {
        this.TenNSX = TenNSX;
    }
    
    
}
