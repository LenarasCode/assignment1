package com.assignment1.tests;

import com.assignment1.algos.DeterministicSelect;
import com.assignment1.core.Metrics;
import com.assignment1.core.Utils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeterministicSelectTest {

    @Test
    public void testSelectMedian() {
        int[] arr = Utils.randomArray(101, 1000);
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int expected = sorted[sorted.length / 2];

        Metrics metrics = new Metrics();
        int median = DeterministicSelect.select(arr, arr.length / 2, metrics);

        assertEquals(expected, median);
        System.out.println(metrics);
    }

    @Test
    public void testSelectMinMax() {
        int[] arr = Utils.randomArray(50, 1000);
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Metrics metrics = new Metrics();
        int min = DeterministicSelect.select(arr, 0, metrics);
        int max = DeterministicSelect.select(arr, arr.length - 1, metrics);

        assertEquals(sorted[0], min);
        assertEquals(sorted[arr.length - 1], max);
    }
}
