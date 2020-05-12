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
public class HospedajeControllador {
       public static int grabarHospedaje(Hospedaje c)
    {
        HospedajeDAO hd = new HospedajeDAO();
        int resultado = hd.grabarHospedaje(c);
        return resultado; 
    }
     public static ArrayList<Hospedaje> listadoActor(int s)
    {
        ArrayList<Hospedaje> listado;
        listado = new ArrayList();
        HospedajeDAO a = new HospedajeDAO();
        listado = a.listadoHospedaje(s);
        return listado; 
    }
      public static int borrarActor(String s)
    {
       HospedajeDAO a = new HospedajeDAO();
        int resultado = a.borrarHospedaje(s);
        return resultado; 
    }
           public static int modificarActor(Hospedaje c)
    {
        HospedajeDAO a = new HospedajeDAO();
        int resultado =a.modificarHospedaje(c);
        return resultado; 
    }     
}
