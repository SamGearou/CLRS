import java.util.Scanner;

public class Spreadsheets {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLines = Integer.parseInt(sc.nextLine());
        while (numLines > 0) {
            String line = sc.nextLine();
            if (line.matches("R\\d+C\\d+")) {
                convertToLetterSystem(line);
            } else {
                convertToRowAndCol(line);
            }
            numLines--;
        }
    }

    private static void convertToLetterSystem(String line) {
        String[] tokens = line.split("[R,C]"); //tokens[0] == ""
        int row = Integer.parseInt(tokens[1]);
        int col = Integer.parseInt(tokens[2]);
        StringBuilder sb = new StringBuilder();
        int pow = 1;
        while (pow * 26 <= col) {
            pow *= 26;
        }
        while (pow > 0) {
            char c = (char) ((col / pow) + 64); //65 == 'A'
            col %= pow;
            pow /= 26;
            sb.append(c + "");
        }
        sb.append(row);
        System.out.println(sb);
    }

    private static void convertToRowAndCol(String line) {
        StringBuilder col = new StringBuilder();
        StringBuilder row = new StringBuilder();
        int i = 0;
        while (i < line.length() && Character.isLetter(line.charAt(i))) {
            col.append(line.charAt(i++) + "");
        }
        while (i < line.length() && Character.isDigit(line.charAt(i))) {
            row.append(line.charAt(i++) + "");
        }
        int rowVal = Integer.parseInt(row.toString());
        i = col.length() - 1;
        int pow = 1;
        int colVal = 0;
        while (i >= 0) {
            char letter = col.charAt(i--);
            colVal += (pow * ((int) letter - 64));
            pow *= 26;
        }
        System.out.println(String.format("R%sC%s", rowVal, colVal));
    }
}