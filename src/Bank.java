import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bank extends JFrame implements ActionListener
{	
	
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	//private variables
	JPanel p1, p2, p3, p4;
	JButton b1, b2, b3, b4, b5, b6, b7;
	JLabel l1;
	
	//Customer Variables
	private JLabel name1, contact1, email1, password1, accType1;
	private JTextField name2, contact2, email2, password2, accType2, security;
	private String[] choices = { "Whats your maiden name?","What was your first job?", "Whats your pet's name?"};
	private final JComboBox<String> cb = new JComboBox<String>(choices);
	
	
	//constructor to set up GUI components
	public Bank()
	{
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		p3 = new JPanel(new GridLayout(7,2,10,10)); 
		p4 = new JPanel(new FlowLayout());
		
		b1 = new JButton("New Client");
		b2 = new JButton("Existing Client");
		b3 = new JButton("Next");
		b4 = new JButton("Withdrawals");
		b5 = new JButton("Deposits");
		b6 = new JButton("Pay Bills");
		b7 = new JButton("Money Transfer");
		
		
		l1 = new JLabel("Client Information Form");
		name1 = new JLabel("Full Name");
		contact1 = new JLabel("Contact");
		email1 = new JLabel("Email");
		password1 = new JLabel("Password");
		accType1 = new JLabel("Account Type");

		
		name2 = new JTextField();
		contact2 = new JTextField();
		email2 = new JTextField();
		password2 = new JTextField();
		accType2 = new JTextField();
		security = new JTextField();			
		
		b1.setFocusPainted(false);
		b2.setFocusPainted(false);
		b3.setFocusPainted(false);
		b4.setFocusPainted(false);
		b5.setFocusPainted(false);
		b6.setFocusPainted(false);
		b7.setFocusPainted(false);
		
		p1.add(b1);
		p1.add(b2);
		
		p2.add(l1);
		
		p3.add(name1);
		p3.add(name2);
		p3.add(contact1);
		p3.add(contact2);
		p3.add(email1);
		p3.add(email2);
		p3.add(password1);
		p3.add(password2);
		p3.add(accType1);
		p3.add(accType2);
		p3.add(cb);
		p3.add(security);
		p3.add(b3);
		
		p4.add(b4);
		p4.add(b5);
		p4.add(b6);
		p4.add(b7);
		
		
		setContentPane(p1);	     

	    b1.addActionListener(new ActionListener() 
	    {
	      @Override
	      public void actionPerformed(ActionEvent evt) 
	      {
	        getContentPane().removeAll();
	        setLayout(new BorderLayout());
	        add(p2,BorderLayout.NORTH);
	        add(p3,BorderLayout.CENTER);
	        revalidate();
	        repaint();
	      }
	    });
	      
	      b2.addActionListener(new ActionListener() 
	      {
	         @Override
	         public void actionPerformed(ActionEvent evt) 
	         {
	        	 getContentPane().removeAll();
	        	 setLayout(new BorderLayout());
	        	 add(p4,BorderLayout.CENTER);
	        	 revalidate();
	        	 repaint();
	        	 
	         }
	      });
	      
	      b3.addActionListener(new ActionListener() 
	      {
	         @Override
	         public void actionPerformed(ActionEvent evt) 
	         {
	        	 
	        	 //create account on button press here
	         }
	      });
	      
	      b4.addActionListener(new ActionListener() 
	      {
	         @Override
	         public void actionPerformed(ActionEvent evt) 
	         {
	        	 
	        	 //create account on button press here
	         }
	      });
	 
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
	      setTitle("Banking System"); // "super" Frame sets title
	      setSize(350, 350);  // "super" Frame sets initial size
	      setVisible(true);   // "super" Frame shows
	   
		
	}

	public static void main(String[] args) throws Exception 
	{
		
		XmlUtils xml = new XmlUtils();		
		
		Client cli = new Client(1,"Joao Victor","Guimaraes","j@gmail.com","Somewhere","joaovictor","123456");
		cli.saving = new Saving(1,0,"Test");
		cli.chequing = new Chequing(0,0,"Test");
		
		cli.createNewClient(xml, cli);
		
		//System.out.print(cli.getClientInfo(xml, "Joao Victor"));
		
//		NodeList list = xml.getElementsByTagName("Client");
//		
//		for(int i=0; i< list.getLength(); i++){
//			Node node = list.item(i);
//			
//			if(node.getNodeType() == Node.ELEMENT_NODE){
//				Element elem = (Element) node;
//				//xml.changeNodeValue(elem, "Name", "Joao");
//				System.out.println(elem.getElementsByTagName("Name").item(0).getTextContent());
//			}
//		}
		
		xml.updateXml();
		
		/****GUI Code: Esha 04/10/2019****/
		 SwingUtilities.invokeLater(new Runnable() 
		 {
	         @Override
	         public void run() 
	         {
	        	 new Bank();
	         }
	      });
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
