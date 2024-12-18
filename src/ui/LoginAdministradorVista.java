/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dominio.Administrador;
import dominio.Usuario;
import servicios.Fachada;
import uiControlador.ControladorLogin;
import uiControlador.ControladorLoginAdministrador;
import uiInterfacesVistas.ILoginVista;

/**
 *
 * @author Ryzen
 */
public class LoginAdministradorVista extends LoginVista{
    
    public LoginAdministradorVista(){        
        setTitle("Login Admin");
    }
    
    @Override
    public void login(String ci, String password) throws Exception{
        this.controlador.login(ci, password);
    }

    @Override
    public void ejecutarSiguienteCU(Usuario usuario) {                   
        Administrador admin = (Administrador) usuario;
        AdministrarMesasVista amv = new AdministrarMesasVista(admin); 
        amv.setLocationRelativeTo(null);
        amv.setVisible(true);
    }

    @Override
    protected ControladorLogin crearControlador(ILoginVista vista, Fachada fachada) {
        return new ControladorLoginAdministrador(this, Fachada.getInstancia());
    }
    
}
