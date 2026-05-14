package app;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        DataRepository repository = new DataRepository();
        DataHandler dataHandler = new DataHandler();

        // 1. Getting a shared resource (array)
        int[] sharedArray = repository.getData();
        System.out.println("Стартовый массив: " + Arrays.toString(sharedArray));
        System.out.println("=== Старт потоков через ExecutorService ===\n");

        // 2. Creation of a pool from 2 fixed streams
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 3. Sending two competing tasks for execution
        executor.submit(() -> dataHandler.handleData(sharedArray));
        executor.submit(() -> dataHandler.handleData(sharedArray));

        // 4. We initiate the correct completion of the task pool
        executor.shutdown();

        // 5. The main thread (main) is waiting for the completion of all tasks
        // (maximum 5 seconds)
        if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("\n=== Все задачи успешно завершены ===");
        } else {
            System.out.println("\n=== Время ожидания истекло, задачи прерваны ===");
        }

        // 6. Output of the final state of the common resource
        System.out.println("Финальный массив: " + Arrays.toString(sharedArray));
    }
}