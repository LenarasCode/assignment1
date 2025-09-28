package com.assignment1.tests;

import com.assignment1.algos.ClosestPair;
import com.assignment1.core.Metrics;
import com.assignment1.core.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    public void testSmallSet() {
        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(3, 4),   // distance = 5
                new Point(1, 1)    // distance ~1.414 to (0,0)
        };

        Metrics metrics = new Metrics();
        double minDist = ClosestPair.findClosest(points, metrics);

        // минимальное расстояние должно быть между (0,0) и (1,1)
        assertEquals(Math.sqrt(2), minDist, 1e-6);
    }

    @Test
    public void testTwoPoints() {
        Point[] points = new Point[] {
                new Point(2, 3),
                new Point(5, 7)
        };

        Metrics metrics = new Metrics();
        double minDist = ClosestPair.findClosest(points, metrics);

        double expected = Math.sqrt((5 - 2)*(5 - 2) + (7 - 3)*(7 - 3));
        assertEquals(expected, minDist, 1e-6);
    }

    @Test
    public void testIdenticalPoints() {
        Point[] points = new Point[] {
                new Point(1, 1),
                new Point(1, 1),
                new Point(2, 2)
        };

        Metrics metrics = new Metrics();
        double minDist = ClosestPair.findClosest(points, metrics);

        // минимальное расстояние между двух одинаковых точек = 0
        assertEquals(0.0, minDist, 1e-6);
    }

    @Test
    public void testLargeRandomSet() {
        int n = 100;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(Math.random() * 1000, Math.random() * 1000);
        }

        Metrics metrics = new Metrics();
        double minDist = ClosestPair.findClosest(points, metrics);

        // Проверка, что возвращается положительное число
        assert(minDist >= 0);
    }
}
