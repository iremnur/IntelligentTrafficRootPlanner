package servlet;

import java.util.List;
import java.util.PriorityQueue;

public class Algorithm {

	public final static double AVERAGE_RADIUS_OF_EARTH = 6371;

	public static double calculateDistance(double lat1, double long1,
			double lat2, double long2) {

		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(long2 - long1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2)
				* Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return 6372.8 * c * 1000;
	}

	public static NodeUCS UCS(Node s, Node g) {

		NodeComparatorUCS nodeComparator = new NodeComparatorUCS();
		/* 2000 is the capacity, */
		PriorityQueue<NodeUCS> expandList = new PriorityQueue<NodeUCS>(2000,
				nodeComparator);

		NodeUCS startPoint = new NodeUCS();
		startPoint.setNode(s);
		startPoint.setParent(null);
		startPoint.setCost(0);

		expandList.add(startPoint);

		while (expandList.size() > 0) {
			NodeUCS toBeExpanded = expandList.remove();
			if (toBeExpanded.getNode().equals(g)) {
				return toBeExpanded;
			}
			List<Node> neighbors = toBeExpanded.getNode().getList();
			// neighbors, toBeExpanded'ýn bütün komþularý.

			for (Node neighbor : neighbors) {

				NodeUCS neighbors1 = new NodeUCS();
				neighbors1.setParent(toBeExpanded);
				neighbors1.setNode(neighbor);
				neighbors1.setCost(toBeExpanded.getCost()
						+ (int) calculateDistance(toBeExpanded.getNode()
								.getLat(), toBeExpanded.getNode().getLon(),
								neighbors1.getNode().getLat(), neighbors1
										.getNode().getLon()));

				expandList.add(neighbors1);

			}

		}
		return null;
	}

	public static void print(NodeUCS result) {
		/* path i yazdýrcaz */

		if (result != null) {
			System.out.println(result.getCost());
			System.out.println("Ways between two points.");
			while (result != null) {
				result.print();
				result = result.getParent();
			}
		} else {
			System.out.println("Result node un null!!!!!!");
		}
	}

}
