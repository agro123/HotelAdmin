/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.ServiciosAdicionado;
import Modelo.ServiciosAdicionadoDAO;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class ControladorServiciosAdicionado {
  public static ArrayList<ServiciosAdicionado> listadoSH(int idcheckout){
      ServiciosAdicionadoDAO sad = new ServiciosAdicionadoDAO();
      return sad.listadoSH(idcheckout);
  }  
}
