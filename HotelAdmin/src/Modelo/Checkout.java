/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author crist
 */
public class Checkout {
    private String id_checkout, id_hospedaje,medio_pago;
    private int valorTotal;
    private Timestamp fPago;
    
    
    public Checkout(){
     this.id_hospedaje  = "";
     this.id_checkout  = "";
     this.medio_pago= "";
     this.valorTotal= 0;
     this.fPago = new Timestamp(1900,1 , 1, 00, 00, 00, 00);
    }
    public Checkout(String iho ,String ich,String mp,int vt,Timestamp fp){
     this.id_hospedaje  = iho;
     this.id_checkout  = ich;
     this.medio_pago= mp;
     this.valorTotal= vt;
     this.fPago = fp;
    }
     //------------------------------ SET Hospedaje ----------------------------
    public void setIdHospedaje (String iho)
    {
        this.id_hospedaje = iho;
    }
    public void setIdCheckout(String ich)
    {
        this.id_checkout = ich;
    }
    public void setmediodepago(String mdp)
    {
        this.medio_pago = mdp;
    }
    public void setValorTotal(int vl)
    {
        this.valorTotal = vl;
    }
    public void setFpago(Timestamp fp)
    {
        this.fPago = fp;
    }

    //-----------------------------GET Hospedaje--------------------------------
    public String getIdHospedaje ()
    {
        return this.id_hospedaje;
    }
    public String setIdCheckout()
    {
        return id_checkout;
    }
    public String setmediodepago()
    {
        return this.medio_pago;
    }
    public int setValorTotal()
    {
        return this.valorTotal;
    }
    public Timestamp setFpago()
    {
        return this.fPago;
    }
    //--------------------------------------------------------------------------
    public void sumar(int val){
        this.valorTotal= this.valorTotal+val; 
    }

  
}
