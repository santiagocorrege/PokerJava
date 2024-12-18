/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import excepciones.JugadorException;

/**
 *
 * @author Ryzen
 */
public class Jugador extends Usuario{
    private float saldo;
    private Mesa mesaActual;
    private ManoJugador mano;
    
    public Jugador(String cedula, String password, String nombreCompleto, float saldo) {
        super(cedula, password, nombreCompleto);
        this.saldo = saldo;
    }

    public boolean validarSaldo(float monto) {
        if(this.saldo < monto){
            return false;
        }        
        return true;
    }
    
    public void validarIngreso() throws JugadorException{
        if(this.mesaActual != null){
            throw new JugadorException("Ya pertenece a una mesa");            
        }
    }

    public void retirarSaldo(float cantidad){
        this.saldo = this.saldo - cantidad;
    }
    
    public void cargarSaldo(float cantidad){
        this.saldo = this.saldo + cantidad;
    }
    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public void ingresarMesa(Mesa m){
        mesaActual = m;
    }
    
    public void salirMesa() throws Exception{
        this.mesaActual = null;        
        avisar(JugadorRetirado.Finalizada);
    }            
    
    public void retirado() throws Exception{ 
        this.mesaActual = null;
        avisar(JugadorRetirado.SaldoInsuficiente);
    }  
        
    public Mesa getMesaActual(){
        return this.mesaActual;
    }
  
    public void setManoJugador(ManoJugador m){
        this.mano = m;
    }

    public ManoJugador getMano() {
        return mano;
    }

    public void setMano(ManoJugador mano) {        
        this.mano = mano;       
    }
    
      
    
    public enum JugadorRetirado{
        SaldoInsuficiente,   
        Finalizada
    }
}
