package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;

/**
 * Servlet implementation class Route
 */
public class Route extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String sourcePoint;
	public static String destinationPoint;
	public static NodeUCS result;
	public static double sourceLat;
	public static double sourceLon;
	public static double destinationLat;
	public static double destinationLon;

	/**
	 * Default constructor.
	 */
	public Route() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			Parser parser = new Parser();
			Parser2 parser2 = new Parser2(parser.nodes);
			
		
			saxParser.parse("C:\\Users\\irem\\RoutePlanner\\map.xml",
					parser.handler);
			saxParser.parse("C:\\Users\\irem\\RoutePlanner\\map.xml",
					parser2.handler);
			// returns map
			GeneralParser.mapCreator(null);

		
			
			if (request.getParameter("src") != null) {
				String[] array = request.getParameter("src").split(",");
				sourceLon = Double.parseDouble(array[0]);
				sourceLat =  Double.parseDouble(array[1]);
				//sourceLon = new Double(request.getParameter("src"));
			}
			if (request.getParameter("dst") != null) {
				String[] array = request.getParameter("dst").split(",");
				destinationLon = Double.parseDouble(array[0]);
				destinationLat =  Double.parseDouble(array[1]);
			}
			
			
			
			Node closestSource = null;
			Node closestDestination= null;
			double minDistance = Integer.MAX_VALUE;
			double x;
			double y;
			for(Node node : GeneralParser.getMap().values()){
				x=(node.getLat()-sourceLat);
				y=(node.getLon()-sourceLon);
				float distance = (float) Math.sqrt((x*x)+(y*y));
				if(distance < minDistance) {
					closestSource= node;
					minDistance = distance;
				}
			}
			System.out.println("ClosestSource :"+ closestSource);
			double minDistancee = Integer.MAX_VALUE;
			for(Node node : GeneralParser.getMap().values()){
				x=(node.getLat()-destinationLat);
				y=(node.getLon()-destinationLon);
				float distance = (float) Math.sqrt((x*x)+(y*y));
				if(distance < minDistancee) {
					closestDestination= node;
					minDistancee = distance;
				}
			}
			
			System.out.println("ClosestDestination :"+ closestDestination);
			result = Algorithm.UCS(closestSource, closestDestination);

			// baþlangýç noktasý olarak verebiliriz yada lat long arasýndaki
			// farký alabiliriz.
			//virgülden sonraki 6. basamaðý yuvarlýyor.
			String centerObservationMap = (Float.toString(result.getNode()
					.getLon()) + "," + Float
					.toString(result.getNode().getLat()));
			
		
			
			
			// edgeCoordinates iki nokta arasýndaki yolun lat lon deðerlerini
			// tutuyor.
			List<String> edgeCoordinates = new ArrayList<>(); 

			while (result != null) {

				edgeCoordinates.add(Float.toString(result.getNode().getLon())
						+ "," + Float.toString(result.getNode().getLat()));
				
				result = result.getParent();
				System.out.println("deneme");
			}
		
			

			// get UI
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("edges.vm");
	
			request.setAttribute("edgeList", edgeCoordinates);
			request.setAttribute("centerObservationMap", centerObservationMap);
			requestDispatcher.forward(request, response);
		} catch (Exception ex) {
			System.out.println("Hata");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
