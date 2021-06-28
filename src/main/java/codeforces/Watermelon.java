package codeforces;

import java.util.Scanner;

public class Watermelon {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int kilos = Integer.parseInt(sc.nextLine());
            if (kilos <= 2 || kilos % 2 == 1) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
