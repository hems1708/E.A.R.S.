import java.sql.*;


public class EARSdatabase {
	
	private Statement statement = null;
	private Connection connection = null;
	
	public EARSdatabase() throws SQLException, ClassNotFoundException{		
		
		//loading the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		
		//connecting to database
		connection = DriverManager.getConnection("jdbc:mysql://localhost/projectears", "hemesh", "admin");
		System.out.println("Database connected.");
		
		statement = connection.createStatement();
	}
	
	public void closeConnection() throws SQLException{
		connection.close();
	}
	
	public void updateDatabase(String s) throws SQLException {
		statement.executeUpdate(s);
	}
	
	public ResultSet queryDatabase(String s) throws SQLException {
		return statement.executeQuery(s);
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		
		//loading the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		
		//connecting to the database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/projectears", "hemesh", "admin");
		System.out.println("Database connected.");
		
		Statement statement = connection.createStatement();
		
		//statement.executeUpdate("insert into eployee values(\"muskan\", 4, 5)");
		
		
		/*statement.executeUpdate("create table student("
				+ "studentId INT PRIMARY KEY,"
				+ "name VARCHAR(30),"
				+ "major VARCHAR(30));");*/
		
		/*while(resultSet.next()) {
			System.out.println(resultSet.getString(1)+"\t"+
		resultSet.getString(2)+"\t"+resultSet.getString(3));
		}*/
		
		connection.close();
	}
}
