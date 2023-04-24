package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        final String address = "127.0.0.1";
        final int port = 23456;

        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            ServerStatus.setStatus(true);
            System.out.println("Server started!");

            while (ServerStatus.isActive()) {
                Socket socket = new Socket();
                socket = server.accept();
                SocketHandler socketHandler = new SocketHandler(socket);
                socketHandler.start();
                socketHandler.join();
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
