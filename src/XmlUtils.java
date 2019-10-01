import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlUtils {
	private File xmlFile;
	private DocumentBuilderFactory facBuilder;
	private DocumentBuilder docBuilder;
	private Document doc;
	
	public XmlUtils() throws Exception{
		xmlFile = new File(".\\bank.xml");		
		facBuilder = DocumentBuilderFactory.newInstance();
		docBuilder = facBuilder.newDocumentBuilder();		
		doc = docBuilder.parse(xmlFile);
	}
	
	public void changeNodeValue(Element element, String name, String value){
		element.getElementsByTagName(name).item(0).setTextContent(value);
	}
	
	public NodeList getElementsByTagName(String name){
		return doc.getElementsByTagName(name);
	}
	
	public void updateXml() throws Exception{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(xmlFile);
		transformer.transform(source, result);
	}
	
	public Element createNewElement(){
		NodeList nodes = doc.getElementsByTagName("Bank");
		Element element = (Element) nodes.item(0);
		Element elem = doc.createElement("Client");
		element.appendChild(elem);
		return elem;
	}
	
	public void createChildElement(Element element, String name, String value){
		Element child = doc.createElement(name);
		child.appendChild(doc.createTextNode(value));
		element.appendChild(child);
	}	
}
