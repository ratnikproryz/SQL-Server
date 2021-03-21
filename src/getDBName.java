import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getDBName {
	DAO dao;
	Connection connection;
//	DAO db= new DAO(null);
	public getDBName(DAO temp) {
		dao=temp;
		connection=dao.DAOC(null);
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
		// TODO Auto-generated constructor stub
	}
}
