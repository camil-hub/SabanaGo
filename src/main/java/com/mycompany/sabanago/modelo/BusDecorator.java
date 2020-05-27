package com.mycompany.sabanago.modelo;

abstract class BusDecorator implements Transporte{
    protected Transporte specialBus; 

    public BusDecorator(Transporte specialBus) {
        this.specialBus = specialBus;
    }
    
    @Override
    public String showTransport() {
        return specialBus.showTransport();
    }
}
