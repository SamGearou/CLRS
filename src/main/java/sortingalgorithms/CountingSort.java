package sortingalgorithms;

//Counting sort assumes that each of the n input elements is an integer in the range
//0 to k, for some integer k. When k = O(n), the sort runs in Theta(n) time
public class CountingSort {

    //stable sort, time complexity: O(n + k), where n is A.length, and k = max elem in A
    public int[] countingSort(int[] A, int[] B, int k) {
        if (k < 1 || A == null || B == null) {
            return null;
        }
        int[] C = new int[k + 1];
        for (int i = 0; i < A.length; i++) {
            C[A[i]] += 1;
        }
        for (int i = 1; i <= k; i++) {
            C[i] += C[i - 1];
        }
        //could go from i = 0 to B.length-1, but this would make the algorithm not stable
        for (int i = B.length - 1; i >= 0; i--) {
            B[C[A[i]] - 1] = A[i];
            C[A[i]]--;
        }
        return B;
    }

    //radix sort can utilize this stable sort algorithm as a sub-procedure
    public int[] countingSortWithRadix(int[] A, int[] B, int digit){
        if (A == null || B == null) {
            return null;
        }
        int[] C = new int[10];
        for (int i = 0; i < A.length; i++) {
            C[A[i]/digit%10] += 1;
        }
        for (int i = 1; i < 10; i++) {
            C[i] += C[i - 1];
        }
        //could go from i = 0 to B.length-1, but this would make the algorithm not stable
        for (int i = B.length - 1; i >= 0; i--) {
            B[C[A[i]/digit%10] - 1] = A[i];
            C[A[i]/digit%10]--;
        }
        return B;
    }
}
