/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import ViewModel.QLThongKeHD;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IThongKeHdReposittory {
    List<QLThongKeHD> getALLThongKeHDs();
    
    public List<QLThongKeHD> thongKeALL(String thongKeTheo, String SapXepTheo);
}
