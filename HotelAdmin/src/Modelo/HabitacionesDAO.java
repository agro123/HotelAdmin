/*
 * Programa	: HabitacionDAO
 * Fecha	: 02/04/2020
 * Objetivo	: Modela el acceso a datos de la tabla Habitación
 * Autor	: Leidy Vanesa Cifuentes
 */

package Modelo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Servicios.*;


public class HabitacionesDAO {
    
    public HabitacionesDAO(){
        
    }
    
    /**
     * 
     * @param h Objeto de la clase Habitacion a grabar
     * @return rtdo resultado de la operación grabar
     */
    public int grabarHabitacion(Habitaciones1 h){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "INSERT INTO habitacion values (?,?,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, h.getId_habitacion());
            pstm.setString(2, h.getTipo_habitacion());
            pstm.setString(3, h.getPiso());
            pstm.setInt(4, h.getCantidadPersonas());    
            pstm.setInt(5, h.getPrecio_hab());
            pstm.setInt(6, h.getNum_camas());
            pstm.setBoolean(7, h.getEstado());
            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Habitación : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Habitación : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
    
    /**
     * 
     * @param h Objeto de la clase Habitacion a modificar
     * @return rtdo resultado de la operación modificar
     */
    public int modificarHabitacion(Habitaciones1 h){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection(); 
            String sql = "UPDATE habitacion " +
                         "SET id_habitacion = ?, piso = ?, cantidad_personas = ?, precio_hab = ? "
                    +    "WHERE id_habitacion = ?";
            pstm = con.prepareStatement(sql);      
            
            pstm.setInt(1, h.getId_habitacion());
           // pstm.setString(2, h.getTipo_habitacion());
            pstm.setString(2, h.getPiso());
            pstm.setInt(3, h.getCantidadPersonas());    
            pstm.setInt(4, h.getPrecio_hab());
           // pstm.setInt(6, h.getNum_camas());
           // pstm.setBoolean(7, h.getEstado());*/
            pstm.setInt(5, h.getId_habitacion());
            
   
            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Habitación : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Habitación : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
    
    /**
     * 
     * @param id_habitacion código de la habitación a borrar
     * @return rtdo resultado de la operación borrar
     */
    public int borrarHabitacion(int id_habitacion){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = Fachada.getConnection();
            String sql = "DELETE FROM habitacion WHERE id_habitacion = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id_habitacion);
            rtdo = pstm.executeUpdate(); 
            return rtdo;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Habitación : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        } 
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Habitación : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
    
    /**
     * 
     * @param id_habitacion id de habitación a listar
     * @return ArrayList, lista de objetos Habitación
     */
    public ArrayList <Habitaciones1> listadoHabitacion(){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList <Habitaciones1> listadoHabitacion = new ArrayList<>();
        try{
            con = Fachada.getConnection(); 
            String sql="";
                       
            sql = "SELECT * FROM habitacion ORDER BY id_habitacion";            
               
            pstm = con.prepareStatement(sql);
   
            rs = pstm.executeQuery();
                        
            Habitaciones1 objhabitacion = null;
            while(rs.next()){
                
                objhabitacion = new Habitaciones1();
                objhabitacion.setId_habitacion(rs.getInt("id_habitacion"));
                objhabitacion.setTipo_habitacion(rs.getString("tipo_habitacion"));
                objhabitacion.setPiso(rs.getString("piso"));
                objhabitacion.setCantidadPersonas(rs.getInt("cantidad_personas"));
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
    
public Habitaciones1 extraerHabitaciones_porID(int idHabitacion)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Habitaciones1 habitacion = new Habitaciones1();
        try {
            con = Fachada.getConnection();
            String sql = "";

            sql
                    = "SELECT * FROM habitacion"      
                    + " WHERE id_habitacion = ?";   
            
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idHabitacion);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                
                habitacion.setId_habitacion(rs.getInt("id_habitacion"));
                habitacion.setTipo_habitacion(rs.getString("tipo_habitacion"));
                habitacion.setPiso(rs.getString("piso"));
                habitacion.setCantidadPersonas(rs.getInt("cantidad_personas"));
                habitacion.setPrecio_hab(rs.getInt("precio_hab"));
                habitacion.setNum_camas(rs.getInt("num_camas"));
                habitacion.setEstado(rs.getBoolean("estado"));
                               
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
        return habitacion;
        
    }


    
}