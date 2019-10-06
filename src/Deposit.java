import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Deposit extends Transaction{

	public Deposit(int id, Date transDate, double amount) {
		super(id, transDate, amount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTransaction(Client cli, XmlUtils xml, Element element){
		try{
			NodeList trans = element.getElementsByTagName("Transactions");
			Element transElem = (Element) trans.item(0);
			Element deposit = xml.createNewParentElement(transElem,"Deposit");
			xml.createChildElement(deposit, "ID", String.valueOf(super.id));
			xml.createChildElement(deposit, "Date", super.TransDate.toString());
			xml.createChildElement(deposit, "Amount", String.valueOf(super.amount));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
