package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.NoSuchFileException;
import java.util.List;

public class Client {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;
    public static void start(Args args) {
        System.out.println("Client started!");

        try (
                Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {

            List<String> messagesJson = InputFormatter.getMessages(args);
            for (String msg : messagesJson) {
                output.writeUTF(msg);
                System.out.println("Sent: " + msg);
                String response = input.readUTF();
                System.out.println("Received: " + response);
            }
        } catch (NoSuchFileException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
