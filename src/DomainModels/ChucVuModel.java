package DomainModels;

public class ChucVuModel {

    private String idCV;
    private String maChucVu;
    private String ChucVu;
    private Integer trangThai;

    public ChucVuModel() {
    }

    public ChucVuModel(String idCV, String maChucVu, String ChucVu, Integer trangThai) {
        this.idCV = idCV;
        this.maChucVu = maChucVu;
        this.ChucVu = ChucVu;
        this.trangThai = trangThai;
    }

    public String getIdCV() {
        return idCV;
    }

    public void setIdCV(String idCV) {
        this.idCV = idCV;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
