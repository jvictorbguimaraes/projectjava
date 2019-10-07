import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.Element;

public abstract class Transaction{
		
	private SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
	protected String date;
	protected double amount;
	
	public String getTransDate() {
		return date;
	}
	public void setTransDate(String date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
		
	public Transaction(){
		this.date = f.format(new Date());
	}
	
	public Transaction(double amount) {
		super();
		this.date = f.format(new Date());
		this.amount = amount;
		
	}
	
	public abstract void addTransaction(Client cli, XmlUtils xml, Element element);
	
	
}
