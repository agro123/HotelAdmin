/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Servicios.Fachada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author crist
 */
public class LoginDAO {
        public int grabarLogin(LoginModelo c){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
       try{
            con = Fachada.getConnection();
            String sql = "INSERT INTO Login values (?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, c.getUsuario());
            pstm.setString(2, c.getContrasena());
            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    } 
     public int modificarLogin(LoginModelo c){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "UPDATE Login " +
                         "SET ID_empleado = ?,contraseña = ?"
                    +    "WHERE ID_empleado=?";
            pstm = con.prepareStatement(sql);            
            pstm.setString(1, c.getUsuario());
            pstm.setString(2, c.getContrasena());
            
            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
     
     public int borrarLogin(String usuario){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "DELETE FROM Login WHERE ID_empleado = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, usuario);
            rtdo = pstm.executeUpdate(); 
            return rtdo;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        } 
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    } 
      public ArrayList<LoginModelo> listadoLogin(int usuario){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<LoginModelo> listado = new ArrayList<>();
        try{
            con = Fachada.getConnection();
            String sql="";
            if(usuario==0){
                sql = "SELECT * FROM Login ORDER BY id_empleado";            
            }else{
                sql = "SELECT * FROM Login where id_empleado = ? "
                    + "ORDER BY id_empleado";      
            }                     
            pstm = con.prepareStatement(sql);
            
            if(usuario != 0){
                pstm.setInt(1, usuario);
            }
            
            rs = pstm.executeQuery();
                        
           LoginModelo login = null;
            while(rs.next()){
                login = new LoginModelo();
                login.setUsuario(rs.getString("id_empleado"));
               login.setContrasena(rs.getString("contrasena"));
               
                listado.add(login);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return listado;
    } 
}
