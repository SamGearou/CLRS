package misc;

public class KMPSubstringSearch {

    //Algorithm Time Complexity: O(m + n)
    //Algorithm Space Complexity: O(n)
    public static boolean isSubString(String str, String pattern) {
        int[] prefix = new int[pattern.length()];
        //populate prefix array
        for (int i = 1, j = 0; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefix[i] = j;
        }

        //KMP algorithm using populated prefix array
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
