package app;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataHandler {

    // Lock to prevent concurrent modification of the shared array
    private final Lock lock = new ReentrantLock();

    public void handleData(int[] sharedArray) {
        // Acquire the lock before accessing the shared resource
        lock.lock();

        try {
            // Process and modify each element of the array
            for (int i = 0; i < sharedArray.length; i++) {
                int initialValue = sharedArray[i];
                sharedArray[i] = initialValue * 3;

                System.out.println("Initial value is " + initialValue);
                System.out.println("New value is " + sharedArray[i]);
            }
        } finally {
            // Always release the lock in a finally block
            lock.unlock();
        }
    }
}