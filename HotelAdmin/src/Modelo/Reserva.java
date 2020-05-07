/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author Leonardo
 */
public class Reserva {
    
    private int numero_reserva, num_Personas;
    private int num_Habitacion, numCliente, num_Empleado;
    private Timestamp fecha_reserva, fecha_ingreso, fecha_salida;
   
  

    public int getNumero_reserva() {
        return numero_reserva;
    }

    public int getNum_Personas() {
        return num_Personas;
       
    }

    public Timestamp getFecha_reserva() {
        return fecha_reserva;
    }

    public Timestamp getFecha_ingreso() {
        return fecha_ingreso;
    }

    public Timestamp getFecha_salida() {
        return fecha_salida;
    }

    public int getNum_Habitacion() {
        return num_Habitacion;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public int getNum_Empleado() {
        return num_Empleado;
    }

    
    
    

    public void setNumero_reserva(int numero_reserva) {
        this.numero_reserva = numero_reserva;
    }

    public void setNum_Personas(int num_Personas) {
        this.num_Personas = num_Personas;
    }

    public void setFecha_reserva(Timestamp fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public void setFecha_ingreso(Timestamp fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setFecha_salida(Timestamp fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public void setNum_Habitacion(int num_Habitacion) {
        this.num_Habitacion = num_Habitacion;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public void setNum_Empleado(int num_Empleado) {
        this.num_Empleado = num_Empleado;
    }

    
   
    
    
    
    
    
    
    
}
