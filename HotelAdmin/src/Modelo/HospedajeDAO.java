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
public class HospedajeDAO {
            public int grabarHospedaje(Hospedaje c){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
       try{
            con = Fachada.getConnection();
            String sql = "INSERT INTO Hospedaje values (?,?,?,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, c.getIdHospedaje());
            pstm.setString(2, c.getIdHabitacion());
            pstm.setString(3, c.getIdCliente());
            pstm.setString(4, c.getIdEmpleado());
            pstm.setTimestamp(5, c.getFechaIngreso());
            pstm.setTimestamp(6, c.getFechaSalida());
            pstm.setInt(7, c.getNumeroPesonas());
            pstm.setBoolean(8, c.getEstado());
            
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
     public int modificarHospedaje(Hospedaje c){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "UPDATE hospedaje " +
                        "SET id_habitacion = ?,id_cliente = ?, id_empleado = ?,"
                    + " fecha_ingreso = ?, fecha_salida = ?, num_personas = ?,"
                    + " estado = ?"
                    +    "WHERE id_hospedaje=?";
            pstm = con.prepareStatement(sql);            
            pstm.setString(8, c.getIdHospedaje());
            pstm.setString(1, c.getIdHabitacion());
            pstm.setString(2, c.getIdCliente());
            pstm.setString(3, c.getIdEmpleado());
            pstm.setTimestamp(4, c.getFechaIngreso());
            pstm.setTimestamp(5, c.getFechaSalida());
            pstm.setInt(6, c.getNumeroPesonas());
            pstm.setBoolean(7, c.getEstado());
            
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
     
     public int borrarHospedaje(String id_hospedaje){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "DELETE FROM hospedaje  "
                    + "WHERE id_hospedaje = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id_hospedaje);
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
      public ArrayList<Hospedaje> listadoHospedaje(int s){ 
          //si s=1 entonces muestra una vista con datos de la habitacion
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Hospedaje> listado = new ArrayList<>();
        try{
            con = Fachada.getConnection();
            String sql="";
            if(s==0){
                sql = "SELECT * FROM hospedaje ORDER BY estado";            
            }else{
                sql = "SELECT * FROM datos_hospedaje "
                    + "ORDER BY id_habitacion";      
            }                     
            pstm = con.prepareStatement(sql);            
            
            rs = pstm.executeQuery();
                        
           Hospedaje h = null;
            while(rs.next()){
                if(s==0){
                    h = new Hospedaje(
                        rs.getString("id_hospedaje"),
                        rs.getString("id_habitacion"),
                        rs.getString("id_cliente"),
                        rs.getString("id_empleado"),
                        rs.getTimestamp("fecha_ingreso"),
                        rs.getTimestamp("fecha_salida"),
                        rs.getInt("num_personas"),
                        rs.getBoolean("estado")                       
                    );           
                } else {
                    h = new Hospedaje(
                        rs.getString("id_hospedaje"),
                        rs.getString("id_habitacion"),
                        rs.getString("id_cliente"),
                        rs.getString("piso")
                    );
                }
                listado.add(h);
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
