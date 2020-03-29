package sortingalgorithms;

import java.util.Arrays;

//Given n d-digit numbers in which each digit can take on up to k possible values,
//RADIX-SORT correctly sorts these numbers in O(d(n + k)) time if the stable sort
//it uses takes O(n + k) time.
public class RadixSort {
    private CountingSort sort;

    public RadixSort() {
        sort = new CountingSort();
    }

    //need to get the max element in the array, in order to figure out
    //the max number of digits to sort
    public int getMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return Arrays.stream(arr).max().getAsInt();
    }

    public int[] radixSort(int[] arr) {
        int max = getMax(arr);
        for (int digit = 1; max / digit > 0; digit *= 10) {
            arr = sort.countingSortWithRadix(arr, new int[arr.length], digit);
        }
        return arr;
    }
}
