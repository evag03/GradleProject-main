package gradleproject;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;


/**
 * Initializes the ReadFile class, which contains methods for computating the dataset.
 */
public class ReadFile {
  /**
  * File reader for dataset; takes in a file with a corresponding file name and reads it, placing it into a scanner.
  * @param filename (String) The name of the file being read
  * @param file (File) File being read
  */
  public static ArrayList<String[]> ReadInFile(MutableValueGraph<String,Integer> graph, HashSet<String> listOfVertices) {
    String filename = "C:\\Users\\Eva\\Downloads\\GradleProject-main\\GradleProject-main\\app\\src\\main\\java\\gradleproject\\TexasRoadnetworks.txt";
    File file = null;

    Scanner scanner = null;
      
    try {
      file = new File(filename);
    }
    // find exactly what exception this is supposed to be
    catch (Exception e) {
      System.out.println("Cannot locate file.");
      System.exit(-1);
    }

    try {
      scanner = new Scanner(file);
    }
    catch (Exception e){
      System.out.println("scanner not working");
      e.printStackTrace();
      System.exit(-1);
    }
    

    //Need to initialize nodes in text files
    // Node fromNodeID = new ReadFile(File[0]);
    // Node ToNodeID = File[1];

    ArrayList<String[]> edgeSets = new ArrayList<>();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] vertices = line.split("\t");
      // ArrayList<String> verticesList = new ArrayList<String>(Arrays.asList(vertices));
      // System.out.println(verticesList.toString());

      //System.out.println("1st node: " + vertices[0] + " 2nd node: " + vertices[1]);

      graph.addNode(vertices[0]);
      graph.putEdgeValue(vertices[0], vertices[1], 1);

