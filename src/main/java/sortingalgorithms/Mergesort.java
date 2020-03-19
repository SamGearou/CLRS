package sortingalgorithms;

public class Mergesort {

    public static void main(String[] args) {
        int[] arr = {42, 5, 6, 78, 5, 4, 34, 5, 6, 5, 4, 3, 4, 56, 77, 4, 3};
        printArray(mergeSort(arr, 0, arr.length - 1));
    }

    public static int[] mergeSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
        return arr;
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int num1 = q - p + 1;
        int num2 = r - q;
        int[] left = new int[num1 + 1];
        int[] right = new int[num2 + 1];
        for (int i = 0; i < num1; i++) {
            left[i] = arr[p + i];
        }
        for (int i = 0; i < num2; i++) {
            right[i] = arr[q + 1 + i];
        }
        left[num1] = Integer.MAX_VALUE;
        right[num2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (int k = p; k <= r; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int ele : arr) {
            System.out.println(ele);
        }
    }
}
