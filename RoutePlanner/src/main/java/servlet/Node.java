package servlet;

import java.util.ArrayList;
import java.util.List;

public class Node {

	int node_version;
	private float lon;
	private float lat;
	String user;
	String uid;
	private String id;
	
	// g(n) transition cost from initial state to this node.

	Parser parser = new Parser();
	Algorithm alg = new Algorithm();

	public Node() {
		this.id = getId();

	}

	public Node(String point,float lat,float lon) {
		/* point kullanýcýdan alýnan id lerdir. */
		id=point;
		this.lat=lat;
		this.lon=lon;

	}

	// Create a linked list
	private List<Node> list = new ArrayList<Node>();// tüm node larým


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Node> getList() {
		return list;
	}

	public void setList(List<Node> list) {
		this.list = list;
	}

	public void print() {
		
		System.out.println(id);
		
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}
	
}
