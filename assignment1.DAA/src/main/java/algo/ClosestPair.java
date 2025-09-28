package algo;

import metrics.*;
import java.util.*;

public class ClosestPair {
    private final Metrics metrics;

    public ClosestPair(Metrics metrics) {
        this.metrics = metrics;
    }

    public double findClosest(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
        return closest(points, 0, points.length - 1);
    }

    private double closest(Point[] pts, int left, int right) {
        if (right - left <= 3) {
            double min = Double.MAX_VALUE;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    metrics.incComparisons();
                    min = Math.min(min, distance(pts[i], pts[j]));
                }
            }
            return min;
        }
        int mid = (left + right) / 2;
        double d1 = closest(pts, left, mid);
        double d2 = closest(pts, mid + 1, right);
        double d = Math.min(d1, d2);

        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++)
            if (Math.abs(pts[i].x - pts[mid].x) < d)
                strip.add(pts[i]);

        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                metrics.incComparisons();
                d = Math.min(d, distance(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }

    private double distance(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static class Point {
        double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }
}
