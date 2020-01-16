package maintenance_system.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import maintenance_system.model.MarSys;
import maintenance_system.model.User;
import maintenance_system.util.SQLConnection;

public class MarSysDAO {
	
static SQLConnection DBMgr = SQLConnection.getInstance();
public static final int MYSQL_DUPLICATE_PK = 1062;
public static boolean isDuplicate = false;
private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
static Date date = new Date();
	private static ArrayList<MarSys> ReturnMatchingMarSysList(String queryString) {
		ArrayList<MarSys> marsysListInDB = new ArrayList<MarSys>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet marsysList = stmt.executeQuery(queryString);
		while (marsysList.next()) {
			MarSys mar_sys = new MarSys(); 
	
			mar_sys.setFacility_type(marsysList.getString("facility_type"));
			mar_sys.setFacility_name(marsysList.getString("facility_name"));
			mar_sys.setUrgency(marsysList.getString("urgency"));
			mar_sys.setDescription(marsysList.getString("description"));
			mar_sys.setReported_by(marsysList.getString("reported_by"));
			mar_sys.setDate_value(marsysList.getString("date_value"));
			mar_sys.setMar_number(marsysList.getString("mar_number"));
			mar_sys.setAssignedTo(marsysList.getString("assigned_to"));
			mar_sys.setAssignedDate(marsysList.getString("assigned_date"));
			mar_sys.setEstimate(marsysList.getString("estimate"));
			marsysListInDB.add(mar_sys);
		}
	} 
		catch (SQLException e) {
		e.printStackTrace();
} finally {
		try {
		conn.close();
		stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
		
	return marsysListInDB;
	}
	
		private static void StoreListinDB (MarSys mar_sys,String queryString) {
		String result = "";
		Statement stmt = null;
		isDuplicate = false;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertMarSys = queryString + " VALUES ('"  
					+ mar_sys.getFacility_type() + "','"
					+ mar_sys.getFacility_name() + "','"
					+ mar_sys.getUrgency() + "','"
					+ mar_sys.getDescription() + "','"
					+ mar_sys.getReported_by() + "','"
					+ sdf.format(date) + "','"
					+ mar_sys.getMar_number()+"','"
					+ mar_sys.getAssignedTo() + "','"
					+ sdf.format(date)+"','"
					+ mar_sys.getEstimate() + "')";
			
			System.out.println(insertMarSys);
			stmt.executeUpdate(insertMarSys);
			conn.commit(); 
			System.out.println("Success: " +insertMarSys);
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

	private static MarSys getMarSys(String queryString){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		MarSys mar_sys = new MarSys();
		System.out.println(queryString);
	try {
		stmt = conn.createStatement();
		ResultSet marsysList = stmt.executeQuery(queryString);
		while (marsysList.next()) { 
			mar_sys.setMarSys(marsysList.getString("facility_type"), marsysList.getString("facility_name"), marsysList.getString("urgency"), marsysList.getString("description"), marsysList.getString("reported_by"), marsysList.getString("date_value"), marsysList.getString("mar_number"), marsysList.getString("assignedTo"), marsysList.getString("assignedDate"), marsysList.getString("estimate"));
			System.out.println(mar_sys.getFacility_name()+ " -> Role= "+ mar_sys.getFacility_type());
			return mar_sys;
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
	
	public static void insertMAR_SYS(MarSys mar_sys) {  
		StoreListinDB(mar_sys,"INSERT INTO MAR_SYS (facility_type,facility_name,urgency,description,reported_by,date_value,mar_number,assigned_to,assigned_date,estimate) ");
	} //insert into column names
	
	
	public static void getMarByMarNumber(String marSelected, String repSelected, String mydate, String estimatetime, String urgency){

		String query = "UPDATE MAR_SYS SET assigned_to='"+repSelected+"' WHERE mar_number='"+marSelected+"';";
		String query1= "UPDATE MAR_SYS SET assigned_date='"+sdf.format(date)+"' WHERE mar_number='"+marSelected+"';";
		String query2= "UPDATE MAR_SYS SET estimate='"+estimatetime+"' WHERE mar_number='"+marSelected+"';";
		String query3= "UPDATE MAR_SYS SET urgency='"+urgency+"' WHERE mar_number='"+marSelected+"';";
		
		Statement stmnt = null;
		Statement stmnt1 = null;
		Statement stmnt2 = null;
		Statement stmnt3 = null;
		Connection con =SQLConnection.getDBConnection();
		System.out.println(query);
		System.out.println(query1);
		System.out.println(query2);
		System.out.println(query3);
		try {
			stmnt=con.createStatement();
			stmnt.executeUpdate(query);
			stmnt1=con.createStatement();
			stmnt1.executeUpdate(query1);
			stmnt2=con.createStatement();
			stmnt2.executeUpdate(query2);
			stmnt3=con.createStatement();
			stmnt3.executeUpdate(query3);
			con.commit();
			System.out.println("updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static void getAssigned_dateValue(String date) {
//		
//		}
///////////////////////.....new code......//////////////////////////////////////////////////////	
	public static MarSys getMarSysByMarNumber(String marNumber){
		MarSys mar_sys = null;
		
		Statement  statement = null;
		Connection con = SQLConnection.getDBConnection();
		try {
			
			statement = con.createStatement();
			String getMarSys = "SELECT * FROM MAR_SYS WHERE mar_number='"+marNumber+"'";
			
			System.out.println(getMarSys);
			ResultSet result = statement.executeQuery(getMarSys);
			
			while(result.next()){
				mar_sys = new MarSys();
				mar_sys.setFacility_type(result.getString("facility_type"));
				mar_sys.setFacility_name(result.getString("facility_name"));
				mar_sys.setUrgency(result.getString("urgency"));
				mar_sys.setDescription(result.getString("description"));
				mar_sys.setReported_by(result.getString("reported_by"));
				mar_sys.setDate_value(result.getString("date_value"));
				mar_sys.setMar_number(result.getString("mar_number"));
				mar_sys.setAssignedTo(result.getString("assigned_to"));
				mar_sys.setAssignedDate(result.getString("assigned_date"));
				mar_sys.setEstimate(result.getString("estimate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mar_sys;
	}
	
	
	

	public static void updateMarDetails(MarSys mar_sys){
		String query = "UPDATE MAR_SYS SET facility_type='"+mar_sys.getFacility_type()+"', "
		        		+ "facility_type='"+mar_sys.getFacility_name()+"',  "
						+ "urgency='"+mar_sys.getUrgency()+"', "
						+ "description='"+mar_sys.getDescription()+"', "
						+ "reported_by='"+mar_sys.getReported_by()+"', "
						+ "date_value='"+mar_sys.getDate_value()+"', "
						+ "mar_number='"+mar_sys.getMar_number()+"',  "
						+ "assigned_to='"+mar_sys.getAssignedTo()+"',  "
						+ "assigned_date='"+mar_sys.getAssignedDate()+"', "
						+ "estimate='"+mar_sys.getEstimate()+"',";
		
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
	
	public static void updateMarAssignedRepairer(MarSys mar_sys){
		String query = "UPDATE MAR_SYS SET urgency='"+mar_sys.getUrgency()+"', "
						+ "assigned_to='"+mar_sys.getAssignedTo()+"',  "
						+ "assigned_date='"+mar_sys.getAssignedDate()+"', "
						+ "estimate='"+mar_sys.getEstimate()+"'"
						+ " where mar_number='"+mar_sys.getMar_number()+"'";
		
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
	
	public static ArrayList<MarSys> searchMAR() {
		return ReturnMatchingMarSysList(" SELECT * from MAR_SYS WHERE assigned_to ='null' ");
	}
	
	public static ArrayList<MarSys> Repairer() {
		return ReturnMatchingMarSysList(" SELECT * from USER WHERE ROLE IS REPAIRER ");
	}
	
	public static ArrayList<MarSys> searchAssignedMAR() {
		return ReturnMatchingMarSysList(" SELECT * from MAR_SYS WHERE assigned_to !='null' ");
	}
	
	
}
