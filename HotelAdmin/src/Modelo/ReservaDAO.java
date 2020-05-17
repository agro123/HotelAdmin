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
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */


public class ReservaDAO {
    
    
   
    
    public static void mensajeError(SQLException ex){

        JOptionPane.showMessageDialog(null,"Código "+
                    ex.getErrorCode() + "\n Error" + ex.getMessage());
    }
    
    
    public static int extraerUltimoId()
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int id = 0;

        
        try {
            con = Fachada.getConnection();
            String sql = "";

            sql
                    = "select  max(id_reserva) as id_reserva from reserva";
            
            pstm = con.prepareStatement(sql);
            
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                id = rs.getInt("id_reserva");
                
            }
        } catch (SQLException ex) {
            mensajeError(ex);;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                mensajeError(ex);
            }
        }
        return id;
    }
    
    
    
    public static int grabarReserva (Reserva reserva)
    {
        Connection con = null;
        PreparedStatement pstm;
        pstm= null;
        int rtdo;
        rtdo= 0;
        
        try{
            con = Fachada.getConnection();
            String sql = "INSERT INTO reserva values(?,?,?,?,?,?,?,?)";
            
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,reserva.getNumero_reserva());
            pstm.setInt(2,reserva.getNum_Habitacion());
            pstm.setInt(3, reserva.getNumCliente());
            pstm.setInt(4, reserva.getNum_Empleado());
            pstm.setTimestamp(5, reserva.getFecha_reserva());
            pstm.setTimestamp(6, reserva.getFecha_ingreso());
            pstm.setTimestamp(7, reserva.getFecha_salida());
            pstm.setInt(8, reserva.getNum_Personas());
            
            rtdo = pstm.executeUpdate();
        }
        catch(SQLException ex){
            mensajeError(ex);
        }
        finally{
            try{
                if (pstm != null) pstm.close();
            }
            catch(SQLException ex)
            {
                mensajeError(ex);
            }
        }
        return rtdo;
    }
    
    
        
    public static  ArrayList <Reserva> listadoReservas(){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList <Reserva> listadoReservas = new ArrayList<>();
        try{
            con = Fachada.getConnection(); 
            String sql="";
                       
            sql = "SELECT * FROM reserva ORDER BY id_reserva";   
            
            
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            Reserva objReserva = null;
            
            while(rs.next()){
                
                objReserva = new Reserva();
                objReserva.setNumero_reserva(rs.getInt("id_reserva"));
                objReserva.setNum_Habitacion(rs.getInt("id_habitacion"));
                objReserva.setNumCliente(rs.getInt("id_cliente"));
                objReserva.setNum_Empleado(rs.getInt("id_empleado"));
                objReserva.setFecha_reserva(rs.getTimestamp("fecha_reserva"));
                objReserva.setFecha_ingreso(rs.getTimestamp("fecha_ingreso"));
                objReserva.setFecha_salida(rs.getTimestamp("fecha_salida"));
                objReserva.setNum_Personas(rs.getInt("num_personas"));
                listadoReservas.add(objReserva);
            }
        }
        catch(SQLException ex){
            mensajeError(ex);
        }
        finally{
            try{
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                mensajeError(ex);
            }
        }
        return listadoReservas;
    }
    
    
    public static Reserva extraerReserva_porID(int idReserva)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Reserva objReserva = new Reserva();
        
        try {
            con = Fachada.getConnection();
            String sql = "";

            sql     = "SELECT * FROM reserva"      
                    + " WHERE id_reserva = ?";   
            
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idReserva);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                objReserva = new Reserva();
                objReserva.setNumero_reserva(rs.getInt("id_reserva"));
                objReserva.setNum_Habitacion(rs.getInt("id_habitacion"));
                objReserva.setNumCliente(rs.getInt("id_cliente"));
                objReserva.setNum_Empleado(rs.getInt("id_empleado"));
                objReserva.setFecha_reserva(rs.getTimestamp("fecha_reserva"));
                objReserva.setFecha_ingreso(rs.getTimestamp("fecha_ingreso"));
                objReserva.setFecha_salida(rs.getTimestamp("fecha_salida"));
                objReserva.setNum_Personas(rs.getInt("num_personas"));
                
            }
        } catch (SQLException ex) {
            mensajeError(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                mensajeError(ex);
            }
        }
        return objReserva;
        
    }
    
    
    public int modificarReserva(Reserva reserva){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "UPDATE reserva " +
                         "SET id_reserva = ?, id_habitacion = ?, id_cliente = ?,"
                    +    "id_empleado = ?, fecha_reserva ?,fecha_ingreso ?,"
                    +    "fecha_salida = ?,  "
                    +    "WHERE id_Servicio = ?, num_personas = ?";

            pstm = con.prepareStatement(sql);   

            pstm.setInt(1, reserva.getNumero_reserva());
            pstm.setInt(2,reserva.getNum_Habitacion());
            pstm.setInt(3, reserva.getNumCliente());
            pstm.setInt(4, reserva.getNum_Empleado());
            pstm.setTimestamp(5, reserva.getFecha_reserva());
            pstm.setTimestamp(6, reserva.getFecha_ingreso());
            pstm.setTimestamp(7, reserva.getFecha_salida());
            pstm.setInt(8, reserva.getNum_Personas());

            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            mensajeError(ex);
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                mensajeError(ex);
            }
        }
        return rtdo;
    }
    
    
    public int borrarReserva(int id_reserva){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "DELETE FROM reserva WHERE id_reserva = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id_reserva);
            rtdo = pstm.executeUpdate(); 
            return rtdo;
        }
        catch(SQLException ex){
            mensajeError(ex);
        } 
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                mensajeError(ex);
            }
        }
        return rtdo;
    }
    
    
    
    public ArrayList <Habitacion> listadoHabitacion(Timestamp fi, Timestamp fs){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList <Habitacion> listadoHabitacion = new ArrayList<>();
        try{
            con = Fachada.getConnection(); 
            String sql="";
                       
            sql = "SELECT * FROM HABITACION " +
                    "WHERE ID_HABITACION " +
                    "NOT IN(SELECT ID_HABITACION FROM RESERVA " +
                    "WHERE " +
                    "(FECHA_INGRESO BETWEEN ? AND ?) " +
                    "OR (FECHA_SALIDA BETWEEN ? AND ?))";            
               
            pstm = con.prepareStatement(sql);
            pstm.setTimestamp(1, fi);
            pstm.setTimestamp(2, fs);
            pstm.setTimestamp(3, fi);
            pstm.setTimestamp(4, fs);
            rs = pstm.executeQuery();
                        
            Habitacion objhabitacion = null;
            while(rs.next()){
                
                objhabitacion = new Habitacion();
                objhabitacion.setId_habitacion(rs.getInt("id_habitacion"));
                objhabitacion.setTipo_habitacion(
                rs.getString("tipo_habitacion"));
                objhabitacion.setPiso(rs.getString("piso"));
                objhabitacion.setCantidadPersonas(
                rs.getInt("cantidad_personas"));
                objhabitacion.setPrecio_hab(rs.getInt("precio_hab"));
                objhabitacion.setNum_camas(rs.getInt("num_camas"));
                objhabitacion.setEstado(rs.getBoolean("estado"));
                listadoHabitacion.add(objhabitacion);
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
                JOptionPane.showMessageDialog(null,"Habitación : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return listadoHabitacion;
    
    }
    
    
}
