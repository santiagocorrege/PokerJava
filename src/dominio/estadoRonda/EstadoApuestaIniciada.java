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
import dominio.Ronda.EstadoRondaTipo;
import excepciones.ManoJugadorException;
import excepciones.MasoException;
import excepciones.RondaException;
import java.util.ArrayList;
/**
 *
 * @author Ryzen
 */
public class EstadoApuestaIniciada extends EstadoRonda{

    public EstadoApuestaIniciada(Ronda mano){
        super(mano, Ronda.EstadoRondaTipo.ApuestaIniciada);        
    }

    @Override
    public void apuestaIniciada(ManoJugador manoJugador, float monto) throws MasoException, RondaException{
        if(manoJugador.getEstado().equals(EstadoManoJugador.ApuestaIniciada)){
            throw new RondaException("Tu iniciaste esta apuesta");
        }else{
            throw new RondaException("Ya hay una apuesta en curso");
        }
    }

    @Override
    public void terminada()  throws MasoException, Exception{
        Ronda contexto = this.getContexto();     
        Jugador ganador = null;
        for(Jugador j : contexto.getJugadores()){
            if(j.getMano().getEstado().equals(EstadoManoJugador.ApuestaIniciada)){
                ganador = j;
            }
        }
        contexto.setGanador(ganador.getMano());
        contexto.gano();        
        accionPendienteJugadores();
        contexto.setEstadoRonda(new EstadoRondaTerminada(contexto));          
        contexto.rondaTerminada();
    }

    @Override
    public void jugadorPasa(ManoJugador manoJugador) throws MasoException, RondaException, Exception {        
        Ronda contexto = this.getContexto();
        if(manoJugador.getEstado().equals(EstadoManoJugador.ApuestaIniciada)){
            throw new RondaException("Ya iniciaste una apuesta, no hay apuestas que pasar,");    
        }else if(manoJugador.getEstado().equals(EstadoManoJugador.Pasa)){
            throw new RondaException("Ya pasaste pasar,");    
        }
        manoJugador.setEstado(EstadoManoJugador.NoPagoLaApuesta); 
        contexto.quitarVigente(manoJugador);
        int contadorPendientes = contarVigentesOJugadoresTipo(EstadoManoJugador.AccionPendiente, "Vigentes");
        int contadorPagos = contarVigentesOJugadoresTipo(EstadoManoJugador.ApuestaPagada, "Vigentes");          
        if(contadorPendientes == 0){            
            if(contadorPagos == 0){
                terminada();                            
            }else{                
                contexto.setEstadoRonda(new EstadoPidiendoCartas(contexto));
            }            
        }        
        contexto.avisar(this.getEstadoRonda());        
    }

    @Override
    public void repartiendoCartas() throws MasoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void apuestaPagada(ManoJugador manoJugador) throws MasoException, RondaException, ManoJugadorException, Exception {
        Ronda contexto = this.getContexto();         
        switch (manoJugador.getEstado()) {
            case AccionPendiente:
                manoJugador.cobrarApuesta(contexto.getApuesta());
                manoJugador.setEstado(EstadoManoJugador.ApuestaPagada);
                contexto.setPozo(contexto.getPozo() + contexto.getApuesta());
                if(contarVigentesOJugadoresTipo(EstadoManoJugador.AccionPendiente, "Vigentes") == 0){
                    accionPendienteTipo("Vigentes");
                    contexto.setEstadoRonda(new EstadoPidiendoCartas(contexto));
                    contexto.avisar(EstadoRondaTipo.PidiendoCartas);
                }else{
                    contexto.avisar(this.getEstadoRonda());
                }   break;
            case ApuestaPagada:
                throw new RondaException("Ya pagaste la apuesta");
            case ApuestaIniciada:
                throw new RondaException("Tu iniciaste esta apuesta");
            default:
                throw new RondaException("Ya realizo una accion esta ronda");
        }
        
        
    }   

    @Override
    public void pedirCartas(ManoJugador manojugador, ArrayList<Carta> cartas) throws RondaException {
        throw new RondaException("No se pueden pedir cartas, hay una apuesta iniciada");
    }

    @Override
    public void jugarOtraRonda(ManoJugador mj) throws MasoException, Exception {
        throw new RondaException("No disponible , Apuesta en curso...");
    }

    @Override
    public void irse(ManoJugador mj) throws MasoException, Exception {
        throw new RondaException("No te puedes ir, Apuesta en curso...");
    }
    
}
