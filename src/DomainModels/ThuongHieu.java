/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author ADMIN
 */
public class ThuongHieu {
    private String id;
    private String Ma;
    private String TenThuongHieu;

    public ThuongHieu() {
    }

    public ThuongHieu(String id, String Ma, String TenThuongHieu) {
        this.id = id;
        this.Ma = Ma;
        this.TenThuongHieu = TenThuongHieu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public String getTenThuongHieu() {
        return TenThuongHieu;
    }

    public void setTenThuongHieu(String TenThuongHieu) {
        this.TenThuongHieu = TenThuongHieu;
    }
    
    
}
