/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;



import static Controladores.ControllerServicios.opcionEjec;
import Modelo.Reserva;
import Modelo.ReservaDAO;
import Modelo.Habitacion;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControllerReserva {

    static int numeroReserva;
    static int numEmpleado;
    
    
    public ControllerReserva() { 
        extraerId();
    }
    
    public static void setNumEmpleado(int numEmpl) {
        numEmpleado = numEmpl;
    }


    public static void setNumeroReserva(int numeroReserva) {
        ControllerReserva.numeroReserva = numeroReserva;
    }

    

    public static int getNumEmpleado() {
        return numEmpleado;
    }
    
    
    public Reserva getReserva(int idcliente){
        ReservaDAO modelo = new ReservaDAO();    
        return modelo.getReserva(idcliente);
    }
    
    
    public static ArrayList<Habitacion> loadListRooms
                (Timestamp fi,Timestamp fs,String modo,int numReserva){
        ReservaDAO modeloHab = new ReservaDAO();
        return  modeloHab.listadoHabitacion(fi, fs, modo, numReserva); 
    }
    
    
    public static int extraerId(){
        ReservaDAO modelo = new ReservaDAO();
        return modelo.extraerUltimoId();
    }
    
    public static ArrayList<Reserva> listarReservas(){
        ReservaDAO modelo = new ReservaDAO(); 
        return modelo.listadoReservas();
    }
    
    public static int registrarReserva(Reserva reserva) {
        int resultado = 0;
        ReservaDAO modelo = new ReservaDAO();
        resultado = modelo.grabarReserva(reserva);
        if (resultado == 1) {
            gestionMensajes("Registro Grabado con éxito",
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            return 1;
        } else {
            return 0;
        }
    }
   
    
        
    
    public static ArrayList<Reserva> listarReservasLike(int numReserva){
        ReservaDAO modelo = new ReservaDAO();
        return modelo.listReservasLike(numReserva);
    }
    
    
    
    public static int eliminarReserva(int ID) {
        ReservaDAO modelo = new ReservaDAO();
        if (modelo.borrarReserva(ID) == 1) {
            gestionMensajes(
                    "Registro Borrado con éxtio",
                    "Confirmación de acción",
                    JOptionPane.INFORMATION_MESSAGE);
            return 1;
        } else {
            gestionMensajes(
                    "Error al borrar",
                    "Confirmación de acción",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    
    public static int actualizarReserva(Reserva reserva){
         ReservaDAO modelo = new ReservaDAO();
        if (modelo.modificarReserva(reserva) == 1) {
            gestionMensajes(
                    "Actualización exitosa",
                    "Confirmación ",
                    JOptionPane.INFORMATION_MESSAGE);
            return 1;
        } else {
            gestionMensajes(
                    "Actualización Falida",
                    "Confirmación ",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        
    }
                        
        
    public static void gestionMensajes(String mensaje, String titulo, int icono) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, icono);
    }

    
    
    
}
