package com.mycompany.sabanago.modelo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;


public class Facade {

    private static Facade instance;
    
    private HashMap<String, Usuario> usuarios;

    private Composite barrio;
    boolean first;
    private ArrayList<Composite> barrios;
    private ArrayList<Transporte> rutas;
    private Sesion sesion;
    private HashMap<String, Sesion> sesiones;
    private ArrayList tickets;
    

    public Facade() {
        usuarios = new HashMap<String, Usuario>();
        sesiones = new HashMap<String, Sesion>();
        sesion = new Sesion(null, null, null);
        
        
       
        barrio = new Composite();
        first = true;
        barrios = new ArrayList();
        rutas = new ArrayList();
        tickets = new ArrayList();
        this.fillRutas();
    }

    public static Facade reemplazarConstructor() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }
    
    public void registrarSesion(Sesion nueva) {
        this.sesion = nueva;
        sesiones.put(this.sesion.getIp(), nueva);
        System.out.println(sesion.toString());
        System.out.println("sesion registrada");
        //this.getAll();
    }
//    public void getAll() {
//        System.out.println(Arrays.asList(usuarios));
//    }



    public void modificarUsuario(Usuario user) {
        usuarios.put(user.getLogin(), user);
        //System.out.println(Arrays.asList(usuarios));
        //JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente");
    }

    private void eliminarUsuario(String login) {
        usuarios.remove(login);
        //System.out.println(Arrays.asList(usuarios));
        JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
    }

    private void buscar(String login) {
        System.out.println(usuarios.get(login));
    }

    private void mostrarTodos() {
        Set<String> keys = usuarios.keySet();
        for(String key: keys){
            System.out.println(usuarios.get(key));
        }
    }

    public String methodCaller(String encrip, String myIP) throws UnsupportedEncodingException, NoSuchMethodException {
        Usuario nuevoUsuario = new Usuario();
        String decripted = "";
        Encriptacion encriptador = new Encriptacion();
        
        try {
            decripted = encriptador.desencriptar(encrip, this.sesion.getPrimo().toString());
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] arrLlamado = decripted.split(",");
        switch(arrLlamado[0]){
            case "modificar":
                nuevoUsuario = new Usuario(arrLlamado[1],arrLlamado[2],arrLlamado[3],arrLlamado[4],arrLlamado[5],arrLlamado[6],arrLlamado[7], arrLlamado[8], "");
                this.modificarUsuario(nuevoUsuario);
                this.mostrarTodos();
                this.addCompo(nuevoUsuario);
                return "void";
            case "eliminar":
                this.eliminarUsuario(arrLlamado[1]);
                return "void";
            case "buscar":
                this.buscar(arrLlamado[1]);
                return "void";
            case "mostrarUsuarios":
                this.compCaller();
                return "void";
            case "decorator":
                this.decoCaller(arrLlamado[1], arrLlamado[2], Integer.parseInt(arrLlamado[3]), arrLlamado[4], Integer.parseInt(arrLlamado[5]), arrLlamado[6]);
                return "void";
            case "mostrarRutas":
                this.mostrarRutas(arrLlamado[1]);
                return "void";
            case "porZona":
                return this.mostrarUnaZona(arrLlamado[1]);
            case "crearLocalidad":
                this.crearLocalidad(arrLlamado[1]);
                return "void";
            case "borrarRuta":
                this.showRutastoDelete();
                return "void";
            case "cancelarRuta":
                this.cancelarViaje(arrLlamado[1]);
                return"void";
                
        }
        return "none";
    }

    private void compCaller() throws NoSuchMethodException {
        Class miClase = Composite.class;
        Method methodCall2 = miClase.getDeclaredMethod("showUsers");

        for (int i = 0; i < barrios.size(); i++) {
          try {
            methodCall2.invoke(barrios.get(i));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        }   
    }
    
    private void crearLocalidad(String localidad) throws NoSuchMethodException{
        boolean addbarrio = true;
        
        for (int i = 0; i < barrios.size(); i++) {
            
            if(barrios.get(i).name.equals(localidad)){
                addbarrio = false;
                barrio = barrios.get(i);
                break;
            }
            
        }
        if (addbarrio) {
            barrio = new Composite(localidad);
            barrios.add(barrio);
        }
        this.compCaller();
        
    }
    
    

    private void addCompo(Usuario nuevoUsuario) throws NoSuchMethodException {
        boolean addbarrio = true;
        Component usuarioComp = nuevoUsuario;
        
        for (int i = 0; i < barrios.size(); i++) {
            
            if(barrios.get(i).name.equals(nuevoUsuario.getBarrio())){
                addbarrio = false;
                barrio = barrios.get(i);
                break;
            }
            
        }
        if (addbarrio) {
            barrio = new Composite(nuevoUsuario.getBarrio());
            barrios.add(barrio);
        }

        Class miClase = Composite.class;
        Method methodCall = miClase.getDeclaredMethod("addComponent", Component.class);
        try {
            methodCall.invoke(barrio, usuarioComp);  
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void decoCaller(String marca, String placa, int cupos, String hora, int costo, String tipoTrans) throws NoSuchMethodException{
        
        Class miClase = Transporte.class;
        Method methodCall = miClase.getDeclaredMethod("showTransport");
        try {
            if(tipoTrans.equals("bus")){
                Transporte Bus= new CostoDecorator(costo, new HoraDecorator(hora, new CuposDecorator(cupos,new PlacaDecorator(placa,new MarcaDecorator(marca,new Bus())))));
                JOptionPane.showMessageDialog(null,"Ruta creada Exitosamente\n" + methodCall.invoke(Bus));  
                rutas.add(Bus);

            }else if(tipoTrans.equals("tren")){

                Transporte tren= new CostoDecorator(costo, new HoraDecorator(hora, new CuposDecorator(cupos,new PlacaDecorator(placa,new MarcaDecorator(marca,new Tren())))));
                JOptionPane.showMessageDialog(null,"Ruta creada Exitosamente\n" + methodCall.invoke(tren));
                rutas.add(tren);
            }else if(tipoTrans.equals("wheels")){
                Transporte wheels= new CostoDecorator(costo, new HoraDecorator(hora, new CuposDecorator(cupos,new PlacaDecorator(placa,new MarcaDecorator(marca,new Wheels())))));
                JOptionPane.showMessageDialog(null,"Ruta creada Exitosamente\n" + methodCall.invoke(wheels));
                rutas.add(wheels);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mostrarRutas(String login){
        String sec = "Rutas Disponibles\n";
        for (int i = 0; i < rutas.size(); i++) {
            sec+= (i+1) + ". " + rutas.get(i).showTransport() + "\n";
        }
        Usuario nuevo = usuarios.get(login);        
        String miRuta= JOptionPane.showInputDialog(sec);
        nuevo.setRuta(rutas.get(Integer.parseInt(miRuta)-1).showTransport());
        usuarios.put(nuevo.getLogin(), nuevo);
       
        this.mostrarTodos();
        Transporte trans = rutas.get(Integer.parseInt(miRuta)-1);
        Ticket nuevoTicket = new Ticket(tickets.size()+1, trans);
        tickets.add(nuevoTicket);
        JOptionPane.showMessageDialog(null, "Ticket Generado  \n"
                + "Codigo: " + nuevoTicket.getId() + "\n"
                        + trans.showTransport());
        
    }
    
    private String mostrarUnaZona(String zona) throws NoSuchMethodException{
        Class miClase = Composite.class;
        Method methodCall2 = miClase.getDeclaredMethod("showUsers");
        for (int i = 0; i < barrios.size(); i++) {
          try {
              if(barrios.get(i).name.equals(zona)){
                   Object value = methodCall2.invoke(barrios.get(i));
                   String cadena = value.toString();
                   return cadena;
                   
                   
              }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        }   
        return "none";
        
        
    }
    
    private void showRutastoDelete(){
        String sec = "Rutas Disponibles\n";
        for (int i = 0; i < rutas.size(); i++) {
            sec+= (i+1) + ". " + rutas.get(i).showTransport() + "\n";
        }      
        String miRuta= JOptionPane.showInputDialog(sec);
        int numRuta = Integer.parseInt(miRuta);
        this.borrarRuta(numRuta);
        
    }
    
    private void borrarRuta(int numRuta){
        
        rutas.remove(numRuta-1);
//        for (int i = 0; i < rutas.size(); i++) {
//            System.out.println(rutas.get(i));
//        }
    }
    
    private void cancelarViaje(String login){
        Usuario nuevo = usuarios.get(login);
        nuevo.setRuta("");
        usuarios.put(login, nuevo);
        System.out.println(usuarios.get(login));
        
    }
    
    private void fillRutas(){
        try {
            this.decoCaller("KSA321", "Mazda", 30, "12:30", 3200, "bus");
            this.decoCaller("ASF429", "Kia", 30, "11:30", 2000, "wheels");
            this.decoCaller("DSA392", "BMW", 30, "15:00", 3200, "wheels");
            this.decoCaller("YNI328", "Saban", 30, "17:30", 1500, "tren");
        
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
