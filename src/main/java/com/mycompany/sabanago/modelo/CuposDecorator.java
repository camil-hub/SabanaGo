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
public class CuposDecorator extends BusDecorator  {
    private int Cupos;
    public CuposDecorator(int cupos,Transporte specialBus) {
       super(specialBus);
       Cupos=cupos;
    }
     public String showTransport(){
         return specialBus.showTransport() + addCupos();
     }

    private String addCupos() {
        return " | " + Cupos + " cupos disponibles";
    }
    
}
