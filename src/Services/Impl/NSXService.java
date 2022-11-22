/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import Models.NSX;
import Repository.Impl.NSXRepository;
import Services.INSXService;
import ViewModel.QLNSX;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC- ASUS
 */
public class NSXService implements INSXService {

    public NSXRepository nSXRepository;

    public NSXService() {
        this.nSXRepository = new NSXRepository();
    }

    @Override
    public List<QLNSX> getAll() {
        List<QLNSX> ds = new ArrayList<>();

        List<NSX> list = this.nSXRepository.getAll();

        for (NSX nsx : list) {

            QLNSX dsnsx = new QLNSX(nsx.getIdNSX(), nsx.getMaNSX(), nsx.getTenNSX());
            ds.add(dsnsx);
        }
        return ds;
    }

    @Override
    public String addCbbNSX(NSX nsx) {
        boolean add = nSXRepository.addCbbNSX(nsx);
        if (add) {
            return "Thanh cong";
        }
        return "That bai";
    }

    @Override
    public void insert(QLNSX qlNSX) {
        NSX nsx = new NSX(qlNSX.getId(), qlNSX.getMa(),
                qlNSX.getTen());

        nSXRepository.insert(nsx);
    }

    @Override
    public void update(QLNSX qlNSX) {
        NSX nsx = new NSX(qlNSX.getId(), qlNSX.getMa(),
                qlNSX.getTen());

        nSXRepository.update(nsx);
    }

    @Override
    public void delete(QLNSX qlNSX) {
        NSX nsx = new NSX(qlNSX.getId(), qlNSX.getMa(),
                qlNSX.getTen());
        this.nSXRepository.delete(nsx);
    }

}
