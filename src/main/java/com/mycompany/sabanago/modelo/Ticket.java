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
public class Ticket {
    private int id;
    private Transporte transporte;

    public Ticket(int id, Transporte transporte) {
        this.id = id;
        this.transporte = transporte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", transporte=" + transporte + '}';
    }
    
    
    
}
