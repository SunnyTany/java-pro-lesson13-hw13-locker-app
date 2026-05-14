package app;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataHandler {

    // Initializing the ReentrantLock lock object
    private final Lock lock = new ReentrantLock();

    // The synchronized keyword has been removed since Lock is used
    public int modify(int num) {
        lock.lock(); // Acquiring a lock before performing an operation
        try {
            num = num * 3;
            return num;
        } finally {
            lock.unlock(); // Mandatory deallocation in the finally block
        }
    }
}