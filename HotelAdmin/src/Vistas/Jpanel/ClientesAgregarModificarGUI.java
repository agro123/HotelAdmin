/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Jpanel;

import Controladores.ControladorCliente;
import Modelo.Cliente;
import Modelo.ClientDAO;
import Vistas.Jframe.ClienteRecepcionista;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nicol
 */
public class ClientesAgregarModificarGUI extends javax.swing.JPanel {

    ClientDAO clientDAO = new ClientDAO();
    ControladorCliente Controladorcliente = new ControladorCliente();
    ClienteRecepcionista panel_prin;
    String validar_panel;

    public String getValidar_panel() {
        return validar_panel;
    }

    public void setValidar_panel(String value) {
        this.validar_panel = value;
    }

    /**
     * Creates new form ClientesAgregarModificarGUI
     */
    public ClientesAgregarModificarGUI() {

        initComponents();

    }

    public ClientesAgregarModificarGUI(ClienteRecepcionista cr) {
        this.panel_prin = cr;
        initComponents();

    }

    public void llenarValores(Cliente cliente) {

        String Cedula = String.valueOf(cliente.getID());
        jTCedula.setText(Cedula);
        jTnombre.setText(cliente.getNombre());
        jTapellido.setText(cliente.getApellido());
        jTcorreo.setText(cliente.getCorreo());
        jTtelefono.setText(cliente.getTelefono());
        jTdireccion.setText(cliente.getDireccion());
    }

    public Cliente getData() {
        int cedula, telefono;
        String nombre, apellido, correo, direccion;

        // Se extrae la info de los campos y se guardan en una var(gClient) que cambiará de valor cada vez 
        // que se oprima "Guardar", luego en añadir se enviará toda esta info a la BD
        Cliente gClient = new Cliente();
        //Extraer el nombre y apellido de jTnombre
        String[] names = jTnombre.getText().split(" ");
        gClient.setNombre(names[0]);
        if (names.length > 1) {
            gClient.setApellido(names[1]);
        } else {
            apellido = "";
        }

        gClient.setID(Integer.parseInt(jTCedula.getText().trim()));
        gClient.setApellido(jTapellido.getText().trim());
        gClient.setCorreo(jTcorreo.getText().trim());
        gClient.setDireccion(jTdireccion.getText().trim());
        gClient.setTelefono(jTtelefono.getText().trim());

        return gClient;

    }

    public boolean idFound(Integer id) {
        ArrayList<Cliente> listadoCli = new ArrayList<>();
        listadoCli = Controladorcliente.listClients(0);
        boolean found = false;

        for (int i = 0; i < listadoCli.size(); i++) {
            if (id == listadoCli.get(i).getID()) {
                found = true;
            }
        }

        return found;

    }

