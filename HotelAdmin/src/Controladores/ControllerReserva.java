/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.HabitacionDAO;
import Modelo.Reserva;
import Modelo.ReservaDAO;
import Modelo.RoomServices;
import Servicios.Fecha;
import Vistas.Jframe.Reservas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControllerReserva {

    
    Reservas vista = new Reservas();
    ReservaDAO modelo = new ReservaDAO();
    HabitacionDAO modeloHab;
    
    public ControllerReserva(Reservas vista,ReservaDAO modelo) {
        
        ReservasListener escucha = new ReservasListener();
        this.vista = vista;
        this.modelo = modelo;
        modeloHab = new HabitacionDAO();
        cargarListaHabitaciones();
        
        
        
        
        vista.getpRealizarReserva().getjComboBox1().addItemListener(escucha);
        vista.getpRealizarReserva().getjBguardar().addActionListener(escucha);
        
    }
    
    
    
    public void cargarListaHabitaciones()
    {
        vista.CargarHabitaciones(modeloHab.listadoHabitacion());
        
    }
    
    
    public void registrarReserva() {

        Reserva reserva = new Reserva();
        //int idReser = numReserva;
        int idHab = Integer.parseInt(vista.getTextjTidHabitacion());
        int idCli = Integer.parseInt(vista.getTextjTidCliente());
        Timestamp fecha = Fecha.crearFechaTimeStamp();
        //int numPer = Integer.parseInt(vista.getT);
        
        
        reserva.setNum_Habitacion(idHab);
        reserva.setNumCliente(idCli);
        reserva.setNum_Empleado(1);
        reserva.setFecha_reserva(fecha);
        reserva.setFecha_ingreso(vista.getFechaIngreso());
        reserva.setFecha_salida(vista.getFechaSalida());
        
        int resultado = 0;

        resultado = modelo.grabarReserva(reserva);

        if (resultado == 1) {
            vista.gestionMensajes("Registro Grabado con éxito",
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            //crearIdServicio();

        } else {
            vista.gestionMensajes("Error al grabar",
                    "Confirmación", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    public class ReservasListener implements ItemListener, ActionListener{

        @Override
        public void itemStateChanged(ItemEvent ie) {
            cargarListaHabitaciones();
        }
        
        @Override
        public void actionPerformed(ActionEvent ae){
            if(ae.getSource() == vista.getpRealizarReserva().getjBguardar()){
                registrarReserva();
                
            }
        }
        
        
        
        
    }
    
    
}
