package misc.uva;

import java.util.Scanner;

//UVA 272 - TEX Quotes
public class UVA272 {

    public void readInput() {
        Scanner scan = new Scanner(System.in);
        String line;
        boolean leftQuote = true;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '\u001a') { // EOF Character
                    break;
                } else if (line.charAt(i) != '\"') {
                    System.out.print(line.charAt(i));
                } else if (leftQuote) {
                    leftQuote = false;
                    System.out.print("``");
                } else {
                    leftQuote = true;
                    System.out.print("''");
                }
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        UVA272 uva272 = new UVA272();
        uva272.readInput();
    }
}
