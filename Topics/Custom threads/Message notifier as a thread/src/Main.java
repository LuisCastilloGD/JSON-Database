class MessageNotifier extends Thread {

    // write fields to store variables here

    private int repeats;
    private String msg;
    public MessageNotifier(String msg, int repeats) {
        this.msg = msg;
        this.repeats = repeats;
    }

    @Override
    public void run() {

        for (int i = 0; i < repeats; i++) {
            System.out.println(msg);
        }
        // implement the method to print the message stored in a field
    }
}