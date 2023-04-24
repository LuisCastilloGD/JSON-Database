package server;

final public class ServerStatus {
    private static boolean status;
    public static boolean isActive() {
        return ServerStatus.status;
    }
    public static void setStatus(boolean status) {
        ServerStatus.status = status;
    }
}