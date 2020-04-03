/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoteladmin;

import Vistas.Jframe.Login_;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Leonardo
 */
public class HotelAdmin{


    public static void main(String[] args) {
    
        Login_ inicio = new Login_();
        inicio.setVisible(true);
        inicio.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    }
    
}
