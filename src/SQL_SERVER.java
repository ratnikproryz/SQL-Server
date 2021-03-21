import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.swing.tree.DefaultMutableTreeNode;

public class SQL_SERVER extends JFrame implements TreeSelectionListener{

	private JPanel contentPane;
	private JTable jtable;
	private DefaultTableModel defaultTable;
	JScrollPane scrollTable;
	static DAO dao2;
	Connection connection;
	private Vector vData= new Vector();
	private Vector vTitle= new Vector();
	
	public SQL_SERVER(DAO temp) {
		dao2=temp;
		connection = dao2.DAOC(null);
		DatabaseMetaData metaData;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 534);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] tableType= new String[1];
		tableType[0]="TABLE";
		
		JTree treeEplorer = null;
		try {
			metaData = connection.getMetaData();
			ResultSet resultSet= metaData.getCatalogs();
			
			DefaultMutableTreeNode tree=new DefaultMutableTreeNode("Schema");
			while (resultSet.next()) {
				DefaultMutableTreeNode rootTreeNode= new DefaultMutableTreeNode(resultSet.getString(1));
				ResultSet resultTable=metaData.getTables(resultSet.getString(1),null,"%", tableType);
				while(resultTable.next()) {
					DefaultMutableTreeNode tableName=new DefaultMutableTreeNode(resultTable.getString(3));
					rootTreeNode.add(tableName);
				}
				tree.add(rootTreeNode);
			}
			treeEplorer= new JTree(tree);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		treeEplorer.setEditable(true);
		treeEplorer.setBounds(15, 35, 160, 406);
		// gắn hành động vào tree
		treeEplorer.getSelectionModel().addTreeSelectionListener((TreeSelectionListener) this);
		
//		contentPane.add(treeEplorer);
		
		JScrollPane scrollPane = new JScrollPane(treeEplorer);
		scrollPane.setBounds(15, 35, 160, 406);
		contentPane.add(scrollPane);
		
//		defaultTable= new DefaultTableModel(vData, vTitle);
//		jtable = new JTable(defaultTable);
//		scrollTable= new JScrollPane(jtable);
//		scrollTable.setBounds(181, 36, 658, 406);
//		contentPane.add(scrollTable);
		
	}
	public void getDBName(DAO temp) {
		dao2=temp;
		connection = dao2.DAOC(null);
		DatabaseMetaData metaData;
		try {
			metaData = connection.getMetaData();
			ResultSet resultSet= metaData.getCatalogs();
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub	
		String dbName=arg0.getPath().getPathComponent(1).toString();
		String table= arg0.getPath().getLastPathComponent().toString();
		connection=dao2.DAOC(dbName);
		reload(table);
//		defaultTable.fireTableDataChanged();
	}
	public void reload(String tableName) {
		try {
			//delete data in vectors
			
			vTitle.clear();
			vData.clear();
			int col_num;
			Statement statement=  connection.createStatement();
			ResultSet resultSet= statement.executeQuery("SELECT * FROM" + " "+tableName);// query information from table KhachHang
			ResultSetMetaData  resultSetMetaData= resultSet.getMetaData(); //Get information of ResultSet
			col_num= resultSetMetaData.getColumnCount(); // get num col of table KhahchHang
			
			vTitle= new Vector(col_num);
			for(int i=1; i<= col_num; i++) {
				vTitle.add(resultSetMetaData.getColumnLabel(i));
			}
			vData= new Vector();
			while (resultSet.next()) {
				Vector row= new Vector(col_num);
				for(int i=1; i<=col_num; i++) {
					row.add(resultSet.getString(i));
				}
				vData.add(row);
			}
//			defaultTable.fireTableDataChanged();
			defaultTable= new DefaultTableModel(vData, vTitle);
			jtable = new JTable(defaultTable);
			scrollTable= new JScrollPane(jtable);
			scrollTable.setBounds(181, 36, 658, 406);
			contentPane.add(scrollTable);
			resultSet.close();
			statement.close();

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
}
