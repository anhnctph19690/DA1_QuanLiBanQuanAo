/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import Models.MauSac;
import java.util.ArrayList;

/**
 *
 * @author tuane_nluzcuo
 */
public interface IMauSacRepository {
    public String getIdMauSac(String ID);
    public ArrayList<MauSac> getAllsMauSac();
    boolean addCbbMauSac(MauSac ms);
    
}
