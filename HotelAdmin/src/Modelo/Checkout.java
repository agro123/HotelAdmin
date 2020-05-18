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
    private String medio_pago;
    private int id_checkout,id_hospedaje;
    private float valorTotal;
    private Timestamp fPago;
    
   //--------Vista cuenta
    private int id_cliente, id_habitacion; 
    private Timestamp fechaIngreso,fechaSalida;
    
    public Checkout(){
     this.id_hospedaje  = 0;
     this.id_checkout  = 0;
     this.medio_pago= "";
     this.valorTotal= 0;
     this.fPago = new Timestamp(1900,1 , 1, 00, 00, 00, 00);
    }
    public Checkout(int iho ,int ich,String mp,float vt,Timestamp fp){
     this.id_hospedaje  = iho;
     this.id_checkout  = ich;
     this.medio_pago= mp;
     this.valorTotal= vt;
     this.fPago = fp;
    }
    public Checkout(int iho ,int ich,String mp,float vt,Timestamp fp, 
            int ic,int ih, Timestamp fi, Timestamp fs){
     this.id_hospedaje  = iho;
     this.id_checkout  = ich;
     this.medio_pago= mp;
     this.valorTotal= vt;
     this.fPago = fp;
     this.id_cliente = ic;
     this.id_habitacion = ih;
     this.fechaIngreso = fi;
     this.fechaSalida = fs;
    }
     //------------------------------ SET checkout ----------------------------
    public void setIdHospedaje (int iho)
    {
        this.id_hospedaje = iho;
    }
    public void setIdCheckout(int ich)
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
    public void setIdCliente(int ic){
        this.id_cliente = ic;
    }
    public void setIdhabitacion(int id){
        this.id_habitacion = id;
    }
    public void setfechaIngreso(Timestamp fi){
        this.fechaIngreso = fi;
    }
    public void setfechaSalida(Timestamp fs){
        this.fechaSalida = fs;
    }

    //-----------------------------GET checkout --------------------------------
    public int getIdHospedaje ()
    {
        return this.id_hospedaje;
    }
    public int getIdCheckout()
    {
        return id_checkout;
    }
    public String getMediodepago()
    {
        return this.medio_pago;
    }
    public float getValorTotal()
    {
        return this.valorTotal;
    }
    public int getIdCliente(){
        return this.id_cliente;
    }
    public int getIdhabitacion(){
        return this.id_habitacion;
    }
    public Timestamp getFpago()
    {
        return this.fPago;
    }    
    public Timestamp getfechaIngreso(){
        return this.fechaIngreso;
    }
    public Timestamp getfechaSalida(){
        return this.fechaSalida;
    }
    //-------------------------------------------------------------------------- 
}
