/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import dominio.Jugador;
import dominio.Mesa;
import dominio.Mesa.EstadoMesa;
import java.util.ArrayList;
import observador.Observable;
import observador.Observador;

/**
 *
 * @author Ryzen
 */
public class ServicioMesas extends Observable implements Observador{
    private ArrayList<Mesa> mesas;
    
    private int numeroMesa;
    
    public ServicioMesas(){
        this.mesas = new ArrayList<>();
    }        
    
    public void agregar(Mesa mesa) throws Exception{
        mesa.validar();
        mesa.setNumero(numeroMesa);
        numeroMesa++;
        mesas.add(mesa);
        mesa.agregarObservador(this);
        //Pedir estado a la mesa
        avisar(EstadoMesa.Abierta);
    }
    
    public void ingresarJugador(Jugador j, Mesa m) throws Exception{
        m.ingresarJugador(j);
    }
    
    public ArrayList<Mesa> getMesas(){
        return this.mesas;
    }
    
    public ArrayList<Mesa> getMesasAbiertas(){
        ArrayList<Mesa> mesas = new ArrayList<>();
        for(Mesa m : getMesas()){
            if(m.getEstado().equals(Mesa.EstadoMesa.Abierta)){
                mesas.add(m);
            }
        }
        return mesas;
    }

    public float getRecaudacionTotal(){
        float monto = 0; 
        for(Mesa m : getMesas()){
            monto += m.getTotalRecaudado();
        }
        return monto;
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) throws Exception {        
        avisar(evento);
    }
            
}
