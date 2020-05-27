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
public class HoraDecorator extends BusDecorator{
    
        private String hora;
    public HoraDecorator(String horaP,Transporte specialBus) {
       super(specialBus);
       hora=horaP;
    }
     public String showTransport(){
         return specialBus.showTransport() + addCupos();
     }

    private String addCupos() {
        return " | " + hora + " salida";
    }
    
}
