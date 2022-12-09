/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.math.BigDecimal;

/**
 *
 * @author tuane_nluzcuo
 */
public class KhuyenMaiChiTiet {

    private String id;
    private String idSanPham;
    private String idGiamGia;
    private BigDecimal donGia;
    private BigDecimal soTienConLai;
    private int trangThai;

    public KhuyenMaiChiTiet() {
    }

    public KhuyenMaiChiTiet(String id, String idSanPham, String idGiamGia, BigDecimal donGia, BigDecimal soTienConLai, int trangThai) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.idGiamGia = idGiamGia;
        this.donGia = donGia;
        this.soTienConLai = soTienConLai;
        this.trangThai = trangThai;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public void setIdGiamGia(String idGiamGia) {
        this.idGiamGia = idGiamGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public void setSoTienConLai(BigDecimal soTienConLai) {
        this.soTienConLai = soTienConLai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public String getIdGiamGia() {
        return idGiamGia;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public BigDecimal getSoTienConLai() {
        return soTienConLai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    @Override
    public String toString() {
        return "KhuyenMaiChiTiet{" + "id=" + id + ", idSanPham=" + idSanPham + ", idGiamGia=" + idGiamGia + ", donGia=" + donGia + ", soTienConLai=" + soTienConLai + ", trangThai=" + trangThai + '}';
    }
    
    public String trangThaiString (){
        if (trangThai == 1) {
            return "Đang Áp Dụng";
        } else if (trangThai == 0) {
            return "Chờ Áp Dụng";
        }
        else{
            return "Huỷ Áp Dụng";
        }
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj instanceof KhuyenMaiChiTiet) {
            KhuyenMaiChiTiet temp = (KhuyenMaiChiTiet) obj;
            
            if (this.id.equals(temp.id) && this.idSanPham.equals(temp.idSanPham) && this.idGiamGia.equals(temp.idGiamGia) && this.donGia.equals(temp.donGia) && this.soTienConLai.equals(temp.soTienConLai) ) {
                return true;
            }
        }
        return false;
    }

    
    
    @Override
    public int hashCode() {
        return  this.id.hashCode() + this.idGiamGia.hashCode() + this.idSanPham.hashCode() + this.donGia.hashCode() + this.soTienConLai.hashCode();
    }
    
    
}
