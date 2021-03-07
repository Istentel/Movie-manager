package gui;

import java.beans.XMLEncoder;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import javax.swing.DefaultListModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.xml.txw2.annotation.XmlAttribute;  

public class Movie {
	static MovieNode head;
	static int moviesCount;
	public static DefaultListModel<String> moviesDataList;
	
	public Movie(){
		head = null;
		moviesCount = 0;
		moviesDataList = new DefaultListModel<String>();
	}
	
	public static DefaultListModel<String> GetMoviesList() { return moviesDataList; }
	
	public static void addMovieNode(MovieNode m) {

		if(head == null) {
			head = m;
			
			String newMovie;
			
			newMovie = m.getName();
			newMovie += " - ";
			newMovie += m.getCategory();
			newMovie += " - ";
			newMovie += m.getReleaseDate();
			newMovie += " - ";
			newMovie += m.getRating();
			newMovie += " - ";
			newMovie += m.getImdb();
			
			moviesDataList.add(moviesCount, newMovie);
			
			moviesCount++;
			return;
		}
		
		m.next = null;
		
		MovieNode last = head;
		while(last.next  != null) {
			last = last.next;
		}
		
		last.next = m;
		
		String newMovie;
		
		newMovie = m.getName();
		newMovie += " - ";
		newMovie += m.getCategory();
		newMovie += " - ";
		newMovie += m.getReleaseDate();
		newMovie += " - ";
		newMovie += m.getRating();
		newMovie += " - ";
		newMovie += m.getImdb();
		
		moviesDataList.add(moviesCount, newMovie);
		
		moviesCount++;
		
	}
	
	public static void removeMovieNode(int index) {
		if(head == null) return;
		
		MovieNode temp = head;
		
		if(index == 0) {
			head = temp.next;
			return;
		}
		
		for(int i = 0; temp != null && i < index - 1; i++) {
			temp = temp.next;
		}
		
		if(temp == null || temp.next == null) {
			return;
		}
		
		MovieNode next = temp.next.next;
		
		temp.next = next;
	}
	
	public static String getNameNode(int index) {
		MovieNode temp = new MovieNode();
		
		temp = head;
		
		for(int i=0;i<index;i++) {
			temp = temp.next;
		}
		
		return temp.getName();
	}
	
	public static String getCategoryNode(int index) {
		MovieNode temp = new MovieNode();
		
		temp = head;
		
		for(int i=0;i<index;i++) {
			temp = temp.next;
		}
		
		return temp.getCategory();
	}
	
	public static String getReleaseDateNode(int index) {
		MovieNode temp = new MovieNode();
		
		temp = head;
		
		for(int i=0;i<index;i++) {
			temp = temp.next;
		}
		
		return temp.getReleaseDate();
	}
	
	public static String getRatingNode(int index) {
		MovieNode temp = new MovieNode();
		
		temp = head;
		
		for(int i=0;i<index;i++) {
			temp = temp.next;
		}
		
		return temp.getRating();
	}
	
	public static String getImdbNode(int index) {
		MovieNode temp = new MovieNode();
		
		temp = head;
		
		for(int i=0;i<index;i++) {
			temp = temp.next;
		}
		
		return temp.getImdb();
	}
	
	@XmlRootElement
	public static class MovieNode{
		private String name;
		private String category;
		private String releaseDate;
		private String rating;
		private String imdb;
		
		MovieNode next;
		
		MovieNode(){}
		
		MovieNode(String name, String cat, String rel, String rat, String imdb){
			this.name = name;
			this.category = cat;
			this.releaseDate = rel;
			this.rating = rat;
			this.imdb = imdb;
			this.next = null;
		}
		
		
		@XmlAttribute
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getReleaseDate() {
			return releaseDate;
		}

		public void setReleaseDate(String releaseDate) {
			this.releaseDate = releaseDate;
		}

		public String getRating() {
			return rating;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

		public String getImdb() {
			return imdb;
		}

		public void setImdb(String imdb) {
			this.imdb = imdb;
		}

		public MovieNode getNext() {
			return next;
		}

		public void setNext(MovieNode next) {
			this.next = next;
		}
	}
	
