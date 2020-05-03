/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class RoomServices {
    
    String id_servicio,nombrePro;
    int cantidad;
    Double precio;

    public RoomServices() {
    }

   /* 
    public RoomServices(String id_servicio, String nombrePro, int cantidad, Double precio) {
        this.id_servicio = id_servicio;
        this.nombrePro = nombrePro;
        this.cantidad = cantidad;
        this.precio = precio;
    }*/
    
    

    public String getId_servicio() {
        return id_servicio;
    }

    public String getNombrePro() {
        return nombrePro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setId_servicio(String id_servicio) {
        this.id_servicio = id_servicio;
    }

    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    
    


}
