import java.util.ArrayList;
import java.util.List;

public class Vertex {

	//right now there is just one edge because this for 
	// the valence two graph and the only edge stored is the forward edge
	// basically,  like a singly-liked list
	List<Edge> edgeList = new ArrayList<Edge>();
	Vertex next = null;
	
	Integer index;
	
	public Vertex(int index) {
		this.index = index;
	}
	
	public Integer getIndex() {
		return this.index;
	}
	
	public Vertex getNext() {
		return this.next;
	}
	
	public Boolean nextEdge() {
		if (edgeList.size() == 0) {
			return null;
		}
		return edgeList.get(0).exists();
		
	}
	
	public void setNextEdge(boolean edgeStatus) {
		edgeList.get(0).setStatus(edgeStatus);
	}
	
	public void addNextEdge() {
		Vertex nextV = new Vertex(this.getIndex() + 1);
		Edge newEdge = new Edge();
		newEdge.setV1(this);
		newEdge.setV2(nextV);
		this.next = nextV;
		this.edgeList.add(newEdge);
	}
	
}
