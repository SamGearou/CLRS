package graphalgorithms.articulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Articulation Points and Bridges for Undirected Graphs
 */
public class ArticulationPointsAndBridges {
    private Map<String, List<String>> graph;
    private Map<String, String> parent;
    private Map<String, Integer> num;
    private Map<String, Integer> lowNum;
    private Set<String> articulationPoints;
    private Set<Pair> bridges;
    private int dfsNumberCount;
    private String dfsRoot;
    private int rootChildren;

    public ArticulationPointsAndBridges() {
        graph = new HashMap<>();
        parent = new HashMap<>();
        num = new HashMap<>();
        lowNum = new HashMap<>();
        articulationPoints = new HashSet<>();
        bridges = new HashSet<>();
        dfsNumberCount = 0;
        dfsRoot = "";
        rootChildren = 0;

    }

    public void addVertex(String vertex) {
        num.put(vertex, -1);
        lowNum.put(vertex, -1);
        graph.put(vertex, new ArrayList<>());
    }

    public void addUndirectedEdge(String src, String dest) {
        graph.get(src).add(dest);
        graph.get(dest).add(src);
    }

    public Map<String, List<String>> getGraph() {
        return graph;
    }

    public Map<String, Integer> getNum() {
        return num;
    }

    public void articulationPointAndBridge(String src) {
        num.put(src, dfsNumberCount);
        lowNum.put(src, dfsNumberCount);
        dfsNumberCount++;
        for (int i = 0; i < graph.get(src).size(); i++) {
            String neighbor = graph.get(src).get(i);
            if (num.get(neighbor).equals(-1)) {
                if (src.equals(dfsRoot)) {
                    rootChildren++;
                }
                parent.put(neighbor, src);
                articulationPointAndBridge(neighbor);

                if (lowNum.get(neighbor) >= num.get(src)) {
                    articulationPoints.add(src);
                }

                if (lowNum.get(neighbor) > num.get(src)) {
                    bridges.add(new Pair(src, neighbor));
                }

                lowNum.put(src, Math.min(lowNum.get(src), lowNum.get(neighbor)));

            } else if (!neighbor.equals(parent.get(src))) { //cycle of length 3 or more
                lowNum.put(src, Math.min(lowNum.get(src), num.get(neighbor)));
            }
        }
    }

    public class Pair {
        private String srcVertex;
        private String destVertex;

        public Pair(String srcVertex, String destVertex) {
            this.srcVertex = srcVertex;
            this.destVertex = destVertex;
        }

        @Override
        public String toString() {
            return "src: " + srcVertex + " dest: " + destVertex;
        }
    }

    public static void main(String[] args) {
        ArticulationPointsAndBridges pointsAndBridges = new ArticulationPointsAndBridges();
        pointsAndBridges.addVertex("0");
        pointsAndBridges.addVertex("1");
        pointsAndBridges.addVertex("2");
        pointsAndBridges.addVertex("3");
        pointsAndBridges.addVertex("4");
        pointsAndBridges.addVertex("5");
        pointsAndBridges.addUndirectedEdge("0", "1");
        pointsAndBridges.addUndirectedEdge("1", "2");
        pointsAndBridges.addUndirectedEdge("1", "3");
        pointsAndBridges.addUndirectedEdge("1", "4");
        pointsAndBridges.addUndirectedEdge("1", "5");
        pointsAndBridges.addUndirectedEdge("4", "5");
        pointsAndBridges.addUndirectedEdge("4", "4");

        for (String vertex : pointsAndBridges.getGraph().keySet()) {
            if (pointsAndBridges.getNum().get(vertex).equals(-1)) { //if have not seen yet
                pointsAndBridges.dfsRoot = vertex;
                pointsAndBridges.rootChildren = 0;
                pointsAndBridges.articulationPointAndBridge(vertex);
                if (pointsAndBridges.rootChildren <= 1) { //if root vertex has more than 1 child, it is an articulation point (special case)
                    pointsAndBridges.articulationPoints.remove(vertex);
                }
            }
        }
        System.out.println(pointsAndBridges.articulationPoints); //["0", "1"]
        System.out.println(pointsAndBridges.bridges); //[("1", "2"), ("0", "1"), ("1", "3")]
    }
}
