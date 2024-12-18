/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiInterfacesVistas;

import dominio.Usuario;

/**
 *
 * @author Ryzen
 */
public interface ILoginVista {
    public void cerrar();
    
    public void error(String mensaje);
    
    public void ejecutarSiguienteCU(Usuario usuario);
}
