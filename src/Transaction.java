import java.util.Date;

import org.w3c.dom.Element;

public abstract class Transaction{
		
	protected int id;
	protected Date TransDate;
	protected double amount;
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public Date getTransDate() {
		return TransDate;
	}
	public void setTransDate(Date transDate) {
		TransDate = transDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Transactions [ID=" + id + ", TransDate=" + TransDate + ", amount=" + amount
				+ "]";
	}
	
	public Transaction(){
		
	}
	
	public Transaction(int id, Date transDate, double amount) {
		super();
		this.id = id;
		TransDate = transDate;
		this.amount = amount;
	}
	
	public abstract void addTransaction(Client cli, XmlUtils xml, Element element);
	
	
}
