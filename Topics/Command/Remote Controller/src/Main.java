import java.util.Scanner;

class Client {

    public static void main(String[] args) {

        Controller controller = new Controller();
        TV tv = new TV();

        int[] channelList = new int[3];

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            channelList[i] = scanner.nextInt();
        }

        Command turnOnTV = new TurnOnCommand(tv);
        controller.setCommand(turnOnTV);
        controller.executeCommand();

        Command changeChannel;
        for (int i = 0; i < 3; i++) {
            changeChannel = new ChangeChannelCommand(new Channel(tv,channelList[i]));
            controller.setCommand(changeChannel);
            controller.executeCommand();
        }

        Command turnOffTV = new TurnOffCommand(tv);
        controller.setCommand(turnOffTV);
        controller.executeCommand();
    }
}

class TV {

    Channel channel;

    void turnOn() {
        System.out.println("Turning on the TV");
        //setChannel(new Channel(this, 0));
    }

    void turnOff() {
        System.out.println("Turning off the TV");
        //setChannel(new Channel(this, 0));
    }

    void setChannel(Channel channel) {
        System.out.println("Channel was changed to "+channel.getChannel());
        this.channel = channel;
    }
}

class Channel {
    private TV tv;
    private int channelNumber;

    Channel(TV tv, int channelNumber) {
        this.tv=tv;
        this.channelNumber = channelNumber;
    }

    public int getChannel (){
        return channelNumber;
    }
    void changeChannel() {
        tv.setChannel(this);
    }
}

interface Command {
    /* write your code here */
    public void execute();
}

class TurnOnCommand implements Command {
    /* write your code here */
    TV tv;
    TurnOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.turnOn();
    }
}

class TurnOffCommand implements Command {
    /* write your code here */
    TV tv;
    TurnOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.turnOff();
    }
}

class ChangeChannelCommand implements Command {

    private Channel channel;


    ChangeChannelCommand(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void execute() {
        this.channel.changeChannel();
    }

}

class Controller {
    private Command command;

    void setCommand(Command command) {
        this.command = command;
    }

    void executeCommand() {
        command.execute();
    }
}