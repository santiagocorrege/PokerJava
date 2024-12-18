
import ui.InicioVista;
import utilidades.CargarDatos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ryzen
 */
public class Main {
    
    public static void main(String[] args) {  

        try{
            CargarDatos.ejecutar();        
            InicioVista iv = new InicioVista();
            iv.setLocationRelativeTo(null);
            iv.setVisible(true);
            
        }catch(Exception e){
            System.out.println("Chequeo: " + e.getMessage());
        }
        
    }
}
