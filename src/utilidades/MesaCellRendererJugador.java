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
public class MesaCellRendererJugador extends JLabel implements ListCellRenderer<Mesa> {
    public MesaCellRendererJugador(){
        setOpaque(true);
    }
    
        @Override
    public Component getListCellRendererComponent(JList<? extends Mesa> list, Mesa mesa, int index, boolean isSelected, boolean cellHasFocus) {
        // Personaliza el texto que se muestra
        setText(mesa.getNumero() + " | N# Requeridos:" + mesa.getCantidadJugadoresRequeridos() + " | Jugadores: " + mesa.getCantidadJugadores() + " | Luz: " + mesa.getLuz() + " | Comision: " + mesa.getComision()  );
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
