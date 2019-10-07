import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.Element;

public abstract class Transaction{
		
	private SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
	protected int id;
	protected String date;
	protected double amount;
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
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
	
	@Override
	public String toString() {
		return "Transactions [ID=" + id + ", date=" + date + ", amount=" + amount
				+ "]";
	}
	
	public Transaction(){
		this.date = f.format(new Date());
	}
	
	public Transaction(int id, double amount) {
		super();
		this.id = id;
		this.date = f.format(new Date());
		this.amount = amount;
		
	}
	
	public abstract void addTransaction(Client cli, XmlUtils xml, Element element);
	
	
}
