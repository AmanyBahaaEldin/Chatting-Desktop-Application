/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author Bahaa eldin
 */
public class ChatHandler extends Thread{
    DataInputStream dis;
    PrintStream ps;
    static Vector<ChatHandler> clientsVector = new Vector<ChatHandler>();

    public ChatHandler(Socket sc) throws IOException{

    try{
            dis = new DataInputStream(sc.getInputStream());
            ps = new PrintStream(sc.getOutputStream());
            clientsVector.add(this);
            start();
        }
        catch(IOException e){
            ps.close();
            dis.close();
            System.out.println("server failed");
        }
    }
    public void sendMsgToAll(String mssg){
        for(ChatHandler ch:clientsVector){
            ch.ps.println(mssg);
            }
    }
@Override
    public void run(){
    while(true){
        try{
        String msg = dis.readLine();
        sendMsgToAll(msg);
        }
        catch(Exception e){
        System.out.println("Cannot send messages to all");

        }
    }

    }

}
