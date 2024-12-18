/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.figuras;

import dominio.Carta;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ryzen
 */
public abstract class TipoFigura {
    private String nombre;
    private int valor;
    
    public TipoFigura(String nombre, int valor){
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    public boolean cumple(ArrayList<Carta> cartas){
        ordenarCartasAscendente(cartas);
        return analizarFigura(cartas);
    }
    
    private ArrayList<Carta> ordenarCartasAscendente(ArrayList<Carta> cartas) {        
        Collections.sort(cartas);
        return cartas;
    }  
    
    public abstract Carta getCartaAltaFigura(ArrayList<Carta> cartas);
    
    protected abstract boolean analizarFigura(ArrayList<Carta> cartas);
}