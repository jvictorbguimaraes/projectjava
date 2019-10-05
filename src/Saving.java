import org.w3c.dom.Element;
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

	public boolean withdraw(Client cli, XmlUtils xml, double amount) {		
		try {
			if(amount <= super.accountBal){
				super.accountBal -= amount + minCharge;				
			}else if(super.accountBal <= 0){
				super.accountBal -= amount * 1.15 + minCharge;
			}else{
				super.accountBal = (super.accountBal - amount) * 1.15 + minCharge;
			}
			
			NodeList saving = cli.getNodeElement().getElementsByTagName("Savings");
			Element savElem = (Element) saving.item(0);
			xml.changeNodeValue(savElem, "Balance", String.valueOf(super.accountBal));
			xml.updateXml();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
