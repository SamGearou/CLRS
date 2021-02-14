package graphalgorithms.search;

public class FloodFill {
    private char[][] grid;

    public FloodFill(char[][] grid) {
        this.grid = grid;
    }

    /**
     * Assumes an implicit graph represented as a 2D grid, can traverse in all 8 directions (S,SE,SW,N,NE,NW,E,W)
     */
    public int floodFill(int r, int c, char one, char two) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[r].length) {
            return 0;
        }
        int[] dr = {1, 1, 1, -1, -1, -1, 0, 0}; //S, SE, SW, N, NE, NW, E, W
        int[] dc = {0, 1, -1, 0, 1, -1, 1, -1}; //S, SE, SW, N, NE, NW, E, W
        if (grid[r][c] != one) {
            return 0;
        }
        int ans = 1;
        grid[r][c] = two; //avoids cycles!
        for (int i = 0; i < 8; i++) {
            ans += floodFill(r + dr[i], c + dc[i], one, two);
        }
        return ans;
    }

    public static void main(String[] args) {
        char[][] grid = {{'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L'},
                {'L', 'L', 'W', 'W', 'L', 'L', 'W', 'L', 'L'},
                {'L', 'W', 'W', 'L', 'L', 'L', 'L', 'L', 'L'},
                {'L', 'W', 'W', 'W', 'L', 'W', 'W', 'L', 'L'},
                {'L', 'L', 'L', 'W', 'W', 'W', 'L', 'L', 'L'},
                {'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L'},
                {'L', 'L', 'L', 'W', 'W', 'L', 'L', 'W', 'L'},
                {'L', 'L', 'W', 'L', 'W', 'L', 'L', 'L', 'L'},
                {'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L', 'L'}};
        FloodFill floodFill = new FloodFill(grid);
        floodFill.floodFill(2, 1, 'W', '.');
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
            //output equal to:
//            L L L L L L L L L
//            L L . . L L W L L
//            L . . L L L L L L
//            L . . . L . . L L
//            L L L . . . L L L
//            L L L L L L L L L
//            L L L W W L L W L
//            L L W L W L L L L
//            L L L L L L L L L
        }
    }
}
