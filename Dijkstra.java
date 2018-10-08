/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package djikstra_floyd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author BLAKJ
 */
public class Dijkstra {
    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;
   
    public Dijkstra(Graph graph) {
        this.nodes = new ArrayList<Vertex>(graph.getVertices());
        this.edges = new ArrayList<Edge>(graph.getEdges());
        
    }
    
    public void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while(unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }
    
    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for(Vertex target : adjacentNodes) {
            if(getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) +
                        getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            } 
        }
    }
    
    private int getDistance(Vertex node, Vertex target) {
        for(Edge edge : edges) {
            if (edge.getV1().equals(node) && edge.getV2().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }
    
    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for(Edge edge : edges) {
            if(edge.getV1().equals(node) && !isSettled(edge.getV2())) {
                neighbors.add(edge.getV2());
            }
        }
        
        return neighbors;
    }
    
    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for(Vertex vertex : vertexes) {
            if(minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }
    
    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }
    
    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if(d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }
    
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        //check if a path exists
        if(predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while(predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        
        //put it into the correct order
        Collections.reverse(path);
        return path;
    }
    
    public void print(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        path = this.getPath(target);
        for(Vertex v : path) {
            System.out.print("--> " + v.getLabel() + " ");
        }
    }   
}


