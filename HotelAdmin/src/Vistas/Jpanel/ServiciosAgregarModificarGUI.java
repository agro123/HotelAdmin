/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Jpanel;

/**
 *
 * @author nicol
 */
public class ServiciosAgregarModificarGUI extends javax.swing.JPanel {

    /**
     * Creates new form ServiciosAgregarModificar
     */
    public ServiciosAgregarModificarGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTprecio = new javax.swing.JTextField();
        jTNombre = new javax.swing.JTextField();
        jTcantidad = new javax.swing.JTextField();
        jBcancelar = new javax.swing.JButton();
        jBguardar = new javax.swing.JButton();
        jLfondo = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTprecio.setBackground(new java.awt.Color(241, 242, 246));
        jTprecio.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jTprecio.setForeground(new java.awt.Color(153, 153, 153));
        jTprecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        add(jTprecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 190, 30));

        jTNombre.setBackground(new java.awt.Color(241, 242, 246));
        jTNombre.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jTNombre.setForeground(new java.awt.Color(153, 153, 153));
        jTNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        add(jTNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 190, 30));

        jTcantidad.setBackground(new java.awt.Color(241, 242, 246));
        jTcantidad.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jTcantidad.setForeground(new java.awt.Color(153, 153, 153));
        jTcantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        add(jTcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 190, 30));

        jBcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar-sinSeleccion.png"))); // NOI18N
        jBcancelar.setContentAreaFilled(false);
        jBcancelar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar-seleccionado.png"))); // NOI18N
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });
        add(jBcancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, 130, 50));

        jBguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar-blanco.png"))); // NOI18N
        jBguardar.setBorder(null);
        jBguardar.setBorderPainted(false);
        jBguardar.setContentAreaFilled(false);
        jBguardar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar-seleccionado.png"))); // NOI18N
        jBguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBguardarActionPerformed(evt);
            }
        });
        add(jBguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, -1, -1));

        jLfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo-servicios.png"))); // NOI18N
        jLfondo.setPreferredSize(new java.awt.Dimension(739, 429));
        add(jLfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 380, 340));
    }// </editor-fold>//GEN-END:initComponents

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jBguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBguardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBguardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBguardar;
    private javax.swing.JLabel jLfondo;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTcantidad;
    private javax.swing.JTextField jTprecio;
    // End of variables declaration//GEN-END:variables
}
