package server;

public class ServerStatus {
    private final static ServerStatus INSTANCE = new ServerStatus();
    private static boolean status;

    public static boolean isActive() {
        return ServerStatus.status;
    }

    public static void setStatus(boolean status) {
        //System.out.println("Set Server status to: "+status +" before it was "+ServerStatus.status);
        ServerStatus.status = status;
    }

    public static ServerStatus getInstance() {
        return INSTANCE;
    }
}