/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiControlador;

import dominio.Jugador;
import dominio.Usuario;
import excepciones.LoginException;
import servicios.Fachada;
import uiInterfacesVistas.ILoginVista;

/**
 *
 * @author Ryzen
 */
public class ControladorLoginJugador extends ControladorLogin{

    public ControladorLoginJugador(ILoginVista vista, Fachada fachada) {
        super(vista, fachada);
    }

    @Override
    public void validarTipoUsuario(Usuario usuario) throws LoginException {
        if(!(usuario instanceof Jugador)){
            this.modelo.logout(usuario);
            throw new LoginException("Error de usuario y/o contrasena");
        }
    }
    
}
