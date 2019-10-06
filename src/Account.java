import java.util.ArrayList;

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
	
	public abstract boolean withdraw(Client cli, XmlUtils xml, double amount);
	
	public abstract void deposit(Client cli, XmlUtils xml, double amount);
	
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
	
	public boolean transferMoney(XmlUtils xml, Client cli, int accountNo, double amount){
		if(this.accountBal < amount)
			return false;
		
		
		
		
		return true;
		
		
	}
}
