/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import dominio.figuras.TipoFigura;
import excepciones.ManoJugadorException;
import java.util.ArrayList;
import observador.Observable;

/**
 *
 * @author Ryzen
 */
public class ManoJugador{
    public ArrayList<Carta> cartas;    
    private Figura figura;
    private EstadoManoJugador estado;
    private Jugador jugador;
    private float apuesta;

    public ManoJugador(Jugador j){
        this.jugador = j;        
        this.estado = EstadoManoJugador.AccionPendiente;
        this.figura = new Figura();
        this.cartas = new ArrayList<Carta>();
        this.apuesta = 0;
        j.setManoJugador(this);
    }
   
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    
    public void cobrarApuesta(float monto) throws ManoJugadorException{        
        if(!this.jugador.validarSaldo(monto)){            
            throw new ManoJugadorException("Apuesta máxima $" + this.jugador.getSaldo());            
        }  
        this.apuesta = this.apuesta + monto;
        this.jugador.retirarSaldo(monto);       
    }
    
    public void pagarRonda(float cantidad){
        this.jugador.cargarSaldo(cantidad);
    }
        

    public Figura getFigura() {
        return figura;
    }

    public void setTipoFigura(TipoFigura tf) {
        this.figura.setTipoFigura(tf, cartas);
    }

    public float getApuesta() {
        return apuesta;
    }

    public void setApuesta(float apuesta) {
        this.apuesta = apuesta;
    }
    
    
    public EstadoManoJugador getEstado() {
        return estado;
    }


    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setEstado(EstadoManoJugador estado) {
        this.estado = estado;
    }
    
    public void irse() throws ManoJugadorException, Exception{
        this.jugador.salirMesa();
    }
    
    public enum EstadoManoJugador{
        AccionPendiente,
        ApuestaIniciada,        
        ApuestaPagada,
        NoPagoLaApuesta,
        Pasa,
        Irse,
        JugarOtraRonda
    }
        
}
