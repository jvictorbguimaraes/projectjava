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
	JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17;
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, search2, modify, delete, back1, back2, back3, back4, back5, back6, back7, withdraw, deposit, cl1, search, logout,pay;
	JLabel l1;
	
	//Customer Variables
	private JLabel name1, contact1, email1, address1, password1, accType1;
	private JTextField login, pass, name2, contact2, email2, address2, password2, accType2, security,name3,contact3, email3,address3,searchtf,amount1,amount2,amount3;
	private String[] questions = { "Whats your maiden name?","What was your first job?", "Whats your pet's name?"};
	private final JComboBox<String> cb1 = new JComboBox<String>(questions);
	private String[] accType11 = { "Chequing","Savings","Credit"};
	private final JComboBox<String> cb21 = new JComboBox<String>(accType11);
	private String[] accType22 = { "Chequing","Savings","Credit"};
	private final JComboBox<String> cb22 = new JComboBox<String>(accType22);
	private String[] accType23 = { "Chequing","Savings","Credit"};
	private final JComboBox<String> cb23 = new JComboBox<String>(accType23);
	private String[] billType = { "Electricity","Water","Mobile","Credit"};
	private final JComboBox<String> cb3 = new JComboBox<String>(billType);
	
	//constructor to set up GUI components
	public Bank()
	{

		p1 = new JPanel(new GridLayout(6,2,10,10));
		p2 = new JPanel(new GridLayout(6,2,10,10));
		p3 = new JPanel(new GridLayout(7,2,10,10)); 
		p4 = new JPanel(new GridLayout(6,1,10,10));
		p5 = new JPanel(new GridLayout(6,1,10,10));
		p6 = new JPanel(new GridLayout(6,1,10,10));
		p7 = new JPanel(new GridLayout(6,1,10,10));
		p8 = new JPanel(new GridLayout(6,1,10,10));
		p9 = new JPanel(new GridLayout(6,1,10,10));
		p10 = new JPanel(new GridLayout(6,1,10,10));
		p11 = new JPanel(new GridLayout(7,1,10,10));
		p12 = new JPanel(new GridLayout(6,1,10,10));
		p13 = new JPanel(new GridLayout(6,1,10,10));
		
		p14= new JPanel(new GridLayout(6,1,10,10));
		p15= new JPanel(new GridLayout(6,1,10,10));
		p16= new JPanel(new GridLayout(6,1,10,10));
		p17= new JPanel(new GridLayout(6,1,10,10));
		
		b1 = new JButton("Login");
		cl1 = new JButton("CLogin");
		b2 = new JButton("New Account");
		search = new JButton("Search Client");
		logout = new JButton("Logout");
		b3 = new JButton("Next");
		b4 = new JButton("Withdrawals");
		b5 = new JButton("Deposits");
		b6 = new JButton("Pay Bills");
		b7 = new JButton("Money Transfer");
		b8 = new JButton("Check Balance");
		b9 = new JButton("Transaction History");
		back1= new JButton("Back");
		back2= new JButton("Back");
		back3= new JButton("Back");
		back4= new JButton("Back");
		back5= new JButton("Back");
		back6= new JButton("Back");
		back7= new JButton("Back");
		withdraw = new JButton("Withdraw");
		deposit = new JButton("Deposit");
		pay = new JButton("Pay Bill");
		
		
		l1 = new JLabel("Client Information Form");
		name1 = new JLabel("Full Name");
		contact1 = new JLabel("Contact");
		email1 = new JLabel("Email");
		password1 = new JLabel("Password");
		accType1 = new JLabel("Account Type");
		
		login = new JTextField("Login");
		pass = new JTextField("Password");
		name2 = new JTextField();
		contact2 = new JTextField();
		email2 = new JTextField();
		password2 = new JTextField();
		accType2 = new JTextField();
		security = new JTextField();	
		name3 = new JTextField("Name");
		search2 = new JButton("Search");
		searchtf = new JTextField("Enter Client Name");
		contact3= new JTextField("Contact");
		email3 = new JTextField("Email");
		address3 = new JTextField("Address");
		modify = new JButton("Update");
		delete= new JButton("Delete Client");
		amount1 = new JTextField("Enter Amount");
		amount2 = new JTextField("Enter Amount");
		amount3 = new JTextField("Enter Bill Amount");
		
		b1.setFocusPainted(false);
		b2.setFocusPainted(false);
		b3.setFocusPainted(false);
		b4.setFocusPainted(false);
		b5.setFocusPainted(false);
		b6.setFocusPainted(false);
		b7.setFocusPainted(false);
		b8.setFocusPainted(false);
		b9.setFocusPainted(false);
		
		//Login Screen
		login();		    

	    b1.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent evt) {
	    	  adminView();
	      }
	    });
	    
	    cl1.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent evt) {
	    	  clientView();
	      }
	    });
	      
	    b2.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent evt) {
	    	  newClient(); 
	      }
	    });
	    
	    search.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent evt) {
	       	  searchClient();
	      }
	    });
	      
	    b3.addActionListener(new ActionListener(){
	      @Override
	      public void actionPerformed(ActionEvent evt) {
	        	 
	        	 //create account on button press here
	      }
	    });
	      
	    b4.addActionListener(new ActionListener() {
	       @Override
	       public void actionPerformed(ActionEvent evt) {
	    	   withdrawView();
	       }
	    });
	      
	    b5.addActionListener(new ActionListener() {
	       @Override
	       public void actionPerformed(ActionEvent evt) {
	           depositView();
	       }
	    });
	      
	    b6.addActionListener(new ActionListener() {
	       @Override
	       public void actionPerformed(ActionEvent evt){
	    	   billView();
	       }
	    });
	      
	    b7.addActionListener(new ActionListener() {
	       @Override
	       public void actionPerformed(ActionEvent evt){	        	 
	           mTransferView();
	       }
	    });
	 
	    b8.addActionListener(new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent evt){	        	 
		       balanceView();
	       }
	    });

	    b9.addActionListener(new ActionListener() {
	       @Override
	       public void actionPerformed(ActionEvent evt){	        	 
	    	   transactionListView();
		    }
		});
	      
	    logout.addActionListener(new ActionListener() {
	       @Override
	       public void actionPerformed(ActionEvent evt){
	           login();
	       }
	    });
	      
	    back1.addActionListener(new ActionListener() {
	       @Override
	       public void actionPerformed(ActionEvent evt) {
	    	   adminView();	 
	       }
	    });
	      
	    back2.addActionListener(new ActionListener() {
	       @Override
	       public void actionPerformed(ActionEvent evt) {
	 	       clientView();	 
	       }
	    });
	      
	    back3.addActionListener(new ActionListener() {
	       @Override
	       public void actionPerformed(ActionEvent evt) {
		       clientView(); 
	       }
	    });
	      
	    back4.addActionListener(new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent evt) {
		       clientView(); 
		   }
		});
	    
	    back5.addActionListener(new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent evt) {
		       clientView(); 
		   }
		});
	    
	    back6.addActionListener(new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent evt) {
			   clientView(); 
		   }
		});
	    
	    back7.addActionListener(new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent evt) {
		       clientView(); 
		   }
	    });
	      
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    setTitle("Banking System"); 
	    setSize(450, 450);  
	    setVisible(true);  
	}

	public static void main(String[] args) throws Exception 
	{
		
		XmlUtils xml = new XmlUtils();		
		
		Client cli = new Client(1,"Joao Victor","Guimaraes","j@gmail.com","Somewhere","joaovictor","123456");
		cli.saving = new Saving(1,0,"Test");
		cli.chequing = new Chequing(0,0,"Test");
		
		//cli.createNewClient(xml, cli);
		
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
		
		//xml.updateXml();
		
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
		//Auto-generated method stub
		
	}
	
	public void login()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
        add(p1,BorderLayout.CENTER);
		p1.add(login);
		p1.add(pass);
		p1.add(b1);
		p1.add(cl1);
		revalidate();
   	 	repaint();
	}
	
	public void clientView()
	{
		getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(p4,BorderLayout.CENTER);
		p4.add(b4);
		p4.add(b5);
		p4.add(b6);
		p4.add(b7);
		p4.add(b8);
		p4.add(b9);
		add(p4);
   	 	revalidate();
   	 	repaint();
	}
	
	public void adminView()
	{
		getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(p2,BorderLayout.CENTER);
		p2.add(b2);
		p2.add(search);
		p2.add(logout);
   	 	revalidate();
   	 	repaint();
	}
	
	public void newClient()
	{
		getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(p3,BorderLayout.CENTER);
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
		p3.add(cb1);
		p3.add(security);
		p3.add(b3);
   	 	revalidate();
   	 	repaint();
	}
	
	public void searchClient()
	{
		getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(p11,BorderLayout.CENTER);
        //add jlist item to p11
        p11.add(searchtf);
      	p11.add(search2);
      	p11.add(name3); 
      	p11.add(contact3);
      	p11.add(email3);
      	p11.add(address3);
      	p11.add(modify);
      	p11.add(delete);
      	p11.add(back1); 
		revalidate();
   	 	repaint();
	}
	
	public void withdrawView()
	{
		getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(p12,BorderLayout.CENTER);
		p12.add(cb21);
		p12.add(amount1);
		p12.add(withdraw);
		p12.add(back2);
   	 	revalidate();
   	 	repaint();
	}
	
	public void depositView()
	{
		getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(p13,BorderLayout.CENTER);
		p13.add(cb22);
		p13.add(amount2);
		p13.add(deposit);
		p13.add(back3);
   	 	revalidate();
   	 	repaint();
	}
	
	public void billView() 
	{
		getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(p14,BorderLayout.CENTER);
		p14.add(cb23);
		p14.add(cb3);
		p14.add(amount3);
		p14.add(pay);
		p14.add(back4);
   	 	revalidate();
   	 	repaint();
	}
	
	public void mTransferView()
	{
		getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(p15,BorderLayout.CENTER);
        p15.add(back5);
   	 	revalidate();
   	 	repaint();
	}
	
	public void balanceView()
	{
		getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(p16,BorderLayout.CENTER);
        p16.add(back6);
   	 	revalidate();
   	 	repaint();
	}
		
	public void transactionListView()
	{
		//display Jtable of transactions
		getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(p17,BorderLayout.CENTER);
		p17.add(back7);
   	 	revalidate();
   	 	repaint();
	}

}//class ends
