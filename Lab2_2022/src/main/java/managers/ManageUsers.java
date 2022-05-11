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
	public void addUser(String user, String mail, String pwd1, String name, String surname, String surname2,
			Date birthDate,  String photo) {
		String query = "INSERT INTO users (user,name,surname,surname2,mail,pwd1,birthDate,photo) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user);
			statement.setString(2,name);
			statement.setString(2,surname);
			statement.setString(3,surname2);
			statement.setString(4,mail);	
			statement.setString(5,pwd1);
			statement.setDate(6,(java.sql.Date) birthDate);//sino podemos usar la clase time de java
			statement.setString(7,photo);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addGender(List<String> gender) {
		
		String query = "INSERT INTO users_genders (user_id,gender_id) VALUES (?,?)";
		
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
