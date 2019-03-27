
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
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
public class Cliente implements Runnable {

    InputStream is;
    OutputStream os;
    InetSocketAddress addr;
    Socket clienteSocket;
    byte[] mensaje = new byte[64];
    String cadena = "mensajeCliente";

    Cliente() {
        
        new Thread(this).start();

    }

    @Override
    public void run() {
        try {
            conexion();
            
            for (int i = 10; i < 14; i++) {
                
                clienteEscribir(i);

                clienteLeer();

            }

            System.out.println("Cliente:Cerrando el socket");

            clienteSocket.close();

            System.out.println("Cliente: Terminado");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void clienteLeer() {
        try {
            mensaje = new byte[64];
            is.read(mensaje);
            System.out.println("Cliente recibio: " + new String(mensaje));
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clienteEscribir(int x) {

        try {
            
            mensaje = (cadena + " " + x).getBytes();
            System.out.println("Cliente: Enviando mensaje");
            os.write(mensaje);

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void conexion() {
        try {
            System.out.println("Cliente: Creando Socket");
            clienteSocket = new Socket();
            System.out.println("Cliente: Estableciendo conexion");

            addr = new InetSocketAddress("localhost", 6666);
            clienteSocket.connect(addr);
            
            is = clienteSocket.getInputStream();
            os = clienteSocket.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
