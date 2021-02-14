package misc.uva;

import java.util.Scanner;

//UVA 573 - The Snail
public class UVA573 {

    public void readInput() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String[] tokens = scan.nextLine().split(" ");
            if (tokens[0].equals("0")) {
                break;
            }
            double wellHeight = Double.parseDouble(tokens[0]);
            double climbDist = Double.parseDouble(tokens[1]);
            double slideDist = Double.parseDouble(tokens[2]);
            double fatigueFactor = Double.parseDouble(tokens[3]) / 100 * climbDist;
            double currHeight = 0;
            int day = 1;
            while (currHeight >= 0 && currHeight < wellHeight) {
                currHeight += Math.max(0, climbDist);
                if (currHeight > wellHeight) {
                    System.out.println("success on day " + day);
                    break;
                }
                currHeight -= slideDist;
                if (currHeight < 0) {
                    System.out.println("failure on day " + day);
                    break;
                }
                climbDist -= fatigueFactor;
                day++;
            }
        }
    }

    public static void main(String[] args) {
        UVA573 uva573 = new UVA573();
        uva573.readInput();
    }
}
