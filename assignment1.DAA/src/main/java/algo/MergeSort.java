package algo;

import metrics.*;

public class MergeSort {
    private final Metrics metrics;
    private final int cutoff = 10; // маленькие массивы сортируем вставками

    public MergeSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (right - left + 1 <= cutoff) {
            insertionSort(arr, left, right);
            return;
        }

        metrics.enterRecursion();
        int mid = (left + right) / 2;
        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);
        merge(arr, buffer, left, mid, right);
        metrics.exitRecursion();
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                metrics.incComparisons();
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.incComparisons();
            if (arr[i] <= arr[j]) buffer[k++] = arr[i++];
            else buffer[k++] = arr[j++];
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];
        for (int x = left; x <= right; x++) arr[x] = buffer[x];
    }
}
