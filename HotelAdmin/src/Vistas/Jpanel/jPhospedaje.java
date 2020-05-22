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
public class jPhospedaje extends javax.swing.JPanel {
      private int idhospedaje;
      private int idCliente;
      private int habitacion;
      private int piso;
    /**
     * Creates new form jPhospedaje
     */
    public jPhospedaje() {
        
    }

    public jPhospedaje(int idhospedaje_,int idCliente_,int habitacion_,int piso_) {
        this.idhospedaje = idhospedaje_;
        this.idCliente = idCliente_;
        this.habitacion = habitacion_;
        this.piso = piso_;
        
        initComponents();
        
        //AÑADE AL LA INTERFAZ       
        jLidhospedaje.setText(String.valueOf(idhospedaje_));
        jLidCliente.setText(String.valueOf(idCliente_));
        jLidHabitacion.setText(String.valueOf(habitacion_));
        jLpiso.setText(String.valueOf(piso_));
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLpiso = new javax.swing.JLabel();
        jLidhospedaje = new javax.swing.JLabel();
        jLidCliente = new javax.swing.JLabel();
        jLidHabitacion = new javax.swing.JLabel();
        jBhospedaje = new javax.swing.JButton();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLpiso.setFont(new java.awt.Font("Decker", 0, 10)); // NOI18N
        jLpiso.setForeground(new java.awt.Color(112, 112, 112));
        jLpiso.setText("09");
        jLpiso.setToolTipText("");
        add(jLpiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 28, -1, -1));

        jLidhospedaje.setFont(new java.awt.Font("Decker", 0, 10)); // NOI18N
        jLidhospedaje.setForeground(new java.awt.Color(112, 112, 112));
        jLidhospedaje.setText("09");
        add(jLidhospedaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 28, -1, -1));

        jLidCliente.setFont(new java.awt.Font("Decker", 0, 10)); // NOI18N
        jLidCliente.setForeground(new java.awt.Color(112, 112, 112));
        jLidCliente.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLidCliente.setText("09");
        add(jLidCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 28, -1, -1));

        jLidHabitacion.setFont(new java.awt.Font("Decker", 0, 10)); // NOI18N
        jLidHabitacion.setForeground(new java.awt.Color(112, 112, 112));
        jLidHabitacion.setText("30");
        add(jLidHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 28, -1, -1));

        jBhospedaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/hospedajeSinSele.png"))); // NOI18N
        jBhospedaje.setBorder(null);
        jBhospedaje.setContentAreaFilled(false);
        jBhospedaje.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBhospedaje.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/hospedajeSele.png"))); // NOI18N
        jBhospedaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhospedajeActionPerformed(evt);
            }
        });
        add(jBhospedaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 725, 68));
    }// </editor-fold>//GEN-END:initComponents

    private void jBhospedajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhospedajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBhospedajeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBhospedaje;
    private javax.swing.JLabel jLidCliente;
    private javax.swing.JLabel jLidHabitacion;
    private javax.swing.JLabel jLidhospedaje;
    private javax.swing.JLabel jLpiso;
    // End of variables declaration//GEN-END:variables
}
