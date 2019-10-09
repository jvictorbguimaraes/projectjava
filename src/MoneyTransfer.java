import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class MoneyTransfer extends Transaction {

	private int RAccountNo;
	private String accountName;
	
	public MoneyTransfer() {
	}


	public MoneyTransfer(double amount, int rAccountNo, String accountName) {
		super(amount);
		RAccountNo = rAccountNo;
		this.accountName = accountName;
	}
	
	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getRAcountNo() {
		return RAccountNo;
	}

	public void setRAcountNo(int rAcountNo) {
		RAccountNo = rAcountNo;
	}

	@Override
	public void addTransaction(Client cli, XmlUtils xml, Element element){
		try{
			NodeList trans = element.getElementsByTagName("Transactions");
			Element transElem = (Element) trans.item(0);
			Element transfer = xml.createNewParentElement(transElem,"MoneyTransfer");
			xml.createChildElement(transfer, "Account", String.valueOf(this.RAccountNo));
			xml.createChildElement(transfer, "Name", this.accountName);
			xml.createChildElement(transfer, "Date", super.date);
			xml.createChildElement(transfer, "Amount", String.valueOf(super.amount));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
