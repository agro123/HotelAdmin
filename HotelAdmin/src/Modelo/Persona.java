/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Leonardo
 */
public class Persona {
    protected int ID;
    protected String nombre, apellido, correo, telefono, direccion;
    
    //-------------------- SET -------------------------------------------------
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }
    
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }
    
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }
    
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }
    
    public void setID(int id)
    {
        this.ID = id;
    }
    
    //------------------------- GET --------------------------------------------
    
    public String getNombre()
    {
        return nombre;
    }
    
    public String getApellido()
    {
        return apellido;
    }
    
    public String getCorreo()
    {
        return correo;
    }
    
    public String getTelefono()
    {
        return telefono;
    }
    
    public String getDireccion()
    {
        return direccion;
    }
    
    public int getID()
    {
        return ID;
    }
}
