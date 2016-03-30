package qc.rest.examples.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.Hashtable;
 
public class ReadXMLFileDOM {
	
  public Hashtable<String, Hashtable<String, String>> testInstance = new Hashtable<String, Hashtable<String, String>>();
  
  public void getInstance(String responseStr){
 
    try {
    
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	InputSource is = new InputSource(new StringReader(responseStr));
	Document doc = dBuilder.parse(is);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
 
	//System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
	NodeList nInstances = doc.getElementsByTagName("Fields");
	//System.out.println("Instances count : " + nInstances.getLength());
	for (int i = 0; i < nInstances.getLength(); i++){
		System.out.println("---------- " + (i+1) + "----------");
		Hashtable<String, String> table = new Hashtable<String, String>();
		Node nNodeInstance = nInstances.item(i);
		NodeList nList = nNodeInstance.getChildNodes();
		
		for (int j = 0; j < nList.getLength(); j++) {
			Node nNode = nList.item(j);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				// Get test-id
				if(eElement.getAttribute("Name").equalsIgnoreCase("test-id")){
					System.out.println("Name: " + eElement.getAttribute("Name"));
					try {
						table.put("test-id", eElement.getElementsByTagName("Value").item(0).getTextContent());
						System.out.println("Value: " + eElement.getElementsByTagName("Value").item(0).getTextContent());
					}catch (Exception e) {}
				}
				// Get status
				if(eElement.getAttribute("Name").equalsIgnoreCase("status")){
					System.out.println("Name: " + eElement.getAttribute("Name"));
					try {
						table.put("status", eElement.getElementsByTagName("Value").item(0).getTextContent());
						System.out.println("Value: " + eElement.getElementsByTagName("Value").item(0).getTextContent());
					}catch (Exception e) {}
				}
			}
		}
		testInstance.put(table.get("test-id"), table);
	}
	

    } catch (Exception e) {
    	e.printStackTrace();
    }
    
	
    
  }
  
  public Hashtable<String, String> setField(Element eElement, String sName, Hashtable<String, String> table){
		if(eElement.getAttribute("Name").equalsIgnoreCase(sName)){
			//System.out.println("Name: " + eElement.getAttribute(sName));
			try {
				table.put(sName, eElement.getElementsByTagName("Value").item(0).getTextContent());
				//System.out.println("Value: " + eElement.getElementsByTagName("Value").item(0).getTextContent());
			}catch (Exception e) {}
		}
		return table;
  }
    

 
}