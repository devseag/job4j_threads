package ru.job4j.pool;


import net.jcip.annotations.ThreadSafe;
import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

@ThreadSafe
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            );
            thread.start();
            threads.add(thread);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 1; i <= 10; i++) {
            int number = i;
            Runnable runnable = () -> System.out.println("Thread executes with " + number);
            threadPool.work(runnable);
        }
        threadPool.shutdown();
    }
}