package misc.uva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//UVA 908 - Re-connecting Computer Sites
public class Main {
    private List<Edge> edgeList;

    public Main() {
        edgeList = new ArrayList<>();
    }

    public void readInput() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            int n = Integer.parseInt(scan.nextLine());
            DisjointSet dsu = new DisjointSet(n);
            int originalMST = 0;
            while (n > 1) {
                String[] tokens = scan.nextLine().split(" ");
                String src = tokens[0];
                String dest = tokens[1];
                int weight = Integer.parseInt(tokens[2]);
                edgeList.add(new Edge(src, dest, weight));
                originalMST += weight;
                n--;
            }
            int newMST = 0;
            int k = Integer.parseInt(scan.nextLine());
            while (k > 0) {
                String[] tokens = scan.nextLine().split(" ");
                String src = tokens[0];
                String dest = tokens[1];
                int weight = Integer.parseInt(tokens[2]);
                edgeList.add(new Edge(src, dest, weight));
                k--;
            }
            Collections.sort(edgeList);
            for (Edge e : edgeList) {
                String src = e.src;
                String dest = e.dest;
                int weight = e.weight;
                if (!dsu.findSet(src).equals(dsu.findSet(dest))) {
                    dsu.union(src, dest);
                    newMST += weight;
                }
            }
            System.out.println(originalMST);
            System.out.println(newMST);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.equals("")) {
                    break;
                }
            }
        }
    }

    public class DisjointSet {
        Map<String, String> parents;
        Map<String, Integer> rank;

        public DisjointSet(int n) {
            parents = new HashMap<>();
            rank = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                parents.put(String.valueOf(i), String.valueOf(i));
                rank.put(String.valueOf(i), 0);
            }
        }

        public String findSet(String src) {
            if (!parents.get(src).equals(src)) {
                parents.put(src, findSet(parents.get(src)));
            }
            return parents.get(src);
        }

        public String union(String one, String two) {
            return link(findSet(one), findSet(two));
        }

        public String link(String one, String two) {
            if (rank.get(one) < rank.get(two)) {
                parents.put(one, two);
                return two;
            } else {
                if (rank.get(one).equals(rank.get(two))) {
                    rank.put(one, rank.get(one) + 1);
                }
                parents.put(two, one);
                return one;
            }
        }
    }

    public class Edge implements Comparable<Edge> {
        String src;
        String dest;
        int weight;

        public Edge(String src, String dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight == o.weight) {
                return src.compareTo(o.src) - dest.compareTo(o.dest);
            }
            return weight - o.weight;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.readInput();
    }
}
