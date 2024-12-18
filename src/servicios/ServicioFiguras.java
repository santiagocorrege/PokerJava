/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dominio.Carta;
import dominio.Figura;
import dominio.ManoJugador;
import dominio.figuras.CantidadCartas;
import dominio.figuras.Escalera;
import dominio.figuras.SinFigura;
import dominio.figuras.TipoFigura;
import java.util.ArrayList;

/**
 *
 * @author Ryzen
 */
public class ServicioFiguras {
    private ArrayList<TipoFigura> tipoFiguras;

    public ServicioFiguras(){
        tipoFiguras = new ArrayList<>();
        tipoFiguras.add(new CantidadCartas("Poker", 5, 4));
        tipoFiguras.add(new Escalera());
        tipoFiguras.add(new CantidadCartas("Pierna", 3, 3));
        tipoFiguras.add(new CantidadCartas("Par", 2, 2));
        tipoFiguras.add(new SinFigura());
    }
    
    public void setFigura(ManoJugador m) {
        ArrayList<Carta> cartas = m.getCartas();
        for (TipoFigura tipo : tipoFiguras) {            
            if (tipo.cumple(cartas)) {                
                m.setTipoFigura(tipo);                
                break;
            }
        }
    }  
    
    public ManoJugador figuraGanadora(ArrayList<ManoJugador> manos){
        ManoJugador ganadora = manos.get(0);
        Figura figuraGanadora = ganadora.getFigura();
        for(ManoJugador mj : manos){
            Figura f = mj.getFigura();
            if(f.compareTo(figuraGanadora) > 0){
                ganadora = mj;
                figuraGanadora = f;
            }
        }
        return ganadora;
    }
    
    
    public ArrayList<TipoFigura> getTiposFiguras(){
        return this.tipoFiguras;
    }
    
    
}
