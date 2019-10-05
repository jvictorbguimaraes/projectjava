import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class Account

{
	//declaring local variables
	protected int accountNum;
	protected double accountBal;
	protected String accountPlan;
	protected ArrayList<Transactions> transactions;
	
	//unparameterized constructor
	public Account()
	{
		accountNum=0;
		accountBal=0;
		accountPlan=null;
	}
	
	//parameterized constructor
	public Account(int accountNum, double accountBal, String accountPlan)
	{
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

	public ArrayList<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transactions> transactions) {
		this.transactions = transactions;
	}
	
	public abstract boolean withdraw(Client cli, XmlUtils xml, double amount);
	
	public void deposit(Client cli, XmlUtils xml, double amount) {
		try {
			this.accountBal += amount;
			NodeList chequing = cli.getNodeElement().getElementsByTagName("Chequing");
			Element cheqElem = (Element) chequing.item(0);
			xml.changeNodeValue(cheqElem, "Balance", String.valueOf(this.accountBal));		
			xml.updateXml();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public boolean payBill(Client cli, XmlUtils xml, int billId) {
		try {			
			BillPayment bill = new BillPayment();
			bill = bill.getBill(xml, billId);
			
			if(!bill.isPaid() && withdraw(cli, xml, bill.getAmount())){	
				bill.setBillPayment(xml);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}	
}
