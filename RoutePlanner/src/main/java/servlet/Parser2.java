package servlet;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Parser2 {
	boolean oneWay = false;
	boolean highway = false;

	HashMap<String, Node> nodes; //all nodes
	List<Node> nodesList = new ArrayList<Node>(); // nd ler
	List<Node> place_of_worship= new ArrayList<Node>();
	Node mosque;

	public Parser2(HashMap<String, Node> nodes) {
		this.nodes = nodes;
		
	}

	DefaultHandler handler = new DefaultHandler() {

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {

			// System.out.println("Start Element :" + qName);

			if (qName.equalsIgnoreCase("way")) {
				
				if (nodesList.size() > 0 && highway) {
					
					Node prev = null;

					for (int i = 0; i < nodesList.size(); i++) {
						if (prev == null) {
							prev = nodesList.get(i);
						} else {
							Node cur = nodesList.get(i);
							prev.getList().add(cur);

							if (!oneWay) {
								cur.getList().add(prev);
							}

							prev = cur;
						}
					}
				}
				nodesList.clear();
				oneWay = false;
				highway = false;

			}

			if (qName.equalsIgnoreCase("tag")) {
				// yani tek yönlü yolsa
				if (attributes.getValue(0) != null
						&& attributes.getValue(0).equals("oneWay")
						&& attributes.getValue(1).equals("yes")) {
					oneWay = true;
				}
				//yani highwayse highway i true yap.
				if (attributes.getValue(0) != null
						&& attributes.getValue(0).equals("highway")) {
					highway = true;
				}
				if(attributes.getValue(0) == "amenity"){
					mosque=nodes.get(attributes.getValue(0).equals("amenity"));
					System.out.println("Mosqueeee    "+mosque);
					place_of_worship.add(mosque);
				}

			}

			if (qName.equalsIgnoreCase("nd")) {
				Node nd = nodes.get(attributes.getValue(0));
				nodesList.add(nd);
			}

		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {

			// System.out.println("End Element :" + qName);

		}

	};

}// end of class

