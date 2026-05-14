package app;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataHandler {

    private final Lock lock = new ReentrantLock();

    public void handleData(int[] sharedArray) {
        String threadName = Thread.currentThread().getName();

        System.out.println("[" + threadName + "] wants to take a seat");
        lock.lock();

        try {
            System.out.println("[" + threadName + "] taking a lock");

            for (int i = 0; i < sharedArray.length; i++) {
                sharedArray[i] = sharedArray[i] * 3;
            }

            System.out.println("[" + threadName + "] want to give you a lock");
        } finally {
            lock.unlock();
            System.out.println("[" + threadName + "] gave the lock");
        }
    }
}