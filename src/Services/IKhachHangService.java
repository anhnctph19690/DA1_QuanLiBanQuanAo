/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.KhachHang;
import ViewModel.QLKhachHang;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface IKhachHangService {

    List<QLKhachHang> getAll();

    KhachHang add(KhachHang kh);
}
