import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Saving extends Account 
{
	private final double minCharge=5;
	
	public Saving()
	{
		super();
	}
	
	public Saving(int accountNum, int accountBal, String accountName)
	{
		super(accountNum,accountBal,accountName);
	}
	
	//getters
	public double getMinCharge()
	{
		return minCharge;
	}

	public boolean withdraw(Client cli, XmlUtils xml, double amount, boolean addTransaction) {		
		try {
			if(amount <= super.accountBal){
				super.accountBal -= amount + minCharge;				
			}else if(super.accountBal <= 0){
				super.accountBal -= amount * 1.15 + minCharge;
			}else{
				super.accountBal = (super.accountBal - amount) * 1.15 + minCharge;
			}
			
			NodeList saving = cli.getNodeElement().getElementsByTagName("Saving");
			Element savElem = (Element) saving.item(0);
			xml.changeNodeValue(savElem, "Balance", String.valueOf(super.accountBal));
			
			if(addTransaction){
				Transaction trans = new Withdraw((int)Math.random(), new Date(), amount);
				trans.addTransaction(cli, xml, savElem);		
				xml.updateXml();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void deposit(Client cli, XmlUtils xml, double amount, boolean transfer) {
		try {
			this.accountBal += amount;
			NodeList saving = cli.getNodeElement().getElementsByTagName("Saving");
			Element savingElem = (Element) saving.item(0);
			xml.changeNodeValue(savingElem, "Balance", String.valueOf(this.accountBal));
			if(transfer){
				Transaction trans = new Deposit((int)Math.random(), new Date(), amount);
				trans.addTransaction(cli, xml, savingElem);
				xml.updateXml();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public ArrayList<Transaction> getTransactions(XmlUtils xml, Element element) {
		ArrayList<Transaction> transList = new ArrayList<Transaction>();
		NodeList list = element.getElementsByTagName("Transactions");		
		Transaction trans;
		for(int i=0; i< list.getLength(); i++){			
			Node node = list.item(i);			
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element elem = (Element) node;
				if(elem.getAttribute("name").equalsIgnoreCase("Deposit")){
					trans = new Deposit();
					trans.setID(Integer.parseInt(elem.getElementsByTagName("Id").item(0).getTextContent()));
					trans.setAmount(Double.parseDouble(elem.getElementsByTagName("Amount").item(0).getTextContent()));
					//trans.setTransDate(Integer.parseInt(elem.getElementsByTagName("Date").item(0).getTextContent()));
					transList.add(trans);					
				}else if(elem.getAttribute("name").equalsIgnoreCase("Withdraw")){
					trans = new Withdraw();
					trans.setID(Integer.parseInt(elem.getElementsByTagName("Id").item(0).getTextContent()));
					trans.setAmount(Double.parseDouble(elem.getElementsByTagName("Amount").item(0).getTextContent()));
					//trans.setTransDate(Integer.parseInt(elem.getElementsByTagName("Date").item(0).getTextContent()));
					transList.add(trans);
				}else if(elem.getAttribute("name").equalsIgnoreCase("BillPayment")){
					trans = new BillPayment();
					trans.setID(Integer.parseInt(elem.getElementsByTagName("Id").item(0).getTextContent()));
					trans.setAmount(Double.parseDouble(elem.getElementsByTagName("Amount").item(0).getTextContent()));
					//trans.setTransDate(Integer.parseInt(elem.getElementsByTagName("Date").item(0).getTextContent()));
					transList.add(trans);
				}else{
					trans = new MoneyTransfer();
					trans.setID(Integer.parseInt(elem.getElementsByTagName("Id").item(0).getTextContent()));
					trans.setAmount(Double.parseDouble(elem.getElementsByTagName("Amount").item(0).getTextContent()));
					//trans.setTransDate(Integer.parseInt(elem.getElementsByTagName("Date").item(0).getTextContent()));
					transList.add(trans);
				}
			}
		}
		return transList;
	}
}
