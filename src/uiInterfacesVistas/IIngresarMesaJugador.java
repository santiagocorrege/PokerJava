/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uiInterfacesVistas;

import dominio.Jugador;
import dominio.Mesa;
import java.util.ArrayList;

/**
 *
 * @author Ryzen
 */
public interface IIngresarMesaJugador {
    public void ejecutarSiguienteCU(Jugador j, Mesa m);
    public void actualizarMesas(ArrayList<Mesa> mesas);
    public void actualizarUsuario(Jugador j);
    public void error(String mensaje);
    public void cerrar();    
    public void logout();    
}
