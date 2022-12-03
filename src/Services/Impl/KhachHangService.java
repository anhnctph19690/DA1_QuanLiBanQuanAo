/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModels.KhachHang;
import Repository.IKhachHangRepository;
import Repository.Impl.KhachHangRepository;
import Services.IKhachHangService;
import ViewModel.QLKhachHang;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class KhachHangService implements IKhachHangService {

    private IKhachHangRepository khachHangRepo = new KhachHangRepository();

    @Override
    public KhachHang add(KhachHang kh) {
        return khachHangRepo.add(kh);
    }

    @Override
    public List<QLKhachHang> getAll() {
        return khachHangRepo.getAll();
    }

}
