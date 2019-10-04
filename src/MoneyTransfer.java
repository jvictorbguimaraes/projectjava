import java.util.Date;

public class MoneyTransfer extends Transactions {

	private String RAcountNo;
	private String SecurityQ;
	

	public MoneyTransfer(int iD, Date transDate, double amount, String sAccountNo, String rAcountNo,
			String securityQ) {
		super(iD, transDate, amount, sAccountNo);
		RAcountNo = rAcountNo;
		SecurityQ = securityQ;
	}


	@Override
	public String toString() {
		return "MoneyTransfer [RAcountNo=" + RAcountNo + ", SecurityQ=" + SecurityQ + "]";
	}


	public String getRAcountNo() {
		return RAcountNo;
	}

	public void setRAcountNo(String rAcountNo) {
		RAcountNo = rAcountNo;
	}

	public String getSecurityQ() {
		return SecurityQ;
	}

	public void setSecurityQ(String securityQ) {
		SecurityQ = securityQ;
	}

}
