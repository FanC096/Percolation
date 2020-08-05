package edu.brown.cs.student.stars;

public class Edge {
	Vertex v1;
	Vertex v2;
	boolean exists = true;
	
	public void setV1(Vertex v1) {
		this.v1  = v1;
	}

	public void setV2(Vertex v2) {
		this.v2  = v2;
	}
	
	public Boolean exists() {
		return this.exists;
	}
	
	public void setStatus(boolean status) {
		this.exists = status;
	}
	
}
