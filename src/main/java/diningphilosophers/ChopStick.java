package diningphilosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
    // Le nombre total de baguettes
    private static int stickCount = 0;
    // Le numéro de chaque baguette
    private final int myNumber;
    // Est-ce que ma baguette est libre ?
    private boolean iAmFree = true;
    private final Lock verrou = new ReentrantLock();

    public ChopStick() {
        // Chaque baguette est numérotée 
        myNumber = ++stickCount;
    }

    public boolean tryTake(int delay) throws InterruptedException {
        return verrou.tryLock(delay, TimeUnit.MILLISECONDS);
    }

    public void release() {
            verrou.unlock();
    }
    
    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
