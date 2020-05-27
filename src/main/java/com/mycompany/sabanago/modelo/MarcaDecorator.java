package com.mycompany.sabanago.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Camil
 */
public class MarcaDecorator extends BusDecorator {
    private String Marca;
    public MarcaDecorator(String marca,Transporte specialBus) {
        super(specialBus);
        this.Marca=marca;
    }
    
    public String showTransport(){
        return specialBus.showTransport() + addMarca();
    }

    private String addMarca() {
        return " | marca " + Marca;

    }
    
}
