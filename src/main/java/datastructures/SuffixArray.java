package datastructures;

//A suffix array will contain integers that represent the starting
// indexes of the all the suffixes of a given string, after the aforementioned suffixes are sorted.
//Example: for s = "abaab" suffix array = [2, 3, 0, 4, 1]
//corresponding to the (sorted) suffixes aab, ab, abaab, b, baab
public class SuffixArray {
    private final int alaphabetLen = 256;

    //Runtime: O(nlogn), where n is the length of String str
    //Space Complexity: O(n), where n is the length of String str
    //Taking into account the size of the alphabet (i.e., not a constant alphabet size) yields a running time
    //of O((n + k)logn) time and O(n + k) space
    public int[] sortCyclicShifts(String str) {
        int n = str.length();
        int[] p = new int[n];
        int[] c = new int[n];
        int[] cnt = new int[Math.max(alaphabetLen, n)];
        //sort first character of the string using counting sort
        for (int i = 0; i < n; i++) {
            cnt[str.charAt(i)]++;
        }
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }
        for (int i = 0; i < n; i++) {
            p[cnt[str.charAt(i)] - 1] = i;
            cnt[str.charAt(i)]--;
        }
        c[p[0]] = 0;
        //number of distinct equivalence classes
        int classes = 1;
        for (int i = 1; i < n; i++) {
            //if different characters, increase equivalence classes
            if (str.charAt(p[i]) != str.charAt(p[i - 1])) {
                classes++;
            }
            c[p[i]] = classes - 1;
        }
        int[] pn = new int[n];
        int[] cn = new int[n];
        for (int h = 0; (1 << h) < n; h++) {
            for (int i = 0; i < n; i++) {
                //sort strings by the second half
                pn[i] = p[i] - (1 << h);
                if (pn[i] < 0) {
                    pn[i] += n;
                }
            }
            //reset cnt values for each equivalence class
            for (int i = 0; i < classes; i++) {
                cnt[i] = 0;
            }
            //sort strings by first half using counting sort
            for (int i = 0; i < n; i++) {
                cnt[c[pn[i]]]++;
            }
            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i - 1];
            }
            //starting at n-1 makes sort stable
            for (int i = n - 1; i >= 0; i--) {
                p[cnt[c[pn[i]]] - 1] = pn[i];
                cnt[c[pn[i]]]--;
            }
            cn[p[0]] = 0;
            classes = 1;
            for (int i = 1; i < n; i++) {
                //compare start of first half of string and start of end half of the string pairs
                int[] cur = new int[]{c[p[i]], c[(p[i] + (1 << h)) % n]};
                int[] prev = new int[]{c[p[i - 1]], c[(p[i - 1] + (1 << h)) % n]};
                if (cur[0] != prev[0] || cur[1] != prev[1]) {
                    classes++;
                }
                cn[p[i]] = classes - 1;
            }
            //swap contents of cn and c arrays
            swap(cn, c);
        }
        return p;
    }

    public void swap(int[] a, int b[]) {
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            a[i] = b[i];
            b[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] suffixArray = new SuffixArray().sortCyclicShifts("dabbb$");
        for (int x : suffixArray) {
            //ignore first index, as this cyclic shift starts with '$'
            System.out.println(x);
        }
    }
}
