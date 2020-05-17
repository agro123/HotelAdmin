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
    private void mensajeError(SQLException ex){

        JOptionPane.showMessageDialog(null,"Código "+
                    ex.getErrorCode() + "\n Error" + ex.getMessage());
    }
    public int grabarHospedaje(Hospedaje c){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
       try{
            con = Fachada.getConnection();
            String sql = "INSERT INTO Hospedaje values (?,?,?,?,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, c.getIdHospedaje());
            pstm.setInt(2, c.getIdHabitacion());
            pstm.setInt(3, c.getIdCliente());
            pstm.setInt(4, c.getIdEmpleado());
            pstm.setTimestamp(5, c.getFechaIngreso());
            pstm.setTimestamp(6, c.getFechaSalida());
            pstm.setInt(7, c.getNumeroPesonas());
            pstm.setBoolean(8, c.getEstado());
            pstm.setInt(9, c.getId_reserva());
            
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
            pstm.setInt(8, c.getIdHospedaje());
            pstm.setInt(1, c.getIdHabitacion());
            pstm.setInt(2, c.getIdCliente());
            pstm.setInt(3, c.getIdEmpleado());
            pstm.setTimestamp(4, c.getFechaIngreso());
            pstm.setTimestamp(5, c.getFechaSalida());
            pstm.setInt(6, c.getNumeroPesonas());
            pstm.setBoolean(7, c.getEstado());
            
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
    public int extraerUltimoId()
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int id = 0;       
        try {
            con = Fachada.getConnection();
            String sql = "";

            sql = "select  max(id_hospedaje) as id_hospedaje from hospedaje";
            
            pstm = con.prepareStatement(sql);
            
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                id = rs.getInt("id_hospedaje");
                
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
        return id;
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
                    + "ORDER BY habitacion";      
            }                     
            pstm = con.prepareStatement(sql);            
            
            rs = pstm.executeQuery();
                        
           Hospedaje h = null;
            while(rs.next()){
                if(s==0){                    
                    h = new Hospedaje(
                        rs.getInt("id_hospedaje"),
                        rs.getInt("id_habitacion"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_empleado"),
                        rs.getTimestamp("fecha_ingreso"),
                        rs.getTimestamp("fecha_salida"),
                        rs.getInt("num_personas"),
                        rs.getBoolean("estado"),  
                        rs.getInt("id_reserva")  
                    );           
                } else {
                    h = new Hospedaje(
                        rs.getInt("id_hospedaje"),
                        rs.getInt("habitacion"),
                        rs.getInt("cedula_cliente"),
                        rs.getInt("piso")
                    );
                }
                listado.add(h);
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
        return listado;
    } 
}
