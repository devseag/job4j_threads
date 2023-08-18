package ru.job4j.concurrent;

public class DebugConcurrentDemo {
    public static void main(String[] args) {
        String name = "Pervyj potok";
        String name1 = "Vtoroj potok";
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " prervan ");
            }
            System.out.println(Thread.currentThread().getName() + " zavershen.");
        }, name);
        Thread t2 = new Thread(() -> {
            try {
                for (int i = 10; i < 13; i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " prervan ");
            }
            System.out.println(Thread.currentThread().getName() + " zavershen.");
        }, name1);
        t1.start();
        t2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Glavnyj potok prervan ");
        }
        System.out.println("Glavnyj potok zavershen.");
    }
}

//    Vtoroj potok : 10
//        Pervyj potok : 3
//        Pervyj potok : 2
//        Vtoroj potok : 11
//        Pervyj potok : 1
//        Vtoroj potok : 12
//        Pervyj potok zavershen.
//        Vtoroj potok zavershen.
//        Glavnyj potok zavershen.

//    Vtoroj potok : 10
//        Pervyj potok : 3
//        Pervyj potok : 2
//        Vtoroj potok : 11
//        Vtoroj potok : 12
//        Pervyj potok : 1
//        Vtoroj potok zavershen.
//        Pervyj potok zavershen.
//        Glavnyj potok zavershen.


