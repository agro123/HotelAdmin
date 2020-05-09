
package Modelo;

import Servicios.Fachada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author linkedV0id
 **/

public class ClientDAO {
    
    public int addClient(Cliente c){
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
       try{
            con = Fachada.getConnection();
            String sql = "INSERT INTO Cliente "
                    + "(id_cliente, nombre_cli, apellido_cli,email_cli, "
                    + "direccion_cli, telefono_cli ) values (?,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, c.getID());
            pstm.setString(2, c.getNombre());
            pstm.setString(3, c.getApellido());
            pstm.setString(4, c.getCorreo());
            pstm.setString(5, c.getDireccion());
            pstm.setString(6, c.getTelefono());
            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Code: " + 
                        ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Code: " + 
                        ex.getErrorCode() + "\nError: " + ex.getMessage());
            }
        }
        return rtdo;
    } 
    
    
    public int modifyClient(Cliente c){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "UPDATE Cliente "
                        + "SET id_cliente = ?, nombre_cli = ?,apellido_cli = ?,"
                        + "direccion_cli = ?,  email_cli = ?,  telefono_cli = ?,"
                        + "WHERE id_cliente = ?";
            pstm = con.prepareStatement(sql);  
            pstm.setInt(1, c.getID());
            pstm.setString(2, c.getNombre());
            pstm.setString(3, c.getApellido());
            pstm.setString(6, c.getCorreo());
            pstm.setString(5, c.getDireccion());
            pstm.setString(4, c.getTelefono());
            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Code: " + 
                        ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Code: " + 
                        ex.getErrorCode() + "\nError: " + ex.getMessage());
            }
        }
        return rtdo;
    }
    
    public ArrayList<Cliente> listCliente(int id_cliente){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Cliente> list = new ArrayList<>();
        try{
            con = Fachada.getConnection();
            String sql="";
            if(id_cliente ==0){
                sql = "SELECT * FROM Cliente ORDER BY id_cliente";            
            }else{
                sql = "SELECT * FROM Cliente where id_cliente = ? "
                    + "ORDER BY id_cliente";      
            }                     
            pstm = con.prepareStatement(sql);
            
            if(id_cliente != 0){
                pstm.setInt(1, id_cliente);
            }
            
            rs = pstm.executeQuery();
                        
           Cliente cliente = null;
            while(rs.next()){
                cliente = new Cliente();
                cliente.setID(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre_cli"));
                cliente.setApellido(rs.getString("apellido_cli"));
                cliente.setDireccion(rs.getString("direccion_cli"));
                cliente.setCorreo(rs.getString("email_cli"));
                cliente.setTelefono(rs.getString("telefono_cli"));
                list.add(cliente);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Code: " + 
                        ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
        finally{
            try{
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Code: " + 
                        ex.getErrorCode() + "\nError: " + ex.getMessage());
            }
        }
        return list;
    }
   
}