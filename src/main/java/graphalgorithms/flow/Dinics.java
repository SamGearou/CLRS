package graphalgorithms.flow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

//runs in O(V^2E)
public class Dinics {
    private int largeFlow = 1_000_000_000;
    private ArrayList<Edge> edges;
    private ArrayList<ArrayList<Integer>> adj;
    private int V;
    private int E;
    private int s;
    private int t;
    private ArrayList<Integer> level;
    private Queue<Integer> q;

    public Dinics(int V, int s, int t) {
        this.V = V;
        this.s = s;
        this.t = t;
        this.E = 0;
        edges = new ArrayList<>();
        adj = new ArrayList<>();
        level = new ArrayList<>();
        q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
            level.add(-1);
        }
    }

    public void addEdge(int src, int dest, int capacity) {
        edges.add(new Edge(src, dest, capacity));
        edges.add(new Edge(dest, src, capacity));
        adj.get(src).add(E);
        adj.get(dest).add(E + 1);
        E += 2;
    }

    public boolean bfs() {
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int id : adj.get(v)) {
                if (edges.get(id).capacity - edges.get(id).flow < 1) {
                    continue;
                }
                if (level.get(edges.get(id).dest) != -1) {
                    continue;
                }
                level.set(edges.get(id).dest, level.get(v) + 1);
                q.add(edges.get(id).dest);
            }
        }
        return level.get(t) != -1;
    }

    public int dfs(int v, int pushed) {
        if (pushed == 0) {
            return 0;
        }
        if (v == t) {
            return pushed;
        }
        for (int i = 0; i < adj.get(v).size(); i++) {
            int id = adj.get(v).get(i);
            int dest = edges.get(id).dest;
            if (level.get(v) + 1 != level.get(dest) || edges.get(id).capacity - edges.get(id).flow < 1) {
                continue;
            }
            int flow = dfs(dest, Math.min(pushed, edges.get(id).capacity - edges.get(id).flow));
            if (flow == 0) {
                continue;
            }
            edges.get(id).flow += flow;
            edges.get(id ^ 1).flow -= flow; //id ^ 1 == id + 1, but faster
            return flow;
        }
        return 0;
    }

    public int flow() {
        int flow = 0;
        while (true) {
            Collections.fill(level, -1);
            level.set(s, 0);
            q.add(s);
            if (!bfs()) {
                break;
            }
            int pushed;
            while ((pushed = dfs(s, largeFlow)) != 0) {
                flow += pushed;
            }
        }
        return flow;
    }


    public class Edge {
        private int src;
        private int dest;
        private int capacity;
        private int flow;

        public Edge(int str, int dest, int capacity) {
            this.src = src;
            this.flow = 0;
            this.dest = dest;
            this.capacity = capacity;
            this.flow = flow;
        }
    }

    public static void main(String[] args) {
        Dinics dinics = new Dinics(5, 0, 4);
        dinics.addEdge(0, 2, 100);
        dinics.addEdge(0, 3, 50);
        dinics.addEdge(2, 1, 5);
        dinics.addEdge(2, 4, 5);
        dinics.addEdge(3, 4, 100);
        dinics.addEdge(4, 1, 125);
        System.out.println(dinics.flow()); //60
    }
}
