/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

/**
 *
 * @author tuane_nluzcuo
 */
public class KichThuoc {
    private String IdSize;
    private String ma;
    private String soSize;

    public KichThuoc() {
    }

    public KichThuoc(String IdSize, String ma, String soSize) {
        this.IdSize = IdSize;
        this.ma = ma;
        this.soSize = soSize;
    }

    public KichThuoc(String IdSize, String soSize) {
        this.IdSize = IdSize;
        this.soSize = soSize;
    }
    

    public String getIdSize() {
        return IdSize;
    }

    public void setIdSize(String IdSize) {
        this.IdSize = IdSize;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getSoSize() {
        return soSize;
    }

    public void setSoSize(String soSize) {
        this.soSize = soSize;
    }

   

   
    
    
}
