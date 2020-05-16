/*
 * Programa      : PROYECTO PROGRAMACION INTERACTIVA 2019- DVD RENTAL
 * Fecha         : Septiembre-2019
 * Objetivo      : Establecer y/o fecha en formato Timestamp y Date
 * Programadores : Cristhian Guzman, Nathalia Riascos, Vanesa Cifuentes
 * Clase         : Fachada
 */
package Servicios;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Fecha {

    public static Timestamp crearFechaTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        return currentTimestamp;
    }
    
    
    public static Timestamp cambiarFecha(Date fecha){
        Timestamp ts = new Timestamp(fecha.getTime());
        return ts;
   }
   
    public static Date dateTomorrow(Date now){
        Date f = new Date(now.getTime()+86400000);
        return f;
    }
    
    
    
    public static Date dateYesterday(Date now){
        Date f = new Date(now.getTime()-86400000);
        return f;
    }
    
   /*
    public static Timestamp crearFechaTimeStampEspecifico(int año,int mes,int dia)
    {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now;
        calendar.set(año,mes-1,dia,0,0,0);
        now = calendar.getTime();
        Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        return currentTimestamp;
    }*/
}
