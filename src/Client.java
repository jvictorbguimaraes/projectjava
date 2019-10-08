import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Client 
{
	private int id;
	private String name;
	private String email;
	private String address;
	private String password;
	private String securityQ;
	private String securityA;
	private String phone;
	private boolean admin;
	private Account saving;
	private Account chequing;
	private Account credit;
	private Element nodeElement;

	public Client()
	{
		
	}
		
	public Client(int id, String name,  String email, String phone, String address, String password, String securityQ, String securityA, boolean admin) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.securityQ = securityQ;
		this.securityA = securityA;
		this.admin = admin;
	}

	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public Account getCredit() {
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
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getSecurityQ() 
	{
		return securityQ;
	}
	public void setSecurityQ(String securityQ) 
	{
		this.securityQ = securityQ;
	}
	public String getSecurityA() 
	{
		return securityA;
	}
	public void setSecurityA(String securityA) 
	{
		this.securityA = securityA;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
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
			xml.changeNodeValue(client.nodeElement, "Email", client.email);
			xml.changeNodeValue(client.nodeElement, "Address", client.address);
			xml.changeNodeValue(client.nodeElement, "Password", client.password);		
			xml.updateXml();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Client> searchClients(XmlUtils xml, String name, String address, String email){
	
		ArrayList<Client> clients = new ArrayList<Client>();
		boolean found;
		try{
			NodeList list = xml.getElementsByTagName("Client");						
			for(int i=0; i< list.getLength(); i++){
				found = false;
				Node node = list.item(i);			
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element elem = (Element) node;
					if(email != null){
						if(elem.getElementsByTagName("Email").item(0).getTextContent().equalsIgnoreCase(email))
							found = true;
						else
							found = false;
					}
					if(name != null){
						if(elem.getElementsByTagName("Name").item(0).getTextContent().equalsIgnoreCase(name))
							found = true;
						else
							found = false;
					}
					if(address != null){
						if(elem.getElementsByTagName("Address").item(0).getTextContent().equalsIgnoreCase(address))
							found = true;
						else
							found = false;
					}
					if(found)
						clients.add(getClientInfo(xml, "Email", elem.getElementsByTagName("Email").item(0).getTextContent()));
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return clients;
	}
	
	public ArrayList<Client> getClients(XmlUtils xml)
	{
		ArrayList<Client> clients = new ArrayList<Client>();
		try{
			NodeList list = xml.getElementsByTagName("Client");		
			
			for(int i=0; i< list.getLength(); i++){
				Node node = list.item(i);			
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element elem = (Element) node;
					clients.add(getClientInfo(xml, "Email", elem.getElementsByTagName("Email").item(0).getTextContent()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clients;
	}

	public void createNewClient(XmlUtils xml, Client client)
	{
		try {
			Element element = xml.createNewElement("Bank","Client");
			xml.createChildElement(element, "Id", xml.getNextId("clientId"));
			xml.createChildElement(element, "Name", client.name);
			xml.createChildElement(element, "Email", client.email);
			xml.createChildElement(element, "Address", client.address);
			xml.createChildElement(element, "Password", client.password);
			xml.createChildElement(element, "Phone", client.phone);
			xml.createChildElement(element, "SecurityQuestion", client.securityQ);
			xml.createChildElement(element, "SecurityAnswer", client.securityA);
			xml.createChildElement(element, "Admin", "False");
			
			Element saving = xml.createNewParentElement(element,"Saving");
			xml.createChildElement(saving, "Number", xml.getNextId("accountId"));
			xml.createChildElement(saving, "Balance", "0");
			xml.createNewParentElement(saving,"Transactions");
			
			Element chequing = xml.createNewParentElement(element,"Chequing");
			xml.createChildElement(chequing, "Number", xml.getNextId("accountId"));
			xml.createChildElement(chequing, "Balance", "0");
			xml.createNewParentElement(chequing,"Transactions");
			
			Element credit = xml.createNewParentElement(element,"Credit");
			xml.createChildElement(credit, "Number", xml.getNextId("accountId"));
			xml.createChildElement(credit, "Balance", "0");
			xml.createChildElement(credit, "CredScore", "0");
			xml.createChildElement(credit, "CredLimit", "500");
			//xml.createChildElement(credit, "CredLimit", String.valueOf(client.credit.getCredLimit()));
			xml.createNewParentElement(credit,"Transactions");
			
			xml.updateXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Client getClientInfo(XmlUtils xml, String name, String value)
	{
		Client cli = new Client();
		try{
			NodeList list = xml.getElementsByTagName("Client");		
			
			for(int i=0; i< list.getLength(); i++){
				Node node = list.item(i);			
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element elem = (Element) node;
					cli.nodeElement = elem;
					if(elem.getElementsByTagName(name).item(0).getTextContent().equalsIgnoreCase(value)){
						cli.id = xml.getIntValue(elem,"Id");
						cli.name = elem.getElementsByTagName("Name").item(0).getTextContent();
						cli.email = elem.getElementsByTagName("Email").item(0).getTextContent();
						cli.password = elem.getElementsByTagName("Password").item(0).getTextContent();
						cli.phone = elem.getElementsByTagName("Phone").item(0).getTextContent();						
						cli.admin = Boolean.parseBoolean(elem.getElementsByTagName("Admin").item(0).getTextContent());
						
						if(elem.getElementsByTagName("Admin").item(0).getTextContent().equalsIgnoreCase("False")){
							cli.securityQ = elem.getElementsByTagName("SecurityQuestion").item(0).getTextContent();
							cli.securityA = elem.getElementsByTagName("SecurityAnswer").item(0).getTextContent();
							
							NodeList saving = elem.getElementsByTagName("Saving");
							Element savingElem = (Element) saving.item(0);
							
							cli.saving = new Saving();
							cli.saving.setAccountNum(xml.getIntValue(savingElem, "Number"));
							cli.saving.setAccountBal(xml.getDoubleValue(savingElem, "Balance"));
							cli.saving.getTransactions(xml, savingElem);
							
							NodeList chequing = elem.getElementsByTagName("Chequing");
							Element cheqElem = (Element) chequing.item(0);
							
							cli.chequing = new Chequing();
							cli.chequing.setAccountNum(xml.getIntValue(cheqElem, "Number"));
							cli.chequing.setAccountBal(xml.getDoubleValue(cheqElem, "Balance"));
							cli.chequing.getTransactions(xml, cheqElem);
							
							NodeList credit = elem.getElementsByTagName("Credit");
							Element creditElem = (Element) credit.item(0);
							
							cli.credit = new Credit();
							cli.credit.setAccountNum(xml.getIntValue(creditElem, "Number"));
							cli.credit.setAccountBal(xml.getDoubleValue(creditElem, "Balance"));
							((Credit)cli.credit).setCredLimit(xml.getDoubleValue(creditElem, "CredLimit"));
							((Credit)cli.credit).setCredScore(xml.getDoubleValue(creditElem, "CredScore"));
							cli.credit.getTransactions(xml, creditElem);
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
	
	public void removeClient(XmlUtils xml){
		xml.removeElement(this.getNodeElement());
		xml.updateXml();
	}
}
