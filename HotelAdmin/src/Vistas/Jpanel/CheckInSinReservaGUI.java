/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Jpanel;

import Controladores.ControladorCheckout;
import Controladores.ControladorHospedaje;
import Controladores.ControllerHabitacion;
import Controladores.ControllerReserva;
import Modelo.Checkout;
import Modelo.Habitacion;
import Modelo.Hospedaje;
import Servicios.Fecha;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author nicol
 */
public class CheckInSinReservaGUI extends javax.swing.JPanel {

    /**
     * Creates new form CheckInSinReserva
     */
    String validaOperacion;
    int numEmpleado;

    public CheckInSinReservaGUI(int nummEmpleado) {
        initComponents();
        this.numEmpleado = nummEmpleado;
        establecerInterfaz();
        Timestamp fi = Fecha.formatearFechaIngreso(jdFechaIngreso.getDate());
        Timestamp fs = Fecha.formatearFechaSalida(jdFechaSalida.getDate());
        cargarHabitaciones(ControllerReserva.
                loadListRooms(fi, fs, "save", 0));
    }

    public void establecerInterfaz() {
        Timestamp fechaActual = Fecha.crearFechaTimestamp();
        jdFechaIngreso.setMinSelectableDate(fechaActual);
        jdFechaIngreso.setMaxSelectableDate(fechaActual);
        jdFechaSalida.setMinSelectableDate(fechaActual);
        jdFechaIngreso.setDate(Fecha.crearFechaTimestamp());
        jdFechaSalida.setDate(Fecha.dateTomorrow(Fecha.crearFechaTimestamp()));
    }

    public void cargarHabitaciones(ArrayList<Habitacion> lista) {
        jPcontenido.removeAll();
        for (int i = 0; i < lista.size(); i++) {
            int num = lista.get(i).getId_habitacion();
            int prec = lista.get(i).getPrecio_hab();
            int capac = lista.get(i).getCantidadPersonas();
            String Tipo = lista.get(i).getTipo_habitacion();
            if (jComboBox1.getSelectedItem().equals(Tipo)) {
                jPhabitacionCheckIn jp;
                jp = new jPhabitacionCheckIn(num, Tipo, prec, capac);
                jp.setjThabitacion(jTidHabitacion);
                jPcontenido.add(jp);
            }
        }
        jPcontenido.revalidate();
        jPcontenido.repaint();
    }

    public void setearCampos() {
        jTidCliente.setText("Cliente");
        jTidHabitacion.setText("");
        jTcantidadPersonas.setText("Cantidad de personas");
        jdFechaIngreso.setDate(Fecha.crearFechaTimestamp());
        jdFechaSalida.setDate(Fecha.dateTomorrow(Fecha.crearFechaTimestamp()));
    }

    public void reestablecerBorderPanel() {
        //Despinta el borde del panel de la habitacion que fue deseleccionada
        for (int i = 0; i < jPcontenido.getComponentCount(); i++) {
            jPhabitacionCheckIn jp;
            jp = (jPhabitacionCheckIn) jPcontenido.getComponent(i);
            if (jp.getjButton1().getBorder() != null) {
                jp.getjButton1().setBorder(null);
                i = jPcontenido.getComponentCount();
            }
        }
    }

    public void validaNumero(char c, KeyEvent evt) {
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }

    private void hacerHospedaje() {
        Hospedaje h = new Hospedaje();
        ControladorHospedaje ch = new ControladorHospedaje();
        
        Timestamp fechaIngreso,fechaSalida;
        fechaIngreso = Fecha.formatearFechaIngreso(jdFechaIngreso.getDate());
        fechaSalida = Fecha.formatearFechaSalida(jdFechaSalida.getDate());
        
        h.setIdCliente(ch.extraerId() + 1);
        h.setIdHabitacion(Integer.parseInt(jTidHabitacion.getText()));
        h.setIdCliente(Integer.parseInt(jTidCliente.getText()));
        h.setNumeroPesonas(Integer.parseInt(jTcantidadPersonas.getText()));
        h.setFechaIngreso(fechaIngreso);
        h.setFechaSalida(fechaSalida);
        h.setEstado(true);
        h.setIdEmpleado(numEmpleado);
        h.setId_reserva(0);

        int i = ch.grabarHospedaje(h);
        crearCheckout(ch.extraerId());//---------------
        if (i == 1) {
            JOptionPane.showMessageDialog(null,
                    "Hospedaje registrado con exito.");
            setearCampos();
        } else {
            JOptionPane.showMessageDialog(null,
                    "No existe el cliente en la base de datos.");
            setearCampos();
        }
    }

