/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Jpanel;

import Controladores.ControllerReserva;
import Modelo.Habitacion;
import Modelo.Reserva;
import Servicios.Fecha;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nicol
 */
public class RealizarReservaGUI extends javax.swing.JPanel {

    /**
     * Creates new form RealizarReservaGUI
     */
    
    int numeroReserva;
    String validaOperacion = "guardar";
    public RealizarReservaGUI() {
        initComponents();
        establecerCalendarios();
        Timestamp fi = Fecha.formatearFechaIngreso(jdFechaIngreso.getDate());
        Timestamp fs = Fecha.formatearFechaSalida(jdFechaSalida.getDate()); 
        cargarHabitaciones(ControllerReserva.cargarListaHabitaciones(fi,fs));
    }
    
    
    public void establecerCalendarios(){
        jTidHabitacion.setEditable(false);
        jdFechaIngreso.setMinSelectableDate(Fecha.crearFechaTimestamp());
        jdFechaSalida.setMinSelectableDate(Fecha.dateTomorrow(Fecha.crearFechaTimestamp()));
        jdFechaIngreso.setDate(Fecha.crearFechaTimestamp());
        jdFechaSalida.setDate(Fecha.dateTomorrow(Fecha.crearFechaTimestamp()));   
    }
    
    public void setearCampos() {
        jTidCliente.setText("Cliente");
        jTidHabitacion.setText("Número de habitación");
        jTnumPersonas.setText("Cantidad de personas");
        jdFechaIngreso.setDate(Fecha.crearFechaTimestamp());
        jdFechaSalida.setDate(Fecha.dateTomorrow(Fecha.crearFechaTimestamp())); 
    }
    
    
    private Reserva infoRerserva() {
        Reserva reserva = new Reserva();
        int numReserva = ControllerReserva.getNumeroReserva()+1;
        int idHab = Integer.parseInt(jTidHabitacion.getText());
        int idCli = Integer.parseInt(jTidCliente.getText());
        int numPer = Integer.parseInt(jTnumPersonas.getText());
        Timestamp fi = Fecha.formatearFechaIngreso(jdFechaIngreso.getDate());
        Timestamp fs = Fecha.formatearFechaSalida(jdFechaSalida.getDate());
        Timestamp fecha = Fecha.crearFechaTimestamp();
        System.err.println("AL Registrar una reserva"+fi +"        "+fs+"            "+fecha);
        reserva.setNumero_reserva(numReserva);
        reserva.setNum_Habitacion(idHab);
        reserva.setNumCliente(idCli);
        reserva.setNum_Empleado(ControllerReserva.getNumEmpleado());
        reserva.setFecha_reserva(fecha);
        reserva.setFecha_ingreso(fi);
        reserva.setFecha_salida(fs);
        reserva.setNum_Personas(numPer);
        return reserva;
        
    }
    
    public int validarCampos() {
        int rtdo = 1;

        if (jTidCliente.getText().equals("")
                ||jTidCliente.getText().equalsIgnoreCase("Cliente")){
            
            rtdo = 0;
            JOptionPane.showMessageDialog(this,"Hay Campos Vacios");

        }else
        if (jTidHabitacion.getText().equals("") 
            || jTidHabitacion.getText().equalsIgnoreCase("Número de habitación"))
        {
            rtdo = 0;
            JOptionPane.showMessageDialog(this,"Seleccione una Habitación");
        }else
        if(jTnumPersonas.getText().equals("") || 
                jTnumPersonas.getText().equalsIgnoreCase("Cantidad de personas"))
        {
            rtdo = 0;
            JOptionPane.showMessageDialog(this,"Hay Campos Vacios");
            
        }else
        if(jdFechaIngreso.getDate() == null || jdFechaSalida.getDate() == null){
            JOptionPane.showMessageDialog(this,"Hay campos de fecha vacios");
            rtdo = 0;
            
        }else{
        if(jdFechaIngreso.getDate().getTime() >= jdFechaSalida.getDate().getTime()){
            JOptionPane.showMessageDialog(this,"No coinciden las fechas");
            rtdo = 0;
        }
        }
        
        return rtdo;
    }
    
    public void mostrarCamposVacios(JTextField jt) {
        jt.setForeground(Color.blue);
        jt.setText("****");
    }
    
    
    
