/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiControlador;

import dominio.Mesa;
import observador.Observable;
import observador.Observador;
import uiInterfacesVistas.IDetallesMesa;

/**
 *
 * @author Ryzen
 */
public class ControladorDetallesMesa implements Observador{
    private IDetallesMesa vista;
    private Mesa modelo;
    
    public ControladorDetallesMesa(IDetallesMesa vista, Mesa mesa){
        this.vista = vista;
        this.modelo = mesa;
        mesa.agregarObservador(this);
    }

    @Override
    public void actualizar(Observable origen, Object evento) throws Exception {
        if(origen.equals(this.modelo)){
            vista.actualizarRondas(modelo.getRondas());
        }        
    }
    
    public void actualizarRondas(){
        vista.actualizarRondas(modelo.getRondas());
    }
    
    
}
