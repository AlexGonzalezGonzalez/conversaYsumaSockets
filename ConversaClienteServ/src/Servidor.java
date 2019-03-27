
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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
public class Servidor implements Runnable {

    static boolean servReady = false;
    Socket newSocket;
    byte[] mensaje = new byte[64];
    String cadena = "mensajeServidor";
    ServerSocket serverSocket;
    InetSocketAddress addr;
    InputStream is = null;
    OutputStream os = null;

    Servidor() {

        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            prepararConexion();

            System.out.println("Servidor: Conexion recibida");

            for (int i = 10; i < 14; i++) {
                servidorLeer();
                servidorEscribir(i);
            }

            System.out.println("Servidor: Cerrando socket cliente");

            newSocket.close();

            System.out.println("Servidor: Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Servidor: Terminado");

        } catch (IOException e) {
        }
    }

    public void prepararConexion() {
        try {
            System.out.println("Servidor: Creando socket");
            serverSocket = new ServerSocket();

            System.out.println("Servidor: bind");
            addr = new InetSocketAddress("localhost", 6666);
            serverSocket.bind(addr);

            System.out.println("Servidor: Aceptando conexiones");
            Servidor.servReady = true;
            newSocket = serverSocket.accept();

            is = newSocket.getInputStream();
            os = newSocket.getOutputStream();

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void servidorLeer() throws IOException {

        mensaje = new byte[64];
        is.read(mensaje);
        System.out.println("Servidor recibio: " + new String(mensaje));

    }

    public void servidorEscribir(int x) throws IOException {

        mensaje = (cadena + " " + x).getBytes();
        System.out.println("Servidor: Escribiendo mensaje");
        os.write(mensaje);

    }
}
