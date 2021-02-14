package misc.uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

//UVA 10911 - Forming Quiz Teams
public class UVA10911 {
    private double[][] dist = new double[16][16];
    private int numTeams;

    public static void main(String args[]) throws NumberFormatException, IOException {
        UVA10911 uva10911 = new UVA10911();
        uva10911.readInput();
    }

    public void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCases = 1;
        while (true) {
            numTeams = Integer.parseInt(br.readLine());
            if (numTeams == 0) {
                break;
            }
            int[][] points = new int[2 * numTeams][2];

            for (int i = 0; i < 2 * numTeams; i++) {
                String[] line = br.readLine().split(" ");
                int x = Integer.parseInt(line[1]);
                int y = Integer.parseInt(line[2]);
                points[i][0] = x;
                points[i][1] = y;
            }
            for (int i = 0; i < 2 * numTeams; i++) {
                for (int j = i + 1; j < 2 * numTeams; j++) {
                    dist[i][j] = findDist(points, i, j);
                    dist[j][i] = dist[i][j];
                }
            }
            double min = findMinimum(0, new HashMap<>());
            BigDecimal ans = BigDecimal.valueOf(min).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Case " + (numCases++) + ": " + ans);


        }
    }

    public double findDist(int[][] points, int x, int y) {
        int x1 = points[x][0];
        int y1 = points[x][1];
        int x2 = points[y][0];
        int y2 = points[y][1];
        return Math.sqrt((Math.pow(x1 - x2, 2)) + (Math.pow(y1 - y2, 2)));
    }

    private double findMinimum(int bitmask, HashMap<Integer, Double> dp) {
        //Math.pow(2, 2*numTeams)) is MUCH slower than bit shifting
        if (bitmask == (1 << 2 * numTeams) - 1) {
            return 0;
        }
        if (dp.containsKey(bitmask)) {
            return dp.get(bitmask);
        }
        dp.put(bitmask, (double) Integer.MAX_VALUE);
        for (int i = 0; i < (numTeams * 2); i++) {
            if ((bitmask & (1 << i)) == 0) {
                for (int j = i + 1; j < (numTeams * 2); j++)
                    if ((bitmask & (1 << j)) == 0) {
                        dp.put(bitmask, Math.min(dp.get(bitmask), dist[i][j] + findMinimum(bitmask | (1 << i) | (1 << j), dp)));
                    }
            }
        }
        return dp.get(bitmask);
    }
}
