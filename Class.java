import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Class {
	static int Class_ID;
	static int Student_ID;
	static int Course_ID;
	static Connection conn;
	static PreparedStatement myStmt;
	static Statement stmt;
	static ResultSet result;
	static String nume,numes;
	
	public static int getSize() throws SQLException{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Management","root","");
		stmt = conn.createStatement();
		result = stmt.executeQuery("Select distinct Count(Class_ID) from Class;");
		result.last();
		return result.getInt(1);
	}
	
	public static void viewClasses() throws SQLException{
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Management","root","");
			stmt = conn.createStatement();
			result = stmt.executeQuery("Select * from Class");
			while (result.next()) {
				Class_ID = result.getInt("Class_ID");
				Course_ID = result.getInt("Course_ID");
				Student_ID = result.getInt("Student_ID");
				System.out.printf("%d, %d, %d\n", Class_ID, Course_ID, Student_ID);}
		} catch (SQLException e){
			e.printStackTrace();
		}	
	}
	
	public static void viewClassesDetail() throws SQLException{
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Management","root","");
			stmt = conn.createStatement();
			result = stmt.executeQuery("Select * from Class");
			while (result.next()) {
				Class_ID = result.getInt("Class_ID");
				Course_ID = result.getInt("Course_ID");
				Student_ID = result.getInt("Student_ID");
				myStmt = conn.prepareStatement("Select Nume From Course where Course_ID=?");
				myStmt.setInt(1,Course_ID);
				result = myStmt.executeQuery();
				result.last();
				nume =  result.getString(1);
				myStmt = conn.prepareStatement("Select Nume From Student where Student_ID=?");
				myStmt.setInt(1,Student_ID);
				result = myStmt.executeQuery();
				result.last();
				numes =  result.getString(1);
				System.out.printf("%d, %s, %s\n", Class_ID,nume, numes);}
		} catch (SQLException e){
			e.printStackTrace();
		}	
	}

	
	public static void deleteClass(int Class_ID) throws SQLException{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Management","root","");
			myStmt = conn.prepareStatement("Delete from Class where Class_ID=?");
			myStmt.setInt(1, Class_ID);
			myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void deleteClassCourse(int Course_ID) throws SQLException{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Management","root","");
			myStmt = conn.prepareStatement("Delete from Class where Course_ID=?");
			myStmt.setInt(1, Course_ID);
			myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void enrolStudent(int Class_ID,int Course_ID,int Student_ID) throws SQLException{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Management","root","");
			myStmt = conn.prepareStatement("Insert into Class values(?,?,?)");
			myStmt.setInt(1, Class_ID);
			myStmt.setInt(2, Course_ID);
			myStmt.setInt(3, Student_ID);
			myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) throws SQLException{
			viewClassesDetail();
			//addStudent("Popoviciu Tibi","1992-10-09","Agarbiceanu 1");
		    //updateStudent(10,"Pop Tiberiu",null,null);
	}	
}
