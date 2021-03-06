package sortingalgorithms;


import java.util.Random;

public class QuickSort {

    //not a stable sort, because it swaps non-adjacent elements
    //simple example: [2,2,1]
    public void quickSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
            quickSort(arr, p, q - 1);
            quickSort(arr, q + 1, r);
        }
    }

    public void randomizedQuickSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(arr, p, r);
            randomizedQuickSort(arr, p, q - 1);
            randomizedQuickSort(arr, q + 1, r);
        }
    }

    public int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, r);
        return i + 1;
    }

    public int randomizedPartition(int[] arr, int p, int r) {
        int i = p + new Random().nextInt(r - p + 1);
        swap(arr, i, r);
        return partition(arr, p, r);
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
