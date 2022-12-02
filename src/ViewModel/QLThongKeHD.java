/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class QLThongKeHD {
    private Integer stt;
    private String mahd;
    private Date NgayTao;
    private String tennv;
    private Integer tongslsp;
    private Double tongtien;

    public QLThongKeHD() {
    }

    public QLThongKeHD(Integer stt, String mahd, Date NgayTao, String tennv) {
        this.stt = stt;
        this.mahd = mahd;
        this.NgayTao = NgayTao;
        this.tennv = tennv;
    }
    
    public QLThongKeHD(Integer stt, String mahd, Date NgayTao, String tennv, Integer tongslsp, Double tongtien) {
        this.stt = stt;
        this.mahd = mahd;
        this.NgayTao = NgayTao;
        this.tennv = tennv;
        this.tongslsp = tongslsp;
        this.tongtien = tongtien;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public Integer getTongslsp() {
        return tongslsp;
    }

    public void setTongslsp(Integer tongslsp) {
        this.tongslsp = tongslsp;
    }

    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }
    
    
}
