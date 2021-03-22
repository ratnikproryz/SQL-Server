import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class Login extends JFrame implements ActionListener{

	private Connection connection;
	private JPanel contentPane;
	private JTextField tfServerName;
	private JTextField tfLogin;
	private JPasswordField pfPassword;
	private JTextField tfPort;
	private String user,pass, databaseName;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 723, 486);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbSQLServer = new JLabel("SQL Server");
		lbSQLServer.setBounds(30, 16, 656, 53);
		lbSQLServer.setHorizontalAlignment(SwingConstants.CENTER);
		lbSQLServer.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane.add(lbSQLServer);
		
		JLabel lbServerType = new JLabel("Server Type");
		lbServerType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbServerType.setBounds(30, 118, 104, 20);
		contentPane.add(lbServerType);
		
		JLabel lbServerNamel = new JLabel("Sever Name");
		lbServerNamel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbServerNamel.setBounds(30, 154, 104, 20);
		contentPane.add(lbServerNamel);
		
		
		JLabel lbAuthentication = new JLabel("Authentication");
		lbAuthentication.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbAuthentication.setBounds(30, 190, 130, 20);
		contentPane.add(lbAuthentication);
		
		JLabel lbLogin = new JLabel("Login");
		lbLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbLogin.setBounds(30, 226, 69, 20);
		contentPane.add(lbLogin);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbPassword.setBounds(30, 262, 104, 20);
		contentPane.add(lbPassword);
		
		JComboBox cbServerType = new JComboBox();
		cbServerType.setBounds(281, 116, 350, 26);
		cbServerType.addItem("Database Engine");
		cbServerType.addItem("Analysis Service");
		cbServerType.addItem("Reporting Service");
		contentPane.add(cbServerType);
		
		tfServerName = new JTextField();
		tfServerName.setBounds(281, 152, 174, 26);
		contentPane.add(tfServerName);
		tfServerName.setColumns(10);
		tfServerName.setText("localhost");
		
		JComboBox cbAuthentication = new JComboBox();
		cbAuthentication.setModel(new DefaultComboBoxModel(new String[] {"SQL Server Authentication", "Windows Authentication"}));
		cbAuthentication.setBounds(281, 190, 350, 26);
		contentPane.add(cbAuthentication);
		
		tfLogin = new JTextField();
		tfLogin.setBounds(281, 224, 350, 26);
		contentPane.add(tfLogin);
		tfLogin.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(281, 260, 350, 26);
		contentPane.add(pfPassword);
		
		JButton btConnect = new JButton("Connect");
		btConnect.setBounds(208, 362, 130, 40);
		contentPane.add(btConnect);
		btConnect.addActionListener(this);
		
		JButton btCancel = new JButton("Cancel");
		btCancel.setBounds(423, 362, 130, 40);
		contentPane.add(btCancel);
		btCancel.addActionListener(this);
		
		JLabel lbPort = new JLabel("Port");
		lbPort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbPort.setBounds(470, 158, 69, 20);
		contentPane.add(lbPort);
		
		tfPort = new JTextField();
		tfPort.setText("1433");
		tfPort.setBounds(527, 152, 104, 26);
		contentPane.add(tfPort);
		tfPort.setColumns(10);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Connect")) {
			setUser(tfLogin.getText());
			setPass(String.valueOf(pfPassword.getPassword()));
			DAO db= new DAO(Login.this);
			db.DAOC(Login.this.databaseName);
			SQL_SERVER sql= new SQL_SERVER(db);
			
		}
		else if (e.getActionCommand().equals("Cancel")) {
			dispose();
		}
	}
	
}
