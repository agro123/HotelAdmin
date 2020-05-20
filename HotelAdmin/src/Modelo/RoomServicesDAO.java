/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Servicios.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class RoomServicesDAO {
    
    public int grabarServicio (RoomServices rs)
    {
        Connection con = null;
        PreparedStatement pstm;
        pstm= null;
        int rtdo;
        rtdo= 0;
        try{
            con = Fachada.getConnection();
            String sql = "INSERT INTO roomservices values(?,?,?,?)";
            
            pstm = con.prepareStatement(sql);
            
            pstm.setInt(1, rs.getId_servicio());
            pstm.setString(2,rs.getNombrePro());
            pstm.setInt(3, rs.getCantidad());
            pstm.setDouble(4, rs.getPrecio());
            
            rtdo = pstm.executeUpdate();
            
        }
        
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código "+
                    ex.getErrorCode() + "\n Error" + ex.getMessage());
        }
        
        finally{
            try{
                if (pstm != null) pstm.close();
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, " Código : "+ 
                        ex.getErrorCode() + " \n Error : " + ex.getMessage());
            }
        }
        return rtdo;
    }
    
    public ArrayList <RoomServices> listadoRoomServices(){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList <RoomServices> listadoServicios = new ArrayList<>();
        try{
            con = Fachada.getConnection(); 
            String sql="";
                       
            sql = "SELECT * FROM RoomServices ORDER BY id_servicio";            
               
            pstm = con.prepareStatement(sql);
   
            rs = pstm.executeQuery();
                        
            RoomServices objRoomServices = null;
            while(rs.next()){
                
                objRoomServices = new RoomServices();
                objRoomServices.setId_servicio(rs.getInt("id_servicio"));
                objRoomServices.setNombrePro(rs.getString("nombre_pro"));
                objRoomServices.setPrecio(rs.getDouble("precio"));
                objRoomServices.setCantidad(rs.getInt("cantidad"));
                listadoServicios.add(objRoomServices);
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
        return listadoServicios;
    
    }
    
     public RoomServices extraerServicio_porID(int idServicio)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        RoomServices servicio = new RoomServices();
        try {
            con = Fachada.getConnection();
            String sql = "";

            sql
                    = "SELECT * FROM roomservices"      
                    + " WHERE id_Servicio = ?";   
            
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idServicio);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                servicio.setId_servicio(rs.getInt("id_servicio"));
                servicio.setNombrePro(rs.getString("nombre_pro"));
                servicio.setCantidad(rs.getInt("cantidad"));
                servicio.setPrecio(rs.getDouble("precio")); 
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : "
                    + ex.getErrorCode() + "\nError :" + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Código : "
                        + ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return servicio;
        
    }
     
    public int extraerUltimoId()
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int id= 0;

        
        try {
            con = Fachada.getConnection();
            String sql = "";

            sql
                    = "select  max(id_servicio) as id_servicio from roomservices";
            
            pstm = con.prepareStatement(sql);
            //pstm.setString(1, idServicio);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                id = rs.getInt("id_servicio");
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : "
                    + ex.getErrorCode() + "\nError :" + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Código : "
                        + ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return id;
    }
    
    public int modificarRoomSercices(RoomServices roomServices){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "UPDATE RoomServices " +
                         "SET id_Servicio = ?, nombre_Pro = ?, cantidad = ?, precio = ?"
                    +    "WHERE id_Servicio = ?";

            pstm = con.prepareStatement(sql);   
            pstm.setInt(1, roomServices.getId_servicio());
            pstm.setString(2,roomServices.getNombrePro());
            pstm.setInt(3, roomServices.getCantidad());
            pstm.setDouble(4, roomServices.getPrecio());
            pstm.setInt(5, roomServices.getId_servicio());

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
    
    
    public int borrarServicio(int id_servicio){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "DELETE FROM roomServices WHERE id_servicio = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id_servicio);
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
           
    
    
    
    
}
