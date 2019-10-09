import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Account

{
	//declaring local variables
	protected int accountNum;
	protected double accountBal;
	protected String accountPlan;
	protected ArrayList<Transaction> transactions;
	
	//unparameterized constructor
	public Account()
	{
		transactions = new ArrayList<Transaction>();
		accountNum=0;
		accountBal=0;
		accountPlan=null;
	}
	
	//parameterized constructor
	public Account(int accountNum, double accountBal, String accountPlan)
	{
		transactions = new ArrayList<Transaction>();
		this.accountNum=accountNum;
		this.accountBal=accountBal;
		this.accountPlan=accountPlan;
	}
	
	//getters
	public int getAccountNum()
	{
		return accountNum;
	}
	
	public double getAccountBal()
	{
		return accountBal;
	}
	
	public String getAccountPlan()
	{
		return accountPlan;
	}
	
	//setters
	public void setAccountNum(int accountNum)
	{
		this.accountNum=accountNum;
	}
	
	public void setAccountBal(double accountBal)
	{
		this.accountBal=accountBal;
	}
	
	public void setAccountPlan(String accountPlan)
	{
		this.accountPlan=accountPlan;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public abstract boolean withdraw(Client cli, XmlUtils xml, double amount, boolean transfer);
	
	public abstract void deposit(Client cli, XmlUtils xml, double amount, boolean transfer);
	
	public abstract String payBill(Client cli, XmlUtils xml, int billId);
	
	public void getTransactions(XmlUtils xml, Element element){
		ArrayList<Transaction> transList = new ArrayList<Transaction>();
		NodeList list = element.getElementsByTagName("Transactions");		
		Transaction trans;
		Node node = list.item(0);
		
		if(node.getNodeType() == Node.ELEMENT_NODE && node.hasChildNodes()){
			Element elem = (Element) node;
			list = elem.getChildNodes();
			for(int i=0; i< list.getLength(); i++){	
				Node nChild = list.item(i);
				Element child = (Element) nChild;
				if(child.getNodeName().equalsIgnoreCase("Deposit")){
					trans = new Deposit();
					trans.setAmount(xml.getDoubleValue(child, "Amount"));
					trans.setTransDate((child.getElementsByTagName("Date").item(0).getTextContent()));
					transList.add(trans);					
				}else if(child.getNodeName().equalsIgnoreCase("Withdraw")){
					trans = new Withdraw();
					trans.setAmount(xml.getDoubleValue(child, "Amount"));
					trans.setTransDate((child.getElementsByTagName("Date").item(0).getTextContent()));
					transList.add(trans);
				}else if(child.getNodeName().equalsIgnoreCase("BillPayment")){
					trans = new BillPayment();
					trans.setAmount(xml.getDoubleValue(child, "Amount"));
					trans.setTransDate((child.getElementsByTagName("Date").item(0).getTextContent()));
					transList.add(trans);
				}else{
					trans = new MoneyTransfer();
					trans.setAmount(xml.getDoubleValue(child, "Amount"));
					((MoneyTransfer)trans).setRAcountNo(xml.getIntValue(child, "Account"));
					((MoneyTransfer)trans).setAccountName((child.getElementsByTagName("Name").item(0).getTextContent()));
					trans.setTransDate(child.getElementsByTagName("Date").item(0).getTextContent());
					transList.add(trans);
				}
			}
		}
		setTransactions(transList);
	}
	
	public String transferMoney(XmlUtils xml, Client client, int accountNo, String type, double amount){
		
		Client sentClient = new Client();	
		
		if(accountBal < amount)
			return "Account doesn't have enough money";
		
		Element elem = null;
		NodeList list = xml.getElementsByTagName("Chequing");						
		for(int i=0; i< list.getLength(); i++){
			Node node = list.item(i);			
			if(node.getNodeType() == Node.ELEMENT_NODE){
				elem = (Element) node;
				if(elem.getElementsByTagName("Number").item(0).getTextContent().equalsIgnoreCase(String.valueOf(accountNo))){
					sentClient.getClientInfo(xml, "Id", ((Element)elem.getParentNode()).getElementsByTagName("Id").item(0).getTextContent());
					sentClient.getChequing().deposit(sentClient, xml, amount, false);
					break;
				}
				elem = null;
			}
		}
		
		if(elem == null){
			list = xml.getElementsByTagName("Saving");						
			for(int i=0; i< list.getLength(); i++){
				Node node = list.item(i);			
				if(node.getNodeType() == Node.ELEMENT_NODE){
					elem = (Element) node;
					if(elem.getElementsByTagName("Number").item(0).getTextContent().equalsIgnoreCase(String.valueOf(accountNo))){
						sentClient.getClientInfo(xml, "Id", ((Element)elem.getParentNode()).getElementsByTagName("Id").item(0).getTextContent());
						sentClient.getSaving().deposit(sentClient, xml, amount, false);
						break;
					}
					elem = null;
				}
			}
		}
		
		if(elem == null){
			return "Account not found";
		}else{			
			withdraw(client,xml,amount,false);
			
			Transaction trans = new MoneyTransfer(amount * -1, accountNo,sentClient.getName());
			trans.addTransaction(client, xml, (Element)client.getNodeElement().getElementsByTagName(type).item(0));
			
			trans = new MoneyTransfer(amount, this.accountNum, client.getName());
			trans.addTransaction(sentClient, xml, elem);
			
			xml.updateXml();
			return "Money Transfered Successfully";
		}
	}

	
}
