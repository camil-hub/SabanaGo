/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sabanago.modelo;

/**
 *
 * @author Afeli
 */
public class CostoDecorator extends BusDecorator{
    private int costo;
    public CostoDecorator(int costoP,Transporte specialBus) {
       super(specialBus);
       costo=costoP;
    }
     public String showTransport(){
         return specialBus.showTransport() + addCupos();
     }

    private String addCupos() {
        return " | $" + costo + " de costo";
    }
}
