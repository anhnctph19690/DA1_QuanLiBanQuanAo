/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Models.HoaDon;
import Models.HoaDonChiTiet;
import Models.NhanVien;
import Models.SanPham;
import Repository.IHoaDonChiTietRepository;
import Repository.Impl.HoaDonRepository;
import Repository.Impl.NhanVienRepository;
import Repository.Impl.SanPhamRepository;
import Services.IChiTietSanPhamService;
import Services.IHoaDonChiTietService;
import Services.IHoaDonService;
import Services.Impl.ChiTietSanPhamService;
import Services.Impl.HoaDonChiTietService;
import Services.Impl.HoaDonService;
import Services.Impl.NhanVienServicer;
import ViewModel.QLChiTietSanPham;
import ViewModel.QLHoaDon;
import ViewModel.QLHoaDonChiTiet;
import ViewModel.QLNhanVien;
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
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC- ASUS
 */
public class ViewBanHang extends javax.swing.JFrame implements Runnable, ThreadFactory{

    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    /**
     * Creates new form From_BanHang
     */
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
    static ViewBanHang viewBanHang;

     private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel9.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));

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
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
    
    public ViewBanHang() {
        viewBanHang = this;
        initComponents();
        initWebcam();
        _iChiTietSanPhamService = new ChiTietSanPhamService();
        _iHoaDonChiTietService = new HoaDonChiTietService();
        _iHoaDonService = new HoaDonService();
        _listChiTietSanPham = new ArrayList<>();
        _listHoaDon = new ArrayList<>();
        _listHoaDonChiTiet = new ArrayList<>();

        _listHoaDon = _iHoaDonService.getAll();
        showDataTableHoaDon(_listHoaDon);

        _listChiTietSanPham = _iChiTietSanPhamService.getAll();
        showDataTableSanPham(_listChiTietSanPham);

        loadTienThuaHoaDon();
        loadTienThuaDatHang();
//        onlyNumberTotal();
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

    public void showDataTableSanPham(List<QLChiTietSanPham> list) {
        tableSanPham.getTableHeader().setDefaultRenderer(new HeaderColor());
        dtmSanPham = (DefaultTableModel) tableSanPham.getModel();
        dtmSanPham.setRowCount(0);
        list.forEach(s -> dtmSanPham.addRow(s.toDataRowBanHang()));
    }

    public void showDataTableHoaDon(List<QLHoaDon> list) {
//        tableHoaDon.getTableHeader().setDefaultRenderer(new HeaderColor());
        dtmHoaDon = (DefaultTableModel) tableHoaDon.getModel();
        dtmHoaDon.setRowCount(0);
        list.forEach(s -> dtmHoaDon.addRow(s.toDataRow()));
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSanPham = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        panelDonHang = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        btnTaoHoaDon = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        labelTongTienHang = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        labelKhachCanTra = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        labelTienThua = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenNguoiNhan = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnTaoHoaDon1 = new javax.swing.JButton();
        btnThanhToan1 = new javax.swing.JButton();
        btnDaGiao = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelTongTienHang1 = new javax.swing.JLabel();
        labelKhachCanTra1 = new javax.swing.JLabel();
        txtTienKhachDua1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        labelTienThua1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableGioHang = new javax.swing.JTable();
        lblQRMaSV = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        panelDonHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn hàng"));

        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel42.setText("Tổng tiền hàng: ");

        labelTongTienHang.setText("0");

        jLabel43.setText("Khách cần trả:");

        labelKhachCanTra.setText("0");

        jLabel44.setText("Tiền khách đưa:");

        jLabel45.setText("Tiền thừa: ");

        labelTienThua.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelKhachCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jLabel44)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(labelTienThua))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(btnTaoHoaDon)
                .addGap(73, 73, 73)
                .addComponent(btnThanhToan)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon)
                    .addComponent(btnThanhToan))
                .addContainerGap(487, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa đơn", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153))));

        jLabel1.setText("Tên người nhận:");

        jLabel3.setText("SĐT:");

        jLabel4.setText("Địa chỉ:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        btnTaoHoaDon1.setText("Tạo hóa đơn");
        btnTaoHoaDon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDon1ActionPerformed(evt);
            }
        });

        btnThanhToan1.setText("Giao hàng");
        btnThanhToan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan1ActionPerformed(evt);
            }
        });

        btnDaGiao.setText("Đã giao");
        btnDaGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaGiaoActionPerformed(evt);
            }
        });

        btnexit.setText("Exit");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnTaoHoaDon1)
                .addGap(33, 33, 33)
                .addComponent(btnThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnDaGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnexit)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon1)
                    .addComponent(btnThanhToan1)
                    .addComponent(btnDaGiao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(btnexit)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel6.setText("Tổng tiền hàng:");

        jLabel7.setText("Khách cần trả:");

        jLabel9.setText("Tiền khách đưa:");

        jLabel10.setText("HT Thanh toán:");

        jLabel11.setText("Tiền thừa:");

        labelTongTienHang1.setText("0");

        labelKhachCanTra1.setText("0");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Ví điện tử" }));

        labelTienThua1.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelKhachCanTra1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(labelTongTienHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTienThua1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienKhachDua1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTienKhachDua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labelTienThua1))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đặt hàng", jPanel10);

        javax.swing.GroupLayout panelDonHangLayout = new javax.swing.GroupLayout(panelDonHang);
        panelDonHang.setLayout(panelDonHangLayout);
        panelDonHangLayout.setHorizontalGroup(
            panelDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        panelDonHangLayout.setVerticalGroup(
            panelDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
        );

        lblQRMaSV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblQRMaSV.setText("------------");

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQRMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(panelDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblQRMaSV))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(jLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSanPhamMouseClicked
        // TODO add your handling code here:
        int rowHoaDon = tableHoaDon.getSelectedRow();
        int rowSanPham = tableSanPham.getSelectedRow();
        int rowGioHang = tableGioHang.getSelectedRow();

        if (rowHoaDon == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn");
        } else {
            QLHoaDon hd = _listHoaDon.get(rowHoaDon);
            QLChiTietSanPham sp = _listChiTietSanPham.get(rowSanPham);
            String soLuong = JOptionPane.showInputDialog("Nhập số lượng: ", "0");
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
                    showDataTableSanPham(_listChiTietSanPham);
                    soLuongConLai = sp.getSoLuongTonKho();

                    showDataTableGioHang(_listHoaDonChiTiet);
                    labelTongTienHang.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                    labelKhachCanTra.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                    labelTongTienHang1.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                    labelKhachCanTra1.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                }
            }
        }
    }//GEN-LAST:event_tableSanPhamMouseClicked

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
        JMenuItem removeItem = new JMenuItem("Xóa sản phẩm");
        popupMenu.add(removeItem);

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableGioHang.getSelectedRow();
//                dtmGioHang.removeRow(row);
                if (row == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Chọn sản phẩm cần xóa");
                } else {
                    _listHoaDonChiTiet.remove(row);
                    showDataTableGioHang(_listHoaDonChiTiet);
                    labelKhachCanTra.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                    labelTongTienHang.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                    labelTongTienHang1.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                    labelKhachCanTra1.setText(String.valueOf(_iHoaDonChiTietService.totalMoneyOfInvoice(_listHoaDonChiTiet)));
                }
            }
        });
        tableGioHang.setComponentPopupMenu(popupMenu);
    }//GEN-LAST:event_tableGioHangMouseClicked

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon();
        hd.setNgayTao(new Date());
        hd.setIdNhanVien("6434928A-874A-4C8B-84C2-ACC343E91400");
        hd.setTrangThai(0);

        JOptionPane.showMessageDialog(this, _iHoaDonService.add(hd));
        _listHoaDon = _iHoaDonService.getAll();
        showDataTableHoaDon(_listHoaDon);
        tableHoaDon.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        JOptionPane.showMessageDialog(this, _iHoaDonChiTietService.addListInvoice(_listHoaDonChiTiet));
        int row = tableHoaDon.getSelectedRow();
        QLHoaDon hd = _listHoaDon.get(row);
        _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 1);
        _listHoaDon = _iHoaDonService.getAll();
        showDataTableHoaDon(_listHoaDon);
        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
        showDataTableGioHang(_listHoaDonChiTiet);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTaoHoaDon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDon1ActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon();
        hd.setNgayTao(new Date());
        hd.setIdNhanVien("6434928A-874A-4C8B-84C2-ACC343E91400");
        hd.setTrangThai(2);

        JOptionPane.showMessageDialog(this, _iHoaDonService.add(hd));
        _listHoaDon = _iHoaDonService.getAll();
        showDataTableHoaDon(_listHoaDon);
        tableHoaDon.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnTaoHoaDon1ActionPerformed

    private void btnThanhToan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, _iHoaDonChiTietService.addListInvoice(_listHoaDonChiTiet));
        int row = tableHoaDon.getSelectedRow();
        QLHoaDon hd = _listHoaDon.get(row);
        _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 3);
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTenNguoiNhan(txtTenNguoiNhan.getText());
        hoaDon.setDiaChi(txtDiaChi.getText());
        hoaDon.setsDT(txtSDT.getText());
        _iHoaDonService.updateHoaDonGiaoHang(hoaDon, hd.getIdHoaDon());
        _listHoaDon = _iHoaDonService.getAll();
        showDataTableHoaDon(_listHoaDon);
        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
        showDataTableGioHang(_listHoaDonChiTiet);
    }//GEN-LAST:event_btnThanhToan1ActionPerformed

    private void btnDaGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaGiaoActionPerformed
        // TODO add your handling code here:
        int row = tableHoaDon.getSelectedRow();
        QLHoaDon hd = _listHoaDon.get(row);
        _iHoaDonService.uppdateTrangThai(hd.getIdHoaDon(), 4);
        _listHoaDon = _iHoaDonService.getAll();
        showDataTableHoaDon(_listHoaDon);
        _listHoaDonChiTiet.removeAll(_listHoaDonChiTiet);
        showDataTableGioHang(_listHoaDonChiTiet);
        JOptionPane.showMessageDialog(this, "Đã giao thành công");
    }//GEN-LAST:event_btnDaGiaoActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:\
       this.dispose();
    }//GEN-LAST:event_btnexitActionPerformed

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
            labelTienThua.setText(Double.valueOf(tienThua) + " VNĐ");
        } catch (Exception e) {
        }
    }

    private void tinhTienThuaDatHang() {
        try {
            Double khachCanTra = Double.valueOf(labelKhachCanTra1.getText());
            Double tienKhachDua = Double.valueOf(txtTienKhachDua1.getText());
            Double tienThua = tienKhachDua - khachCanTra;
            labelTienThua1.setText(Double.valueOf(tienThua) + " VNĐ");
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
            java.util.logging.Logger.getLogger(ViewBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new ViewBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDaGiao;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnTaoHoaDon1;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThanhToan1;
    private javax.swing.JButton btnexit;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelKhachCanTra;
    private javax.swing.JLabel labelKhachCanTra1;
    private javax.swing.JLabel labelTienThua;
    private javax.swing.JLabel labelTienThua1;
    private javax.swing.JLabel labelTongTienHang;
    private javax.swing.JLabel labelTongTienHang1;
    private javax.swing.JLabel lblQRMaSV;
    private javax.swing.JPanel panelDonHang;
    private javax.swing.JTable tableGioHang;
    private javax.swing.JTable tableHoaDon;
    private javax.swing.JTable tableSanPham;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNguoiNhan;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienKhachDua1;
    // End of variables declaration//GEN-END:variables

}
