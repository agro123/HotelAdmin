
package Controladores;

import Modelo.Cliente;
import Modelo.ClientDAO;
import java.util.ArrayList;

/**
 * @author linkedV0id
 **/

public class ControladorCliente {
      public static int addClient(Cliente c)
    {
        ClientDAO clientDAO = new ClientDAO();
        int resultado = clientDAO.addClient(c);
        return resultado; 
    }
      
     public static ArrayList<Cliente> listClients(int id)
    {
        ArrayList<Cliente> clientList;
        clientList = new ArrayList();
        ClientDAO clientes = new ClientDAO();
        clientList = clientes.listCliente(id);
        return clientList; 
    }
     
     public static int modificarCliente(Cliente c)
    {
        ClientDAO clientDAO = new ClientDAO();
        int resultado = clientDAO.modifyClient(c);
        return resultado; 
    }  
}