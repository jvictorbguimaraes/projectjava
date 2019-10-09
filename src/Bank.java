import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Bank extends JFrame implements ActionListener
{	
	private static final long serialVersionUID = 1L;

	//private variables
	private int i = 0, j;
	private JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17,p18, cont,pan;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9,reset,payB,showB,checkBal,update1,update2, search2, modify, delete, back1, back2, back3, back4, back5, back6, back7, back8,back9, withdraw, deposit, search, logout1,logout2,pay;
	private JPanel np1,np2,np3,np4,np5,np6,np7,np8,np9,np10,np11;
	private JPanel sp1,sp2,sp3,sp4,sp5,sp6,sp7;
	private JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
	private JLabel er1,er2,er3,er4,er5,er6;
	private JLabel s1,s2,s3,s4,s5,s6;
	private JLabel f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,f21,f22,f23,f24,f25,f26;
	private JLabel msg1,msg2;
	private JLabel taccountType, billno1,bamount1,billno2,accountType,showB2,displayBill;
	private JTextField bamount2,billnum;

	private JButton taccount;
	private String data2[][];
	private String column1[]={"TYPE","DATE","AMOUNT"};    
	private JTable jt=new JTable();
	private JScrollPane sp=new JScrollPane();
	private JScrollPane clientList =new JScrollPane();


	private XmlUtils xml = new XmlUtils();
	private Client client;
	private Client client2=new Client();
	private JList <String>clients;
	private DefaultListModel<String> listModel; 
	private ArrayList<Client> clientInfo;

	//Customer Variables
	private JTextField login, pass, name1, contact1, email1, address1, password1, name2, contact2, email2, address2, password2, security,name3,contact3, email3,address3,searchtf,amount1,amount2,amount3;
	private JTextField amt,recieverA,securityQ,securityA;
	private String[] questions = { "Whats your maiden name?","What was your first job?", "Whats your pet's name?"};
	private final JComboBox<String> cb1 = new JComboBox<String>(questions);
	private String[] accType11 = { "Chequing","Savings","Credit"};
	private final JComboBox<String> cb21 = new JComboBox<String>(accType11);
	private String[] accType22 = { "Chequing","Savings"};
	private final JComboBox<String> cb22 = new JComboBox<String>(accType22);
	private String[] accType23 = { "Chequing","Savings"};
	private final JComboBox<String> cb23 = new JComboBox<String>(accType23);
	private String[] accType24 = { "Chequing","Savings","Credit"};
	private final JComboBox<String> cb24 = new JComboBox<String>(accType24);
	private String[] accType25 = { "Chequing","Savings","Credit"};
	private final JComboBox<String> cb25 = new JComboBox<String>(accType25);
	private String[] accType26 = { "Chequing","Savings"};
	private final JComboBox<String> cb26 = new JComboBox<String>(accType26);
	private String[] billType = { "Electricity","Water","Mobile","Credit"};
	private final JComboBox<String> cb3 = new JComboBox<String>(billType);

	public Bank()
	{
		//initializing all the GUI components
		initialize();
		
		//initialing client list that is being displayed		
		initializeClients();		

		//Login Screen
		login();		    

		//The admin should be able to create a savings / chequing / credit account for a client
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) { 
				//System.out.println(login.getText()+" "+pass.getText());
				client= new Client();

				if(client.validateLogin(xml, login.getText(), pass.getText()))
				{
					client.getClientInfo(xml,"Email", login.getText());
					if(client.isAdmin())
						adminView();
					else
						clientView();
				}	
				else
				{
					er1.setForeground (Color.red);
					er1.setText("Incorrect Email or Password");
				}
				//display error message
			}
		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				resetFields();
				newClient(); 
			}
		});

		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				initializeClients();	
				searchtf.setText("");
				searchClient();
			}
		});

		b3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {

				//validation
				er3.setForeground (Color.red);
				s2.setForeground(Color.green);
				if(name1.getText().equals("")||contact1.getText().equals("")||email1.getText().equals("")||address1.getText().equals("")||password1.getText().equals("")||security.getText().equals(""))
					er3.setText("Error: All fields are mandatory.");
				else if(checkFormat(email1.getText())==false)
					er3.setText("Error: Incorrect email format.");
				else if(checkNumber(contact1.getText()))
					er3.setText("Error: Phone number must not have characters.");
				else
				{
					er3.setText("");
					s2.setText("Customer account created successfully!");
					Client newClient = new Client(10, name1.getText(),email1.getText(),contact1.getText(),address1.getText(),password1.getText(),cb1.getSelectedItem().toString(),security.getText(), false);
					newClient.createNewClient(xml, newClient);
				}
			}
		});

		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				resetFields();
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
				f16.setText(client.getSecurityQ());
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

		withdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){
				s1.setForeground (Color.green);
				er2.setForeground (Color.red);
				if(cb21.getSelectedItem().equals("Chequing"))
				{
					client.getChequing().withdraw(client, xml, Double.parseDouble(amount1.getText()),true); 
					s1.setText("Withdraw Successful from Chequing Account!");
					initializeTable();
				}
				else if(cb21.getSelectedItem().equals("Savings"))
				{
					if(client.getSaving().withdraw(client, xml, Double.parseDouble(amount1.getText()),true))
					{
						initializeTable();
						s1.setText("Withdraw Successful from Savings Account!");
					}
					else	
						er2.setText("Error: No Balance in your Savings Account");

				}
				else
				{
					if(client.getCredit().withdraw(client, xml, Double.parseDouble(amount1.getText()),true))
						s1.setText("Withdraw Successful from Credit Account!");
					else	
						er2.setText("Error: Exceeded credit limit");
				}

			}
		});

		deposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){	        	 

				//check validation if no money then set error text
				s4.setForeground (Color.green);
				if(cb22.getSelectedItem().equals("Chequing"))
				{
					client.getChequing().deposit(client, xml, Double.parseDouble(amount2.getText()),true); 
					s4.setText("Deposit Successful into Chequing Account!");
				}
				else if(cb22.getSelectedItem().equals("Savings"))
				{
					client.getSaving().deposit(client, xml, Double.parseDouble(amount2.getText()),true); 
					s4.setText("Deposit Successful into Savings Account!");
				}
			}
		});

		pay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){	 

				er6.setForeground (Color.red);
				s5.setForeground(Color.green);
				String msg="";
				if(client.getSecurityA().equalsIgnoreCase(securityA.getText()))
				{
					if(cb26.getSelectedItem().equals("Savings"))
					{
						msg=client.getSaving().transferMoney(xml, client, Integer.parseInt(recieverA.getText()), "Saving", Double.parseDouble(amt.getText()));
					}
					else
					{
						msg=client.getChequing().transferMoney(xml, client, Integer.parseInt(recieverA.getText()), "Chequing", Double.parseDouble(amt.getText()));
					}
				}
				else
					er6.setText("Error: Security check failed");
				
				s5.setText(msg);
			}
		});

		showB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){	
				BillPayment bills= new BillPayment();
				if(bills.getBill(xml,Integer.parseInt(billnum.getText()))) 
				{
					billno2.setText(bills.toString());
				}
				else
				{
					billno2.setText("Bill Not Found");
				}
			}
		});

		payB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){	
				if(cb23.getSelectedItem().equals("Chequing"))
					bamount1.setText(client.getChequing().payBill(client, xml,Integer.parseInt(billnum.getText())));
				else
					bamount1.setText(client.getSaving().payBill(client, xml,Integer.parseInt(billnum.getText())));
			}
		});

		checkBal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){	
				if(cb24.getSelectedItem().equals("Chequing"))
					s6.setText("Account Balance: "+client.getChequing().getAccountBal());
				else if(cb24.getSelectedItem().equals("Savings"))
					s6.setText("Account Balance: "+client.getSaving().getAccountBal());
				else
					s6.setText("Balance Payment: "+client.getCredit().getAccountBal());

			}
		});
		modify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){	
				
				String searchName=searchtf.getText();
				int point=0,m=0;
				msg2.setText("");
				
				clientInfo = new ArrayList<Client>(client.getClients(xml));
				for(Client c : clientInfo )
				{
					if(c.getName().equalsIgnoreCase(searchName))
						point = m;
					else
						m++;
				}
				Client cli = clientInfo.get(point);
				
				if(name3.getText().equals("")||contact3.getText().equals("")||email3.getText().equals("")||address3.getText().equals(""))
					msg2.setText("Error: All fields are mandatory.");
				else if(checkFormat(email3.getText())==false)
					msg2.setText("Error: Incorrect email format.");
				else if(checkNumber(contact3.getText()))
					msg2.setText("Error: Phone number must not have characters.");
				else
				{
					cli.setName(name3.getText());
					cli.setPhone(contact3.getText());
					cli.setAddress(address3.getText());
					cli.setEmail(email3.getText());
					
					client.updateClientInfo(xml, cli);
					msg2.setText("Details Updated Successfully");
					
				}
				initializeClients();
			}
		});
		
		search2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				String searchName=searchtf.getText();
				int point=0,m=0;
				boolean found=false;
				msg1.setText("");
				
				clientInfo = new ArrayList<Client>(client.getClients(xml));
				for(Client c : clientInfo )
				{
					if(c.getName().equalsIgnoreCase(searchName))
					{
						point = m;
						found = true;
						break;
					}
					else
						m++;	
				}
				
				if(found==true)
				{
					Client cli = clientInfo.get(point);
					name3.setText(cli.getName());
					contact3.setText(cli.getPhone());
					email3.setText(cli.getEmail());
					address3.setText(cli.getAddress());
				}
				else
					msg1.setText("Client does not exist");
				
			}
		});
				
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){	
				
				String searchName=searchtf.getText();
				int point=0,m=0;
				
				clientInfo = new ArrayList<Client>(client.getClients(xml));
				for(Client c : clientInfo )
				{
					if(c.getName().equalsIgnoreCase(searchName))
						point = m;
					else
						m++;
				}
				Client cli = clientInfo.get(point);					
				cli.removeClient(xml);
				name3.setText("");
				contact3.setText("");
				email3.setText("");
				address3.setText("");
				initializeClients();
			}
		});

		logout1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){
				login();
			}
		});

		logout2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){
				login();
			}
		});

		update1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){
				updateView(); 
			}
		});  

		update2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt){
				//update the client details 
				
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

		back8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				clientView();  
			}
		});

		back9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				adminView();	 
			}
		});
		securityA.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				securityA.setText("");
			}
		});

		taccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				initializeTable();
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setTitle("Banking System"); 
		setSize(450, 650);  
		setVisible(true);  
	}

	public static void main(String[] args) throws Exception 
	{		
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

	}

	public void initializeTable()
	{
		//reinitializing variables
		ArrayList<Transaction> list;
		i=0;j=0;

		if(cb25.getSelectedItem().equals("Chequing"))
			list = client.getChequing().getTransactions();
		else if(cb25.getSelectedItem().equals("Savings")) 
			list = client.getSaving().getTransactions();
		else 
			list = client.getCredit().getTransactions();
		
		data2=new String[list.size()][3];	
				
		for(Transaction trans : list)
		{        
			j=0;
			if(trans instanceof Deposit) 
			{
				data2[i][j]="Deposit" ;
			}
			else if(trans instanceof Withdraw)
			{
				data2[i][j]= "Withdraw";
			}
			else if(trans instanceof MoneyTransfer)
			{
				data2[i][j]= "Money Transfer";
			}
			else
			{
				data2[i][j]= "Bill Payment";
			}

			j++;            	 
			data2[i][j]=list.get(i).getTransDate();
			j++;
			data2[i][j]=Double.toString(list.get(i).getAmount());
			i++;
		} 

		//Display the transactions
//		for(i=0;i<list.size();i++)
//		{
//			for(j =0;j<3;j++)
//			{
//				System.out.println("data2["+i+"]["+j+"]"+data2[i][j]);
//			}
//			System.out.println();
//		}

		jt=new JTable(data2,column1);    
		sp.setViewportView(jt);
	}

	public void initialize()
	{
		p1 = new JPanel(new GridLayout(6,1,10,10));
		p2 = new JPanel(new GridLayout(5,2,10,10));
		p3 = new JPanel(new GridLayout(10,2,10,10)); 
		p4 = new JPanel(new GridLayout(5,4,10,10));
		p5 = new JPanel(new GridLayout(10,1,10,10));
		p6 = new JPanel(new GridLayout(10,1,10,10));
		p7 = new JPanel(new GridLayout(10,1,10,10));
		p8 = new JPanel(new GridLayout(10,1,10,10));
		p9 = new JPanel(new GridLayout(10,1,10,10));
		p10 = new JPanel(new GridLayout(10,1,10,10));
		p11 = new JPanel(new GridLayout(2,2,10,10));
		p12 = new JPanel(new GridLayout(5,2,10,10));
		p13 = new JPanel(new GridLayout(5,2,10,10));
		p14= new JPanel(new GridLayout(10,1,10,10));
		p15= new JPanel(new GridLayout(7,2,10,10));
		p16= new JPanel(new GridLayout(6,1,10,10));
		p17= new JPanel(new GridLayout(1,2,10,10));
		p18= new JPanel(new GridLayout(10,1,10,10));

		np1= new JPanel(new GridLayout(3,1,10,10));
		np2= new JPanel(new GridLayout(1,1,10,10));
		np3= new JPanel(new GridLayout(1,1,10,10));
		np4= new JPanel(new GridLayout(1,1,10,10));
		np5= new JPanel(new GridLayout(1,1,10,10));
		np6= new JPanel(new GridLayout(1,1,10,10));
		np7= new JPanel(new GridLayout(1,1,10,10));
		np8= new JPanel(new GridLayout(1,1,10,10));
		np9= new JPanel(new GridLayout(1,1,10,10));
		np10= new JPanel(new GridLayout(1,1,10,10));
		np11= new JPanel(new GridLayout(2,1,10,10));

		sp1= new JPanel(new GridLayout(2,1,10,10));
		sp2= new JPanel(new GridLayout(2,1,10,10));
		sp3= new JPanel(new GridLayout(10,1,10,10));
		sp4 = new JPanel(new GridLayout(2,1,10,10));
		sp5 = new JPanel(new GridLayout(2,1,10,10));
		sp6 = new JPanel(new GridLayout(2,1,10,10));
		sp7= new JPanel(new GridLayout(1,1,10,10));

		f1 = new JLabel("Email");
		f2 = new JLabel("Password");
		f3 = new JLabel("Full Name");
		f4 = new JLabel("Contact");
		f5 = new JLabel("Email");
		f6 = new JLabel("Address");
		f7 = new JLabel("Password");
		f8 = new JLabel("Security Question Answer");
		f9 = new JLabel("Client Name");
		f10 = new JLabel("Select Account");
		f11 = new JLabel("Enter Amount");
		f12 = new JLabel("Select Account");
		f13 = new JLabel("Enter Amount");
		f14 = new JLabel("Reciever Account Number");
		f15 = new JLabel("Account Type");
		f16 = new JLabel("Security Question");
		f17 = new JLabel("Enter Security Answer");
		f18 = new JLabel("Select Account");
		f19 = new JLabel("Select Security Question");
		f20 = new JLabel("Enter Amount");
		f21 = new JLabel("Select Account");
		f22 = new JLabel("Enter Client Name");
		f23 = new JLabel("Name");
		f24 = new JLabel("Contact");
		f25 = new JLabel("Email");
		f26 = new JLabel("Address");
		taccountType=new JLabel("Select Account Type");
		billno1 =new JLabel("Enter Bill Number");
		bamount1 = new JLabel("Enter Bill Amount");

		b1 = new JButton("Login");
		b2 = new JButton("New Account");
		search = new JButton("Search or Update Client");
		logout1 = new JButton("Logout");
		logout2 = new JButton("Logout");
		b3 = new JButton("Create");
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
		back8= new JButton("Back");
		back9= new JButton("Back");
		withdraw = new JButton("Withdraw");
		deposit = new JButton("Deposit");
		pay = new JButton("Transfer");
		payB = new JButton("Pay Bill");
		checkBal = new JButton("Check Balance");
		update1 = new JButton("Update");
		update2 = new JButton("Update");
		reset = new JButton("Reset");
		search2 = new JButton("Search");
		modify = new JButton("Update");
		delete= new JButton("Delete Client");		
		taccount =new JButton("Display");
		billno2 =new JLabel("");
		accountType =new JLabel("Enter Account Type");
		showB =new JButton("Show");


		l1 = new JLabel("TORONTO BANKING");
		l2 = new JLabel("Kindly login using your email and password.");
		l3 = new JLabel("New Client? Request our Administrator to create your account.");
		l4 =new JLabel("Welcome Client! Select one of the following functions ");
		l5 = new JLabel("Withdraw Money from (Chequing, Savings or Credit)");
		l6 = new JLabel("Welcome Admin! Select one of the following funtions.");
		l7 = new JLabel("Enter Client Details");
		l8 = new JLabel("Deposit Money into (Chequing or Savings)");
		l9 = new JLabel("Pay Your Bills");
		l10 = new JLabel("Transfer Money");
		l11= new JLabel("Check your Account Balance");

		er1 = new JLabel(" ");
		er2 = new JLabel(" ");
		er3 = new JLabel(" ");
		er4 = new JLabel(" ");
		er5 = new JLabel(" ");
		er6 = new JLabel(" ");
		s1 = new JLabel(" ");
		s2 = new JLabel(" ");
		s3 = new JLabel(" ");
		s4 = new JLabel(" ");
		s5 = new JLabel(" ");
		s6 = new JLabel(" ");
		msg1 = new JLabel(" ");
		msg2 = new JLabel(" ");

		bamount2= new JTextField("");
		login = new JTextField("");
		pass = new JTextField("");
		name1 = new JTextField("");
		address1 = new JTextField("");
		contact1 = new JTextField("");
		email1 = new JTextField("");
		password1 = new JTextField("");
		name2 = new JTextField("");
		address2 = new JTextField("");
		contact2 = new JTextField("");
		email2 = new JTextField("");
		password2 = new JTextField("");
		security = new JTextField("");	
		name3 = new JTextField("");
		searchtf = new JTextField("");
		contact3= new JTextField("");
		email3 = new JTextField("");
		address3 = new JTextField("");
		amount1 = new JTextField("");
		amount2 = new JTextField("");
		amount3 = new JTextField("Enter Bill Number");
		amt = new JTextField("");
		billnum = new JTextField("");
		recieverA = new JTextField("");
		securityQ = new JTextField();//set Text from Client
		securityA = new JTextField("Type Answer...");

		b1.setFocusPainted(false);
		b2.setFocusPainted(false);
		b3.setFocusPainted(false);
		b4.setFocusPainted(false);
		b5.setFocusPainted(false);
		b6.setFocusPainted(false);
		b7.setFocusPainted(false);
		b8.setFocusPainted(false);
		b9.setFocusPainted(false);


	}

	public void initializeClients()
	{
		//getting the list of all clients
		clientInfo = new ArrayList<Client>(client2.getClients(xml));
		listModel = new DefaultListModel<>();
		//populating the list modal to display client list
		for(int i=0;i<clientInfo.size();i++)
		{
			listModel.addElement(clientInfo.get(i).getName());
		}
		clients = new JList<>(listModel);
		clientList.setViewportView(clients);
		np9.add(clientList);
	}
	
	public void login()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(np1,BorderLayout.NORTH);
		add(p1,BorderLayout.CENTER);
		add(sp1,BorderLayout.SOUTH);
		np1.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
		p1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		sp1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		login.setHorizontalAlignment(SwingConstants.CENTER);
		pass.setHorizontalAlignment(SwingConstants.CENTER);
		b1.setHorizontalAlignment(SwingConstants.CENTER);
		np1.add(l1);
		np1.add(l2);
		np1.add(l3);
		p1.add(f1);
		p1.add(login);
		p1.add(f2);
		p1.add(pass);
		p1.add(b1);
		sp1.add(er1);
		er1.setText(" ");
		revalidate();
		repaint();
	}

	public void clientView()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(np2,BorderLayout.NORTH);
		add(p4,BorderLayout.CENTER);
		np2.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		p4.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		np2.add(l4);       
		p4.add(b4);
		p4.add(b5);
		p4.add(b6);
		p4.add(b7);
		p4.add(b8);
		p4.add(b9);
		//p4.add(update1);
		p4.add(logout2);
		add(p4);
		revalidate();
		repaint();
	}

	public void adminView()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(p2,BorderLayout.CENTER);
		p2.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		l6.setHorizontalAlignment(SwingConstants.CENTER);
		p2.add(l6);
		p2.add(b2);
		p2.add(search);
		p2.add(logout1);
		revalidate();
		repaint();
	}

	public void newClient()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(np3,BorderLayout.NORTH);
		add(sp2,BorderLayout.SOUTH);
		add(p3,BorderLayout.CENTER);
		np3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
		p3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		sp2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		np3.add(l7);
		sp2.add(er3);
		sp2.add(s2);
		p3.add(f3);
		p3.add(name1);
		p3.add(f4);
		p3.add(contact1);
		p3.add(f5);
		p3.add(email1);
		p3.add(f6);
		p3.add(address1);
		p3.add(f7);
		p3.add(password1);
		//p3.add(f18);
		//p3.add(cb25);
		p3.add(f19);
		p3.add(cb1);
		p3.add(f8);
		p3.add(security);
		p3.add(b3);
		p3.add(back9);
		p3.add(reset);
		revalidate();
		repaint();
	}
	public void resetFields()
	{
		name1.setText("");
		contact1.setText("");
		email1.setText("");
		address1.setText("");
		password1.setText("");
		security.setText("");
		er3.setText("");
		s2.setText("");
	}

	public void searchClient()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(np9,BorderLayout.CENTER);
		add(sp3,BorderLayout.SOUTH);
		add(p11,BorderLayout.NORTH);
		p11.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		np9.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		sp3.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		p11.add(f22);
		p11.add(searchtf);
		p11.add(search2);
		p11.add(msg1);
		sp3.add(f23);
		sp3.add(name3); 
		sp3.add(f24);
		sp3.add(contact3);
		sp3.add(f25);
		sp3.add(email3);
		sp3.add(f26);
		sp3.add(address3);
		sp3.add(modify);
		sp3.add(delete);
		sp3.add(back1); 
		sp3.add(msg2);		
		
		revalidate();
		repaint();
	}

	public void withdrawView()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(np4,BorderLayout.NORTH);
		add(p12,BorderLayout.CENTER);
		add(sp4,BorderLayout.SOUTH);
		np4.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		p12.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		sp4.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		np4.add(l5);
		p12.add(f10);
		p12.add(cb21);
		p12.add(f11);
		p12.add(amount1);
		p12.add(withdraw);
		p12.add(back2);
		sp4.add(s1);
		sp4.add(er2);
		er2.setText(" ");
		s1.setText(" ");
		revalidate();
		repaint();
	}

	public void depositView()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(np5,BorderLayout.NORTH);
		add(p13,BorderLayout.CENTER);
		add(sp5,BorderLayout.SOUTH);
		np5.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		p13.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		sp5.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		np5.add(l8);
		p13.add(f21);
		p13.add(cb22);
		p13.add(f20);
		p13.add(amount2);
		p13.add(deposit);
		p13.add(back3);
		sp5.add(s4);
		sp5.add(er5);
		er5.setText(" ");
		s4.setText(" ");
		revalidate();
		repaint();
	}

	public void billView() 
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(np6,BorderLayout.NORTH);
		add(p14,BorderLayout.CENTER);
		p14.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		np6.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		np6.add(l9);
		billno2.setText("Test");
		p14.add(billno1);
		p14.add(billnum);
		p14.add(showB);
		p14.add(billno2);
		p14.add(accountType);
		p14.add(cb23);
		p14.add(bamount1);
		p14.add(payB);
		p14.add(back4);
		billno2.setText("");
		bamount1.setText("");
		revalidate();
		repaint();
	}

	public void mTransferView()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(np7,BorderLayout.NORTH);
		add(p15,BorderLayout.CENTER);
		add(sp6,BorderLayout.SOUTH);
		p15.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		np7.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		sp6.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		np7.add(l10);
		p15.add(f14);
		p15.add(recieverA);
		p15.add(f13);
		p15.add(amt);
		p15.add(f15);
		p15.add(cb26);
		p15.add(f16);
		p15.add(securityA);
		p15.add(pay);
		p15.add(back5);
		sp6.add(s5);
		sp6.add(er6);
		er6.setText(" ");
		s5.setText(" ");
		revalidate();
		repaint();
	}

	public void balanceView()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(np8,BorderLayout.NORTH);
		add(p16,BorderLayout.CENTER);
		p16.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		np8.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		np8.add(l11);
		p16.add(f12);
		p16.add(cb24);
		p16.add(checkBal);
		p16.add(back6);
		p16.add(s6);
		s6.setText(" ");
		revalidate();
		repaint();
	}

	public void transactionListView()
	{
		//display Jtable of transactions
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		np11.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		p17.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		sp7.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(np11,BorderLayout.NORTH);
		add(p17,BorderLayout.CENTER);
		add(sp7,BorderLayout.SOUTH);
		np11.add(taccountType);
		np11.add(cb25);
		p17.add(sp);
		sp7.add(taccount);
		sp7.add(back7);
		revalidate();
		repaint();
	}

	public void updateView()
	{
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		add(p18,BorderLayout.CENTER);
		p18.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		p18.add(name2);
		p18.add(contact2);
		p18.add(email2);
		p18.add(address2);
		p18.add(password2);
		p18.add(update2);
		p18.add(back8);
		revalidate();
		repaint();
	}

	public boolean checkFormat(String email)
	{
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
	public boolean checkNumber(String num)
	{
		if (num == null || num.length() == 0) {
			return true;
		}

		for (char c : num.toCharArray()) {
			if (!Character.isDigit(c)) {
				return true;
			}
		}
		return false;
	}

}//class ends
