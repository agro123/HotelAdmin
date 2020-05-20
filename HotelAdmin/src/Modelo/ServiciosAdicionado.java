/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author crist
 */
public class ServiciosAdicionado {
    //--------------Vista--------------
    private int id_checkout, id_servicio, cantidad, precio, total;
    private String nombre_producto;
   
    public ServiciosAdicionado(){
        this.id_checkout = 0;
        this.id_servicio = 0;
        this.cantidad = 0;
        this.precio = 0;
        this.total = 0;
        this.nombre_producto = "";
    }
    
    public ServiciosAdicionado(int id_checkout,int id_servicio,int cantidad,
            int precio,int total,String nombre_pro){
        this.id_checkout = id_checkout;
        this.id_servicio = id_servicio;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.nombre_producto = nombre_pro;
    }
    //----------------------GET-------------------------------------------------
   public int getIdCheckout()
    {
        return id_checkout;
    }
   public int getIdServicio()
    {
        return id_servicio;
    }
   public int getcantidad()
    {
        return cantidad;
    }
   public int getPrecio()
    {
        return precio;
    }
   public int getTotal()
    {
        return total;
    }
   public String getNombreProducto()
    {
        return nombre_producto;
    }
    
    
    //---------------------SET--------------------------------------------------
   public void setIdCheckout(int m)
    {
        id_checkout = m;
    }
   public void  setIdServicio(int m)
    {
        id_servicio = m;
    }
   public void  setcantidad(int m)
    {
         cantidad = m;
    }
   public void  setPrecio(int m)
    {
        precio = m;
    }
   public void  setTotal(int m)
    {
        total = m;
    }
   public void  setNombreProducto(String m)
    {
         nombre_producto = m;
    }      
 
}
