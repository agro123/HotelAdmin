/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author Leonardo
 */
public class Reserva {
    
    private int numero_reserva, dias_estancia;
    private Timestamp fecha_reserva, fecha_ingreso, fecha_salida;
    private Habitacion unaHabitacion;
    private Cliente unCliente;    
}
