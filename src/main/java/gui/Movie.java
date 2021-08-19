package gui;

import java.beans.XMLEncoder;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
	
	public Movie(){
		head = null;
	}
	
	public static void addMovieNode(MovieNode m) {

		if(head == null) {
			head = m;
			return;
		}
		
		m.next = null;
		
		MovieNode last = head;
		while(last.next  != null) {
			last = last.next;
		}
		
		last.next = m;		
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
		
		MovieNode(MovieNode m){
			this.name = m.getName();
			this.category = m.getCategory();
			this.releaseDate = m.getReleaseDate();
			this.rating = m.getRating();
			this.imdb = m.getImdb();
			this.next = null;
		}
		
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
	
	public static void resetHeadNode() { head = null; }
	
	public static int getIndex(MovieNode data) {
		MovieNode temp = new MovieNode();
		
		resetHeadNode();
		getXmlData();
		temp = head;
		int index = 0;
		
		while(temp != null) {
			if(temp.getName().equals(data.getName()) && temp.getCategory().equals(data.getCategory()) && temp.getReleaseDate().equals(data.getReleaseDate())
					&& temp.getRating().equals(data.getRating()) && temp.getImdb().equals(data.getImdb())) {
				return index;
			}
			
			temp = temp.next;
			index++;
		}
		
		return -1;
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
	
	public static MovieNode getCategorySortedData() {
		MovieNode headCategory = new MovieNode();
		MovieNode dp = new MovieNode();
		MovieNode temp = new MovieNode();
		
		dp = headCategory;
		
		temp = head;
		while(temp != null) {
			if(temp.getCategory().equals("Action")) {
				dp.next = new MovieNode(temp);
				dp = dp.next;				
			}

			temp = temp.next;			
		}
		

		temp = head;
		while(temp != null) {
			if(temp.getCategory().equals("Comedy")) {
				dp.next = new MovieNode(temp);
				dp = dp.next;
			}
			temp = temp.next;			
		}
		

		temp = head;
		while(temp != null) {
			if(temp.getCategory().equals("Drama")) {
				dp.next = new MovieNode(temp);
				dp = dp.next;				
			}
			temp = temp.next;			
		}
		

		temp = head;
		while(temp != null) {
			if(temp.getCategory().equals("Fantasy")) {
				dp.next = new MovieNode(temp);
				dp = dp.next;				
			}
			temp = temp.next;			
		}
		

		temp = head;
		while(temp != null) {
			if(temp.getCategory().equals("Horror")) {
				dp.next = new MovieNode(temp);
				dp = dp.next;				
			}
			temp = temp.next;			
		}
		

		temp = head;
		while(temp != null) {
			if(temp.getCategory().equals("Mystery")) {
				dp.next = new MovieNode(temp);
				dp = dp.next;				
			}
			temp = temp.next;			
		}
		

		temp = head;
		while(temp != null) {
			if(temp.getCategory().equals("Romance")) {
				dp.next = new MovieNode(temp);
				dp = dp.next;			
			}
			temp = temp.next;			
		}
		

		temp = head;
		while(temp != null) {
			if(temp.getCategory().equals("Thriller")) {
				dp.next = new MovieNode(temp);
				dp = dp.next;				
			}
			temp = temp.next;			
		}
		
		return headCategory.next;
	}
	
	public static MovieNode getRatingSortedData() {
		MovieNode headRating = new MovieNode();
		MovieNode dp = new MovieNode();
		MovieNode temp = new MovieNode();		
		
		dp = headRating;
		temp = head;
		
		while(temp != null) {
			int fin = 0;
			int index = 0;
			temp = head;
			MovieNode add = new MovieNode(temp);
			while(temp != null) {
				if(Integer.parseInt(add.getRating()) < Integer.parseInt(temp.getRating())) {
					add = temp;
					fin = index;
				}
				index++;
				temp = temp.next;
			}

			dp.next = new MovieNode(add);
			dp = dp.next;
			removeMovieNode(fin);
			temp = head;
		}
		
		getXmlData();
		return headRating.next;
	}
	
	public static MovieNode getImdbSortedData() {
		MovieNode headImdb = new MovieNode();
		MovieNode dp = new MovieNode();
		MovieNode temp = new MovieNode();		
		
		dp = headImdb;
		temp = head;
		
		while(temp != null) {
			int fin = 0;
			int index = 0;
			temp = head;
			MovieNode add = new MovieNode(temp);
			while(temp != null) {
				if(Float.parseFloat(add.getImdb()) < Float.parseFloat(temp.getImdb())) {
					add = temp;
					fin = index;
				}
				index++;
				temp = temp.next;
			}

			dp.next = new MovieNode(add);
			dp = dp.next;
			removeMovieNode(fin);
			temp = head;
		}
		
		getXmlData();
		return headImdb.next;
	}

	public static void writeRaport(String name, MovieNode data) {
		try {
			FileWriter file = new FileWriter(name);
			
			MovieNode temp = new MovieNode();
			
			temp = data;
			int index = 1;
			while(temp != null) {
				String dataToWrite = Integer.toString(index) + ". ";
				dataToWrite += "Name: " + temp.getName() + "  ";
				dataToWrite += "Category: " + temp.getCategory() + "  ";
				dataToWrite += "Release Date: " + temp.getReleaseDate() + "  ";
				dataToWrite += "Rating: " + temp.getRating() + "  ";
				dataToWrite += "IMDB: " + temp.getImdb() + "  \n";
				file.write(dataToWrite);
				index++;
				System.out.println(temp.getName());
				temp = temp.next;				
			}
			file.close();
			
		}catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
		} 
			
	}

}
