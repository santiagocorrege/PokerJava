/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.estadoMesa;

import dominio.Jugador;
import dominio.ManoJugador;
import dominio.Mesa;
import dominio.Mesa.EstadoMesa;
import dominio.Ronda;
import excepciones.MesaException;

/**
 *
 * @author Ryzen
 */
public abstract class IEstadoMesa {
    private Mesa contexto;
    private EstadoMesa estadoMesa;
            
    public IEstadoMesa(Mesa contexto, EstadoMesa estado){
        this.contexto = contexto;
        this.estadoMesa = estado;
    }
    
    public abstract void ingresarJugador(Jugador j) throws Exception, MesaException;
    
    public abstract void quitarJugador(Jugador j) throws Exception, MesaException;
    
    public abstract void iniciarMesa() throws Exception, MesaException;
    
    public abstract void abrirMesa() throws Exception, MesaException;
        
    public abstract void finalizarMesa() throws Exception, MesaException;
    
    public abstract void nuevaRonda() throws Exception, MesaException;
    
    public void cobrarRonda() throws Exception, MesaException{
        Ronda ronda = contexto.getRondaActual();
        ManoJugador ganador = ronda.getGanador();        
        float pozo = ronda.getPozo();
        if(ganador != null){                                        
            float comision = pozo * contexto.getComision() / 100;
            contexto.setTotalApostado(contexto.getTotalApostado() + pozo);
            contexto.setTotalRecaudado(contexto.getTotalRecaudado() + comision);                                
        }    
    }
    public Mesa getContexto(){
        return this.contexto;
    }
    
    public EstadoMesa getEstado(){
        return this.estadoMesa;
    }
}
