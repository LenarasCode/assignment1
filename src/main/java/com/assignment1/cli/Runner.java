package com.assignment1.cli;

import com.assignment1.algos.MergeSort;
import com.assignment1.algos.QuickSort;
import com.assignment1.algos.DeterministicSelect;
import com.assignment1.algos.ClosestPair;
import com.assignment1.core.Metrics;
import com.assignment1.core.Point;
import com.assignment1.core.Utils;

import java.io.FileWriter;
import java.io.IOException;

public class Runner {

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000};  // размеры массивов
        String desktopPath = System.getProperty("user.home") + "/Desktop/metrics.csv";

        try (FileWriter writer = new FileWriter(desktopPath)) {
            writer.write("Algorithm,n,Time_ms,Depth,Comparisons,Allocations\n");

            for (int n : sizes) {
                runMergeSort(n, writer);
                runQuickSort(n, writer);
                runDeterministicSelect(n, writer);
                runClosestPair(n, writer);
            }

            System.out.println("All algorithms finished. Metrics saved to: " + desktopPath);
        } catch (IOException e) {
            System.err.println("Error writing metrics: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void runMergeSort(int n, FileWriter writer) throws IOException {
        int[] arr = Utils.randomArray(n, n);
        Metrics metrics = new Metrics();
        long start = System.currentTimeMillis();
        MergeSort.sort(arr, metrics);
        long end = System.currentTimeMillis();

        writer.write(String.format("MergeSort,%d,%d,%d,%d,%d\n",
                n, end - start, metrics.maxDepth, metrics.comparisons, metrics.allocations));
    }

    private static void runQuickSort(int n, FileWriter writer) throws IOException {
        int[] arr = Utils.randomArray(n, n);
        Metrics metrics = new Metrics();
        long start = System.currentTimeMillis();
        QuickSort.sort(arr, metrics);
        long end = System.currentTimeMillis();

        writer.write(String.format("QuickSort,%d,%d,%d,%d,%d\n",
                n, end - start, metrics.maxDepth, metrics.comparisons, metrics.allocations));
    }

    private static void runDeterministicSelect(int n, FileWriter writer) throws IOException {
        int[] arr = Utils.randomArray(n, n);
        Metrics metrics = new Metrics();
        int k = n / 2;
        long start = System.currentTimeMillis();
        DeterministicSelect.select(arr, k, metrics);
        long end = System.currentTimeMillis();

        writer.write(String.format("DeterministicSelect,%d,%d,%d,%d,%d\n",
                n, end - start, metrics.maxDepth, metrics.comparisons, metrics.allocations));
    }

    private static void runClosestPair(int n, FileWriter writer) throws IOException {
        // Создание случайных точек
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(Math.random() * 10000, Math.random() * 10000);
        }

        Metrics metrics = new Metrics();
        long start = System.currentTimeMillis();
        ClosestPair.findClosest(points, metrics);
        long end = System.currentTimeMillis();

        writer.write(String.format("ClosestPair,%d,%d,%d,%d,%d\n",
                n, end - start, metrics.maxDepth, metrics.comparisons, metrics.allocations));
    }
}
