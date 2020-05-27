package com.mycompany.sabanago.modelo;

import java.math.BigInteger;

/**
 *
 * @author Camil
 */
public class Sesion {
    private BigInteger primo;
    private Usuario user;
    private String ip;



    public Sesion(BigInteger primo, Usuario user, String ip) {
        this.primo = primo;
        this.user = user;
        this.ip = ip;
    }

    public BigInteger getPrimo() {
        return primo;
    }

    public Usuario getUser() {
        return user;
    }

    public String getIp() {
        return ip;
    }
    
    

    @Override
    public String toString() {
        return "Sesion{" + "primo=" + primo + ", user=" + user +   "ip=" + ip +'}';
    }

}