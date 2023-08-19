package ru.job4j.concurrent;

public class Count {
    private int value;

    public synchronized void increment() {
        value++;
    }

//    public void increment() {
//        synchronized (this) {
//            value++;
//        }
//    }

    public synchronized int get() {
        return value;
    }

//    public int get() {
//        synchronized (this) {
//            return value;
//        }
//    }
}