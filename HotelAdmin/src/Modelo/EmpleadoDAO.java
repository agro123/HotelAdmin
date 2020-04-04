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
            pstm.setString(2, c.getNombre());
            pstm.setString(3, c.getApellido());
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
    
    public int modificarEmpleado(Empleado c){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "UPDATE actor " +
                         "SET id_empleado = ?, nombre_emp = ?,apellido_emp = ?, "
                    + "direccion_emp = ?,  email_emp = ?,  telefono_emp = ?, "
                    + "cargo_emp = ?, fecha_ingreso = ?, estado = ?"
                    +    "WHERE id_empleado = ?";
            pstm = con.prepareStatement(sql);  
            pstm.setInt(1, c.getID());
            pstm.setString(2, c.getNombre());
            pstm.setString(3, c.getApellido());
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
    public ArrayList<Empleado> listadoEmpleado(int id_empleado){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Empleado> listado = new ArrayList<>();
        try{
            con = Fachada.getConnection();
            String sql="";
            if(id_empleado ==0){
                sql = "SELECT * FROM empleado ORDER BY id_empleado";            
            }else{
                sql = "SELECT * FROM empleado where id_empleado = ? "
                    + "ORDER BY id_empleado";      
            }                     
            pstm = con.prepareStatement(sql);
            
            if(id_empleado != 0){
                pstm.setInt(1, id_empleado);
            }
            
            rs = pstm.executeQuery();
                        
           Empleado empleado = null;
            while(rs.next()){
                empleado.setID(rs.getInt("id_empleado"));
                empleado.setNombre(rs.getString("nombre_emp"));
                empleado.setApellido(rs.getString("apellido_emp"));
                empleado.setDireccion(rs.getString("direccion_emp"));
                empleado.setCorreo(rs.getString("email_emp"));
                empleado.setTelefono(rs.getString("telefono_emp"));
                empleado.setCargo(rs.getString("cargo_emp"));
                empleado.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
                empleado.setEstado(rs.getBoolean("estado_emp"));
                listado.add(empleado);
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