/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Jframe;

import Vistas.Jpanel.RegistrarSalidaGUI;

/**
 *
 * @author nicol
 */
public class CheckOut extends javax.swing.JPanel {

    /**
     * Creates new form CheckOut
     */
    public CheckOut() {
        initComponents();
        seleccionar();
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
        jLabel1 = new javax.swing.JLabel();
        jBregistrarSalida = new javax.swing.JButton();

        setBackground(new java.awt.Color(239, 239, 239));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPcontenedor.setBackground(new java.awt.Color(255, 255, 255));
        jPcontenedor.setVerifyInputWhenFocusTarget(false);
        jPcontenedor.setLayout(new java.awt.BorderLayout());
        add(jPcontenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 146, 1021, 534));

        jLabel1.setFont(new java.awt.Font("Decker", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(112, 112, 112));
        jLabel1.setText("Check-Out");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jBregistrarSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/botonRegistrarSalidaSinSele.png"))); // NOI18N
        jBregistrarSalida.setBorder(null);
        jBregistrarSalida.setContentAreaFilled(false);
        jBregistrarSalida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBregistrarSalida.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/botonRegistrarSalidaSele.png"))); // NOI18N
        add(jBregistrarSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 115, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void seleccionar(){
        jBregistrarSalida.setSelected(true);
        RegistrarSalidaGUI check = new RegistrarSalidaGUI();
        jPcontenedor.removeAll();
        jPcontenedor.add(check);
        jPcontenedor.revalidate();
        jPcontenedor.repaint();
        jPcontenedor.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBregistrarSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPcontenedor;
    // End of variables declaration//GEN-END:variables
}
