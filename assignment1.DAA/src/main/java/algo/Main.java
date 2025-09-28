package algo;

import metrics.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Metrics metrics = new Metrics();
        Random rand = new Random();

        int[] sizes = {10, 100, 1000};
        System.out.printf("%-12s %-10s %-12s %-12s %-5s%n",
                "Algorithm", "n", "Time(ns)", "Comparisons", "Depth");

        for (int n : sizes) {
            int[] arr = rand.ints(n, 0, 1000).toArray();

            // MergeSort
            metrics.reset();
            int[] copy1 = arr.clone();
            long start = System.nanoTime();
            new MergeSort(metrics).sort(copy1);
            long time = System.nanoTime() - start;
            CSVWriter.printRow("MergeSort", n, time, metrics.getComparisons(), metrics.getMaxDepth());

            // QuickSort
            metrics.reset();
            int[] copy2 = arr.clone();
            start = System.nanoTime();
            new QuickSort(metrics).sort(copy2);
            time = System.nanoTime() - start;
            CSVWriter.printRow("QuickSort", n, time, metrics.getComparisons(), metrics.getMaxDepth());

            // Select (k = n/2)
            metrics.reset();
            int[] copy3 = arr.clone();
            start = System.nanoTime();
            int kth = new Select(metrics).select(copy3, n/2);
            time = System.nanoTime() - start;
            CSVWriter.printRow("Select", n, time, 0, metrics.getMaxDepth());

            // Closest Pair
            metrics.reset();
            ClosestPair.Point[] pts = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++)
                pts[i] = new ClosestPair.Point(rand.nextDouble()*100, rand.nextDouble()*100);
            start = System.nanoTime();
            double dist = new ClosestPair(metrics).findClosest(pts);
            time = System.nanoTime() - start;
            CSVWriter.printRow("ClosestPair", n, time, 0, metrics.getMaxDepth());
        }
    }
}
