import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Withdraw extends Transaction {

	public Withdraw() {
		super();
	}
	
	public Withdraw(double amount) {
		super(amount);
	}

	@Override
	public void addTransaction(Client cli, XmlUtils xml, Element element){
		try{
			NodeList trans = element.getElementsByTagName("Transactions");
			Element transElem = (Element) trans.item(0);
			Element withdraw = xml.createNewParentElement(transElem,"Withdraw");
			xml.createChildElement(withdraw, "Date", super.date);
			xml.createChildElement(withdraw, "Amount", String.valueOf(super.amount));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
