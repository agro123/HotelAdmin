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
public class ServiciosAdicionadoDAO {
    private void mensajeError(SQLException ex){

        JOptionPane.showMessageDialog(null,"Código "+
                    ex.getErrorCode() + "\n Error" + ex.getMessage());
    }
    public ArrayList<ServiciosAdicionado> listadoSH(int idcheckout){ 
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<ServiciosAdicionado> listado = new ArrayList<>();
        try{
            con = Fachada.getConnection();
            String sql="";
            
                sql = "SELECT * FROM servicioscheckout "
                        + "WHERE id_checkout = ?";
                                    
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idcheckout);
            
            rs = pstm.executeQuery();
                        
           ServiciosAdicionado h = null;
            while(rs.next()){                   
                    h = new ServiciosAdicionado(
                        rs.getInt("id_checkout"),
                        rs.getInt("id_servicio"),
                        rs.getInt("cantidad"),
                        rs.getInt("precio"),
                        rs.getInt("total"),
                        rs.getString("nombre_pro")          
                    );           
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