    public void reestablecerBorderPanel()
    {
        for(int i = 0; i < jPcontenido.getComponentCount();i++){
            jPhabitacionCheckIn jp;
            jp = (jPhabitacionCheckIn)jPcontenido.getComponent(i);
            if(jp.getjButton1().getBorder() != null){
                jp.getjButton1().setBorder(null);
                i = jPcontenido.getComponentCount();
            }
            
        }
    }
    
    
    public void llenarFormulario(Reserva reserva){
        cambiarCombobobox(reserva.getNum_Habitacion());
        validaOperacion = "actualizar";
        jdFechaIngreso.setDate(reserva.getFecha_ingreso());
        jdFechaSalida.setDate(reserva.getFecha_salida());
        jTidCliente.setText(""+reserva.getNumCliente());
        jTidHabitacion.setText(""+reserva.getNum_Habitacion());
        System.out.println("HHH "+reserva.getNum_Habitacion());
        jTnumPersonas.setText(""+reserva.getNum_Personas());
        numeroReserva = reserva.getNumero_reserva();
        
    }
    
    public void cambiarCombobobox(int numHab){
        /*ESTABLECE EL COMBOBOBOX EN LA CATEGORIA DE LA HABITACION SELECCIONADA 
          PARA ACTUALIZAR*/
        if(numHab > 100 && numHab < 200 ){
            jComboBox1.setSelectedIndex(0);
        }else
        if(numHab > 200 && numHab < 300 ){
            jComboBox1.setSelectedIndex(1);
        }else
        if(numHab > 300 && numHab < 400){
            jComboBox1.setSelectedIndex(2);
        }else
        if(numHab > 400 && numHab < 500 ){
            jComboBox1.setSelectedIndex(3);
        }
    }
    
    
    public void cargarHabitaciones(ArrayList<Habitacion> lista) {
        jPcontenido.removeAll();
        for(int i = 0; i < lista.size(); i++) {
            int num = lista.get(i).getId_habitacion();
            int prec = lista.get(i).getPrecio_hab();
            int capac = lista.get(i).getCantidadPersonas();
            String Tipo = lista.get(i).getTipo_habitacion();
            if(jComboBox1.getSelectedItem().equals(Tipo)){
                jPhabitacionCheckIn jp;
                jp = new jPhabitacionCheckIn(num,Tipo,prec,capac);
                jp.setjThabitacion(jTidHabitacion);
                jPcontenido.add(jp); 
            }
        }
        jPcontenido.revalidate();
        jPcontenido.repaint();
    }
    
    
    public void validaNumero(char c, KeyEvent evt) {
        if (!Character.isDigit(c)) {
            evt.consume();
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

        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jdFechaSalida = new com.toedter.calendar.JDateChooser();
        jdFechaIngreso = new com.toedter.calendar.JDateChooser();
        jBcancelar = new javax.swing.JButton();
        jBguardar = new javax.swing.JButton();
        jTidCliente = new javax.swing.JTextField();
        jTnumPersonas = new javax.swing.JTextField();
        jTidHabitacion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPcontenido = new javax.swing.JPanel();
        jBbuscar = new javax.swing.JButton();
        jLformulario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Decker", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(191, 191, 191));
        jLabel4.setText("Fecha Salida:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 345, 110, 23));

        jLabel3.setFont(new java.awt.Font("Decker", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(191, 191, 191));
        jLabel3.setText("Fecha ingreso:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 300, 110, 23));

        jdFechaSalida.setBackground(new java.awt.Color(255, 255, 255));
        jdFechaSalida.setForeground(new java.awt.Color(191, 191, 191));
        jdFechaSalida.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jdFechaSalidaInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jdFechaSalida.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdFechaSalidaPropertyChange(evt);
            }
        });
        add(jdFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 345, 160, 25));
        jdFechaSalida.getAccessibleContext().setAccessibleName("");

        jdFechaIngreso.setBackground(new java.awt.Color(255, 255, 255));
        jdFechaIngreso.setForeground(new java.awt.Color(191, 191, 191));
        jdFechaIngreso.setFont(new java.awt.Font("Decker", 0, 11)); // NOI18N
        jdFechaIngreso.setMinSelectableDate(new java.util.Date(-62135747907000L));
        jdFechaIngreso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdFechaIngresoPropertyChange(evt);
            }
        });
        add(jdFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 301, 160, 25));

        jBcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar-sinSeleccion.png"))); // NOI18N
        jBcancelar.setBorder(null);
        jBcancelar.setBorderPainted(false);
        jBcancelar.setContentAreaFilled(false);
        jBcancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBcancelar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar-seleccionado.png"))); // NOI18N
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });
        add(jBcancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 395, 125, 40));

        jBguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar-SinSele.png"))); // NOI18N
        jBguardar.setBorder(null);
        jBguardar.setContentAreaFilled(false);
        jBguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBguardar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarSele.png"))); // NOI18N
        jBguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBguardarActionPerformed(evt);
            }
        });
        add(jBguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(706, 395, 125, 40));

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
        jTidCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTidClienteActionPerformed(evt);
            }
        });
        jTidCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTidClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTidClienteKeyTyped(evt);
            }
        });
        add(jTidCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 152, 276, 25));

        jTnumPersonas.setFont(new java.awt.Font("Decker", 0, 16)); // NOI18N
        jTnumPersonas.setForeground(new java.awt.Color(191, 191, 191));
        jTnumPersonas.setText("Cantidad de personas");
        jTnumPersonas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(191, 191, 191)));
        jTnumPersonas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTnumPersonasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTnumPersonasFocusLost(evt);
            }
        });
        jTnumPersonas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTnumPersonasActionPerformed(evt);
            }
        });
        jTnumPersonas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTnumPersonasKeyTyped(evt);
            }
        });
        add(jTnumPersonas, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 252, 276, 25));

        jTidHabitacion.setFont(new java.awt.Font("Decker", 0, 16)); // NOI18N
        jTidHabitacion.setForeground(new java.awt.Color(191, 191, 191));
        jTidHabitacion.setText("Número de habitación");
        jTidHabitacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(191, 191, 191)));
        jTidHabitacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTidHabitacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTidHabitacionFocusLost(evt);
            }
        });
        jTidHabitacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTidHabitacionMouseClicked(evt);
            }
        });
        jTidHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTidHabitacionActionPerformed(evt);
            }
        });
        jTidHabitacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTidHabitacionKeyTyped(evt);
            }
        });
        add(jTidHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 203, 276, 25));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setOpaque(false);

        jPcontenido.setBackground(new java.awt.Color(255, 255, 255));
        jPcontenido.setLayout(new java.awt.GridLayout(0, 1, 0, 1));
        jScrollPane1.setViewportView(jPcontenido);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 119, 300, 360));

        jBbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar-azul.png"))); // NOI18N
        jBbuscar.setBorder(null);
        jBbuscar.setContentAreaFilled(false);
        jBbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(jBbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(837, 153, 24, 24));

        jLformulario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MarcoReserva.png"))); // NOI18N
        add(jLformulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 85, 575, 397));

        jLabel1.setBackground(new java.awt.Color(112, 112, 112));
        jLabel1.setFont(new java.awt.Font("Decker", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(112, 112, 112));
        jLabel1.setText("Número      Tipo                  Precio   Capacidad");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 90, 250, -1));

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
    }// </editor-fold>//GEN-END:initComponents

    private void jTidClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTidClienteKeyTyped
        validaNumero(evt.getKeyChar(), evt);
    }//GEN-LAST:event_jTidClienteKeyTyped

    private void jTnumPersonasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnumPersonasKeyTyped
        validaNumero(evt.getKeyChar(), evt);
    }//GEN-LAST:event_jTnumPersonasKeyTyped

    private void jTidClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTidClienteFocusGained
        if(jTidCliente.getText().equalsIgnoreCase("Cliente")){
            jTidCliente.setText(""); 
        }
    }//GEN-LAST:event_jTidClienteFocusGained

    private void jTnumPersonasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnumPersonasFocusGained
        if(jTnumPersonas.getText().equalsIgnoreCase("Cantidad de personas")){
            jTnumPersonas.setText("");
        }
    }//GEN-LAST:event_jTnumPersonasFocusGained

    private void jTidClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTidClienteFocusLost
       
        if(jTidCliente.getText().equals("")){
            jTidCliente.setText("Cliente");
        }
    }//GEN-LAST:event_jTidClienteFocusLost

    private void jTnumPersonasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnumPersonasFocusLost
        if(jTnumPersonas.getText().equals("")){
            jTnumPersonas.setText("Cantidad de personas");
        }
    }//GEN-LAST:event_jTnumPersonasFocusLost

    private void jBguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBguardarActionPerformed
        ControllerReserva.verificarCliente(jTidCliente.getText());
        if(validarCampos()==1){
            if(validaOperacion.equals("guardar")){
                if(ControllerReserva.isVerCliente() == true){
                    ControllerReserva.extraerId();
                    ControllerReserva.registrarReserva(infoRerserva());
                    setearCampos();
                    reestablecerBorderPanel();
                }else{
                    JOptionPane.showMessageDialog(null,"No existe el cliente "
                        + "en la base de datos");
                }  
            }else 
            if(validaOperacion.equals("actualizar")){
                //ControllerReserva.actualizarReserva();
            }
        }
    }//GEN-LAST:event_jBguardarActionPerformed

    private void jdFechaSalidaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdFechaSalidaPropertyChange
        if(jdFechaIngreso.getDate() != null && jdFechaSalida.getDate() != null){
            Timestamp fi = Fecha.formatearFechaIngreso(jdFechaIngreso.getDate());
            Timestamp fs = Fecha.formatearFechaSalida(jdFechaSalida.getDate());
            cargarHabitaciones(ControllerReserva.cargarListaHabitaciones(fi,fs));
            jTidHabitacion.setText("Número de habitación");
        }
    }//GEN-LAST:event_jdFechaSalidaPropertyChange

    private void jdFechaIngresoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdFechaIngresoPropertyChange
        if(jdFechaIngreso.getDate() != null && jdFechaSalida.getDate() != null){
            Timestamp fi = Fecha.formatearFechaIngreso(jdFechaIngreso.getDate());
            Timestamp fs = Fecha.formatearFechaSalida(jdFechaSalida.getDate());
            cargarHabitaciones(ControllerReserva.cargarListaHabitaciones(fi,fs));
            jTidHabitacion.setText("Número de habitación");
            Date f = Fecha.dateTomorrow(jdFechaIngreso.getDate());
            jdFechaSalida.setMinSelectableDate(f);
        } 
    }//GEN-LAST:event_jdFechaIngresoPropertyChange

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        setearCampos();
        reestablecerBorderPanel();
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jTidHabitacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTidHabitacionKeyTyped
        validaNumero(evt.getKeyChar(), evt);
    }//GEN-LAST:event_jTidHabitacionKeyTyped

    private void jTidHabitacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTidHabitacionMouseClicked

        if(jTidHabitacion.getText().equalsIgnoreCase("Número de habitación")){
            JOptionPane.showMessageDialog
            (null,"Seleccione una Habitacion de la lista");
        }
    }//GEN-LAST:event_jTidHabitacionMouseClicked

    private void jTidHabitacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTidHabitacionFocusLost
        if(jTidHabitacion.getText().equals("")){

            jTidHabitacion.setText("Número de Habitación");
        }
    }//GEN-LAST:event_jTidHabitacionFocusLost

    private void jdFechaSalidaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jdFechaSalidaInputMethodTextChanged
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "kxkxxkxkxkxkxkxk");
    }//GEN-LAST:event_jdFechaSalidaInputMethodTextChanged

    private void jTnumPersonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTnumPersonasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTnumPersonasActionPerformed

    private void jTidHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTidHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTidHabitacionActionPerformed

    private void jTidHabitacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTidHabitacionFocusGained
        /*if(jTidHabitacion.getText().equalsIgnoreCase("Número de habitación")){
            jTidHabitacion.setText("");
            JOptionPane.showMessageDialog(null,"Seleccione una Habitacion de la lista");
        }*/

    }//GEN-LAST:event_jTidHabitacionFocusGained

    private void jTidClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTidClienteKeyPressed

    }//GEN-LAST:event_jTidClienteKeyPressed

    private void jTidClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTidClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTidClienteActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        Timestamp fi = Fecha.formatearFechaIngreso(jdFechaIngreso.getDate());
        Timestamp fs = Fecha.formatearFechaSalida(jdFechaSalida.getDate());
        cargarHabitaciones(ControllerReserva.cargarListaHabitaciones(fi,fs));
        jTidHabitacion.setText("Número de habitación");
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbuscar;
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBguardar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLformulario;
    private javax.swing.JPanel jPcontenido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTidCliente;
    private javax.swing.JTextField jTidHabitacion;
    private javax.swing.JTextField jTnumPersonas;
    private com.toedter.calendar.JDateChooser jdFechaIngreso;
    private com.toedter.calendar.JDateChooser jdFechaSalida;
    // End of variables declaration//GEN-END:variables
}
