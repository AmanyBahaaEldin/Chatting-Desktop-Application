/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Bahaa eldin
 */
public class Client extends Application{
    Socket clientSocket;
    DataInputStream dis;
    PrintStream ps; 
//for GUI
    Label label = new Label("Enter Your Message: ");
    TextArea text = new TextArea("Welcome to Chat Application ^_^");
    TextField field = new TextField();
    Button btn = new Button("send");
    HBox buttomBox;
    BorderPane root;
    Scene scene;
    @Override
    public void init() throws IOException{
        try{
            clientSocket = new Socket("127.0.0.1" , 5005);
            dis = new DataInputStream(clientSocket.getInputStream());
            ps = new PrintStream(clientSocket.getOutputStream());
            buttomBox = new HBox(5 , label ,  field , btn);
            root = new BorderPane();
            root.setCenter(text);
            root.setBottom(buttomBox);
            scene = new Scene(root , 314 , 400);
            }
        catch(IOException e){
            System.out.println("Client Failed");
            ps.close();
            dis.close();
        }
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    String replyMsg;
                    try{
                    replyMsg = dis.readLine();
                    text.appendText("\n" + replyMsg);
                    }
                    catch(Exception e){
                    System.out.println("failed reading");
                    }
                    }
                }
        }).start();
    }

    @Override
    public void start(Stage primaryStage){
        field.setPromptText("Enter Your message...");
        btn.setDefaultButton(true);
        btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            try{
                ps.println(field.getText());
                field.setText("");    
            }
            catch(Exception e){
                System.out.println("Client Failed");
                }
        }});
        text.setEditable(false);
        primaryStage.setTitle("Chat Applicat");
        primaryStage.setScene(scene);
        primaryStage.show();
}
    public static void main(String[] args){
        new Client(); 
        Application.launch(args);
    }
}
