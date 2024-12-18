/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiControlador;

import dominio.Carta;
import dominio.Jugador;
import dominio.ManoJugador;
import dominio.Mesa;
import dominio.Mesa.EstadoMesa;
import dominio.Ronda;
import dominio.Ronda.EstadoRondaTipo;
import dominio.figuras.TipoFigura;
import java.util.ArrayList;
import observador.Observable;
import observador.Observador;
import panelCartasPoker.CartaPoker;
import servicios.Fachada;
import uiInterfacesVistas.IJugarPokerVista;

/**
 *
 * @author Ryzen
 */
public class ControladorJugarPoker implements Observador{
    private IJugarPokerVista vista;
    private Jugador jugador;
    private ManoJugador mj;
    private Ronda ronda;
    private Mesa mesa;
    private ArrayList<Carta> cambiarCartas = new ArrayList<Carta>();
    private ArrayList<TipoFigura> figurasActuales;
    
    public ControladorJugarPoker(IJugarPokerVista vista, Jugador j, Mesa m){
        this.vista = vista;
        this.jugador = j;        
        this.mesa = m;        
        this.figurasActuales = Fachada.getInstancia().getTiposFiguras();
        mesa.agregarObservador(this);
        cargarMesa(); 
    }

    @Override
    public void actualizar(Observable origen, Object evento) {                            
        if(origen.equals(mesa)){            
            cargarMesa();                     
            if(evento.equals(EstadoRondaTipo.JugadoresConfirmados)){                        
                vista.actualizarGanador("");                  
            }
            if(evento.equals(EstadoRondaTipo.PidiendoCartas)){                
                vista.habilitarPanel(true);            
            }
            if(evento.equals(EstadoRondaTipo.Terminada)){
                vista.actualizarEstadoRonda(String.valueOf(evento));
                if(ronda.getGanador() != null){
                    if(ronda.getGanador().equals(mj)){
                        vista.actualizarGanador("Ganaste $" +ronda.getMontoGanado());
                    }else{
                        vista.actualizarGanador("Gano: " + ronda.getGanador().getJugador().getNombreCompleto() + " con " + ronda.getGanador().getFigura().getNombre());
                    }
                }
            }
            if(evento.equals(EstadoMesa.Finalizada)){
                vista.cerrar();
            }
        }

    }    
       
    public void cargarMesa(){     
        //Cuidado
        vista.error("");
        vista.habilitarPanel(false);
        vista.actualizarJugador(jugador);
        vista.actualizarMesa(mesa);   
        vista.actualizarSaldo(jugador.getSaldo());   
        vista.actualizarFigurasActuales(figurasActuales);
        if(mesa.getEstado().equals(EstadoMesa.Abierta)){  
            cargarJugadoresPendientes();            
        }else if(mesa.getEstado().equals(EstadoMesa.Iniciada)){                        
            cargarRonda();
        }        
    }
    
    public void cargarRonda(){                    
        ronda = mesa.getRondaActual();                                           
        vista.actualizarEstadoRonda(ronda.getEstadoRondaTipo().toString());        
        vista.actualizarNumeroRonda(ronda.getNumeroMano());          
        vista.actualizarPozo(ronda.getPozo());        
        cargarJugadores();
        cargarCartas();
    }
        
    public void cargarCartas(){
        mj = jugador.getMano();  
        ArrayList<CartaPoker> cp = new ArrayList<CartaPoker>();        
        for(Carta c : mj.getCartas()){            
            c.setVisible(true);
            cp.add(c);                     
        }             
        vista.cargarCartas(cp);  
        vista.actualizarFigura(mj.getFigura().getNombre());
        vista.actualizarApostado(mj.getApuesta());
    }
    
    public void cargarJugadores(){
        ArrayList<Jugador> jugadores = ronda.getJugadores();
        ArrayList<String> listado = new ArrayList<String>();
        for(Jugador j : jugadores){
            ManoJugador mj = j.getMano();
            listado.add(j.getNombreCompleto() + " - " + String.valueOf(mj.getEstado()));
        }        
        vista.actualizarJugadores(listado);
    }
    public void cargarJugadoresPendientes(){                                                              
        vista.actualizarEstadoRonda("Esperando inicio del juego, hay " + mesa.getCantidadJugadores() + " jugadores de " + mesa.getCantidadJugadoresRequeridos() +" en la mesa");
    }
        
    
    public void agregarCartaCambiar(CartaPoker cp){        
        Carta c = (Carta) cp;        
        if(!cambiarCartas.contains(c)){
            cambiarCartas.add(c);
        }
    }
    
    public void iniciarApuesta(float cantidad){        
        try{ 
            if(cantidad > 0){                
                this.ronda.apuestaIniciada(mj, cantidad);                
            }            
        }catch(NullPointerException e){
            vista.error("Mesa no iniciada"); 
        }
        catch(Exception e){
            vista.error(e.getMessage());
        }        
    }
    
    public void pedirCartas(){
        try{    
            this.ronda.pedirCartas(mj, cambiarCartas);
            this.cambiarCartas.clear();
        }catch(NullPointerException e){
            vista.error("Mesa no iniciada"); 
        }catch(Exception e){
            vista.error(e.getMessage());
        }
    }
        
    public void pasar(){
        try{ 
            this.ronda.jugadorPasa(mj);
        }catch(NullPointerException e){
            vista.error("Mesa no iniciada"); 
        }catch(Exception e){
            vista.error(e.getMessage());
        }
    }
    
    public void pagarApuesta(){
        try{ 
            this.ronda.apuestaPagada(mj);
        }catch(NullPointerException e){
            vista.error("Mesa no iniciada"); 
        }catch(Exception e){
            vista.error(e.getMessage());
        }
    }
    
    public void jugarOtraRonda(){        
        try{ 
            this.ronda.jugarOtraRonda(mj);
        }catch(NullPointerException e){
            vista.error("Mesa no iniciada"); 
        }catch(Exception e){
            vista.error(e.getMessage());
        }
    }
    
        
    public void irse(){        
        try{            
            this.mesa.quitarJugador(jugador);                        
            mesa.quitarObservador(this);   
            vista.cerrar();                 
        }catch(NullPointerException e){
            vista.error("Mesa no iniciada"); 
        }catch(Exception e){            
            vista.error(e.getMessage());
        }
    }
                
}
