/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author tuane_nluzcuo
 */
public class KichThuoc {
    private String IdSize;
    private String soSize;

    public KichThuoc() {
    }

    public KichThuoc(String IdSize, String soSize) {
        this.IdSize = IdSize;
        this.soSize = soSize;
    }

    public String getIdSize() {
        return IdSize;
    }

    public String getSoSize() {
        return soSize;
    }

    public void setIdSize(String IdSize) {
        this.IdSize = IdSize;
    }

    public void setSoSize(String soSize) {
        this.soSize = soSize;
    }
    
    
}
