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
import Models.HoaDon;
import Models.HoaDonChiTiet;
import Models.KhachHang;
import Models.NhanVien;
import Models.SanPham;
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
import ViewModel.QLKhachHang;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 *
 * @author PC- ASUS
 */
public class BanHangView extends javax.swing.JFrame implements Runnable, ThreadFactory {

    /**
     * Creates new form From_BanHang
     */
    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private Map<QLChiTietSanPham, Integer> maps;

    private List<QLKhachHang> _listKhachHang;
    private IKhachHangService _ikhacHangService;
    private NhanVienServicer nhanVienServicer;
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    private IChiTietSanPhamService _iChiTietSanPhamService;
    private IHoaDonChiTietService _iHoaDonChiTietService;
    private IHoaDonService _iHoaDonService;
    private DefaultTableModel dtmGioHang;
    private DefaultTableModel dtmHoaDon;
    private DefaultTableModel dtmSanPham;
    private List<QLHoaDon> _listHoaDon;
    private List<QLHoaDonChiTiet> _listHoaDonChiTiet;
    private List<QLChiTietSanPham> _listChiTietSanPham;
    private int soLuongConLai = 1;
    private int indexSanPham = -1;
    static BanHangView viewBanHang;

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

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }

            
            
            if (result != null) {
                lblQRMaSV.setText(result.getText());
                String maSP = lblQRMaSV.getText();
                String soLuong = JOptionPane.showInputDialog("Nhập số lượng: ", "0");
                QLChiTietSanPham ctsp = getSanPhamByMaQr(maSP);
                if (soLuong == null) {
                    JOptionPane.showMessageDialog(this, "Đã hủy");
                } else {
                    QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                    hdct.setIdCTSP(ctsp.getIdCTSP());
                    hdct.setMaSP(ctsp.getMaSanPham());
                    hdct.setTenSP(ctsp.getTenSanPham());
                    hdct.setSoLuongMua(Integer.valueOf(soLuong));
                    hdct.setDonGia(ctsp.getGiaBan());
                    _listHoaDonChiTiet.add(hdct);
                    showDataTableGioHang(_listHoaDonChiTiet);
                }
            }
        } while (true);
    }
    
    private QLChiTietSanPham getSPByQRCode(String ma) {
        for (QLChiTietSanPham x : _listChiTietSanPham) {
            if (x.getMaSanPham().equals(ma)) {
                return x;
            }
        }
        return null;
    }

    private QLChiTietSanPham getSanPhamByMaQr(String ma) {
        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
        for (QLChiTietSanPham ctsp : _listChiTietSanPham) {
            if (ctsp.getMaSanPham().equals(ma)) {
                return ctsp;
            }
        }
        return null;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    public BanHangView() {
        viewBanHang = this;
        initComponents();
        initWebcam();
        maps = new HashMap<>();
        _ikhacHangService = new KhachHangService();
        nhanVienServicer = new NhanVienServicer();
        chiTietSanPhamRepository = new ChiTietSanPhamRepository();
        _iChiTietSanPhamService = new ChiTietSanPhamService();
        _iHoaDonChiTietService = new HoaDonChiTietService();
        _iHoaDonService = new HoaDonService();
        _listChiTietSanPham = new ArrayList<>();
        _listHoaDon = new ArrayList<>();
        _listHoaDonChiTiet = new ArrayList<>();
        _listKhachHang = new ArrayList<>();
        labelTenNguoiBan.setText(getNhanVien("410DDF58-4BDC-4C6A-BB33-21BBDDF3CC72"));
        labelTenNguoiBan1.setText(getNhanVien("410DDF58-4BDC-4C6A-BB33-21BBDDF3CC72"));

        _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
        showDataTableHoaDon(_listHoaDon);

        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
        showDataTableSanPham(_listChiTietSanPham);

        loadTienThuaHoaDon();
        loadTienThuaDatHang();
        getHoaDonByTabbedPane();
        loadDateTime();
        setDateDefault();
//        onlyNumberTotal();
    }

    private void loadDateTime() {
        DateTime dt = new DateTime(labelNgayTao);
        Thread t1 = new Thread(dt);
        t1.start();

        DateTime dt1 = new DateTime(labelNgayTao1);
        Thread t2 = new Thread(dt1);
        t2.start();
    }

    private void setDateDefault() {
        Date d = new Date();
        jDateChooser1.setDate(d);
    }

//    private void onlyNumberTotal() {
//        txtTienKhachDua.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent ke) {
//                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == VK_BACK_SPACE) {
//                    txtTienKhachDua.setEditable(true);
//                    txtTienKhachDua.setForeground(Color.BLACK);
//                    txtTienKhachDua.getBorder();
//                    txtTienKhachDua.setBorder(BorderFactory.createLineBorder(new Color(187, 187, 187)));
//                } else {
//                    txtTienKhachDua.setEditable(false);
//                }
//            }
//        });
//    }
    private void getHoaDonByTabbedPane() {
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == 0) {
                    cbbTrangThaiHoaDon.setSelectedIndex(0);
                    _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
                    showDataTableHoaDon(_listHoaDon);
                    _listChiTietSanPham = _iChiTietSanPhamService.getAll();
                    showDataTableSanPham(_listChiTietSanPham);
                    txtTenKhachHang.setText(null);
                    txtSDT.setText(null);
                    labelTongTienHang.setText("0");
                    labelKhachCanTra.setText("0");
                    txtGiamGia.setText("0");
                    txtTienKhachDua.setText("0");
                    labelTienThua.setText("0");

                } else {
                    cbbTrangThaiHoaDon.setSelectedIndex(2);
                    _listHoaDon = _iHoaDonService.getAllHoaDonCho(2);
                    showDataTableHoaDon(_listHoaDon);
                    _listChiTietSanPham = _iChiTietSanPhamService.getAll();
                    showDataTableSanPham(_listChiTietSanPham);
                    txtTenNguoiNhan.setText(null);
                    txtSDT1.setText(null);
                    txtDiaChi.setText(null);
                    labelTongTienHang1.setText("0");
                    labelKhachCanTra1.setText("0");
                    txtGiamGia1.setText("0");
                    txtTienShip.setText("0");
                    txtTienKhachDua1.setText("0");
                    labelTienThua1.setText("0");
                }
            }
        });
    }

    private void filterDataInJTable(String query) {
        TableRowSorter<DefaultTableModel> ts = new TableRowSorter<DefaultTableModel>(dtmSanPham);
        tableSanPham.setRowSorter(ts);

        ts.setRowFilter(RowFilter.regexFilter(query));
    }

    private void loadTienThuaHoaDon() {
        txtTienKhachDua.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                tinhTienThuaHoaDon();
            }
        });
    }

    private void loadTienThuaDatHang() {
        txtTienKhachDua1.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                tinhTienThuaDatHang();
            }
        });
    }

    private QLChiTietSanPham getSanPhamByMa(String ma) {
        for (QLChiTietSanPham sp : _listChiTietSanPham) {
            if (sp.getMaSanPham().equals(ma)) {
                return sp;
            }
        }
        return null;
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

    private void setSoLuongSanPhamKhiXoa(String maSP, int soLuong) {
        for (QLChiTietSanPham ctsp : _listChiTietSanPham) {
            if (ctsp.getMaSanPham().equals(maSP)) {
                ctsp.setSoLuongTonKho(ctsp.getSoLuongTonKho() + soLuong);
            }
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

    private Integer getSoLuongSanPhamBanDau(String ma) {
        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
        for (QLChiTietSanPham ctsp : _listChiTietSanPham) {
            if (ctsp.getMaSanPham().equals(ma)) {
                return ctsp.getSoLuongTonKho();
            }
        }
        return null;
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        cbbTrangThaiHoaDon = new javax.swing.JComboBox<>();
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
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        btnTaoHoaDon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
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
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelTongTienHang1 = new javax.swing.JLabel();
        labelKhachCanTra1 = new javax.swing.JLabel();
        txtTienKhachDua1 = new javax.swing.JTextField();
        cbbHTTT1 = new javax.swing.JComboBox<>();
        labelTienThua1 = new javax.swing.JLabel();
        btnTaoHoaDon1 = new javax.swing.JButton();
        btnThanhToan1 = new javax.swing.JButton();
        btnDaGiao = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtGiamGia1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTienShip = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableGioHang = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        panelWebCam = new javax.swing.JPanel();
        lblQRMaSV = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

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

        jLabel17.setText("Trạng thái");

        cbbTrangThaiHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán", "Đang tạo", "Đang giao", "Đã giao" }));
        cbbTrangThaiHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
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
        });

        jLabel45.setText("Tiền thừa: ");

        labelTienThua.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTienThua.setForeground(new java.awt.Color(255, 0, 0));
        labelTienThua.setText("0");

        jLabel12.setText("HT Thanh toán:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Ví điện tử", "Cà thẻ" }));

        jLabel13.setText("Giảm giá:");

        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyReleased(evt);
            }
        });

        btnTaoHoaDon.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, 0, 201, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(labelTongTienHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelKhachCanTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel45))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienKhachDua)
                                    .addComponent(labelTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(labelTienThua))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
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
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTenNguoiBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT)
                    .addComponent(txtSearchKH))
                .addGap(84, 84, 84))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelTenNguoiBan))
                .addGap(28, 28, 28)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelNgayTao))
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(txtSDT1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(labelTenNguoiBan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNgayTao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(labelTenNguoiBan1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(labelNgayTao1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thanh toán"));

        jLabel6.setText("Tổng tiền hàng:");

        jLabel7.setText("Khách cần trả:");

        jLabel9.setText("Tiền khách đưa:");

        jLabel10.setText("HT Thanh toán:");

        jLabel11.setText("Tiền thừa:");

        labelTongTienHang1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTongTienHang1.setForeground(new java.awt.Color(255, 0, 0));
        labelTongTienHang1.setText("0");

        labelKhachCanTra1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelKhachCanTra1.setForeground(new java.awt.Color(255, 0, 0));
        labelKhachCanTra1.setText("0");

        cbbHTTT1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Ví điện tử", "Cà thẻ" }));

        labelTienThua1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTienThua1.setForeground(new java.awt.Color(255, 0, 0));
        labelTienThua1.setText("0");

        btnTaoHoaDon1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnTaoHoaDon1.setText("Tạo đơn hàng");
        btnTaoHoaDon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDon1ActionPerformed(evt);
            }
        });

        btnThanhToan1.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.icon.hoverBackground"));
        btnThanhToan1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnThanhToan1.setText("Giao hàng");
        btnThanhToan1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        });

        jLabel25.setText("Tiền ship: ");

        txtTienShip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienShipKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnDaGiao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelKhachCanTra1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelTongTienHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGiamGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(cbbHTTT1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTienThua1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTienKhachDua1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                .addComponent(txtTienShip, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addComponent(btnThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelTongTienHang1))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(labelKhachCanTra1))
                .addGap(18, 18, 18)
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
                    .addComponent(txtTienKhachDua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labelTienThua1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbbHTTT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDaGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
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
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
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

        jButton1.setText("EXit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblQRMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(panelDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblQRMaSV)
                                .addGap(12, 12, 12))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(panelDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSanPhamMouseClicked
        // TODO add your handling code here:
        int rowHoaDon = tableHoaDon.getSelectedRow();
        int rowSanPham = tableSanPham.getSelectedRow();
        int rowGioHang = tableGioHang.getSelectedRow();
//        _listHoaDon = _iHoaDonService.getAllHoaDonCho(cbbTrangThaiHoaDon.getSelectedIndex());
//        QLHoaDon getHoaDonIndex1 = _listHoaDon.get(1);
//        String trangThai = String.valueOf(getHoaDonIndex1.getTrangThai());

        String soLuong = JOptionPane.showInputDialog("Nhập số lượng: ", "0");
        if (soLuong == null) {
            JOptionPane.showMessageDialog(this, "Đã hủy");
        } else if (rowHoaDon == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
        } else {
            QLHoaDon hd = _listHoaDon.get(rowHoaDon);
            QLChiTietSanPham sp = getProductByIndex(dtmSanPham.getValueAt(rowSanPham, 1).toString());
            if (soLuong == null) {
                JOptionPane.showMessageDialog(this, "Đã hủy");
            } else {
                if (sp.getSoLuongTonKho() <= 0) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng");
                } else if (Integer.valueOf(soLuong) <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                } else if (Integer.valueOf(soLuong) > sp.getSoLuongTonKho()) {
                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm chỉ còn: " + sp.getSoLuongTonKho());
                } else {
                    QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
                    hdct.setIdHoaDon(hd.getIdHoaDon());
                    hdct.setIdCTSP(sp.getIdCTSP());
                    hdct.setMaSP(sp.getMaSanPham());
                    hdct.setTenSP(sp.getTenSanPham());
                    hdct.setSoLuongMua(Integer.valueOf(soLuong));
                    hdct.setDonGia(sp.getGiaBan());
                    _listHoaDonChiTiet.add(hdct);

                    sp.setSoLuongTonKho(sp.getSoLuongTonKho() - Integer.valueOf(soLuong));
                    maps.put(sp, sp.getSoLuongTonKho());
                    if (!txtSearchSP.getText().isEmpty()) {
                        List<QLChiTietSanPham> listFound = searchByName(txtSearchSP.getText());
                        List<QLChiTietSanPham> listFound1 = searchByMa(txtSearchSP.getText());
                        if (!listFound.isEmpty()) {
                            showDataTableSanPham(listFound);
                        } else {
                            showDataTableSanPham(listFound1);
                        }
                    } else {
                        showDataTableSanPham(_listChiTietSanPham);
                    }

                    showDataTableGioHang(_listHoaDonChiTiet);
                    labelKhachCanTra.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                    labelTongTienHang.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                    labelTongTienHang1.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                    labelKhachCanTra1.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                }
            }
        }
    }//GEN-LAST:event_tableSanPhamMouseClicked

    private QLChiTietSanPham getProductByIndex(String ma) {
        for (QLChiTietSanPham sp : _listChiTietSanPham) {
            if (sp.getMaSanPham().equals(ma)) {
                return sp;
            }
        }
        return null;
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

    }//GEN-LAST:event_tableHoaDonMouseClicked

    private void tableGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGioHangMouseClicked
        // TODO add your handling code here:
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Xóa");
        popupMenu.add(removeItem);
        int row = tableGioHang.getSelectedRow();

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowHoaDon = tableHoaDon.getSelectedRow();
                if (rowHoaDon == -1) {
                    JOptionPane.showMessageDialog(viewBanHang, "Chưa chọn hóa đơn");
                } else if (row >= 0) {
                    String trangThai = dtmHoaDon.getValueAt(rowHoaDon, 5).toString();
                    if (trangThai.equalsIgnoreCase("Đã thanh toán") || trangThai.equalsIgnoreCase("Đã giao") || trangThai.equalsIgnoreCase("Đang giao")) {
                        JOptionPane.showMessageDialog(viewBanHang, "Đơn hàng này đã được thanh toán!");
                    } else {
                        if (row == -1) {
                            JOptionPane.showMessageDialog(viewBanHang, "Chọn sản phẩm cần xóa");
                        } else {
                            String maSP = dtmGioHang.getValueAt(row, 0).toString();
                            String soLuong = dtmGioHang.getValueAt(row, 2).toString();
                            _listHoaDonChiTiet.remove(row);
                            showDataTableGioHang(_listHoaDonChiTiet);
                            setSoLuongSanPhamKhiXoa(maSP, Integer.valueOf(soLuong));
                            if (!txtSearchSP.getText().isEmpty()) {
                                List<QLChiTietSanPham> listFound = searchByName(txtSearchSP.getText());
                                List<QLChiTietSanPham> listFound1 = searchByMa(txtSearchSP.getText());
                                if (!listFound.isEmpty()) {
                                    showDataTableSanPham(listFound);
                                } else {
                                    showDataTableSanPham(listFound1);
                                }
                            } else {
                                showDataTableSanPham(_listChiTietSanPham);
                            }
//                    showDataTableSanPham(_listChiTietSanPham);
                            labelKhachCanTra.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                            labelTongTienHang.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                            labelTongTienHang1.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                            labelKhachCanTra1.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                        }

                    }

                }
            }
        });
        tableGioHang.setComponentPopupMenu(popupMenu);
    }//GEN-LAST:event_tableGioHangMouseClicked

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon();
        hd.setNgayTao(new Date(labelNgayTao.getText()));
        hd.setIdNhanVien("410DDF58-4BDC-4C6A-BB33-21BBDDF3CC72");
        hd.setTrangThai(0);

        JOptionPane.showMessageDialog(this, _iHoaDonService.add(hd));
        _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
        showDataTableHoaDon(_listHoaDon);
        tableHoaDon.setRowSelectionInterval(0, 0);

        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
        showDataTableGioHang(_listHoaDonChiTiet);
        
        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
        showDataTableSanPham(_listChiTietSanPham);
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thanh toán ?");
        if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Đã hủy");
        } else {
            if (_listHoaDonChiTiet.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giỏ hàng chưa có sản phẩm");
            } else {
                int row = tableHoaDon.getSelectedRow();
                QLHoaDon hd = _listHoaDon.get(row);

                KhachHang kh = new KhachHang();
                kh.setHoTen(txtTenKhachHang.getText());
                kh.setSdt(txtSDT.getText());
                KhachHang khachHang = _ikhacHangService.add(kh);

                HoaDon update = new HoaDon();
                update.setIdKhachHang(getKhachHangByMa(khachHang.getMaKhachHang()));
                update.setNgayThanhToan(new Date());
                _iHoaDonService.updateHoaDon(update, hd.getIdHoaDon());

                _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 1);
                JOptionPane.showMessageDialog(this, _iHoaDonChiTietService.addListInvoice(_listHoaDonChiTiet));

                _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
                showDataTableHoaDon(_listHoaDon);

                _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
                showDataTableGioHang(_listHoaDonChiTiet);

                _iChiTietSanPhamService.uppdateSoLuong(maps);

                txtTenKhachHang.setText(null);
                txtSDT.setText(null);
                labelTongTienHang.setText("0");
                labelKhachCanTra.setText("0");
                txtGiamGia.setText("0");
                txtTienKhachDua.setText("0");
                labelTienThua.setText("0");
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private String getKhachHangByMa(String ma) {
        _listKhachHang = _ikhacHangService.getAll();
        for (QLKhachHang kh : _listKhachHang) {
            if (kh.getMaKhachHang().equalsIgnoreCase(ma)) {
                return kh.getIdKhachHang();
            }
        }
        return null;
    }

    private void btnTaoHoaDon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDon1ActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon();
        hd.setNgayTao(new Date());
        hd.setIdNhanVien("410DDF58-4BDC-4C6A-BB33-21BBDDF3CC72");
        hd.setTrangThai(2);

        JOptionPane.showMessageDialog(this, _iHoaDonService.add(hd));
        _listHoaDon = _iHoaDonService.getAllHoaDonCho(2);
        showDataTableHoaDon(_listHoaDon);
        tableHoaDon.setRowSelectionInterval(0, 0);

        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
        showDataTableGioHang(_listHoaDonChiTiet);
        
        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
        showDataTableSanPham(_listChiTietSanPham);
    }//GEN-LAST:event_btnTaoHoaDon1ActionPerformed

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

                _listHoaDon = _iHoaDonService.getAllHoaDonCho(2);
                showDataTableHoaDon(_listHoaDon);

                _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
                showDataTableGioHang(_listHoaDonChiTiet);

                _iChiTietSanPhamService.uppdateSoLuong(maps);

                txtTenNguoiNhan.setText(null);
                txtSDT1.setText(null);
                txtDiaChi.setText(null);
                labelTongTienHang1.setText("0");
                labelKhachCanTra1.setText("0");
                txtGiamGia1.setText("0");
                txtTienShip.setText("0");
                txtTienKhachDua1.setText("0");
                labelTienThua1.setText("0");
            }
        }
    }//GEN-LAST:event_btnThanhToan1ActionPerformed

    private void btnDaGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaGiaoActionPerformed
        // TODO add your handling code here:
        int row = tableHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn");
        } else if (row >= 0) {
            String trangThai = dtmHoaDon.getValueAt(row, 5).toString();
            if (trangThai.equalsIgnoreCase("Đang tạo")) {
                JOptionPane.showMessageDialog(this, "Đơn hàng chưa đi giao");
            } else {
                QLHoaDon hd = _listHoaDon.get(row);
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
                txtTienKhachDua1.setText("0");
                labelTienThua1.setText("0");
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
        List<QLChiTietSanPham> listFound = searchByName(txtSearchSP.getText());
        List<QLChiTietSanPham> listFound1 = searchByMa(txtSearchSP.getText());
        if (!listFound.isEmpty()) {
            showDataTableSanPham(listFound);
        } else {
            showDataTableSanPham(listFound1);
        }

    }//GEN-LAST:event_txtSearchSPKeyReleased

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:
        tinhTienThuaHoaDon();
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void cbbTrangThaiHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiHoaDonActionPerformed
        // TODO add your handling code here:
        int trangThai = cbbTrangThaiHoaDon.getSelectedIndex();
        _listHoaDon = _iHoaDonService.getAllHoaDonCho(trangThai);
        showDataTableHoaDon(_listHoaDon);
        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
        showDataTableGioHang(_listHoaDonChiTiet);
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
        tinhTienCanTraKhiGiamGiaVaPhiShip();
    }//GEN-LAST:event_txtTienShipKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        webcam.close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private List<QLChiTietSanPham> searchByName(String name) {
        List<QLChiTietSanPham> listFound = new ArrayList<>();
        for (QLChiTietSanPham sp : _listChiTietSanPham) {
            if (sp.getTenSanPham().toLowerCase().contains(name.toLowerCase())) {
                listFound.add(sp);
            }
        }
        return listFound;
    }

    private List<QLChiTietSanPham> searchByMa(String ma) {
        List<QLChiTietSanPham> listFound = new ArrayList<>();
        for (QLChiTietSanPham sp : _listChiTietSanPham) {
            if (sp.getMaSanPham().toLowerCase().contains(ma.toLowerCase())) {
                listFound.add(sp);
            }
        }
        return listFound;
    }

    public void showDataTableGioHang(List<QLHoaDonChiTiet> list) {
        dtmGioHang = (DefaultTableModel) tableGioHang.getModel();
        dtmGioHang.setRowCount(0);
        list.forEach(s -> dtmGioHang.addRow(s.toDataRow()));
    }

    private void tinhTienThuaHoaDon() {
        try {
            Double khachCanTra = Double.valueOf(labelKhachCanTra.getText());
            Double tienKhachDua = Double.valueOf(txtTienKhachDua.getText());
            Double tienThua = tienKhachDua - khachCanTra;
            labelTienThua.setText(String.valueOf(tienThua));
        } catch (Exception e) {
        }
    }

    private void tinhTienThuaDatHang() {
        try {
            Double khachCanTra = Double.valueOf(labelKhachCanTra1.getText());
            Double tienKhachDua = Double.valueOf(txtTienKhachDua1.getText());
            Double tienThua = tienKhachDua - khachCanTra;
            labelTienThua1.setText(String.valueOf(tienThua));
        } catch (Exception e) {
        }
    }

    private void tinhTienCanTraKhiGiamGiaHoaDon() {
        try {
            Double tongTien = Double.valueOf(labelTongTienHang.getText());
            Double giamGia = Double.valueOf(txtGiamGia.getText());
            Double khachCanTraKhiGiamGia = tongTien - giamGia;
            labelKhachCanTra.setText(String.valueOf(khachCanTraKhiGiamGia));
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
        try {
            if (txtTienShip.getText().isEmpty()) {
                Double tongTien = Double.valueOf(labelTongTienHang1.getText());
                Double giamGia = Double.valueOf(txtGiamGia1.getText());
                Double khachCanTra = tongTien - giamGia + 0;
                labelKhachCanTra1.setText(String.valueOf(khachCanTra));
            } else if (!txtTienShip.getText().isEmpty()) {
                Double tongTien = Double.valueOf(labelTongTienHang1.getText());
                Double phiShip = Double.valueOf(txtTienShip.getText());
                Double giamGia = Double.valueOf(txtGiamGia1.getText());
                Double khachCanTra = tongTien + phiShip - giamGia;
                labelKhachCanTra1.setText(String.valueOf(khachCanTra));
            }
        } catch (Exception e) {
        }
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
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnTaoHoaDon1;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThanhToan1;
    private javax.swing.JComboBox<String> cbbHTTT1;
    private javax.swing.JComboBox<String> cbbTrangThaiHoaDon;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelKhachCanTra;
    private javax.swing.JLabel labelKhachCanTra1;
    private javax.swing.JLabel labelNgayTao;
    private javax.swing.JLabel labelNgayTao1;
    private javax.swing.JLabel labelTenNguoiBan;
    private javax.swing.JLabel labelTenNguoiBan1;
    private javax.swing.JLabel labelTienThua;
    private javax.swing.JLabel labelTienThua1;
    private javax.swing.JLabel labelTongTienHang;
    private javax.swing.JLabel labelTongTienHang1;
    private javax.swing.JLabel lblQRMaSV;
    private javax.swing.JPanel panelDonHang;
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
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienKhachDua1;
    private javax.swing.JTextField txtTienShip;
    // End of variables declaration//GEN-END:variables

}
