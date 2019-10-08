import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Deposit extends Transaction{

	public Deposit() {
		super();
	}
	
	public Deposit(double amount) {
		super(amount);
	}

	@Override
	public void addTransaction(Client cli, XmlUtils xml, Element element){
		try{
			NodeList trans = element.getElementsByTagName("Transactions");
			Element transElem = (Element) trans.item(0);
			Element deposit = xml.createNewParentElement(transElem,"Deposit");
			xml.createChildElement(deposit, "Date", super.date);
			xml.createChildElement(deposit, "Amount", String.valueOf(super.amount));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
