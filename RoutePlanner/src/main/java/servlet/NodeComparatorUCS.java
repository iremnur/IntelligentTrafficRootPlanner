package servlet;

/*Comparator art�k NodeUCS i�in �al��acak.*/

import java.util.Comparator;

public class NodeComparatorUCS implements Comparator<NodeUCS> {

	public int compare(NodeUCS node, NodeUCS node2) {
		int costOne = node.getCost();
		int costTwo = node2.getCost();
		
		if(costOne < costTwo)
		{
			return -1;
		}
		
		if(costTwo < costOne)
		{
			return 1;
		}
		
		return 0;
	}

}

