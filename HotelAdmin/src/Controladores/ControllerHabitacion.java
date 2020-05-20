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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author vanes
 */
public class ControllerHabitacion {

    Habitaciones vista;
    //HabitacionListaGUI vista_actualizar_eliminar;
    HabitacionDAO modelo;
    int id_seleccionado;

    public ControllerHabitacion(Habitaciones vista_agregar, HabitacionDAO modelo) {

        this.vista = vista_agregar;
        this.modelo = modelo;

        HabitacionListener manejadoreventos = new HabitacionListener();
        this.vista.getPanelAgregar().getjBcancelar().addActionListener(manejadoreventos);

        this.vista.getjBagregar().addActionListener(manejadoreventos);
        this.vista.getjBmodificar().addActionListener(manejadoreventos);
        this.vista.getjBeliminar().addActionListener(manejadoreventos);
    }
    //--------------------------------------------------------------------------
    public ControllerHabitacion(){
        
    }
    
    public int getPrecioHabitacion(int idHabitacion){
        int precio= 0;
        HabitacionDAO hd = new HabitacionDAO();
        Habitacion h = new Habitacion();
        h = hd.extraerHabitaciones_porID(idHabitacion);
        precio = h.getPrecio_hab();
        return precio;
    }
    public static void cambiarEstadoHabitacion(int idHabitacion){
        HabitacionDAO hd = new HabitacionDAO();
        hd.cambiarEstadoHabitacion(idHabitacion);
    }      
    //--------------------------------------------------------------------------

    /*public ControllerHabitacion (HabitacionListaGUI vista_actualizar_eliminar, HabitacionDAO modelo){
        
        this.vista_actualizar_eliminar = vista_actualizar_eliminar;
        this.modelo = modelo;
    }*/
    //Método para leer las habitaciones y lograr cargar a la vista la información de habitaciones
    public void leerHabitaciones() {
        ArrayList<Habitacion> lista_habitaciones;

        lista_habitaciones = modelo.listadoHabitacion();
        vista.getPanelModificar().CargarLista(lista_habitaciones, "actualizar");
        vista.getPanelEliminar().CargarLista(lista_habitaciones, "eliminar");
    }

    public void leerHabitacionID(int idHabitacion) {

        id_seleccionado = idHabitacion;
        Habitacion objhabitaciones;
        objhabitaciones = modelo.extraerHabitaciones_porID(idHabitacion);

        vista.getPanelAgregar().setValidador("actualizar");
        vista.getPanelModificar().llenarFormulario(objhabitaciones);
    }

    //Implementar escuchas
    public class HabitacionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (ae.getSource() == vista.getPanelAgregar().getjBcancelar()) {
                if (vista.getPanelAgregar().getValidador() == "guardar") {
                    if (vista.getPanelAgregar().validarCampos() == 0) {
                        JOptionPane.showMessageDialog
                                                (null, "Introduce los campos");
                    } else {

                        registrarHabitacion();
                        vista.getPanelAgregar().setearCampos();
                    }

                } else if (vista.getPanelAgregar()
                        .getValidador() == "actualizar") {
                    actualizarHabitacion();
                    vista.getPanelAgregar().setearCampos();

                } else if (vista.getPanelAgregar()
                        .getValidador() == "eliminar") {
                    eliminarhabitacion(id_seleccionado);
                }

            }

            if (ae.getSource() == vista.getjBagregar()) {
                vista.mostrarPanelAgregar();

            }

            if (ae.getSource() == vista.getjBmodificar()) {
                vista.mostrarPanelModificar();
                leerHabitaciones();
            }

            if (ae.getSource() == vista.getjBeliminar()) {
                vista.mostrarPanelEliminar();
                leerHabitaciones();
            }

        }
    }

    //Método registrar habitación        
    public void registrarHabitacion() {

        if (vista.getPanelAgregar().getjTnumeroHabitacion().equals("")) {
            vista.getPanelAgregar().gestionMensajes
                 ("Ingrese el id de habitación",
                    "Error de Entrada", JOptionPane.ERROR_MESSAGE);

        } else {
            Habitacion habitacion = new Habitacion();

            habitacion.setId_habitacion(Integer.parseInt(vista.getPanelAgregar()
                    .getjTnumeroHabitacion().getText()));
            habitacion.setTipo_habitacion(vista.getPanelAgregar()
                    .getjCtipoHabi().getSelectedItem().toString());
            habitacion.setPiso(vista.getPanelAgregar().getjTpiso().getText());
            habitacion.setCantidadPersonas(
                    Integer.parseInt(vista.getPanelAgregar().getjTcapacidad()
                            .getText()));
            habitacion.setPrecio_hab(Integer.parseInt(vista.getPanelAgregar()
                    .getjTprecio().getText()));
            habitacion.setNum_camas(Integer.parseInt(vista.getPanelAgregar()
                    .getjCnumeroCamas().getSelectedItem().toString()));
            //habitacion.setEstado(vista_agregar.getPanelAM().getjTpiso().setVisible(true)); // Se debe cambiar por estado
            //

            int resultado;
            resultado = modelo.grabarHabitacion(habitacion);

            if (resultado == 1) {
                vista.getPanelAgregar().gestionMensajes
                        ("Registro Grabado con éxito",
                        "Mensaje grabar habitación", 
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                vista.getPanelAgregar().gestionMensajes
                         ("Error al grabar habitación",
                        "Mensaje grabar habitación", JOptionPane.ERROR_MESSAGE);
            }

//            vista.getPanelAgregar().gestionMensajes("Codigo ya está registrado",
//                    "Confirmación",
//                    JOptionPane.ERROR_MESSAGE);
        }
    }
    //Método para modificar habitación   

    public void actualizarHabitacion() {

        Habitacion habitacion = new Habitacion();

        habitacion.setId_habitacion(id_seleccionado);

        //habitacion.setId_habitacion(Integer.parseInt(vista_agregar.getPanelAM().getjTnumeroHabitacion().getText()));
        habitacion.setTipo_habitacion(vista.getPanelAgregar()
                .getjCtipoHabi().getSelectedItem().toString().trim());
        habitacion.setPiso(vista.getPanelAgregar().getjTpiso().getText());
        habitacion.setCantidadPersonas(Integer.parseInt(vista.getPanelAgregar()
                .getjTcapacidad().getText()));
        habitacion.setPrecio_hab(Integer.parseInt(vista.getPanelAgregar()
                .getjTprecio().getText().trim()));
        habitacion.setNum_camas(Integer.parseInt(vista.getPanelAgregar()
                .getjCnumeroCamas().getSelectedItem().toString().trim()));
        //habitacion.setEstado(vista_agregar.getPanelAM().getjTpiso().setVisible(true)); // Se debe cambiar por estado

        if (modelo.modificarHabitacion(habitacion) == 1) {
            vista.getPanelAgregar().gestionMensajes(
                    "Se modificó la habitación correctamente",
                    "Mensaje de actualización",
                    JOptionPane.INFORMATION_MESSAGE);
            vista.getPanelAgregar().setValidador("guardar");
        } else {
            vista.getPanelAgregar().gestionMensajes(
                    "Actualización fallida",
                    "Mensaje de actualización",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    // }

    //Método borrar habitación
    public void eliminarhabitacion(int id_habitacion) {

        id_seleccionado = id_habitacion;

        if (modelo.borrarHabitacion(id_seleccionado) == 1) {
            JOptionPane.showMessageDialog(null,
                    "Registro de habitación borrado",
                    "Mensaje borrar habitación",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null,
                    "Error al borrar habitación",
                    "Mensaje borrar habitación",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
