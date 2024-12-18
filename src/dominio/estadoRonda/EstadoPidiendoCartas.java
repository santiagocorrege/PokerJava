/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.estadoRonda;

import dominio.Carta;
import dominio.Jugador;
import dominio.ManoJugador;
import dominio.ManoJugador.EstadoManoJugador;
import dominio.Ronda;
import excepciones.MasoException;
import excepciones.RondaException;
import java.util.ArrayList;
import servicios.Fachada;

/**
 *
 * @author Ryzen
 */
public class EstadoPidiendoCartas extends EstadoRonda{

    public EstadoPidiendoCartas(Ronda mano){
        super(mano, Ronda.EstadoRondaTipo.PidiendoCartas);
    }
    
    @Override
    public void apuestaIniciada(ManoJugador manoJugador, float monto) throws MasoException, Exception, RondaException{
        throw new RondaException("No es posible indicar que no deseas iniciar una apuesta en este momento.");
    }

    @Override
    public void apuestaPagada(ManoJugador manojugador) throws MasoException, Exception, RondaException {
        throw new RondaException("Pidiendo cartas, no hay apuesta que pagar...");
    }

    @Override
    public void jugadorPasa(ManoJugador manojugador) throws MasoException, Exception {
        throw new RondaException("Pidiendo cartas, debes pedir cartas sea (0 o 5)");
    }

    @Override
    public void pedirCartas(ManoJugador manoJugador, ArrayList<Carta> cartas) throws MasoException, Exception, RondaException{
        Ronda contexto = this.getContexto();
        if(manoJugador.getEstado().equals(EstadoManoJugador.AccionPendiente)){                    
            manoJugador.setEstado(EstadoManoJugador.Pasa);
            contexto.getMaso().cambiarCartas(manoJugador.getCartas(), cartas);
            Fachada.getInstancia().getFigura(manoJugador);
            contexto.avisar(this.getEstadoRonda());
            if(!faltanVigentesPendientes()){
                terminada();
            }
        }else if(manoJugador.getEstado().equals(EstadoManoJugador.NoPagoLaApuesta)){
            throw new RondaException("No pagaste la apuesta, no puedes pedir cartas");            
        }
        else{
            throw new RondaException("Ya cambiaste de cartas");
        }        
    }

    @Override
    public void repartiendoCartas() throws MasoException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void terminada() throws MasoException, Exception {
        Ronda contexto = this.getContexto();
        ArrayList<ManoJugador> manos = new ArrayList<ManoJugador>();
        for(Jugador j : contexto.getVigentes()){
            ManoJugador mj = j.getMano();
            manos.add(mj);                  
        }
        accionPendienteJugadores();
        ManoJugador ganador = Fachada.getInstancia().figuraGanadora(manos);
        contexto.setGanador(ganador); 
        contexto.gano();        
        contexto.setEstadoRonda(new EstadoRondaTerminada(contexto));
        contexto.rondaTerminada();
    }

    @Override
    public void jugarOtraRonda(ManoJugador mj) throws MasoException, Exception {
        throw new RondaException("No disponible, ronda en curso...");
    }

    @Override
    public void irse(ManoJugador mj) throws MasoException, Exception {
        throw new RondaException("No disponible, ronda en curso...");
    }
    
}
