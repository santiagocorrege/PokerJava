/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.estadoMesa;

import dominio.Jugador;
import dominio.Mesa;
import dominio.Ronda;
import excepciones.MasoException;
import excepciones.MesaException;

/**
 *
 * @author Ryzen
 */
public class EstadoIniciada extends IEstadoMesa{

    public EstadoIniciada(Mesa m) throws MesaException{
        super(m, Mesa.EstadoMesa.Iniciada);        
    }
    
    @Override
    public void ingresarJugador(Jugador j) throws MesaException {
        throw new MesaException("La mesa esta iniciada no se pueden unir jugadores");
    }

    @Override
    public void quitarJugador(Jugador jugador) throws MesaException, Exception {        
        Mesa contexto = this.getContexto();
        contexto.getRondaActual().irse(jugador.getMano());                  
        contexto.getParticipantes().remove(jugador);
        jugador.salirMesa();
        contexto.avisar(Mesa.EstadoMesa.CambioJugadores);   
    }

    @Override
    public void iniciarMesa() throws MesaException, MasoException, Exception{        
        Mesa contexto = this.getContexto(); 
        nuevaRonda();                           
        contexto.avisar(this.getEstado());                     
    }

    @Override
    public void abrirMesa() throws MesaException{
        throw new MesaException("La mesa esta inicializada, no se puede abrir");
    }

    @Override
    public void finalizarMesa() throws MesaException {
        throw new MesaException("La mesa no ha finalizado");
    }

    @Override
    public void nuevaRonda() throws MesaException, Exception {
        Mesa contexto = this.getContexto();
        Ronda ronda = contexto.getRondaActual();                      
        if(ronda == null){                
            ronda = new Ronda(1, contexto.getParticipantes(), contexto.getMaso(), contexto.getLuz(), 0, contexto.getComision());                
        }else{       
            cobrarRonda();                
            ronda.quitarObservador(contexto); 
            int numeroMano = ronda.getNumeroMano();
            float pozo = ronda.getPozo();                            
            if(ronda.getGanador() != null){
                pozo = 0;
            }
            ronda = new Ronda(numeroMano + 1, contexto.getParticipantes(), contexto.getMaso(), contexto.getLuz(), pozo, contexto.getComision());                                  
        }  
        ronda.cobrarLuz();
        contexto.agregarRonda(ronda);
        contexto.setRondaActual(ronda);           
        ronda.agregarObservador(contexto);              
        contexto.avisar(contexto.getEstado());
    }
    
}
