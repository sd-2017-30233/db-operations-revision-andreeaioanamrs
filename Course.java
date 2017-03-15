import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Course {
	static Connection conn;
	static PreparedStatement myStmt;
	static Statement stmt;
	static ResultSet result;
	static String query;
 	static int Course_ID ;
	static String Nume;
	static String Teacher;
	static int Study_Year;
	
	public static int getSize() throws SQLException{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Management","root","");
		stmt = conn.createStatement();
		result = stmt.executeQuery("Select Count(*) from Course;");
		result.last();
		return result.getInt(1);
	}
	
	public static void viewCourses() throws SQLException{
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Management","root","");
			stmt = conn.createStatement();
			result = stmt.executeQuery("Select * from Course");
			while (result.next()) {
				Course_ID = result.getInt("Course_ID");
				Nume = result.getString("Nume");
				Teacher = result.getString("Teacher");
				Study_Year = result.getInt("Study_Year");
				System.out.printf("%d, %s, %s, %d\n", Course_ID, Nume, Teacher, Study_Year);}
		} catch (SQLException e){
			e.printStackTrace();
		}	
	}
	
	public static void addCourse(String Nume, String Teacher, int Study_Year) throws SQLException{
		try {
			Course_ID = getSize() + 1;
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Management","root","");
			myStmt = conn.prepareStatement("Insert into Course values (?,?,?,?)");
			myStmt.setInt(1, Course_ID);
			myStmt.setString(2, Nume);
			myStmt.setString(3, Teacher);
			myStmt.setInt(4, Study_Year);
		    myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void deleteCourse(int Course_ID) throws SQLException{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Management","root","");
			myStmt = conn.prepareStatement("Delete from Course where Course_ID=?");
			myStmt.setInt(1, Course_ID);
			myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void updateCourse(int Course_ID,String Nume,String Teacher, int Study_Year) throws SQLException{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Management","root","");
			if(Nume != null){
				myStmt = conn.prepareStatement("Update Course Set Nume=? where Course_ID=?");
				myStmt.setString(1, Nume);
				myStmt.setInt(2, Course_ID);
				myStmt.executeUpdate();
			}
			
			if(Teacher != null){
				myStmt = conn.prepareStatement("Update Student Set Teacher=? where Course_ID=?");
				myStmt.setString(1, Teacher);
				myStmt.setInt(2, Course_ID);
				myStmt.executeUpdate();
			}
			
			if(Study_Year != 0){
				myStmt = conn.prepareStatement("Update Student Set Study_Year=? where Course_ID=?");
				myStmt.setInt(1, Study_Year);
				myStmt.setInt(2, Course_ID);
				myStmt.executeUpdate();
			}	
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) throws SQLException{
			viewCourses();
			//addStudent("Popoviciu Tibi","1992-10-09","Agarbiceanu 1");
		    //updateStudent(10,"Pop Tiberiu",null,null);
	}
}
