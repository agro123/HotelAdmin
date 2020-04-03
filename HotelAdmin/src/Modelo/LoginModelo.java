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
public class LoginModelo {
    private String usuario;
    private String contrasena;
    
    
    public LoginModelo(){
        usuario="";
        contrasena="";
    }
    public LoginModelo(String usuario, String contrasena){
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    public String getUsuario(){
        return this.usuario;
    }
    
    public String getContrasena(){
     return this.contrasena;   
    }
    
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
}
