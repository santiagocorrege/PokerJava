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
import excepciones.MasoException;
import excepciones.MesaException;
import excepciones.RondaException;
import java.util.ArrayList;

/**
 *
 * @author Ryzen
 */
public class EstadoRondaTerminada extends EstadoRonda{

    public EstadoRondaTerminada(Ronda mano){
        super(mano, EstadoRondaTipo.Terminada);
    }    

    @Override
    public void apuestaIniciada(ManoJugador manoJugador, float monto) throws MasoException, RondaException{
        throw new RondaException("No es posible indicar que no deseas iniciar una apuesta en este momento.”");
    }

    @Override
    public void terminada() throws MasoException, Exception{        
        Ronda contexto = this.getContexto();           
        for(Jugador j : contexto.getJugadores()){
            if(!j.validarSaldo(contexto.getLuz())){
                j.retirado();
                irse(j.getMano());                                
            }
        }
        if(contexto.getJugadores().size() <= 1 || contarVigentesOJugadoresTipo(EstadoManoJugador.AccionPendiente, "Jugadores") == 0){
            contexto.avisar(EstadoRondaTipo.JugadoresConfirmados);
        }
        contexto.avisar(this.getEstadoRonda());                 
    }

    @Override
    public void repartiendoCartas() throws MasoException {
        throw new MasoException("Ronda terminada esperando confirmacion de jugadores");
    }

    @Override
    public void apuestaPagada(ManoJugador manojugador) throws MasoException {
        throw new MasoException("Ronda terminada esperando confirmacion de jugadores");
    }

    @Override
    public void jugadorPasa(ManoJugador manojugador) throws MasoException {
        throw new MasoException("Ronda terminada esperando confirmacion de jugadores");
    }

    @Override
    public void pedirCartas(ManoJugador manojugador, ArrayList<Carta> cartas) throws MasoException {
        throw new MasoException("Ronda terminada esperando confirmacion de jugadores");
    }

    @Override
    public void jugarOtraRonda(ManoJugador mj) throws MasoException, Exception {        
        Ronda contexto = this.getContexto();                
        if(mj.getEstado().equals(EstadoManoJugador.AccionPendiente)){            
            mj.setEstado(EstadoManoJugador.JugarOtraRonda);
            contexto.avisar(EstadoManoJugador.JugarOtraRonda);
        }else{
            throw new MesaException("No hay acciones que tomar, esperando confirmacion resto jugadores");
        }
        if(contarVigentesOJugadoresTipo(EstadoManoJugador.AccionPendiente, "Jugadores") == 0){
            terminada();
        }        
    }

    @Override
    public void irse(ManoJugador mj) throws MasoException, Exception {        
        Ronda contexto = this.getContexto();              
        if(mj.getEstado().equals(EstadoManoJugador.AccionPendiente)){  
            contexto.quitarJugador(mj);
            contexto.avisar(EstadoManoJugador.Irse);
            mj.irse();
        }else{
            throw new MesaException("No hay acciones que tomar, esperando confirmacion resto jugadores");
        }
        if(contarVigentesOJugadoresTipo(EstadoManoJugador.AccionPendiente, "Jugadores") <= 1){                        
            terminada();                        
        }        
    }
    
}
