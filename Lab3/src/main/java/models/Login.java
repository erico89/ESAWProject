package models;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DB;
import utils.Hash;

public class Login {

	private String user = ""; //Nickname or mail
	private String password = "";
	private String error = "";
	private DB db = null ;
	
	//Create the DB client
	public Login() {
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
	
	public String getUser(){
		return user;
	}
	
	public void setUser(String user){
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) throws NoSuchAlgorithmException{
		Hash hash = new Hash();
		this.password = hash.hash_password(password);
	}
	
	public String getError() {
		return error;
	}
	
	public boolean isComplete() {
		boolean bool = hasValue(getUser()) && hasValue(getPassword());
	    return(bool);
	}
	
	//Check if the credentials match
  	public boolean checkCredentials() throws SQLException {
        ResultSet usr = db.prepareStatement("SELECT * FROM users WHERE (nickname = '" + getUser() +"' OR mail = '" + getUser()  + "') AND password = '"+ getPassword() +"'").executeQuery();
		boolean bool = usr.next();
        if (!bool) {
			this.error = "User or Password incorrect.";
		}
        setUser(usr.getString("nickname"));
        return bool;
    }
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
}