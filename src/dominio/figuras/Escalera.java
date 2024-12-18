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
public class Escalera extends TipoFigura{        

    public Escalera() {
        super("Escalera", 4);
    }

    @Override
    public boolean analizarFigura(ArrayList<Carta> cartas) {        
        boolean cumple = true;
        int contador = 0;        
        int cAnterior = -1;
        for(Carta c : cartas){
            if(contador == 0){
                contador++;
                cAnterior = c.getNumero();
            }else if(c.getNumero() == cAnterior + 1){
                contador++;
                cAnterior = c.getNumero();
            }else{
                cumple = false;
                break;
            }
        }        
        return cumple;
    }            

    @Override
    public Carta getCartaAltaFigura(ArrayList<Carta> cartas) {        
        int contador = 0;        
        int cAnterior = -1;
        for(Carta c : cartas){
            if(contador == 0){
                contador++;
                cAnterior = c.getNumero();
            }else if(c.getNumero() == cAnterior + 1){
                contador++;
                cAnterior = c.getNumero();
            }else{                
                break;
            }
        }        
        return cartas.get(cartas.size() - 1);
    }
    

}
