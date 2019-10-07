import java.util.ArrayList;

import org.w3c.dom.Element;

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

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public abstract boolean withdraw(Client cli, XmlUtils xml, double amount, boolean addTransaction);
	
	public abstract void deposit(Client cli, XmlUtils xml, double amount);
	
	public abstract ArrayList<Transaction> getTransactions(XmlUtils xml, Element element);
	
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
	
	public String transferMoney(XmlUtils xml, Client cli, int accountNo, double amount){
		if(this.accountBal < amount)
			return "Account doesn't have enough money";
		
		
		
		
		return "Success";
		
		
	}
}
