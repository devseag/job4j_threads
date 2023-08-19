package ru.job4j.concurrent;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

//    public synchronized void count() {
//        monitor.notifyAll();
//        count++;
//    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

//    public synchronized void await() {
//        while (count < total) {
//            try {
//                monitor.wait();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//    }

    public void await() {
        synchronized (monitor) {
            while (count <= total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
