/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import dominio.Ronda.EstadoRondaTipo;
import dominio.estadoMesa.EstadoAbierta;
import dominio.estadoMesa.EstadoFinalizada;
import dominio.estadoMesa.IEstadoMesa;
import excepciones.MasoException;
import excepciones.MesaException;
import java.util.ArrayList;
import observador.Observable;
import observador.Observador;

/**
 *
 * @author Ryzen
 */
public class Mesa extends Observable implements Observador{
    private int numero;
    private int cantidadJugadoresRequeridos;
    private float luz;
    private float comision;
    private IEstadoMesa estadoMesa;
    private ArrayList<Ronda> rondas;
    private Ronda rondaActual;    
    private ArrayList<Jugador> participantes;    
    private Maso maso = new Maso();
    private float totalRecaudado = 0;
    private float totalApostado = 0;    

    public Mesa(int maximaCantidadJugadores, float luz, float comision) throws Exception, MesaException{
        this.cantidadJugadoresRequeridos = maximaCantidadJugadores;
        this.luz = luz;
        this.comision = comision;                
        this.rondas = new ArrayList<>();
        this.participantes = new ArrayList<>();   
        this.estadoMesa = new EstadoAbierta(this);
        abrirMesa();      
    }
    
    public void validar() throws MesaException{        
        if(cantidadJugadoresRequeridos < 2 || cantidadJugadoresRequeridos > 5){
            throw new MesaException("Cantidad de jugadores invalida");
        }
        if(luz < 1 ){
            throw new MesaException("Apuesta base invalida");
        }
        
        if(comision < 1 || comision > 50){
            throw new MesaException("Comision invalida");
        }
    }

    //TODO: CUIDAO
    public void setEstadoMesa(IEstadoMesa estadoMesa) {        
        this.estadoMesa = estadoMesa;               
    }           

    @Override
    public void actualizar(Observable origen, Object evento) throws Exception{
        avisar(evento);
        if(origen.equals(this.rondaActual)){
            if(evento.equals(EstadoRondaTipo.JugadoresConfirmados) && origen.equals(rondaActual)){              
                estadoMesa.nuevaRonda();                 
            }
            if(evento.equals(ManoJugador.EstadoManoJugador.Irse)){
                if(this.rondaActual.getJugadores().size() < 2){
                    this.estadoMesa = new EstadoFinalizada(this);
                    estadoMesa.finalizarMesa();                    
                }
            }
        }                
    }
    
    public enum EstadoMesa{
        Abierta,
        Iniciada,
        Finalizada,
        CambioJugadores,                
    }      
    
    //STRATEGY MESA
    public void ingresarJugador(Jugador jugador) throws Exception, MesaException{
        estadoMesa.ingresarJugador(jugador);
    }            
    
    public void quitarJugador(Jugador j) throws Exception, MesaException{
        estadoMesa.quitarJugador(j);
    }
    
    public void iniciarMesa() throws Exception, MesaException{        
        estadoMesa.iniciarMesa();
    }
    
    public void abrirMesa() throws Exception, MesaException {
        estadoMesa.abrirMesa();
    }
    
    public void finalizarMesa() throws Exception, MesaException{
        estadoMesa.finalizarMesa();
    }
        
    //MAS   
    public void nuevaRonda() throws MasoException, MesaException, Exception{        
        estadoMesa.nuevaRonda();
    }        
    
    public void agregarRonda(Ronda r){
        this.rondas.add(r);
    }
   
    public ArrayList<Ronda> getRondas() {
        return rondas;
    }

    //PROPERTIES
    

    public Maso getMaso() {
        return maso;
    }

    public void setMaso(Maso maso) {
        this.maso = maso;
    }

    public Ronda getRondaActual() {
        return rondaActual;
    }

    public void setRondaActual(Ronda rondaActual) {
        this.rondaActual = rondaActual;
    }
    
    public float getTotalRecaudado() {
        return totalRecaudado;
    }

    public void setTotalRecaudado(float totalRecaudado) {
        this.totalRecaudado = totalRecaudado;
    }

    public float getTotalApostado() {
        return totalApostado;
    }

    public void setTotalApostado(float totalApostado) {
        this.totalApostado = totalApostado;
    }    

    public ArrayList<Jugador> getParticipantes() {
        return participantes;
    }

    public EstadoMesa getEstado(){
        return this.estadoMesa.getEstado();
    }

    public float getLuz() {
        return luz;
    }

    public void setLuz(float luz) {
        this.luz = luz;
    }

    public float getComision() {
        return comision;
    }

    public void setManoActual(Ronda manoActual) {
        this.rondaActual = manoActual;
    }

        public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCantidadJugadoresRequeridos() {
        return cantidadJugadoresRequeridos;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadoresRequeridos = cantidadJugadores;
    }
    
    public int getCantidadJugadores(){
        return participantes.size();
    }
    
    public float getSaldoMinimoIngreso(){
        return luz * 10;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.numero;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mesa other = (Mesa) obj;
        return this.numero == other.numero;
    }
    
    
    
}
