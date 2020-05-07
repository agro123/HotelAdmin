/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Jframe;

import Vistas.Jpanel.CheckInReservaPreviaGUI;
import Vistas.Jpanel.CheckInSinReservaGUI;

/**
 *
 * @author nicol
 */
public class CheckIn extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public CheckIn() {
        initComponents();
        seleccionarPrimero();     
    }
    
      public void seleccionarPrimero(){
        jBsinReserva.setSelected(false);
        jBreservaPrevia.setSelected(true); 
        
        CheckInReservaPreviaGUI check = new CheckInReservaPreviaGUI();
        jPcontenedor.removeAll();
        jPcontenedor.add(check);
        jPcontenedor.revalidate();
        jPcontenedor.repaint();
        jPcontenedor.setVisible(true);
    }
      
      

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPcontenedor = new javax.swing.JPanel();
        jBreservaPrevia = new javax.swing.JButton();
        jBsinReserva = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(239, 239, 239));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPcontenedor.setBackground(new java.awt.Color(255, 255, 255));
        jPcontenedor.setVerifyInputWhenFocusTarget(false);
        jPcontenedor.setLayout(new java.awt.BorderLayout());
        add(jPcontenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 146, 1021, 534));

        jBreservaPrevia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ReservaPrevia.png"))); // NOI18N
        jBreservaPrevia.setBorder(null);
        jBreservaPrevia.setContentAreaFilled(false);
        jBreservaPrevia.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ReservaPrevia-Selección.png"))); // NOI18N
        jBreservaPrevia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBreservaPreviaActionPerformed(evt);
            }
        });
        add(jBreservaPrevia, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 115, -1, -1));

        jBsinReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SinReserva.png"))); // NOI18N
        jBsinReserva.setBorder(null);
        jBsinReserva.setContentAreaFilled(false);
        jBsinReserva.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SinReservaSelec.png"))); // NOI18N
        jBsinReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsinReservaActionPerformed(evt);
            }
        });
        add(jBsinReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 115, -1, -1));

        jLabel1.setFont(new java.awt.Font("Decker", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(112, 112, 112));
        jLabel1.setText("Check-in");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jBreservaPreviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBreservaPreviaActionPerformed
        // TODO add your handling code here:
      
        jPcontenedor.removeAll();
        seleccionarPrimero();
    }//GEN-LAST:event_jBreservaPreviaActionPerformed

    private void jBsinReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsinReservaActionPerformed
        // TODO add your handling code here:
        jBreservaPrevia.setSelected(false); 
        jBsinReserva.setSelected(true);
           
        jPcontenedor.setVisible(false);
        jPcontenedor.setVisible(true);
        jPcontenedor.removeAll();
        CheckInSinReservaGUI check = new CheckInSinReservaGUI();      
        jPcontenedor.add(check);
        jPcontenedor.revalidate();
        jPcontenedor.repaint();
        jPcontenedor.setVisible(true);
    }//GEN-LAST:event_jBsinReservaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBreservaPrevia;
    private javax.swing.JButton jBsinReserva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPcontenedor;
    // End of variables declaration//GEN-END:variables
}