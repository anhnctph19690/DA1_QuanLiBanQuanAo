/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;
import Repository.Impl.ThongKeHdRepository;
import Repository.IThongKeHdReposittory;
import Services.IThongKeHdService;
import ViewModel.QLThongKeHD;
import ViewModel.QLThongKeHD;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKeHdService implements IThongKeHdService{
    private IThongKeHdReposittory IThongkerp = new ThongKeHdRepository();
    

    @Override
    public List<QLThongKeHD> getALLThongKeHDs() {
        return IThongkerp.getALLThongKeHDs();    
    }

    
    public List<QLThongKeHD> thongKeALL(String thongKeTheo, String SapXepTheo) {
        return IThongkerp.thongKeALL(thongKeTheo, SapXepTheo);
    }

    
}
    
