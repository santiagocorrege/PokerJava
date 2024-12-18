/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import dominio.Carta;
import dominio.figuras.TipoFigura;
import java.util.ArrayList;

public class Figura implements Comparable<Figura> {

    public Carta cartaMayor;    
    public TipoFigura tipoFigura;
    
    public String getNombre() {
        return tipoFigura.getNombre();
    }

    public int getValor() {
        return tipoFigura.getValor();
    }    
    
    public Carta getCartaMayor() {
        return this.cartaMayor;
    }    

    public TipoFigura getTipoFigura() {
        return tipoFigura;
    }

    public void setTipoFigura(TipoFigura tipoFigura, ArrayList<Carta> cartas) {
        this.tipoFigura = tipoFigura;
        this.cartaMayor = tipoFigura.getCartaAltaFigura(cartas);
    }
            
            
    @Override
    public int compareTo(Figura otra) {
        if(this.getValor() != otra.getValor()){
            return Integer.compare(this.getValor(), otra.getValor());
        }
        return this.cartaMayor.compareTo(otra.cartaMayor);
    }   
        
}
    
    

