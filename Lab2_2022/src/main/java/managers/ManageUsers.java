package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
import utils.DB;

public class ManageUsers {
	
	private DB db = null ;
	
	//Create the DB client
	public ManageUsers() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Shut down connection with the client
	public void shutDownConnection() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
		
	// Add a new user in the users table
	public void addUser(String nickname, String name, String surname, String secondSurname, String mail, 
			String password, String birthdate, String profilePhoto) 
	{
		
		//Set the query
		String query = "INSERT INTO users (nickname,name,surname,second_surname,mail,password,birthdate,profile_photo) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		
		try {
			//Fill the query
			statement = db.prepareStatement(query);
			statement.setString(1,nickname);
			statement.setString(2,name);
			statement.setString(3,surname);
			statement.setString(4,secondSurname);
			statement.setString(5,mail);	
			statement.setString(6,password);
			statement.setString(7,birthdate);
			statement.setString(8,profilePhoto);
			
			//Execute the query
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Add a new relation between the user and the genre in the relational table users_genres
	public void addGenres(String nickname, String genre) throws SQLException {
		String query1 = "SELECT id FROM users WHERE nickname = '" + nickname + "'";
		ResultSet userID = runQuery(query1);
		
		String query2 = "SELECT id FROM genres WHERE genre = '" + genre + "'";
		ResultSet genreID = runQuery(query2);
		

		if (userID.next() && genreID.next()) {
			String query3 = "SELECT id FROM users_genres WHERE user_id = '" + userID.getInt("id") + "' AND genre_id = '" + genreID.getInt("id") + "'";
			ResultSet rs = runQuery(query3);
			
			// If not exist, Create
			if (!rs.next()) {
				String query4 = "INSERT INTO users_genres (user_id, genre_id) VALUES (?, ?)";
				PreparedStatement statement = null;
				try {
					statement = db.prepareStatement(query4);
					statement.setInt(1, userID.getInt("id"));
					statement.setInt(2, genreID.getInt("id"));
					statement.executeUpdate();
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}			
 
	}
	
	//Check if all the fields are filled correctly
	public boolean isComplete(User user) {
	    return(hasValue(user.getNickname()) &&
	    	   hasValue(user.getName()) &&
	    	   hasValue(user.getSurname()) &&
	    	   hasValue(user.getMail()) &&
	    	   hasValue(user.getPassword()) &&
	           hasValue(user.getConfirmationPassword())
	           );
	}
	
	//Validate a value
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
		
	
	// Validate register form
	public boolean isValidForm(User user) {
		boolean[] errors = user.getErrors();
		
		boolean result = true;
		for (boolean error : errors) {
			result = result && !error;
		}
	    return(result);
	}
	
	//Check if the nickname is already registered on the DataBase (error 0)
	public void checkUsername(String nickname, User model) throws SQLException {
        ResultSet usr = db.prepareStatement("SELECT nickname FROM users WHERE nickname = '" + nickname +"'").executeQuery();
        if(usr.next()) {
            model.setError(0,true);
        }
    }
    
	//Check if the mail is already registered on the DataBase (error 1)
    public void checkMail(String mail, User model) throws SQLException {
        ResultSet mil= db.prepareStatement("SELECT mail FROM users WHERE mail = '" + mail +"'").executeQuery();
        if(mil.next()) {
            model.setError(1,true);
        }
    }
    
	//Execute a query and manage exceptions
	private ResultSet runQuery (String query) {
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = db.prepareStatement(query);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