    private void crearCheckout(int idhospedaje) {
        Checkout c = new Checkout();
        ControladorCheckout co = new ControladorCheckout();
        ControllerHabitacion ch = new ControllerHabitacion();

        int valorTotal = numDias()
                * ch.getPrecioHabitacion(Integer.parseInt(jTidHabitacion.getText()));
        c.setIdHospedaje(idhospedaje);
        c.setValorTotal(valorTotal);
        co.grabarChekout(c);
    }

    private int numDias() {
        int dias = 0;
        try {
            String fi = jdFechaIngreso.getDateFormatString();
            String fs = jdFechaSalida.getDateFormatString();
            fi = fi.substring(0, fi.indexOf(" "));
            fs = fs.substring(0, fs.indexOf(" "));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicial = dateFormat.parse(fi);
            Date fechaFinal = dateFormat.parse(fs);
            dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrio un error al realizar el Hospedaje");
        } finally {
            return dias;
        }
    }

    public int validarCampos() {
        int rtdo = 1;
        if (jTidCliente.getText().equals("")
                || jTidCliente.getText().equalsIgnoreCase("Cliente")) {

            rtdo = 0;
            JOptionPane.showMessageDialog(this, "Hay Campos Vacios");

        } else if (jTidHabitacion.getText().equals("")
                || jTidHabitacion.getText().equalsIgnoreCase("Número de habitación")) {
            rtdo = 0;
            JOptionPane.showMessageDialog(this, "Seleccione una Habitación");
        } else if (jTcantidadPersonas.getText().equals("")
                || jTcantidadPersonas.getText().equalsIgnoreCase("Cantidad de personas")) {
            rtdo = 0;
            JOptionPane.showMessageDialog(this, "Hay Campos Vacios");

        }
        return rtdo;
    }

