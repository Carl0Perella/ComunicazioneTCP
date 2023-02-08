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

public class Client {
    
    Socket Clientsocket;
    DataInputStream input;
    DataOutputStream output;
    BufferedReader Breader;
    InetAddress ipadress;
    String hostname; 
    
     public Socket connessione(){
        try {
            ipadress = InetAddress.getLocalHost();
            hostname = ipadress.getHostName();
            System.out.println("0.connessione effettuata con Indirizzo IP : " +Inet4Address.getLocalHost().getHostAddress()+ " e all Hostname : " + hostname +  "e alla porta: numero 2000"); 
            Socket Clientsocket = new Socket(InetAddress.getLocalHost(),2000); 
            input = new DataInputStream(Clientsocket.getInputStream());
            output = new DataOutputStream(Clientsocket.getOutputStream());            
        } 
              
        catch (UnknownHostException err) { 
            System.err.println("errore host non riconosciuto");
        } 
        
        catch (ConnectException err){
            System.err.println("non e possibile effettuare la connessione");
        }        
        
        catch (Exception err) {
            System.err.println("non e possibile effettuare la connessione");
        }
        return Clientsocket;
    }
     
    public void messaggio(){
        try {
            System.out.print("1. Messaggio da inviare : ");
            Breader = new BufferedReader(new InputStreamReader(System.in));
            String messaggio = Breader.readLine();
            System.out.println("2. Invio del messaggio");
            output.writeBytes(messaggio +"\n");
            System.out.println("3. In attesa di risposta");
            String messricevuto = input.readLine();
            System.out.println("4. Risposta del server : " + messricevuto);
        }  
        catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.connessione();
        c.messaggio();
        
        
    }
    
}

