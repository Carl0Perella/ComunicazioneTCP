/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package progetto.comunicazione.perella;

/**
 *
 * @author Carloperella
 */

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    ServerSocket server = null;
    Socket socketClient = null;
    String ipadress;
    String hostname;
    String risposta;
    String messletto;
    DataInputStream input;
    DataOutputStream output;
    
    public Socket inizializzserver(){
        try {
            ipadress = Inet4Address.getLocalHost().getHostAddress();
            hostname = InetAddress.getLocalHost().getHostName();
            
            System.out.println("0. Inizializzo il server all indirizzo: " + ipadress +" all Hostname: " + hostname + " e alla porta: numero 2000" );            
            server = new ServerSocket(2000); //inizializzare il server alla porta 2000
            System.out.println("1. Server attivo e in ascolto ");
            socketClient = server.accept(); //ascolto la porta
            System.out.println("2. Connessione effettuata");
            server.close(); //serve per evitare connessioni multiple
            //System.out.println("3. Connessione terminata");
            
            input = new DataInputStream(socketClient.getInputStream());
            output = new DataOutputStream(socketClient.getOutputStream());
            
            }
        
        catch (BindException err){
            System.err.println("Ci dispiace ma la porta che richiesta e gia in uso");
        }        
        
        catch (IOException err) {
            err.printStackTrace();
        }
        return socketClient;
        }
       
    public void messaggio(){
        try {
            System.out.println("3. aspettando un messaggio: ");
            messletto = input.readLine();
            System.out.println("4. Messaggio arrivato");
            risposta = "Ciao anche a te";
            output.writeBytes(risposta +"\n"); 
            socketClient.close();
            } 
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        
        Server s = new Server();
        s.inizializzserver();
        s.messaggio();
        
    }}
