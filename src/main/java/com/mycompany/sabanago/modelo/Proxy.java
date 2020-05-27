package com.mycompany.sabanago.modelo;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camil
 */
public class Proxy implements IFolder{
    private static Proxy instance;
    
    private HashMap<String,Usuario> usuarios;
    Facade facade;

    public Proxy(){
        usuarios=new HashMap<String,Usuario>();
    }
    
    
    public static Proxy reemplazarConstructor(){
        if(instance ==null)
            instance=new Proxy();
        return instance;
    }
    public void addUsuario(Usuario user){
        this.usuarios.put(user.getLogin(), user);
    }

    @Override
    public BigInteger accederSistema(String login, String password, String myIP) throws ErrorC {
              
        if(usuarios.containsKey(login) && usuarios.get(login).getPassword().equals(password)){
            BigInteger primo;
            int bitLength = 31;
            Random rnd = new Random();
            primo = BigInteger.probablePrime(bitLength, rnd);
           
            InetAddress ip = null;
            try {
                ip = InetAddress.getLocalHost();
                //Sesion nueva =new Sesion(primo, usuarios.get(login), ip.toString());

                Sesion nueva =new Sesion(primo, usuarios.get(login), myIP);
                facade= Facade.reemplazarConstructor();
                facade.registrarSesion(nueva);
                facade.modificarUsuario(usuarios.get(login));
                
            } catch (UnknownHostException ex) {
                Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return primo;
        }
        throw new ErrorC("Usuario o Contraseña equivocada");
    }
}
