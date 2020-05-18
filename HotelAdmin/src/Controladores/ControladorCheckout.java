/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Checkout;
import Modelo.CheckoutDAO;

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
    
    
}
