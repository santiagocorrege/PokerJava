/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.Objects;
import panelCartasPoker.CartaPoker;


public class Carta implements Comparable<Carta>, CartaPoker{    
    private int numero;
    private Palo palo;
    private boolean visible;    

    public Carta(int numero, Palo palo) {
        this.numero = numero;
        this.palo = palo;        
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


    public void setPalo(Palo palo) {
        this.palo = palo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    @Override
    public int compareTo(Carta otra) {        
        if (this.numero != otra.numero) {
            return Integer.compare(this.numero, otra.numero);
        }

        return Integer.compare(this.palo.getValor(), otra.palo.getValor());
    }

    @Override
    public String toString(){
        return "Numero: " + this.numero + " Palo: " + this.palo.getNombre();
    }

    @Override
    public int getValorCarta() {
        return numero;
    }

    @Override
    public String getPaloCarta() {
        return palo.getNombre();
    }

    @Override
    public boolean estaVisible() {
        return this.visible;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return Objects.equals(this.palo, other.palo);
    }
    
}
