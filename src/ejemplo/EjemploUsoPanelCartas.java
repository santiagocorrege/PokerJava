/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo;

import dominio.Carta;
import dominio.Maso;
import java.util.ArrayList;
import panelCartasPoker.CartaPoker;

/**
 *
 * @author PC
 */
public class EjemploUsoPanelCartas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<CartaPoker> cartas = new ArrayList();
        
        cartas.add(new Carta(1, Maso.getPalo(CartaPoker.CORAZON)));
        cartas.add(new Carta(1,Maso.getPalo(CartaPoker.DIAMANTE)));
        cartas.add(new Carta(1,Maso.getPalo(CartaPoker.TREBOL)));
        cartas.add(new Carta(1,Maso.getPalo(CartaPoker.PIQUE)));
        cartas.add(new Carta(11,Maso.getPalo(CartaPoker.PIQUE)));
        
        DialogoEjemplo dialogo = new DialogoEjemplo(null, false);
        dialogo.setVisible(true);  
       
        dialogo.cargarCartas(cartas);
        
     
        cartas.set(0, new Carta(10,Maso.getPalo(CartaPoker.PIQUE)));
        dialogo.cargarCartas(cartas);
        cartas.set(0, new Carta(9,Maso.getPalo(CartaPoker.PIQUE)));
        dialogo.cargarCartas(cartas);        
        
    }
    
}
