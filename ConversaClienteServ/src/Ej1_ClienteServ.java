
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agonzalezgonzalez
 */
public class Ej1_ClienteServ {
    public static boolean servReady = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creamos el proceso del servidor
        Servidor s = new Servidor();
        //Este sleep es necesario para que el servidor tenga tiempo de prepararse y empezar a aceptar conexiones
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ej1_ClienteServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se conecta el cliente al servidor
        Cliente c = new Cliente();
    }

}
