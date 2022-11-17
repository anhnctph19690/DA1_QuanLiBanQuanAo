/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author ADMIN
 */
public class QLChatLieu {
    private String ma;
 

    public QLChatLieu() {
    }

    public QLChatLieu(String ma) {
        this.ma = ma;
       
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    @Override
    public String toString() {
        return "QLChatLieu{" + "ma=" + ma + '}';
    }
    
    public Object[] todataRow(){
        return new Object[]{ma};
    }
    
    
}
