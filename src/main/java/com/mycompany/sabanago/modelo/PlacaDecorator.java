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
public class PlacaDecorator extends BusDecorator{
    private String Placa;
    public PlacaDecorator(String placa,Transporte specialBus) {
        super(specialBus);
        this.Placa=placa;
    }
   public String showTransport(){
        return specialBus.showTransport() + addPlaca(); 
     }
    private  String addPlaca(){
        return " | placa " +Placa;
    }
    

}
