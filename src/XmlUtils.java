import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlUtils 
{
	private File xmlFile;
	private DocumentBuilderFactory facBuilder;
	private DocumentBuilder docBuilder;
	private Document doc;
	
	// Initialization of the xml variables
	public XmlUtils()
	{
		try {
			xmlFile = new File(".\\bank.xml");		
			facBuilder = DocumentBuilderFactory.newInstance();
			docBuilder = facBuilder.newDocumentBuilder();			
			doc = docBuilder.parse(xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Changing the information in a variable in the xml
	public void changeNodeValue(Element element, String name, String value)
	{
		element.getElementsByTagName(name).item(0).setTextContent(value);
	}
	
	// Getting information in the xml file by the tag name
	public NodeList getElementsByTagName(String name)
	{
		return doc.getElementsByTagName(name);
	}
	
	// Updating the information in the xml file that was changed
	public void updateXml()
	{
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
		
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	// Creating a new element in the xml file. Using an parent name and attaching a child to it
	public Element createNewElement(String parentName, String newElement)
	{
		NodeList nodes = doc.getElementsByTagName(parentName);
		Element element = (Element) nodes.item(0);
		Element elem = doc.createElement(newElement);
		element.appendChild(elem);
		return elem;
	}
	
	public Element createNewParentElement(Element element, String name)
	{
		Element elem = doc.createElement(name);
		element.appendChild(elem);
		return elem;
	}
	
	// Creating a child for an element (parent)
	public void createChildElement(Element element, String name, String value)
	{
		Element child = doc.createElement(name);
		child.appendChild(doc.createTextNode(value));
		element.appendChild(child);
	}	
	
	//Getting integer values from the xml
	public int getIntValue(Element element, String tagName){
		if(element.getElementsByTagName(tagName).item(0).getTextContent().isEmpty()){
			return 0;
		}
		return Integer.parseInt(element.getElementsByTagName(tagName).item(0).getTextContent());
	}
	
	//Getting double values from the xml
	public double getDoubleValue(Element element, String tagName){
		if(element.getElementsByTagName(tagName).item(0).getTextContent().isEmpty()){
			return 0;
		}
		return Double.parseDouble(element.getElementsByTagName(tagName).item(0).getTextContent());
	}
	
	public void removeElement(Element element){
		element.getParentNode().removeChild(element);
	}
	
	public String getNextId(String name){
		Element element = (Element) doc.getElementsByTagName("Bank").item(0);
		String nextId = String.valueOf(Integer.parseInt(element.getAttribute(name)) + 1);
		element.setAttribute(name, nextId);
		return nextId;
	}
}
