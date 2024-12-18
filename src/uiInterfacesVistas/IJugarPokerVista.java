/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uiInterfacesVistas;

import dominio.Jugador;
import dominio.Mesa;
import dominio.Ronda;
import dominio.figuras.TipoFigura;
import java.util.ArrayList;
import panelCartasPoker.CartaPoker;
import panelCartasPoker.PanelCartasListener;

/**
 *
 * @author Ryzen
 */
public interface IJugarPokerVista extends PanelCartasListener {
    public void actualizarJugador(Jugador j);
    public void actualizarMesa(Mesa m);
    public void actualizarSaldo(float saldo);
    public void actualizarNumeroRonda(int numero);
    public void actualizarEstadoRonda(String estado);
    public void actualizarPozo(Float monto);
    public void actualizarFigura(String figura);
    public void actualizarGanador(String ganador);
    public void actualizarApostado(Float apostado);
    public void actualizarJugadores(ArrayList<String> jugadores);
    public void actualizarFigurasActuales(ArrayList<TipoFigura> figuras);
    public void cargarCartas(ArrayList<CartaPoker> cartas);
    public void habilitarPanel(boolean habilitar);    
    public void error(String mensaje);    
    public void cerrar();    
    public void irse();
}
