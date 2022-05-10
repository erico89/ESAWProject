package managers;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Part;

import models.User;
import utils.DB;

public class ManageUsers {
	
	private DB db = null ;
	
	public ManageUsers() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
		
	// Add new user
	public void addUser(String username, String mail, String pwd, String name, String surname, String secondsurname,
			Date birthdate, List<String> musicgenders, Part photo) {
		String query = "INSERT INTO users (user,name,surname,surname2,mail,pwd1,birthDate,gender,photo) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,username);
			statement.setString(2,name);
			statement.setString(2,surname);
			statement.setString(3,secondsurname);
			statement.setString(4,mail);
			statement.setString(5,pwd);
			statement.setDate(6,(java.sql.Date) birthdate);//sino podemos usar la clase time de java
			statement.setArray(7,(Array) musicgenders);
			//statement.setPart(8,photo);
			//statement.setObject(8, photo);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Check if all the fields are filled correctly */
	public boolean isComplete(User user) {
	    return(hasValue(user.getUser()) &&
	    	   hasValue(user.getMail()) &&
	    	   hasValue(user.getPwd1()) &&
	           hasValue(user.getPwd2()) &&
	           hasValue(user.getName()) &&
	           hasValue(user.getSurname()) );
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
		
	
	// TODO: add other methods 
	private boolean checkUsername(String user) throws SQLException {
		ResultSet usr = db.prepareStatement("select user from users WHERE user = '" + user +"'").executeQuery();
		return usr.next();
	}

}
