package app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        DataRepository repository = new DataRepository();
        DataHandler dataHandler = new DataHandler();

        // 1. Getting a shared resource (array)
        int[] sharedArray = repository.getData();

        // 2. Creation of a pool with 2 fixed threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 3. Sending two competing tasks for execution
        executor.submit(() -> dataHandler.handleData(sharedArray));
        executor.submit(() -> dataHandler.handleData(sharedArray));

        // 4. Initiate orderly shutdown of the thread pool
        executor.shutdown();

        // 5. The main thread waits for tasks completion (maximum 5 seconds)
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}