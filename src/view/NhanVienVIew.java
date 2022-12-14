package view;

import DomainModels.NhanVien;
import Repository.INhanVienRepository;
import Repository.Impl.NhanVienRepository;
import Services.IChucVuServicer;
import Services.INhanVienServicer;
import Services.Impl.ChucVuServicer;
import Services.Impl.NhanVienServicer;
import ViewModel.QLChucVu;
import ViewModel.QLNhanVien;
import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NhanVienVIew extends javax.swing.JFrame {

    NhanVienServicer nhanVienServicer;
    ChucVuServicer cvServicer;
    NhanVienRepository nhanVienRepository = new NhanVienRepository();
    DefaultComboBoxModel dcbmd;

    public NhanVienVIew() {
        nhanVienServicer = new NhanVienServicer();
        cvServicer = new ChucVuServicer();
        initComponents();
        loadTable();
        loadChucVuCBB();

    }

    public void loadChucVuCBB() {
        dcbmd = (DefaultComboBoxModel) cboChucVu.getModel();
        List<QLChucVu> listCbbCV = cvServicer.getList();
        ArrayList<String> listTenCV = new ArrayList<>();
        for (QLChucVu qlCV : listCbbCV) {
            listTenCV.add(qlCV.getChucVu());
        }
        for (String o : listTenCV) {
            dcbmd.addElement(o);
        }

    }

    public void loadTable() {
        DefaultTableModel dtm = (DefaultTableModel) tableNhanVien.getModel();
        dtm.setRowCount(0);
        for (QLNhanVien QLnv : this.nhanVienServicer.getList()) {
            Object rowData[] = {
                QLnv.getMaNV(),
                QLnv.getTenNV(),
                QLnv.getTenChucVu(),
                QLnv.getDiaChi(),
                QLnv.getsDT(),
                QLnv.getGioiTinh(),
                QLnv.getNgaySinh(),
                QLnv.getMatKhau(),
                QLnv.getTrangThai(),};
            dtm.addRow(rowData);
        }

        jDateChooser1.setDate(Calendar.getInstance().getTime());

    }

    @SuppressWarnings("unchecked")

    boolean checkTen(String text) {
        Pattern pattern = Pattern.compile("[a-zA-Z ]{6,}");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public boolean checkSDT() {
        Pattern p1 = Pattern.compile("^0\\d{9}$");
        Matcher matcher1 = p1.matcher(txtSdt.getText().trim());
        if (matcher1.matches() == false) {

            return false;
        }
        return true;
    }

    public boolean checNgay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date now = new Date();
        String Ygetdate = sdf.format(now);
        String jdate = sdf.format(jDateChooser1.getDate());

        int yerGetDate = Integer.parseInt(Ygetdate);
        int yerJdate = Integer.parseInt(jdate);
        int tuoi = yerGetDate - yerJdate;
        if (tuoi > 18) {
            return true;

        } else {
            return false;
        }

    }

//public void run() {
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        while (true) {
//            Date now = new Date();
//            String time = sdf.format(now);
//            lbTime.setText(time);
//        }
//        
//        
//    }
    public QLNhanVien getData() {

        String id = lbMaNhanVien.getText();

        String ten = txtTen.getText().trim();
        String diaChi = txtdiaChi.getText().trim();
        String sDt = txtSdt.getText().trim();
        String gioiTinh;
        String matKhau = txtMK.getText().trim();
        Integer trangThai = chkTrangThai.isSelected() ? 1 : 0;
        String tenChucVu;

        //gioi tinh
        if (rdoNam.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }

        //getdate
        Date ngaySinh = jDateChooser1.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhString = dateFormat.format(ngaySinh);

        if (cboChucVu.getSelectedIndex() == 0) {
            tenChucVu = "NhanVien";
        } else {
            tenChucVu = "Admin";
        }
        // check trống
        if (ten.isBlank() || diaChi.isEmpty() || sDt.isEmpty() || matKhau.isEmpty() || ngaySinh == null) {
            if (ten.isEmpty()) {
                txtTen.setBackground(Color.yellow);

            }
            if (diaChi.isEmpty()) {
                txtdiaChi.setBackground(Color.yellow);
            }
            if (sDt.isEmpty()) {
                txtSdt.setBackground(Color.yellow);
            }
            if (matKhau.isEmpty()) {
                txtMK.setBackground(Color.yellow);
            }
            if (ngaySinh == null) {
                jDateChooser1.setBackground(Color.yellow);
            }

            JOptionPane.showMessageDialog(this, "Trường Thông tin Màu Vàng đang Trống");
            return null;
        }
        if (!checkTen(ten)) {
            JOptionPane.showMessageDialog(this, "Tên ít nhất phải có 6 kí tự");
            return null;
        }

        try {
            int sdtSo = Integer.parseInt(sDt);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Phải là số");
            return null;
        }
        if (checkSDT() == false) {
            JOptionPane.showMessageDialog(this, "sdt khong dung dinh dang(sdt gồm 10 số bắt đầu số 0)");
            return null;
        }
        if (checNgay() == false) {
            JOptionPane.showMessageDialog(this, "Nhân Viên phải đủ 18 tuổi !!!");
            return null;

        }

        QLNhanVien qlNV = new QLNhanVien(id, "", ten, tenChucVu, diaChi, sDt, gioiTinh, ngaySinhString, matKhau, trangThai);
        return qlNV;

    }

    public void clearForm() {
        lbMaNhanVien.setText("-------------------------------");
        txtTen.setText("");
        txtdiaChi.setText("");
        txtSdt.setText("");
        rdoNam.setSelected(true);
        jDateChooser1.setDate(Calendar.getInstance().getTime());
        txtMK.setText("");
        cboChucVu.setSelectedIndex(0);
        chkTrangThai.setSelected(false);

        txtTen.setBackground(Color.WHITE);
        txtdiaChi.setBackground(Color.WHITE);
        txtSdt.setBackground(Color.WHITE);
        txtMK.setBackground(Color.WHITE);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNhanVien = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbMaNhanVien = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtdiaChi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtMK = new javax.swing.JTextField();
        cboChucVu = new javax.swing.JComboBox<>();
        chkTrangThai = new javax.swing.JCheckBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(0, 0));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1650, 1080));

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông Tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 0, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 0, 255));
        btnSua.setText("Sửa ");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 0, 255));
        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnClear.setForeground(new java.awt.Color(51, 51, 255));
        btnClear.setText("Refresh");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnClear)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Chức Vụ", "Địa Chỉ", "SĐT", "Giới Tính", "Ngày Sinh", "Mật Khẩu", "Trạng Thái"
            }
        ));
        tableNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNhanVien);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Staff Managerment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        lbMaNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMaNhanVien.setText("-------------------------------");

        txtTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Mã Nhân Viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tên Nhân Viên");

        txtdiaChi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiaChiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Địa chỉ");

        txtSdt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("SĐT");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Giới Tính");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Ngày Sinh");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Mật Khẩu");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Chức Vụ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Trạng Thái");

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rdoNu.setText("Nữ");

        txtMK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKActionPerformed(evt);
            }
        });

        cboChucVu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cboChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVuActionPerformed(evt);
            }
        });

        chkTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chkTrangThai.setSelected(true);
        chkTrangThai.setText(" Hoạt Động");

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtdiaChi, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMaNhanVien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(txtSdt))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(rdoNam)
                                .addGap(40, 40, 40)
                                .addComponent(rdoNu))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtMK, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboChucVu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))))
                    .addComponent(chkTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoNam)
                                .addComponent(rdoNu))
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbMaNhanVien)
                            .addComponent(jLabel2))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtdiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(chkTrangThai))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 45, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        btnThem.setBackground(Color.orange);
        btnSua.setBackground(Color.white);
        btnXoa.setBackground(Color.white);
        btnClear.setBackground(Color.white);

        QLNhanVien qlNV = getData();
        if (qlNV == null) {
            //JOptionPane.showMessageDialog(this, "Thêm KHÔNG Thành Công!!!!", "Thông Báo", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String idcv = nhanVienRepository.getIDChucVu(qlNV.getTenChucVu());
            NhanVien nv = new NhanVien("", "", qlNV.getTenNV(), qlNV.getDiaChi(), qlNV.getsDT(), qlNV.getGioiTinh(), qlNV.getNgaySinh(), qlNV.getMatKhau(), qlNV.getTrangThai(), idcv);
            nhanVienServicer.insert(nv);
            JOptionPane.showMessageDialog(this, "Thêm Thành Công!!!!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
            clearForm();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        btnThem.setBackground(Color.white);
        btnSua.setBackground(Color.orange);
        btnXoa.setBackground(Color.white);
        btnClear.setBackground(Color.white);

        QLNhanVien qlNV = getData();
        if (qlNV == null) {
            return;
        }
        String maNV = lbMaNhanVien.getText().trim();
        String idCV = this.nhanVienRepository.getIDChucVu(qlNV.getTenChucVu());
        NhanVien nv = new NhanVien("", qlNV.getMaNV(), qlNV.getTenNV(), qlNV.getDiaChi(), qlNV.getsDT(), qlNV.getGioiTinh(), qlNV.getNgaySinh(), qlNV.getMatKhau(), qlNV.getTrangThai(), idCV);

        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không", "Xác Nhận", JOptionPane.OK_CANCEL_OPTION) == 0) {
            if (maNV.equals("-------------------------------")) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bên dưới bảng để update???", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.nhanVienServicer.update(nv, maNV);
                JOptionPane.showMessageDialog(this, "Đã Sửa Thành Công!!!!");
                loadTable();
                clearForm();
            }
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        btnThem.setBackground(Color.white);
        btnSua.setBackground(Color.white);
        btnXoa.setBackground(Color.orange);
        btnClear.setBackground(Color.white);
        String maNV = lbMaNhanVien.getText();

        if (maNV.equals("-------------------------------")) {
            JOptionPane.showMessageDialog(this, "Click vào Nhân Viên Để Xóa Nhân Viên");

        } else if (JOptionPane.showConfirmDialog(this, "Xác Nhận ", "Xóa", JOptionPane.OK_CANCEL_OPTION) == 0) {

            if (nhanVienServicer.deleteBoolean(maNV) == true) {
                JOptionPane.showMessageDialog(this, "Đã Xoá Thành Công!!!!");
            } else {
                JOptionPane.showMessageDialog(this, "Nhân Viên Đang có trong 'Hóa Đơn' Khổng Thể xóa ");
            }

            loadTable();
            clearForm();
        }


    }//GEN-LAST:event_btnXoaActionPerformed

    private void tableNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhanVienMouseClicked

        int row = tableNhanVien.getSelectedRow();

        if (row == -1) {
            return;
        }
        String ma = tableNhanVien.getValueAt(row, 0).toString();

        String ten = tableNhanVien.getValueAt(row, 1).toString();
        String chucVu = tableNhanVien.getValueAt(row, 2).toString();
        if (chucVu.equals("NhanVien")) {
            cboChucVu.setSelectedIndex(0);
        } else {
            cboChucVu.setSelectedIndex(0);
        }

        String diaChi = tableNhanVien.getValueAt(row, 3).toString();
        String sdt = tableNhanVien.getValueAt(row, 4).toString();
        String gioiTinh = tableNhanVien.getValueAt(row, 5).toString();
        String ngaySinh = tableNhanVien.getValueAt(row, 6).toString();

        String mk = tableNhanVien.getValueAt(row, 7).toString();
        String trangThai = tableNhanVien.getValueAt(row, 8).toString();

        if (gioiTinh.equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        lbMaNhanVien.setText(ma);

        txtTen.setText(ten);
        txtdiaChi.setText(diaChi);
        txtSdt.setText(sdt);

        txtMK.setText(mk);

        if (trangThai.equals("1")) {
            chkTrangThai.setSelected(true);
        }
        if (trangThai.equals("0")) {
            chkTrangThai.setSelected(false);
        }


    }//GEN-LAST:event_tableNhanVienMouseClicked

    private void txtMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMKActionPerformed

    private void cboChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChucVuActionPerformed

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        btnThem.setBackground(Color.white);
        btnSua.setBackground(Color.white);
        btnXoa.setBackground(Color.white);
        btnClear.setBackground(Color.orange);

        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtdiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiaChiActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int check = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn trở lại màn hình chính !!!!", "Trờ lại màn hình chính", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (check == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienVIew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienVIew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienVIew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienVIew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienVIew().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JCheckBox chkTrangThai;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel lbMaNhanVien;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tableNhanVien;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtdiaChi;
    // End of variables declaration//GEN-END:variables

}
