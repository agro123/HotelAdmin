/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Vistas.Jframe.*;
import Vistas.Jpanel.*;
import Modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class ControllerServicios {

    String num_servicio;
    Services vista;
    RoomServicesDAO modelo;
    serviciosListener manejador_eventos;
    String id_Seleccionado;

    public ControllerServicios(Services vista, RoomServicesDAO modelo) {
        //serviciosListener esc = new serviciosListener(vista);
        
        this.modelo = modelo;
        this.vista = vista;
        extraerId();
        crearIdServicio();
        manejador_eventos = new serviciosListener();

        this.vista.getPanelAgregar().getjBcancelar().addActionListener
        (manejador_eventos);
        this.vista.getjBagregar().addActionListener(manejador_eventos);
        this.vista.getjBeliminar().addActionListener(manejador_eventos);
        this.vista.getjBmodificar().addActionListener(manejador_eventos);

        //this.vista.getPanelM().getJp().addMouseListener(manejador_eventos);
    }
    
    

    public class serviciosListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (ae.getSource() == vista.getPanelAgregar().getjBcancelar()) {
                if (vista.getPanelAgregar().getValidador() == "guardar") {
                    if (vista.getPanelAgregar().validarCampos() == 0) {
                        JOptionPane.showMessageDialog(null, "Ingrese Todos los "
                                + "Campos Requeridos");

                    }else
                    {
                        registrarServicio();
                        vista.getPanelAgregar().setearCampos();
                    }
                     
                } else if (vista.getPanelAgregar().getValidador() 
                        == "actualizar") {
                    actualizarServicio();
                    vista.getPanelAgregar().setearCampos();
                }
            }
            if (ae.getSource() == vista.getjBagregar()) {

                vista.mostrarPanelAgregar();
            }
            if (ae.getSource() == vista.getjBmodificar()) {
                vista.mostrarPanelModificar();
                leerServicios();
            }
            if (ae.getSource() == vista.getjBeliminar()) {

                vista.mostrarPanelEliminar();
                leerServicios();
            }

        }

    }

    public int extraerEntero(String s) {

        int valor = 0;
        for (int a = 0; a < s.length(); a++) {
            if (Integer.parseInt(s.substring(a, a + 1)) == 0) {
            } else {
                valor = Integer.parseInt(s.substring(0));
            }
        }
        JOptionPane.showMessageDialog(null, "hola  " + valor);
        return valor;
    }

    public void crearIdServicio() {
        int valor_num_extraido = extraerEntero(num_servicio.substring(1));
        int nuevoNum = valor_num_extraido + 1;
        String numero_ser = "" + nuevoNum;

        if (valor_num_extraido >= 999) {

            num_servicio = "S" + nuevoNum;
        } else if (valor_num_extraido >= 99) {
            num_servicio = "S0" + nuevoNum;
        } else if (valor_num_extraido >= 9) {
            num_servicio = "S00" + nuevoNum;
        } else {
            num_servicio = "S000" + nuevoNum;
        }

    }

    public void leerServicios() {

        ArrayList<RoomServices> lista_roomServices;
        lista_roomServices = modelo.listadoRoomServices();
        vista.getPanelModificar().CargarLista(lista_roomServices, "actualizar");
        vista.getPanelEliminar().CargarLista(lista_roomServices, "eliminar");

    }

    public void leerServicio_porID(String ID) {
        id_Seleccionado = ID;
        RoomServices obj;
        obj = modelo.extraerServicio_porID(ID);
        vista.getPanelAgregar().setValidadorBoton("actualizar");
        vista.getPanelModificar().llenarFormulario(obj);
    }
    
    

    public void registrarServicio() {

        RoomServices servicio = new RoomServices();

        servicio.setId_servicio(num_servicio);
        servicio.setNombrePro(vista.getPanelAgregar().getjTNombre().getText().trim());
        servicio.setCantidad(Integer.parseInt(vista.getPanelAgregar()
                .getjTcantidad().getText().trim()));
        servicio.setPrecio(Double.parseDouble(vista.getPanelAgregar()
                .getjTprecio().getText().trim()));

        int resultado = 0;

        resultado = modelo.grabarServicio(servicio);

        if (resultado == 1) {
            vista.gestionMensajes("Registro Grabado con éxito",
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            crearIdServicio();

        } else {
            vista.gestionMensajes("Error al grabar",
                    "Confirmación", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void extraerId()
    {
        String id="";
    
        id = modelo.extraerUltimoId();
        if(id == null)
        {
            num_servicio = "S0000";
        }
        else
        {
            num_servicio = id;
        }
    }

    public void actualizarServicio() {
        RoomServices servicio = new RoomServices();
        servicio.setId_servicio(id_Seleccionado);
        servicio.setNombrePro(vista.getPanelAgregar().getjTNombre()
                .getText().trim());
        servicio.setCantidad(Integer.parseInt(vista.getPanelAgregar()
                .getjTcantidad().getText().trim()));
        servicio.setPrecio(Double.parseDouble(vista.getPanelAgregar()
                .getjTprecio().getText().trim()));

        if (modelo.modificarRoomSercices(servicio) == 1) {
            vista.gestionMensajes(
                   
                    "Actualización exitosa",
                    "Confirmación ",
                    JOptionPane.INFORMATION_MESSAGE);
                    vista.getPanelAgregar().setValidadorBoton("guardar");
        } else {
            vista.gestionMensajes(
                    "Actualización Falida jajaja",
                    "Confirmación ",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void eliminarServicio(String ID) {
        id_Seleccionado = ID;
        if (modelo.borrarServicio(id_Seleccionado) == 1) {
            JOptionPane.showMessageDialog(null,
                    "Registro Borrado con éxtio",
                    "Confirmación de acción",
                    JOptionPane.INFORMATION_MESSAGE);
            extraerId();
            crearIdServicio();

        } else {
            JOptionPane.showMessageDialog(null,
                    "Error al borrar",
                    "Confirmación de acción",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
