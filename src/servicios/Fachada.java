/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dominio.Jugador;
import dominio.ManoJugador;
import dominio.Mesa;
import dominio.Usuario;
import dominio.figuras.TipoFigura;
import excepciones.LoginException;
import java.util.ArrayList;
import observador.Observable;
import observador.Observador;


public class Fachada extends Observable implements Observador{
    private static final Fachada instancia = new Fachada();
    private ServicioUsuarios su;
    private ServicioMesas sm;
    private ServicioFiguras sf;
    
    private Fachada(){
        su = new ServicioUsuarios();        
        sm = new ServicioMesas();
        sf = new ServicioFiguras();
        sm.agregarObservador(this);
    }
    
    public static Fachada getInstancia(){
        return instancia;
    }
    
    public Usuario login(String ci, String pass) throws Exception, LoginException {
        return su.login(ci, pass);
    }
    
    public void logout(Usuario u){
        su.logout(u);
    }
    
    public ArrayList<TipoFigura> getTiposFiguras(){
        return sf.getTiposFiguras();
    }
    
    public ManoJugador figuraGanadora(ArrayList<ManoJugador> manos){
        return sf.figuraGanadora(manos);
    }
    
    public void agregar(Usuario user){
        su.agregar(user);
    }
    
    public void agregar(Mesa mesa) throws Exception{
        sm.agregar(mesa);
    }
    
    public void agregar(Jugador j, Mesa m) throws Exception{
        sm.ingresarJugador(j, m);
    }
    
    public ArrayList<Mesa> getMesas(){
        return sm.getMesas();
    }
    
    public ArrayList<Mesa> getMesasAbiertas(){
        return sm.getMesasAbiertas();
    }

    public void getFigura(ManoJugador mano){
        sf.setFigura(mano);
    }
    
    public float getRecaudacionTotal(){
        return sm.getRecaudacionTotal();
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) throws Exception{        
        avisar(evento);
    }
    
    
    
}
