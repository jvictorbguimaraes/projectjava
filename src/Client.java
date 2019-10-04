import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Client 
{
	int id;
	String name;
	String lastname;
	String email;
	String address;
	String username;
	String password;
	Account saving;
	Account chequing;
		
	public Client()
	{
		
	}
		
	public Client(int id, String name, String lastname, String email, String address, String username, String password) 
	{
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.username = username;
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

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
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

	public void createNewClient(XmlUtils xml, Client client)
	{
		Element element = xml.createNewElement("Bank","Client");
		xml.createChildElement(element, "Id", String.valueOf(client.id));
		xml.createChildElement(element, "Name", client.name);
		xml.createChildElement(element, "LastName", client.lastname);
		xml.createChildElement(element, "Email", client.email);
		xml.createChildElement(element, "Address", client.address);
		xml.createChildElement(element, "Username", client.username);
		xml.createChildElement(element, "Password", client.password);
		
		Element saving = xml.createNewParentElement(element,"Saving");
		xml.createChildElement(saving, "Number", String.valueOf(client.saving.getAccountNum()));
		xml.createChildElement(saving, "Balance", String.valueOf(client.saving.getAccountBal()));
		
		Element chequing = xml.createNewParentElement(element,"Chequing");
		xml.createChildElement(chequing, "Number", String.valueOf(client.chequing.getAccountNum()));
		xml.createChildElement(chequing, "Balance", String.valueOf(client.chequing.getAccountBal()));
	}
	
	public Client getClientInfo(XmlUtils xml, String name)
	{
		NodeList list = xml.getElementsByTagName("Client");
		Client cli = new Client();
		
		for(int i=0; i< list.getLength(); i++){
			Node node = list.item(i);			
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element elem = (Element) node;
				if(elem.getElementsByTagName("Name").item(0).getTextContent().equalsIgnoreCase(name)){
					cli.id = xml.getIntValue(elem,"Id");
					cli.name = elem.getElementsByTagName("Name").item(0).getTextContent();
					cli.lastname = elem.getElementsByTagName("LastName").item(0).getTextContent();
					cli.email = elem.getElementsByTagName("Email").item(0).getTextContent();
					cli.password = elem.getElementsByTagName("Password").item(0).getTextContent();
					
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
					break;
				}
			}
		}
		
		return cli;
	}
}
