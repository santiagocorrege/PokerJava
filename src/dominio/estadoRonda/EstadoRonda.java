/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.estadoRonda;

import dominio.Carta;
import dominio.Jugador;
import dominio.Ronda;
import dominio.Ronda.EstadoRondaTipo;
import dominio.ManoJugador;
import excepciones.MasoException;
import java.util.ArrayList;

/**
 *
 * @author Ryzen
 */
public abstract class EstadoRonda{
    private Ronda contexto;
    private EstadoRondaTipo estado;
    
    public EstadoRonda(Ronda mano, EstadoRondaTipo estado){
        this.contexto = mano;
        this.estado = estado;
    }    
    public void accionPendienteVigentes(){
        for(Jugador j : contexto.getVigentes()){
            ManoJugador mj = j.getMano();
            mj.setEstado(ManoJugador.EstadoManoJugador.AccionPendiente);
        }
    }
    
    public void accionPendienteJugadores(){
        for(Jugador j : contexto.getJugadores()){
            ManoJugador mj = j.getMano();
            mj.setEstado(ManoJugador.EstadoManoJugador.AccionPendiente);
        }
    }
    
    public abstract void apuestaIniciada(ManoJugador manoJugador, float Monto) throws MasoException, Exception;
    
    public abstract void apuestaPagada(ManoJugador manojugador) throws MasoException, Exception;
    
    public abstract void jugadorPasa(ManoJugador manojugador) throws MasoException, Exception;            
    
    public abstract void pedirCartas(ManoJugador manojugador, ArrayList<Carta> cartas) throws MasoException, Exception;
    
    public abstract void repartiendoCartas() throws MasoException, Exception;
    
    public abstract void terminada() throws MasoException, Exception;          

    public abstract void jugarOtraRonda(ManoJugador mj) throws MasoException, Exception;          
    
    public abstract void irse(ManoJugador mj) throws MasoException, Exception;                  
    
    public int contarVigentesOJugadoresTipo(ManoJugador.EstadoManoJugador estado, String tipo){        
        int contador = 0;
        ArrayList<Jugador> listado = contexto.getVigentes();
        if(tipo.equals("Jugadores")){
            listado = contexto.getJugadores();
        }
        for(Jugador j : listado){
            ManoJugador mj = j.getMano();
            if(estado.equals(mj.getEstado())){
                contador ++;
            }
        }
        return contador;
    }
    
    public boolean faltanVigentesPendientes(){
        return contarVigentesOJugadoresTipo(ManoJugador.EstadoManoJugador.AccionPendiente, "Vigentes") > 0;
    }    

    public void accionPendienteTipo(String tipo){
        ArrayList<Jugador> listado = contexto.getVigentes();;
        if(tipo.equals("Jugadores")){
            listado = this.contexto.getJugadores();;
        }
        for(Jugador j : listado){
            ManoJugador mj = j.getMano();
            mj.setEstado(ManoJugador.EstadoManoJugador.AccionPendiente);
        }        
    } 
    public EstadoRondaTipo getEstadoRonda(){
        return this.estado;
    }
    
    public Ronda getContexto(){
        return this.contexto;
    }
    
}
