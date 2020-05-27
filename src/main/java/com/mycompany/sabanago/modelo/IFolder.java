package com.mycompany.sabanago.modelo;

import java.math.BigInteger;


/**
 *
 * @author Camil
 */
public interface IFolder {
    public BigInteger accederSistema(String login, String password, String myIP) throws ErrorC;
}
