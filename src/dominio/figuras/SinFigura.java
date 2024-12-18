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
public class SinFigura extends TipoFigura{

    public SinFigura(){
        super("SinFigura", 1);
    }
    
    @Override
    public boolean analizarFigura(ArrayList<Carta> cartas) {
              
        return true;
    }

    @Override
    public Carta getCartaAltaFigura(ArrayList<Carta> cartas) {
        if(cartas.size() > 0){
            return cartas.getLast();  
        }
        return null;
    }


    
}
