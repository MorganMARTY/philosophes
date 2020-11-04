package diningphilosophers;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher
        extends Thread {

    private static int seed = 1;
    private final Random myRandom = new Random(System.currentTimeMillis() + seed++);
    private final static int DELAY = 1000;
    private final String myName;
    private final ChopStick myLeftStick;
    private final ChopStick myRightStick;
    private boolean jeContinue = true;

    public Philosopher(String name, ChopStick left, ChopStick right) {
        myName = name;
        myLeftStick = left;
        myRightStick = right;
    }

    @Override
    public void run() {
        while (jeContinue) {
            try {
                think();

                if (takeStick(myLeftStick)) {
                    if (takeStick(myRightStick)) {
                        eat();
                        releaseStick(myLeftStick);
                        releaseStick(myRightStick);
                    } else {
                        releaseStick(myLeftStick);
                    }
                }

            } catch (InterruptedException ex) {
                Logger.getLogger("Table").log(Level.SEVERE, "{0} Interrupted", this.getName());
            }
        }
    }

    public void leaveTable() {
        jeContinue = false;
    }

    private boolean takeStick(ChopStick stick) throws InterruptedException {
        int delay = myRandom.nextInt(100 + DELAY);
        boolean result = stick.tryTake(delay);
        if (result) {
            System.out.println(myName + " prends " + stick + " en " + delay + " ms");
        } else {
            System.out.println(myName + " n'a pas pu prendre " + stick + " en " + delay + " ms");
        }
        return result;
    }

    private void releaseStick(ChopStick stick) {
        stick.release();
    }

    private void think() {
        int delay = myRandom.nextInt(100 + DELAY);
        System.out.println(myName + " Commence à penser pendant :  " + delay + " ms");
        try {
            sleep(delay);
        } catch (InterruptedException ex) {
        }
        System.out.println(myName + " Arrete de penser");
    }

    private void eat() {
        int delay = myRandom.nextInt(100 + DELAY);
        System.out.println(myName + " Commence à manger pendant :" + delay + " ms");
        try {
            sleep(delay);
        } catch (InterruptedException ex) {
        }
        System.out.println(myName + " Arrête de manger");
    }
}
