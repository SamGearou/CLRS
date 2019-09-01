package sortingalgorithms;

public class SelectionSort {

    public static void main(String[] args) {
        int[] unsorted = {5, 6, 7, 5, 3, 34, 5, 67, 7, 6, 5, 345, 6, 54};
        printArray(sortIncreasing(unsorted));
    }

    private static int[] sortIncreasing(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            int j = i + 1;
            int min = i;
            while (j < arr.length) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
                j++;
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }

    private static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }
}
