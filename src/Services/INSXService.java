/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Models.NSX;
import ViewModel.QLNSX;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public interface INSXService {
     public List<QLNSX> getAll();
     
     String addCbbNSX(NSX nsx);
}
