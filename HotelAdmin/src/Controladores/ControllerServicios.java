/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControllerServicios {

    static int num_servicio = 0;

    //valida si se requiere hacer una actuslizacion o ingresar un registro
    static String opcionEjec = "guardar";

    public static ArrayList<RoomServices> desplegarServicios() {
        RoomServicesDAO modelo = new RoomServicesDAO();
        ArrayList<RoomServices> lista_roomServices;
        lista_roomServices = modelo.listadoRoomServices();
        return lista_roomServices;
    }

    public static RoomServices leerServicio_porID(int ID) {
        RoomServicesDAO modelo = new RoomServicesDAO();
        RoomServices obj;
        obj = modelo.extraerServicio_porID(ID);
        return obj;
    }

    public static void registrarServicio(RoomServices servicio) {
        RoomServicesDAO modelo = new RoomServicesDAO();
        int resultado = 0;
        resultado = modelo.grabarServicio(servicio);
        if (resultado == 1) {
            gestionMensajes("Registro Grabado con éxito",
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } else {
            gestionMensajes("Error al grabar",
                    "Confirmación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void extraerId() {
        int id = 0;
        RoomServicesDAO modelo = new RoomServicesDAO();
        id = modelo.extraerUltimoId();
        if (id == 0) {
            num_servicio = 1;
        } else {
            num_servicio = id + 1;
        }
    }

    public static void actualizarServicio(RoomServices servicio) {

        RoomServicesDAO modelo = new RoomServicesDAO();
        if (modelo.modificarRoomSercices(servicio) == 1) {
            gestionMensajes(
                    "Actualización exitosa",
                    "Confirmación ",
                    JOptionPane.INFORMATION_MESSAGE);
            opcionEjec = "guardar";
        } else {
            gestionMensajes(
                    "Actualización Falida",
                    "Confirmación ",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void eliminarServicio(int ID) {
        RoomServicesDAO modelo = new RoomServicesDAO();
        if (modelo.borrarServicio(ID) == 1) {
            gestionMensajes(
                    "Registro Borrado con éxtio",
                    "Confirmación de acción",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            gestionMensajes(
                    "Error al borrar",
                    "Confirmación de acción",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public static int getNum_servicio() {
        return num_servicio;
    }

    public static void setNum_servicio(int num_servicio) {
        ControllerServicios.num_servicio = num_servicio;
    }

    public static void gestionMensajes(String mensaje, String titulo, int icono) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, icono);
    }

    public static String getOpcionEjec() {
        return opcionEjec;
        
    }

    public static void setOpcionEjec(String opcionEjec) {
        ControllerServicios.opcionEjec = opcionEjec;
    }

}
