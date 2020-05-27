package com.mycompany.sabanago.das2;

import com.mycompany.sabanago.controlador.Controlador;
import javax.swing.UIManager;
import com.mycompany.sabanago.modelo.Facade;
import com.mycompany.sabanago.modelo.Proxy;
import com.mycompany.sabanago.modelo.Usuario;
import com.mycompany.sabanago.vista.FormaAdmin;
import com.mycompany.sabanago.vista.FormaBienvenido;
import com.mycompany.sabanago.vista.FormaRuta;
import com.mycompany.sabanago.vista.FormaUsuario;


public class DAS2 {

    public static void main(String[] args) {
        
         try {
              UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");//Negro sensual
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormaBienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         
        Facade fa = Facade.reemplazarConstructor();
        Usuario user = new Usuario();
        FormaUsuario forma = new FormaUsuario();
        Proxy miProxy = Proxy.reemplazarConstructor();
        FormaBienvenido bienvenido = new FormaBienvenido();
        FormaAdmin admin = new FormaAdmin();
        
        FormaRuta formaRuta = new FormaRuta();

        Controlador controlador = new Controlador(user, fa, forma, miProxy, bienvenido, admin, formaRuta);
        controlador.iniciar();
        bienvenido.setVisible(true);
        
        
    }

}
