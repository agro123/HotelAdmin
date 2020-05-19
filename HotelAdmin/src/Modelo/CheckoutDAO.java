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
import javax.swing.JOptionPane;

/**
 *
 * @author crist
 */
public class CheckoutDAO {
    private void mensajeError(SQLException ex){

        JOptionPane.showMessageDialog(null,"Código "+
                    ex.getErrorCode() + "\n Error" + ex.getMessage());
    }
    public int grabarCheckout(Checkout c){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
       try{
            con = Fachada.getConnection();
            String sql = "INSERT INTO checkout (id_checkout,id_hospedaje,"
                    + "valortotal,medio_pago,fecha)"
                    + "VALUES (?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, c.getIdCheckout());
            pstm.setInt(2, c.getIdHospedaje());
            pstm.setInt(3, c.getValorTotal());
            pstm.setString(4, c.getMediodepago());
            pstm.setTimestamp(5, c.getFpago());
            
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
    public int extraerUltimoId()
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int id = 0;       
        try {
            con = Fachada.getConnection();
            String sql = "";

            sql = "select  max(id_checkout) as id_checkout from checkout";
            
            pstm = con.prepareStatement(sql);
            
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                id = rs.getInt("id_checkout"); 
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
    public Checkout extraerCheckout (int idcliente)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Checkout c = new Checkout();
        try {
            con = Fachada.getConnection();
            String sql = "";

            sql
                    = "SELECT * FROM cuenta" //cuenta es una vista      
                    + " WHERE estado = true "
                    + "AND id_cliente = ?";   
            
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idcliente);
            rs = pstm.executeQuery();
            
            while (rs.next()) {                
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setIdhabitacion(rs.getInt("habitacion"));
                c.setValorTotal(rs.getInt("valortotal"));
                c.setPreciohabitacion(rs.getInt("precio_hab"));
                c.setTipo(rs.getString("tipo_habitacion"));
                c.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
                c.setFechaSalida(rs.getTimestamp("fecha_salida"));
                c.setIdCheckout(rs.getInt("id_checkout"));                   
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
        return c;    
    }

}
