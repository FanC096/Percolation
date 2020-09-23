import java.util.LinkedList;



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
	  
	  LinkedList<LinkedList<Double>> exps = new LinkedList();
	  
	  for (double p = 0.1; p < 0.9; p = p+0.1) {
	    
	    //average across multiple runs on large graph
	    double sumOfAvgs = 0;
	    int runs = 100; // number of times to run
	//    int runs = 1;
	    for (int i = 0; i < runs ; i++) {
	    	sumOfAvgs = sumOfAvgs + oneRun(50000, p);
	//    	sumOfAvgs = sumOfAvgs + oneRun(50, 0.5);
	
	    }
	    
	    LinkedList<Double> sublist = new LinkedList();
	    sublist.add(p);
	    sublist.add(1, sumOfAvgs / (double) runs);
	    exps.add(sublist);
	    
	    // System.out.println(sumOfAvgs);
	  //  System.out.println("average over runs is " + sumOfAvgs / runs);
	    
	  }
    System.out.println(exps);
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
//	   testGraph.printGraph(); //so you can see the vertices and edges
	   System.out.println("quick avg gives the avg: " + testGraph.quickAvgClusters() + " nodes per clusters");
	   return testGraph.countClusters();
  }





}