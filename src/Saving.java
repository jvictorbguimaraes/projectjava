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

	public boolean withdraw(Client cli, XmlUtils xml, double amount, boolean addTransaction) {		
		try {
			if(amount + minCharge > super.accountBal)
				return false;				
			
			super.accountBal -= amount + minCharge;

			NodeList saving = cli.getNodeElement().getElementsByTagName("Saving");
			Element savElem = (Element) saving.item(0);
			xml.changeNodeValue(savElem, "Balance", String.valueOf(super.accountBal));

			if(addTransaction){
				Transaction trans = new Withdraw(amount);
				trans.addTransaction(cli, xml, savElem);		
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
			NodeList saving = cli.getNodeElement().getElementsByTagName("Saving");
			Element savingElem = (Element) saving.item(0);
			xml.changeNodeValue(savingElem, "Balance", String.valueOf(this.accountBal));
			if(transfer){
				Transaction trans = new Deposit(amount);
				trans.addTransaction(cli, xml, savingElem);
				xml.updateXml();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public String payBill(Client cli, XmlUtils xml, int billId) {
		try {			
			BillPayment bill = new BillPayment();
			bill.getBill(xml, billId);
			
			if(bill.isPaid()){
				return "Bill was already paid";
			}else if(withdraw(cli, xml, bill.getAmount(), false)){
				bill.setBillPayment(xml);
				
				Element element =  (Element) cli.getNodeElement().getElementsByTagName("Saving").item(0);
								
				bill.addTransaction(cli, xml, element);
				xml.updateXml();
				return "Bill Paid Successfully";
			}else{
				return "Account doesn't have enough money";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}	
}
