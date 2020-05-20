/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Jpanel;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import Controladores.ControladorCheckout;
import Controladores.ControladorHospedaje;
import Controladores.ControladorServiciosAdicionado;
import Controladores.ControllerHabitacion;
import Modelo.Checkout;
import Modelo.ServiciosAdicionado;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;    


/**
 *
 * @author nicol
 */
public class RegistrarSalidaGUI extends javax.swing.JPanel {

    private int idEmpleado;
    private int idcheckout;
    private ArrayList<String> servicios;
    private boolean found;
    private int valorTotal;
    private Timestamp fecha_de_hoy;
    private int habitacion;
    
    /**
     * Creates new form RegistrarSalidaGUI
     */
    public RegistrarSalidaGUI(int ide) {
        initComponents();
         this.fecha_de_hoy = new Timestamp(System.currentTimeMillis());
         this.idEmpleado = ide;
    }
    
    
    private void agregarDatosFactura(){
       ControladorCheckout cc= new ControladorCheckout();
       String id = jTbuscador.getText().trim();
       Checkout co = new Checkout();
       int idcliente;
       found=false;
       if(id.equalsIgnoreCase("")||
             id.equalsIgnoreCase("Buscar factura por ID de cliente")){
            JOptionPane.showMessageDialog(null,
               "Debe de ingresar la identificacion de un cliente "
                       + "para encontrar la factura");
            vaciarCampos();
        } else if(id.length()>10){
        JOptionPane.showMessageDialog(null,
               "La identificación no debe de tener mas de 10 caracateres.");
            vaciarCampos();
       }
        else{
          idcliente = Integer.parseInt(id);
          co = cc.getCheckout(idcliente);
          idcheckout = co.getIdCheckout();
          if(co.getIdCheckout()!=0){
              jLcliente.setText(co.getIdCliente()+"");
              jLhabitacion.setText(co.getIdhabitacion()+"");
              habitacion = co.getIdhabitacion();
              jLfechaIngreso.setText(co.getfechaIngreso()+"");
              jLfechaSalida.setText(co.getfechaSalida()+"");
              calcularValorTotal(co.getValorTotal());
              jLprecio.setText(valorTotal+"$");
              found = true;
           }else {
              JOptionPane.showMessageDialog(null,
                 "No se encontro ninguna factura con este ID,"
                       + " verifique si fue ingresado correctamente");
              vaciarCampos();
            }   
        }          
      //LISTA DE SERVICIOS 
       if(found){
   
          int n = numDias(); 
          int p = co.getPrecioH();
          servicios = new ArrayList();
          DefaultListModel modelo = new DefaultListModel();
          servicios.add(1+". "+"Habitacion " + co.getTipoH()
                 +" "+ n +" días");
          servicios.add( "    \nValor por día: " + p + "$");
          servicios.add( "    \nValor de estadía: " + n*p+ "$");
          adicionarServicios();
         for(int i = 0; i<servicios.size(); i++){
            modelo.addElement(servicios.get(i));
         }
         
          jListServicios.setModel(modelo);
       }
    }
    
