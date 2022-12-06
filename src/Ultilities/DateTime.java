/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ultilities;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DateTime implements Runnable {

    JLabel clockLabel;

    public DateTime(JLabel clockLabel) {
        this.clockLabel = clockLabel;
    }

    @Override
    public void run() {
        while (true) {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            clockLabel.setText(sdf.format(d));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

}
