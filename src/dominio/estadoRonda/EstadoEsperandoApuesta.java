/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.estadoRonda;

import dominio.Carta;
import dominio.Jugador;
import dominio.Ronda;
import dominio.ManoJugador;
import dominio.ManoJugador.EstadoManoJugador;
import dominio.Maso;
import dominio.Ronda.EstadoRondaTipo;
import excepciones.MasoException;
import excepciones.RondaException;
import java.util.ArrayList;

/**
 *
 * @author Ryzen
 */
public class EstadoEsperandoApuesta extends EstadoRonda{

    public EstadoEsperandoApuesta(Ronda mano){
        super(mano, EstadoRondaTipo.EsperandoApuesta);
    }            
    
    @Override
    public void apuestaIniciada(ManoJugador manoJugador, float monto) throws MasoException, Exception, RondaException{
        Ronda contexto = this.getContexto();                
        if(manoJugador.getEstado().equals(EstadoManoJugador.AccionPendiente)){
            //Cobrar apuesta valida monto
            contexto.validarApuesta(monto);
            manoJugador.cobrarApuesta(monto);  
            contexto.setPozo(contexto.getPozo() + monto);
            contexto.setApuesta(monto);                            
            accionPendienteTipo("Jugadores");
            manoJugador.setEstado(EstadoManoJugador.ApuestaIniciada);                               
            contexto.setEstadoRonda(new EstadoApuestaIniciada(contexto));
            contexto.avisar(EstadoRondaTipo.ApuestaIniciada);
        }else{
            throw new RondaException("Ya no puedes apostar, has pasado");
        }        
    }

    @Override
    public void terminada()  throws MasoException , Exception{
        Ronda contexto = this.getContexto();
        accionPendienteJugadores();
        contexto.setEstadoRonda(new EstadoRondaTerminada(contexto));   
        contexto.rondaTerminada();
    }

    @Override
    public void jugadorPasa(ManoJugador manoJugador) throws MasoException , Exception{
        Ronda contexto = this.getContexto();        
        manoJugador.setEstado(EstadoManoJugador.Pasa);
        contexto.avisar(EstadoManoJugador.Pasa);                 
        if(!faltanVigentesPendientes()){                      
            terminada();
        }        
    }

    @Override
    public void repartiendoCartas() throws MasoException, Exception{
        Ronda contexto = this.getContexto();                
        ArrayList<Jugador> jugadores = contexto.getJugadores();        
        Maso maso = contexto.getMaso();
        maso.reiniciarMaso();
        maso.barajarMaso();        
        maso.darManos(jugadores);                      
        contexto.avisar(this.getEstadoRonda());           
    }

    @Override
    public void apuestaPagada(ManoJugador manojugador) throws RondaException {
        throw new RondaException("No hay apuestas que pagar aun");
    }

    @Override
    public void pedirCartas(ManoJugador manojugador, ArrayList<Carta> cartas) throws MasoException, RondaException {
        throw new RondaException("No se pueden pedir cartas, esperando apuestas...");
    }

    @Override
    public void jugarOtraRonda(ManoJugador mj) throws MasoException, Exception {
        throw new RondaException("No disponible , Esperando apuestas...");
    }

    @Override
    public void irse(ManoJugador mj) throws MasoException, Exception {
        throw new RondaException("Espera al finalizar la ronda para irte...");
    }
    
}
