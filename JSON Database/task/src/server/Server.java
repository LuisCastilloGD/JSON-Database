package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;
    private static final int BACKLOG = 50;
    private static final int THREADS = Runtime.getRuntime().availableProcessors();

    public static void start() {
        System.out.println("Server started!");
        try (ServerSocket server = new ServerSocket(PORT, BACKLOG, InetAddress.getByName(ADDRESS))) {
            ServerStatus.setStatus(true);
            //ExecutorService executorService = Executors.newFixedThreadPool(THREADS);

            while (ServerStatus.isActive()) {
                Socket socket = new Socket();
                socket = server.accept();

                SocketHandler socketHandler = new SocketHandler(socket);
                //executorService.submit(socketHandler);
                socketHandler.start();
                socketHandler.join();

            }
            //executorService.shutdown();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
