/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiControlador;

import dominio.Mesa;
import excepciones.MesaException;
import servicios.Fachada;
import uiInterfacesVistas.IDialogoCrearMesa;

/**
 *
 * @author Ryzen
 */
public class ControladorCrearMesaAdministrador {
    private IDialogoCrearMesa vista;
    private Fachada modelo;
    
    public ControladorCrearMesaAdministrador(IDialogoCrearMesa vista) {
        this.vista = vista;
        this.modelo = Fachada.getInstancia();
    }
    
    public void crearMesa(Mesa mesa) throws Exception, MesaException{
        try{            
            modelo.agregar(mesa);
            vista.error("Mesa creada");
        }catch(MesaException me){            
            vista.error(me.getMessage());
        }catch(Exception e){
            vista.error(e.getMessage());
        }
    }
}
