import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Bank {	

	public static void main(String[] args) throws Exception {
		
		XmlUtils xml = new XmlUtils();		
		
		Client cli = new Client(1,"Joao Victor","Guimaraes","Somewhere","joaovictor","123456");
		
		cli.createNewClient(xml, cli);
		
//		NodeList list = xml.getElementsByTagName("Client");
//		
//		for(int i=0; i< list.getLength(); i++){
//			Node node = list.item(i);
//			
//			if(node.getNodeType() == Node.ELEMENT_NODE){
//				Element elem = (Element) node;
//				//xml.changeNodeValue(elem, "Name", "Joao");
//				System.out.println(elem.getElementsByTagName("Name").item(0).getTextContent());
//			}
//		}
		
		xml.updateXml();
	}

}
