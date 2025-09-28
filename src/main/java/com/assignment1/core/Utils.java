package com.assignment1.core;

import java.util.Random;

public class Utils {
    private static final Random rand = new Random();

    public static int[] randomArray(int n, int maxVal) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(maxVal);
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            metrics.addComparisons(1);
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                metrics.addComparisons(1);
                j--;
            }
            arr[j + 1] = key;
            metrics.addAllocations(1);
        }
    }
}
