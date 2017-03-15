import java.sql.*;
public class Student {
	static Connection conn;
	static PreparedStatement myStmt;
	static Statement stmt;
	static ResultSet result;
	String query;
	static int size;
 	static int Student_ID ;
	static String Nume;
	static String Birth_Date;
	static String Adress;
	
	public static int getSize() throws SQLException{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Management","root","");
		stmt = conn.createStatement();
		result = stmt.executeQuery("Select Count(*) from Student;");
		result.last();
		return result.getInt(1);
	}
	
	public static void viewStudents() throws SQLException{
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Management","root","");
			stmt = conn.createStatement();
			result = stmt.executeQuery("Select * from Student");
			while (result.next()) {
				Student_ID = result.getInt("Student_ID");
				Nume = result.getString("Nume");
				Birth_Date = result.getString("Birth_Date");
				Adress = result.getString("Adress");
				System.out.printf("%d, %s, %s, %s\n", Student_ID, Nume, Birth_Date, Adress);}
		} catch (SQLException e){
			e.printStackTrace();
		}	
	}
	
	public static void addStudent(String Nume,String Birth_Date, String Adress) throws SQLException{
		try {
			Student_ID = getSize() + 1;
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Management","root","");
			myStmt = conn.prepareStatement("Insert into Student values (?,?,?,?)");
			myStmt.setInt(1, Student_ID);
			myStmt.setString(2, Nume);
			myStmt.setString(3, Birth_Date);
			myStmt.setString(4, Adress);
		    myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void deleteStudent(int Student_ID) throws SQLException{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Management","root","");
			myStmt = conn.prepareStatement("Delete from Student where Student_ID=?");
			myStmt.setInt(1, Student_ID);
			myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void updateStudent(int Student_ID,String Nume,String Birth_Date, String Adress) throws SQLException{
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Management","root","");
		if(Nume != null){
			myStmt = conn.prepareStatement("Update Student Set Nume=? where Student_ID=?");
			myStmt.setString(1, Nume);
			myStmt.setInt(2, Student_ID);
			myStmt.executeUpdate();
		}
		if(Birth_Date != null){
			myStmt = conn.prepareStatement("Update Student Set Birth_Date=? where Student_ID=?");
			myStmt.setString(1, Birth_Date);
			myStmt.setInt(2, Student_ID);
			myStmt.executeUpdate();
		}
		if(Adress != null){
			myStmt = conn.prepareStatement("Update Student Set Adress=? where Student_ID=?");
			myStmt.setString(1, Adress);
			myStmt.setInt(2, Student_ID);
			myStmt.executeUpdate();
		}		
	}
	
	public static void main(String[] args) throws SQLException{
			viewStudents();
			//addStudent("Popoviciu Tibi","1992-10-09","Agarbiceanu 1");
		    //updateStudent(10,"Pop Tiberiu",null,null);
	}
}
