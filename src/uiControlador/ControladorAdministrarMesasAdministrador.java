/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiControlador;

import dominio.Administrador;
import dominio.Mesa;
import dominio.Mesa.EstadoMesa;
import java.util.ArrayList;
import observador.Observable;
import observador.Observador;
import servicios.Fachada;
import uiInterfacesVistas.IAdministrarMesasAdministrador;

/**
 *
 * @author Ryzen
 */
public class ControladorAdministrarMesasAdministrador implements Observador {
    private IAdministrarMesasAdministrador vista;
    private Administrador modelo;
    private Fachada f;

    public ControladorAdministrarMesasAdministrador(IAdministrarMesasAdministrador vista, Administrador j){
        this.f = Fachada.getInstancia();
        f.agregarObservador(this);
        this.vista = vista;
        this.modelo = j;   
        actualizarMesas();
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) {            
        actualizarMesas();        
    }
    
    public void actualizarMesas(){        
        ArrayList<Mesa> mesas = Fachada.getInstancia().getMesas();        
        vista.actualizarMesas(mesas);
        vista.actualizarRecaudado(f.getRecaudacionTotal());
    }
    
    public void verMesa(Mesa m){
        if(m.getEstado().equals(EstadoMesa.Abierta)){
            vista.error("La mesa no se inicio aun");
        }else{           
            vista.ejecutarVerMesa(m);
        }
    }
    
    public void logout(){
        f.logout(modelo);
        f.quitarObservador(this);
        vista.cerrar();
    }
    
    public void crearMesa(){
        vista.ejecutarCrearMesa();
    }

}
