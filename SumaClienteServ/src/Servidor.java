
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
    byte[] mensaje = new byte[1];
    String cadena = "mensajeServidor";
    ServerSocket serverSocket;
    InetSocketAddress addr;
    InputStream is = null;
    OutputStream os = null;
    int[] numeros = new int[5];
    int num;

    Servidor() {

        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            //Comenzamos la conexion del servidor
            prepararConexion();
            //recibimos la respuesta del cliente
            System.out.println("Servidor: Conexion recibida");
            
            //leyemos los 5 numeros que nos ewnvia el cliente
            for (int i = 0; i < 5; i++) {
                numeros[i]=servidorLeer();
                
            }
            //Escribimos la suma de los numeros del cliente
            servidorEscribir(servidorSuma(numeros));
            
            //cerramos la conexion
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

    public int servidorLeer() {

        try {
            mensaje = new byte[1];
            is.read(mensaje);
            System.out.println("Servidor leyo: "+new String(mensaje));
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.parseInt(new String(mensaje));
    }

    public int servidorSuma(int[] numeros) {
        int num1 = 0;

        for (int i = 0; i < numeros.length; i++) {
            num1 = num1 + numeros[i];
        }
        System.out.println("Servidor: sumando");

        return num1;
    }
    public void servidorEscribir(int x){
        try {
            os.write(String.valueOf(x).getBytes());
            System.out.println("Servidor:Escribiendo la suma");
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
