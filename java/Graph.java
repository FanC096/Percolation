import java.util.ArrayList;
import java.util.List;


public class Graph {
	Double probability;
	Vertex root = new Vertex(0);
	Integer numNodes;
	double numClosedEdges = 0;
	
	public void makeGraph(int numNodes, double prob) {
		this.probability = prob;
		this.numNodes = numNodes; //maybe in constructor
		Vertex curr = root;
				
		for (int i = 0; i < (numNodes - 1); i++) {
			curr.addNextEdge();
			curr = curr.getNext();
		}
		//System.out.println("Made graph!");
	}
	

	//this is really inefficient b/c we make the graph, then break it
	// could be sped up
	public void breakEdges() {
		Vertex curr = this.root;
		Boolean prevEdgeStatus = null;
		while (curr.getNext() != null) {
			Double randomNum = Math.random(); //something between 0 and 1
			Boolean status;
			if (randomNum <= this.probability) { //is this the right way?
				status = true;
			} else {
				status = false;
				this.numClosedEdges++;
			}
			curr.setNextEdge(status);
			prevEdgeStatus = status;
			curr = curr.getNext();
			//what we want is to get the edge and break it or not
		}

		// if curr.getNext() is null (we've reached the end of the graph)
		// prevEdgeStatus true indicates that curr is part of an existing cluster
		if (prevEdgeStatus) {
			extendGraph(curr);
		}
		
		makeRootFuzzy(root);
		
	}

	public void extendGraph(Vertex curr) {
		Double randomNum = Math.random();
		if (randomNum <= this.probability) {
			curr.addNextEdge();
			curr.setNextEdge(true);
			this.numNodes++;
			extendGraph(curr.getNext());
		} else {
			// stop extending once first closed edge is reached
			return;
		}
	}
	
	// Should be called from breakEdges() on the root Vertex
	public void makeRootFuzzy(Vertex curr) {
//		System.out.println("calling makeRootFuzzy");
		Double randomNum = Math.random();
		if (randomNum <= this.probability) {
			Vertex prev = new Vertex(curr.getIndex() - 1);
			Edge newEdge = new Edge();
			newEdge.setV1(curr);
			newEdge.setV2(prev);
			prev.edgeList.add(newEdge);
			prev.next = curr;
			
			this.root = prev;
			this.numNodes++;
			makeRootFuzzy(this.root);
		} else {
			return;
		}
	}
	
	/**
	 * Count clusters in the graph, after graph edges have been broken
	 * @return
	 */
	public double countClusters(){ //probably could speed this by doing @same time
		List<List<Integer>> clusterList = new ArrayList();
		Vertex curr = root;
		List<Integer> currCluster = new ArrayList();
		currCluster.add(curr.getIndex());
		
		while (curr != null) {
			if (curr.nextEdge() == null) {
				//duplicate code, ikr
				clusterList.add(currCluster);
				break;
			}
			if (curr.nextEdge() == true) { //fix
				currCluster.add(curr.getNext().getIndex());
			} else {
				clusterList.add(currCluster);
				currCluster = new ArrayList();
				currCluster.add(curr.getNext().getIndex());
			}
			curr = curr.getNext();
			
		}
		
		return this.crunchClusters(clusterList, this.numNodes);

		
	}
	/**
	 * Find average size of the clusters in a given graph
	 * @param clusterList -- list of clusters found already
	 * @param expected -- expected size of graph for error checking
	 * @return average size of clusters
	 */
	public double crunchClusters(List<List<Integer>> clusterList, int expected) {
		List<Integer> countList = new ArrayList<Integer>();
		int total = 0;
		for (List<Integer> cluster : clusterList) {
			int clusterSize = cluster.size();
			countList.add(clusterSize);
			total = total + clusterSize;
		}
		
		int numClusters = clusterList.size();
		assert(countList.size() == numClusters);
		//get average cluster size
		// THE MISSING FLOAT CAST HERE WAS THROWING THINGS OFF (every result was 1 or 2)
		double averageSize = (double) total / numClusters;
		
		System.out.println(countList); //to print out cluster 
		                               // sizes in a list by cluster
//		System.out.println("numClusters is " + numClusters);
//		System.out.println("countList size is " + countList.size());
//		System.out.println("total vertices is " + total);
//		System.out.println("average size is " + averageSize);
		return averageSize;
	}
	
	/**
	 * Find average size of the clusters in a given graph quickly, by counting number of closed edges
	 * @return average size of clusters
	 */
	public double quickAvgClusters()  {
		
		// a finite graph with n closed edges has n+1 clusters 
		return (double) this.numNodes / (this.numClosedEdges + 1);
	}
	
/**
 * To print the graph out in a basic way
 */
	public void printGraph() {
		Vertex curr = root;
		String builtString = "";
		
		while (curr != null) {
			String edgeString = "-/-";
			if (curr.getNext() == null) {
				System.out.println(builtString + "v" + curr.getIndex() +" end");
				return;
			}
			if (curr.nextEdge()) {
				edgeString = "---";
			}
			builtString = builtString + "v" + curr.getIndex() + edgeString;
			curr = curr.getNext();
		}
		
	}

}
