package org.example;


public class PriorityCheck {
    public static void main (String [] args) {
        new PriorityThread(Thread.MIN_PRIORITY).start();
        new PriorityThread(Thread.NORM_PRIORITY).start();
        new PriorityThread(Thread.MAX_PRIORITY).start();
    }
}


class PriorityThread extends Thread {

    public PriorityThread(int priority) {
        setPriority(priority);
    }

    @Override
    public void run() {
        System.out.printf("Thread with priority %d is running\n", getPriority());
        for (int i = 1; i <= 50; i++) {
        }
    }

};


