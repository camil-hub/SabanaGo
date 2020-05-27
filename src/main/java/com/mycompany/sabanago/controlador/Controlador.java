package com.mycompany.sabanago.controlador;

import com.mycompany.sabanago.modelo.Facade;
import com.mycompany.sabanago.modelo.Usuario;
import com.mycompany.sabanago.vista.FormaUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import com.mycompany.sabanago.modelo.Encriptacion;
import com.mycompany.sabanago.modelo.ErrorC;
import com.mycompany.sabanago.modelo.Proxy;
import com.mycompany.sabanago.vista.FormaAdmin;
import com.mycompany.sabanago.vista.FormaBienvenido;
import com.mycompany.sabanago.vista.FormaRuta;

public class Controlador implements ActionListener {
    private Usuario nuevoUsuario;
    private Facade fa;
    private FormaUsuario forma;
    private Proxy miProxy;
    private BigInteger primo;
    private FormaBienvenido bienvenido;
    private FormaAdmin adminForma;
    private FormaRuta formaRuta;
    private String llamado;
    
   
    

    public Controlador(Usuario user, Facade fa, FormaUsuario forma, Proxy miProxy, FormaBienvenido bienvenido, FormaAdmin adminForma, FormaRuta formaRuta) {
        this.nuevoUsuario = user;
        this.fa = fa;
        this.forma = forma;
        this.miProxy = miProxy;
        this.primo = null;
        this.bienvenido = bienvenido;
        this.adminForma = adminForma;
        this.formaRuta = formaRuta;
        this.llamado = "";
        
        this.bienvenido.iniciarBButton.addActionListener(this);
        this.bienvenido.registrarBbutton.addActionListener(this);
        
        this.forma.eliminarButton.addActionListener(this);
        this.forma.limpiarButton.addActionListener(this);
        this.forma.modificarButton.addActionListener(this);
        this.forma.atrasButton.addActionListener(this);
        this.forma.mostrarRutasButton.addActionListener(this);
        this.forma.cancelarRutaButton.addActionListener(this);
        
        this.adminForma.crearRutaButton.addActionListener(this);
        this.adminForma.atrasButton.addActionListener(this);
        this.adminForma.mostrarbutton.addActionListener(this);
        this.adminForma.buscarbutton.addActionListener(this);
        this.adminForma.borrarRutaButton.addActionListener(this);
        
        this.formaRuta.crearRutaButton.addActionListener(this);
        this.formaRuta.atrasButton.addActionListener(this);

        
        
    }

