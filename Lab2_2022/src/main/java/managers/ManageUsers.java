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
			String birthDate,  String photo) {
		String query = "INSERT INTO users (user,name,surname,surname2,mail,pwd,birthDate,photo) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user);
			statement.setString(2,name);
			statement.setString(3,surname);
			statement.setString(4,surname2);
			statement.setString(5,mail);	
			statement.setString(6,pwd1);
			statement.setString(7,birthDate);
			statement.setString(8,photo);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Add user_genders relation
	public void addGender(String name, String gender) throws SQLException {
		String query1 = "SELECT id FROM users as u where u.user = '" + name + "'";
		ResultSet userId = runQuery(query1);
		
		String query2 = "SELECT id FROM genders as g where gender = '" + gender + "'";
		ResultSet genderId = runQuery(query2);

		if (userId.next() && genderId.next()) {
			String query3 = "SELECT id FROM users_genders WHERE user_id = '" + userId.getInt("id") + "' AND gender_id = '" + genderId.getInt("id") + "'";
			ResultSet rs = runQuery(query3);
			
			// If not exist, Create
			if (!rs.next()) {
				String query4 = "INSERT INTO users_genders (user_id, gender_id) VALUES (?, ?)";
				PreparedStatement statement = null;
				try {
					statement = db.prepareStatement(query4);
					statement.setInt(1, userId.getInt("id"));
					statement.setInt(2, genderId.getInt("id"));
					statement.executeUpdate();
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
	public boolean checkUsername(String user, User model) throws SQLException {
        ResultSet usr = db.prepareStatement("select user from users WHERE user = '" + user +"'").executeQuery();
        if(usr.next()) {
            model.setError(0,true);
        }
        return usr.next();
    }
    
    public boolean checkMail(String mail, User model) throws SQLException {
        ResultSet mil= db.prepareStatement("select mail from users WHERE mail = '" + mail +"'").executeQuery();
        if(mil.next()) {
            model.setError(1,true);
        }
        return mil.next();
    }
    
	
	private ResultSet runQuery (String query) {
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = db.prepareStatement(query);
			result = statement.executeQuery();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
