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
public class Cliente extends Persona{
    private String usuario, contraseña;
    
    public Cliente(){}
    
    // Creo constructor para Cliente para crear e insertar directamente a la BD
    public Cliente(int cedula, String nombre, String apellido, String correo, String direccion, int telefono){
    
    }
}
