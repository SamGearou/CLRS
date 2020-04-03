package orderstatistic;

import sortingalgorithms.QuickSort;

import java.util.ArrayList;
import java.util.Collections;

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
    //distinct elements
    public int quickSelect(int[] A, int p, int r, int i) {
        if (p >= r) {
            return A[p];
        } else {
            ArrayList<Pair> medians = new ArrayList<>();
            for (int j = p; j <= r; j += 5) {
                int median = findMedian(A, j, Math.min(j + 4, r));
                medians.add(new Pair(A[median], median));
            }
            int pivot;
            Collections.sort(medians);
            if (medians.size() % 2 == 0) {
                pivot = medians.get(medians.size() / 2 - 1).getIndex();
            } else {
                pivot = medians.get(medians.size() / 2).getIndex();
            }
            System.out.println(pivot + " " + A[pivot]);
            quickSort.swap(A, pivot, r);
            int q = quickSort.partition(A, p, r);
            System.out.println("q: " + q);
            int k = q - p + 2;
            if (i == k) {
                return A[q];
            } else if (i < k) {
                return quickSelect(A, p, q - 1, i);
            } else {
                return quickSelect(A, q - 1, r, i - k);
            }
        }
    }

    public int findMedian(int[] arr, int p, int r) {
        ArrayList<Pair> sortedList = new ArrayList<>();
        for (int i = p; i <= r; i++) {
            sortedList.add(new Pair(arr[i], i));
        }
        Collections.sort(sortedList);
        if (sortedList.size() % 2 == 0) {
            return sortedList.get(sortedList.size() / 2 - 1).getIndex();
        } else {
            return sortedList.get(sortedList.size() / 2).getIndex();
        }
    }

    public class Pair implements Comparable<Pair> {
        private int value;
        private int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.getValue() == other.getValue()) {
                return this.getIndex() - other.getIndex();
            } else {
                return this.getValue() - other.getValue();
            }
        }
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        OrderStatistic os = new OrderStatistic(qs);
        int[] arr = {4, 2, 3, 8, 9};
        System.out.println(os.quickSelect(arr, 0, arr.length - 1, 1));
    }
}
