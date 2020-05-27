package com.mycompany.sabanago.modelo;
import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    String name;
    List<Component> components  = new ArrayList<>();

    public Composite(){
        this.name = "";
    }
    public Composite(String name) {
        this.name = name;
    }

    public void addComponent(Component com){
        System.out.println("adding " + com + " to " + this.name);
        components.add(com);
    }
    @Override
    public String showUsers() {
        System.out.println("En el barrio de " + name);
        String cadena = name + ",";
        for (int i = 0; i < components.size(); i++) {
            if(i == components.size()-1){
                cadena+= components.get(i).showUsers();
            }else{
                cadena+= components.get(i).showUsers()+", ";
            }
        }
        return cadena;
    }
//    
//    public String justNames(){
//        
//        return name
//    }
}
