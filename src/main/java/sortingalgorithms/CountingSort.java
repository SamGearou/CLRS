package sortingalgorithms;

//Counting sort assumes that each of the n input elements is an integer in the range
//0 to k, for some integer k. When k = O(n), the sort runs in Theta(n) time
public class CountingSort {

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
        for (int i = B.length - 1; i >= 0; i--) {
            B[C[A[i]] - 1] = A[i];
            C[A[i]]--;
        }
        return B;
    }
}
