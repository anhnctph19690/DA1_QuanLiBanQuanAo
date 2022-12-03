/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModels.NSX;
import ViewModel.QLNSX;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public interface INSXRepository {

    public List<NSX> getAll();

    public String getIDByNSX(String tenNSX);
    
    NSX addCbb(String name);
}
