package org.example;

public class TheBlockingClassSync {
    public static void main(String[]args) {
        BlockingThread t1 = new BlockingThread();
        BlockingThread t2 = new BlockingThread();

        t1.setBlockingThread(t2);
        t2.setBlockingThread(t1);

        t1.start();
        t2.start();
    }
}

class BlockingThread extends Thread {
    private Thread blockingThread;

    public void setBlockingThread(Thread blockingThread) {
        this.blockingThread = blockingThread;
    }

    /**
     * викликаємо метод з пониженним значенням таймера
     */
    @Override
    public void run() {
        synchronized (this) {
            if (blockingThread==null) throw new NullPointerException();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (blockingThread) {
                // some work
            }

            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }
}
