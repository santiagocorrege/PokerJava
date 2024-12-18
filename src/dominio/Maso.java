/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;
import java.util.Random;
import servicios.Fachada;

/**
 *
 * @author Ryzen
 */
public class Maso {
    private ArrayList<Carta> mazo = new ArrayList<>();
    private ArrayList<Carta> cartasEntregadas = new ArrayList<>();
    private static Palo[] palos = {new Palo("C", 4), new Palo("D", 3), new Palo("T", 2), new Palo("P",1)};    
    
    public Maso(){
        reiniciarMaso();
    }
    
    public void barajarMaso(){
        ArrayList<Carta> barajado = new ArrayList<>();
        Random random = new Random();               
        if(cartasEntregadas.size() > 0){
            juntarMazo();
        }
        while(mazo.size() > 0){
            int min = 0;
            int max = mazo.size() - 1;
            int valorRandom = random.nextInt(max - min + 1) + min;
            barajado.add(mazo.get(valorRandom));
            mazo.remove(valorRandom);
        }
        mazo = barajado;
    }
    
    public void cambiarCartas(ArrayList<Carta> mano, ArrayList<Carta> aCambiar){
        int cantidadCartas = aCambiar.size();
        for(Carta c : aCambiar){
            mano.remove(c);
        }
        for(int i=0; i<cantidadCartas; i++){
            Carta carta = mazo.get(0);
            mano.add(carta);   
            cartasEntregadas.add(carta);
            mazo.remove(0);             
        }            
    }
    
    public void reiniciarMaso(){
        mazo.removeAll(mazo);
        for(int numero=1; numero<=13; numero++){
            for(int j=0; j<palos.length; j++){
                Carta c = new Carta(numero, palos[j]);     
                mazo.add(c);
            }
        }
    }
    
    public ArrayList<Carta> darMano(){
        ArrayList<Carta> mano = new ArrayList<>();
        for(int i=0; i<5; i++){
            Carta carta = mazo.get(0);
            cartasEntregadas.add(carta);
            mazo.remove(0);
            mano.add(carta);            
        }
        return mano;
    }
    
    public void darManos(ArrayList<Jugador> jugadores){
        for(Jugador j : jugadores){
            ManoJugador mj = new ManoJugador(j); 
            mj.setCartas(this.darMano());            
            Fachada.getInstancia().getFigura(mj); 
        }  
    }
    
    public void juntarMazo(){
        mazo.addAll(cartasEntregadas);
        cartasEntregadas.clear();
    }
    
    public static Palo getPalo(String palo){        
        Palo p = null;
        for(int i=0; i<4; i++){
            if(palos[i].getNombre() == palo){ 
                p = palos[i];
                break;
            }
        }        
        return p;
    }
}
