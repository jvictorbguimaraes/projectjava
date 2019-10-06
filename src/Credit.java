import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Credit extends Account
{
	private double credLimit;
	private double credScore;
	private final double minCharge=15;
	
	public Credit()
	{
		super();
	}
		
	public Credit(int accountNum, int accountBal, String accountName, double credLimit, double credScore)
	{
		super(accountNum,accountBal,accountName);
		this.credLimit = credLimit;
		this.credScore = credScore;
	}	

	public double getCredLimit() {
		return credLimit;
	}

	public void setCredLimit(double credLimit) {
		this.credLimit = credLimit;
	}

	public double getCredScore() {
		return credScore;
	}

	public void setCredScore(double credScore) {
		this.credScore = credScore;
	}

	@Override
	public boolean withdraw(Client cli, XmlUtils xml, double amount) {		
		try {
			if(amount <= this.credLimit - super.accountBal){
				super.accountBal += amount + minCharge;	
				NodeList saving = cli.getNodeElement().getElementsByTagName("Savings");
				Element savElem = (Element) saving.item(0);
				xml.changeNodeValue(savElem, "Balance", String.valueOf(super.accountBal));
				xml.updateXml();
				return true;
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void deposit(Client cli, XmlUtils xml, double amount) {
				
	}
}
