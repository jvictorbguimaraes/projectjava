import java.util.Date;

public class BillPayment extends Transactions {

	

	private String BillType;
	private int BillNo;
	
	@Override
	public String toString() {
		return "BillPayment [BillType=" + BillType + ", BillNo=" + BillNo + "]";
	}
	public String getBillType() {
		return BillType;
	}
	public void setBillType(String billType) {
		BillType = billType;
	}
	public int getBillNo() {
		return BillNo;
	}
	public void setBillNo(int billNo) {
		BillNo = billNo;
	}
	
	public BillPayment(int iD, Date transDate, double amount, String sAccountNo, String billType,
			int billNo) {
		super(iD, transDate, amount, sAccountNo);
		BillType = billType;
		BillNo = billNo;
	}
	
	
	
}
