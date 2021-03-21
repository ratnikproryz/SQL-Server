import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DAO {
	Login lgLogin;
	private String dbName, user, pass, url;
	private Connection connection;
	public DAO(Login temp) {
		// TODO Auto-generated constructor stub
		lgLogin=temp;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
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
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Connection DAOC(String dbName) {
		this.dbName=dbName;
		user=lgLogin.getUser();
		pass=lgLogin.getPass();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			if (dbName==null) {
				url = "jdbc:sqlserver://localhost:1433;";
			}
			else {
				url = "jdbc:sqlserver://localhost:1433;"+"databaseName="+dbName;
			}
			connection= DriverManager.getConnection(url,user,pass);
			return connection;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
}
