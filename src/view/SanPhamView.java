/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Models.ChiTietSanPham;
import Models.LoaiSanPham;
import Models.MauSac;
import Models.SanPham;
import Repository.Impl.MauSacRepository;
import Services.IChiTietSanPhamService;
import Services.ISanPhamService;
import Services.Impl.ChatLieuServices;
import Services.Impl.ChiTietSanPhamService;
import Services.Impl.KichThuocService;
import Services.Impl.LoaiSanPhamService;
import Services.Impl.NSXService;
import Services.Impl.SanPhamService;
import Services.Impl.ThuongHieuService;
import ViewModel.QLChatLieu;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLKichThuoc;
import ViewModel.QLLoaiSanPham;
import ViewModel.QLNSX;
import ViewModel.QLThuongHieu;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tuane_nluzcuo
 */
public class SanPhamView extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienView
     */
    private static int index = 1;

    private List<QLChatLieu> listqlcl = new ArrayList<>();
    private List<QLThuongHieu> listqlTH = new ArrayList<>();
    private List<QLNSX> listnsx = new ArrayList<>();
    private List<QLKichThuoc> listSize = new ArrayList<>();
    private List<QLChiTietSanPham> _listChiTietSP = new ArrayList<>();
    private List<QLLoaiSanPham> listLSP = new ArrayList<>();
    private List<MauSac> listMauSac = new ArrayList<>();

    private DefaultComboBoxModel dcbChatLieu;
    private DefaultComboBoxModel dcbThuongHieu;
    private DefaultComboBoxModel dcbSize;
    private DefaultTableModel dtmChiTietSanPham;
    private DefaultComboBoxModel dmtLSP;
    private DefaultComboBoxModel dmtMauSac;

    private IChiTietSanPhamService _iChiTietSanPhamService = new ChiTietSanPhamService();
    private ChatLieuServices chatLieuservice = new ChatLieuServices();
    private NSXService nSXService = new NSXService();
    private ThuongHieuService thuongHieuServicer = new ThuongHieuService();
    private KichThuocService KTServicer = new KichThuocService();
    private LoaiSanPhamService loaiSPhamService = new LoaiSanPhamService();
    private ISanPhamService _iSanPhamService = new SanPhamService();

    private MauSacRepository mauSacRepository = new MauSacRepository();

    public SanPhamView() {
        initComponents();

        loadChatLieu();
        LoadNSX();
        loadThuongHieu();
        loadSize();
        LoadLoaiSP();
        LoadMausac();

        _listChiTietSP = _iChiTietSanPhamService.getAll();
        showDataTableChiTietSanPham(_listChiTietSP);
    }

    public void loadSize() {
        dcbSize = (DefaultComboBoxModel) cbbSize.getModel();
        listSize = KTServicer.getAllsKichThuoc();
        for (QLKichThuoc x : listSize) {
            dcbSize.addElement(x.getSoSize());
        }
    }

    public void loadThuongHieu() {
        dcbThuongHieu = (DefaultComboBoxModel) CbbThuongHieu.getModel();
        listqlTH = thuongHieuServicer.getAll();
        for (QLThuongHieu x : listqlTH) {
            dcbThuongHieu.addElement(x.getTenTH());
        }
    }

    private void loadChatLieu() {

        dcbChatLieu = (DefaultComboBoxModel) cbcchatLieu.getModel();
        listqlcl = chatLieuservice.getAll();
        for (QLChatLieu x : listqlcl) {
            dcbChatLieu.addElement(x.getTenChatLieu());
        }

    }

    private void LoadNSX() {
        DefaultComboBoxModel dcb = (DefaultComboBoxModel) CbbNSX.getModel();
        listnsx = nSXService.getAll();
        for (QLNSX nsx : listnsx) {
            dcb.addElement(nsx.getTen());
        }
    }

    private void LoadLoaiSP() {
        dmtLSP = (DefaultComboBoxModel) cbbLSP.getModel();
        listLSP = loaiSPhamService.getAll();
        for (QLLoaiSanPham lsp : listLSP) {
            dmtLSP.addElement(lsp.getTenLSP());
        }
    }

    private void LoadMausac() {
        dmtMauSac = (DefaultComboBoxModel) cboMauSac.getModel();
        listMauSac = this.mauSacRepository.getAllsMauSac();
        for (MauSac o : listMauSac) {
            dmtMauSac.addElement(o.getTenMauSac());
        }
    }

    private void showDataTableChiTietSanPham(List<QLChiTietSanPham> list) {
        dtmChiTietSanPham = (DefaultTableModel) tableChiTietSP.getModel();
        dtmChiTietSanPham.setRowCount(0);
        _listChiTietSP.forEach(s -> dtmChiTietSanPham.addRow(new Object[]{s.getMaSanPham(), s.getTenSanPham(), s.getTenNhaSanXuat(), s.getTenMauSac(), s.getTenLoai(), s.getTenChatLieu(), s.getTenThuongHieu(), s.getSoSize(), s.getSoLuongTonKho(), s.getGiaNhap(), s.getGiaBan(), s.getMoTa(), s.getTrangThai()}));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        radioConHang = new javax.swing.JRadioButton();
        radioHetHang = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMota = new javax.swing.JTextArea();
        cbbSize = new javax.swing.JComboBox<>();
        CbbThuongHieu = new javax.swing.JComboBox<>();
        cbcchatLieu = new javax.swing.JComboBox<>();
        CbbNSX = new javax.swing.JComboBox<>();
        cbbLSP = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        btnAddCbbSize = new javax.swing.JButton();
        btnAddCbbLoaiSP = new javax.swing.JButton();
        btnAddCbbChatLieu = new javax.swing.JButton();
        btnAddCbbMauSac = new javax.swing.JButton();
        btnAddCbbNsx = new javax.swing.JButton();
        btnAddCbbThuongHieu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableChiTietSP = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Thông Tin"));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã Sản Phẩm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Số Lượng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Giá Nhập");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Giá Bán");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Size");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Loại Sản Phẩm");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Chất Liệu");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Màu Sắc");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Nhà Sản Xuất");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Thương Hiệu");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Trạng Thái");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Mô Tả");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txtGiaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaNhapActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioConHang);
        radioConHang.setText("Còn Hàng");

        buttonGroup1.add(radioHetHang);
        radioHetHang.setText("Hết Hàng");

        txtMota.setColumns(20);
        txtMota.setRows(5);
        jScrollPane1.setViewportView(txtMota);

        cbbSize.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        cbbSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSizeActionPerformed(evt);
            }
        });

        CbbThuongHieu.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N

        cbcchatLieu.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        cbcchatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcchatLieuActionPerformed(evt);
            }
        });

        CbbNSX.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        CbbNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbbNSXActionPerformed(evt);
            }
        });

        cbbLSP.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N

        cboMauSac.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N

        btnAddCbbSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icon/Add.png"))); // NOI18N

        btnAddCbbLoaiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icon/Add.png"))); // NOI18N

        btnAddCbbChatLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icon/Add.png"))); // NOI18N

        btnAddCbbMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icon/Add.png"))); // NOI18N

        btnAddCbbNsx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icon/Add.png"))); // NOI18N

        btnAddCbbThuongHieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icon/Add.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbLSP, 0, 137, Short.MAX_VALUE)
                            .addComponent(cbbSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddCbbSize)
                            .addComponent(btnAddCbbLoaiSP))))
                .addGap(93, 93, 93)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radioConHang)
                                .addGap(37, 37, 37)
                                .addComponent(radioHetHang)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(28, 28, 28)
                                .addComponent(CbbThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(CbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbcchatLieu, 0, 170, Short.MAX_VALUE)
                                    .addComponent(cboMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddCbbMauSac, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAddCbbChatLieu, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAddCbbNsx, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAddCbbThuongHieu, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(48, 48, 48))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddCbbChatLieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(cbcchatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddCbbMauSac))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(CbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(CbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAddCbbNsx)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(btnAddCbbThuongHieu)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(radioConHang)
                            .addComponent(radioHetHang))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(cbbSize)
                            .addComponent(btnAddCbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbbLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddCbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Sửa");

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setText("Làm Mới");

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setText("Xoá");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addGap(44, 44, 44))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(38, 38, 38)
                .addComponent(jButton2)
                .addGap(32, 32, 32)
                .addComponent(jButton4)
                .addGap(42, 42, 42)
                .addComponent(jButton3)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tableChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Nhà Sản Xuất", "Màu Sắc", "Loại Sản Phẩm", "Chất Liệu", "Thương Hiệu", "Size", "Số Lượng Tồn Kho", "Giá Nhập", "Giá Bán", "Mô Tả", "Trang Thái"
            }
        ));
        jScrollPane3.setViewportView(tableChiTietSP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtGiaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaNhapActionPerformed

    private void cbcchatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcchatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbcchatLieuActionPerformed

    private void cbbSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSizeActionPerformed

    private void CbbNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbbNSXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbbNSXActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        SanPham newSanPham = new SanPham();
        newSanPham.setMaSP(_iChiTietSanPhamService.autoMaSanPham());
        newSanPham.setTenSP(txtTenSP.getText());
        _iSanPhamService.add(newSanPham);

        SanPham getIdSanPham = _iSanPhamService.getOne(newSanPham.getMaSP());

        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setIdSanPham(getIdSanPham.getIdSanPham());
        ctsp.setIdNhaSanXuat("7148A7A0-B6D5-4391-B869-6722C16F3A00");
        ctsp.setIdMauSac("4EB92ECE-823F-4CC6-B0CA-12CD149085A1");
        ctsp.setIdLoaiSanPham("9B050835-A580-4517-B9AC-38356925ABC5");
        ctsp.setIdChatLieu("F4B9662E-444C-4736-8FFD-0079BEB402FC");
        ctsp.setIdThuongHieu("E5A2228F-39F6-4E92-975F-1779A7029492");
        ctsp.setIdSize("7BA55748-FE9E-4CB2-B45E-0581068A6195");
        ctsp.setSoLuong(100);
        ctsp.setTrangThai(1);
        ctsp.setGiaNhap(new BigDecimal(200000));
        ctsp.setGiaBan(new BigDecimal(500000));
        ctsp.setMoTa("New");

//        _iSanPhamService.updateTenSanPham(txtTenSP.getText(), getIdSanPham.getIdSanPham());
        JOptionPane.showMessageDialog(this, _iChiTietSanPhamService.add(ctsp));
        _listChiTietSP = _iChiTietSanPhamService.getAll();
        showDataTableChiTietSanPham(_listChiTietSP);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CbbNSX;
    private javax.swing.JComboBox<String> CbbThuongHieu;
    private javax.swing.JButton btnAddCbbChatLieu;
    private javax.swing.JButton btnAddCbbLoaiSP;
    private javax.swing.JButton btnAddCbbMauSac;
    private javax.swing.JButton btnAddCbbNsx;
    private javax.swing.JButton btnAddCbbSize;
    private javax.swing.JButton btnAddCbbThuongHieu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbLSP;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbcchatLieu;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JRadioButton radioConHang;
    private javax.swing.JRadioButton radioHetHang;
    private javax.swing.JTable tableChiTietSP;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextArea txtMota;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
