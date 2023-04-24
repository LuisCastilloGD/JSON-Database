package server;

import com.google.gson.JsonParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SocketHandler extends Thread {
    private final Socket socket;
    private final InputController inputController;
    public SocketHandler(Socket socketForClient) {
        this.socket = socketForClient;
        this.inputController = new InputController();
    }

    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            String msg = input.readUTF();
            System.out.println("Received: " + msg);


            String response = inputController.inputRequest(msg);
            output.writeUTF(response);
            System.out.println("Sent: " + response);

            if (new JsonParser().parse(msg).getAsJsonObject().get("type").getAsString().equals("exit"))
                ServerStatus.setStatus(false);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
