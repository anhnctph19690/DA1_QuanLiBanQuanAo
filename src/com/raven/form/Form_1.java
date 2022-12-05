
package com.raven.form;

import DomainModels.HoaDon;
import Services.Impl.ChiTietSanPhamService;
import Services.Impl.HoaDonChiTietService;
import Services.Impl.HoaDonService;
import Services.Impl.ThongKeHdService;
import ViewModel.QLThongKe;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class Form_1 extends javax.swing.JPanel {
//    private HoaDonService hoaDonService;
//    private HoaDonChiTietService hoaDonChiTietService;
//    private ChiTietSanPhamService chiTietSanPhamService;
//    private ThongKeHdService thongKeHdService;
//    private List<HoaDon> hoadon;
//    private DefaultTableModel model;
//    
//    public Form_1() {
//        initComponents();
//        thongKeHdService = new ThongKeHdService();
//        hoaDonService = new HoaDonService();
//        chiTietSanPhamService = new ChiTietSanPhamService();
//        hoaDonChiTietService = new HoaDonChiTietService();
//        loadTable("SoLuong", "ASC");
//        hienthithongkehoadon();
//        hienthithongkeSP();
//        doanhthu();
//    }   
//    public void hienthithongkehoadon() {
//        int soluonghoadonCTT = hoaDonService.demSoLuongHoaDonCTT();
//        int soluonghoadonDTT = hoaDonService.demSoLuongHoaDonDTT();
//        int tonghoadon = hoaDonService.Tonghoadon();
//
//        String HDCTT = String.valueOf(soluonghoadonCTT);
//        String HDDTT = String.valueOf(soluonghoadonDTT);
//        String tonghd = String.valueOf(tonghoadon);
//
//        lbHoaDonCTT.setText(HDCTT);
//        lbHoadonDaTT.setText(HDDTT);
//        lbTongHD.setText(tonghd);
//
//    }
//    public void hienthithongkeSP() {
//        int soLSPCH = chiTietSanPhamService.demSoLuongSPCH();
//        int soLSPHH = chiTietSanPhamService.demSoLuongSPHH();
//        int tongSP = chiTietSanPhamService.TongSP();
//
//        String SPCH = String.valueOf(soLSPCH);
//        String SPHH = String.valueOf(soLSPHH);
//        String tongsp = String.valueOf(tongSP);
//        lbSPCH1.setText(SPCH);
//        lbSPHH1.setText(SPHH);
//        lbTongSP1.setText(tongsp);
//    }
//
//    public void doanhthu() {
//
//        int tongDoanhThuNam = hoaDonChiTietService.doanhthu();
//        int tongDoanhThuNgay = hoaDonService.doanhThuNgay();
//        int tongDoanhThuQuy = hoaDonService.doanhThuQuy();
//        
//        
//        String doanhthuNam = String.valueOf(tongDoanhThuNam);
//        String doanhthuNgay = String.valueOf(tongDoanhThuNgay);
//        String doanhthuQuy = String.valueOf(tongDoanhThuQuy);
//        
//
//        lbdoanhthuNam.setText(doanhthuNam + " VND");
//        lbdoanhthuNgay.setText(doanhthuNgay + " VND");
//        lbdoanhthuQuy.setText(doanhthuQuy + " VND");
//        
//    }
//
//    public void loadTable(String thongKeTheo, String sapXepTheo) {
//
//        DefaultTableModel dtm = (DefaultTableModel) tbCTSP4.getModel();
//        dtm.setRowCount(0);
//        int stt = 1;
//        for (QLThongKe QLtk : this.chiTietSanPhamService.thongKeALL(thongKeTheo, sapXepTheo)) {
//            Object rowData[] = {
//                stt++,
//                QLtk.getMaSP(),
//                QLtk.getTenSP(),
//                QLtk.getChatLieu(),
//                QLtk.getMauSac(),
//                QLtk.getKichThuoc(),
//                QLtk.getThuongHieu(),
//                QLtk.getNsx(),
//                QLtk.getSoLuong(),
//                QLtk.getGiaNhap(),
//                QLtk.getGiaBan(),};
//            dtm.addRow(rowData);
//        }
//
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbHoaDonCTT = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbHoadonDaTT = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbTongHD = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbTongSP3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbSPCH3 = new javax.swing.JLabel();
        lbSPHH3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbdoanhthuNam = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbdoanhthu1 = new javax.swing.JLabel();
        lbdoanhthu2 = new javax.swing.JLabel();
        lbdoanhthu3 = new javax.swing.JLabel();
        lbdoanhthuNgay = new javax.swing.JLabel();
        lbdoanhthu5 = new javax.swing.JLabel();
        lbdoanhthu6 = new javax.swing.JLabel();
        lbdoanhthuQuy = new javax.swing.JLabel();
        lbdoanhthu8 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbCTSP4 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        radioGiaBan4 = new javax.swing.JRadioButton();
        radioGiaNhap4 = new javax.swing.JRadioButton();
        radioSoLuong4 = new javax.swing.JRadioButton();
        cbbSapXep4 = new javax.swing.JComboBox<>();
        btnThoat = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbHoaDonCTT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbHoaDonCTT.setForeground(new java.awt.Color(255, 255, 255));
        lbHoaDonCTT.setText("0");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tổng Hóa Đơn ");

        lbHoadonDaTT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbHoadonDaTT.setForeground(new java.awt.Color(255, 255, 255));
        lbHoadonDaTT.setText("0");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Đã Thanh Toán");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Chưa Thanh Toán");

        lbTongHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTongHD.setForeground(new java.awt.Color(255, 255, 255));
        lbTongHD.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoaDonCTT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoadonDaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbTongHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbHoadonDaTT))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoaDonCTT))
                .addGap(16, 16, 16))
        );

        jPanel9.setBackground(new java.awt.Color(204, 153, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setForeground(new java.awt.Color(255, 255, 255));

        lbTongSP3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTongSP3.setForeground(new java.awt.Color(255, 255, 255));
        lbTongSP3.setText("0");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Số Lượng ");

        lbSPCH3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSPCH3.setForeground(new java.awt.Color(255, 255, 255));
        lbSPCH3.setText("0");

        lbSPHH3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSPHH3.setForeground(new java.awt.Color(255, 255, 255));
        lbSPHH3.setText("0");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Sản Phẩm Còn Hàng ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Sản Phẩm Hết Hàng ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbSPHH3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbSPCH3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTongSP3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTongSP3)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSPCH3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSPHH3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbdoanhthuNam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbdoanhthuNam.setForeground(new java.awt.Color(255, 255, 255));
        lbdoanhthuNam.setText("1.000.000.000");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Doanh Thu");

        lbdoanhthu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbdoanhthu1.setForeground(new java.awt.Color(255, 255, 255));
        lbdoanhthu1.setText("Theo năm");

        lbdoanhthu2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbdoanhthu2.setForeground(new java.awt.Color(255, 255, 255));
        lbdoanhthu2.setText("VND");

        lbdoanhthu3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbdoanhthu3.setForeground(new java.awt.Color(255, 255, 255));
        lbdoanhthu3.setText("Theo Quý");

        lbdoanhthuNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbdoanhthuNgay.setForeground(new java.awt.Color(255, 255, 255));
        lbdoanhthuNgay.setText("2.500.000");

        lbdoanhthu5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbdoanhthu5.setForeground(new java.awt.Color(255, 255, 255));
        lbdoanhthu5.setText("VND");

        lbdoanhthu6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbdoanhthu6.setForeground(new java.awt.Color(255, 255, 255));
        lbdoanhthu6.setText("Theo Ngày");

        lbdoanhthuQuy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbdoanhthuQuy.setForeground(new java.awt.Color(255, 255, 255));
        lbdoanhthuQuy.setText("250.000.000");

        lbdoanhthu8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbdoanhthu8.setForeground(new java.awt.Color(255, 255, 255));
        lbdoanhthu8.setText("VND");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbdoanhthu1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(131, 131, 131))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbdoanhthu2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbdoanhthu3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbdoanhthu6, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(121, 121, 121)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbdoanhthuNam, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbdoanhthuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbdoanhthu5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbdoanhthuQuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbdoanhthu8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbdoanhthu1)
                    .addComponent(lbdoanhthu2)
                    .addComponent(lbdoanhthuNam, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbdoanhthu3)
                    .addComponent(lbdoanhthuQuy)
                    .addComponent(lbdoanhthu8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbdoanhthu6)
                    .addComponent(lbdoanhthuNgay)
                    .addComponent(lbdoanhthu5))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 153));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng Thống Kê Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbCTSP4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Chất Liệu", "Màu sắc ", "Kích Thước", "Thương Hiệu", "NSX", "Số Lượng Mua", "Giá Nhập", "Giá Bán"
            }
        ));
        jScrollPane5.setViewportView(tbCTSP4);

        jPanel6.setBackground(new java.awt.Color(51, 0, 255));

        radioGiaBan4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        radioGiaBan4.setForeground(new java.awt.Color(255, 255, 255));
        radioGiaBan4.setText("Giá Bán ");
        radioGiaBan4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioGiaBan4radioGiaBanMouseClicked(evt);
            }
        });
        radioGiaBan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioGiaBan4radioGiaBanActionPerformed(evt);
            }
        });

        radioGiaNhap4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        radioGiaNhap4.setForeground(new java.awt.Color(255, 255, 255));
        radioGiaNhap4.setText("Giá Nhập");
        radioGiaNhap4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioGiaNhap4radioGiaNhapMouseClicked(evt);
            }
        });

        radioSoLuong4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        radioSoLuong4.setForeground(new java.awt.Color(255, 255, 255));
        radioSoLuong4.setText("Số Lượng");
        radioSoLuong4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioSoLuong4radioSoLuongMouseClicked(evt);
            }
        });
        radioSoLuong4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSoLuong4radioSoLuongActionPerformed(evt);
            }
        });

        cbbSapXep4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbSapXep4.setForeground(new java.awt.Color(255, 255, 255));
        cbbSapXep4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tăng Dần", "Giảm Dần" }));
        cbbSapXep4.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cbbSapXep4cbbSapXepAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        cbbSapXep4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSapXep4cbbSapXepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioSoLuong4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(radioGiaNhap4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(radioGiaBan4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSapXep4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioGiaNhap4)
                    .addComponent(radioGiaBan4)
                    .addComponent(radioSoLuong4)
                    .addComponent(cbbSapXep4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1172, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Thống Kê", jPanel16);

        btnThoat.setBackground(new java.awt.Color(255, 0, 51));
        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setText("EXIT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void radioGiaBan4radioGiaBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioGiaBan4radioGiaBanMouseClicked

    }//GEN-LAST:event_radioGiaBan4radioGiaBanMouseClicked

    private void radioGiaBan4radioGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioGiaBan4radioGiaBanActionPerformed
//        String sapXep = "";
//        if (cbbSapXep4.getSelectedItem().equals("Giảm Dần")) {
//            sapXep = "DESC";
//        } else {
//            sapXep = "ASC";
//        }
//        loadTable("GiaBan", sapXep);
    }//GEN-LAST:event_radioGiaBan4radioGiaBanActionPerformed

    private void radioGiaNhap4radioGiaNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioGiaNhap4radioGiaNhapMouseClicked
//        String sapXep = "";
//
//        if (cbbSapXep4.getSelectedItem().equals("Giảm Dần")) {
//            sapXep = "DESC";
//        } else {
//            sapXep = "ASC";
//        }
//        loadTable("GiaNhap", sapXep);
    }//GEN-LAST:event_radioGiaNhap4radioGiaNhapMouseClicked

    private void radioSoLuong4radioSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioSoLuong4radioSoLuongMouseClicked

    }//GEN-LAST:event_radioSoLuong4radioSoLuongMouseClicked

    private void radioSoLuong4radioSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSoLuong4radioSoLuongActionPerformed