      String[] connections = {vertices[0], vertices[1], "1"};
      edgeSets.add(connections);
      
      
      if (!listOfVertices.contains(vertices[0])) {
        listOfVertices.add(vertices[0]);
      }
      // vertices[0].addNode();
    }

    new GraphDisplay(graph);

    scanner.close();
    return edgeSets;
  }

  // turned source into an integer because it made more sense to me to have the starting node be an integer; it also fixed some errors later
  // are we missing any parameters? I saw edgeSet in the 2nd version but assuming that got replaced by listOfVerticies?
  // not sure why a 'Return type for the method is missing' error is popping up
  public static DijkstraAlgorithm(MutableValueGraph<String, Integer> graph, int source, HashSet<String> listOfVertices) {
    //psuedocode from Wikipedia page:
    //  1  function Dijkstra(Graph, source):
    //  2 
    //  3      for each vertex v in Graph.Vertices:
    //  4          dist[v] ← INFINITY
    //  5          prev[v] ← UNDEFINED
    //  6          add v to Q
    //  7      dist[source] ← 0
    //  8      
    //  9      while Q is not empty:
    // 10          u ← vertex in Q with min dist[u]
    // 11          remove u from Q
    // 12          
    // 13          for each neighbor v of u still in Q:
    // 14              alt ← dist[u] + Graph.Edges(u, v)
    // 15              if alt < dist[v]:
    // 16                  dist[v] ← alt
    // 17                  prev[v] ← u
    // 18
    // 19      return dist[], prev[]

    // start with endpoint that isn't original endpoint
    // think of each node as having a link in the chain to the next node
     String startNode = "0";
     int[] distance = new int[listOfVertices.size()];
     boolean[] visited = new boolean[listOfVertices.size()];
     HashMap via = new HashMap<String, String>();
     //i think prev is just via.
     // Does Q have to be a string array? removing u from Q will be hard as a result; can't we make it an Array List instead?
     // Update: turned it into an ArrayList
     ArrayList[] Q = new ArrayList[listOfVertices.size()];
     
     //for each vertex v in Graph.Vertices:
     for (int v = 0; v < listOfVertices.size(); v++) {
        // dist[v] ← INFINITY
        distance[v] = Integer.MAX_VALUE;
        // prev[v] ← UNDEFINED
        via.put(v, "undefined");
        // Commented out line below because it no longer works when Q is an ArrayList
        // Q[v] = listOfVertices.toArray()[v].toString();
        
        // add v to Q
        Q[listOfVertices.size()].add(v);
      }
      // dist[source] ← 0
      distance[Arrays.asList(Q).indexOf(source)] = 0;
      
      //is this necessary?
      // Q[Arrays.asList(Q).indexOf(u)] = null;
      // via[Arrays.asList(Q).indexOf(u)] = null;

      // need to iterate (maybe using a hashmap?) through dataset to get paths and determine order of
      // nodes the shortest possible path goes through
      
      // Not touching this because I saw it was already commented out - Eva
      // for (int u)
      
      // while Q is not empty:
      while (Q != null && Q.length > 0) {
        // u ← vertex in Q with min dist[u]
        // is source equal to this ^^?
        int u = source;
        
        //remove u from Q; i don't think we need to initialize a new variable like w1
        //what we do need to do if it's an string array is copy to a new array if you want the length to reduce.
        //Alternatively, we initialize Q as an ArrayList if we want a dynamic size array
        Q[listOfVertices.size()].remove(u);

        // What was this supposed to do?
        // distance[Q.indexOf(u)];

        // for each neighbor v of u still in Q:
        for (int v = 0; v < listOfVertices.size(); v++) {
          int alt = distance[u] + graph.edges(u, v);
          
          // What was this for?
          //Q[distance] = 

          // if alt < dist[v]:
          if (alt < distance[v]) {

          // via gets updated to = neighbor because neighbor is via
          // insert/modify location being looked at (key), via is the node that brought you there (where distance is)
          
          // dist[v] ← alt
            distance[v] = alt;
          // prev[v] ← u
            // Not sure how to fix this error
            via = u;
          }

        
        // for (u = 0; u < Q.length; u++) {
        //   // if (min(distance, 0, distance.length - 1) == distance[u]) {
        //   //   break;
        //   // }
        // }
        // System.out.println("u =  " + u);

        // String current = Q[u];
        // Q[u] = "null";

        // //getEdgeSet()

        // for (int v = 0; v < Q.length; v++) {
        //   //if (graph.hasEdgeConnecting(u, v)) {
        //   //}
      }
    }

    // is it possible to write return statements like this?
    // putting them in one line gave an error for via saying it couldn't be resolved
    return distance;
    return via;
  }
  
  // /**
  //  * Determines the shortest path between one node to another.1st
  //  * @param graph (MutableValueGraph<String,Integer>) The graph graphic that will display the dataset in a graph form
  //  * @param source (String) The starting node
  //  * @param edgeSet (ArrayList<String>) A group of the edges in the dataset
  //  */
  // public static HashMap DijkstraAlgorithm2(MutableValueGraph<String,Integer> graph, String source, ArrayList<String[]> edgeSet) {
  //   //psuedocode from Wikipedia page:
  //   //  1  function Dijkstra(Graph, source):
  //   //  2 
  //   //  3      for each vertex v in Graph.Vertices:
  //   //  4          dist[v] ← INFINITY
  //   //  5          prev[v] ← UNDEFINED
  //   //  6          add v to Q
  //   //  7      dist[source] ← 0
  //   //  8      
  //   //  9      while Q is not empty:
  //   // 10          u ← vertex in Q with min dist[u]
  //   // 11          remove u from Q
  //   // 12          
  //   // 13          for each neighbor v of u still in Q:
  //   // 14              alt ← dist[u] + Graph.Edges(u, v)
  //   // 15              if alt < dist[v]:
  //   // 16                  dist[v] ← alt
  //   // 17                  prev[v] ← u
  //   // 18
  //   // 19      return dist[], prev[]
     
  //   //3 hashtables- parents, nodes, and the cost. or the graph, the parents, and the costs.
  //   //parents hashmap, to see if the cost is lower. 

  //   // Are the parents via?
  //   HashMap<String, String> parents = new HashMap();

  //   ArrayList<String> listOfVertices = new ArrayList<String>();
  //   HashMap<String, Integer> nodeValues = new HashMap();
  //   HashMap<String, Integer> viaNode = new HashMap();
  //   HashSet<String> visited = new HashSet<>();

  //   //creating list of vertices
  //   for (int i = 0; i < edgeSet.size(); i++) {
  //     String[] temp = edgeSet.get(i);
  //     //this is a weird conditional homie
  //     if (listOfVertices.contains(temp[0])) {
  //       listOfVertices.add(temp[0]);
  //     }
  //   }
    
    
    
  //   // ArrayList<ArrayList<String>> adj = new ArrayList<ArrayList<String>>();
    
  //   // for (int n = 0; n < listOfVertices.size(); n++) {
  //   //   //maybe this has to be a loop in a loop type situation
  //   //     adj.set(n, new ArrayList<String>());
  //   // }

  //   // hashmap that take node value to a cost, another one that given a node label gives u the via node, and 
  //   // then a set to keep track of what nodes are already done. 

  //   // ask tomorrow about this part, supposed to keep track of adjacent nodes
  //   // for (int i = 0; i < edgeSet.size(); i++){
  //   //   String[] temp = edgeSet.get(i);
  //   //   source = temp[0];
  //   // }


  //   // for (int i = 0; i < edgeSet.size(); i++) {
  //   //   String[] temp = edgeSet.get(i);
  //   //   ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(temp));
  //   //   for (int j = 0; j < edgeSet.size(); j++) {
  //   //     //i would have to do another loop to get edgeset here, but using edgeset doesn't yield any fruitful results. 
  //   //     edgeSet.get(i)[j] = edgeSet.get(j);
  //   //   }
  //   //     edgeSet.get(i).add(tempList);
  //   // }

  //   HashMap<String, Integer> shortest = new HashMap<String, Integer>();
  //   ArrayList<String> minHeap = new ArrayList<>();
  //   minHeap.add("0");
  //   minHeap.add(source);

  //   while (minHeap.size() != 0) {
  //     int w1 = Integer.parseInt(minHeap.remove(0));
  //     String n1 = minHeap.remove(0);
  //     if (shortest.containsKey(n1)) {
  //       continue;
  //     }
  //     shortest.put(n1, w1);
      

  //     for (int i = 0; i < graph.adjacentNodes(n1).size(); i++) {
  //       //System.out.println(graph.adjacentNodes(n1).toArray()[i]);
  //       int w2 = Integer.parseInt(edgeSet.get(i)[2]);
  //       String n2 = graph.adjacentNodes(n1).toArray()[i].toString();

  //       // graph.adjacentNodes(n1).toArray()[i].toString();
  //       String via = n1;
        
  //       if (!shortest.containsKey(graph.adjacentNodes(n1).toArray()[i])) {
  //         minHeap.add(Integer.toString(w1 + w2));
  //         minHeap.add(n2);
  //         parents.put(via, n2);
  //         System.out.println(parents);

  //       }
          
  //       // if (edgeSet.get(i)[0] == n1) {
  //       //   int w2 = Integer.parseInt(edgeSet.get(i)[2]);
  //       //   String n2 = edgeSet.get(i)[1];
  //       //   if (!shortest.containsKey(n2)) {
  //       //     minHeap.add(w1 + w2, n2);
  //       //   }

  //       // }
  //     }
  //   }

  //   for (int i = 0; i < listOfVertices.size(); i++) {
  //     if (shortest.containsKey(listOfVertices.get(i))) {
  //       shortest.put(listOfVertices.get(i), -1);
  //     }
  //   }
    
  //   return shortest;
  // }

    /**
     * Returns the maximum value found in the specified range of a given array.
     * @param arr A given array of floats.
     * @param lo The lowest index at which the method will start looking for a maximum value.
     * @param hi The highest index at which the method will stop looking for a maximum value.
     * @return The maximum value found in the array within the specified range.
    */
    public static float max(int[] arr, int lo, int hi) { 

        if (arr.length == 0 || hi == 0) {
                return Float.NaN;
        }
        
        else {
            float maxValue = arr[lo];
            for (int i = lo; i < hi; i++) {
                    if (maxValue < arr[i]) {
                        maxValue = arr[i];
                    }
                    else {
                        continue;
                    }
                }
            return maxValue;
        }
        
    }
    /**
     * Prints the number of nodes, number of edges, maximum node degree, average node degree, and other characteristics of the dataset
     * @param listofVerticies (HashSet<String>) The verticies in the dataset being analyzed
     * @param graph (MutableValueGraph<String,Integer>) The graph graphic that will display the dataset in a graph form
     * @param edgeSets (ArrayList<String[]>) A group of the edges in the dataset
     */
    public static void printStatistics(HashSet<String> listOfVertices, MutableValueGraph<String,Integer> graph, ArrayList<String[]> edgeSets) {
      System.out.println("Number of nodes in this graph: " + graph.nodes().size());
      System.err.println("Number of edges: " + graph.edges().size());
      
      int[] storedEdges = new int[edgeSets.size()];
      for (int i = 0; i < listOfVertices.size(); i++) {
        // storedEdges.add(storedEdges.size()-1, graph.degree(listOfVertices.toArray()[i].toString()));
        storedEdges[i] = graph.degree(listOfVertices.toArray()[i].toString());
      }
      System.out.println("Maximum node degree: " + max(storedEdges, 0, storedEdges.length));

      //avg node degree
      int numberOfEdges = 0;
      for (int i = 0; i < graph.edges().size(); i++) {
        numberOfEdges++;
      }
      int avgDegree = numberOfEdges/listOfVertices.size();
      System.out.println("The average node degree: " + avgDegree);

    }
  

    public static void main(String[] args) {
      //MutableGraph<String> graph = GraphBuilder.directed().build();
      MutableValueGraph<String,Integer> graph = ValueGraphBuilder.undirected().build();
      HashSet<String> listOfVertices = new HashSet<>();
      ArrayList<String[]> edgeSets = ReadFile.ReadInFile(graph, listOfVertices);
      // Not sure how to fix this error
      System.out.println(DijkstraAlgorithm(graph, 6, listOfVertices));
      printStatistics(listOfVertices, graph, edgeSets);

      
    }
}
