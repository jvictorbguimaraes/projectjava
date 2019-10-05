import java.util.ArrayList;

public abstract class Account

{
	//declaring local variables
	private int accountNum;
	private double accountBal;
	private String accountPlan;
	private ArrayList<Transactions> transactions;
	
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
	
	public void withdraw(double amount)
	{
		this.accountBal -= amount;
		//xml functions to save
	}
	
	public void deposit(double amount)
	{
		this.accountBal += amount;
		//xml functions to save
		
	}
	
	public void billpayment(double amount)
	{
		//
		this.accountBal -= amount;
		//xml functions to save
		
	}
	
	public void moneytransfer(double amount, String Raccountno)
	{
		this.accountBal -= amount;
		//account balance of receiver += amount
	}
}
