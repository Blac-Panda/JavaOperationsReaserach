/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package djikstra_floyd;

import static java.lang.String.format;
import java.util.Arrays;

/**
 *
 * @author BLAKJ
 */
public class FloydOuter {
    public static void floydOuter(Graph graph) {
        int[][] weights = graph.getWeights();
        int numVertices = graph.getNoOfEdges();
        
        double[][] dist = new double[numVertices][numVertices];
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);
 
        for (int[] w : weights)
            dist[w[0] - 1][w[1] - 1] = w[2];
 
        int[][] next = new int[numVertices][numVertices];
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++)
                if (i != j)
                    next[i][j] = j + 1;
        }
 
        for (int k = 0; k < numVertices; k++)
            for (int i = 0; i < numVertices; i++)
                for (int j = 0; j < numVertices; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
 
        printResult(dist, next);
    }
 
    static void printResult(double[][] dist, int[][] next) {
        System.out.println("NodePair\t\tDistance\t\tRoute");
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++) {
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;
                    if(dist[i][j] < 1000) {
                        String path = format("%d -> %d\t\t%2d\t\t\t%s", u, v,
                                (int) dist[i][j], u);
                        do {
                            u = next[u - 1][v - 1];
                            path += " -> " + u;
                        } while (u != v);
                        System.out.println(path);
                    }
                }
            }
        }
    }
}
