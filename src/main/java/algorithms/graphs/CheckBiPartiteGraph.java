package algorithms.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CheckBiPartiteGraph {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(1);
                add(2);
            }});
            add(new ArrayList<>() {{
                add(3);
                add(4);
            }});
            add(new ArrayList<>() {{
                add(1);
                add(3);
            }});
            add(new ArrayList<>() {{
                add(2);
                add(4);
            }});
        }};

        CheckBiPartiteGraph checkBiPartiteGraph = new CheckBiPartiteGraph();
        System.out.println(checkBiPartiteGraph.checkPartiteCondition(edges, 4));
    }

    public boolean bfsCheck(ArrayList<ArrayList<Integer>> graph, int node, int[] color) {

        Queue<Integer> queue = new LinkedList<>();
        color[node] = 1;
        queue.add(node);
        while (!queue.isEmpty()) {
            int front = queue.poll();
            for (int adj : graph.get(front)) {
                if (color[adj] == -1) {
                    color[adj] = 1 - color[front];
                    queue.add(adj);
                } else {
                    if (color[adj] == color[front]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkPartiteCondition(ArrayList<ArrayList<Integer>> edges, int numNodes) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        edges.forEach(
                edge -> {
                    if (graph.get(edge.get(0) - 1) == null) {
                        graph.add(edge.get(0) - 1, new ArrayList<>());
                    }
                    if (graph.get(edge.get(1) - 1) == null) {
                        graph.add(edge.get(1) - 1, new ArrayList<>());
                    }
                    graph.get(edge.get(0) - 1).add(edge.get(1) - 1);
                    graph.get(edge.get(1) - 1).add(edge.get(0) - 1);
                }
        );

        Queue<Integer> queue = new LinkedList<>();
        int[] gender = new int[numNodes];
        Arrays.fill(gender, -1);

        for (int i = 0; i < numNodes; i++) {
            if (gender[i] == -1) {
                if (!bfsCheck(graph, i, gender)) {
                    return false;
                }
            }
        }
        return true;
    }
}
