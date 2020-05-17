/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.ControllerServicios.gestionMensajes;
import Modelo.HabitacionDAO;
import Modelo.Reserva;
import Modelo.ReservaDAO;

import Modelo.ClientDAO;
import Modelo.Cliente;
import Modelo.Habitacion;
import Modelo.RoomServicesDAO;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControllerReserva {

    static int numeroReserva;
    static boolean verCliente;
    static int numEmpleado;
    
    
    public ControllerReserva() {
        verCliente = false;
        extraerId();
    }
    
    

    public static boolean isVerCliente() {
        return verCliente;
    }

    public static void setNumeroReserva(int numeroReserva) {
        ControllerReserva.numeroReserva = numeroReserva;
    }

    public static int getNumeroReserva() {
        return numeroReserva;
    }

    public static int getNumEmpleado() {
        return numEmpleado;
    }
    
    
    
    public static ArrayList<Habitacion> cargarListaHabitaciones(Timestamp fi,Timestamp fs){
        ArrayList<Habitacion> lista;
        ReservaDAO modeloHab = new ReservaDAO();
        lista = modeloHab.listadoHabitacion(fi, fs);
        System.out.println(lista.size());
        return lista; 
    }
    
    
    public static void extraerId(){
        ReservaDAO modelo = new ReservaDAO();
        numeroReserva = modelo.extraerUltimoId();
    }
    
    public static ArrayList<Reserva> listarReservas()
    {
        ReservaDAO modelo = new ReservaDAO();
        ArrayList<Reserva> lista_reservas;
        lista_reservas = modelo.listadoReservas();
        return lista_reservas;
    }
    
    public static void registrarReserva(Reserva reserva) {
        int resultado = 0;
        ReservaDAO modelo = new ReservaDAO();
        resultado = modelo.grabarReserva(reserva);
        if (resultado == 1) {
            gestionMensajes("Registro Grabado con éxito",
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } else {
            gestionMensajes("Error al grabar",
                    "Confirmación", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    
    public static void verificarCliente(String idClien){
        ClientDAO modelo = new ClientDAO();
        ArrayList<Cliente> listaClientes;
        listaClientes = modelo.listCliente(0);
        for(int a = 0; a < listaClientes.size(); a++){
            String id = ""+listaClientes.get(a).getID();
            if(id.equalsIgnoreCase(idClien)){
                verCliente = true;
                a = listaClientes.size();
            }
        }     
    }
    
    
    
    public static void eliminarReserva(int ID) {
        ReservaDAO modelo = new ReservaDAO();
        if (modelo.borrarReserva(ID) == 1) {
            gestionMensajes(
                    "Registro Borrado con éxtio",
                    "Confirmación de acción",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            gestionMensajes(
                    "Error al borrar",
                    "Confirmación de acción",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
                        
        
    public static void gestionMensajes(String mensaje, String titulo, int icono) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, icono);
    }

    public static void setNumEmpleado(int numEmpl) {
        numEmpleado = numEmpl;
    }
    
    
}
