/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tuane_nluzcuo
 */
public class DBConnection {

    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                try {
                    String user = "sa";
                    String pW = "123456";
                    String url = "jdbc:sqlserver://localhost:1433;databaseName = DuAn1_QuanLiBanQuanAo; encrypt = true; trustServerCertificate = true";

                    conn = DriverManager.getConnection(url, user, pW);
                    System.out.println("Ket noi Thanh Cong !!!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("Loi Driver???");
            }
        }

        return conn;
    }

    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
    }
}
