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
	public boolean withdraw(Client cli, XmlUtils xml, double amount, boolean addTransaction) {		
		try {
			if(amount <= super.accountBal){
				super.accountBal -= amount;				
			}else if(super.accountBal <= 0){
				super.accountBal -= amount * 1.1;
			}else{
				super.accountBal = (super.accountBal - amount) * 1.1;
			}
			//((super.accountBal - amount ) * -1.15 + minCharge) * -1
			NodeList chequing = cli.getNodeElement().getElementsByTagName("Chequing");
			Element cheqElem = (Element) chequing.item(0);
			xml.changeNodeValue(cheqElem, "Balance", String.valueOf(super.accountBal));
			if(addTransaction){
				Transaction trans = new Withdraw(amount);
				trans.addTransaction(cli, xml, cheqElem);
				xml.updateXml();
			}			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void deposit(Client cli, XmlUtils xml, double amount, boolean transfer) {
		try {
			this.accountBal += amount;
			NodeList chequing = cli.getNodeElement().getElementsByTagName("Chequing");
			Element cheqElem = (Element) chequing.item(0);
			xml.changeNodeValue(cheqElem, "Balance", String.valueOf(this.accountBal));	
			if(transfer){
				Transaction trans = new Deposit(amount);
				trans.addTransaction(cli, xml, cheqElem);
				xml.updateXml();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
