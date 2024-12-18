/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dominio.Jugador;
import dominio.Usuario;
import servicios.Fachada;
import uiControlador.ControladorLogin;
import uiControlador.ControladorLoginJugador;
import uiInterfacesVistas.ILoginVista;

/**
 *
 * @author Ryzen
 */
public class LoginJugadorVista extends LoginVista{

    public LoginJugadorVista(){
        setTitle("Login Jugador");
    }
    
    @Override
    public void login(String ci, String password) throws Exception{
        this.controlador.login(ci, password);
    }

    @Override
    public void ejecutarSiguienteCU(Usuario usuario) {        
        Jugador jugador = (Jugador) usuario;
        IngresarMesaVista imv = new IngresarMesaVista(jugador); 
        imv.setLocationRelativeTo(null);
        imv.setVisible(true);
    }

    @Override
    protected ControladorLogin crearControlador(ILoginVista vista, Fachada fachada) {
        return new ControladorLoginJugador(this, Fachada.getInstancia());
    }
    
}
