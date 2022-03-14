package stringalgorithms;

import java.util.Arrays;

/**
 * Given a String s, finds the longest palindromic substring in O(n) time
 */
public class ManachersAlgorithm {

    public int longestPalSubstring(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i) + "");
            sb.append("|");
        }
        String strPrime = sb.toString();
        int n = strPrime.length();
        int[] palindromeRadii = new int[n];
        int center = 0;
        int radius = 0;
        while (center < n) {
            while (center - (radius + 1) >= 0 && center + (radius + 1) < n && strPrime.charAt(center - (radius + 1)) == strPrime.charAt(center + (radius + 1))) {
                radius++;
            }
            palindromeRadii[center] = radius;
            int oldCenter = center;
            int oldRadius = radius;
            center++;
            radius = 0;
            while (center <= oldCenter + oldRadius) {
                int mirroredCenter = oldCenter - (center - oldCenter);
                int maxMirroredRadius = oldCenter + oldRadius - center;
                if (palindromeRadii[mirroredCenter] < maxMirroredRadius) {
                    palindromeRadii[center] = palindromeRadii[mirroredCenter];
                    center++;
                } else if (palindromeRadii[mirroredCenter] > maxMirroredRadius) {
                    palindromeRadii[center] = maxMirroredRadius;
                    center++;
                } else {
                    radius = maxMirroredRadius;
                    break;
                }
            }
        }
        int maxVal = 2 * Arrays.stream(palindromeRadii).max().getAsInt() + 1;
        return (maxVal - 1) / 2;
    }

    public static void main(String[] args) {
        ManachersAlgorithm manachersAlgorithm = new ManachersAlgorithm();
        System.out.println(manachersAlgorithm.longestPalSubstring("zwxwvuabacabagferg"));
    }
}
