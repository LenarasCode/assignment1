package com.assignment1.algos;

import com.assignment1.core.Metrics;
import com.assignment1.core.Utils;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics metrics) {
        return select(arr, 0, arr.length - 1, k, metrics);
    }

    private static int select(int[] arr, int left, int right, int k, Metrics metrics) {
        metrics.enterRecursion();
        if (left == right) {
            metrics.exitRecursion();
            return arr[left];
        }

        int pivotIndex = medianOfMedians(arr, left, right, metrics);
        int pivotFinal = partition(arr, left, right, pivotIndex, metrics);

        if (k == pivotFinal) {
            metrics.exitRecursion();
            return arr[k];
        } else if (k < pivotFinal) {
            int result = select(arr, left, pivotFinal - 1, k, metrics);
            metrics.exitRecursion();
            return result;
        } else {
            int result = select(arr, pivotFinal + 1, right, k, metrics);
            metrics.exitRecursion();
            return result;
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics metrics) {
        int pivotValue = arr[pivotIndex];
        Utils.swap(arr, pivotIndex, right);
        metrics.addAllocations(1);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.addComparisons(1);
            if (arr[i] < pivotValue) {
                Utils.swap(arr, storeIndex, i);
                metrics.addAllocations(1);
                storeIndex++;
            }
        }
        Utils.swap(arr, storeIndex, right);
        metrics.addAllocations(1);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;
        if (n <= 5) {
            Utils.insertionSort(arr, left, right, metrics);
            return left + n / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Utils.insertionSort(arr, i, subRight, metrics);
            int medianIndex = i + (subRight - i) / 2;
            Utils.swap(arr, left + numMedians, medianIndex);
            metrics.addAllocations(1);
            numMedians++;
        }
        return select(arr, left, left + numMedians - 1, left + numMedians / 2, metrics);
    }
}