    public void saveORupdate() {
        if (validarCampos() == 1) {
            hacerHospedaje();
            setearCampos();
            reestablecerBorderPanel();
            //numeroReserva += 1;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTidCliente = new javax.swing.JTextField();
        jTcantidadPersonas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPcontenido = new javax.swing.JPanel();
        jBbuscar = new javax.swing.JButton();
        jBcancelar = new javax.swing.JButton();
        jBcheckIn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jdFechaIngreso = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jdFechaSalida = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTidHabitacion = new javax.swing.JTextField();
        jLformulario = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTidCliente.setFont(new java.awt.Font("Decker", 0, 16)); // NOI18N
        jTidCliente.setForeground(new java.awt.Color(191, 191, 191));
        jTidCliente.setText("Cliente");
        jTidCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(191, 191, 191)));
        jTidCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTidClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTidClienteFocusLost(evt);
            }
        });
        jTidCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTidClienteKeyTyped(evt);
            }
        });
        add(jTidCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 265, 276, 25));

        jTcantidadPersonas.setFont(new java.awt.Font("Decker", 0, 16)); // NOI18N
        jTcantidadPersonas.setForeground(new java.awt.Color(191, 191, 191));
        jTcantidadPersonas.setText("Cantidad de personas");
        jTcantidadPersonas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(191, 191, 191)));
        jTcantidadPersonas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTcantidadPersonasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTcantidadPersonasFocusLost(evt);
            }
        });
        jTcantidadPersonas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTcantidadPersonasKeyTyped(evt);
            }
        });
        add(jTcantidadPersonas, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 310, 276, 25));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setOpaque(false);

        jPcontenido.setBackground(new java.awt.Color(255, 255, 255));
        jPcontenido.setLayout(new java.awt.GridLayout(0, 1, 0, 1));
        jScrollPane1.setViewportView(jPcontenido);
        jPcontenido.getAccessibleContext().setAccessibleDescription("");

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 119, 300, 360));

        jBbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar-azul.png"))); // NOI18N
        jBbuscar.setBorder(null);
        jBbuscar.setContentAreaFilled(false);
        jBbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(jBbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(837, 265, 24, 24));

        jBcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar-sinSeleccion.png"))); // NOI18N
        jBcancelar.setBorder(null);
        jBcancelar.setBorderPainted(false);
        jBcancelar.setContentAreaFilled(false);
        jBcancelar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar-seleccionado.png"))); // NOI18N
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });
        add(jBcancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 405, 125, 40));

        jBcheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Check-inBoton.png"))); // NOI18N
        jBcheckIn.setBorder(null);
        jBcheckIn.setContentAreaFilled(false);
        jBcheckIn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Check-in-seleccionado.png"))); // NOI18N
        jBcheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcheckInActionPerformed(evt);
            }
        });
        add(jBcheckIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(706, 405, 125, 40));

        jLabel1.setBackground(new java.awt.Color(112, 112, 112));
        jLabel1.setFont(new java.awt.Font("Decker", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(112, 112, 112));
        jLabel1.setText("Número    Tipo     Precio   Capacidad");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 89, -1, -1));

        jLabel3.setFont(new java.awt.Font("Decker", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(191, 191, 191));
        jLabel3.setText("Fecha ingreso:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 175, 110, 23));

        jdFechaIngreso.setBackground(new java.awt.Color(255, 255, 255));
        jdFechaIngreso.setForeground(new java.awt.Color(191, 191, 191));
        jdFechaIngreso.setFont(new java.awt.Font("Decker", 0, 11)); // NOI18N
        jdFechaIngreso.setMinSelectableDate(new java.util.Date(-62135747907000L));
        jdFechaIngreso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdFechaIngresoPropertyChange(evt);
            }
        });
        add(jdFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 175, 160, 25));

        jLabel4.setFont(new java.awt.Font("Decker", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(191, 191, 191));
        jLabel4.setText("Fecha Salida:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 220, 110, 23));

        jdFechaSalida.setBackground(new java.awt.Color(255, 255, 255));
        jdFechaSalida.setForeground(new java.awt.Color(191, 191, 191));
        jdFechaSalida.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdFechaSalidaPropertyChange(evt);
            }
        });
        add(jdFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 220, 160, 25));

        jComboBox1.setFont(new java.awt.Font("Decker", 0, 13)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(204, 204, 204));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SIMPLE", "DOBLE", "MATRIMONIAL", "SUITES" }));
        jComboBox1.setBorder(null);
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 37, 122, 20));

        jLabel2.setFont(new java.awt.Font("Decker", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(165, 165, 165));
        jLabel2.setText("Habitaciones :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 37, -1, -1));

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(191, 191, 191));
        jLabel5.setText("NUMERO DE HABITACIÓN");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 355, 180, 25));

        jTidHabitacion.setEditable(false);
        jTidHabitacion.setBackground(new java.awt.Color(204, 204, 204));
        jTidHabitacion.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        add(jTidHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 355, 80, 25));

        jLformulario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MarcoCheckin.png"))); // NOI18N
        add(jLformulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 85, 575, 397));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jdFechaIngresoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdFechaIngresoPropertyChange
    }//GEN-LAST:event_jdFechaIngresoPropertyChange

    private void jdFechaSalidaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdFechaSalidaPropertyChange
        if (jdFechaIngreso.getDate() != null && jdFechaSalida.getDate() != null) {

            Timestamp fi = Fecha.formatearFechaIngreso(jdFechaIngreso.getDate());
            Timestamp fs = Fecha.formatearFechaSalida(jdFechaSalida.getDate());
            cargarHabitaciones(ControllerReserva.
                    loadListRooms(fi, fs, "save", 0));
            jTidHabitacion.setText("");
        }
    }//GEN-LAST:event_jdFechaSalidaPropertyChange

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        Timestamp fi = Fecha.formatearFechaIngreso(jdFechaIngreso.getDate());
        Timestamp fs = Fecha.formatearFechaSalida(jdFechaSalida.getDate());
        cargarHabitaciones(ControllerReserva.
                loadListRooms(fi, fs, "save", 0));
        jTidHabitacion.setText("");

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTidClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTidClienteFocusGained
        jTidCliente.setText("");
    }//GEN-LAST:event_jTidClienteFocusGained

    private void jTcantidadPersonasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTcantidadPersonasFocusGained
        jTcantidadPersonas.setText("");
    }//GEN-LAST:event_jTcantidadPersonasFocusGained

    private void jTcantidadPersonasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTcantidadPersonasFocusLost
        if (jTcantidadPersonas.getText().equals("")) {
            jTcantidadPersonas.setText("Cantidad de personas");
        }
    }//GEN-LAST:event_jTcantidadPersonasFocusLost

    private void jTidClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTidClienteFocusLost
        if (jTidCliente.getText().equals("")) {
            jTidCliente.setText("Cliente");
        }
    }//GEN-LAST:event_jTidClienteFocusLost

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        setearCampos();
        reestablecerBorderPanel();
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jTidClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTidClienteKeyTyped
        validaNumero(evt.getKeyChar(), evt);
    }//GEN-LAST:event_jTidClienteKeyTyped

    private void jTcantidadPersonasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTcantidadPersonasKeyTyped
        validaNumero(evt.getKeyChar(), evt);
    }//GEN-LAST:event_jTcantidadPersonasKeyTyped

    private void jBcheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcheckInActionPerformed
        saveORupdate();
    }//GEN-LAST:event_jBcheckInActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbuscar;
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBcheckIn;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLformulario;
    private javax.swing.JPanel jPcontenido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTcantidadPersonas;
    private javax.swing.JTextField jTidCliente;
    private javax.swing.JTextField jTidHabitacion;
    private com.toedter.calendar.JDateChooser jdFechaIngreso;
    private com.toedter.calendar.JDateChooser jdFechaSalida;
    // End of variables declaration//GEN-END:variables
}
