/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import Models.MauSac;
import Repository.IMauSacRepository;
import Repository.Impl.MauSacRepository;
import Services.IMauSacSerVice;

/**
 *
 * @author ADMIN
 */
public class MauSacService implements IMauSacSerVice{
    private MauSacRepository mauSacRepo = new MauSacRepository();
    @Override
    public String addCbbMauSac(MauSac ms) {
         boolean add =mauSacRepo.addCbbMauSac(ms);
        if (add) {
            return "Thanh cong";
        }return "That bai";
    }
    
}
