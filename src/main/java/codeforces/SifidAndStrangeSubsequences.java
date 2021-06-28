import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class SifidAndStrangeSubsequences {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases = Integer.parseInt(sc.nextLine());
        while (numCases > 0) {
            sc.nextLine();
            String[] elements = sc.nextLine().trim().split(" ");
            int smallestPos = Integer.MAX_VALUE;
            int negCount = 0;
            TreeSet<Integer> uniqueNeg = new TreeSet<>();
            HashMap<Integer, Integer> freq = new HashMap<>();
            for (String element : elements) {
                int val = Integer.parseInt(element);
                if (val <= 0) {
                    uniqueNeg.add(val);
                    if (val < 0) {
                        negCount++;
                    }
                }
                if (val > 0) {
                    smallestPos = Math.min(smallestPos, val);
                }

                freq.put(val, freq.getOrDefault(val, 0) + 1);
            }
            int withPos;
            if (smallestPos != Integer.MAX_VALUE) {
                boolean canUsePos = true;
                Integer prev = null;
                for (int neg : uniqueNeg) {
                    if (prev != null) {
                        int diff = Math.abs(prev - neg);
                        if (diff < smallestPos) {
                            canUsePos = false;
                            break;
                        }
                    }
                    prev = neg;
                }
                if (canUsePos) {
                    withPos = uniqueNeg.size() + 1;
                } else {
                    withPos = uniqueNeg.size();
                }
            } else {
                withPos = uniqueNeg.size();
            }
            uniqueNeg.remove(0);
            int withoutPos = freq.getOrDefault(0, 0) + negCount;
            System.out.println(Math.max(withPos, withoutPos));
            numCases--;
        }
    }
}
