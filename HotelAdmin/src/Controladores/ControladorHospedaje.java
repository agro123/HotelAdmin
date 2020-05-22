/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Hospedaje;
import Modelo.HospedajeDAO;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class ControladorHospedaje {
    public static int extraerId()
    {
        HospedajeDAO hd = new HospedajeDAO();  
        return hd.extraerUltimoId();
       
    }       
       public static int grabarHospedaje(Hospedaje c)
    {
        HospedajeDAO hd = new HospedajeDAO();
        c.setIdHospedaje(extraerId()+1);
        int resultado = hd.grabarHospedaje(c);
        return resultado; 
    }

     public static ArrayList<Hospedaje> listadoHospedaje(int s)
    {
        ArrayList<Hospedaje> listado;
        listado = new ArrayList();
        HospedajeDAO a = new HospedajeDAO();
        listado = a.listadoHospedaje(s);
        return listado; 
    }
      public static int borrarHospedaje(String s)
    {
       HospedajeDAO a = new HospedajeDAO();
        int resultado = a.borrarHospedaje(s);
        return resultado; 
    }
           public static int modificarHospedaje(Hospedaje c)
    {
        HospedajeDAO a = new HospedajeDAO();
        int resultado =a.modificarHospedaje(c);
        return resultado; 
    }
    public static int cambiarEstado(int idhospedaje)
    {
        HospedajeDAO a = new HospedajeDAO();
        int resultado =a.cambiarEstado(idhospedaje);
        return resultado; 
    }  
}
