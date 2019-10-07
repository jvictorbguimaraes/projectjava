import java.util.ArrayList;
import java.util.Date;

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
	
	public void getTransactions(XmlUtils xml, Element element){
		ArrayList<Transaction> transList = new ArrayList<Transaction>();
		NodeList list = element.getElementsByTagName("Transactions");		
		Transaction trans;
		Node node = list.item(0);
		
		if(node.getNodeType() == Node.ELEMENT_NODE && node.hasChildNodes()){
			Element elem = (Element) node;
			list = elem.getChildNodes();
			for(int i=0; i< list.getLength(); i++){	
				Node nChild = list.item(0);
				Element child = (Element) nChild;
				if(nChild.getNodeName().equalsIgnoreCase("Deposit")){
					trans = new Deposit();
					trans.setID(Integer.parseInt(child.getElementsByTagName("ID").item(0).getTextContent()));
					trans.setAmount(Double.parseDouble(child.getElementsByTagName("Amount").item(0).getTextContent()));
					transList.add(trans);					
				}else if(nChild.getNodeName().equalsIgnoreCase("Withdraw")){
					trans = new Withdraw();
					trans.setID(Integer.parseInt(child.getElementsByTagName("ID").item(0).getTextContent()));
					trans.setAmount(Double.parseDouble(child.getElementsByTagName("Amount").item(0).getTextContent()));
					transList.add(trans);
				}else if(nChild.getNodeName().equalsIgnoreCase("BillPayment")){
					trans = new BillPayment();
					trans.setID(Integer.parseInt(child.getElementsByTagName("ID").item(0).getTextContent()));
					trans.setAmount(Double.parseDouble(child.getElementsByTagName("Amount").item(0).getTextContent()));
					transList.add(trans);
				}else{
					trans = new MoneyTransfer();
					trans.setID(Integer.parseInt(child.getElementsByTagName("ID").item(0).getTextContent()));
					trans.setAmount(Double.parseDouble(child.getElementsByTagName("Amount").item(0).getTextContent()));
					transList.add(trans);
				}
			}
		}
		setTransactions(transList);
	}
	
	public String payBill(Client cli, XmlUtils xml, int billId) {
		try {			
			BillPayment bill = new BillPayment();
			bill = bill.getBill(xml, billId);
			
			if(bill.isPaid()){
				return "Bill was already paid";
			}else if(withdraw(cli, xml, bill.getAmount(), false)){
				bill.setBillPayment(xml);
				return "Success";
			}else{
				return "Account doesn't have enough money";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}	
	
	public String transferMoney(XmlUtils xml, Client client, int accountNo, String type, double amount){
		
		Client sentClient = new Client();	
		
		if(accountBal < amount)
			return "Account doesn't have enough money";
		
		NodeList list = xml.getElementsByTagName(type);						
		for(int i=0; i< list.getLength(); i++){
			Node node = list.item(i);			
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element elem = (Element) node;
				if(elem.getElementsByTagName("Number").item(0).getTextContent().equalsIgnoreCase(String.valueOf(accountNo))){
					sentClient = sentClient.getClientInfo(xml, "Id", ((Element)elem.getParentNode()).getElementsByTagName("Id").item(0).getTextContent());
					
					if(type == "Chequing"){
						sentClient.getChequing().deposit(sentClient, xml, amount, false);
					}else{
						sentClient.getSaving().deposit(sentClient, xml, amount, false);
					}
					
					withdraw(client,xml,amount,false);
					
					Transaction trans = new MoneyTransfer((int)Math.random(), amount * -1, accountNo);
					trans.addTransaction(client, xml, client.getNodeElement());
					
					trans = new MoneyTransfer((int)Math.random(), amount, this.accountNum);
					trans.addTransaction(sentClient, xml, elem);
					
					xml.updateXml();
					return "Success";
				}
			}
		}
		
		return "Account not found";
	}
}
