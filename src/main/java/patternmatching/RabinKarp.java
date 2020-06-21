package patternmatching;

public class RabinKarp {
    private static int hash(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash *= 31;
            hash += s.charAt(i);
        }
        return hash;
    }

    public static void main(String[] args) {
        String s1 = "abcdefghij";
        String s2 = s1.substring(1) + "k";
        int pow = 1;
        for (int i = 0; i < s1.length(); i++) {
            pow *= 31;
        }
        System.out.printf("hash(%s) = %d%n", s1, hash(s1));
        System.out.printf("hash(%s) = %d%n31 * hash(%s) - (31^%d * %s) + %s = %s%n",
                s2,
                hash(s2),
                s1,
                s1.length(),
                s1.charAt(0),
                s2.charAt(s2.length() - 1),
                31 * hash(s1) - (pow * s1.charAt(0)) + s2.charAt(s2.length() - 1));
    }
}
