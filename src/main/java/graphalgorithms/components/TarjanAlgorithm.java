package graphalgorithms.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class TarjanAlgorithm {
    private Map<String, List<String>> graph;
    private HashSet<String> visited;
    private Map<String, Integer> num;
    private Map<String, Integer> lowNum;
    private Stack<String> stack;
    private int dfsNumberCount;

    public TarjanAlgorithm() {
        graph = new HashMap<>();
        visited = new HashSet<>();
        num = new HashMap<>();
        lowNum = new HashMap<>();
        stack = new Stack<>();
        dfsNumberCount = 0;
    }

    public void addVertex(String vertex) {
        num.put(vertex, -1);
        lowNum.put(vertex, -1);
        graph.put(vertex, new ArrayList<>());
    }

    public void addDirectedEdge(String src, String dest) {
        graph.get(src).add(dest);
    }

    public Map<String, List<String>> getGraph() {
        return graph;
    }

    public Map<String, Integer> getNum() {
        return num;
    }

    public void stronglyConnectedComponents(String src) {
        num.put(src, dfsNumberCount);
        lowNum.put(src, dfsNumberCount);
        dfsNumberCount++;
        stack.push(src);
        visited.add(src);
        for (int i = 0; i < graph.get(src).size(); i++) {
            String neighbor = graph.get(src).get(i);
            if (num.get(neighbor).equals(-1)) {
                stronglyConnectedComponents(neighbor);
            }
            if (visited.contains(neighbor)) {
                lowNum.put(src, Math.min(lowNum.get(src), lowNum.get(neighbor)));
            }
        }

        if (lowNum.get(src).equals(num.get(src))) { //if this is the "root" of the Strongly Connected Component
            while (true) {
                String currVertex = stack.pop();
                visited.remove(currVertex);
                System.out.print(currVertex + " ");
                if (currVertex.equals(src)) {
                    System.out.println();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        TarjanAlgorithm tarjanAlgorithm = new TarjanAlgorithm();
        tarjanAlgorithm.addVertex("0");
        tarjanAlgorithm.addVertex("1");
        tarjanAlgorithm.addVertex("2");
        tarjanAlgorithm.addVertex("3");
        tarjanAlgorithm.addVertex("4");
        tarjanAlgorithm.addVertex("5");
        tarjanAlgorithm.addVertex("6");
        tarjanAlgorithm.addVertex("7");
        tarjanAlgorithm.addDirectedEdge("0", "1");
        tarjanAlgorithm.addDirectedEdge("1", "3");
        tarjanAlgorithm.addDirectedEdge("2", "1");
        tarjanAlgorithm.addDirectedEdge("3", "2");
        tarjanAlgorithm.addDirectedEdge("3", "4");
        tarjanAlgorithm.addDirectedEdge("4", "5");
        tarjanAlgorithm.addDirectedEdge("5", "7");
        tarjanAlgorithm.addDirectedEdge("7", "6");
        tarjanAlgorithm.addDirectedEdge("6", "4");
        for (String vertex : tarjanAlgorithm.getGraph().keySet()) {
            if (tarjanAlgorithm.getNum().get(vertex).equals(-1)) {
                tarjanAlgorithm.stronglyConnectedComponents(vertex);
            }
        }
        //output:
//        6 7 5 4
//        2 3 1
//        0
    }
}