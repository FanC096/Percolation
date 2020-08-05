package edu.brown.cs.student.stars;


/**
 * The Main class of our project. This is where execution begins.
 *
 */
public final class Main {


  /**
   * The initial method called when execution begins.
   *
   * @param args
   *          An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {
    
    //average across multiple runs on large graph
    double total = 0;
    int runs = 1000; // number of times to run
    for (int i = 0; i < runs ; i++) {
    	total = total + oneRun(50000, 0.5);
    }
    
    System.out.println("average over runs is " + total / runs);
    
    
  }
  /**
   * 
   * @param nodes
   * @param prob
   * @return
   */
  private double oneRun (int nodes, double prob) {
	   Graph testGraph = new Graph();
	   testGraph.makeGraph(nodes, prob); 
	   testGraph.breakEdges();
	   //testGraph.printGraph(); //so you can see the vertices and edges
	   return testGraph.countClusters();
  }





}