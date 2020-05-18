/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Timestamp;
import java.util.Date;
/**
 *
 * @author crist
 */
public class Hospedaje {
    private int id_hospedaje, id_habitacion, id_cliente, id_empleado,id_reserva;
    private Timestamp fIngreso, fSalida;
    private int numPersonas;
    private boolean estado;
    //---------Hospedaje con datos de Habitacion
    private int piso;
    
    
    public Hospedaje() {
     Date fecha = new Date();
     this.id_hospedaje  = 0;
     this.id_habitacion  = 0;
     this.id_cliente = 0;
     this.id_empleado  = 0;
     this.fIngreso = new Timestamp(fecha.getTime());
     this.fSalida = new Timestamp(fecha.getTime());
     this.numPersonas= 0;
     this.estado = true;
     this.piso = 0;
     this.id_reserva = 0;
    }
    public Hospedaje(int iho, int iha, int icl, int iem, 
            Timestamp fin, Timestamp fsa, int nump, boolean es, int idr ) {
     Date fecha = new Date();
     this.id_hospedaje  = iho;
     this.id_habitacion  = iha;
     this.id_cliente = icl;
     this.id_empleado  = iem;
     this.fIngreso = fin;
     this.fSalida = fsa;
     this.numPersonas= nump;
     this.estado = es;
     this.id_reserva = idr;
    }
    public Hospedaje(int iho, int iha, int icl,  int p ) {
     Date fecha = new Date();
     this.id_hospedaje  = iho;
     this.id_habitacion  = iha;
     this.id_cliente = icl;
     this.piso = p;
    }    

     //------------------------------ SET Hospedaje ----------------------------
    public void setIdHospedaje (int iho)
    {
        this.id_hospedaje = iho;
    }
    public void setIdHabitacion (int iha)
    {
        this.id_habitacion = iha;
    }
    public void setIdEmpleado (int iem)
    {
        this.id_empleado = iem;
    }
    public void setIdCliente (int icl)
    {
        this.id_cliente = icl;
    }

    public void setFechaIngreso(Timestamp ingreso)
    {
        this.fIngreso = ingreso;
    }
    public void setFechaSalida(Timestamp salida)
    {
        this.fSalida = salida;
    }
        public void setNumeroPesonas(int nump)
    {
        this.numPersonas = nump;
    }
    
    public void setEstado (boolean estado)
    {
        this.estado = estado;
    }
    public void setId_reserva(int idr){
        this.id_reserva = idr;
    }
    //-----------------------------GET Hospedaje--------------------------------
    public int getIdHospedaje ()
    {
        return this.id_hospedaje;
    }
    public int getIdHabitacion ()
    {
        return this.id_habitacion;
    }
    public int getIdEmpleado ()
    {
        return this.id_empleado;
    }
    public int getIdCliente ()
    {
        return this.id_cliente;
    }

    public Timestamp getFechaIngreso()
    {
       return this.fIngreso;
    }
    public Timestamp getFechaSalida()
    {
        return this.fSalida;
    }
        public int getNumeroPesonas()
    {
        return this.numPersonas;
    }
    
    public boolean getEstado ()
    {
        return this.estado;
    }
    public int getId_reserva(){
        return this.id_reserva;
    }
    public int getPiso(){
        return this.piso;
    }
}
