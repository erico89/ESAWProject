package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@WebServlet("/FileUpload")
@MultipartConfig(
 fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
 maxFileSize = 1024 * 1024 * 10,      // 10 MB
 maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class User implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private String nickname = "";
	private String mail = "";
	private String password = "";
	private String confirmationPassword = "";
	private String name = "";
	private String surname = "";
	private String secondSurname = "";
	private String birthdate = "";
	private String genres[] = {""};
	private String profilePhoto = "";
	
	private boolean[] errors  = 
		{
			false, // Controls if the nickname is already registered on the DataBase.
			false, // Controls if the mail is already registered on the DataBase and matches a specific regular expression.
			false, // Controls if the password has more than seven characters.
			false // Controls if the password and confirmation password matches.
		};
	
	//Constructor
	public User() {
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	//Getters	
	public String getNickname() {
		return this.nickname;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getSecondSurname() {
		return this.secondSurname;
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getConfirmationPassword() {
		return this.confirmationPassword;
	}
	
	public String getBirthdate() {
		return this.birthdate;
	}
	
	public String[] getGenres() {
		return this.genres;
	}
	
	public String getProfilePhoto() {
		return this.profilePhoto;
	}
	
	//Setters
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}
	
	public void setMail(String mail) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			this.mail = mail;
		} else {
			errors[1]=true;
		}
		
	}
	
	public void setPassword(String password) {
		if(password.length() > 7) {
			this.password = password;
		}else {
			errors[2]=true;
		}
	}
	
	public void setConfirmationPassword(String confirmationPassword) {
		if(this.password.equals(confirmationPassword)) {
			this.confirmationPassword = confirmationPassword;
		}else {
			errors[3] = true;
		}
	}
	
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	public void setGenres(String genres[]) {
		this.genres = genres;
	}
	
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	
	//Error Management
	public boolean[] getErrors() {
		return errors;
	}
	
	public void setError(Integer indx, boolean bool) {
		this.errors[indx] = bool;
	}
		
}
