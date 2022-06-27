//Si se compila y ejecuta desde VSC, la siguiente línea debe incluirse
//Si se compila desde la terminal se debe de comentar.

//package com.sic;

/* Para compilar desde terminal: javac Cliente.java
 * Para ejecutar desde terminal: java Cliente
 * Author Mario Alberto Ramírez Reyna
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
public class Cliente 
{
    public static void main(String[] args) {

        Socket s;
        PrintStream escritura;
        BufferedReader buffer;

        //String   host = "localhost";//Dirección del Cliente, debe estar en la misma red local que el Servidor
        String   host = "192.168.1.108";//Raspberry
        int      port = 30000;
        String   respuesta;

        //Referencia a la entrada por consola (System.in)
        BufferedReader    entrada = new BufferedReader(new InputStreamReader(System.in));

        try {

            //Creo una conexion al socket servidor
            s = new Socket(host,port);

            //Creo las referencias al canal de escritura y lectura del socket
            escritura = new PrintStream(s.getOutputStream()); // para escribir en el socker
            buffer = new BufferedReader ( new InputStreamReader ( s.getInputStream() ) ); // para leer del socket

            while ( true ) {
               
                System.out.print("Mensaje a enviar: ");

                //Escribo en el canal del socket
                escritura.println( entrada.readLine() );

                //Espero la respuesta del socket
                respuesta = buffer.readLine();
                System.out.println(respuesta);
                if ( respuesta.equals("salir")) { //si escribes salir tanto el servidor como el cliente se terminaran
                    break;
                }
            }
            escritura.close();
            buffer.close();
            s.close();

        } catch (UnknownHostException e) {
            System.out.println("No puedo conectarme a " + host + ":" + port);
        } catch (IOException e) {
            System.out.println("Error de E/S en " + host + ":" + port);
        }
    }
}