	//adds data to xml after that getXmlData() is called
	public static void addMovie(MovieNode m) throws SAXException, IOException {
		final String xmlFilePath = "moviesData.xml";
		
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document;
            Element root;
            
            if(!(new File(xmlFilePath).exists())) {
            	document = documentBuilder.newDocument();
                
            	root = document.createElement("movies");
            	
            	document.appendChild(root);
            } else {
 
            	document = documentBuilder.parse(new File(xmlFilePath));
            	
            	root = document.getDocumentElement();
            }
            
            //node name
            Element movieNode = document.createElement("movieNode");
            root.appendChild(movieNode);
            
            //name
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(m.getName()));
            movieNode.appendChild(name);
            
            //category
            Element category = document.createElement("category");
            category.appendChild(document.createTextNode(m.getCategory()));
            movieNode.appendChild(category);
            
            //releaseDate
            Element releaseDate = document.createElement("releaseDate");
            releaseDate.appendChild(document.createTextNode(m.getReleaseDate()));
            movieNode.appendChild(releaseDate);
            
            //rating
            Element rating = document.createElement("rating");
            rating.appendChild(document.createTextNode(m.getRating()));
            movieNode.appendChild(rating);
            
            //imdb
            Element imdb = document.createElement("imdb");
            imdb.appendChild(document.createTextNode(m.getImdb()));
            movieNode.appendChild(imdb);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
 
