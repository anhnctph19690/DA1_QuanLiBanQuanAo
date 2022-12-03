/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author tuane_nluzcuo
 */
public class MauSac {
  private String IdmauSac;
  private String maMauSac;
  private String tenMauSac;

    public MauSac(String IdmauSac, String maMauSac, String tenMauSac) {
        this.IdmauSac = IdmauSac;
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
    }

    public MauSac() {
    }
    

    public String getIdmauSac() {
        return IdmauSac;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setIdmauSac(String IdmauSac) {
        this.IdmauSac = IdmauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }
  
  
  
}
