
public abstract class Account

{
	//declaring local variables
	private int accountNum;
	private int accountBal;
	private String accountName;
	
	//unparameterized constructor
	public Account()
	{
		accountNum=0;
		accountBal=0;
		accountName="Null";
	}
	
	//parameterized constructor
	public Account(int accountNum, int accountBal, String accountName)
	{
		this.accountNum=accountNum;
		this.accountBal=accountNum;
		this.accountName=accountName;
	}
	
	//getters
	public int getAccountNum()
	{
		return accountNum;
	}
	
	public int getAccountBal()
	{
		return accountBal;
	}
	
	public String getAccountName()
	{
		return accountName;
	}
	
	//setters
	public void setAccountNum(int accountNum)
	{
		this.accountNum=accountNum;
	}
	
	public void setAccountBal(int accountBal)
	{
		this.accountBal=accountBal;
	}
	
	public void setAccountName(String accountName)
	{
		this.accountName=accountName;
	}
	
}
