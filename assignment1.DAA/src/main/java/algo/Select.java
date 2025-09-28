package algo;

import metrics.*;
import java.util.Arrays;

public class Select {
    private final Metrics metrics;

    public Select(Metrics metrics) {
        this.metrics = metrics;
    }

    public int select(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private int select(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        metrics.enterRecursion();
        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);
        int length = pivotIndex - left + 1;
        int result;
        if (k == length) result = arr[pivotIndex];
        else if (k < length) result = select(arr, left, pivotIndex - 1, k);
        else result = select(arr, pivotIndex + 1, right, k - length);
        metrics.exitRecursion();
        return result;
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }
        int[] medians = new int[(n + 4) / 5];
        for (int i = 0; i < medians.length; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }
        return medianOfMedians(medians, 0, medians.length - 1);
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
            while (arr[left] < pivot) { metrics.incComparisons(); left++; }
            while (arr[right] > pivot) { metrics.incComparisons(); right--; }
            if (left <= right) {
                int tmp = arr[left]; arr[left] = arr[right]; arr[right] = tmp;
                left++; right--;
            }
        }
        return left - 1;
    }
}
