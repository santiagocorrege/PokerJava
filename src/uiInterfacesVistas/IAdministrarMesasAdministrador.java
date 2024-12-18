/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uiInterfacesVistas;

import dominio.Mesa;
import java.util.ArrayList;

/**
 *
 * @author Ryzen
 */
public interface IAdministrarMesasAdministrador {
    public void ejecutarVerMesa(Mesa mesa);
    public void ejecutarCrearMesa();
    public void actualizarMesas(ArrayList<Mesa> mesas);
    public void actualizarRecaudado(float cantidad);
    public void logout();
    public void cerrar();    
    public void error(String mensaje);
}
