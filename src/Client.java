import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Client 
{
	private int id;
	private String name;
	private String lastname;
	private String email;
	private String address;
	private String password;
	private Account saving;
	private Account chequing;
	private Credit credit;
	private Element nodeElement;

	public Client()
	{
		
	}
		
	public Client(int id, String name, String lastname, String email, String address, String password) 
	{
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getLastName() 
	{
		return lastname;
	}
	public void setLastName(String lastname) 
	{
		this.lastname = lastname;
	}	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}	
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Account getSaving() {
		return saving;
	}

	public void setSaving(Account saving) {
		this.saving = saving;
	}

	public Account getChequing() {
		return chequing;
	}

	public void setChequing(Account chequing) {
		this.chequing = chequing;
	}
	
	public Element getNodeElement() {
		return nodeElement;
	}

	public void setNodeElement(Element nodeElement) {
		this.nodeElement = nodeElement;
	}
	
	public boolean validateLogin(XmlUtils xml, String email, String password){
		try{
			NodeList list = xml.getElementsByTagName("Client");
			
			for(int i=0; i< list.getLength(); i++){
				Node node = list.item(i);			
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element elem = (Element) node;
					if(elem.getElementsByTagName("Email").item(0).getTextContent().equalsIgnoreCase(email)){
						if(elem.getElementsByTagName("Password").item(0).getTextContent().equalsIgnoreCase(password)){
							return true;
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void updateClientInfo(XmlUtils xml, Client client){
		try {
			xml.changeNodeValue(client.nodeElement, "Name", client.name);
			xml.changeNodeValue(client.nodeElement, "LastName", client.lastname);
			xml.changeNodeValue(client.nodeElement, "Email", client.email);
			xml.changeNodeValue(client.nodeElement, "Address", client.address);
			xml.changeNodeValue(client.nodeElement, "Password", client.password);		
			xml.updateXml();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createNewClient(XmlUtils xml, Client client)
	{
		try {
			Element element = xml.createNewElement("Bank","Client");
			xml.createChildElement(element, "Id", String.valueOf(client.id));
			xml.createChildElement(element, "Name", client.name);
			xml.createChildElement(element, "LastName", client.lastname);
			xml.createChildElement(element, "Email", client.email);
			xml.createChildElement(element, "Address", client.address);
			xml.createChildElement(element, "Password", client.password);
			xml.createChildElement(element, "Admin", "False");
			
			Element saving = xml.createNewParentElement(element,"Saving");
			xml.createChildElement(saving, "Number", String.valueOf(Math.random()));
			xml.createChildElement(saving, "Balance", "0");
			xml.createNewParentElement(saving,"Transactions");
			
			Element chequing = xml.createNewParentElement(element,"Chequing");
			xml.createChildElement(chequing, "Number", String.valueOf(Math.random()));
			xml.createChildElement(chequing, "Balance", "0");
			xml.createNewParentElement(chequing,"Transactions");
			
			Element credit = xml.createNewParentElement(element,"Credit");
			xml.createChildElement(credit, "Number", String.valueOf(Math.random()));
			xml.createChildElement(credit, "Balance", "0");
			xml.createChildElement(credit, "CredScore", "0");
			xml.createChildElement(credit, "CredLimit", String.valueOf(client.credit.getCredLimit()));
			xml.createNewParentElement(credit,"Transactions");
			
			xml.updateXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Client getClientInfo(XmlUtils xml, String name)
	{
		Client cli = new Client();
		try{
			NodeList list = xml.getElementsByTagName("Client");		
			
			for(int i=0; i< list.getLength(); i++){
				Node node = list.item(i);			
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element elem = (Element) node;
					cli.nodeElement = elem;
					if(elem.getElementsByTagName("Name").item(0).getTextContent().equalsIgnoreCase(name)){
						cli.id = xml.getIntValue(elem,"Id");
						cli.name = elem.getElementsByTagName("Name").item(0).getTextContent();
						cli.lastname = elem.getElementsByTagName("LastName").item(0).getTextContent();
						cli.email = elem.getElementsByTagName("Email").item(0).getTextContent();
						cli.password = elem.getElementsByTagName("Password").item(0).getTextContent();
						
						if(elem.getElementsByTagName("Admin").item(0).getTextContent().equalsIgnoreCase("False")){					
							NodeList saving = elem.getElementsByTagName("Savings");
							Element savingElem = (Element) saving.item(0);
							
							cli.saving = new Saving();
							cli.saving.setAccountNum(xml.getIntValue(savingElem, "Number"));
							cli.saving.setAccountBal(xml.getDoubleValue(savingElem, "Balance"));
							
							NodeList chequing = elem.getElementsByTagName("Chequing");
							Element cheqElem = (Element) chequing.item(0);
							
							cli.chequing = new Chequing();
							cli.chequing.setAccountNum(xml.getIntValue(cheqElem, "Number"));
							cli.chequing.setAccountBal(xml.getDoubleValue(cheqElem, "Balance"));
						}
						break;
					}
				}
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cli;	
	}
}
