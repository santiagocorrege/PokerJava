/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.estadoMesa;

import dominio.Jugador;
import dominio.Mesa;
import dominio.Mesa.EstadoMesa;
import excepciones.MesaException;

/**
 *
 * @author Ryzen
 */
public class EstadoAbierta extends IEstadoMesa{

    public EstadoAbierta(Mesa m) throws Exception, MesaException{
        super(m, EstadoMesa.Abierta);        
    }
    
    @Override
    public void ingresarJugador(Jugador jugador) throws Exception, MesaException{
        Mesa contexto = this.getContexto();
        jugador.validarIngreso();
        if(!jugador.validarSaldo(contexto.getSaldoMinimoIngreso())){
            throw new MesaException("Saldo insuficiente");
        }        
        contexto.getParticipantes().add(jugador);         
        jugador.ingresarMesa(contexto);
        if(!jugadoresRequeridos()){
            iniciarMesa();            
        }
        contexto.avisar(Mesa.EstadoMesa.CambioJugadores);        
    }

    @Override
    public void quitarJugador(Jugador jugador) throws Exception, MesaException{
        Mesa contexto = this.getContexto();
        contexto.getParticipantes().remove(jugador);
        jugador.salirMesa();
        contexto.avisar(Mesa.EstadoMesa.CambioJugadores);   
    }

    @Override
    public void iniciarMesa() throws Exception, MesaException {
        Mesa contexto = this.getContexto();                                      
        contexto.setEstadoMesa(new EstadoIniciada(contexto));     
        contexto.iniciarMesa();        
    }

    @Override
    public void abrirMesa() throws Exception, MesaException {
        Mesa contexto = this.getContexto();
        contexto.avisar(this.getEstado());
    }

    @Override
    public void finalizarMesa() throws MesaException {
        throw new MesaException("No se puede finalizar una mesa aun no iniciada");
    }

    @Override
    public void nuevaRonda() throws MesaException {
        throw new MesaException("No se puede comenzar una ronda, la mesa aun no inicio");
    }
    
    public boolean jugadoresRequeridos(){
        Mesa contexto = this.getContexto();
        return contexto.getCantidadJugadoresRequeridos() - contexto.getCantidadJugadores() > 0;
    }

}
