/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.figuras;

import dominio.Carta;
import java.util.ArrayList;

/**
 *
 * @author Ryzen
 */
public class CantidadCartas extends TipoFigura{

    private int cantidadCartas; 
    
    public CantidadCartas(String nombre, int valorFigura, int cantidad) {
        super(nombre, valorFigura);
        this.cantidadCartas = cantidad;
    }

    @Override
    public boolean analizarFigura(ArrayList<Carta> cartas) {
        Carta cMaxima = cartas.get(0);
        int contador = 0;
        boolean cumple = false;

        for (Carta c : cartas) {
            if (c.getNumero() == cMaxima.getNumero()) {
                contador++;
                if(c.compareTo(cMaxima) > 0){
                    cMaxima = c;
                }
                if (contador >= this.cantidadCartas) {
                    cumple = true;                    
                    break;
                }
            } else {                
                cMaxima = c;
                contador = 1; 
            }
        }
        return cumple;
    }
    
    @Override
    public Carta getCartaAltaFigura(ArrayList<Carta> cartas) {
        Carta cMaxima = null;
        int contador = 0;
        int numeroActual = -1;

        for (Carta c : cartas) {
            if (c.getNumero() == numeroActual) {
                contador++;
                if (contador == this.cantidadCartas) {                    
                    if (cMaxima == null || c.compareTo(cMaxima) > 0) {
                        cMaxima = c;
                    }
                    break; 
                }
            } else {                
                numeroActual = c.getNumero();
                contador = 1;
                cMaxima = c;
            }
        }
        return cMaxima;
    }
}