    public void iniciar(){
        bienvenido.setTitle("Inicio");
        bienvenido.setLocationRelativeTo(null);
        forma.setTitle("Usuario");
        forma.setLocationRelativeTo(null);
        adminForma.setLocationRelativeTo(null);
        formaRuta.setLocationRelativeTo(null);
 
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
             
    
        
        if (e.getSource() == bienvenido.registrarBbutton){
            String login = bienvenido.usuarioBtxt.getText();
            String password = new String(bienvenido.passwordB.getPassword());
            String ip = bienvenido.iptxt.getText();
            nuevoUsuario = new Usuario("","","","","",login,password,"","");
            miProxy.addUsuario(nuevoUsuario);
            
            
            try{
            
            primo = miProxy.accederSistema(login, password, ip);
 
            if(bienvenido.radioUsuario.isSelected()){
                 forma.setVisible(true);
              

            }else if(bienvenido.radioConductor.isSelected()){
                adminForma.setVisible(true);
             
            }
            bienvenido.setVisible(false);
            
            } catch (ErrorC ex) {
                System.out.println(ex);;
            }  
            this.limpiar();
           
        }
        
        if (e.getSource() == bienvenido.iniciarBButton){

            try{
            String login = bienvenido.usuarioBtxt.getText();
            String password = new String(bienvenido.passwordB.getPassword());
            String ip = bienvenido.iptxt.getText();
            primo = miProxy.accederSistema(login, password, ip);

            
            if(bienvenido.radioUsuario.isSelected()){
                 forma.setVisible(true);
            }else if(bienvenido.radioConductor.isSelected()){
                adminForma.setVisible(true);
            }
            bienvenido.setVisible(false);
            } catch (ErrorC ex) {
                System.out.println(ex);;
            }  
               this.limpiar();
        }
        

        if (e.getSource() == forma.modificarButton) {
            String encriptado = "";
            
            String nombre = forma.nombretxt.getText();
            String apellido = forma.apellidotxt.getText();
            String edad = forma.edadtxt.getText();
            String telefono = forma.telefonotxt.getText();
            String direccion = forma.direcciontxt.getText();
            String login = forma.logintxt.getText();
            String password = new String(forma.jPasswordField1.getPassword());
            String ciudad = forma.ciudadtxt.getText();
            llamado = "modificar, " + nombre + "," + apellido + "," + edad + "," + telefono + "," + direccion + "," + login + "," + password + "," + ciudad;
            Encriptacion encriptador = new Encriptacion();
            try {
                encriptado = encriptador.encriptar(llamado, primo.toString());
                fa.methodCaller(encriptado, bienvenido.iptxt.getText());
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchMethodException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        if (e.getSource() == forma.eliminarButton) {
            String encriptado = "";
            llamado = "eliminar," + forma.logintxt.getText();
            Encriptacion encriptador = new Encriptacion();
            try {
                encriptado = encriptador.encriptar(llamado, primo.toString());
                fa.methodCaller(encriptado, bienvenido.iptxt.getText());
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchMethodException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.limpiar();
        }

        if (e.getSource() == forma.limpiarButton) {
            limpiar();
        }
        
        if(e.getSource() == forma.atrasButton){
            forma.setVisible(false);
            bienvenido.setVisible(true);
        }
        
        if(e.getSource() == adminForma.atrasButton){
            adminForma.setVisible(false);
            bienvenido.setVisible(true);
        }
        
        if(e.getSource() == adminForma.crearRutaButton){
            adminForma.setVisible(false);
            formaRuta.setVisible(true);
  
        }
        
        if(e.getSource() == adminForma.mostrarbutton){
            String encriptado = "";
            llamado = "mostrarUsuarios";
            Encriptacion encriptador = new Encriptacion();
            try {
                encriptado = encriptador.encriptar(llamado, primo.toString());
                fa.methodCaller(encriptado, bienvenido.iptxt.getText());
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchMethodException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == adminForma.buscarbutton){
            String login = JOptionPane.showInputDialog("Ingrese login del usuario");
            String encriptado = "";
            llamado = "buscar," + login;
            Encriptacion encriptador = new Encriptacion();
            try {
                encriptado = encriptador.encriptar(llamado, primo.toString());
     
                    fa.methodCaller(encriptado, bienvenido.iptxt.getText());

            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchMethodException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(e.getSource() == forma.mostrarRutasButton){
            
            String encriptado = "";
            llamado = "mostrarRutas," + forma.logintxt.getText();
            Encriptacion encriptador = new Encriptacion();
            try {
                encriptado = encriptador.encriptar(llamado, primo.toString());
     
                    fa.methodCaller(encriptado, bienvenido.iptxt.getText());

            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchMethodException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource() == forma.cancelarRutaButton){
                        String encriptado = "";
            llamado = "cancelarRuta," + forma.logintxt.getText();
            Encriptacion encriptador = new Encriptacion();
            try {
                encriptado = encriptador.encriptar(llamado, primo.toString());
     
                    fa.methodCaller(encriptado, bienvenido.iptxt.getText());

            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchMethodException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource() == adminForma.borrarRutaButton){
            String encriptado = "";
            llamado = "borrarRuta," + forma.logintxt.getText();
            Encriptacion encriptador = new Encriptacion();
            try {
                encriptado = encriptador.encriptar(llamado, primo.toString());
     
                    fa.methodCaller(encriptado, bienvenido.iptxt.getText());

            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchMethodException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(e.getSource() == formaRuta.crearRutaButton){
            String encriptado = "";
            String placa = formaRuta.placatxt.getText();
            String marca = formaRuta.marcatxt.getText();
            String hora = formaRuta.horatxt.getText();
            String costo = formaRuta.costotxt.getText();
            String tipoTrans = "";
            if(formaRuta.busRadio.isSelected()){
                tipoTrans = "bus";
            }else if(formaRuta.trenRadio.isSelected()){
                tipoTrans = "tren";
            }else if(formaRuta.wheelsRadio.isShowing()){
                tipoTrans = "wheels";
            }
            int cupos = Integer.parseInt(formaRuta.cupostxt.getText());
            llamado = "decorator," + placa + "," + marca + "," + cupos + "," + hora + "," + costo + "," +  tipoTrans;
            Encriptacion encriptador = new Encriptacion();
            try {
                encriptado = encriptador.encriptar(llamado, primo.toString());
                fa.methodCaller(encriptado, bienvenido.iptxt.getText());
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchMethodException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.limpiar();
            
        }
        
        if(e.getSource() == formaRuta.atrasButton){
            formaRuta.setVisible(false);
            adminForma.setVisible(true);
        }
    }
    
    
       
    public void limpiar()
    {
        forma.nombretxt.setText(null);
        forma.apellidotxt.setText(null);
        forma.edadtxt.setText(null);
        forma.telefonotxt.setText(null);
        forma.direcciontxt.setText(null);
        forma.logintxt.setText(null);
        forma.jPasswordField1.setText(null);
        forma.ciudadtxt.setText(null);
        
        bienvenido.usuarioBtxt.setText(null);
        bienvenido.passwordB.setText(null);
        bienvenido.buttonGroup2.clearSelection();
//        
//        adminForma.cupostxt.setText(null);
//        adminForma.marcatxt.setText(null);
//        adminForma.placatxt.setText(null);
//        adminForma.buttonGroup3.clearSelection();
    }
    
}