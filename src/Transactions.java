import java.util.Date;

public class Transactions{
	
	
	private int ID;
	private Date TransDate;
	private double amount;
	private String SAccountNo;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public String getSAccountNo() {
		return SAccountNo;
	}
	public void setSAccountNo(String sAccountNo) {
		SAccountNo = sAccountNo;
	}
	@Override
	public String toString() {
		return "Transactions [ID=" + ID + ", TransDate=" + TransDate + ", amount=" + amount
				+ ", SAccountNo=" + SAccountNo + "]";
	}
	
	public Transactions(){
		
	}
	
	public Transactions(int iD, Date transDate, double amount, String sAccountNo) {
		super();
		ID = iD;
		TransDate = transDate;
		this.amount = amount;
		SAccountNo = sAccountNo;
	}
	
	
	
	
}
