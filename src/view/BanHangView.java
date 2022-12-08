/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import DomainModels.KhachHang;
import DomainModels.NhanVien;
import DomainModels.SanPham;
import Repository.IHoaDonChiTietRepository;
import Repository.Impl.ChiTietSanPhamRepository;
import Repository.Impl.HoaDonRepository;
import Repository.Impl.NhanVienRepository;
import Repository.Impl.SanPhamRepository;
import Services.IChiTietSanPhamService;
import Services.IHoaDonChiTietService;
import Services.IHoaDonService;
import Services.IKhachHangService;
import Services.Impl.ChiTietSanPhamService;
import Services.Impl.HoaDonChiTietService;
import Services.Impl.HoaDonService;
import Services.Impl.KhachHangService;
import Services.Impl.NhanVienServicer;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLHoaDon;
import ViewModel.QLHoaDonChiTiet;
import ViewModel.QLNhanVien;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Ultilities.DateTime;
import Ultilities.Validation;
import ViewModel.QLKhachHang;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultListModel;

/**
 *
 * @author PC- ASUS
 */
public class BanHangView extends javax.swing.JFrame implements Runnable, ThreadFactory {

    static QLNhanVien getEmployeeByDashBoard;
    /**
     * Creates new form From_BanHang
     */
    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private Map<QLChiTietSanPham, Integer> mapsProduct;

    private NhanVienRepository nvRepo;
    private DefaultListModel dlmSearchCustomer;
    private Validation validation;
    private List<QLKhachHang> _listKhachHang;
    private IKhachHangService _ikhacHangService;
    private NhanVienServicer nhanVienServicer;
    private IChiTietSanPhamService _iChiTietSanPhamService;
    private IHoaDonChiTietService _iHoaDonChiTietService;
    private IHoaDonService _iHoaDonService;
    private DefaultTableModel dtmGioHang;
    private DefaultTableModel dtmHoaDon;
    private DefaultTableModel dtmSanPham;
    private List<QLHoaDon> _listHoaDon;
    private List<QLHoaDonChiTiet> _listHoaDonChiTiet;
    private List<QLChiTietSanPham> _listChiTietSanPham;
    static BanHangView viewBanHang;
    private String hinhThucThanhToan = "";
    private Double tienThuaTotal;
    private Double tienKhachCanTra;
    private Double tienKhachDua;
    private Double tienThua1;
    private Double tienKhachCanTra1;
    private Double tienKhachDua1;

    private void showListHoaDonByCombobox() {
        int trangThaiHoaDon = cbbTrangThaiHoaDon.getSelectedIndex();
        _listHoaDon = _iHoaDonService.getAllHoaDonCho(trangThaiHoaDon);
        if (!_listHoaDon.isEmpty()) {
            if (trangThaiHoaDon == 0 || trangThaiHoaDon == 2) {
                showDataTableHoaDon(_listHoaDon);
                tableHoaDon.setRowSelectionInterval(0, 0);
//                _listChiTietSanPham = _iChiTietSanPhamService.getAll();
//                showDataTableSanPham(_listChiTietSanPham);
//                _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
//                showDataTableGioHang(_listHoaDonChiTiet);
                mapsProduct.clear();
            } else {
                showDataTableHoaDon(_listHoaDon);
                _listChiTietSanPham = _iChiTietSanPhamService.getAll();
                showDataTableSanPham(_listChiTietSanPham);
                _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
                showDataTableGioHang(_listHoaDonChiTiet);
                mapsProduct.clear();
            }
        } else {
            showDataTableHoaDon(_listHoaDon);
            _listChiTietSanPham = _iChiTietSanPhamService.getAll();
            showDataTableSanPham(_listChiTietSanPham);
            _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
            showDataTableGioHang(_listHoaDonChiTiet);
            mapsProduct.clear();
        }
    }

    private QLChiTietSanPham getProductByQr(String ma) {
        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
        for (QLChiTietSanPham ctsp : _listChiTietSanPham) {
            if (ctsp.getMaSanPham().equals(ma)) {
                return ctsp;
            }
        }
        return null;
    }

    public BanHangView() {
        viewBanHang = this;
        initComponents();
        initWebcam();
        nvRepo = new NhanVienRepository();
        dlmSearchCustomer = new DefaultListModel();
        mapsProduct = new HashMap<>();
        validation = new Validation();
        _ikhacHangService = new KhachHangService();
        nhanVienServicer = new NhanVienServicer();
        _iChiTietSanPhamService = new ChiTietSanPhamService();
        _iHoaDonChiTietService = new HoaDonChiTietService();
        _iHoaDonService = new HoaDonService();
        _listChiTietSanPham = new ArrayList<>();
        _listHoaDon = new ArrayList<>();
        _listHoaDonChiTiet = new ArrayList<>();
        _listKhachHang = new ArrayList<>();

        _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
        showDataTableHoaDon(_listHoaDon);

        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
        showDataTableSanPham(_listChiTietSanPham);

        listSearchKH.setModel(dlmSearchCustomer);

        menu.add(panelListSearchCustomer);

        setNameEmployee();
        loadTienThuaHoaDon();
        loadTienThuaDatHang();
        showListHoaDonByTabbedPane();
        setJDateChooserDefaultToday();
        cbTienMat.setSelected(true);
        txtTienChuyenKhoan.setEditable(false);
        if (!_listHoaDon.isEmpty()) {
            showListHoaDonByCombobox();
//            fillHoaDon(0);
        }
        onlyNumber();
    }

    private QLNhanVien getEmployeeByLogin() {
        if (getEmployeeByDashBoard != null) {
            QLNhanVien nv = getEmployeeByDashBoard;
            return nv;
        }
        return null;
    }

    private void setNameEmployee() {
        QLNhanVien nv = getEmployeeByLogin();
        if (getEmployeeByLogin() != null) {
            labelTenNguoiBan.setText(nv.getTenNV());
            labelTenNguoiBan1.setText(nv.getTenNV());
        }
    }

    private void setJDateChooserDefaultToday() {
        Date d = new Date();
        jDateChooser1.setDate(d);
    }

