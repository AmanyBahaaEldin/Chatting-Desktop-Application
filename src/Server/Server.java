/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Bahaa eldin
 */
public class Server {
    ServerSocket serverSocket;
    Socket internalSocket;
    public Server() throws IOException{
    try{
        serverSocket = new ServerSocket(5005);
        while(true){
        internalSocket = serverSocket.accept();
        new ChatHandler(internalSocket);
        }
    }
    catch(IOException e){
        System.out.println("server failed");
        serverSocket.close();
        internalSocket.close();
    }
    }
    public static void main(String[] args) throws IOException{
        new Server();
    }

    
}