            transformer.transform(domSource, streamResult);
            
           
		} catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
	}
	
	public static void removeMovie(int index) throws ParserConfigurationException, SAXException, IOException {
		final String xmlFilePath = "moviesData.xml";
		
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		 
		documentFactory.setValidating(false);
		
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        
        Document document = documentBuilder.parse(new FileInputStream(new File(xmlFilePath)));
        
        Element removedMovie = (Element) document.getElementsByTagName("movieNode").item(index);
        
        if(removedMovie == null) return;
        
        removedMovie.getParentNode().removeChild(removedMovie);
        
        document.normalize(); 
        
        try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(xmlFilePath);
			transformer.transform(domSource, streamResult);
			
			moviesDataList.remove(index);
			moviesCount--;
			removeMovieNode(index);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void updateMovie(MovieNode m, int index) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException {
		final String xmlFilePath = "moviesData.xml";
		
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		 
		documentFactory.setValidating(false);
		
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        
        Document document = documentBuilder.parse(new FileInputStream(new File(xmlFilePath)));
        
        Element movieNode = (Element) document.getElementsByTagName("movieNode").item(index);
        
        //------
        
        
        //-------
        
        Element name = (Element) movieNode.getElementsByTagName("name").item(0);
        name.setTextContent(m.getName());
        
        Element category = (Element) movieNode.getElementsByTagName("category").item(0);
        category.setTextContent(m.getCategory());
        
        Element releaseDate = (Element) movieNode.getElementsByTagName("releaseDate").item(0);
        releaseDate.setTextContent(m.getReleaseDate());
        
        Element rating = (Element) movieNode.getElementsByTagName("rating").item(0);
        rating.setTextContent(String.valueOf(m.getRating()));
        
        Element imdb = (Element) movieNode.getElementsByTagName("imdb").item(0);
        imdb.setTextContent(String.valueOf(m.getImdb()));
        
        //-------
        
        document.normalize(); 
        
        try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(xmlFilePath);
			transformer.transform(domSource, streamResult);
			
			moviesDataList.remove(index);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void getXmlData() {

		File xmlData = new File("moviesData.xml");
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(xmlData);
		    
		    doc.getDocumentElement().normalize();
		    
		    NodeList nList = doc.getElementsByTagName("movieNode");		  
		    
		    for(int i = 0; i < nList.getLength(); i++) {
		    	Node nNode = nList.item(i);		    	
		    	
		    	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		    		Element eElement = (Element) nNode;
		    		
		    		MovieNode movie = new MovieNode();
		    		movie.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
		    		movie.setCategory(eElement.getElementsByTagName("category").item(0).getTextContent());
		    		movie.setReleaseDate(eElement.getElementsByTagName("releaseDate").item(0).getTextContent());
		    		movie.setRating(eElement.getElementsByTagName("rating").item(0).getTextContent());
		    		movie.setImdb(eElement.getElementsByTagName("imdb").item(0).getTextContent());
		    		movie.setNext(null);
		    		
		    		addMovieNode(movie);
		    	}
		    }
		} catch (Exception e) {
		    e.printStackTrace();
	    }
	}
	
	public static DefaultListModel<String> getCategorySortedData() {
		DefaultListModel sortedData = new DefaultListModel();
		
		MovieNode temp = new MovieNode();
		
		temp = head;
		int index = 0;
		sortedData.addElement("Action");
		while(temp != null) {
			String cat = temp.getCategory();
			
			if(temp.getCategory().equals("Action")) {
				sortedData.addElement(moviesDataList.elementAt(index));
				//System.out.println(moviesDataList.get(index));
			}
			index++;
			temp = temp.next;			
		}
		
		sortedData.addElement("Comedy");
		temp = head;
		index = 0;
		while(temp != null) {
			if(temp.getCategory().equals("Comedy")) {
				sortedData.addElement(moviesDataList.elementAt(index));
				
			}
			index++;
			temp = temp.next;			
		}
		
		sortedData.addElement("Drama");
		temp = head;
		index = 0;
		while(temp != null) {
			if(temp.getCategory().equals("Drama")) {
				sortedData.addElement(moviesDataList.elementAt(index));
				
			}
			index++;
			temp = temp.next;			
		}
		
		sortedData.addElement("Fantasy");
		temp = head;
		index = 0;
		while(temp != null) {
			if(temp.getCategory().equals("Fantasy")) {
				sortedData.addElement(moviesDataList.elementAt(index));
				
			}
			index++;
			temp = temp.next;			
		}
		
		sortedData.addElement("Horror");
		temp = head;
		index = 0;
		while(temp != null) {
			if(temp.getCategory().equals("Horror")) {
				sortedData.addElement(moviesDataList.elementAt(index));
				
			}
			index++;
			temp = temp.next;			
		}
		
		sortedData.addElement("Mystery");
		temp = head;
		index = 0;
		while(temp != null) {
			if(temp.getCategory().equals("Mystery")) {
				sortedData.addElement(moviesDataList.elementAt(index));
				
			}
			index++;
			temp = temp.next;			
		}
		
		sortedData.addElement("Romance");
		temp = head;
		index = 0;
		while(temp != null) {
			if(temp.getCategory().equals("Romance")) {
				sortedData.addElement(moviesDataList.elementAt(index));
				
			}
			index++;
			temp = temp.next;			
		}
		
		sortedData.addElement("Thriller");
		temp = head;
		index = 0;
		while(temp != null) {
			if(temp.getCategory().equals("Thriller")) {
				sortedData.addElement(moviesDataList.elementAt(index));
				
			}
			index++;
			temp = temp.next;			
		}
		
		return sortedData;
	}

	//for debbuging
	public static void getMovies() {
		MovieNode pointer = new MovieNode();
		
		pointer = head;
		
		while(pointer != null) {
			System.out.println("Name: " + pointer.getName());
			System.out.println("Category: " + pointer.getCategory());
			System.out.println("ReleaseDate: " + pointer.getReleaseDate());
			System.out.println("Rating: " + pointer.getRating());
			System.out.println("IMDB: " + pointer.getImdb());
			
			pointer = pointer.next;
		}
	}
	public static void main(String[] args) throws PropertyException, JAXBException, ParserConfigurationException, SAXException, IOException {
		Movie data = new Movie();
		DefaultListModel testData = new DefaultListModel();
		
		data.getXmlData();
		
		testData = data.getCategorySortedData();
		

		//System.out.println(testData.getElementAt(0));
		//System.out.println(testData.elementAt(1));
		
		for(int i=0;i<testData.getSize();i++) {
			System.out.println(testData.elementAt(i) + "   index =" + i);
			//System.out.println(moviesDataList.getElementAt(i));
		}
		
		
	}
}
