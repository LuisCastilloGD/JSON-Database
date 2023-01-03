package server;

import com.google.gson.JsonParser;
import server.request.RequestController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketHandler extends Thread {
    private final Socket socket;
    private final RequestController requestController;

    public SocketHandler(Socket socketForClient) {
        this.socket = socketForClient;
        this.requestController = new RequestController();
    }

    @Override
    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            String msg = input.readUTF();
            System.out.println("Received: " + msg);
            String response = requestController.inputRequest(msg);
            output.writeUTF(response);
            System.out.println("Sent: " + response);
            if (JsonParser.parseString(msg).getAsJsonObject().get("type").getAsString().equals("exit"))
                ServerStatus.setStatus(false);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
