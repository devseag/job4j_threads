package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<int[]> {

    private final int[] array;
    private final int from;
    private final int to;

    public ParallelMergeSort(int[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    protected int[] compute() {
        if (from == to) {
            return new int[] {array[from]};
        }
        int mid = (from + to) / 2;
        // sozdaem zadachi dlja sortirovki chastej
        ParallelMergeSort leftSort = new ParallelMergeSort(array, from, mid);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, to);
        // proizvodim delenie.
        // ono budet proishodit', poka v chastjah ne ostanetsja po odnomu jelementu
        leftSort.fork();
        rightSort.fork();
        // ob#edinjaem poluchennye rezul'taty
        int[] left = leftSort.join();
        int[] right = rightSort.join();
        return MergeSort.merge(left, right);
    }

    public static int[] sort(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
    }

}