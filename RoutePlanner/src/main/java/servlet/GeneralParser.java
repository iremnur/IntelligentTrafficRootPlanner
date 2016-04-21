package servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;

public class GeneralParser {

	private static Parser parser;
	
	public static HashMap<String, Node> mapCreator(String[] args) {

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			parser = new Parser();
			saxParser.parse("C:\\Users\\irem\\RoutePlanner\\map.xml", parser.handler);
			Parser2 parser2 = new Parser2(parser.nodes);
			saxParser.parse("C:\\Users\\irem\\RoutePlanner\\map.xml", parser2.handler);

			/* deleteList in i�erisinde highwat olmayan node lar var. */
			List<String> deleteList = new ArrayList<String>();
			/*deleteList in i�erisinden t�m elemanlar� silme i�lemi.*/
			for (String key : parser.nodes.keySet()) {
				Node node = parser.nodes.get(key);
				
				if (node.getList().isEmpty()) {
					/* highway olmayan nodelar� deleteList'e ekleme i�lemi. */
					deleteList.add(node.getId());
				}
			}
			
		
			
			
			/*
			 * Burada highway olmayan nodelar� tuttu�umuz deleteList objesinin
			 * elemanlar�n� siliyoruz.
			 */
			for (String key : deleteList) {
				parser.nodes.remove(key);
			}
			
			
			
					
			/*node un kom�ular�n� yazd�r�yoruz.*/
			for (String key : parser.nodes.keySet()) {
				Node node = parser.nodes.get(key);

				//System.out.println("Node\n" + node.getId());
				// child
				for (Node child : node.getList()) {
					//System.out.println(child.getId());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMap();
	}
	public static HashMap<String, Node> getMap() {
		return parser.nodes;
	}
	
	

}// Bu a�aman�n sonunda �zerinde i�lem yapaca��m�z map olu�mu� oluyor.
