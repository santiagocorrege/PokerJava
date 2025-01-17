/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import dominio.Jugador;
import dominio.Mesa;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import uiControlador.ControladorIngresarMesaJugador;
import uiInterfacesVistas.IIngresarMesaJugador;
import utilidades.MesaCellRendererJugador;

/**
 *
 * @author Ryzen
 */
public class IngresarMesaVista extends javax.swing.JFrame implements IIngresarMesaJugador {
        
    private ControladorIngresarMesaJugador controlador;
    private DefaultListModel<Mesa> modeloMesas;
    
    public IngresarMesaVista(Jugador jugador) {
        initComponents();
        setResizable(false);
        setTitle("Ingresar Mesa");        
        this.modeloMesas = new DefaultListModel<>();
        lstMesas.setModel(modeloMesas);
        lstMesas.setCellRenderer(new MesaCellRendererJugador());        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        agregarEscuchadorCierre();
        controlador = new ControladorIngresarMesaJugador(this, jugador);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstMesas = new javax.swing.JList<>();
        btnVerMesa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNombreJugador = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblSaldoJugador = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setText("MESAS");

        jScrollPane1.setViewportView(lstMesas);

        btnVerMesa.setText("Ingresar");
        btnVerMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMesaActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("Bienvenido!");

        jLabel2.setText("Saldo: $");

        lblSaldoJugador.setForeground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(169, 169, 169))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSaldoJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnVerMesa)
                        .addGap(156, 156, 156))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombreJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSaldoJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVerMesa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMesaActionPerformed
        Mesa mesaSeleccionada = lstMesas.getSelectedValue();  // Ahora es de tipo Mesa
        try{
            if (mesaSeleccionada != null) {
                controlador.ingresarMesa(mesaSeleccionada);                
            }else{
                throw new Exception("Seleccione una mesa");
            }         
        }catch(Exception e){
            lblError.setText(e.getMessage());
        }
        
    }//GEN-LAST:event_btnVerMesaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerMesa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblNombreJugador;
    private javax.swing.JLabel lblSaldoJugador;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<Mesa> lstMesas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizarMesas(ArrayList<Mesa> mesas) {
        modeloMesas.removeAllElements();        
        for(Mesa m : mesas){
            modeloMesas.addElement(m);
        }  
    }

    @Override
    public void actualizarUsuario(Jugador j) {
        lblNombreJugador.setText(j.getNombreCompleto());
        lblSaldoJugador.setText(String.valueOf(j.getSaldo()));
    }

    @Override
    public void cerrar() {
        this.dispose();
    }

    @Override
    public void ejecutarSiguienteCU(Jugador j, Mesa m) {
        JugarPokerVista jpk = new JugarPokerVista(j, m);        
        jpk.setLocationRelativeTo(null);
        jpk.setVisible(true);
    }

    @Override
    public void error(String mensaje) {
        lblError.setText(mensaje);
    }

    @Override
    public void logout() {
        controlador.logout();
    }
    
    public void agregarEscuchadorCierre(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logout();
            }
        });
    }
}
