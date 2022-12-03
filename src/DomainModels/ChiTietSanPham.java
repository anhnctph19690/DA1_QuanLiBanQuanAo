/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.math.BigDecimal;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ChiTietSanPham {

    private String id;
    private String idSanPham;
    private String idNhaSanXuat;
    private String idMauSac;
    private String idLoaiSanPham;
    private String idChatLieu;
    private String idThuongHieu;
    private String idSize;
    private int soLuong;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private String moTa;
    private int trangThai;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String id, String idSanPham, String idNhaSanXuat, String idMauSac, String idLoaiSanPham, String idChatLieu, String idThuongHieu, String idSize, int soLuong, BigDecimal giaNhap, BigDecimal giaBan, String moTa, int trangThai) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.idNhaSanXuat = idNhaSanXuat;
        this.idMauSac = idMauSac;
        this.idLoaiSanPham = idLoaiSanPham;
        this.idChatLieu = idChatLieu;
        this.idThuongHieu = idThuongHieu;
        this.idSize = idSize;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getIdNhaSanXuat() {
        return idNhaSanXuat;
    }

    public void setIdNhaSanXuat(String idNhaSanXuat) {
        this.idNhaSanXuat = idNhaSanXuat;
    }

    public String getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(String idMauSac) {
        this.idMauSac = idMauSac;
    }

    public String getIdLoaiSanPham() {
        return idLoaiSanPham;
    }

    public void setIdLoaiSanPham(String idLoaiSanPham) {
        this.idLoaiSanPham = idLoaiSanPham;
    }

    public String getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(String idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public String getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(String idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getIdSize() {
        return idSize;
    }

    public void setIdSize(String idSize) {
        this.idSize = idSize;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "ChiTietSanPham{" + "id=" + id + ", idSanPham=" + idSanPham + ", idNhaSanXuat=" + idNhaSanXuat + ", idMauSac=" + idMauSac + ", idLoaiSanPham=" + idLoaiSanPham + ", idChatLieu=" + idChatLieu + ", idThuongHieu=" + idThuongHieu + ", idSize=" + idSize + ", soLuong=" + soLuong + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", moTa=" + moTa + ", trangThai=" + trangThai + '}';
    }

    
    
}
