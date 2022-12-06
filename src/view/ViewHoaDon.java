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
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADMIN
 */
public class ViewHoaDon extends javax.swing.JFrame {

    /**
     * Creates new form ViewHoaDon
     */
    DefaultTableModel dtmHD = new DefaultTableModel();
    DefaultTableModel dtmHDCT = new DefaultTableModel();
    IHoaDonChiTietService iHDCT = new HoaDonChiTietService();
    IHoaDonService iHD = new HoaDonService();
    List<QLHoaDonChiTiet> listqlhdct = new ArrayList<>();
    List<QLHoaDon> listqlhd = new ArrayList<>();

    public ViewHoaDon() {
        initComponents();

    }

//    private void showDataHDCT(List<QLHoaDonChiTiet> list) {
//        dtmHDCT = (DefaultTableModel) tblHoaDonChiTiet.getModel();
//        dtmHDCT.setRowCount(0);
//        list.forEach(s -> dtmHDCT.addRow(s.DataRow()));
//    }

    private void showDataHD(List<QLHoaDon> list) {
        dtmHD = (DefaultTableModel) tblHoaDon.getModel();
        dtmHD.setRowCount(0);
        list.forEach(s -> dtmHD.addRow(s.dataRow()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        rdoTongTien = new javax.swing.JRadioButton();
        rdoSoLuong = new javax.swing.JRadioButton();
        rdoNgayTao = new javax.swing.JRadioButton();
        btnFilter = new javax.swing.JButton();
        btnAll = new javax.swing.JButton();
        cboTangGiam = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(51, 51, 255));

        tblHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Người Tạo", "Ngày Tạo", "Tổng Sản Phẩm", "Tổng Tiền", "Trạng Thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Quản Lí Hoá Đơn");

        btnExit.setBackground(new java.awt.Color(255, 51, 51));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rdoTongTien.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoTongTien);
        rdoTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoTongTien.setForeground(new java.awt.Color(51, 51, 255));
        rdoTongTien.setText("Tổng Tiền");

        rdoSoLuong.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoSoLuong);
        rdoSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoSoLuong.setForeground(new java.awt.Color(51, 51, 255));
        rdoSoLuong.setText("Số Lượng");

        rdoNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNgayTao);
        rdoNgayTao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoNgayTao.setForeground(new java.awt.Color(51, 51, 255));
        rdoNgayTao.setSelected(true);
        rdoNgayTao.setText("Ngày Tạo");

        btnFilter.setBackground(new java.awt.Color(51, 51, 255));
        btnFilter.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnFilter.setForeground(new java.awt.Color(255, 255, 255));
        btnFilter.setText("FILTER");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        btnAll.setBackground(new java.awt.Color(51, 51, 255));
        btnAll.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAll.setForeground(new java.awt.Color(255, 255, 255));
        btnAll.setText("ALL");
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });

        cboTangGiam.setBackground(new java.awt.Color(255, 255, 255));
        cboTangGiam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cboTangGiam.setForeground(new java.awt.Color(51, 51, 255));
        cboTangGiam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tâng dần", "Giảm dần" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(rdoNgayTao)
                .addGap(29, 29, 29)
                .addComponent(rdoSoLuong)
                .addGap(39, 39, 39)
                .addComponent(rdoTongTien)
                .addGap(39, 39, 39)
                .addComponent(cboTangGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnAll)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoTongTien)
                    .addComponent(rdoSoLuong)
                    .addComponent(rdoNgayTao)
                    .addComponent(btnFilter)
                    .addComponent(btnAll)
                    .addComponent(cboTangGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Lọc Theo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 391, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        // TODO add your handling code here:
        listqlhd = iHD.getAllHD();
        showDataHD(listqlhd);
        listqlhdct = iHDCT.getAllHDCT();
//        showDataHDCT(listqlhdct);
    }//GEN-LAST:event_btnAllActionPerformed

    private void jdate1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdate1KeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jdate1KeyReleased

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn trở lại màn hình chính !!!!", "Trờ lại màn hình chính", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (check == JOptionPane.YES_OPTION) {
            this.dispose();}
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(ViewHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFilter;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTangGiam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNgayTao;
    private javax.swing.JRadioButton rdoSoLuong;
    private javax.swing.JRadioButton rdoTongTien;
    private javax.swing.JTable tblHoaDon;
    // End of variables declaration//GEN-END:variables
}