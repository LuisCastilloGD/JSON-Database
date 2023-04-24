import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int THREADS = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            PrintIfPrimeTask p = new PrintIfPrimeTask(number);
            executor.submit(p::run);
        }
        executor.shutdown();
    }
}

class PrintIfPrimeTask implements Runnable {
    private final int number;

    public PrintIfPrimeTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        if (number<= 1) {
            return;
            //return false;
        }
        for (int i = 2; i< number; i++) {
            if (number % i == 0) {
                return;
                //return false;
            }
        }
        System.out.println(number);
        //return true;
    }
}