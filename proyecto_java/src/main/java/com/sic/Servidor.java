//Si se compila y ejecuta desde VSC, la siguiente línea debe incluirse
//Si se compila desde la terminal se debe de comentar.

//package com.sic;

/* Para compilar desde terminal: javac Servidor.java
 * Para ejecutar desde terminal: java Servidor
 * Author Mario Alberto Ramírez Reyna
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor 
{
    public static void main(String[] args) {

        ServerSocket    s;  //Socket servidor
        Socket      sc;      //Socket cliente

        PrintStream     escribe;
        BufferedReader  buffer;
        int port = 30000;
        String    mensaje;

        try {
            //Creo el socket server
            s = new ServerSocket(port);

            //aceptar solicitud
            sc = s.accept();

            //Obtengo una referencia a los canales de escritura y lectura del socket cliente
            buffer = new BufferedReader( new InputStreamReader ( sc.getInputStream() ) );//leer
            escribe = new PrintStream   ( sc.getOutputStream() );//escribir

            while ( true ) {
                //Leo el socket
                mensaje = buffer.readLine();
                System.out.println(mensaje);

                //Escribo en el socket
                escribe.println(mensaje);

                if ( mensaje.equals("salte")) {
                    break;
                }
            }

            buffer.close();
            escribe.close();

            sc.close();
            s.close();
        } catch (IOException e) {
            System.out.println("No puedo crear el socket");
        }
    }
}
