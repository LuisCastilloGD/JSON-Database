package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import com.beust.jcommander.JCommander;


public class Main {

    public static void main(String[] args) {

        Args argsJC = new Args();
        JCommander.newBuilder().addObject(argsJC).build().parse(args);
        final String address = "127.0.0.1";
        final int port = 23456;
        try (Socket socket = new Socket(InetAddress.getByName(address), port);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
            System.out.println("Client started!");

            InputFormatter inputFormatter = new InputFormatter(argsJC);
            List<String> messagesJson = inputFormatter.inputToJson();

            for(String msg : messagesJson){
                output.writeUTF(msg);
                System.out.println("Sent: " + msg);
                String response = input.readUTF();
                System.out.println("Received: " + response);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
