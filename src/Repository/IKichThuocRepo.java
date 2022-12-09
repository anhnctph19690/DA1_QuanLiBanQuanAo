/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import DomainModels.KichThuoc;
import java.util.ArrayList;

/**
 *
 * @author tuane_nluzcuo
 */
public interface IKichThuocRepo {

    public ArrayList<KichThuoc> getAllsKichThuoc();

    public String getIDBySize(String tenSize);

    KichThuoc addCbb(String name);
}