    public void saveData() {
        try {
            Cliente cliente = getData();
            if (idFound(cliente.getID())) {
                if (verifyData(cliente)) {
                    clientDAO.modifyClient(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente modificado!");
                }
            } else {
                if (verifyData(cliente)) {
                    clientDAO.addClient(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente agregado!");
                }
            }
        } catch (Exception e) {
        }
    }

    public boolean verifyData(Cliente gClient) {
        int cedula, telefono;
        String nombre, apellido, correo, direccion;
        String regexEmail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|"
                + "}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        correo = "";
        try {
            cedula = gClient.getID();
            try {
                telefono = Integer.parseInt(gClient.getTelefono());
            } catch (Exception e) {
                // TODO :: Add the report of an invalid field on a notification
                telefono = 999101999;
                System.out.println("Error // Phone:" + e.toString());
            };

            nombre = gClient.getNombre();
            apellido = gClient.getApellido();
            try {
                if (gClient.getCorreo().matches(regexEmail)) {
                    correo = gClient.getCorreo();
                }

            } catch (Exception e) {
            }
            direccion = gClient.getDireccion();

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return false;
        }

        return true;

    }

    public void limpiarCampos() {
        jTCedula.setText("");
        jTnombre.setText("");
        jTapellido.setText("");
        jTcorreo.setText("");
        jTtelefono.setText("");
        jTdireccion.setText("");
    }

    public int validarCampos() {
        int rtdo = 1;

        if (jTCedula.getText().equals("") || jTCedula.getText().equals("***")) {

            rtdo = 0;
            mostrarCamposVacios(jTCedula);

        }
        if (jTcorreo.getText().equals("") || jTcorreo.getText().equals("***")) {
            rtdo = 0;

            mostrarCamposVacios(jTcorreo);
        }
        if (jTnombre.getText().equals("") || jTnombre.getText().equals("***")) {
            rtdo = 0;

            mostrarCamposVacios(jTnombre);
        }
        if (jTtelefono.getText().equals("") || jTtelefono.getText().equals("***")) {
            rtdo = 0;

            mostrarCamposVacios(jTtelefono);
        }
        if (jTapellido.getText().equals("") || jTapellido.getText().equals("***")) {
            rtdo = 0;

            mostrarCamposVacios(jTapellido);
        }
        if (jTdireccion.getText().equals("") || jTdireccion.getText().equals("***")) {
            rtdo = 0;

            mostrarCamposVacios(jTdireccion);
        }

        return rtdo;
    }

    public void mostrarCamposVacios(JTextField jt) {
        jt.setForeground(Color.black);
        jt.setText("***");
    }

    public void validaNumero(char c, KeyEvent evt) {
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }

    public void setearFormato(JTextField t) {
        t.setForeground(new Color(153, 153, 153));
        t.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBguardar = new javax.swing.JButton();
        jBcancelar = new javax.swing.JButton();
        jTdireccion = new javax.swing.JTextField();
        jTnombre = new javax.swing.JTextField();
        jTCedula = new javax.swing.JTextField();
        jTcorreo = new javax.swing.JTextField();
        jTtelefono = new javax.swing.JTextField();
        jTapellido = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLcedula = new javax.swing.JLabel();
        jLnombre = new javax.swing.JLabel();
        jLapellido = new javax.swing.JLabel();
        jLdirección = new javax.swing.JLabel();
        jLtelefono = new javax.swing.JLabel();
        jLtcorreo = new javax.swing.JLabel();
        jLfondo = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar-SinSele.png"))); // NOI18N
        jBguardar.setBorder(null);
        jBguardar.setBorderPainted(false);
        jBguardar.setContentAreaFilled(false);
        jBguardar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarSele.png"))); // NOI18N
        jBguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBguardarActionPerformed(evt);
            }
        });
        add(jBguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 371, -1, -1));

        jBcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar-sinSeleccion.png"))); // NOI18N
        jBcancelar.setContentAreaFilled(false);
        jBcancelar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar-seleccionado.png"))); // NOI18N
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });
        add(jBcancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 371, 130, 50));

        jTdireccion.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jTdireccion.setForeground(new java.awt.Color(153, 153, 153));
        jTdireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTdireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTdireccionFocusGained(evt);
            }
        });
        add(jTdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 310, 276, 25));

        jTnombre.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jTnombre.setForeground(new java.awt.Color(153, 153, 153));
        jTnombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTnombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTnombreFocusGained(evt);
            }
        });
        add(jTnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 246, 276, 25));

        jTCedula.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jTCedula.setForeground(new java.awt.Color(153, 153, 153));
        jTCedula.setToolTipText("");
        jTCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTCedulaFocusGained(evt);
            }
        });
        jTCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTCedulaActionPerformed(evt);
            }
        });
        jTCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTCedulaKeyTyped(evt);
            }
        });
        add(jTCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 188, 276, 25));

        jTcorreo.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jTcorreo.setForeground(new java.awt.Color(153, 153, 153));
        jTcorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTcorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTcorreoFocusGained(evt);
            }
        });
        jTcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTcorreoActionPerformed(evt);
            }
        });
        add(jTcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 188, 276, 25));

        jTtelefono.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jTtelefono.setForeground(new java.awt.Color(153, 153, 153));
        jTtelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTtelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTtelefonoFocusGained(evt);
            }
        });
        jTtelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTtelefonoActionPerformed(evt);
            }
        });
        add(jTtelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 246, 276, 25));

        jTapellido.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jTapellido.setForeground(new java.awt.Color(153, 153, 153));
        jTapellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTapellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTapellidoFocusGained(evt);
            }
        });
        add(jTapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 310, 276, 25));

        jLabel1.setFont(new java.awt.Font("Decker", 0, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(112, 112, 112));
        jLabel1.setText("Cliente");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 111, 85, 21));

        jLcedula.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jLcedula.setForeground(new java.awt.Color(191, 191, 191));
        jLcedula.setText("Cédula");
        add(jLcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 162, 180, 20));

        jLnombre.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jLnombre.setForeground(new java.awt.Color(191, 191, 191));
        jLnombre.setText("Nombre");
        add(jLnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 220, 180, 20));

        jLapellido.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jLapellido.setForeground(new java.awt.Color(191, 191, 191));
        jLapellido.setText("Apellido");
        add(jLapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 284, 180, 20));

        jLdirección.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jLdirección.setForeground(new java.awt.Color(191, 191, 191));
        jLdirección.setText("Dirección");
        add(jLdirección, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 284, 180, 20));

        jLtelefono.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jLtelefono.setForeground(new java.awt.Color(191, 191, 191));
        jLtelefono.setText("Teléfono");
        add(jLtelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 220, 180, 20));

        jLtcorreo.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        jLtcorreo.setForeground(new java.awt.Color(191, 191, 191));
        jLtcorreo.setText("Correo");
        add(jLtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 162, 180, 20));

        jLfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ContornoHabitacion.png"))); // NOI18N
        jLfondo.setPreferredSize(new java.awt.Dimension(739, 429));
        add(jLfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 84, 726, 354));
    }// </editor-fold>//GEN-END:initComponents

    private void jBguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBguardarActionPerformed

        if (validarCampos() == 1) {
            if (!idFound(getData().getID())) {
                //saveData();
                ControladorCliente.addClient(getData());
                JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente");
                limpiarCampos();
            } else {

                try {
                    ControladorCliente.modificarCliente(getData());

                } catch (Error error) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un error "
                            + "modificando");
                }
                JOptionPane.showMessageDialog(null, "Se ha modificado con "
                        + "éxito");
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese Todos los "
                    + "Campos Requeridos");
        }
        //saveData(); // Se ejecuta, se envia la data a la BD 
        //jBcancelarActionPerformed(evt);

    }//GEN-LAST:event_jBguardarActionPerformed


    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jTCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTCedulaActionPerformed

    private void jTtelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTtelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTtelefonoActionPerformed

    private void jTcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTcorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTcorreoActionPerformed

    private void jTCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTCedulaKeyTyped

        validaNumero(evt.getKeyChar(), evt);

    }//GEN-LAST:event_jTCedulaKeyTyped

    private void jTCedulaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTCedulaFocusGained

        if (jTCedula.getText().equalsIgnoreCase("***")) {
            setearFormato(jTCedula);
        }
    }//GEN-LAST:event_jTCedulaFocusGained

    private void jTcorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTcorreoFocusGained

        if (jTcorreo.getText().equalsIgnoreCase("***")) {
            setearFormato(jTcorreo);
        }

    }//GEN-LAST:event_jTcorreoFocusGained

    private void jTnombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnombreFocusGained

        if (jTnombre.getText().equalsIgnoreCase("***")) {
            setearFormato(jTnombre);
        }

    }//GEN-LAST:event_jTnombreFocusGained

    private void jTtelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTtelefonoFocusGained

        if (jTtelefono.getText().equalsIgnoreCase("***")) {
            setearFormato(jTtelefono);
        }

    }//GEN-LAST:event_jTtelefonoFocusGained

    private void jTapellidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTapellidoFocusGained

        if (jTapellido.getText().equalsIgnoreCase("***")) {
            setearFormato(jTapellido);
        }
    }//GEN-LAST:event_jTapellidoFocusGained

    private void jTdireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTdireccionFocusGained

        if (jTdireccion.getText().equalsIgnoreCase("***")) {
            setearFormato(jTdireccion);
        }

    }//GEN-LAST:event_jTdireccionFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBguardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLapellido;
    private javax.swing.JLabel jLcedula;
    private javax.swing.JLabel jLdirección;
    private javax.swing.JLabel jLfondo;
    private javax.swing.JLabel jLnombre;
    private javax.swing.JLabel jLtcorreo;
    private javax.swing.JLabel jLtelefono;
    private javax.swing.JTextField jTCedula;
    private javax.swing.JTextField jTapellido;
    private javax.swing.JTextField jTcorreo;
    private javax.swing.JTextField jTdireccion;
    private javax.swing.JTextField jTnombre;
    private javax.swing.JTextField jTtelefono;
    // End of variables declaration//GEN-END:variables
}