    private void onlyNumber() {
        txtTienKhachDua.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == VK_BACK_SPACE) {
                    txtTienKhachDua.setEditable(true);
                }
            }
        });
        txtTienCoc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == VK_BACK_SPACE) {
                    txtTienCoc.setEditable(true);
                }
            }
        });
        txtGiamGia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == VK_BACK_SPACE) {
                    txtGiamGia.setEditable(true);
                }
            }
        });
        txtGiamGia1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == VK_BACK_SPACE) {
                    txtGiamGia1.setEditable(true);
                }
            }
        });
    }

    private void clearThongTinHoaDon() {
        txtTenKhachHang.setText(null);
        txtSDT.setText(null);
        labelTongTienHang.setText("0");
        labelKhachCanTra.setText("0");
        txtGiamGia.setText("0");
        txtTienKhachDua.setText("0");
        labelTienThua.setText("0");
    }

    private void clearThongTinDonHang() {
        txtTenNguoiNhan.setText(null);
        txtSDT1.setText(null);
        txtDiaChi.setText(null);
        labelTongTienHang1.setText("0");
        labelKhachCanTra1.setText("0");
        txtGiamGia1.setText("0");
        txtTienShip.setText("0");
        txtTienCoc.setText("0");
        labelTienThua1.setText("0");
    }

    private void showListHoaDonByTabbedPane() {
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    int trangThaiHoaDon = cbbTrangThaiHoaDon.getSelectedIndex();
                    cbbTrangThaiHoaDon.setSelectedIndex(trangThaiHoaDon == 1 ? 1 : trangThaiHoaDon == 5 ? 5 : 0);
//                    _listChiTietSanPham = _iChiTietSanPhamService.getAll();
//                    showDataTableSanPham(_listChiTietSanPham);
                } else {
                    int trangThaiHoaDon = cbbTrangThaiHoaDon.getSelectedIndex();
                    cbbTrangThaiHoaDon.setSelectedIndex(trangThaiHoaDon == 3 ? 3 : trangThaiHoaDon == 4 ? 4 : 2);
//                    _listChiTietSanPham = _iChiTietSanPhamService.getAll();
//                    showDataTableSanPham(_listChiTietSanPham);
                }
            }
        });
    }

    private void loadTienThuaHoaDon() {
        txtTienKhachDua.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                tinhTienThuaHoaDon();
            }
        });
    }

    private void loadTienThuaHoaDonKhiThanhToanBangChuyenKhoanVaTienMat() {
        txtTienChuyenKhoan.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                tinhTienThuaKhiChuyenKhoanVaTienMat();
            }
        });
    }

    private void loadTienThuaDatHang() {
        txtTienCoc.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                tinhTienThuaDatHang();
            }
        });

    }

    public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            setBackground(new java.awt.Color(32, 136, 203));
            setFont(new Font("Segoe UI", Font.BOLD, 15));
            setForeground(new Color(255, 255, 255));
            table.setRowHeight(30);

            //you can change the color that u want 
            return this;
        }

    }

    private void showDataTableSanPham(List<QLChiTietSanPham> list) {
//        tableSanPham.getTableHeader().setDefaultRenderer(new HeaderColor());
        dtmSanPham = (DefaultTableModel) tableSanPham.getModel();
        dtmSanPham.setRowCount(0);
        list.forEach(s -> dtmSanPham.addRow(s.toDataRowBanHang()));
    }

    private void showDataTableHoaDon(List<QLHoaDon> list) {
//        tableHoaDon.getTableHeader().setDefaultRenderer(new HeaderColor());
        dtmHoaDon = (DefaultTableModel) tableHoaDon.getModel();
        dtmHoaDon.setRowCount(0);
        list.forEach(s -> dtmHoaDon.addRow(s.toDataRow()));
    }

    private int checkBoxSelected() {
        if (checkBoxHoaDon.isSelected()) {
            return 2;
        }
        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        panelListSearchCustomer = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listSearchKH = new javax.swing.JList<>();
        menu = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        cbbTrangThaiHoaDon = new javax.swing.JComboBox<>();
        btnTaoHoaDon = new javax.swing.JButton();
        checkBoxHoaDon = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSanPham = new javax.swing.JTable();
        txtSearchSP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        panelDonHang = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        labelTongTienHang = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        labelKhachCanTra = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        labelTienThua = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        btnHuy = new javax.swing.JButton();
        cbTienMat = new javax.swing.JCheckBox();
        cbChuyenKhoan = new javax.swing.JCheckBox();
        btnThanhToan = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        txtTienChuyenKhoan = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        labelTenNguoiBan = new javax.swing.JLabel();
        labelNgayTao = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtSearchKH = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        labelMaHD = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenNguoiNhan = new javax.swing.JTextField();
        txtSDT1 = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        labelTenNguoiBan1 = new javax.swing.JLabel();
        labelNgayTao1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelTongTienHang1 = new javax.swing.JLabel();
        labelKhachCanTra1 = new javax.swing.JLabel();
        txtTienCoc = new javax.swing.JTextField();
        labelTienThua1 = new javax.swing.JLabel();
        btnThanhToan1 = new javax.swing.JButton();
        btnDaGiao = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtGiamGia1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTienShip = new javax.swing.JTextField();
        btnHuy1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableGioHang = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        panelWebCam = new javax.swing.JPanel();
        lblQRMaSV = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        panelListSearchCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelListSearchCustomerMouseClicked(evt);
            }
        });

        listSearchKH.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        listSearchKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listSearchKHMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(listSearchKH);

        javax.swing.GroupLayout panelListSearchCustomerLayout = new javax.swing.GroupLayout(panelListSearchCustomer);
        panelListSearchCustomer.setLayout(panelListSearchCustomerLayout);
        panelListSearchCustomerLayout.setHorizontalGroup(
            panelListSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
        );
        panelListSearchCustomerLayout.setVerticalGroup(
            panelListSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        );

        menu.setFocusable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        tableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HD", "Ngày Tạo", "Tên NV", "Khách Hàng", "Trạng Thái "
            }
        ));
        tableHoaDon.setSelectionBackground(new java.awt.Color(235, 57, 95));
        tableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHoaDon);

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel17.setText("Trạng thái");

        cbbTrangThaiHoaDon.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbbTrangThaiHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán", "Đang tạo", "Đang giao", "Đã giao", "Đã hủy" }));
        cbbTrangThaiHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiHoaDonActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        checkBoxHoaDon.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        checkBoxHoaDon.setText("Đặt hàng");
        checkBoxHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(checkBoxHoaDon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(checkBoxHoaDon)
                    .addComponent(jLabel17)
                    .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP ", "Tên SP", "Số Lượng Tồn", "Giá Bán ", "Size", "Màu Sắc "
            }
        ));
        tableSanPham.setSelectionBackground(new java.awt.Color(235, 57, 95));
        tableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableSanPham);

        txtSearchSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSPActionPerformed(evt);
            }
        });
        txtSearchSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSPKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelDonHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn hàng"));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thanh toán"));

        jLabel42.setText("Tổng tiền hàng: ");

        labelTongTienHang.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTongTienHang.setForeground(new java.awt.Color(255, 0, 0));
        labelTongTienHang.setText("0");

        jLabel43.setText("Khách cần trả:");

        labelKhachCanTra.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelKhachCanTra.setForeground(new java.awt.Color(255, 0, 0));
        labelKhachCanTra.setText("0");

        jLabel44.setText("Tiền khách đưa:");

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyTyped(evt);
            }
        });

        jLabel45.setText("Tiền thừa: ");

        labelTienThua.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTienThua.setForeground(new java.awt.Color(255, 0, 0));
        labelTienThua.setText("0");

        jLabel12.setText("HT Thanh toán:");

        jLabel13.setText("Giảm giá:");

        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyTyped(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        cbTienMat.setText("Tiền mặt");
        cbTienMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTienMatActionPerformed(evt);
            }
        });

        cbChuyenKhoan.setText("Chuyển khoản");
        cbChuyenKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChuyenKhoanActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel46.setText("Tiền chuyển khoản:");

        txtTienChuyenKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienChuyenKhoanKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienChuyenKhoanKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelKhachCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel44)
                                                .addComponent(jLabel45))
                                            .addGap(29, 29, 29))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel46)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(cbTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbChuyenKhoan))
                                    .addComponent(labelTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                        .addComponent(txtTienChuyenKhoan)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(labelTongTienHang))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(labelKhachCanTra))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtTienChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTienThua)
                    .addComponent(jLabel45))
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbTienMat)
                    .addComponent(cbChuyenKhoan))
                .addGap(31, 31, 31)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin"));

        jLabel5.setText("Tên người bán:");

        jLabel8.setText("Ngày tạo:");

        jLabel14.setText("Tên khách hàng:");

        labelTenNguoiBan.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTenNguoiBan.setForeground(new java.awt.Color(0, 51, 255));
        labelTenNguoiBan.setText("..");

        labelNgayTao.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelNgayTao.setForeground(new java.awt.Color(0, 51, 255));
        labelNgayTao.setText("..");

        jLabel18.setText("SĐT:");

        jLabel19.setText("Tìm khách hàng:");

        txtSearchKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKHKeyReleased(evt);
            }
        });

        jLabel16.setText("Mã HĐ:");

        labelMaHD.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelMaHD.setForeground(new java.awt.Color(0, 51, 255));
        labelMaHD.setText("..");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTenNguoiBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT)
                    .addComponent(txtSearchKH)
                    .addComponent(labelMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(84, 84, 84))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelTenNguoiBan))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(labelMaHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelNgayTao))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(txtSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Hóa đơn", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin")));

        jLabel1.setText("Tên người nhận:");

        jLabel3.setText("SĐT:");

        jLabel4.setText("Địa chỉ:");

        jLabel20.setText("Tên người bán:");

        jLabel21.setText("Ngày tạo:");

        labelTenNguoiBan1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTenNguoiBan1.setForeground(new java.awt.Color(0, 0, 255));
        labelTenNguoiBan1.setText("..");

        labelNgayTao1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelNgayTao1.setForeground(new java.awt.Color(0, 0, 255));
        labelNgayTao1.setText("..");

        jLabel15.setText("Ngày hẹn:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelTenNguoiBan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNgayTao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(labelTenNguoiBan1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(labelNgayTao1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thanh toán"));

        jLabel6.setText("Tổng tiền hàng:");

        jLabel7.setText("Khách cần trả:");

        jLabel9.setText("Tiền cọc:");

        jLabel11.setText("Tiền thừa:");

        labelTongTienHang1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTongTienHang1.setForeground(new java.awt.Color(255, 0, 0));
        labelTongTienHang1.setText("0");

        labelKhachCanTra1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelKhachCanTra1.setForeground(new java.awt.Color(255, 0, 0));
        labelKhachCanTra1.setText("0");

        txtTienCoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienCocKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienCocKeyTyped(evt);
            }
        });

        labelTienThua1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTienThua1.setForeground(new java.awt.Color(255, 0, 0));
        labelTienThua1.setText("0");

        btnThanhToan1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnThanhToan1.setText("Giao hàng");
        btnThanhToan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan1ActionPerformed(evt);
            }
        });

        btnDaGiao.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnDaGiao.setText("Đã giao");
        btnDaGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaGiaoActionPerformed(evt);
            }
        });

        jLabel24.setText("Giảm giá: ");

        txtGiamGia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGia1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiamGia1KeyTyped(evt);
            }
        });

        jLabel25.setText("Tiền ship: ");

        txtTienShip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienShipKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienShipKeyTyped(evt);
            }
        });

        btnHuy1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnHuy1.setText("Hủy");
        btnHuy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuy1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(39, 39, 39)
                            .addComponent(labelTongTienHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(55, 55, 55))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(btnHuy1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDaGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnThanhToan1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel25)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTienThua1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienShip, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelKhachCanTra1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiamGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelTongTienHang1))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(labelKhachCanTra1))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtGiamGia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtTienShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labelTienThua1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDaGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Đặt hàng", jPanel10);

        javax.swing.GroupLayout panelDonHangLayout = new javax.swing.GroupLayout(panelDonHang);
        panelDonHang.setLayout(panelDonHangLayout);
        panelDonHangLayout.setHorizontalGroup(
            panelDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane)
                .addContainerGap())
        );
        panelDonHangLayout.setVerticalGroup(
            panelDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tableGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá ", "Thành Tiền"
            }
        ));
        tableGioHang.setSelectionBackground(new java.awt.Color(235, 57, 95));
        tableGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGioHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableGioHang);

        btnClear.setText("Làm mới giỏ hàng");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnClear)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelWebCam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        panelWebCam.setLayout(new javax.swing.BoxLayout(panelWebCam, javax.swing.BoxLayout.LINE_AXIS));

        lblQRMaSV.setText("-----");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblQRMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addComponent(panelDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(panelWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblQRMaSV)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panelDonHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSanPhamMouseClicked
        // TODO add your handling code here:
        int rowHoaDon = tableHoaDon.getSelectedRow();
        int rowSanPham = tableSanPham.getSelectedRow();
        int rowGioHang = tableGioHang.getSelectedRow();

        //Lấy MaSP
        String productCode = dtmSanPham.getValueAt(rowSanPham, 1).toString();
        QLChiTietSanPham sp = _iChiTietSanPhamService.getProductByMa(_listChiTietSanPham, productCode);

        String soLuong = JOptionPane.showInputDialog("Nhập số lượng: ", "0");
        String validate = validation.validateProduct(soLuong, sp);  //validate số lượng của Product

        if (validate != null) {
            JOptionPane.showMessageDialog(this, validation.validateProduct(soLuong, sp));
            tableSanPham.clearSelection();
        } else {
            if (tabbedPane.getSelectedIndex() == 0) {
                if (_iHoaDonService.getAllHoaDonCho(0).isEmpty()) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn tạo hóa đơn?");
                    if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(this, "Đã hủy");
                    } else {
                        HoaDon hd = addHoaDonTuDong();
                        QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                        hdct.setIdHoaDon(hd.getIdHoaDon());
                        hdct.setIdCTSP(sp.getIdCTSP());
                        hdct.setMaSP(sp.getMaSanPham());
                        hdct.setTenSP(sp.getTenSanPham());
                        hdct.setSoLuongMua(Integer.valueOf(soLuong));
                        hdct.setDonGia(sp.getGiaBan());
                        _listHoaDonChiTiet.add(hdct);
                        //Set tạm số lượng sản phẩm;
                        sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                        //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                        mapsProduct.put(sp, sp.getSoLuongTonKho());
                        showDataTableSanPhamFromInput();
                        showDataTableGioHang(_listHoaDonChiTiet);
                        loadTongTienHoaDon();
                    }
                } else {
                    if (rowHoaDon == -1) {
                        cbbTrangThaiHoaDon.setSelectedIndex(0);
                        tableHoaDon.setRowSelectionInterval(0, 0);
                        QLHoaDon hd = _listHoaDon.get(0);
                        QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                        hdct.setIdHoaDon(hd.getIdHoaDon());
                        hdct.setIdCTSP(sp.getIdCTSP());
                        hdct.setMaSP(sp.getMaSanPham());
                        hdct.setTenSP(sp.getTenSanPham());
                        hdct.setSoLuongMua(Integer.valueOf(soLuong));
                        hdct.setDonGia(sp.getGiaBan());
                        _listHoaDonChiTiet.add(hdct);
                        //Set tạm số lượng sản phẩm;
                        sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                        //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                        mapsProduct.put(sp, sp.getSoLuongTonKho());
                        showDataTableSanPhamFromInput();
                        showDataTableGioHang(_listHoaDonChiTiet);
                        loadTongTienHoaDon();
                    } else {
                        QLHoaDon hd = _listHoaDon.get(rowHoaDon);
                        if (hd.getTrangThai() != 0) {
                            JOptionPane.showMessageDialog(this, "Yêu cầu hóa đơn chờ");
                        } else {
                            QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                            hdct.setIdHoaDon(hd.getIdHoaDon());
                            hdct.setIdCTSP(sp.getIdCTSP());
                            hdct.setMaSP(sp.getMaSanPham());
                            hdct.setTenSP(sp.getTenSanPham());
                            hdct.setSoLuongMua(Integer.valueOf(soLuong));
                            hdct.setDonGia(sp.getGiaBan());
                            _listHoaDonChiTiet.add(hdct);
                            //Set tạm số lượng sản phẩm;
                            sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                            //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                            mapsProduct.put(sp, sp.getSoLuongTonKho());
                            showDataTableSanPhamFromInput();
                            showDataTableGioHang(_listHoaDonChiTiet);
                            loadTongTienHoaDon();
                        }
                    }
                }

            } else if (tabbedPane.getSelectedIndex() == 1) {
                if (_iHoaDonService.getAllHoaDonCho(2).isEmpty()) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn tạo hóa đơn?");
                    if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(this, "Đã hủy");
                    } else {
                        HoaDon hd = addDonHangTuDong();
                        QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                        hdct.setIdHoaDon(hd.getIdHoaDon());
                        hdct.setIdCTSP(sp.getIdCTSP());
                        hdct.setMaSP(sp.getMaSanPham());
                        hdct.setTenSP(sp.getTenSanPham());
                        hdct.setSoLuongMua(Integer.valueOf(soLuong));
                        hdct.setDonGia(sp.getGiaBan());
                        _listHoaDonChiTiet.add(hdct);
                        //Set tạm số lượng sản phẩm;
                        sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                        //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                        mapsProduct.put(sp, sp.getSoLuongTonKho());
                        showDataTableSanPhamFromInput();
                        showDataTableGioHang(_listHoaDonChiTiet);
                        loadTongTienHoaDon();
                    }
                } else {
                    if (rowHoaDon == -1) {
                        cbbTrangThaiHoaDon.setSelectedIndex(2);
                        tableHoaDon.setRowSelectionInterval(0, 0);
                        QLHoaDon hd = _listHoaDon.get(0);
                        QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                        hdct.setIdHoaDon(hd.getIdHoaDon());
                        hdct.setIdCTSP(sp.getIdCTSP());
                        hdct.setMaSP(sp.getMaSanPham());
                        hdct.setTenSP(sp.getTenSanPham());
                        hdct.setSoLuongMua(Integer.valueOf(soLuong));
                        hdct.setDonGia(sp.getGiaBan());
                        _listHoaDonChiTiet.add(hdct);
                        //Set tạm số lượng sản phẩm;
                        sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                        //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                        mapsProduct.put(sp, sp.getSoLuongTonKho());
                        showDataTableSanPham(_listChiTietSanPham);
                        showDataTableSanPhamFromInput();
                        showDataTableGioHang(_listHoaDonChiTiet);
                        loadTongTienHoaDon();
                    } else {
                        QLHoaDon hd = _listHoaDon.get(rowHoaDon);
                        if (hd.getTrangThai() != 2) {
                            JOptionPane.showMessageDialog(this, "Yêu cầu hóa đơn đang tạo");
                        } else {
                            QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                            hdct.setIdHoaDon(hd.getIdHoaDon());
                            hdct.setIdCTSP(sp.getIdCTSP());
                            hdct.setMaSP(sp.getMaSanPham());
                            hdct.setTenSP(sp.getTenSanPham());
                            hdct.setSoLuongMua(Integer.valueOf(soLuong));
                            hdct.setDonGia(sp.getGiaBan());
                            _listHoaDonChiTiet.add(hdct);
                            //Set tạm số lượng sản phẩm;
                            sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                            //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                            mapsProduct.put(sp, sp.getSoLuongTonKho());
                            showDataTableSanPham(_listChiTietSanPham);
                            showDataTableSanPhamFromInput();
                            showDataTableGioHang(_listHoaDonChiTiet);
                            loadTongTienHoaDon();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tableSanPhamMouseClicked

    private void loadTongTienHoaDon() {
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        String total = formatter.format(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet));
        if (tabbedPane.getSelectedIndex() == 0) {
            labelKhachCanTra.setText(total);
            labelTongTienHang.setText(total);
        } else {
            labelTongTienHang1.setText(total);
            labelKhachCanTra1.setText(total);
        }

    }

    private String getNhanVien(String id) {
        List<QLNhanVien> list = nhanVienServicer.getList();
        for (QLNhanVien nv : list) {
            if (nv.getIdNhanVien().equals(id)) {
                return nv.getTenNV() + " - " + nv.getMaNV();
            }
        }
        return null;
    }

    private void tableHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tableHoaDon.getSelectedRow();
        QLHoaDon hoaDon = _listHoaDon.get(row);
        dtmGioHang = (DefaultTableModel) tableGioHang.getModel();
        _listHoaDonChiTiet = _iHoaDonChiTietService.getListInvoices(hoaDon.getIdHoaDon());
        showDataTableGioHang(_listHoaDonChiTiet);
//        fillHoaDon(row);
    }//GEN-LAST:event_tableHoaDonMouseClicked
//    private void fillHoaDon(int index) {
//        if (index >= 0) {
//            if (tabbedPane.getSelectedIndex() == 0) {
//                QLHoaDon hd = _listHoaDon.get(index);
//                labelMaHD.setText(hd.getMaHoaDon());
//                labelNgayTao.setText(String.valueOf(hd.getNgayTao()));
//                labelTenNguoiBan.setText(hd.getTenNhanVien());
//            } else {
//                QLHoaDon hd = _listHoaDon.get(index);
//                labelNgayTao1.setText(String.valueOf(hd.getNgayTao()));
//                labelTenNguoiBan1.setText(hd.getTenNhanVien());
//            }
//        }
//    }

    private void tableGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGioHangMouseClicked
        // TODO add your handling code here:
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Xóa");
        popupMenu.add(removeItem);
        tableGioHang.setComponentPopupMenu(popupMenu);

        tableGioHang.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
            }
        });

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowHoaDon = tableHoaDon.getSelectedRow();
                int rowGioHang = tableGioHang.getSelectedRow();
                if (rowHoaDon <= -1) {
                    JOptionPane.showMessageDialog(viewBanHang, "Chưa chọn hóa đơn");
                } else if (rowHoaDon >= 0 && rowGioHang == -1) {
                    JOptionPane.showMessageDialog(viewBanHang, "Chọn sản phẩm cần xóa");
                } else {
                    QLHoaDon hd = _listHoaDon.get(rowHoaDon);
                    int trangThai = hd.getTrangThai();
                    if (trangThai == 1 || trangThai == 3 || trangThai == 4) {
                        JOptionPane.showMessageDialog(viewBanHang, "Không thể xóa");
                    } else {
                        QLHoaDonChiTiet hdct = _listHoaDonChiTiet.get(rowGioHang);
                        _listHoaDonChiTiet.remove(hdct);
                        showDataTableGioHang(_listHoaDonChiTiet);
                        _iChiTietSanPhamService.updateSoLuongProductKhiRemove(_listChiTietSanPham, hdct.getMaSP(), hdct.getSoLuongMua());
                        QLChiTietSanPham sp = _iChiTietSanPhamService.getProductByMa(_listChiTietSanPham, hdct.getMaSP());
                        mapsProduct.remove(sp);
                        showDataTableSanPham(_listChiTietSanPham);
                        showDataTableSanPhamFromInput();
                        loadTongTienHoaDon();
                    }
                }
            }
        }
        );

        int rowGioHang = tableGioHang.getSelectedRow();
        if (rowGioHang >= 0) {

        }
    }//GEN-LAST:event_tableGioHangMouseClicked

    private String getDateVN() {
        Locale locale = new Locale("vi", "VN");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        String date = dateFormat.format(new Date());
        return date;
    }

    private HoaDon addDonHangTuDong() {
        HoaDon hd = new HoaDon();
        hd.setNgayTao(new Date());
        hd.setIdNhanVien("16A5796D-4090-4F82-931D-6FD8CA7D1964");
        hd.setTrangThai(2);
        HoaDon create = _iHoaDonService.add(hd);
        JOptionPane.showMessageDialog(this, "Tạo thành công");
        labelNgayTao1.setText(String.valueOf(getDateVN()));

        _listHoaDon = _iHoaDonService.getAllHoaDonCho(2);
        showDataTableHoaDon(_listHoaDon);
        cbbTrangThaiHoaDon.setSelectedIndex(2);
        tableHoaDon.setRowSelectionInterval(0, 0);

        mapsProduct.clear();

        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
        showDataTableGioHang(_listHoaDonChiTiet);
        return create;
    }

    private HoaDon addHoaDonTuDong() {
        HoaDon hd = new HoaDon();
        hd.setNgayTao(new Date());
        hd.setIdNhanVien("16A5796D-4090-4F82-931D-6FD8CA7D1964");
        hd.setTrangThai(0);
        HoaDon create = _iHoaDonService.add(hd);
        JOptionPane.showMessageDialog(this, "Tạo thành công");
        labelMaHD.setText(create.getMaHoaDon());
        labelNgayTao.setText(String.valueOf(getDateVN()));

        _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
        showDataTableHoaDon(_listHoaDon);
        cbbTrangThaiHoaDon.setSelectedIndex(0);
        tableHoaDon.setRowSelectionInterval(0, 0);

        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
        showDataTableGioHang(_listHoaDonChiTiet);
        return create;
    }

    private void addHoaDonTaiQuay() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn tạo hóa đơn?");
        if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Đã hủy");
        } else {
            HoaDon hd = new HoaDon();
            hd.setNgayTao(new Date());
            hd.setIdNhanVien("16A5796D-4090-4F82-931D-6FD8CA7D1964");
            hd.setTrangThai(0);
            HoaDon insert = _iHoaDonService.add(hd);
            JOptionPane.showMessageDialog(this, "Tạo thành công");
            labelMaHD.setText(insert.getMaHoaDon());
            labelNgayTao.setText(String.valueOf(getDateVN()));

            _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
            showDataTableHoaDon(_listHoaDon);
            tableHoaDon.setRowSelectionInterval(0, 0);

            _listChiTietSanPham = _iChiTietSanPhamService.getAll();
            showDataTableSanPham(_listChiTietSanPham);

            mapsProduct.clear();

            _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
            showDataTableGioHang(_listHoaDonChiTiet);
        }
    }

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        if (checkBoxSelected() == 2) {
            addHoaDonDatHang();
            tabbedPane.setSelectedIndex(1);
            cbbTrangThaiHoaDon.setSelectedIndex(2);
            tableHoaDon.setRowSelectionInterval(0, 0);
        } else {
            addHoaDonTaiQuay();
            tabbedPane.setSelectedIndex(0);
            cbbTrangThaiHoaDon.setSelectedIndex(0);
            tableHoaDon.setRowSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        int row = tableHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
        } else {
            QLHoaDon hd = _listHoaDon.get(row);
            if (hd.getTrangThai() == 0 || hd.getTrangThai() == 2) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy hóa đơn?");
                if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(this, "Đã hủy");
                } else {
                    _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 5);
                    _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
                    showDataTableHoaDon(_listHoaDon);
                    JOptionPane.showMessageDialog(this, "Hủy thành công");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không thể hủy đơn hàng đã được thanh toán");
            }
    }//GEN-LAST:event_btnHuyActionPerformed
    }

    private String getKhachHangByMa(String ma) {
        _listKhachHang = _ikhacHangService.getAll();
        for (QLKhachHang kh : _listKhachHang) {
            if (kh.getMaKhachHang().equalsIgnoreCase(ma)) {
                return kh.getIdKhachHang();
            }
        }
        return null;
    }

    private void addHoaDonDatHang() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn tạo hóa đơn?");
        if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Đã hủy");
        } else {
            HoaDon hd = new HoaDon();
            hd.setNgayTao(new Date());
            hd.setIdNhanVien("16A5796D-4090-4F82-931D-6FD8CA7D1964");
            hd.setTrangThai(2);

            HoaDon insert = _iHoaDonService.add(hd);
            JOptionPane.showMessageDialog(this, "Tạo thành công");
            labelNgayTao.setText(String.valueOf(getDateVN()));

            _listHoaDon = _iHoaDonService.getAllHoaDonCho(2);
            showDataTableHoaDon(_listHoaDon);

            _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
            showDataTableGioHang(_listHoaDonChiTiet);
        }
    }
    private void btnThanhToan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan1ActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thanh toán ?");
        if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Đã hủy");
        } else {
            if (_listHoaDonChiTiet.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giỏ hàng chưa có sản phẩm");
            } else {
                int row = tableHoaDon.getSelectedRow();
                QLHoaDon hd = _listHoaDon.get(row);
                if (hd.getTrangThai() == 2) {
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setTenNguoiNhan(txtTenNguoiNhan.getText());
                    hoaDon.setDiaChi(txtDiaChi.getText());
                    hoaDon.setsDT(txtSDT1.getText());
                    hoaDon.setNgayShip(new Date());
                    hoaDon.setNgayThanhToan(new Date());
                    Date d = jDateChooser1.getDate();
                    String dateNow = DateFormat.getDateInstance().format(d);
                    hoaDon.setNgayHen(d);
                    _iHoaDonService.updateHoaDonGiaoHang(hoaDon, hd.getIdHoaDon());

                    _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 3);

                    JOptionPane.showMessageDialog(this, _iHoaDonChiTietService.addListInvoice(_listHoaDonChiTiet));
                    _iChiTietSanPhamService.uppdateSoLuong(mapsProduct);

                    _listHoaDon = _iHoaDonService.getAllHoaDonCho(2);
                    showDataTableHoaDon(_listHoaDon);
                    cbbTrangThaiHoaDon.setSelectedIndex(2);
                    if (!_listHoaDon.isEmpty()) {
                        tableHoaDon.setRowSelectionInterval(0, 0);
                    }
                    _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
                    showDataTableGioHang(_listHoaDonChiTiet);
                    _listChiTietSanPham = _iChiTietSanPhamService.getAll();
                    showDataTableSanPham(_listChiTietSanPham);

                    mapsProduct.clear();

                    clearThongTinDonHang();
                } else {
                    JOptionPane.showMessageDialog(this, "Yêu cầu hóa đơn đang tạo");
                }
            }
        }
    }//GEN-LAST:event_btnThanhToan1ActionPerformed

    private void btnDaGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaGiaoActionPerformed
        // TODO add your handling code here:
        int row = tableHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
        } else if (row >= 0) {
            QLHoaDon hd = _listHoaDon.get(row);
            int trangThai = hd.getTrangThai();
            if (trangThai == 2) {
                JOptionPane.showMessageDialog(this, "Đơn hàng chưa thanh toán");
            } else if (trangThai == 4) {
                JOptionPane.showMessageDialog(this, "Đơn hàng đã được giao");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn đơn hàng này đã được giao?");
                if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(this, "Đã hủy");
                } else {
                    _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 4);

                    HoaDon update = new HoaDon();
                    update.setNgayNhan(new Date());
                    _iHoaDonService.updateNgayNhan(update, hd.getIdHoaDon());

                    _listHoaDon = _iHoaDonService.getAllHoaDonCho(4);
                    showDataTableHoaDon(_listHoaDon);
                    _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
                    showDataTableGioHang(_listHoaDonChiTiet);
                    cbbTrangThaiHoaDon.setSelectedItem("Đã giao");
                    JOptionPane.showMessageDialog(this, "Đã giao thành công");

                    txtTenNguoiNhan.setText(null);
                    txtSDT1.setText(null);
                    txtDiaChi.setText(null);
                    labelTongTienHang1.setText("0");
                    labelKhachCanTra1.setText("0");
                    txtGiamGia1.setText("0");
                    txtTienShip.setText("0");
                    txtTienCoc.setText("0");
                    labelTienThua1.setText("0");
                }
            }
        }
    }//GEN-LAST:event_btnDaGiaoActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
        showDataTableGioHang(_listHoaDonChiTiet);
        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
        showDataTableSanPham(_listChiTietSanPham);
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtSearchSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchSPActionPerformed

    private void txtSearchSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSPKeyReleased
        // TODO add your handling code here:
        showDataTableSanPhamFromInput();
    }//GEN-LAST:event_txtSearchSPKeyReleased

    private void showDataTableSanPhamFromInput() {
        List<QLChiTietSanPham> listFound = _iChiTietSanPhamService.searchSanPhamFromInput(_listChiTietSanPham, txtSearchSP.getText());
        showDataTableSanPham(listFound);
    }

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:
        tinhTienThuaHoaDon();
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void cbbTrangThaiHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiHoaDonActionPerformed
        // TODO add your handling code here:
        showListHoaDonByCombobox();
        int trangThaiHoaDon = cbbTrangThaiHoaDon.getSelectedIndex();
        if (trangThaiHoaDon == 0 || trangThaiHoaDon == 1 || trangThaiHoaDon == 5) {
            tabbedPane.setSelectedIndex(0);
        }
        if (trangThaiHoaDon == 2 || trangThaiHoaDon == 3 || trangThaiHoaDon == 4) {
            tabbedPane.setSelectedIndex(1);
        }

    }//GEN-LAST:event_cbbTrangThaiHoaDonActionPerformed

    private void txtGiamGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaKeyReleased
        // TODO add your handling code here:
        tinhTienCanTraKhiGiamGiaHoaDon();
    }//GEN-LAST:event_txtGiamGiaKeyReleased

    private void txtGiamGia1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGia1KeyReleased
        // TODO add your handling code here:
        tinhTienCanTraKhiGiamGiaDonHang();
    }//GEN-LAST:event_txtGiamGia1KeyReleased

    private void txtTienShipKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienShipKeyReleased
        // TODO add your handling code here:
        tinhTienCanTraKhiGiamGiaDonHang();
    }//GEN-LAST:event_txtTienShipKeyReleased

    private void txtSearchKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKHKeyReleased
        // TODO add your handling code here:
        _listKhachHang = _ikhacHangService.getAll();
        List<QLKhachHang> listFound = _ikhacHangService.searchKhachHangFromInput(_listKhachHang, txtSearchKH.getText());
        if (listFound != null) {
            dlmSearchCustomer.removeAllElements();
            listFound.forEach(customers -> dlmSearchCustomer.addElement(customers.displayCustomer()));
            menu.show(txtSearchKH, 0, txtSearchKH.getHeight());
        } else {
            dlmSearchCustomer.removeAllElements();
            menu.show(txtSearchKH, 0, txtSearchKH.getHeight());
        }
    }//GEN-LAST:event_txtSearchKHKeyReleased

    private void listSearchKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listSearchKHMouseClicked
        // TODO add your handling code here:
        String dataCustomer = listSearchKH.getSelectedValue();
        if (dataCustomer.contains("-")) {
            String[] parts = dataCustomer.split(" - ");
            String partEnd = parts[1];
            QLKhachHang kh = _ikhacHangService.getCustomerByPhone(partEnd, _listKhachHang);
            txtTenKhachHang.setText(kh.getTen());
            txtSDT.setText(kh.getSdt());
            menu.setVisible(false);
        } else {
            QLKhachHang kh1 = _ikhacHangService.getCustomerByPhone(dataCustomer, _listKhachHang);
            txtTenKhachHang.setText(kh1.getTen());
            txtSDT.setText(kh1.getSdt());
        }
    }//GEN-LAST:event_listSearchKHMouseClicked

    private void panelListSearchCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelListSearchCustomerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelListSearchCustomerMouseClicked

    private void checkBoxHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxHoaDonActionPerformed

    private void cbTienMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTienMatActionPerformed
        // TODO add your handling code here:
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        try {
            if (cbTienMat.isSelected()) {
                txtTienKhachDua.setEditable(true);
            } else {
                if (!txtTienKhachDua.getText().isEmpty()) {
                    Double tienMat = Double.valueOf(txtTienKhachDua.getText());
                    labelTienThua.setText(formatter.format(tienThuaTotal - tienMat));
                    tienThuaTotal = tienThuaTotal - tienMat;
                    txtTienKhachDua.setText(null);
                    txtTienKhachDua.setEditable(false);
                } else {
                    txtTienKhachDua.setText(null);
                    txtTienKhachDua.setEditable(false);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbTienMatActionPerformed

    private void cbChuyenKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChuyenKhoanActionPerformed
        // TODO add your handling code here:
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        try {
            if (cbChuyenKhoan.isSelected()) {
                txtTienChuyenKhoan.setEditable(true);
            } else {
                if (!txtTienChuyenKhoan.getText().isEmpty()) {
                    Double tienCK = Double.valueOf(txtTienChuyenKhoan.getText());
                    labelTienThua.setText(formatter.format(tienThuaTotal - tienCK));
                    tienThuaTotal = tienThuaTotal - tienCK;
                    txtTienChuyenKhoan.setText(null);
                    txtTienChuyenKhoan.setEditable(false);
                } else {
                    txtTienChuyenKhoan.setText(null);
                    txtTienChuyenKhoan.setEditable(false);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbChuyenKhoanActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thanh toán ?");
        if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Đã hủy");
        } else {
            if (_listHoaDonChiTiet.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giỏ hàng chưa có sản phẩm");
            } else {
                String name = txtTenKhachHang.getText();
                String phone = txtSDT.getText();
                int row = tableHoaDon.getSelectedRow();
                QLHoaDon hd = _listHoaDon.get(row);
                if (hd.getTrangThai() == 0) {
                    _listKhachHang = _ikhacHangService.getAll();
                    QLKhachHang checkPhoneCustomer = _ikhacHangService.checkPhoneCustomer(_listKhachHang, phone);
                    String validateCustomer = validation.validateCustomer(name, phone);
                    if (checkPhoneCustomer != null) {
                        String idKhachHang = checkPhoneCustomer.getIdKhachHang();
                        HoaDon update = new HoaDon();
                        update.setIdKhachHang(idKhachHang);
                        update.setNgayThanhToan(new Date());
                        _iHoaDonService.updateHoaDon(update, hd.getIdHoaDon());

                        _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 1);
                        JOptionPane.showMessageDialog(this, _iHoaDonChiTietService.addListInvoice(_listHoaDonChiTiet));
                        _iChiTietSanPhamService.uppdateSoLuong(mapsProduct);
                        _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
                        showDataTableHoaDon(_listHoaDon);
                        cbbTrangThaiHoaDon.setSelectedIndex(0);
                        if (!_listHoaDon.isEmpty()) {
                            tableHoaDon.setRowSelectionInterval(0, 0);
                        }
                        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
                        showDataTableGioHang(_listHoaDonChiTiet);
                        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
                        showDataTableSanPham(_listChiTietSanPham);

                        mapsProduct.clear();
                        clearThongTinHoaDon();

                    } else if (validateCustomer == null) {
                        HoaDon update = new HoaDon();
                        update.setNgayThanhToan(new Date());
                        _iHoaDonService.updateHoaDon(update, hd.getIdHoaDon());
                        _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 1);
                        JOptionPane.showMessageDialog(this, _iHoaDonChiTietService.addListInvoice(_listHoaDonChiTiet));
                        _iChiTietSanPhamService.uppdateSoLuong(mapsProduct);
                        _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
                        showDataTableHoaDon(_listHoaDon);
                        cbbTrangThaiHoaDon.setSelectedIndex(0);
                        if (!_listHoaDon.isEmpty()) {
                            tableHoaDon.setRowSelectionInterval(0, 0);
                        }

                        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
                        showDataTableGioHang(_listHoaDonChiTiet);
                        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
                        showDataTableSanPham(_listChiTietSanPham);

                        mapsProduct.clear();
                        clearThongTinHoaDon();
                    } else if (validateCustomer.equalsIgnoreCase("NewCustomer") && checkPhoneCustomer == null) {
                        KhachHang kh = new KhachHang();
                        kh.setHoTen(name);
                        kh.setSdt(phone);
                        KhachHang khachHang = _ikhacHangService.add(kh);
                        HoaDon update = new HoaDon();
                        update.setIdKhachHang(getKhachHangByMa(khachHang.getMaKhachHang()));
                        update.setNgayThanhToan(new Date());
                        _iHoaDonService.updateHoaDon(update, hd.getIdHoaDon());

                        _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 1);
                        JOptionPane.showMessageDialog(this, _iHoaDonChiTietService.addListInvoice(_listHoaDonChiTiet));
                        _iChiTietSanPhamService.uppdateSoLuong(mapsProduct);
                        _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
                        showDataTableHoaDon(_listHoaDon);
                        cbbTrangThaiHoaDon.setSelectedIndex(0);
                        if (!_listHoaDon.isEmpty()) {
                            tableHoaDon.setRowSelectionInterval(0, 0);
                        }
                        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
                        showDataTableGioHang(_listHoaDonChiTiet);
                        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
                        showDataTableSanPham(_listChiTietSanPham);

                        mapsProduct.clear();
                        clearThongTinHoaDon();
                    } else {
                        JOptionPane.showMessageDialog(this, validateCustomer);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Yêu cầu hóa đơn chờ thanh toán!");
                }
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTienChuyenKhoanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienChuyenKhoanKeyReleased
        // TODO add your handling code here:
        tinhTienThuaHoaDon();
    }//GEN-LAST:event_txtTienChuyenKhoanKeyReleased

    private void btnHuy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuy1ActionPerformed
        // TODO add your handling code here:
        int row = tableHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
        } else {
            QLHoaDon hd = _listHoaDon.get(row);
            if (hd.getTrangThai() == 0 || hd.getTrangThai() == 2) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy hóa đơn?");
                if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(this, "Đã hủy");
                } else {
                    _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 5);
                    _listHoaDon = _iHoaDonService.getAllHoaDonCho(cbbTrangThaiHoaDon.getSelectedIndex());
                    showDataTableHoaDon(_listHoaDon);
                    JOptionPane.showMessageDialog(this, "Hủy thành công");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không thể hủy đơn hàng đã được thanh toán");
            }
        }
    }//GEN-LAST:event_btnHuy1ActionPerformed

    private void txtTienCocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienCocKeyReleased
        // TODO add your handling code here:
        tinhTienCanTraKhiGiamGiaDonHang();
    }//GEN-LAST:event_txtTienCocKeyReleased

    private void txtGiamGiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamGiaKeyTyped

    private void txtTienKhachDuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyTyped

    private void txtTienChuyenKhoanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienChuyenKhoanKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTienChuyenKhoanKeyTyped

    private void txtGiamGia1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGia1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGiamGia1KeyTyped

    private void txtTienShipKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienShipKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTienShipKeyTyped

    private void txtTienCocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienCocKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTienCocKeyTyped

    public void showDataTableGioHang(List<QLHoaDonChiTiet> list) {
        dtmGioHang = (DefaultTableModel) tableGioHang.getModel();
        dtmGioHang.setRowCount(0);
        list.forEach(s -> dtmGioHang.addRow(s.toDataRow()));
    }

    private void tinhTienThuaKhiChuyenKhoanVaTienMat() {
        try {
            Locale locale = new Locale("vi", "VN");
            NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
            Double tienChuyenKhoan = Double.valueOf(txtTienChuyenKhoan.getText());
            Double tienKhachDua = Double.valueOf(txtTienKhachDua.getText());
            Double tienThua = (tienChuyenKhoan + tienKhachDua) - tienKhachCanTra;
            labelTienThua.setText(formatter.format(tienThua));
        } catch (Exception e) {
        }
    }

    private void tinhTienThuaHoaDon() {
        try {
            Locale locale = new Locale("vi", "VN");
            NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
            if (cbTienMat.isSelected()) {
                Double khachCanTra = tienKhachCanTra;
                Double tienKhachDua = Double.valueOf(txtTienKhachDua.getText());
                Double tienThua = tienKhachDua - tienKhachCanTra;
                labelTienThua.setText(formatter.format(tienThua));
                tienThuaTotal = tienThua;
            }
            if (cbChuyenKhoan.isSelected()) {
                Double khachCanTra = tienKhachCanTra;
                Double tienChuyenKhoan = Double.valueOf(txtTienChuyenKhoan.getText());
                Double tienThua = tienChuyenKhoan - tienKhachCanTra;
                labelTienThua.setText(formatter.format(tienThua));
                tienThuaTotal = tienThua;
            }
            if (cbChuyenKhoan.isSelected() && cbTienMat.isSelected()) {
                Double tienChuyenKhoan = Double.valueOf(txtTienChuyenKhoan.getText());
                Double tienKhachDua = Double.valueOf(txtTienKhachDua.getText());
                Double tienThua = (tienChuyenKhoan + tienKhachDua) - tienKhachCanTra;
                labelTienThua.setText(formatter.format(tienThua));
                tienThuaTotal = tienThua;
            }
            if (txtGiamGia.getText().isEmpty()) {
                labelTienThua.setText("Alo");
            }
        } catch (Exception e) {
        }
    }

    private void tinhTienThuaDatHang() {
        try {
            Double khachCanTra = Double.valueOf(labelKhachCanTra1.getText());
            Double tienKhachDua = Double.valueOf(txtTienCoc.getText());
            Double tienChuyenKhoan = Double.valueOf(txtTienChuyenKhoan.getText());
            Double tienThua = (tienChuyenKhoan + tienKhachDua) - khachCanTra;
            labelTienThua1.setText(String.valueOf(tienThua));
        } catch (Exception e) {
        }
    }

    private void tinhTienCanTraKhiGiamGiaHoaDon() {
        try {
            Locale locale = new Locale("vi", "VN");
            NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
            Double tongTien = _iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet);
            Double giamGia = Double.valueOf(txtGiamGia.getText());
            Double khachCanTraKhiGiamGia = tongTien - giamGia;
            labelKhachCanTra.setText(formatter.format(khachCanTraKhiGiamGia));
            tienKhachCanTra = khachCanTraKhiGiamGia;
            if (cbTienMat.isSelected()) {
                Double khachCanTra = tienKhachCanTra;
                Double tienKhachDua = Double.valueOf(txtTienKhachDua.getText());
                Double tienThua = tienKhachDua - tienKhachCanTra;
                labelTienThua.setText(formatter.format(tienThua));
            }
            if (cbChuyenKhoan.isSelected()) {
                Double khachCanTra = tienKhachCanTra;
                Double tienChuyenKhoan = Double.valueOf(txtTienChuyenKhoan.getText());
                Double tienThua = tienChuyenKhoan - tienKhachCanTra;
                labelTienThua.setText(formatter.format(tienThua));
            }
            if (cbChuyenKhoan.isSelected() && cbTienMat.isSelected()) {
                Double tienChuyenKhoan = Double.valueOf(txtTienChuyenKhoan.getText());
                Double tienKhachDua = Double.valueOf(txtTienKhachDua.getText());
                Double tienThua = (tienChuyenKhoan + tienKhachDua) - tienKhachCanTra;
                labelTienThua.setText(formatter.format(tienThua));
            }
        } catch (Exception e) {
        }
    }

    private void tinhTienCanTraKhiGiamGiaVaPhiShip() {
        try {
            if (txtGiamGia1.getText().isEmpty()) {
                Double tongTien = Double.valueOf(labelTongTienHang1.getText());
                Double phiShip = Double.valueOf(txtTienShip.getText());
                Double khachCanTra = tongTien + phiShip - 0;
                labelKhachCanTra1.setText(String.valueOf(khachCanTra));
            } else if (!txtGiamGia1.getText().isEmpty()) {
                Double tongTien = Double.valueOf(labelTongTienHang1.getText());
                Double phiShip = Double.valueOf(txtTienShip.getText());
                Double giamGia = Double.valueOf(txtGiamGia1.getText());
                Double khachCanTra = tongTien + phiShip - giamGia;
                labelKhachCanTra1.setText(String.valueOf(khachCanTra));
            }
        } catch (Exception e) {
        }
    }

    private void tinhTienCanTraKhiGiamGiaDonHang() {
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        try {
            if (txtTienShip.getText().isEmpty() && !txtGiamGia1.getText().isEmpty()) {
                Double tongTien = _iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet);
                Double giamGia = Double.valueOf(txtGiamGia1.getText());
                Double khachCanTraKhiGiamGia = tongTien - giamGia;
                labelKhachCanTra1.setText(formatter.format(khachCanTraKhiGiamGia));
            }
            if (!txtTienShip.getText().isEmpty() && !txtGiamGia1.getText().isEmpty()) {
                Double tongTien = _iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet);
                Double phiShip = Double.valueOf(txtTienShip.getText());
                Double giamGia = Double.valueOf(txtGiamGia1.getText());
                Double khachCanTraKhiGiamGia = (tongTien + phiShip) - giamGia;
                labelKhachCanTra1.setText(formatter.format(khachCanTraKhiGiamGia));
                tienKhachCanTra1 = khachCanTraKhiGiamGia;
                Double tienCK = Double.valueOf(txtTienCoc.getText());
                labelTienThua1.setText(formatter.format(tienCK - khachCanTraKhiGiamGia));
            }
            if (txtGiamGia1.getText().isEmpty() && !txtTienShip.getText().isEmpty()) {
                Double tongTien = _iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet);
                Double phiShip = Double.valueOf(txtTienShip.getText());
                Double khachCanTraKhiGiamGia = tongTien + phiShip;
                labelKhachCanTra1.setText(formatter.format(khachCanTraKhiGiamGia));
            }
            if (txtGiamGia1.getText().isEmpty() && txtTienShip.getText().isEmpty()) {
                Double tongTien = _iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet);
                Double tienCK = Double.valueOf(txtTienCoc.getText());
                labelTienThua1.setText(formatter.format(tienCK - tongTien));
                labelKhachCanTra1.setText(formatter.format(tongTien));
            }
        } catch (Exception e) {
        }
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        panelWebCam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));

        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            } else {
                webcam.close();
            }

            try {
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                result = new MultiFormatReader().decode(bitmap);

            } catch (NotFoundException e) {
                //No result...
            }

            if (result != null) {
                int rowHoaDon = tableHoaDon.getSelectedRow();
                int rowSanPham = tableSanPham.getSelectedRow();
                int rowGioHang = tableGioHang.getSelectedRow();
                //Lấy MaSP
                QLChiTietSanPham sp = _iChiTietSanPhamService.getProductByMa(_listChiTietSanPham, result.getText());

                String soLuong = JOptionPane.showInputDialog("Nhập số lượng: ", "0");
                String validate = validation.validateProduct(soLuong, sp);  //validate số lượng của Product
                if (validate != null) {
                    JOptionPane.showMessageDialog(this, validation.validateProduct(soLuong, sp));
                    tableSanPham.clearSelection();
                } else {
                    if (tabbedPane.getSelectedIndex() == 0) {
                        if (_iHoaDonService.getAllHoaDonCho(0).isEmpty()) {
                            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn tạo hóa đơn?");
                            if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
                                JOptionPane.showMessageDialog(this, "Đã hủy");
                            } else {
                                HoaDon hd = addHoaDonTuDong();
                                QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                                hdct.setIdHoaDon(hd.getIdHoaDon());
                                hdct.setIdCTSP(sp.getIdCTSP());
                                hdct.setMaSP(sp.getMaSanPham());
                                hdct.setTenSP(sp.getTenSanPham());
                                hdct.setSoLuongMua(Integer.valueOf(soLuong));
                                hdct.setDonGia(sp.getGiaBan());
                                _listHoaDonChiTiet.add(hdct);
                                //Set tạm số lượng sản phẩm;
                                sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                                //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                                mapsProduct.put(sp, sp.getSoLuongTonKho());
                                showDataTableSanPhamFromInput();
                                showDataTableGioHang(_listHoaDonChiTiet);
                                loadTongTienHoaDon();
                            }
                        } else {
                            if (rowHoaDon == -1) {
                                cbbTrangThaiHoaDon.setSelectedIndex(0);
                                tableHoaDon.setRowSelectionInterval(0, 0);
                                QLHoaDon hd = _listHoaDon.get(0);
                                QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                                hdct.setIdHoaDon(hd.getIdHoaDon());
                                hdct.setIdCTSP(sp.getIdCTSP());
                                hdct.setMaSP(sp.getMaSanPham());
                                hdct.setTenSP(sp.getTenSanPham());
                                hdct.setSoLuongMua(Integer.valueOf(soLuong));
                                hdct.setDonGia(sp.getGiaBan());
                                _listHoaDonChiTiet.add(hdct);
                                //Set tạm số lượng sản phẩm;
                                sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                                //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                                mapsProduct.put(sp, sp.getSoLuongTonKho());
                                showDataTableSanPhamFromInput();
                                showDataTableGioHang(_listHoaDonChiTiet);
                                loadTongTienHoaDon();
                            } else {
                                QLHoaDon hd = _listHoaDon.get(rowHoaDon);
                                if (hd.getTrangThai() != 0) {
                                    JOptionPane.showMessageDialog(this, "Yêu cầu hóa đơn chờ");
                                } else {
                                    QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                                    hdct.setIdHoaDon(hd.getIdHoaDon());
                                    hdct.setIdCTSP(sp.getIdCTSP());
                                    hdct.setMaSP(sp.getMaSanPham());
                                    hdct.setTenSP(sp.getTenSanPham());
                                    hdct.setSoLuongMua(Integer.valueOf(soLuong));
                                    hdct.setDonGia(sp.getGiaBan());
                                    _listHoaDonChiTiet.add(hdct);
                                    //Set tạm số lượng sản phẩm;
                                    sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                                    //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                                    mapsProduct.put(sp, sp.getSoLuongTonKho());
                                    showDataTableSanPhamFromInput();
                                    showDataTableGioHang(_listHoaDonChiTiet);
                                    loadTongTienHoaDon();
                                }
                            }
                        }

                    } else if (tabbedPane.getSelectedIndex() == 1) {
                        if (_iHoaDonService.getAllHoaDonCho(2).isEmpty()) {
                            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn tạo hóa đơn?");
                            if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
                                JOptionPane.showMessageDialog(this, "Đã hủy");
                            } else {
                                HoaDon hd = addDonHangTuDong();
                                QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                                hdct.setIdHoaDon(hd.getIdHoaDon());
                                hdct.setIdCTSP(sp.getIdCTSP());
                                hdct.setMaSP(sp.getMaSanPham());
                                hdct.setTenSP(sp.getTenSanPham());
                                hdct.setSoLuongMua(Integer.valueOf(soLuong));
                                hdct.setDonGia(sp.getGiaBan());
                                _listHoaDonChiTiet.add(hdct);
                                //Set tạm số lượng sản phẩm;
                                sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                                //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                                mapsProduct.put(sp, sp.getSoLuongTonKho());
                                showDataTableSanPhamFromInput();
                                showDataTableGioHang(_listHoaDonChiTiet);
                                loadTongTienHoaDon();
                            }
                        } else {
                            if (rowHoaDon == -1) {
                                cbbTrangThaiHoaDon.setSelectedIndex(2);
                                tableHoaDon.setRowSelectionInterval(0, 0);
                                QLHoaDon hd = _listHoaDon.get(0);
                                QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                                hdct.setIdHoaDon(hd.getIdHoaDon());
                                hdct.setIdCTSP(sp.getIdCTSP());
                                hdct.setMaSP(sp.getMaSanPham());
                                hdct.setTenSP(sp.getTenSanPham());
                                hdct.setSoLuongMua(Integer.valueOf(soLuong));
                                hdct.setDonGia(sp.getGiaBan());
                                _listHoaDonChiTiet.add(hdct);
                                //Set tạm số lượng sản phẩm;
                                sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                                //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                                mapsProduct.put(sp, sp.getSoLuongTonKho());
                                showDataTableSanPham(_listChiTietSanPham);
                                showDataTableSanPhamFromInput();
                                showDataTableGioHang(_listHoaDonChiTiet);
                                loadTongTienHoaDon();
                            } else {
                                QLHoaDon hd = _listHoaDon.get(rowHoaDon);
                                if (hd.getTrangThai() != 2) {
                                    JOptionPane.showMessageDialog(this, "Yêu cầu hóa đơn đang tạo");
                                } else {
                                    QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                                    hdct.setIdHoaDon(hd.getIdHoaDon());
                                    hdct.setIdCTSP(sp.getIdCTSP());
                                    hdct.setMaSP(sp.getMaSanPham());
                                    hdct.setTenSP(sp.getTenSanPham());
                                    hdct.setSoLuongMua(Integer.valueOf(soLuong));
                                    hdct.setDonGia(sp.getGiaBan());
                                    _listHoaDonChiTiet.add(hdct);
                                    //Set tạm số lượng sản phẩm;
                                    sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                                    //Add sản phẩm vào map (Maps này chứa danh sách sản phẩm); 
                                    mapsProduct.put(sp, sp.getSoLuongTonKho());
                                    showDataTableSanPham(_listChiTietSanPham);
                                    showDataTableSanPhamFromInput();
                                    showDataTableGioHang(_listHoaDonChiTiet);
                                    loadTongTienHoaDon();
                                }
                            }
                        }
                    }
                }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

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
            java.util.logging.Logger.getLogger(BanHangView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHangView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDaGiao;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnHuy1;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThanhToan1;
    private javax.swing.JCheckBox cbChuyenKhoan;
    private javax.swing.JCheckBox cbTienMat;
    private javax.swing.JComboBox<String> cbbTrangThaiHoaDon;
    private javax.swing.JCheckBox checkBoxHoaDon;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelKhachCanTra;
    private javax.swing.JLabel labelKhachCanTra1;
    private javax.swing.JLabel labelMaHD;
    private javax.swing.JLabel labelNgayTao;
    private javax.swing.JLabel labelNgayTao1;
    private javax.swing.JLabel labelTenNguoiBan;
    private javax.swing.JLabel labelTenNguoiBan1;
    private javax.swing.JLabel labelTienThua;
    private javax.swing.JLabel labelTienThua1;
    private javax.swing.JLabel labelTongTienHang;
    private javax.swing.JLabel labelTongTienHang1;
    private javax.swing.JLabel lblQRMaSV;
    private javax.swing.JList<String> listSearchKH;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JPanel panelDonHang;
    private javax.swing.JPanel panelListSearchCustomer;
    private javax.swing.JPanel panelWebCam;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tableGioHang;
    private javax.swing.JTable tableHoaDon;
    private javax.swing.JTable tableSanPham;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtGiamGia1;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDT1;
    private javax.swing.JTextField txtSearchKH;
    private javax.swing.JTextField txtSearchSP;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenNguoiNhan;
    private javax.swing.JTextField txtTienChuyenKhoan;
    private javax.swing.JTextField txtTienCoc;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienShip;
    // End of variables declaration//GEN-END:variables

}
