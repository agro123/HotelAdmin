/*
 * Programa	: Habitacion
 * Fecha	: 02/04/2020
 * Objetivo	: Modela la clase Habitación
 * Autor	: Leidy Vanesa Cifuentes
 */
package Modelo;


public class Habitaciones1 {
    
        private int id_habitacion;
        private String tipo_habitacion;
        private String piso;
        private int cantidadPersonas;
        private int precio_hab;
        private int num_camas;
        private boolean estado;
       /* private image img_habitacion;*/
    

    public Habitaciones1(){
    }

    public Habitaciones1(int id_habitacion, String tipo_habitacion, int precio_hab, int num_camas, String piso, int cantidadPersonas,  boolean estado){
        this.id_habitacion = id_habitacion;
        this.tipo_habitacion = tipo_habitacion;
        this.piso = piso;
        this.cantidadPersonas = cantidadPersonas; 
        this.precio_hab = precio_hab;
        this.num_camas = num_camas;
        this.estado = estado;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public String getPiso() {
        return piso;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public boolean isEstado() {
        return estado;
    }
    

    public int getPrecio_hab() {
        return precio_hab;
    }

    public int getNum_camas() {
        return num_camas;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public void setPrecio_hab(int precio_hab) {
        this.precio_hab = precio_hab;
    }

    public void setNum_camas(int num_camas) {
        this.num_camas = num_camas;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
    
   
   
 }

