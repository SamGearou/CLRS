package sortingalgorithms;

//Insertion Sort is a stable sort
public class InsertionSort {

    public static void main(String[] args){
        int[] unsorted = {3,5,7,3,2,5,7,85,3,3,57};
        printArray(InsertionSort.sortDecreasing(unsorted));
    }
    private static int[] sortIncreasing(int[] arr){
        for(int j = 1; j<arr.length; j++){
            int key = arr[j];
            int i = j - 1;
            while(i >= 0 && arr[i] > key){
                arr[i+1] = arr[i];
                i--;
            }
            arr[i+1] = key;
        }
        return arr;
    }

    private static int[] sortDecreasing(int[] arr){
        for(int j = 1; j<arr.length; j++){
            int key = arr[j];
            int i = j-1;
            while(i >= 0 && arr[i] < key){
                arr[i+1] = arr[i];
                i--;
            }
            arr[i+1] = key;
        }
        return arr;
    }

    public static void printArray(int[] arr){
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }
}
