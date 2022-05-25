package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DB;

public class Login {

	private String user = "";
	private String password = " ";
	private int[] error = {0};
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
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public int[] getError() {
		return error;
	}
	
	public boolean isComplete() {
	    return(hasValue(getUser()) && hasValue(getPassword()));
	}
	
	//Check if the credentials match
  	public boolean checkCredentials() throws SQLException {
          ResultSet usr = db.prepareStatement("SELECT * FROM users WHERE nickname = '" + getUser() +"' AND password = '"+ getPassword() +"'").executeQuery();
          ResultSet usr2 = db.prepareStatement("SELECT * FROM users WHERE mail = '" + getUser() +"' AND password = '"+ getPassword() +"'").executeQuery();
          if(usr2.next()) {//If the user introduced the mail, we change it for the nickname
        	  user = usr2.getString("nickname");
        	  return true;
          }
		return(usr.next());
      }
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
}