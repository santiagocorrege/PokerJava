/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dominio;

/**
 *
 * @author Ryzen
 */
public interface CartaPoker {

    //Valores validos de las cartas
    public static final int AS = 1;
    public static final int DOS = 2;
    public static final int TRES = 3;
    public static final int CUATRO = 4;
    public static final int CINCO = 5;
    public static final int SEIS = 6;
    public static final int SIETE = 7;
    public static final int OCHO = 8;
    public static final int NUEVE = 9;
    public static final int DIEZ = 10;
    public static final int J = 11;
    public static final int Q = 12;
    public static final int K = 13;
    
    //Valores validos de los "palos" de las cartas
    public static final String CORAZON = "C";
    public static final String DIAMANTE = "D";
    public static final String TREBOL = "T";
    public static final String PIQUE = "P";
    
    public int getValorCarta();

    public String getPaloCarta();
    
    public boolean estaVisible();
    
    public void setVisible(boolean b);
    
}
