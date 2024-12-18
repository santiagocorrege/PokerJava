/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import dominio.Mesa;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Ryzen
 */
public class MesaCellRenderer extends JLabel implements ListCellRenderer<Mesa> {
    public MesaCellRenderer(){
        setOpaque(true);
    }
    
        @Override
    public Component getListCellRendererComponent(JList<? extends Mesa> list, Mesa mesa, int index, boolean isSelected, boolean cellHasFocus) {
        // Personaliza el texto que se muestra
        setText("Mesa " + mesa.getNumero() + " | Jugadores requeridos: " + mesa.getCantidadJugadoresRequeridos() + " | Jugadores actuales: " + mesa.getCantidadJugadores() + " | Apuesta base: " + mesa.getLuz() + " | Comision mesa: " + mesa.getComision() + " | Total apostado: " + mesa.getTotalApostado() + " | Total recaudado:  " + mesa.getTotalRecaudado() + " | Estado: " + mesa.getEstado());
        // Personaliza el fondo si está seleccionada
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
