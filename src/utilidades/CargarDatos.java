/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import dominio.Administrador;
import dominio.Carta;
import dominio.Jugador;
import dominio.ManoJugador;
import dominio.Maso;
import dominio.Mesa;
import dominio.Palo;
import dominio.estadoMesa.EstadoFinalizada;
import java.util.ArrayList;
import servicios.Fachada;

/**
 *
 * @author Ryzen
 */
public class CargarDatos {

    public static void ejecutar() throws Exception{
        Fachada f = Fachada.getInstancia();

        Administrador admin1 = new Administrador("100", "100", "A 100");
        Administrador admin2 = new Administrador("200", "101", "A 200");
        
        Jugador j0 = new Jugador("0", "0", "J0", 0);
        Jugador j1 = new Jugador("1", "1", "J1", 1000);
        Jugador j2 = new Jugador("2", "2", "J2", 2000);
        Jugador j3 = new Jugador("3", "3", "J3", 3000);
        Jugador j4 = new Jugador("4", "4", "J4", 4000);
        Jugador j5 = new Jugador("5", "5", "J5", 5000);
        Jugador j6 = new Jugador("6", "6", "J6", 6000);
        Jugador j7 = new Jugador("7", "7", "J7", 7000);
        Jugador j8 = new Jugador("8", "8", "J8", 8000);
        Jugador j9 = new Jugador("9", "9", "J9", 9000);

        f.agregar(admin1);
        f.agregar(admin2);
        f.agregar(j0);
        f.agregar(j1);
        f.agregar(j2);
        f.agregar(j3);
        f.agregar(j4);
        f.agregar(j5);
        f.agregar(j6);
        f.agregar(j7);
        f.agregar(j8);
        f.agregar(j9);
        
        Mesa m = new Mesa(2, 5, 5);
        Mesa m1 = new Mesa(2, 5, 5);
        Mesa m2 = new Mesa(3, 5, 5);
        Mesa m3 = new Mesa(3, 5, 5);
        Mesa m4 = new Mesa(4, 5, 5);
        Mesa m5 = new Mesa(4, 5, 5);
        Mesa m6 = new Mesa(4, 5, 5);                
        f.agregar(m);                     
        f.agregar(m1);        
        f.agregar(m2);
        f.agregar(m3);
        f.agregar(m4);
        f.agregar(m5);        
    }     
    
    public static void TestEscalera(){
        Fachada f = Fachada.getInstancia();
        Palo[] p = {new Palo("C", 4), new Palo("D", 3), new Palo("T", 2), new Palo("P",1)}; 
        Maso ma = new Maso();          
        Carta c1 = new Carta(4, p[0]);
        Carta c2 = new Carta(5, p[1]);
        Carta c3 = new Carta(6, p[0]);
        Carta c4 = new Carta(7, p[2]);
        Carta c5 = new Carta(8, p[1]);
        ArrayList<Carta> escalera =  new ArrayList<>();
        escalera.add(c1);
        escalera.add(c2);
        escalera.add(c3);
        escalera.add(c4);
        escalera.add(c5);
        Jugador j1 = new Jugador("11", "11", "J11", 1000);
        ManoJugador mj = new ManoJugador(j1);
        j1.setMano(mj);
        mj.setCartas(escalera);
        f.getFigura(mj);
        System.out.println(mj.getFigura().getNombre());
    }
    
}
