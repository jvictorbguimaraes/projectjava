import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class MoneyTransfer extends Transaction {

	private String RAcountNo;
	private String SecurityQ;
	
	
	public MoneyTransfer() {
	}


	public MoneyTransfer(int iD, Date transDate, double amount, String rAcountNo,
			String securityQ) {
		super(iD, transDate, amount);
		RAcountNo = rAcountNo;
		SecurityQ = securityQ;
	}


	@Override
	public String toString() {
		return "MoneyTransfer [RAcountNo=" + RAcountNo + ", SecurityQ=" + SecurityQ + "]";
	}


	public String getRAcountNo() {
		return RAcountNo;
	}

	public void setRAcountNo(String rAcountNo) {
		RAcountNo = rAcountNo;
	}

	public String getSecurityQ() {
		return SecurityQ;
	}

	public void setSecurityQ(String securityQ) {
		SecurityQ = securityQ;
	}


	@Override
	public void addTransaction(Client cli, XmlUtils xml, Element element){
		try{
			NodeList trans = element.getElementsByTagName("Transactions");
			Element transElem = (Element) trans.item(0);
			Element transfer = xml.createNewParentElement(transElem,"MoneyTransfer");
			xml.createChildElement(transfer, "ID", String.valueOf(super.id));
			xml.createChildElement(transfer, "Date", super.TransDate.toString());
			xml.createChildElement(transfer, "Amount", String.valueOf(super.amount));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
