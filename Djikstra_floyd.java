/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package djikstra_floyd;
import java.util.Scanner;
import java.util.LinkedList;

/**
 *
 * @author BLAK_PANDA
 */


public class Djikstra_floyd {
    public static void main(String[] args) 
    {
        startUp();
    } 
        public static void FloydRun()
    {
        Graph graph = new Graph();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Insert the number of nodes we are dealing with:");
        int numOfNodes = Integer.parseInt(scan.nextLine());
        System.out.println("Please Insert the number of edges we are dealing with:");
        int numOfEdges = Integer.parseInt(scan.nextLine());
            for (int i = 1; i <= numOfNodes; i++) 
                {
                    graph.addVertex(i);
                }
            for (int i = 1; i <= numOfEdges; i++) 
                {
                    int firstNode;
                    int secondNode;
                    int distance;
                    System.out.print("Enter initial node:");
                    firstNode = scan.nextInt();
                    System.out.print("Enter end node:");
                    secondNode = scan.nextInt();
                    System.out.print("Enter distance between node "+ (firstNode) + " and node "+ (secondNode) + " : ");
                    distance = scan.nextInt();
                    graph.addEdge(firstNode,secondNode,distance);
                }
        //Floyd part 
        FloydOuter floyd = new FloydOuter();
        floyd.floydOuter(graph);
        option();
    }
    public static void DijkstraRun()
    {
        Graph graph = new Graph();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Insert the number of nodes we are dealing with:");
        int numOfNodes = Integer.parseInt(scan.nextLine());
        System.out.println("Please Insert the number of edges we are dealing with:");
        int numOfEdges = Integer.parseInt(scan.nextLine());
            for (int i = 1; i <= numOfNodes; i++) 
                {
                    graph.addVertex(i);
                }
            for (int i = 1; i <= numOfEdges; i++) 
                {
                    int firstNode;
                    int secondNode;
                    int distance;
                    System.out.print("Enter initial node:");
                    firstNode = scan.nextInt();
                    System.out.print("Enter end node:");
                    secondNode = scan.nextInt();
                    System.out.print("Enter distance between node "+ (firstNode) + " and node "+ (secondNode) + " : ");
                    distance = scan.nextInt();
                    graph.addEdge(firstNode,secondNode,distance);
                }
        //Dijkstra Part
        Dijkstra dijsktra = new Dijkstra(graph);
        Vertex [] vertices = new Vertex [graph.getNoOfVertex()];
        graph.getVertices().toArray(vertices);
        dijsktra.execute(vertices[1]);
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        dijsktra.print(vertices[vertices.length - 1]);
        option();
    }
    public static void option()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nWould you like to try a different method:\n1. YES\n2. NO");
        int option = Integer.parseInt(scan.nextLine());
        switch(option)
        {
            case 1: startUp();
            break;
            case 2: System.out.println("End");
            break;
        }
    }
    public static void startUp()
    {
    Scanner scan = new Scanner(System.in);
        System.out.println("Please Insert preferred method:\n1. FLOYD\n2. DIJKSTRA");
        int methodChoice = Integer.parseInt(scan.nextLine());
        switch(methodChoice)
        {
            case 1: FloydRun();
            break;
            case 2: DijkstraRun();
            break;
        }
    }
}

