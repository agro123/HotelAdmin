/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class ControladorEmpleado {
      public static int grabarEmpleado(Empleado c)
    {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        int resultado = empleadoDAO.grabarEmpleado(c);
        return resultado; 
    }
      
     public static ArrayList<Empleado> listadoEmpleado(int id)
    {
        ArrayList<Empleado> listado;
        listado = new ArrayList();
        EmpleadoDAO empleados = new EmpleadoDAO();
        listado = empleados.listadoEmpleado(id);
        return listado; 
    }
     
           public static int modificarEmpleado(Empleado c)
    {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        int resultado =empleadoDAO.modificarEmpleado(c);
        return resultado; 
    } 
}
