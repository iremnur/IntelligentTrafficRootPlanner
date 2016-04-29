
package servlet;

import java.util.ArrayList;
import java.util.List;

/*NodeUcs classý girilen noktadan gidilecek olan her bir noktayý yani edge i tutuyor.*/
public class NodeUCS {
	
	private Node node;
	private NodeUCS parent;
	private int cost;
	// Create a linked list
		private List<NodeUCS> list = new ArrayList<NodeUCS>();// tüm node larým
		
	public NodeUCS() {
		
		this.setCost(0);
		this.setParent(parent);
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public List<NodeUCS> getList() {
		return list;
	}

	public void setList(List<NodeUCS> list) {
		this.list = list;
	}
	
	public NodeUCS getParent() {
		return parent;
	}
	public void setParent(NodeUCS parent) {
		this.parent = parent;
	}

	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public void print() {
		
		System.out.println(node.getId());
		
	}

}
