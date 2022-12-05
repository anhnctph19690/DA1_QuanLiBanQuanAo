/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Services.IHoaDonChiTietService;
import Services.IHoaDonService;
import Services.Impl.HoaDonChiTietService;
import Services.Impl.HoaDonService;
import ViewModel.QLHoaDon;
import ViewModel.QLHoaDonChiTiet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADMIN
 */
public class HoaDonView extends javax.swing.JFrame {

    /**
     * Creates new form HoaDonView
     */
    DefaultTableModel dtmHD = new DefaultTableModel();
    DefaultTableModel dtmHDCT = new DefaultTableModel();
    IHoaDonChiTietService iHDCT = new HoaDonChiTietService();
    IHoaDonService iHD = new HoaDonService();
    List<QLHoaDonChiTiet> listqlhdct = new ArrayList<>();
    List<QLHoaDon> listqlhd = new ArrayList<>();

    public HoaDonView() {
        initComponents();
//        showCbb();
        
//        listqlhd = iHD.getAllHD();
         
        listqlhd =  iHD.getAllHD();
        showDataHD(listqlhd);
        listqlhdct = iHDCT.getAllHDCT();
        showDataHDCT(listqlhdct);

    }   

    private void showDataHDCT(List<QLHoaDonChiTiet> list) {
        dtmHDCT = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        dtmHDCT.setRowCount(0);
        list.forEach(s -> dtmHDCT.addRow(s.DataRow()));
    }

    private void showDataHD(List<QLHoaDon> list) {
        dtmHD = (DefaultTableModel) tbHoaDon.getModel();
        dtmHD.setRowCount(0);
        list.forEach(s -> dtmHD.addRow(s.dataRow()));
    }
//    private void showCbb(){
//        DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbbTrangThai.getModel();      
//        dcm.removeAllElements();
//        listqlhd = iHD.getAllHD();
//        for (QLHoaDon qlhd : listqlhd) {
//            dcm.addElement(qlhd.getTrangThai1());
//        
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jdate1 = new com.toedter.calendar.JDateChooser();
        btnfind = new javax.swing.JButton();
        jdate2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Người Tạo", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        jScrollPane2.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jdate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jdate1MouseClicked(evt);
            }
        });
        jdate1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdate1PropertyChange(evt);
            }
        });
        jdate1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jdate1KeyReleased(evt);
            }
        });

        btnfind.setText("Lọc theo ngày");
        btnfind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfindActionPerformed(evt);
            }
        });

        jdate2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdate2PropertyChange(evt);
            }
        });
        jdate2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jdate2KeyReleased(evt);
            }
        });

        jLabel1.setText("Ngày");

        jLabel2.setText("Đến");

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán", "Đang tạo", "Đang giao", "Đã giao" }));
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });

        jLabel3.setText("Lọc theo trạng thái");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnfind)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnfind))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jdate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addComponent(jdate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDon.getSelectedRow();
        QLHoaDon hoaDon = listqlhd.get(row);
        dtmHD = (DefaultTableModel) tbHoaDon.getModel();
        listqlhdct = iHDCT.getListInvoices(hoaDon.getIdHoaDon());
        showDataHDCT(listqlhdct);
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void jdate1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdate1KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jdate1KeyReleased

    private void btnfindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfindActionPerformed
        // TODO add your handling code here:
         Date ngayBatDau = jdate1.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngayBatDauStr = dateFormat.format(ngayBatDau);
        Date ngayketThuc = jdate2.getDate();
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String ngayKetThucStr = dateFormat1.format(ngayketThuc);
        listqlhd = iHD.getFilter(ngayBatDauStr, ngayKetThucStr);
        listqlhdct = iHDCT.getFilters(ngayBatDauStr, ngayKetThucStr);
        showDataHD(listqlhd);
        showDataHDCT(listqlhdct);
    }//GEN-LAST:event_btnfindActionPerformed

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // TODO add your handling code here:
        int trangThai = cbbTrangThai.getSelectedIndex();
        listqlhd = iHD.getAllHoaDonCho(trangThai);
        if (!listqlhd.isEmpty()) {
            showDataHD(listqlhd);
//            showDataHDCT(listqlhdct);
        } else {
            showDataHD(listqlhd);
//            showDataHDCT(listqlhdct);
        }

    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    private void jdate2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdate2KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jdate2KeyReleased

    private void jdate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdate1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jdate1MouseClicked

    private void jdate1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdate1PropertyChange
        // TODO add your handling code here:
//        Date ngayBatDau = jdate1.getDate();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String ngayBatDauStr = dateFormat.format(ngayBatDau);
//        Date ngayketThuc = jdate2.getDate();
//        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//        String ngayKetThucStr = dateFormat1.format(ngayketThuc);
//        listqlhd = iHD.getFilter(ngayBatDauStr, ngayKetThucStr);
//        listqlhdct = iHDCT.getFilters(ngayBatDauStr, ngayKetThucStr);
//        showDataHD(listqlhd);
//        showDataHDCT(listqlhdct);
    }//GEN-LAST:event_jdate1PropertyChange

    private void jdate2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdate2PropertyChange
        // TODO add your handling code here:
//        Date ngayBatDau = jdate1.getDate();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String ngayBatDauStr = dateFormat.format(ngayBatDau);
//        Date ngayketThuc = jdate2.getDate();
//        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//        String ngayKetThucStr = dateFormat1.format(ngayketThuc);
//        listqlhd = iHD.getFilter(ngayBatDauStr, ngayKetThucStr);
//        listqlhdct = iHDCT.getFilters(ngayBatDauStr, ngayKetThucStr);
//        showDataHD(listqlhd);
//        showDataHDCT(listqlhdct);
    }//GEN-LAST:event_jdate2PropertyChange

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        listqlhd = new ArrayList<>();
        String name = txtTimKiem.getText();
        listqlhd = iHD.getByName(name);
        showDataHD(listqlhd);
    }//GEN-LAST:event_txtTimKiemKeyReleased

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
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnfind;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdate1;
    private com.toedter.calendar.JDateChooser jdate2;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
