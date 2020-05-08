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
import Modelo.ClientDAO;
import Modelo.Cliente;
import Servicios.Fecha;
import Vistas.Jframe.Reservas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControllerReserva {

    
    Reservas vista;
    ReservaDAO modelo;
    HabitacionDAO modeloHab;
    int numeroReserva;
    int numEmpleado;
    boolean verCliente;
    
    public ControllerReserva(Reservas vista,ReservaDAO modelo) {
        verCliente = false;
        ReservasListener escucha = new ReservasListener();
        this.vista = vista;
        this.modelo = modelo;
        modeloHab = new HabitacionDAO();
        cargarListaHabitaciones();
        extraerId();
        
        
        
        
        vista.getpRealizarReserva().getjComboBox1().addItemListener(escucha);
        vista.getpRealizarReserva().getjBguardar().addActionListener(escucha);
        vista.getpRealizarReserva().getjBcancelar().addActionListener(escucha);
        
    }
    
    public void extraerId()
    {
        
    
        numeroReserva = modelo.extraerUltimoId();
       
    }
    
    
    
    
    public void cargarListaHabitaciones()
    {
        vista.CargarHabitaciones(modeloHab.listadoHabitacion());
        
    }
    
    
    public void registrarReserva() {

        Reserva reserva = new Reserva();
        numeroReserva += 1;
        int idHab = Integer.parseInt(vista.getTextjTidHabitacion());
        int idCli = Integer.parseInt(vista.getTextjTidCliente());
        Timestamp fecha = Fecha.crearFechaTimeStamp();
        int numPer = Integer.parseInt(vista.getTextjTfechaRserva());
        
        reserva.setNumero_reserva(numeroReserva);
        reserva.setNum_Habitacion(idHab);
        reserva.setNumCliente(idCli);
        reserva.setNum_Empleado(numEmpleado);
        reserva.setFecha_reserva(fecha);
        reserva.setFecha_ingreso(vista.getFechaIngreso());
        reserva.setFecha_salida(vista.getFechaSalida());
        reserva.setNum_Personas(numPer);
        
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
   
    
    public void verificarCliente(String idClien){
        ClientDAO modelo = new ClientDAO();
        ArrayList<Cliente> listaClientes;
        listaClientes = modelo.listCliente(0);
        for(int a=0;a < listaClientes.size();a++){
            
            String id = ""+listaClientes.get(a).getID();
            System.out.println(id+""+""+idClien+"s");
            if(id.equalsIgnoreCase(idClien)){
                verCliente = true;
                a = listaClientes.size();
            }
            else{
                verCliente = false;
            }
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
                verificarCliente(vista.getpRealizarReserva()
                        .getjTidCliente().getText());
                
                if(vista.getpRealizarReserva().validarCampos()==1)
                {
                    if(verCliente == true){
                    
                        registrarReserva();
                        vista.getpRealizarReserva().setearCampos();
                        cargarListaHabitaciones();
                    }else{
                        JOptionPane.showMessageDialog(null,"No existe el cliente "
                                + "en la base de datos");
                    }
                        
                }
            }else
            if(ae.getSource() == vista.getpRealizarReserva().getjBcancelar()){
                vista.getpRealizarReserva().setearCampos();
                
            }else 
            if(ae.getSource() == vista.getpRealizarReserva().getjBbuscar()){
                
                
            }
        }
            
                
        
        
        
        
        
    }

    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
    
    
}
