import java.util.Scanner;

class NumbersFilter extends Thread {

    /* use it to read numbers from the standard input */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (scanner.hasNextLine()){
            int num = scanner.nextInt();
            if(num==0)
                this.stop();
            if(num%2==0)
                System.out.println(num);

        }

    }
}