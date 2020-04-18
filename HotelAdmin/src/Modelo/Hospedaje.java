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
    private String id_hospedaje, id_habitacion, id_cliente, id_empleado;
    private Timestamp fIngreso, fSalida;
    private int numPersonas;
    private boolean estado;
    //---------Hospedaje con datos de Habitacion
    private String piso;
    
    
    public Hospedaje() {
     Date fecha = new Date();
     this.id_hospedaje  = "";
     this.id_habitacion  = "";
     this.id_cliente = "";
     this.id_empleado  = "";
     this.fIngreso = new Timestamp(fecha.getTime());
     this.fSalida = new Timestamp(fecha.getTime());
     this.numPersonas= 0;
     this.estado = false;
     this.piso = "";
    }
    public Hospedaje(String iho, String iha, String icl, String iem, 
            Timestamp fin, Timestamp fsa, int nump, boolean es ) {
     Date fecha = new Date();
     this.id_hospedaje  = iho;
     this.id_habitacion  = iha;
     this.id_cliente = icl;
     this.id_empleado  = iem;
     this.fIngreso = fin;
     this.fSalida = fsa;
     this.numPersonas= nump;
     this.estado = es;
    }
    public Hospedaje(String iho, String iha, String icl,  String p ) {
     Date fecha = new Date();
     this.id_hospedaje  = iho;
     this.id_habitacion  = iha;
     this.id_cliente = icl;
     this.piso = p;
    }    

     //------------------------------ SET Hospedaje ----------------------------
    public void setIdHospedaje (String iho)
    {
        this.id_hospedaje = iho;
    }
    public void setIdHabitacion (String iha)
    {
        this.id_habitacion = iha;
    }
    public void setIdEmpleado (String iem)
    {
        this.id_empleado = iem;
    }
    public void setIdCliente (String icl)
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
    //-----------------------------GET Hospedaje--------------------------------
    public String getIdHospedaje ()
    {
        return this.id_hospedaje;
    }
    public String getIdHabitacion ()
    {
        return this.id_habitacion;
    }
    public String getIdEmpleado ()
    {
        return this.id_empleado;
    }
    public String getIdCliente ()
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
    
    
}
