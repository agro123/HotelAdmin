/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;
import java.sql.*;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
/**
 *
 * @author Leonardo
 */
public class Fachada {
private static Connection con = null;
    
    public static Connection getConnection(){
       try
        {
            if(con == null){
         
                Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
           
                ResourceBundle rb = ResourceBundle.getBundle("Servicios.jdbc");
                String driver = rb.getString("driver");
                String url = rb.getString("url");
                String pwd = rb.getString("pwd");
                String usr = rb.getString("usr");
                
                Class.forName(driver);
                con = DriverManager.getConnection(url, usr, pwd);
            }
                         
        }
        catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error : " + 
                    ex.getMessage());
        }
        return con;
    }
    
    static class MiShDwnHook extends Thread{
    
        @Override
        public void run(){
            try{
                Connection con = Fachada.getConnection();
                con.close();                     
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null,"Error : " + 
                        ex.getMessage());
            }
        }
    }
}
