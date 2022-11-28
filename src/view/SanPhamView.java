/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Models.ChiTietSanPham;
import Models.LoaiSanPham;
import Models.MauSac;
import Models.SanPham;
import Repository.Impl.ChatLieuRepository;
import Repository.Impl.KichThuocRepo;
import Repository.Impl.LoaiSanPhamRepository;
import Repository.Impl.MauSacRepository;
import Repository.Impl.NSXRepository;
import Repository.Impl.SanPhamRepository;
import Repository.Impl.ThuongHieuRepository;
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
    private SanPhamService sanPhamService = new SanPhamService();

    private MauSacRepository mauSacRepository = new MauSacRepository();
    private KichThuocRepo kichThuocRepo = new KichThuocRepo();
    private LoaiSanPhamRepository loaiSanPhamRepository = new LoaiSanPhamRepository();
    private ChatLieuRepository chatLieuRepository = new ChatLieuRepository();
    private NSXRepository nSXRepository = new NSXRepository();
    private ThuongHieuRepository hieuRepository = new ThuongHieuRepository();
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private ThuongHieuRepository thuongHieuRepository = new ThuongHieuRepository();

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
        dcbSize.removeAllElements();
        listSize = KTServicer.getAllsKichThuoc();
        for (QLKichThuoc x : listSize) {
            dcbSize.addElement(x.getSoSize());
        }
    }

    public void loadThuongHieu() {
        dcbThuongHieu = (DefaultComboBoxModel) CbbThuongHieu.getModel();
        dcbThuongHieu.removeAllElements();;
        listqlTH = thuongHieuServicer.getAll();
        for (QLThuongHieu x : listqlTH) {
            dcbThuongHieu.addElement(x.getTenTH());
        }
    }

    private void loadChatLieu() {

        dcbChatLieu = (DefaultComboBoxModel) cbcchatLieu.getModel();
        dcbChatLieu.removeAllElements();
        listqlcl = chatLieuservice.getAll();
        for (QLChatLieu x : listqlcl) {
            dcbChatLieu.addElement(x.getTenChatLieu());
        }

    }

    private void LoadNSX() {
        DefaultComboBoxModel dcb = (DefaultComboBoxModel) CbbNSX.getModel();
        dcb.removeAllElements();
        listnsx = nSXService.getAll();
        for (QLNSX nsx : listnsx) {
            dcb.addElement(nsx.getTen());
        }
    }

    private void LoadLoaiSP() {
        dmtLSP = (DefaultComboBoxModel) cbbLSP.getModel();
        dmtLSP.removeAllElements();
        listLSP = loaiSPhamService.getAll();
        for (QLLoaiSanPham lsp : listLSP) {
            dmtLSP.addElement(lsp.getTenLSP());
        }
    }

    private void LoadMausac() {
        dmtMauSac = (DefaultComboBoxModel) cboMauSac.getModel();
        dmtMauSac.removeAllElements();
        listMauSac = this.mauSacRepository.getAllsMauSac();
        for (MauSac o : listMauSac) {
            dmtMauSac.addElement(o.getTenMauSac());
        }
    }

    private void showDataTableChiTietSanPham(List<QLChiTietSanPham> list) {
        dtmChiTietSanPham = (DefaultTableModel) tableChiTietSP.getModel();
        dtmChiTietSanPham.setRowCount(0);
        _listChiTietSP.forEach(s -> dtmChiTietSanPham.addRow(s.toDataRow()));
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
        jPanel4 = new javax.swing.JPanel();
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
        txtMaSP = new javax.swing.JTextField();
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
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableChiTietSP = new javax.swing.JTable();
        btnThoat = new javax.swing.JButton();

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
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()), "Thông Tin"));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

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

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
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
        radioConHang.setSelected(true);
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

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("+");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("+");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("+");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("+");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

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
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbLSP, 0, 137, Short.MAX_VALUE)
                            .addComponent(cbbSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(31, 31, 31)
                                .addComponent(CbbThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbcchatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addComponent(radioHetHang)))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(CbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(radioConHang)
                            .addComponent(radioHetHang))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(69, 69, 69))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cbcchatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton7)))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(cbbLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton8)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))
                                .addGap(176, 176, 176)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        btnThem.setBackground(new java.awt.Color(51, 51, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 51, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Làm Mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(51, 51, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Xoá");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(51, 51, 255));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Tìm Kiếm");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        tableChiTietSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Nhà Sản Xuất", "Màu Sắc", "Loại Sản Phẩm", "Chất Liệu", "Thương Hiệu", "Size", "Số Lượng Tồn Kho", "Giá Nhập", "Giá Bán", "Mô Tả", "Trang Thái"
            }
        ));
        jScrollPane3.setViewportView(tableChiTietSP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );

        btnThoat.setBackground(new java.awt.Color(255, 0, 0));
        btnThoat.setText("EXIT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(btnThoat))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

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


    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        SanPham newSanPham = _iSanPhamService.add(txtTenSP.getText());
        SanPham sanPham = _iSanPhamService.getOne(newSanPham.getMaSP());
        ChiTietSanPham ctsp = getChiTietSanPhamByForm();
        ctsp.setIdSanPham(sanPham.getIdSanPham());

        JOptionPane.showMessageDialog(this, _iChiTietSanPhamService.add(ctsp));
        _listChiTietSP = _iChiTietSanPhamService.getAll();
        showDataTableChiTietSanPham(_listChiTietSP);

    }//GEN-LAST:event_btnThemActionPerformed

    public ChiTietSanPham getChiTietSanPhamByForm() {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setIdSanPham(null);
        ctsp.setIdNhaSanXuat(nSXRepository.getIDByNSX(CbbNSX.getSelectedItem().toString()));
        ctsp.setIdMauSac(mauSacRepository.getIdMauSac(cboMauSac.getSelectedItem().toString()));
        ctsp.setIdLoaiSanPham(loaiSanPhamRepository.getIDByLoaiSP(cbbLSP.getSelectedItem().toString()));
        ctsp.setIdChatLieu(chatLieuRepository.getIDByChatLieu(cbcchatLieu.getSelectedItem().toString()));
        ctsp.setIdThuongHieu(thuongHieuRepository.getIDByThuongHieu(CbbThuongHieu.getSelectedItem().toString()));
        ctsp.setIdSize(kichThuocRepo.getIDBySize(cbbSize.getSelectedItem().toString()));
        ctsp.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        ctsp.setTrangThai(radioConHang.isSelected() == true ? 1 : 0);
        ctsp.setGiaNhap(new BigDecimal(txtGiaNhap.getText()));
        ctsp.setGiaBan(new BigDecimal(txtGiaBan.getText()));
        ctsp.setMoTa(txtMota.getText());
        return ctsp;
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int row = tableChiTietSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm");
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này không?");
            if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(this, "Đã hủy");
            } else {
                String maSanPham = dtmChiTietSanPham.getValueAt(row, 1).toString();
                SanPham sp = _iSanPhamService.getOne(maSanPham);
                if (_iChiTietSanPhamService.delete(sp.getIdSanPham())) {
                    JOptionPane.showMessageDialog(this, _iSanPhamService.delete(sp.getIdSanPham()));
                    _listChiTietSP = _iChiTietSanPhamService.getAll();
                    showDataTableChiTietSanPham(_listChiTietSP);
                }
            }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int row = tableChiTietSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm");
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa sản phẩm này không?");
            if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(this, "Đã hủy");
            } else {
                String maSanPham = dtmChiTietSanPham.getValueAt(row, 1).toString();
                SanPham sp = _iSanPhamService.getOne(maSanPham);
                ChiTietSanPham ctsp = getChiTietSanPhamByForm();
                ctsp.setIdSanPham(sp.getIdSanPham());
                if (_iChiTietSanPhamService.update(ctsp, ctsp.getIdSanPham())) {
                    JOptionPane.showMessageDialog(this, _iSanPhamService.update(txtTenSP.getText(), sp.getIdSanPham()));
                    _listChiTietSP = _iChiTietSanPhamService.getAll();
                    showDataTableChiTietSanPham(_listChiTietSP);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        System.exit(0);

    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:

    }//GEN-LAST:event_formWindowClosed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn trở lại màn hình chính !!!!", "Trờ lại màn hình chính", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (check == JOptionPane.YES_OPTION) {
            this.dispose();
            
        } 
    }//GEN-LAST:event_btnThoatActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String name = JOptionPane.showInputDialog(rootPane, "");
        chatLieuRepository.addCbb(name);
        loadChatLieu();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String name = JOptionPane.showInputDialog(rootPane, "");
        mauSacRepository.addCbb(name);
        LoadMausac();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String name = JOptionPane.showInputDialog(rootPane, "");
        nSXRepository.addCbb(name);
        LoadNSX();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String name = JOptionPane.showInputDialog(rootPane, "");
        thuongHieuRepository.addCbb(name);
        loadThuongHieu();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String name = JOptionPane.showInputDialog(rootPane, "");
        kichThuocRepo.addCbb(name);
        loadSize();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String name = JOptionPane.showInputDialog(rootPane, "");
        loaiSanPhamRepository.addCbb(name);
        LoadLoaiSP();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:     
        _listChiTietSP = new ArrayList<>();
        String name = txtTenSP.getText();
//            _listChiTietSP = _iChiTietSanPhamService.getSPByName(name);
        
        showDataTableChiTietSanPham(_listChiTietSP);
    }//GEN-LAST:event_jButton10ActionPerformed

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
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbLSP;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbcchatLieu;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton radioConHang;
    private javax.swing.JRadioButton radioHetHang;
    private javax.swing.JTable tableChiTietSP;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMota;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
