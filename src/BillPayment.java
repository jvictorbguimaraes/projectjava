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
		if(Paid)
			return "BillType: " + BillType + " BillNo:" + BillNo + " Payment: Paid";
		else
			return "BillType: " + BillType + " BillNo:" + BillNo + " Payment: Not Paid";
	}
	
	public BillPayment(){
		
	}
	
	public BillPayment(double amount, String billType,
			int billNo) {
		super(amount);
		BillType = billType;
		BillNo = billNo;
	}
	
	public boolean getBill(XmlUtils xml, int id){
		try{
			NodeList list = xml.getElementsByTagName("Bill");		
				
			for(int i=0; i< list.getLength(); i++){
				Node node = list.item(i);			
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element elem = (Element) node;
					if(elem.getElementsByTagName("ID").item(0).getTextContent().equalsIgnoreCase(String.valueOf(id))){
						this.BillNo = id;
						this.BillType = elem.getElementsByTagName("Type").item(0).getTextContent();
						this.setAmount(Double.parseDouble(elem.getElementsByTagName("Value").item(0).getTextContent()));
						this.setPaid(Boolean.parseBoolean(elem.getElementsByTagName("Paid").item(0).getTextContent()));
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
			xml.createChildElement(bill, "Date", super.date);
			xml.createChildElement(bill, "Amount", String.valueOf(super.amount));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
