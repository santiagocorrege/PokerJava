/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.estadoMesa;

import dominio.Jugador;
import dominio.Mesa;
import excepciones.JugadorException;
import excepciones.MesaException;

/**
 *
 * @author Ryzen
 */
public class EstadoFinalizada extends IEstadoMesa{

    public EstadoFinalizada(Mesa m) throws MesaException, Exception {
        super(m, Mesa.EstadoMesa.Finalizada);        
    }
    @Override
    public void ingresarJugador(Jugador j) throws MesaException {
        throw new MesaException("No es posible ingresar jugador, La mesa ha finalizado");
    }

    @Override
    public void quitarJugador(Jugador j) throws MesaException {
        throw new MesaException("No es posible quitar jugador, La mesa ha finalizado");
    }

    @Override
    public void iniciarMesa() throws MesaException {
        throw new MesaException("No es posible iniciar mesa, La mesa ha finalizado");
    }

    @Override
    public void abrirMesa() throws MesaException {
        throw new MesaException("No es posible abrir mesa, La mesa ha finalizado");
    }

    @Override
    public void finalizarMesa() throws MesaException, JugadorException, Exception {        
        Mesa contexto = this.getContexto();     
        cobrarRonda();
        for(Jugador j : contexto.getParticipantes()){            
            j.salirMesa();
        }        
        contexto.getParticipantes().clear();
        contexto.avisar(this.getEstado());
    }

    @Override
    public void nuevaRonda() throws MesaException {
        throw new MesaException("La mesa ha finalizado");
    }
    
}
