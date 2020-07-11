package datastructures;

import java.util.Arrays;

/**
 * A suffix array will contain integers that represent the starting
 * indexes of all the suffixes of a given string, after the aforementioned suffixes are sorted.
 * Example: for s = "abaab" suffix array = [2, 3, 0, 4, 1]
 * corresponding to the (sorted) suffixes aab, ab, abaab, b, baab
 */
public class SuffixArray {
    private final int alaphabetLen = 256;

    /**
     * Runtime: O(nlogn), where n is the length of string str
     * Space: O(n), where n is the length of string str
     * Runtime taking into account size of alphabet (i.e., not a constant alpahbet size): O((n+k)logn)
     * where k is the size of the alphabet
     * Space taking into account size of alphabet: O(n+k)
     *
     * @param str String
     * @return suffix array
     */
    public int[] sortCyclicShifts(String str) {
        int n = str.length();
        int[] p = new int[n]; //stores cyclic strings alphabetically
        int[] c = new int[n]; //stores equivalence class of cyclic strings
        int[] cnt = new int[Math.max(alaphabetLen, n)];
        //sort cyclic substrings of length 1 using counting sort
        for (int i = 0; i < n; i++) {
            cnt[str.charAt(i)]++;
        }
        for (int i = 1; i < alaphabetLen; i++) {
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
            swap(c, cn);
        }
        //remove 1st suffix, as this is the suffix that starts with "$" (or whatever char is used)
        return Arrays.copyOfRange(p, 1, p.length);
    }

    /**
     * Swaps the contents of two arrays
     *
     * @param one first array
     * @param two second array
     */
    public void swap(int[] one, int[] two) {
        for (int i = 0; i < one.length; i++) {
            int temp = one[i];
            one[i] = two[i];
            two[i] = temp;
        }
    }

    /**
     * Return the Longest Common Prefix Array given a suffix array
     * Algorithm Name: Kasai's algorithm
     * Runtime: O(n)
     * Space: O(n)
     *
     * @param str       the original String
     * @param suffixArr Suffix Array
     * @return longest common prefix array
     * @ore length of string str is greater than 0
     */
    public int[] longestCommonPrefix(String str, int[] suffixArr) {
        int n = str.length();
        int[] rank = new int[n];
        //sorts suffixes by length (descending)
        for (int i = 0; i < n; i++) {
            rank[suffixArr[i]] = i;
        }
        int k = 0;

        //lcp[i] = lcp of substring starting at suffixArr[i] and suffixArr[i+1]
        int[] lcp = new int[n - 1];
        for (int i = 0; i < n; i++) {
            if (rank[i] == n - 1) {
                k = 0;
                continue;
            }
            int j = suffixArr[rank[i] + 1];
            while (i + k < n && j + k < n && str.charAt(i + k) == str.charAt(j + k)) {
                k++;
            }
            lcp[rank[i]] = k;
            k = Math.max(0, k - 1);
        }
        return lcp;
    }

    /**
     * Returns the longest repeating substring of string S
     *
     * @param s String
     * @return longest repeating substring
     */
    public String longestRepeatingSubstring(String s) {
        String str = s + "$";
        int maxLen = 0;
        int maxInd = -1;
        SuffixArray sA = new SuffixArray();
        int[] suffixes = sA.sortCyclicShifts(str);
        int[] lcp = sA.longestCommonPrefix(s, suffixes);
        for (int i = 0; i < lcp.length; i++) {
            if (lcp[i] > maxLen) {
                maxLen = lcp[i];
                maxInd = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (maxInd != -1) {
            int start = suffixes[maxInd];
            while (maxLen > 0) {
                sb.append(s.charAt(start++));
                maxLen--;
            }
        }
        return sb.toString();
    }
}
