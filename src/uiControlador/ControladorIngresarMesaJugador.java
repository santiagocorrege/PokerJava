/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiControlador;

import dominio.Jugador;
import dominio.Jugador.JugadorRetirado;
import dominio.Mesa;
import dominio.Mesa.EstadoMesa;
import excepciones.MesaException;
import java.util.ArrayList;
import observador.Observable;
import observador.Observador;
import servicios.Fachada;
import uiInterfacesVistas.IIngresarMesaJugador;

/**
 *
 * @author Ryzen
 */
public class ControladorIngresarMesaJugador implements Observador {
    private IIngresarMesaJugador vista;
    private Jugador modelo;
    private Mesa mesa;

    public ControladorIngresarMesaJugador(IIngresarMesaJugador vista, Jugador j){
        Fachada.getInstancia().agregarObservador(this);
        j.agregarObservador(this);
        this.vista = vista;
        this.modelo = j;     
        actualizarMesas();
        actualizarJugador();
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) {          
        if(evento.equals(EstadoMesa.Iniciada) || evento.equals(EstadoMesa.Abierta) || evento.equals(EstadoMesa.Finalizada) || evento.equals(EstadoMesa.CambioJugadores)){
            actualizarMesas();            
            actualizarJugador();  
        }
        if(origen.equals(modelo) && evento.equals(JugadorRetirado.SaldoInsuficiente)){              
            vista.error("No tienes saldo para continuar jugando");            
        }                
        if(origen.equals(modelo) && evento.equals(JugadorRetirado.Finalizada) && mesa.getEstado().equals(EstadoMesa.Finalizada)){            
            vista.error("La mesa ha finalizado");
        }
    }
    
    public void actualizarMesas(){
        ArrayList<Mesa> mesas = Fachada.getInstancia().getMesasAbiertas();
        vista.actualizarMesas(mesas);
    }

    public void actualizarJugador(){
        vista.actualizarUsuario(modelo);
    }
    
    public void ingresarMesa(Mesa m) throws MesaException{
        vista.error("");
        try{
            m.ingresarJugador(modelo);
            this.mesa = m;           
            vista.ejecutarSiguienteCU(modelo, m);             
        }catch(MesaException me){
            vista.error(me.getMessage());
        }catch(Exception e){
            vista.error(e.getMessage());
        }
    }
    
    public void logout(){
        if(modelo.getMesaActual() == null){
            Fachada f = Fachada.getInstancia();
            f.quitarObservador(this);
            f.logout(modelo);
            vista.cerrar();
        }else{
            vista.error("Es parte de una mesa aun");
        }

    }
}
