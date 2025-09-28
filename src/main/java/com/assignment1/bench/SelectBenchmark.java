package com.assignment1.bench;

import com.assignment1.algos.DeterministicSelect;
import com.assignment1.core.Metrics;
import com.assignment1.core.Utils;

public class SelectBenchmark {
    public static void main(String[] args) {
        int n = 1_000_000;
        int[] arr = Utils.randomArray(n, n);
        Metrics metrics = new Metrics();

        long start = System.currentTimeMillis();
        int median = DeterministicSelect.select(arr, n / 2, metrics);
        long end = System.currentTimeMillis();

        System.out.println("Median-of-Medians select result: " + median);
        System.out.println("Time (ms): " + (end - start));
        System.out.println(metrics);
    }
}
