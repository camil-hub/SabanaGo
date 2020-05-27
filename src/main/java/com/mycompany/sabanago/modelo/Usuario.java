package com.mycompany.sabanago.modelo;

public class Usuario implements Component{
    private String nombre;
    private String apellido;
    private String edad;
    private String tel;
    private String direccion;
    private String login;
    private String password;
    private String Barrio;
    private String ruta;


    public Usuario(){
        this.nombre = "";
        this.apellido = "";
        this.edad = "";
        this.tel = "";
        this.direccion = "";
        this.login = "";
        this.password = "";
        this.Barrio = "";
        this.ruta = "";

    }
    public Usuario(String nombre, String apellido, String edad, String tel, String direccion, String login, String password, String barrio, String ruta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.tel = tel;
        this.direccion = direccion;
        this.login = login;
        this.password = password;
        this.Barrio = barrio;
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String Barrio) {
        this.Barrio = Barrio;
    }
        public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", tel=" + tel + ", direccion=" + direccion + ", login=" + login + ", password=" + password + ", Barrio=" + Barrio + ", ruta=" + ruta +'}';
    }





    @Override
    public String showUsers() {
        System.out.println(this.nombre);
        return this.nombre;
        //To change body of generated methods, choose Tools | Templates.
    }
}
