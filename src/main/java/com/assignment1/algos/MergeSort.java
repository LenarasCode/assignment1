package com.assignment1.algos;

import com.assignment1.core.Metrics;
import com.assignment1.core.Utils;

public class MergeSort {

    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Metrics metrics) {
        int[] buffer = new int[arr.length];
        sort(arr, 0, arr.length - 1, buffer, metrics);
    }

    private static void sort(int[] arr, int left, int right, int[] buffer, Metrics metrics) {
        metrics.enterRecursion();
        if (right - left + 1 <= CUTOFF) {
            Utils.insertionSort(arr, left, right, metrics);
            metrics.exitRecursion();
            return;
        }

        int mid = left + (right - left) / 2;
        sort(arr, left, mid, buffer, metrics);
        sort(arr, mid + 1, right, buffer, metrics);

        merge(arr, left, mid, right, buffer, metrics);
        metrics.exitRecursion();
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] buffer, Metrics metrics) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);
        metrics.addAllocations(right - left + 1);

        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.addComparisons(1);
            if (buffer[i] <= buffer[j]) arr[k++] = buffer[i++];
            else arr[k++] = buffer[j++];
            metrics.addAllocations(1);
        }

        while (i <= mid) arr[k++] = buffer[i++];
        while (j <= right) arr[k++] = buffer[j++];
    }
}
