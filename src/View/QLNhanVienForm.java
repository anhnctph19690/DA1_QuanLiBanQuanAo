package View;

import Servicer.INhanVienServicer;
import Servicer.Impl.NhanVienServicer;
import ViewModel.QLNhanVien;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QLNhanVienForm extends javax.swing.JFrame {

    INhanVienServicer nhanVienServicer;

    public QLNhanVienForm() {
        nhanVienServicer = new NhanVienServicer();
        initComponents();
        loadTable();
    }

    public void loadTable() {
        DefaultTableModel dtm = (DefaultTableModel) tableNhanVien.getModel();
        dtm.setRowCount(0);
        for (QLNhanVien QLnv : this.nhanVienServicer.getList()) {
            Object rowData[] = {
                QLnv.getIdNhanVien(),
                QLnv.getMaNV(),
                QLnv.getTenNV(),
                QLnv.getDiaChi(),
                QLnv.getsDT(),
                QLnv.getGioiTinh(),
                QLnv.getNgaySinh(),
                QLnv.getMatKhau(),
                QLnv.getTrangThai(),
                QLnv.getIdCV(),};
            dtm.addRow(rowData);
        }

    }

    @SuppressWarnings("unchecked")

    public QLNhanVien getData() {
        String id = lbID.getText();
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String diaChi = txtdiaChi.getText().trim();
        String sDt = txtSdt.getText().trim();
        String gioiTinh = txtGioiTinh.getText().trim();
        String ngaySinh = txtNam.getText().trim()
                + "-" + txtThang.getText().trim() + "-" + txtNgay.getText().trim();

        String matKhau = txtMK.getText().trim();
        String idcv = txtIDCV.getText().trim();
        Integer trangThai = -1;
        if (checkTT.isSelected()) {
            trangThai = 0;
        }

        if (ma.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Mã Trống");
            return null;
        } else if (ten.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Tên Trống");
            return null;
        } else if (diaChi.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Địa chỉ Nhân Viên trống");
            return null;
        } else if (sDt.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Số điện Thoại Trống");
            return null;
        } else if (gioiTinh.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Giới Tính Trống");
            return null;
        } else if (ngaySinh.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Ngày Sinh Trống");
            return null;
        } else if (idcv.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "ID Chức vụ Trống");
            return null;
        } else if (matKhau.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Mat Khau Trống");
            return null;
        }
        QLNhanVien qlNV = new QLNhanVien(idcv, ma, ten, diaChi, sDt, gioiTinh, ngaySinh, matKhau, trangThai, idcv);
        return qlNV;

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        txtMK = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        txtTen = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        txtdiaChi = new javax.swing.JTextField();
        txtGioiTinh = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNhanVien = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        checkTT = new javax.swing.JCheckBox();
        txtThang = new javax.swing.JTextField();
        txtNgay = new javax.swing.JTextField();
        txtNam = new javax.swing.JTextField();
        txtIDCV = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setText("Ngày Sinh");

        jLabel8.setText("Mật Khẩu");

        jLabel9.setText("Trạng Thái");

        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel10.setText("IDCV");

        btnSua.setText("Sửa ");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGioiTinhActionPerformed(evt);
            }
        });

        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel2.setText("Mã");

        tableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên", "Địa Chỉ", "SĐT", "Giới Tính", "Ngày Sinh", "Mật Khẩu", "Trạng Thái", "IDCV"
            }
        ));
        tableNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNhanVien);

        jLabel3.setText("Tên");

        jLabel4.setText("Địa chỉ");

        jLabel5.setText("SĐT");

        jLabel6.setText("Giới Tính");

        jLabel11.setText("ID");

        lbID.setText("-");

        checkTT.setText(" Hoạt Động");

        txtThang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtThang.setText("m.m");
        txtThang.setCaretColor(new java.awt.Color(51, 255, 255));
        txtThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtThangMouseClicked(evt);
            }
        });
        txtThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThangActionPerformed(evt);
            }
        });

        txtNgay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNgay.setText("dd");
        txtNgay.setCaretColor(new java.awt.Color(51, 255, 255));
        txtNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayMouseClicked(evt);
            }
        });
        txtNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayActionPerformed(evt);
            }
        });

        txtNam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNam.setText("yyyy");
        txtNam.setCaretColor(new java.awt.Color(51, 255, 255));
        txtNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNamMouseClicked(evt);
            }
        });
        txtNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel11))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(txtTen)
                                    .addComponent(txtdiaChi)
                                    .addComponent(txtSdt)
                                    .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(118, 118, 118))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(102, 102, 102)
                                .addComponent(btnSua)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMK)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtThang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNam))
                                    .addComponent(btnXoa)
                                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkTT, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 4, Short.MAX_VALUE))
                            .addComponent(txtIDCV)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(lbID))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtdiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtIDCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(checkTT))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnXoa))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtThang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNam, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        QLNhanVien qlNV = getData();
        if (qlNV == null) {
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Insert", "Xac Nhan ", JOptionPane.OK_CANCEL_OPTION) == 0) {
            this.nhanVienServicer.insert(qlNV);
            loadTable();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        QLNhanVien qlNV = getData();
        String ma = txtMa.getText().trim();

        if (qlNV == null) {
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Sửa", "Xác Nhận ", JOptionPane.OK_CANCEL_OPTION) == 0) {
            this.nhanVienServicer.update(qlNV);
            loadTable();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        QLNhanVien qlNV = new QLNhanVien(lbID.getText(), "", "", "", "", "", "", "", 0, "");

        if (qlNV.getIdNhanVien().isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Click vào Nhân Viên Để Xóa Nhân Viên");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Xác Nhận ", "Xóa", JOptionPane.OK_CANCEL_OPTION) == 0) {
            nhanVienServicer.delete(qlNV);
            loadTable();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tableNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhanVienMouseClicked
        int row = tableNhanVien.getSelectedRow();
        if (row == -1) {
            return;
        }
        String id = tableNhanVien.getValueAt(row, 0).toString();
        String maNV = tableNhanVien.getValueAt(row, 1).toString();
        String ten = tableNhanVien.getValueAt(row, 2).toString();
        String diaChi = tableNhanVien.getValueAt(row, 3).toString();
        String sdt = tableNhanVien.getValueAt(row, 4).toString();
        String gt = tableNhanVien.getValueAt(row, 5).toString();
        String ngaySinh = tableNhanVien.getValueAt(row, 6).toString();
        String mk = tableNhanVien.getValueAt(row, 7).toString();
        String trangThai = tableNhanVien.getValueAt(row, 8).toString();
        String idCV = tableNhanVien.getValueAt(row, 9).toString();

        lbID.setText(id);
        txtMa.setText(maNV);
        txtTen.setText(ten);
        txtdiaChi.setText(diaChi);
        txtSdt.setText(sdt);
        txtGioiTinh.setText(gt);
        txtMK.setText(mk);
        txtIDCV.setText(idCV);
        if (trangThai.equals("0")) {
            checkTT.setSelected(true);
        }
        if (trangThai.equals("-1")) {
            checkTT.setSelected(false);
        }

        String[] tachNgaySinh = ngaySinh.split("-");
        String ngay = tachNgaySinh[2];
        String thang = tachNgaySinh[1];
        String nam = tachNgaySinh[0];

        txtNgay.setText(ngay);
        txtThang.setText(thang);
        txtNam.setText(nam);


    }//GEN-LAST:event_tableNhanVienMouseClicked

    private void txtGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioiTinhActionPerformed

    private void txtThangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtThangMouseClicked
        if (txtThang.getText().trim().equals("m.m")) {
            txtThang.setText("");
        }
    }//GEN-LAST:event_txtThangMouseClicked

    private void txtThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThangActionPerformed

    private void txtNgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayMouseClicked
        if (txtNgay.getText().trim().equals("dd")) {
            txtNgay.setText("");
        }
    }//GEN-LAST:event_txtNgayMouseClicked

    private void txtNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayActionPerformed

    private void txtNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNamMouseClicked
        if (txtNam.getText().trim().equals("yyyy")) {
            txtNam.setText("");
        }
    }//GEN-LAST:event_txtNamMouseClicked

    private void txtNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamActionPerformed

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
            java.util.logging.Logger.getLogger(QLNhanVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNhanVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNhanVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNhanVienForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLNhanVienForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JCheckBox checkTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbID;
    private javax.swing.JTable tableNhanVien;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtIDCV;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNam;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtThang;
    private javax.swing.JTextField txtdiaChi;
    // End of variables declaration//GEN-END:variables

}
