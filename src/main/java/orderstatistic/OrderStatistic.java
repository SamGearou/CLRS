package orderstatistic;

import sortingalgorithms.QuickSort;

import java.util.Arrays;

//The ith order statistic of a set of n elements is the ith smallest element. For
//example, the minimum of a set of elements is the first order statistic (i = 1),
//and the maximum is the nth order statistic (i = n)
public class OrderStatistic {
    private QuickSort quickSort;

    public OrderStatistic(QuickSort quickSort) {
        this.quickSort = quickSort;
    }


    //The worst-case running time for RANDOMIZED-SELECT is Theta(n^2), even to find
    //the minimum, because we could be extremely unlucky and always partition around
    //the largest remaining element, and partitioning takes Theta(n) time
    //However, this algorithm has an expected running time of O(n)
    public int randomizedSelect(int[] A, int p, int r, int i) {
        if (A == null || A.length == 0) {
            return -1;
        }
        if (p == r) {
            return A[p];
        }
        int q = quickSort.randomizedPartition(A, p, r);
        int k = q - p + 1;
        if (i == k) {
            return A[q];
        } else if (i < k) {
            return randomizedSelect(A, p, q - 1, i);
        } else {
            return randomizedSelect(A, q + 1, r, i - k);
        }
    }

    //This algorithm determines the ith smallest of an input array of n > 1
    //distinct elements in O(n) worst-case time
    public int quickSelect(int[] A, int p, int r, int i) {
        int[] medians = new int[(int) (Math.ceil((r - p + 1) / 5f))];
        int ind = 0;
        int pivot;
        for (int j = p; j <= r; j += 5) {
            int median = findMedian(A, j, Math.min(j + 4, r));
            medians[ind++] = median;
        }
        if (medians.length <= 5) {
            pivot = findMedian(medians, 0, medians.length - 1);
        } else {
            pivot = quickSelect(medians, 0, medians.length - 1, (int) (Math.ceil(medians.length / 2f) - 1));
        }
        int pivotInd = findPivotInd(A, pivot);
        quickSort.swap(A, pivotInd, r);
        int q = quickSort.partition(A, p, r);
        int k = q - p + 1;
        if (i == k) {
            return A[q];
        } else if (i < k) {
            return quickSelect(A, p, q - 1, i);
        } else {
            return quickSelect(A, q + 1, r, i - k);
        }
    }

    public int findMedian(int[] arr, int p, int r) {
        if (p == r) {
            return arr[p];
        }
        Arrays.sort(arr, p, r + 1);
        return arr[(int) (Math.ceil((p + r) / 2f) - 1)];
    }

    public int findPivotInd(int[] arr, int pivot) {
        int ind = 0;
        for (int x : arr) {
            if (x == pivot) {
                return ind;
            }
            ind++;
        }
        return -1;
    }
}
