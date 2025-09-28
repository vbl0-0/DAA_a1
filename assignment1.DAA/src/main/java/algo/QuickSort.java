package algo;

import metrics.*;
import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private final Random random = new Random();

    public QuickSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        while (left < right) {
            metrics.enterRecursion();
            int pivotIndex = left + random.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];
            int i = left, j = right;
            while (i <= j) {
                while (arr[i] < pivot) { metrics.incComparisons(); i++; }
                while (arr[j] > pivot) { metrics.incComparisons(); j--; }
                if (i <= j) {
                    int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
                    i++; j--;
                }
            }
            metrics.exitRecursion();
            if (j - left < right - i) {
                quickSort(arr, left, j);
                left = i;
            } else {
                quickSort(arr, i, right);
                right = j;
            }
        }
    }
}
