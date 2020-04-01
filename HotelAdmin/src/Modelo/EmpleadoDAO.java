/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Servicios.Fachada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class EmpleadoDAO {
    
    //--------------------------------------------------------------------------
    
    public int grabarEmpleado(Empleado c){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
       try{
            con = Fachada.getConnection();
            String sql = "INSERT INTO actor values (?,?,?,?,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, c.getID());
            pstm.setInt(2, c.getCodEmpleado());
            pstm.setString(3, c.getNombre());
            pstm.setString(4, c.getCorreo());
            pstm.setString(5, c.getDireccion());
            pstm.setString(6, c.getTelefono());
            pstm.setString(7, c.getCargo());
            pstm.setBoolean(8, c.getEstado());
            pstm.setTimestamp(9, c.getIngreso());
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
    
    //--------------------------------------------------------------------------
    
    public int modificarActor(Empleado c){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "UPDATE actor " +
                         "SET first_name = ?,last_name = ?, last_update = ? "
                    +    "WHERE actor_id=?";
            pstm = con.prepareStatement(sql);            
            pstm.setString(1, c.Name());
            pstm.setString(2, c.getLastName());
            pstm.setTimestamp(3, c.getLasUpdate());
            pstm.setInt(4,c.getActorid());
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
    
}