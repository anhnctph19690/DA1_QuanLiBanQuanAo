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
import ViewModel.QLKhachHang;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Locale;
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
    NhanVienRepository nhanVienRepository = new NhanVienRepository();
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

    private void selectedTableHoaDon() {
        _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
        if (!_listHoaDon.isEmpty()) {
            tableHoaDon.setRowSelectionInterval(0, 0);
        }
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
                String soLuong = JOptionPane.showInputDialog("Nhập số lượng: ", "0");
                String maSP = result.getText();
                QLChiTietSanPham sp = getSanPhamByMaQr(maSP);
                int rowHoaDon = tableHoaDon.getSelectedRow();
                if (rowHoaDon == -1) {
                    JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơm");
                } else if (soLuong == null) {
                    JOptionPane.showMessageDialog(this, "Đã hủy");
                } else {
                    if (sp.getSoLuongTonKho() <= 0) {
                        JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng");
                    } else if (Integer.valueOf(soLuong) <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                    } else if (Integer.valueOf(soLuong) > sp.getSoLuongTonKho()) {
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm chỉ còn: " + sp.getSoLuongTonKho());
                    } else {
                        QLHoaDon hd = _listHoaDon.get(rowHoaDon);
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
//
//                if (soLuong == null) {
//                    JOptionPane.showMessageDialog(this, "Đã hủy");
//                } else {
//                    QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
//                    hdct.setIdCTSP(ctsp.getIdCTSP());
//                    hdct.setMaSP(ctsp.getMaSanPham());
//                    hdct.setTenSP(ctsp.getTenSanPham());
//                    hdct.setSoLuongMua(Integer.valueOf(soLuong));
//                    hdct.setDonGia(ctsp.getGiaBan());
//                    _listHoaDonChiTiet.add(hdct);
//                    showDataTableGioHang(_listHoaDonChiTiet);
//                }
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
        setDateDefault();
        selectedTableHoaDon();
//        onlyNumberTotal();
    }

    private String getDate() {
        Locale locale = new Locale("vi", "VN");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        String date = dateFormat.format(new Date());
        return date;
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
                    labelNgayTao.setText(String.valueOf(getDate()));
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
                    labelNgayTao1.setText(String.valueOf(getDate()));
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
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableGioHang = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        panelWebCam = new javax.swing.JPanel();
        lblQRMaSV = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        cbbTrangThaiHoaDon = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSanPham = new javax.swing.JTable();
        txtSearchSP = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
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
        btnThanhToan = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
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
        jLabel24 = new javax.swing.JLabel();
        txtGiamGia1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTienShip = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnDaGiao = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(1650, 1090));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tableGioHang.setBackground(new java.awt.Color(255, 255, 255));
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

        btnClear.setBackground(new java.awt.Color(255, 255, 255));
        btnClear.setForeground(new java.awt.Color(51, 51, 255));
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        panelWebCam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        panelWebCam.setLayout(new javax.swing.BoxLayout(panelWebCam, javax.swing.BoxLayout.LINE_AXIS));

        lblQRMaSV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQRMaSV.setText("-----");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQRMaSV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelWebCam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblQRMaSV)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tableHoaDon.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Trạng thái");

        cbbTrangThaiHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        cbbTrangThaiHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbTrangThaiHoaDon.setForeground(new java.awt.Color(51, 51, 255));
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(58, 58, 58)
                .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tableSanPham.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Tìm Kiếm Sản Phẩm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel16)
                        .addGap(198, 198, 198)
                        .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelDonHang.setBackground(new java.awt.Color(255, 255, 255));
        panelDonHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setForeground(new java.awt.Color(51, 51, 255));
        tabbedPane.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(51, 51, 255));
        jPanel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thanh toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("Tổng tiền hàng: ");

        labelTongTienHang.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTongTienHang.setForeground(new java.awt.Color(255, 0, 0));
        labelTongTienHang.setText("0");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setText("Khách cần trả:");

        labelKhachCanTra.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelKhachCanTra.setForeground(new java.awt.Color(255, 0, 0));
        labelKhachCanTra.setText("0");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setText("Tiền khách đưa:");

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setText("Tiền thừa: ");

        labelTienThua.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTienThua.setForeground(new java.awt.Color(255, 0, 0));
        labelTienThua.setText("0");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("HT Thanh toán:");

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(51, 51, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Ví điện tử", "Cà thẻ" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Giảm giá:");

        txtGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyReleased(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(0, 0, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        btnTaoHoaDon.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        btnTaoHoaDon.setForeground(new java.awt.Color(51, 51, 255));
        btnTaoHoaDon.setText("Tạo Đơn Hàng");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel44)
                                .addComponent(jLabel45))
                            .addGap(58, 58, 58)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTienKhachDua)
                                .addComponent(labelTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel42)
                                .addComponent(jLabel43)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(53, 53, 53)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtGiamGia)
                                .addComponent(labelKhachCanTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelTongTienHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(92, 92, 92))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tên người bán:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Ngày tạo:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Tên khách hàng:");

        labelTenNguoiBan.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelTenNguoiBan.setForeground(new java.awt.Color(0, 51, 255));
        labelTenNguoiBan.setText("..");

        labelNgayTao.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelNgayTao.setForeground(new java.awt.Color(0, 51, 255));
        labelNgayTao.setText("..");

        txtTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("SĐT:");

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Tìm khách hàng:");

        txtSearchKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(labelNgayTao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchKH)
                    .addComponent(labelTenNguoiBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 564, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Hóa đơn", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thông tin"), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tên người nhận:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("SĐT:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Địa chỉ:");

        txtTenNguoiNhan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtSDT1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Tên người bán:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Ngày tạo:");

        labelTenNguoiBan1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTenNguoiBan1.setForeground(new java.awt.Color(0, 0, 255));
        labelTenNguoiBan1.setText("..");

        labelNgayTao1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelNgayTao1.setForeground(new java.awt.Color(0, 0, 255));
        labelNgayTao1.setText("..");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Ngày hẹn:");

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNgayTao1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTenNguoiBan1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTenNguoiBan1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(labelNgayTao1))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thanh toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tổng tiền hàng:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Khách cần trả:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Tiền khách đưa:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("HT Thanh toán:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tiền thừa:");

        labelTongTienHang1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTongTienHang1.setForeground(new java.awt.Color(255, 0, 0));
        labelTongTienHang1.setText("0");

        labelKhachCanTra1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelKhachCanTra1.setForeground(new java.awt.Color(255, 0, 0));
        labelKhachCanTra1.setText("0");

        txtTienKhachDua1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cbbHTTT1.setBackground(new java.awt.Color(255, 255, 255));
        cbbHTTT1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbbHTTT1.setForeground(new java.awt.Color(51, 51, 255));
        cbbHTTT1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Ví điện tử", "Cà thẻ" }));

        labelTienThua1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelTienThua1.setForeground(new java.awt.Color(255, 0, 0));
        labelTienThua1.setText("0");

        btnTaoHoaDon1.setBackground(new java.awt.Color(255, 255, 255));
        btnTaoHoaDon1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        btnTaoHoaDon1.setForeground(new java.awt.Color(51, 51, 255));
        btnTaoHoaDon1.setText("Tạo đơn hàng");
        btnTaoHoaDon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDon1ActionPerformed(evt);
            }
        });

        btnThanhToan1.setBackground(new java.awt.Color(255, 255, 255));
        btnThanhToan1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        btnThanhToan1.setForeground(new java.awt.Color(51, 51, 255));
        btnThanhToan1.setText("Giao hàng");
        btnThanhToan1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThanhToan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan1ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Giảm giá: ");

        txtGiamGia1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtGiamGia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamGia1KeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Tiền ship: ");

        txtTienShip.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTienShip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienShipKeyReleased(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 255));
        jButton2.setText("Chờ Giao hàng");

        btnDaGiao.setBackground(new java.awt.Color(255, 255, 255));
        btnDaGiao.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        btnDaGiao.setForeground(new java.awt.Color(51, 51, 255));
        btnDaGiao.setText("Đã giao");
        btnDaGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaGiaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnTaoHoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(45, 45, 45)
                                .addComponent(labelTongTienHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelKhachCanTra1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiamGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbHTTT1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTienKhachDua1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                        .addComponent(txtTienShip)
                                        .addComponent(labelTienThua1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThanhToan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addComponent(btnDaGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtGiamGia1))
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
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbbHTTT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTaoHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDaGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Đặt hàng", jPanel10);

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDonHangLayout = new javax.swing.GroupLayout(panelDonHang);
        panelDonHang.setLayout(panelDonHangLayout);
        panelDonHangLayout.setHorizontalGroup(
            panelDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDonHangLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelDonHangLayout.setVerticalGroup(
            panelDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDonHangLayout.createSequentialGroup()
                .addComponent(tabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 84, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(41, 41, 41)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
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
            HoaDon insert = new HoaDon();
            insert.setNgayTao(new Date(labelNgayTao.getText()));
            insert.setIdNhanVien("99DDEF77-227A-4937-8D2D-9BEFEF840158");
            insert.setTrangThai(0);

            JOptionPane.showMessageDialog(this, _iHoaDonService.add(insert));
            _listHoaDon = _iHoaDonService.getAllHoaDonCho(0);
            showDataTableHoaDon(_listHoaDon);
            tableHoaDon.setRowSelectionInterval(0, 0);

            QLHoaDonChiTiet hdct = new QLHoaDonChiTiet();
            QLChiTietSanPham sp = getProductByIndex(dtmSanPham.getValueAt(rowSanPham, 1).toString());
            hdct.setIdHoaDon(insert.getIdHoaDon());
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

        NhanVien nv = this.nhanVienRepository.getNhanVienByStatus(3);

        HoaDon hd = new HoaDon();
        hd.setNgayTao(new Date());
        hd.setIdNhanVien("410DDF58-4BDC-4C6A-BB33-21BBDDF3CC72");
        hd.setTrangThai(0);

        JOptionPane.showMessageDialog(this, _iHoaDonService.add(hd));
        labelNgayTao.setText(String.valueOf(getDate()));
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
        labelNgayTao1.setText(String.valueOf(getDate()));
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

        int check = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn trở lại màn hình chính !!!!", "Trờ lại màn hình chính", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (check == JOptionPane.YES_OPTION) {
            this.dispose();
            webcam.close();
        }


    }//GEN-LAST:event_jButton1ActionPerformed
    private boolean gained = false;
    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:\
        gained = true;
    }//GEN-LAST:event_formFocusGained

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // TODO add your handling code here:
        if (gained) {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn trở lại màn hình chính !!!!", "Trờ lại màn hình chính", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (check == JOptionPane.YES_OPTION) {
                this.dispose();
                webcam.close();
            }
        }
    }//GEN-LAST:event_formFocusLost

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
                if ("Windows".equals(info.getName())) {
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
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
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
