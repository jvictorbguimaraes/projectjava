import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BillPayment extends Transaction {

	private String BillType;
	private int BillNo;
	private boolean Paid;
	
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
	
	public boolean isPaid() {
		return Paid;
	}
	public void setPaid(boolean paid) {
		Paid = paid;
	}
	@Override
	public String toString() {
		return "BillPayment [BillType=" + BillType + ", BillNo=" + BillNo + "]";
	}
	
	public BillPayment(){
		
	}
	
	public BillPayment(int iD, Date transDate, double amount, String billType,
			int billNo) {
		super(iD, transDate, amount);
		BillType = billType;
		BillNo = billNo;
	}
	
	public BillPayment getBill(XmlUtils xml, int id){
		BillPayment bill = new BillPayment();
		try{
			NodeList list = xml.getElementsByTagName("Bill");		
				
			for(int i=0; i< list.getLength(); i++){
				Node node = list.item(i);			
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element elem = (Element) node;
					if(elem.getElementsByTagName("ID").item(0).getTextContent().equalsIgnoreCase(String.valueOf(id))){
						bill.BillNo = id;
						bill.BillType = elem.getElementsByTagName("Type").item(0).getTextContent();
						bill.setAmount(Double.parseDouble(elem.getElementsByTagName("Value").item(0).getTextContent()));
						bill.setPaid(Boolean.parseBoolean(elem.getElementsByTagName("Paid").item(0).getTextContent()));
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bill;
	}
	
	public void setBillPayment(XmlUtils xml){
		try{
			NodeList list = xml.getElementsByTagName("Bill");		
				
			for(int i=0; i< list.getLength(); i++){
				Node node = list.item(i);			
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element elem = (Element) node;
					if(elem.getElementsByTagName("ID").item(0).getTextContent().equalsIgnoreCase(String.valueOf(this.BillNo))){
						xml.changeNodeValue(elem, "Paid", "True");
						xml.updateXml();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void addTransaction(Client cli, XmlUtils xml, Element element){
		try{
			NodeList trans = element.getElementsByTagName("Transactions");
			Element transElem = (Element) trans.item(0);
			Element bill = xml.createNewParentElement(transElem,"BillPayment");
			xml.createChildElement(bill, "ID", String.valueOf(super.id));
			xml.createChildElement(bill, "Date", super.TransDate.toString());
			xml.createChildElement(bill, "Amount", String.valueOf(super.amount));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
