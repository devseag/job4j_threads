package ru.job4j.concurrent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamExample {
    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

//        Optional<Integer> multiplication = list.stream()
//                .reduce((a, b) -> a * b);
//        System.out.println(multiplication.get());

//        Stream<Integer> stream = list.parallelStream();
//        System.out.println(stream.isParallel());
//        Optional<Integer> multiplication = stream.reduce((a, b) -> a * b);
//        System.out.println(multiplication.get());
////        true
////        120

//        IntStream parallel = IntStream.range(1, 100).parallel();
//        System.out.println(parallel.isParallel());
//        IntStream sequential = parallel.sequential();
//        System.out.println(sequential.isParallel());
////        true
////        false

//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        list.stream().parallel().peek(System.out::println).toList();
////        5
////        4
////        3
////        1
////        2

//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        list.stream().parallel().forEach(System.out::println);
////        3
////        5
////        4
////        2
////        1

        List<Integer> list = Arrays.asList(1, 3, 4, 5, 2);
        list.stream().parallel().forEachOrdered(System.out::println);
//        1
//        3
//        4
//        5
//        2
    }
}