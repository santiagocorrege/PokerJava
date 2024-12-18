package servicios;

import dominio.Usuario;
import excepciones.LoginException;
import java.util.ArrayList;

class ServicioUsuarios {
    private ArrayList<Usuario> usuarios;    
    private ArrayList<Usuario> loggueados;
    
    public ServicioUsuarios(){
        this.usuarios = new ArrayList<>();
        this.loggueados = new ArrayList<>();
    }
    
    public Usuario login(String ci, String password) throws Exception, LoginException{
        Usuario usuarioLogueado = null;
        for(Usuario u : usuarios){
            if(u.getCedula().equals(ci)){  
                if(u.esPassword(password)){
                    if(!loggueados.contains(u)){
                        usuarioLogueado = u;         
                        loggueados.add(u);
                        break;
                    }else{
                        throw new LoginException("Acceso denegado el usuario ya esta loggueado");                
                    }
                }else{
                    throw new LoginException("Credenciales incorrectas");
                }    
            }
        }
        return usuarioLogueado;
    }
     
    public void logout(Usuario usuario){        
        for(Usuario u : usuarios){
            if(usuario.equals(u)){  
                loggueados.remove(u);
            }
        }              
    }
    
    public void agregar(Usuario usuario){        
        usuarios.add(usuario);        
    }
    
}
