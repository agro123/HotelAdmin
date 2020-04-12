/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.LoginModelo;
import Modelo.LoginDAO;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class LoginControlador {
     public static int grabarLogin(LoginModelo c)
    {
        LoginDAO fc = new LoginDAO();
        int resultado = fc.grabarLogin(c);
        return resultado; 
    }
     
     public static ArrayList<LoginModelo> listadoLogin(int usuario)
    {
        ArrayList<LoginModelo> listado;
        listado = new ArrayList();
        LoginDAO fc = new LoginDAO();
        listado = fc.listadoLogin(usuario);
        return listado; 
    }
}
