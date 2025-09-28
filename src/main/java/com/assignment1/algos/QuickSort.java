package com.assignment1.algos;

import com.assignment1.core.Metrics;
import com.assignment1.core.Utils;

import java.util.Random;

public class QuickSort {

    private static final Random rand = new Random();

    public static void sort(int[] arr, Metrics metrics) {
        quickSort(arr, 0, arr.length - 1, metrics);
    }

    private static void quickSort(int[] arr, int left, int right, Metrics metrics) {
        metrics.enterRecursion();
        if (left >= right) {
            metrics.exitRecursion();
            return;
        }

        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        Utils.swap(arr, pivotIndex, right);

        int i = left, j = right - 1;
        while (i <= j) {
            metrics.addComparisons(1);
            while (i <= j && arr[i] < pivot) {
                i++;
                metrics.addComparisons(1);
            }
            metrics.addComparisons(1);
            while (i <= j && arr[j] > pivot) {
                j--;
                metrics.addComparisons(1);
            }
            if (i <= j) {
                Utils.swap(arr, i++, j--);
                metrics.addAllocations(1);
            }
        }

        Utils.swap(arr, i, right);
        metrics.addAllocations(1);

        // recurse on smaller partition first
        if (i - 1 - left < right - (i + 1)) {
            quickSort(arr, left, i - 1, metrics);
            quickSort(arr, i + 1, right, metrics);
        } else {
            quickSort(arr, i + 1, right, metrics);
            quickSort(arr, left, i - 1, metrics);
        }
        metrics.exitRecursion();
    }
}
