/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Jpanel;

import Controladores.ControllerReserva;
import Modelo.Reserva;
import Vistas.Jframe.Reservas;
import java.util.ArrayList;

/**
 *
 * @author nicol
 */
public class ListaReservasGUI extends javax.swing.JPanel {

    /**
     * Creates new form listaReservas
     */
    
    
    public ListaReservasGUI(Reservas frame_reservas) {
        
        initComponents();        
        cargarListaReservas(frame_reservas);
        
    }
    
    
    private void agregarReservas(){
        
        jPcontenido.removeAll();
        for (int i=0;i<9;i++){
        jPreservasHospedaje jp = new jPreservasHospedaje(9,0,9,8,"23-04-2000 12:00 PM","23-04-2000 12:00 PM");
        jPcontenido.add(jp);
          
       }
        jPcontenido.revalidate();
        jPcontenido.repaint();
    }
    
    
    public void cargarListaReservas(Reservas frame_reservas){
        
        
        ArrayList<Reserva> listaReservas;
        listaReservas = ControllerReserva.listarReservas();
        jPcontenido.removeAll();
        for (int i = 0; i < listaReservas.size(); i++) {
            
            int num_res = listaReservas.get(i).getNumero_reserva();
            int id_cli = listaReservas.get(i).getNumCliente();
            int id_hab = listaReservas.get(i).getNum_Habitacion();
            int cant_per = listaReservas.get(i).getNum_Personas();
            
            String fech_in = listaReservas.get(i).getFecha_ingreso().toString();
            String fech_out = listaReservas.get(i).getFecha_salida().toString();
        
           
            
            
            jPreservasHospedaje jp = new jPreservasHospedaje
                    (num_res,id_cli,id_hab,cant_per,fech_in,fech_out);
            jp.setFrame_reservas(frame_reservas);
            
            
            
            jPcontenido.add(jp);  
              
           
        }
        
        jPcontenido.revalidate();
        jPcontenido.repaint();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPcontenido = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setOpaque(false);

        jPcontenido.setBackground(new java.awt.Color(255, 255, 255));
        jPcontenido.setLayout(new java.awt.GridLayout(0, 1, 0, 1));
        jScrollPane2.setViewportView(jPcontenido);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 140, 860, 330));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Marco.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 90, 880, 400));

        jTextField1.setFont(new java.awt.Font("Decker", 0, 15)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(191, 191, 191));
        jTextField1.setText("Buscar reserva por ID de cliente");
        jTextField1.setBorder(null);
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 47, -1, -1));

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
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPcontenido;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
