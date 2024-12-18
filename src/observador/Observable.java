/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observador;

import java.util.ArrayList;

/**
 *
 * @author Dario
 */
public class Observable {
    public ArrayList<Observador> observadores = new ArrayList();
    
    public void agregarObservador(Observador obs){
        if(!observadores.contains(obs)){
            observadores.add(obs);
        }
    }
    public void quitarObservador(Observador obs){
        observadores.remove(obs);
    }
    public void avisar(Object evento) throws Exception{
        for(int x=observadores.size()-1;x>=0;x--){
            observadores.get(x).actualizar(this, evento);
        }
    }
}
