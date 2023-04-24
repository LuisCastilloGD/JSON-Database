package server;

public class KeyMissingException extends Exception{
    public KeyMissingException(){
        super("ERROR: key not found");
    }
}
