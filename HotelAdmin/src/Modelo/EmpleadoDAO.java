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
            String sql = "INSERT INTO actor values (?,?,?,?,?,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, c.getID());
            pstm.setInt(2, c.getCodEmpleado());
            pstm.setString(3, c.getNombre());
            pstm.setString(4, c.getApellido());
            pstm.setString(5, c.getCorreo());
            pstm.setString(6, c.getDireccion());
            pstm.setString(7, c.getTelefono());
            pstm.setString(8, c.getCargo());
            pstm.setBoolean(9, c.getEstado());
            pstm.setTimestamp(10, c.getIngreso());
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
    
    public int modificarEmpleado(Empleado c){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "UPDATE actor " +
                         "SET nombre = ?,apellido = ?, correo = ?, "
                    + "direccion = ?, telefono = ?, cargo = ?, estado = ?,"
                    + "ingreso = ? "
                    +    "WHERE actor_id=?";
            pstm = con.prepareStatement(sql);  
            pstm.setInt(1, c.getID());
            pstm.setInt(2, c.getCodEmpleado());
            pstm.setString(3, c.getNombre());
            pstm.setString(4, c.getApellido());
            pstm.setString(5, c.getCorreo());
            pstm.setString(6, c.getDireccion());
            pstm.setString(7, c.getTelefono());
            pstm.setString(8, c.getCargo());
            pstm.setBoolean(9, c.getEstado());
            pstm.setTimestamp(10, c.getIngreso());
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