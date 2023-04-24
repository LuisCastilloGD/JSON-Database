public class Main {

    public static void main(String[] args) {

        /* write your code here */
        Broker broker = new Broker();
        BuyCommand buyCommand = new BuyCommand(new Stock());
        broker.setCommand(buyCommand);
        broker.executeCommand();
        SellCommand sellCommand = new SellCommand(new Stock());
        broker.setCommand(sellCommand);
        broker.executeCommand();
    }
}


class Stock {

    public void buy() {
        System.out.println("Stock was bought");
    }

    public void sell() {
        System.out.println("Stock was sold");
    }
}

interface Command {
    public void execute();
    /* write your code here */
}

class BuyCommand implements Command {
    private Stock stock;

    public BuyCommand(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        System.out.println("Stock was bought");
    }

    /* write your code here */
}

class SellCommand implements Command {
    private Stock stock;

    public SellCommand(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        System.out.println("Stock was sold");
    }

    /* write your code here */
}

class Broker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        this.command.execute();
    }
}