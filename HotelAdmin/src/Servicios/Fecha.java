/*
 * Programa      : PROYECTO PROGRAMACION INTERACTIVA 2019- DVD RENTAL
 * Fecha         : Septiembre-2019
 * Objetivo      : Establecer y/o fecha en formato Timestamp y Date
 * Programadores : Cristhian Guzman, Nathalia Riascos, Vanesa Cifuentes
 * Clase         : Fachada
 */
package Servicios;

import Vistas.Jpanel.RealizarReservaGUI;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Fecha {

    public static Timestamp crearFechaTimestamp() {

        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        return currentTimestamp;
        

    }
    
    
    public static Timestamp formatearFechaIngreso(Date now){
        Timestamp currentTimestamp; 
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyyy");
        String fechaString = format.format(now);
        fechaString += " 14:00:00";
        format = new SimpleDateFormat("dd/MM/yyyyy HH:mm:ss");
        
        try {
            now = format.parse(fechaString);
            
        } catch (ParseException ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentTimestamp = new Timestamp(now.getTime());
        return currentTimestamp;
    }
    
    public static Timestamp formatearFechaSalida(Date now){
        Timestamp currentTimestamp; 
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyyy");
        String fechaString = format.format(now);
        fechaString += " 12:00:00";
        format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        try {
            
            now = format.parse(fechaString);
            
        } catch (ParseException ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentTimestamp = new Timestamp(now.getTime());
        return currentTimestamp;
    }
    
   
   
    public static Date dateTomorrow(Date now)
    {
        
        Date f = new Date(now.getTime()+86400000);
        return f;
    }
    
    
    public static Timestamp toTimestamp(String now)
    {
        now = now.substring(0,now.length()-2);
        char array[] = now.toCharArray();
        for(int i = 0; i < now.length();i++)
        {
            
            if(array[i] == '-'){
                array[i] = '/';
            }
        }
        
        now = new String(array);
       System.out.println(now);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date fecha = null;
        try {
            fecha = format.parse(now);
             
            Timestamp f = new Timestamp(fecha.getTime());
            return f;
        } catch (ParseException ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (Timestamp)fecha;
        
    }
    
   
    public static Timestamp crearFechaTimeStampEspecifico(int año,int mes,int dia)
    {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now;
        calendar.set(año,mes-1,dia,0,0,0);
        now = calendar.getTime();
        Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        return currentTimestamp;
    }
}
