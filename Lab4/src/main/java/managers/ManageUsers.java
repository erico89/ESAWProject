package managers;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

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
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	// Get a user given its id, 
	public User getUser(Integer id) {
		String query = "SELECT user_id,nickname,mail,birthdate,password,name,surname,second_surname,profile_photo FROM users WHERE user_id = ? ;";
		ResultSet rs = null;
		User user = null;
		PreparedStatement statement = null;
		
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setNickname(rs.getString("nickname"));
				user.setMail(rs.getString("mail"));
				user.setBirthdate(rs.getString("birthdate"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setSecondSurname(rs.getString("second_surname"));
				user.setProfilePhoto(rs.getString("profile_photo"));
				// TO DO: M¨¢s parametros para devolver.
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return user;

	}
	
	public int numFollowing(Integer id) {
		int numFollowing = 0;
		String query = "SELECT COUNT(u.user_id) AS numFollowing FROM users u LEFT OUTER JOIN followers f ON u.user_id = f.follower_id WHERE u.user_id = ? ;";
		ResultSet rs = null;
		PreparedStatement statement = null;
		
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			rs = statement.executeQuery();
			if (rs.next()) {
				numFollowing = rs.getInt("numFollowing");	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return numFollowing;
	}
	
	public int numFollowers(Integer id) {
		int numFollowers = 0;
		String query = "SELECT COUNT(f.user_id) AS numFollowers FROM followers f LEFT OUTER JOIN users u ON f.user_id = u.user_id WHERE u.user_id = ? ;";
		ResultSet rs = null;
		PreparedStatement statement = null;
		
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			rs = statement.executeQuery();
			if (rs.next()) {
				numFollowers = rs.getInt("numFollowers");	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return numFollowers;
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
		String query1 = "SELECT user_id FROM users WHERE nickname = '" + nickname + "'";
		ResultSet userID = runQuery(query1);
		
		String query2 = "SELECT genre_id FROM genres WHERE genre = '" + genre + "'";
		ResultSet genreID = runQuery(query2);
		

		if (userID.next() && genreID.next()) {
			String query3 = "SELECT id FROM users_genres WHERE user_id = '" + userID.getInt("user_id") + "' AND genre_id = '" + genreID.getInt("genre_id") + "'";
			ResultSet rs = runQuery(query3);
			
			// If not exist, Create
			if (!rs.next()) {
				String query4 = "INSERT INTO users_genres (user_id, genre_id) VALUES (?, ?)";
				PreparedStatement statement = null;
				try {
					statement = db.prepareStatement(query4);
					statement.setInt(1, userID.getInt("user_id"));
					statement.setInt(2, genreID.getInt("genre_id"));
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
	
	// Action: Follow user
	public void followUser(Integer uid, Integer fid) {
		String query = "INSERT INTO followers (user_id,follower_id) VALUES (?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,fid);
			statement.setInt(2,uid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Action: Unfollow user
	public void unfollowUser(Integer uid, Integer fid) {
		String query = "DELETE FROM followers WHERE user_id = ? AND follower_id = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,fid);
			statement.setInt(2,uid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Get All users
	// Get not followed users, TO DO: necesitamos m¨¢s datos a parte de id y name
	public List<User> getAllUsers(Integer start, Integer end) {
		 String query = "SELECT * FROM users "
		 		+ " ORDER BY nickname LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,start);
			 statement.setInt(2,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("user_id"));
				 user.setNickname(rs.getString("nickname"));
				 user.setProfilePhoto(rs.getString("profile_photo"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	// Get not followed users, TO DO: necesitamos m¨¢s datos a parte de id y name
	public List<User> getNotFollowedUsers(Integer id, Integer start, Integer end) {
		 String query = "SELECT * FROM users "
		 		+ " WHERE user_id NOT IN ("
		 		+ "	SELECT u.user_id FROM users as u, followers as f"
		 		+ " WHERE u.user_id = f.user_id AND f.follower_id = ?"
		 		+ ") "
		 		+ " AND user_id <> ?"
		 		+ " ORDER BY nickname LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2, id);
			 statement.setInt(3,start);
			 statement.setInt(4,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("user_id"));
				 user.setNickname(rs.getString("nickname"));
				 user.setProfilePhoto(rs.getString("profile_photo"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public List<User> getNotFollowedUsersKeyWord(Integer id, String keyWord,Integer start, Integer end) {
		 String query = "SELECT * FROM users "
		 		+ " WHERE user_id NOT IN ("
		 		+ "	SELECT u.user_id FROM users as u, followers as f"
		 		+ " WHERE u.user_id = f.user_id AND f.follower_id = ?"
		 		+ ") "
		 		+ " AND user_id <> ?"
		 		+ " AND nickname LIKE ? "
		 		+ " ORDER BY nickname LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 String words = "%" + keyWord + "%";
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2, id);
			 statement.setString(3, words);
			 statement.setInt(4,start);
			 statement.setInt(5,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("user_id"));
				 user.setNickname(rs.getString("nickname"));
				 user.setProfilePhoto(rs.getString("profile_photo"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	
	// Get followed Users
	public List<User> getFollowedUsers(Integer id, Integer start, Integer end) {
		 String query = "SELECT * FROM users u JOIN followers f ON u.user_id = f.user_id WHERE f.follower_id = ? ORDER BY nickname LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("user_id"));
				 user.setNickname(rs.getString("nickname"));
				 user.setProfilePhoto(rs.getString("profile_photo"));
				 user.setMail(rs.getString("mail"));
				 user.setName(rs.getString("name"));
				 user.setSurname(rs.getString("surname"));
				 user.setBirthdate(rs.getString("birthdate"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	// Get followed Users
		public List<User> getFollowedUsersKeyWord(Integer id, String keyWord, Integer start, Integer end) {
			 String query = "SELECT * FROM users u JOIN followers f ON u.user_id = f.user_id WHERE f.follower_id = ? AND u.nickname LIKE ? ORDER BY nickname LIMIT ?,?;";
			 PreparedStatement statement = null;
			 List<User> l = new ArrayList<User>();
			 try {
				 String words = "%" + keyWord + "%";
				 statement = db.prepareStatement(query);
				 statement.setInt(1,id);
				 statement.setString(2, words);
				 statement.setInt(3,start);
				 statement.setInt(4,end);
				 ResultSet rs = statement.executeQuery();
				 while (rs.next()) {
					 User user = new User();
					 user.setId(rs.getInt("user_id"));
					 user.setNickname(rs.getString("nickname"));
					 user.setProfilePhoto(rs.getString("profile_photo"));
					 user.setMail(rs.getString("mail"));
					 user.setName(rs.getString("name"));
					 user.setSurname(rs.getString("surname"));
					 user.setBirthdate(rs.getString("birthdate"));
					 l.add(user);
				 }
				 rs.close();
				 statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			return  l;
		}
	
	public Pair<Boolean,User> checkLogin(User user) {

		String query = "SELECT * FROM users WHERE (nickname = ? OR mail = ?) AND password = ?";
		PreparedStatement statement = null;
		boolean output = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user.getNickname());
			statement.setString(2,user.getMail());
			statement.setString(3,user.getPassword());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("user_id"));
				user.setMail(rs.getString("mail"));
				user.setNickname(rs.getString("nickname"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setBirthdate(rs.getString("birthdate"));
				user.setProfilePhoto(rs.getString("profile_photo"));
				output = true;
			} 
			rs.close();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Pair.of(output,user);
		
	}
	
	public boolean isLoginComplete(User user) {
	    return((hasValue(user.getNickname()) || hasValue(user.getMail())) &&
	    	   hasValue(user.getPassword()) );
	}

}
