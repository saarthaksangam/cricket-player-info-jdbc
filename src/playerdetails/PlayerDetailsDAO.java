package playerdetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sunny
 */
public class PlayerDetailsDAO {

	/**
	 * @param args
	 *            the command line arguments
	 * @throws java.sql.SQLException
	 */
	private Connection myConn;

	public PlayerDetailsDAO() throws SQLException {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teamindia", "root", "root");
		System.out.println("DB Connected succesfully to: " + "jdbc:mysql://localhost:3306/teamindia");
	}

	
	
	private PlayerDetails convertRowToPlayerDetails(ResultSet myRs) throws SQLException {

		String name = myRs.getString("Name");
		String matches = myRs.getString("Matches");
		String runs = myRs.getString("Runs");
		String wickets = myRs.getString("Wickets");
		String centuries = myRs.getString("Centuries");
		String fifties = myRs.getString("Fifties");

		PlayerDetails tempPlayerDetails = new PlayerDetails(name, matches, runs, wickets, centuries, fifties);

		return tempPlayerDetails;
	}

	
	
	
	public List<PlayerDetails> searchPlayerDetails(String name) throws Exception {
		List<PlayerDetails> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name = "%"+name+"%";
			myStmt = myConn.prepareStatement("select * from statistics where Name like ?");

			myStmt.setString(1, name);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				PlayerDetails tempPlayerDetails = convertRowToPlayerDetails(myRs);
				list.add(tempPlayerDetails);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	
	public List<PlayerDetails> getAllPlayerDetails() throws Exception {
		List<PlayerDetails> list = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from statistics");

			while (myRs.next()) {
				PlayerDetails tempPlayerDetails = convertRowToPlayerDetails(myRs);
				//System.out.println(tempPlayerDetails);
				list.add(tempPlayerDetails);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	
	
	
	
	
	
	
	public void addPlayerDetails(PlayerDetails thePlayerDetails) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into statistics"
					+ " (name, matches, runs, wickets, centuries, fifties)" + " values (?, ?, ?, ?, ?, ?)");

			// set params
			myStmt.setString(1, thePlayerDetails.getName());
			myStmt.setString(2, thePlayerDetails.getMatches());
			myStmt.setString(3, thePlayerDetails.getRuns());
			myStmt.setString(4, thePlayerDetails.getWickets());
			myStmt.setString(5, thePlayerDetails.getCenturies());
			myStmt.setString(6, thePlayerDetails.getFifties());

			// execute SQL
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}

	}
	
	
	
	
	
	
	
	public void updatePlayerDetails(PlayerDetails thePlayerDetails) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update statistics"
					+ " set name=?, matches=?, runs=?, wickets=?, centuries=?, fifties=? where name=?");
			
			// set params
			myStmt.setString(1, thePlayerDetails.getName());
			myStmt.setString(2, thePlayerDetails.getMatches());
			myStmt.setString(3, thePlayerDetails.getRuns());
			myStmt.setString(4, thePlayerDetails.getWickets());
			myStmt.setString(5, thePlayerDetails.getCenturies());
			myStmt.setString(6, thePlayerDetails.getFifties());
			myStmt.setString(7, thePlayerDetails.getName());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public void deletePlayerDetails(String name) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from statistics where name=?");
			
			// set param
			myStmt.setString(1, name);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}

	
	
		

	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	



	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {

		}

		if (myConn != null) {
			myConn.close();
		}
	}


	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);		
	}
	
	public static void main(String[] args) throws Exception {
		// TODO code application logic here
		PlayerDetailsDAO dao = new PlayerDetailsDAO();
		System.out.println(dao.searchPlayerDetails("Roh"));

		System.out.println(dao.getAllPlayerDetails());
	}

}