//        String sapXep = "";
//        if (cbbSapXep4.getSelectedItem().equals("Giảm Dần")) {
//            sapXep = "DESC";
//        } else {
//            sapXep = "ASC";
//        }
//        loadTable("GiaBan", sapXep);
    }//GEN-LAST:event_radioSoLuong4radioSoLuongActionPerformed

    private void cbbSapXep4cbbSapXepAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cbbSapXep4cbbSapXepAncestorAdded
        
    }//GEN-LAST:event_cbbSapXep4cbbSapXepAncestorAdded

    private void cbbSapXep4cbbSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSapXep4cbbSapXepActionPerformed

//        if (radioSoLuong4.isSelected() && cbbSapXep4.getSelectedItem().equals("Giảm Dần")) {
//            loadTable("SoLuong", "DESC");
//        } else if (radioGiaNhap4.isSelected() && cbbSapXep4.getSelectedItem().equals("Giảm Dần")) {
//            loadTable("GiaNhap", "DESC");
//        } else if (radioGiaBan4.isSelected() && cbbSapXep4.getSelectedItem().equals("Giảm Dần")) {
//            loadTable("GiaBan", "DESC");
//        }
//        if (radioSoLuong4.isSelected() && cbbSapXep4.getSelectedItem().equals("Tăng Dần")) {
//            loadTable("SoLuong", "ASC");
//        } else if (radioSoLuong4.isSelected() && cbbSapXep4.getSelectedItem().equals("Tăng Dần")) {
//            loadTable("GiaNhap", "ASC");
//        } else if (radioSoLuong4.isSelected() && cbbSapXep4.getSelectedItem().equals("Tăng Dần")) {
//            loadTable("GiaBan", "ASC");
//        }
    }//GEN-LAST:event_cbbSapXep4cbbSapXepActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnThoatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThoat;
    private javax.swing.JComboBox<String> cbbSapXep4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbHoaDonCTT;
    private javax.swing.JLabel lbHoadonDaTT;
    private javax.swing.JLabel lbSPCH1;
    private javax.swing.JLabel lbSPCH2;
    private javax.swing.JLabel lbSPCH3;
    private javax.swing.JLabel lbSPHH1;
    private javax.swing.JLabel lbSPHH2;
    private javax.swing.JLabel lbSPHH3;
    private javax.swing.JLabel lbTongHD;
    private javax.swing.JLabel lbTongSP1;
    private javax.swing.JLabel lbTongSP2;
    private javax.swing.JLabel lbTongSP3;
    private javax.swing.JLabel lbdoanhthu1;
    private javax.swing.JLabel lbdoanhthu2;
    private javax.swing.JLabel lbdoanhthu3;
    private javax.swing.JLabel lbdoanhthu5;
    private javax.swing.JLabel lbdoanhthu6;
    private javax.swing.JLabel lbdoanhthu8;
    private javax.swing.JLabel lbdoanhthuNam;
    private javax.swing.JLabel lbdoanhthuNgay;
    private javax.swing.JLabel lbdoanhthuQuy;
    private javax.swing.JRadioButton radioGiaBan4;
    private javax.swing.JRadioButton radioGiaNhap4;
    private javax.swing.JRadioButton radioSoLuong4;
    private javax.swing.JTable tbCTSP4;
    // End of variables declaration//GEN-END:variables
}
