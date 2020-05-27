package com.mycompany.sabanago.modelo;

import java.rmi.RemoteException;

public class ErrorC extends RemoteException {

    private String error;
    public ErrorC(String msg){
        this.error = msg;
    }

    @Override
    public String toString() {
        return error;
    }
}
