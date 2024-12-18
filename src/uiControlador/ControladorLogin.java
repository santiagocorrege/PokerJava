/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiControlador;

import dominio.Usuario;
import excepciones.LoginException;
import servicios.Fachada;
import uiInterfacesVistas.ILoginVista;

/**
 *
 * @author Ryzen
 */
public abstract class ControladorLogin {
    protected ILoginVista vista;
    protected Fachada modelo;
    
    public ControladorLogin(ILoginVista vista, Fachada fachada){
        this.vista = vista;
        this.modelo = fachada;
    }
    
    public void login(String ci, String pass) throws Exception, LoginException{
        try{
            Usuario u = modelo.login(ci, pass);
            if(u != null){
                validarTipoUsuario(u);
                vista.cerrar();
                vista.ejecutarSiguienteCU(u);                
            }else{
                vista.error("Usuario y/o contrasena incorrectos");
            }                        
        }catch(LoginException e){
            vista.error(e.getMessage());
        }                
    }        
    
    public abstract void validarTipoUsuario(Usuario usuario) throws LoginException;
    
}
