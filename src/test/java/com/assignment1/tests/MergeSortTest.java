package com.assignment1.tests;

import com.assignment1.algos.MergeSort;
import com.assignment1.core.Metrics;
import com.assignment1.core.Utils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    public void testRandomArray() {
        int[] arr = Utils.randomArray(100, 1000);
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
        System.out.println(metrics);
    }

    @Test
    public void testSortedArray() {
        int[] arr = new int[50];
        for (int i = 0; i < arr.length; i++) arr[i] = i;
        int[] expected = arr.clone();

        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testReverseArray() {
        int[] arr = new int[50];
        for (int i = 0; i < arr.length; i++) arr[i] = 50 - i;
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
    }
}
