package maintenance_system.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import maintenance_system.model.User;
import maintenance_system.util.SQLConnection;

public class UserDAO {
	
static SQLConnection DBMgr = SQLConnection.getInstance();
public static final int MYSQL_DUPLICATE_PK = 1062;
public static boolean isDuplicate = false;
	private static ArrayList<User> ReturnMatchingUserList (String queryString) {
		ArrayList<User> userListInDB = new ArrayList<User>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet userList = stmt.executeQuery(queryString);
		while (userList.next()) {
			User user = new User();
			
			user.setUsername(userList.getString("username"));
			user.setLastName(userList.getString("lastname"));
			user.setFirstName(userList.getString("firstname"));
			user.setUtaid(userList.getString("utaid"));
			user.setEmail(userList.getString("email"));
			user.setRole(userList.getString("role"));
			userListInDB.add(user);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return userListInDB;
	}
		
	private static void StoreListinDB (User user,String queryString) {
		String result = "";
		Statement stmt = null;
		isDuplicate = false;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertUser = queryString + " VALUES ('"  
					+ user.getUsername() + "','"
					+ user.getFirstName() + "','"
					+ user.getLastName() + "','"
					+ user.getEmail() + "','"
					+ user.getPassword() + "','"
					+ user.getRole() + "','"
					+ user.getUtaid() + "','"
					+ user.getPhone() + "','"
					+ user.getStreetaddress() + "','"
					+ user.getCity() + "','"
					+ user.getState() + "','"
					+ user.getZipcode() + "',"
					+ " SYSDATE())";
			stmt.executeUpdate(insertUser);
			conn.commit(); 
			System.out.println("Success: " +insertUser);
		} catch (SQLException e) {
			if(e.getErrorCode() == MYSQL_DUPLICATE_PK ){
		        //duplicate primary key 
				System.out.println("Duplicate Username");
				isDuplicate = true;
		    }
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}};
	}

	public static User getUser(String queryString){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		User user = new User();
		System.out.println(queryString);
	try {
		stmt = conn.createStatement();
		ResultSet userList = stmt.executeQuery(queryString);
		while (userList.next()) { 
			user.setUser(userList.getString("username"), userList.getString("firstname"), userList.getString("lastName"), userList.getString("email"), userList.getString("password"), userList.getString("role"), userList.getString("utaid"), userList.getString("phone"), userList.getString("streetaddress"), userList.getString("city"), userList.getString("state"), userList.getString("zipcode"));
			System.out.println(user.getFirstName()+ " -> Role= "+ user.getRole());
			return user;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return null;
	}
	
	public static void insertUser(User user) {  
		StoreListinDB(user,"INSERT INTO USER (username,firstName,lastName,email,password,role,utaid,phone,streetaddress,city,state,zipcode, date_in) ");
	}
	
	public static User loginUser(String username, String password){
		return getUser("SELECT * FROM USER WHERE username='"+username+"' and password='"+password+"'");
	}
	
	public static User getUserByUsername(String username){
		User user = null;
		
		Statement  statement = null;
		Connection con = SQLConnection.getDBConnection();
		try {
			
			statement = con.createStatement();
			String getUser = "SELECT * FROM USER WHERE username=" + "'"+username+"'"+ " ;";
			System.out.println(username);
			System.out.println(getUser);
			ResultSet result = statement.executeQuery(getUser);
			
			while(result.next()){
				user = new User();
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setEmail(result.getString("email"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setPhone(result.getString("phone"));
				user.setRole(result.getString("role"));
				user.setUtaid(result.getString("utaid"));
				user.setStreetaddress(result.getString("streetaddress"));
				user.setCity(result.getString("city"));
				user.setState(result.getString("state"));
				user.setZipcode(result.getString("zipcode"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	

	public static void updateUserDetails(User user, String username){
		String query = "UPDATE USER SET firstname='"+user.getFirstName()+"', "
		        		+ "lastname='"+user.getLastName()+"',  "
						+ "email='"+user.getEmail()+"', "
						+ "password='"+user.getPassword()+"', "
						+ "role='"+user.getRole()+"', "
						+ "utaid='"+user.getUtaid()+"', "
						+ "phone='"+user.getPhone()+"',  "
						+ "streetaddress='"+user.getStreetaddress()+"',  "
						+ "city='"+user.getCity()+"', "
						+ "state='"+user.getState()+"',  "
						+ "zipcode='"+user.getZipcode()+"'"
						+ " WHERE username='"+username+"';";
		
		Statement stmnt = null;
		Connection con =SQLConnection.getDBConnection();
		System.out.println(query);
		try {
			stmnt=con.createStatement();
			stmnt.executeUpdate(query);
			con.commit();
			System.out.println("updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateUserDetailsForAdmin(User user, String username){
		String query = "UPDATE USER SET firstname='"+user.getFirstName()+"', "
        		+ "lastname='"+user.getLastName()+"',  "
				+ "email='"+user.getEmail()+"', "
				+ "password='"+user.getPassword()+"', "
				+ "role='"+user.getRole()+"', "
				+ "utaid='"+user.getUtaid()+"', "
				+ "phone='"+user.getPhone()+"',  "
				+ "streetaddress='"+user.getStreetaddress()+"',  "
				+ "city='"+user.getCity()+"', "
				+ "state='"+user.getState()+"',  "
				+ "zipcode='"+user.getZipcode()+"'"
				+ " WHERE username='"+username+"';";

Statement stmnt = null;
Connection con =SQLConnection.getDBConnection();
System.out.println(query);
try {
	stmnt=con.createStatement();
	stmnt.executeUpdate(query);
	con.commit();
	System.out.println("updated");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
	
	public static ArrayList<User>  searchUserwithLname(String lastname)  {  
		return ReturnMatchingUserList("SELECT * from User WHERE lastname LIKE '%"+lastname+"%'");
	}

	public static ArrayList<User>  searchUserwithFname(String firstname)  {  
		return ReturnMatchingUserList(" SELECT * from User WHERE firstname LIKE '%"+firstname+"%'");
	}
	
	public static ArrayList<User>  searchUserwithUid(String utaid)  {  
		return ReturnMatchingUserList(" SELECT * from User WHERE utaid LIKE '%"+utaid+"%'");
	}
	public static ArrayList<User>  searchUserwithLnameOnlyUser(String lastname)  {  
		return ReturnMatchingUserList("SELECT * from User WHERE lastname LIKE '%"+lastname+"%' AND role = 'user'");
	}

	public static ArrayList<User>  searchUserwithFnameOnlyUser(String firstname)  {  
		return ReturnMatchingUserList(" SELECT * from User WHERE firstname LIKE '%"+firstname+"%' AND role = 'user'");
	}
	
	public static ArrayList<User>  searchUserwithUidOnlyUser(String utaid)  {  
		return ReturnMatchingUserList(" SELECT * from User WHERE utaid LIKE '%"+utaid+"%' AND role = 'user'");
	}

	public static ArrayList<User> searchRepToBeAssigned() {
		return ReturnMatchingUserList(" select * from user where role = 'repairer'");
	}
	
}