    private int numDias(){
        int dias=0;
        try {
              String fi = jLfechaIngreso.getText();
              String fs = jLfechaSalida.getText();
              fi = fi.substring(0, fi.indexOf(" "));
              fs = fs.substring(0, fs.indexOf(" "));
              SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");       
              Date fechaInicial = dateFormat.parse(fi);
              Date fechaFinal = dateFormat.parse(fs);
              dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())
                      /86400000);      
        } catch(ParseException e){
            JOptionPane.showMessageDialog(null,
                 "Ocurrio un error al realizar el Hospedaje");
       } finally {
           return dias;
       }
    }    
    private void vaciarCampos(){
              jLcliente.setText("  ");
              jLhabitacion.setText("  ");
              jLfechaIngreso.setText("  ");
              jLfechaSalida.setText("  "); 
              jLprecio.setText("  ");
              DefaultListModel modelo = new DefaultListModel();
              jListServicios.setModel(modelo);
              found = false;
              idcheckout = 0;
              valorTotal = 0;
              habitacion = 0;
    }
    
    private void imprimirFactura(){
        ArrayList<String>info = new ArrayList();      
        info.add("C.C. empleado: "+idEmpleado+"\n");
        info.add("C.C. cliente: " + jLcliente.getText()+ "\n");
        info.add("Habitación: "+habitacion+"\n");
        info.add("Fecha de ingreso: "+jLfechaIngreso.getText()+"\n");
        info.add("Fecha de salida: "+jLfechaSalida.getText()+"\n");   
        info.add("\n-------------------------------------------\n");
        info.add("Servicios consumidos:\n");
        for(int i= 0; i<servicios.size();i++){
            info.add(servicios.get(i));
        }
        info.add("\n-------------------------------------------\n");
        info.add("Total a pagar: "+jLprecio.getText()+"\n");
        info.add("Medio de pago: "+jCBmedioPago.getSelectedItem()+"\n");
        ControladorCheckout.imprimirFactura(info, idcheckout);
    }
    
    private void adicionarServicios(){
        ArrayList<ServiciosAdicionado>h = ControladorServiciosAdicionado
                .listadoSH(idcheckout);
        int item = 1;
        String t;
        for(int i = 0; i<h.size(); i++){
            item++;
            t ="\n" +item+". "+h.get(i).getNombreProducto()
                    +" x"+h.get(i).getcantidad();
            servicios.add(t);
            t = "    \nValor por unidad: " + h.get(i).getPrecio() + "$";
            servicios.add(t);
            t = "    \nValor total: " + h.get(i).getTotal() + "$";
            servicios.add(t);
         }
    }
    private void calcularValorTotal(int h){
        valorTotal = h;
        ArrayList<ServiciosAdicionado>s = ControladorServiciosAdicionado
                .listadoSH(idcheckout);
        for(int i = 0; i<s.size(); i++){
           valorTotal += s.get(i).getTotal();
         }
    }
    private void registrarSalida(){
        Checkout c = new Checkout();
        c.setIdCheckout(idcheckout);
        c.setFpago(fecha_de_hoy);
        c.setValorTotal(valorTotal);
        ControladorCheckout.registrarSalida(c);
        c.setmediodepago(jCBmedioPago.getSelectedItem()+"");
        ControladorHospedaje.cambiarEstado(idcheckout);
        ControllerHabitacion.cambiarEstadoHabitacion(habitacion);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTbuscador = new javax.swing.JTextField();
        jLcliente = new javax.swing.JLabel();
        jLhabitacion = new javax.swing.JLabel();
        jLfechaIngreso = new javax.swing.JLabel();
        jLfechaSalida = new javax.swing.JLabel();
        jLprecio = new javax.swing.JLabel();
        jCBmedioPago = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListServicios = new javax.swing.JList<>();
        jLfactura = new javax.swing.JLabel();
        jBcancelar = new javax.swing.JButton();
        jBcheckOut = new javax.swing.JButton();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTbuscador.setFont(new java.awt.Font("Decker", 0, 15)); // NOI18N
        jTbuscador.setForeground(new java.awt.Color(191, 191, 191));
        jTbuscador.setText("Buscar reserva por ID de cliente");
        jTbuscador.setBorder(null);
        jTbuscador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTbuscadorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTbuscadorFocusLost(evt);
            }
        });
        jTbuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTbuscadorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTbuscadorKeyTyped(evt);
            }
        });
        add(jTbuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 47, -1, -1));

        jLcliente.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        jLcliente.setForeground(new java.awt.Color(112, 112, 112));
        jLcliente.setText("  ");
        add(jLcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 205, 70, -1));

        jLhabitacion.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        jLhabitacion.setForeground(new java.awt.Color(112, 112, 112));
        jLhabitacion.setText("  ");
        add(jLhabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 222, 110, -1));

        jLfechaIngreso.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        jLfechaIngreso.setForeground(new java.awt.Color(112, 112, 112));
        jLfechaIngreso.setText("  ");
        add(jLfechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 238, 110, -1));

        jLfechaSalida.setFont(new java.awt.Font("Decker", 0, 12)); // NOI18N
        jLfechaSalida.setForeground(new java.awt.Color(112, 112, 112));
        jLfechaSalida.setText("  ");
        add(jLfechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 255, 110, -1));

        jLprecio.setFont(new java.awt.Font("Decker", 1, 20)); // NOI18N
        jLprecio.setForeground(new java.awt.Color(112, 112, 112));
        jLprecio.setText("  ");
        add(jLprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 148, 80, -1));

        jCBmedioPago.setBackground(new java.awt.Color(241, 242, 246));
        jCBmedioPago.setForeground(new java.awt.Color(112, 112, 112));
        jCBmedioPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credio", "Debito", "Efectivo"}));
        add(jCBmedioPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 273, 110, 18));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar-azul.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 44, 24, 24));

        jScrollPane2.setBorder(null);
        jScrollPane2.setForeground(new java.awt.Color(112, 112, 112));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setFont(new java.awt.Font("Decker", 0, 9)); // NOI18N

        jScrollPane2.setViewportView(jListServicios);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 335, 320, 120));

        jLfactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Factura.png"))); // NOI18N
        add(jLfactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 88, -1, -1));

        jBcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar-sinSeleccion.png"))); // NOI18N
        jBcancelar.setContentAreaFilled(false);
        jBcancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBcancelar.setInheritsPopupMenu(true);
        jBcancelar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar-seleccionado.png"))); // NOI18N
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });
        add(jBcancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 470, 130, 50));

        jBcheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/botonCheckOutSinSele.png"))); // NOI18N
        jBcheckOut.setBorder(null);
        jBcheckOut.setContentAreaFilled(false);
        jBcheckOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBcheckOut.setInheritsPopupMenu(true);
        jBcheckOut.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/botonCheckOutSele.png"))); // NOI18N
        jBcheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcheckOutActionPerformed(evt);
            }
        });
        add(jBcheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(506, 470, 140, -1));
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregarDatosFactura();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        vaciarCampos();
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jBcheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcheckOutActionPerformed
        if(found){
        imprimirFactura();
        registrarSalida();
        found = false;
        }
    }//GEN-LAST:event_jBcheckOutActionPerformed

    private void jTbuscadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTbuscadorFocusLost
        if(jTbuscador.getText().equalsIgnoreCase("")){ 
        jTbuscador.setText("Buscar factura por ID de cliente");
        }
    }//GEN-LAST:event_jTbuscadorFocusLost

    private void jTbuscadorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTbuscadorFocusGained
        jTbuscador.setText("");
        vaciarCampos();
    }//GEN-LAST:event_jTbuscadorFocusGained

    private void jTbuscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTbuscadorKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jTbuscadorKeyTyped

    private void jTbuscadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTbuscadorKeyPressed
        int key = evt.getKeyCode();
        if(key == KeyEvent.VK_ENTER){
        agregarDatosFactura();
        }
    }//GEN-LAST:event_jTbuscadorKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBcheckOut;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBmedioPago;
    private javax.swing.JLabel jLcliente;
    private javax.swing.JLabel jLfactura;
    private javax.swing.JLabel jLfechaIngreso;
    private javax.swing.JLabel jLfechaSalida;
    private javax.swing.JLabel jLhabitacion;
    private javax.swing.JList<String> jListServicios;
    private javax.swing.JLabel jLprecio;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTbuscador;
    // End of variables declaration//GEN-END:variables
}
