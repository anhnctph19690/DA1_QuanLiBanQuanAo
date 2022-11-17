/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicer.Impl;

import Models.NSX;
import Repository.Impl.NSXRepository;
import Servicer.INSXService;
import ViewModel.QLNSX;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public class NSXService implements INSXService{
    public NSXRepository nSXRepository ;
    
    public NSXService(){
        this.nSXRepository = new NSXRepository();
    }
    
    
    @Override
    public List<QLNSX> getAll() {
         List<QLNSX> ds = new ArrayList<>();
        
       List<NSX> list = this.nSXRepository.getAll();
       
        for (NSX nsx : list) {
            QLNSX dsnsx = new QLNSX(nsx.getMaNSX());
            
            ds.add(dsnsx);
        }
        return ds;
    }
    
    
}
