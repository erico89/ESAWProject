package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

@WebServlet("/FileUpload")
@MultipartConfig(
 fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
 maxFileSize = 1024 * 1024 * 10,      // 10 MB
 maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class User implements java.io.Serializable {
	
	/*
	 CREATE TABLE `users` (
  		`usr` varchar(255) NOT NULL,
  		`mail` varchar(255) NOT NULL,
  		`pwd` varchar(255) NOT NULL,
  		PRIMARY KEY (`usr`),
  		UNIQUE KEY `mail` (`mail`)
	 ); 
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String user = "";
	private String mail = "";
	private String pwd1 = "";
	private String pwd2 = "";
	private String name = "";
	private String surname = "";
	private String surname2 = "";
	private String birthDate = "";
	private String genders[];
	private String photo;
	
	private boolean[] error  = {false,false,false,false};
	
	public User() {
		
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		/* We can simulate that a user with the same name exists in our DB and mark error[0] as true  */
		// sin un manager no podemos hacerlo :(
		//error[0] = true;
		this.user = user;
		System.out.println(user);
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public void setMail(String mail) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			this.mail = mail;
			System.out.println(mail);
		} else {
			error[1]=true;
			System.out.println(mail);
		}
		
	}
	
	public String getPwd1() {
		return this.pwd1;
	}
	
	public void setPwd1(String pwd1) {
		if(pwd1.length() > 7) {
			this.pwd1 = pwd1;
		}else {
			error[2]=true;
		}
		System.out.println(pwd1);
	}
	
	public String getPwd2() {
		return this.pwd2;
	}
	
	public void setPwd2(String pwd2) {
		if(this.pwd1.equals(pwd2)) {
			this.pwd2 = pwd2;
		}else {
			error[3] = true;
		}
		System.out.println(pwd2);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
		System.out.println(name);
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
		System.out.println(surname);
	}
	
	public String getSurname2() {
		return this.surname2;
	}
	
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
		System.out.println(surname2);
	}
	
	public String getBirthDate() {
		return this.birthDate;
	}
	
	public void setBirthDate(String birthdate) {
		this.birthDate = birthdate;
		System.out.print(birthdate);
	}
	
	public String[] getGenders() {
		return this.genders;
	}
	
	public void setGenders(String gender[]) {
		this.genders = gender;
		//System.out.print(musicgenders);
	}
	
	public String getPhoto() {
		return this.photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
		//System.out.print(photo);
	}
	
	public boolean[] getError() {
		return error;
	}
		
}
