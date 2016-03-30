package qc.rest.examples.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class ReadXMLFileSAX {
 
   public static void main(String argv[]) {
	   
    try {
 
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
 
	DefaultHandler handler = new DefaultHandler() {
 
	boolean bfname = false;
	boolean blname = false;
	boolean bnname = false;
	boolean bsalary = false;
	boolean bfield = false;
 
	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {
 
		System.out.println("Start Element :" + qName);
 
		if (qName.equalsIgnoreCase("FIELD")) {
			if(attributes.getValue("Name").equalsIgnoreCase("test-id")){
				bfield = true;
			}
			
		}
  
	}
 
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
 
		System.out.println("End Element :" + qName);
 
	}
 
	public void characters(char ch[], int start, int length) throws SAXException {
 
		if (bfield) {
			System.out.println("Field : " + new String(ch, start, length));
			bfield = false;
		}
		 
	}
 
     };
 
       saxParser.parse(System.getProperty("user.dir") + "\\src\\qc\\rest\\examples\\xml\\testInstances.xml", handler);
 
     } catch (Exception e) {
       e.printStackTrace();
     }
 
   }
 
}
