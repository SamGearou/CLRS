package orderstatistic;

import sortingalgorithms.QuickSort;

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
}
