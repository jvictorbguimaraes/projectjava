import org.w3c.dom.Element;

public class Client 
{
	int id;
	String name;
	String lastname;
	String address;
	String username;
	String password;
	Saving saving;
	Chequing chequing;
	Credit credit;
		
	public Client()
	{
		
	}
		
	public Client(int id, String name, String lastname, String address, String username, String password) 
	{
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
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

	public Saving getSavings() 
	{
		return saving;
	}

	public void setSaving(Saving saving) 
	{
		this.saving = saving;
	}

	public Chequing getChecking() 
	{
		return chequing;
	}

	public void setChecking(Chequing chequing) 
	{
		this.chequing = chequing;
	}	
	
	public void createNewClient(XmlUtils xml, Client client)
	{
		Element element = xml.createNewElement();
		xml.createChildElement(element, "Name", client.name);
		xml.createChildElement(element, "LastName", client.lastname);
		xml.createChildElement(element, "Address", client.address);
		xml.createChildElement(element, "Username", client.username);
		xml.createChildElement(element, "Password", client.password);
	}
}
