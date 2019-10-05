import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Chequing extends Account
{
	public Chequing()
	{
		super();
	}
	
	public Chequing(int accountNum, int accountBal, String accountName)
	{
		super(accountNum,accountBal,accountName);
	}

	@Override
	public boolean withdraw(Client cli, XmlUtils xml, double amount) {		
		try {
			if(amount <= super.accountBal){
				super.accountBal -= amount;				
			}else if(super.accountBal <= 0){
				super.accountBal -= amount * 1.1;
			}else{
				super.accountBal = (super.accountBal - amount) * 1.1;
			}
			
			NodeList chequing = cli.getNodeElement().getElementsByTagName("Chequing");
			Element cheqElem = (Element) chequing.item(0);
			xml.changeNodeValue(cheqElem, "Balance", String.valueOf(super.accountBal));
			xml.updateXml();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}	
}
