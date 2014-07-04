package assignment7;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Utility class containing methods for operating on graphs.
 * <p/>
 * Depth-first-search routine - to find a path between two vertices in a graph
 * Breadth-first-search routine - to find the shortest path between two vertices in a graph
 * Dijkstra's cheapest path routine - to find the cheapest path between two vertices in a graph
 * Topological sort - to produce a topologically sorted list of all vertices in a graph
 * Generating random graphs routine - to generate parameterized random graph for testing
 * Building graphs from file routine - to create and build a graph object from a valid dot file
 *
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class GraphUtil {

//    /**
//     * Performs a depth-first search of a graph to determine a path from a start vertex to an goal vertex.
//     * (See Lecture 18 for the algorithm.)
//     *
//     * @param graph - The graph object to be traversed
//     * @param startName - Name of the starting vertex in the path
//     * @param goalName  - Name of the ending vertex in the path
//     * @return a list of the vertices that make up a path path from the vertex with the name startName (inclusive)
//     * to the ending vertex with the name goalName (inclusive)
//     * @throws UnsupportedOperationException if there are no vertices in the graph with the names startName or goalName
//     */
//    public static List<String> depthFirstSearch(Graph graph, String startName, String goalName) {
//        // check that the correct vertices exist in the graph. If not, throw an exception
//        if (!graph.getVertices().containsKey(startName))
//            throw new UnsupportedOperationException("DFS: the start vertex doesn't exist");
//        if (!graph.getVertices().containsKey(goalName))
//            throw new UnsupportedOperationException("DFS: the goal vertex doesn't exist");
//
//        // use this method as a driver method
//        Vertex currentVertex = DFS(graph, startName, goalName);
//
//        // create path list and return it
//        LinkedList<String> path = new LinkedList<String>();
//        while (currentVertex.getCameFrom() != null) {
//            path.addFirst(currentVertex.getName());
//            currentVertex = currentVertex.getCameFrom();
//        }
//        return path;
//    }
//
//    /**
//     * The recursive method for depthFirstSearch
//     *
//     * @param graph - The graph object to be traversed
//     * @param currentName - Name of the current vertex in the path
//     * @param goalName  - Name of the ending vertex in the path
//     * @return a list of the vertices that make up a path path from the vertex with the name startName (inclusive)
//     * to the ending vertex with the name goalName (inclusive)
//     */
//    private static Vertex DFS(Graph graph, String currentName, String goalName) {
//        // check for completion
//        if (currentName.equals(goalName))
//            return graph.getVertices().get(currentName);
//
//        // get the edges connected to this node to iterate through
//        Vertex currentVertex = graph.getVertices().get(currentName);
//        LinkedList<Edge> currentNeighbors = currentVertex.getEdges();
//
//        // set this vertex as visited to prevent cycling
//        currentVertex.setVisited(true);
//
//        // for each neighbor rerun the recursion at that neighbor
//        for (Edge currentNeighbor : currentNeighbors) {
//            Vertex nextVertex = currentNeighbor.getOtherVertex();
//            if (nextVertex.getVisited()) // if a node has been visited skip it
//                continue;
//            nextVertex.setCameFrom(currentVertex);
//            DFS(graph, nextVertex.getName(), goalName);
//        }
//    }

    /**
     * Performs a depth-first search of a graph to determine a path from a start vertex to an goal vertex.
     * (See Lecture 18 for the algorithm.)
     *
     * @param graph - The graph object to be traversed
     * @param startName - Name of the starting vertex in the path
     * @param goalName  - Name of the ending vertex in the path
     * @return a list of the vertices that make up a path path from the vertex with the name startName (inclusive)
     *         to the ending vertex with the name goalName (inclusive)
     * @throws UnsupportedOperationException if there are no vertices in the graph with the names startName or goalName
     */
    public static List<String> depthFirstSearch(Graph graph, String startName, String goalName) {
        //ArrayList to hold vertices in path in the correct order from startVertex to goalVertex
        ArrayList<String> path = new ArrayList<String>();

        // declare and instantiate a HashMap of this graphs vertices.
        HashMap<String,Vertex> graphMap = graph.getVertices();

        // throw exception of startName and goalName are not associated with any vertices the HashMap
        if (! (graphMap.containsKey(startName)) || ! (graphMap.containsKey(goalName)))
            throw new UnsupportedOperationException("The startName or goalName do not exist!");

        // store vertex at startName & goalName to pass to depthFirstSearchRecursive
        Vertex startVertex = graphMap.get(startName);
        Vertex goalVertex = graphMap.get(goalName);

        // set start vertex to visited to avoid cycles
        startVertex.setVisited(true);

        // call recursive method
        depthFirstSearchRecursive(startVertex, goalVertex);

        //after recursive call, if goal vertex has not been visited, state no path found and return empty list
        if (goalVertex.getVisited() == false) {
            System.out.println("There was no path found from the vertex " + startName + " to the vertex " + goalName + "!");
            return path;
        }

        //ArrayList to hold the vertex names of the found path in reverse
        ArrayList<String> reversePath = new ArrayList<String>();

        // first add the goalVertex before looping
        reversePath.add(goalVertex.getName());
        // continuos loop until goalVertex equal startVertex
        while (! goalVertex.equals(startVertex)) {
            reversePath.add(goalVertex.getCameFrom().getName());
            goalVertex = goalVertex.getCameFrom();
        }

        // enhanced for loop to add path vertex names to return list in correct order.
        for (String name : reversePath)
            path.add(name);

        //return completed array list
        return path;
    }

    private static void depthFirstSearchRecursive(Vertex currentVertex, Vertex goalVertex) {
        // check if currVertex is goal
        if (currentVertex.equals(goalVertex))
            return;

        //get an iterator for the adjacent edges
        Iterator<Edge> currentEdges = currentVertex.edges();

        // edge to hold the current edge that the iterator returned
        Edge thisEdge;
        // next vertex to visit
        Vertex nextVertex;

        //while there are more edges to iterate through & the goal vertex hasn't been visited, call recursive method
        while (currentEdges.hasNext() && goalVertex.getVisited() == false) {
            //set this edge to next edge that the iterator returns
            thisEdge = currentEdges.next();

            if (thisEdge.getOtherVertex().getVisited() == false) {     //if the edge points to an unvisited vertex,
                nextVertex = thisEdge.getOtherVertex();             //visit vertex with recursive method
                nextVertex.setVisited(true);
                nextVertex.setCameFrom(currentVertex);
                // call recursive method
                depthFirstSearchRecursive(nextVertex, goalVertex);
            }
        }
        return;
    }

    /**
     * Performs a breadth-first search on a graph to determine the shortest path from a start vertex to an goal vertex.
     * (See Lecture 18 for the algorithm.)
     *
     * @param graph - The graph object to be traversed
     * @param start - Name of the starting vertex in the path
     * @param goal  - Name of the ending vertex in the path
     * @return a list of the vertices that make up the shortest path from the vertex with the name startName (inclusive)
     * to the ending vertex with the name goalName (inclusive)
     * @throws UnsupportedOperationException if there are no vertices in the graph with the names startName or goalName
     */
    public static List<String> breadthFirstSearch(Graph graph, String startName, String goalName) {
        // TODO

        return null;
    }

    /**
     * Performs Dijkstra's routine on a weighted graph to determine the cheapest path from start vertex to a goal vertex.
     * (See Lecture 19 for the algorithm.)
     * <p/>
     * Uses Java's PriorityQueue class to find the "unvisited vertex with smallest distance from start".
     * See the API for PriorityQueue, and ask the course staff if you need help.
     *
     * @param graph - The graph object to be traversed
     * @param start - Name of the starting vertex in the path
     * @param goal  - Name of the ending vertex in the path
     * @return a list of the vertices that make up the cheapest path from the starting vertex (inclusive) to the
     * ending vertex (inclusive) based on weight associated with the edges between the graphs vertices
     * @throws UnsupportedOperationException if the graph is not weighted, or there are no vertices in the graph
     *                                       with the names startName or goalName
     */
    public static List<String> dijkstrasShortestPath(Graph graph, String startName, String goalName) {
        // TODO

        return null;
    }

    /**
     * Performs a topological sort of the vertices in a directed acyclic graph.
     * (See Lecture 19 for the algorithm.)
     *
     * @param graph - The graph object to be traversed
     * @return a list of the vertex names in topologically sorted order
     * @throws UnsupportedOperationException if the graph is undirected, or it is cyclic.
     */
    public static List<String> topologicalSort(Graph graph) {
        // TODO

        return null;
    }

    /**
     * Builds a graph according to the edges specified in the given DOT file (e.g., "a -- b" or "a -> b").
     * Accepts directed ("digraph") or undirected ("graph") graphs.
     * <p/>
     * Accepts many valid DOT files (see examples posted with assignment).
     * --accepts \\-style comments
     * --accepts one edge per line or edges terminated with ;\
     * --accepts label attributes (e.g., [label = "a label"]) for weights
     *
     * @param filename - name of the DOT file
     */
    public static void generateGraphInDotFile(String filename, int vertexCount, int edgeDensity, boolean directed, boolean acyclic, boolean weighted) {
        PrintWriter out = null;

        try {
            out = new PrintWriter(filename);
        } catch (Exception e) {
            System.out.print("Unable to utilize the graph .dot file name: ");
            System.err.println(e.getMessage());
        }

        Vertex[] vertex = new Vertex[vertexCount];
        Random rng = new Random();

        String edgeOp = "--";

        if (directed) {
            out.print("di");
            edgeOp = "->";
        }

        out.println("graph G {");

        for (int i = 0; i < vertexCount; i++)
            vertex[i] = new Vertex("v" + i);

        int rand1 = rng.nextInt(vertexCount);
        int rand2 = rng.nextInt(vertexCount);

        if (acyclic)
            for (int i = 0; i < edgeDensity * vertexCount; i++) {
                rand1 = rng.nextInt(vertexCount);
                rand2 = rng.nextInt(vertexCount);

                for (Edge e : vertex[rand1].getEdges())
                    while (e.getOtherVertex().equals(vertex[rand2]))
                        rand2 = rng.nextInt(vertexCount);

                while (rand2 <= rand1) {
                    rand1 = rng.nextInt(vertexCount);
                    rand2 = rng.nextInt(vertexCount);

                    for (Edge e : vertex[rand1].getEdges())
                        while (e.getOtherVertex().equals(vertex[rand2]))
                            rand2 = rng.nextInt(vertexCount);
                    if (rand2 <= rand1)
                        rand2 = rng.nextInt(vertexCount);
                }

                vertex[rand1].addEdge(vertex[rand2]);

                out.print("\t" + vertex[rand1].getName() + edgeOp + vertex[rand2].getName());

                if (weighted)
                    out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");

                out.print("\n");
            }
        else
            for (int i = 0; i < edgeDensity * vertexCount; i++) {
                rand1 = rng.nextInt(vertexCount);
                rand2 = rng.nextInt(vertexCount);

                while (rand2 == rand1) {
                    rand2 = rng.nextInt(vertexCount);
                }
                out.print("\t" + vertex[rand1].getName() + edgeOp + vertex[rand2].getName());

                if (weighted)
                    out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");

                out.print("\n");
            }

        out.println("}");
        out.close();
    }

    /**
     * Builds a graph according to the edges specified in the given DOT file (e.g., "a -- b" or "a -> b").
     * Accepts directed ("digraph") or undirected ("graph") graphs.
     * <p/>
     * Accepts many valid DOT files (see examples posted with assignment).
     * --accepts \\-style comments
     * --accepts one edge per line or edges terminated with ;
     * --does not accept attributes in [] (e.g., [label = "a label"])
     *
     * @param filename - name of the DOT file
     */
    public static Graph buildGraphFromDotFile(String filename) {
        Graph g = new Graph();

        Scanner s = null;
        try {
            s = new Scanner(new File(filename)).useDelimiter(";|\n");
        } catch (Exception e) {
            System.out.print("Unable to utilize the graph .dot file: ");
            System.err.println(e.getMessage());
        }

        // Determine if graph is directed or not (i.e., look for "digraph id {" or "graph id {")
        String line = "", edgeOp = "";

        while (s.hasNext()) {
            line = s.next();

            // Skip //-style comments.
            line = line.replaceFirst("//.*", "");

            if (line.indexOf("digraph") >= 0) {
                g.setDirected(true);
                edgeOp = "->";
                line = line.replaceFirst(".*\\{", "");
                break;
            }
            if (line.indexOf("graph") >= 0) {
                g.setDirected(false);
                edgeOp = "--";
                line = line.replaceFirst(".*\\{", "");
                break;
            }
        }

        line = s.next();
        boolean weighted = line.contains("label");

        if (weighted)
            g.setWeighted(true);

        // Look for edge operators -- (or ->) and determine the left and right vertices for each edge.
        while (s.hasNext()) {
            String[] substring2 = null;
            String[] substring = line.split(edgeOp);

            if (weighted) {
                substring2 = line.split(" ");
                substring = substring2[0].split(edgeOp);
            }

            for (int i = 0; i < substring.length - 1; i += 2) {
                // remove " and trim whitespace from node string on the left
                String vertex1 = substring[0].replace("\"", "").trim();
                if (vertex1.equals(""))
                    continue;

                String vertex2 = substring[1].replace("\"", "").trim();
                if (vertex2.equals(""))
                    continue;

                if (weighted) {
                    String[] substring3 = substring2[1].split("=");
                    int weight = Integer.parseInt(substring3[1].replace("]", "").trim());
                    g.addEdgeWeighted(vertex1, vertex2, weight);
                } else
                    g.addEdge(vertex1, vertex2);
            }

            if (substring[substring.length - 1].indexOf("}") >= 0)
                break;

            line = s.next();

            // Skip //-style comments.
            line = line.replaceFirst("//.*", "");
        }

        return g;
    }
}