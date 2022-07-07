package algorithms.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KahnsAlgorithm {
    public static void main(String[] args) {
        KahnsAlgorithm kahnsAlgorithm = new KahnsAlgorithm();
        int[][] pres = {{2, 9}, {10, 9}, {6, 2}, {6, 10}, {4, 1}, {4, 3}, {4, 7}, {12, 7}, {12, 11}, {2, 0}, {1, 3}, {3, 0}, {5, 4}, {6, 0}, {7, 6}, {8, 12}, {8, 4}, {11, 6}};

        boolean canFinish = kahnsAlgorithm.canFinish(14, pres);
        System.out.println(canFinish);
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // construct the adjacency list for efficient look up of all dependents of
        // a given node.
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        int[] inDegreeCount = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        // along with the adjacency list also compute the indegree values
        for (int[] edge : prerequisites) {
            graph[edge[1]].add(edge[0]);
            inDegreeCount[edge[0]]++;
        }
        // pick all zero in degree nodes and start processing

        Queue<Integer> completedNodes = new LinkedList<>();
        int traversedNodes = 0;

        for (int i = 0; i < numCourses; i++) {
            if (inDegreeCount[i] == 0) {
                completedNodes.add(i);
                traversedNodes++;
            }
        }


        while (!completedNodes.isEmpty()) {
            Integer poll = completedNodes.poll();
            for (int node : graph[poll]) {
                inDegreeCount[node]--;
                if (inDegreeCount[node] == 0) {
                    completedNodes.add(node);
                    traversedNodes++;
                }
            }
        }
        return traversedNodes == numCourses;
        //
    }
}
