package client;
import com.beust.jcommander.JCommander;

public class Main {
    public static void main(String[] args) {
        Args argsJC = new Args();
        JCommander.newBuilder().addObject(argsJC).build().parse(args);
        Client.start(argsJC);
    }
}

