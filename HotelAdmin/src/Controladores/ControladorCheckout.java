/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Checkout;
import Modelo.CheckoutDAO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author crist
 */
public class ControladorCheckout {
    public static int extraerId()
    {
        CheckoutDAO hd = new CheckoutDAO();  
        return hd.extraerUltimoId();
       
    }       
    public static int grabarChekout(Checkout c)
    {
        CheckoutDAO hd = new CheckoutDAO();
        c.setIdCheckout(extraerId()+1);
        int resultado = hd.grabarCheckout(c);
        return resultado; 
    }
    public static Checkout getCheckout(int idcliente){
        CheckoutDAO cd = new CheckoutDAO();
        return cd.extraerCheckout(idcliente);
    }
    
    public static void imprimirFactura(ArrayList<String>info, int numf){
       FileWriter flwriter = null;
       Timestamp fecha = new Timestamp(System.currentTimeMillis());
	try {
	   //crea el flujo para escribir en el archivo
	   flwriter = new FileWriter("Facturas//Factura Num. "+numf+".txt");
	   //crea un buffer o flujo intermedio 
           //antes de escribir directamente en el archivo
	   BufferedWriter bfwriter = new BufferedWriter(flwriter);
           bfwriter.write("--------------ADMIN H S.A.S----------------\n");
           bfwriter.write("Factura de hospedaje Numero: "+numf
                   +"\nFecha: "+fecha+"\n");
           bfwriter.write("-------------------------------------------\n");
	   for (int i=0;i<info.size();i++) {
	        //escribe los datos en el archivo
		bfwriter.write(info.get(i));
                
	    }
	    //cierra el buffer intermedio
	    bfwriter.close();
	    JOptionPane.showMessageDialog(null,
                 "Factura creada");
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (flwriter != null) {
		    try {//cierra el flujo principal
			flwriter.close();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	} 
    }
    
}
