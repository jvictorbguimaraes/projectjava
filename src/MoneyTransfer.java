import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class MoneyTransfer extends Transaction {

	private int RAcountNo;
	
	
	public MoneyTransfer() {
	}


	public MoneyTransfer(int id, double amount, int rAcountNo) {
		super(id, amount);
		RAcountNo = rAcountNo;
	}

	public int getRAcountNo() {
		return RAcountNo;
	}

	public void setRAcountNo(int rAcountNo) {
		RAcountNo = rAcountNo;
	}

	@Override
	public void addTransaction(Client cli, XmlUtils xml, Element element){
		try{
			NodeList trans = element.getElementsByTagName("Transactions");
			Element transElem = (Element) trans.item(0);
			Element transfer = xml.createNewParentElement(transElem,"MoneyTransfer");
			xml.createChildElement(transfer, "ID", String.valueOf(super.id));
			xml.createChildElement(transfer, "Date", super.date);
			xml.createChildElement(transfer, "Amount", String.valueOf(super.amount));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
