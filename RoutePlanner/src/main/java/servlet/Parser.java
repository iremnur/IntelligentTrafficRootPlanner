package servlet;



import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Parser {

	static boolean way = false;
	static boolean node_version = false;
	
	HashMap<String, Node> nodes = new HashMap<String, Node>();
	static HashMap<Float, Node> nodesLat = new HashMap<Float, Node>();
	
	
	public static HashMap<Float, Node> getNodesLat() {
		return nodesLat;
	}


	public void setNodesLat(HashMap<Float, Node> nodesLat) {
		this.nodesLat = nodesLat;
	}


	DefaultHandler handler = new DefaultHandler() {

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {

			// System.out.println("Start Element :" + qName);

			if (qName.equalsIgnoreCase("NODE")) {
				Node node = new Node();
				Node nodeLat= new Node();
				node.setId(attributes.getValue(0));
				node.setLat(Float.parseFloat(attributes.getValue(7)));
				node.setLon(Float.parseFloat(attributes.getValue(8)));
				nodes.put(node.getId(), node);
				nodesLat.put(node.getLat(), nodeLat);
			}	
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {

			//System.out.println("End Element :" + qName);

		}
		

	};

	

}// end of class
