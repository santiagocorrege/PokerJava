/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;


import dominio.estadoRonda.EstadoEsperandoApuesta;
import dominio.estadoRonda.EstadoRonda;
import excepciones.MasoException;
import excepciones.RondaException;
import java.util.ArrayList;
import observador.Observable;

/**
 *
 * @author Ryzen
 */
public class Ronda extends Observable{    
    private int numeroMano;    
    private EstadoRonda estadoRonda;
    private float apuesta;
    private ManoJugador ganador;    
    private ArrayList<Jugador> jugadores;    
    private ArrayList<Jugador> vigentes;
    private Maso maso;
    private float luz;
    private float pozo;
    private float comision;
    private float montoGanado;

    public Ronda(int numeroMano, ArrayList<Jugador> jugadores, Maso m, float luz, float pozo, float comision) throws MasoException, Exception{        
        this.numeroMano = numeroMano;        
        this.maso = m;
        this.jugadores = jugadores;    
        this.vigentes = this.vigentes = new ArrayList<>(jugadores);
        this.estadoRonda = new EstadoEsperandoApuesta(this);            
        this.estadoRonda.repartiendoCartas();        
        this.luz = luz;
        this.pozo = pozo;
        this.comision = comision;
    }    
    
    public enum EstadoRondaTipo{
        EsperandoApuesta,
        ApuestaIniciada,
        PidiendoCartas,
        Terminada,  
        RepartiendoCartas,
        JugadoresConfirmados
    }    
        
    public void gano(){               
        float comision = this.pozo * this.comision / 100;
        float montoGanado = this.pozo - comision;
        ganador.pagarRonda(montoGanado);        
        this.montoGanado = montoGanado;
    }
    //STRATEGY
    
    public void apuestaIniciada(ManoJugador manoJugador, float monto) throws RondaException, Exception{  
        lanzarExcepcionSiNoParticipa(manoJugador);
        this.estadoRonda.apuestaIniciada(manoJugador, monto);
    }
    
    public void apuestaPagada(ManoJugador manoJugador) throws RondaException, Exception{
        lanzarExcepcionSiNoParticipa(manoJugador);
        this.estadoRonda.apuestaPagada(manoJugador);
    }
    
    public void jugadorPasa(ManoJugador manoJugador) throws RondaException, Exception{
        lanzarExcepcionSiNoParticipa(manoJugador);
        this.estadoRonda.jugadorPasa(manoJugador);
    }    
    
    public void pedirCartas(ManoJugador manoJugador, ArrayList<Carta> cartas) throws RondaException, Exception{
        lanzarExcepcionSiNoParticipa(manoJugador);
        this.estadoRonda.pedirCartas(manoJugador, cartas);
    }                
    
    public void rondaTerminada() throws RondaException, Exception{
        this.estadoRonda.terminada();
    }        
    
    public void irse(ManoJugador manoJugador) throws RondaException, Exception{        
        this.estadoRonda.irse(manoJugador);
    }    
    
    public void jugarOtraRonda(ManoJugador manoJugador) throws RondaException, Exception{        
        this.estadoRonda.jugarOtraRonda(manoJugador);
    }     
        
    public void lanzarExcepcionSiNoParticipa(ManoJugador mj) throws RondaException{
        if(!vigentes.contains(mj.getJugador())){
            throw new RondaException("Usted no participa de la ronda");
        }
    }    
    
    public void cobrarLuz(){
        for(Jugador j : this.jugadores){
            j.retirarSaldo(this.luz);
            this.pozo += this.luz;
        }
    }
          
    
    public void validarApuesta(float monto) throws RondaException{
        if(monto < 1){
            throw new RondaException("Apuesta minima $1");
        }
    }
    
    //PROPERTIES
    public ArrayList<Jugador> getVigentes() {
        return vigentes;
    }
    
    public void setVigentes(ArrayList<Jugador> vigentes) {
        this.vigentes = vigentes;
    }

    public float getMontoGanado() {
        return montoGanado;
    }

    public void setMontoGanado(float montoGanado) {
        this.montoGanado = montoGanado;
    }

    public EstadoRonda getEstadoRonda() {
        return estadoRonda;
    }
    
    public float getPozo() {
        return pozo;
    }

    public void setPozo(float pozo) {
        this.pozo = pozo;
    }    
    
    public void setEstadoRonda(EstadoRonda estadoRonda) {
        this.estadoRonda = estadoRonda;
    }

    public float getLuz() {
        return luz;
    }
    
    public void quitarVigente(ManoJugador mj){
        vigentes.remove(mj.getJugador());
    }   
    
    public void quitarJugador(ManoJugador mj){
        jugadores.remove(mj.getJugador());
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Maso getMaso() {
        return maso;
    }

    public void setMaso(Maso maso) {
        this.maso = maso;
    }

    public int getNumeroMano() {
        return numeroMano;
    }

    public void setNumeroMano(int numeroMano) {
        this.numeroMano = numeroMano;
    }

    public EstadoRondaTipo getEstadoRondaTipo() {
        return estadoRonda.getEstadoRonda();
    }
    

    public ManoJugador getGanador() {
        return ganador;
    }

    public void setGanador(ManoJugador ganador) {
        this.ganador = ganador;
    }

    public float getApuesta() {
        return apuesta;
    }

    public void setApuesta(float apuesta) {
        this.apuesta = apuesta;
    }
                 
}
