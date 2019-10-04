
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
}
