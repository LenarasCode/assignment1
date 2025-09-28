package com.assignment1.algos;

import com.assignment1.core.Metrics;
import com.assignment1.core.Point;

public class ClosestPair {

    public static double findClosest(Point[] points, Metrics metrics) {
        int n = points.length;
        double minDist = Double.MAX_VALUE;

        metrics.enterRecursion();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                metrics.addComparisons(1);
                double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;
                double dist = Math.sqrt(dx * dx + dy * dy);
                metrics.addAllocations(1);
                if (dist < minDist) minDist = dist;
            }
        }
        metrics.exitRecursion();
        return minDist;
    }
}
