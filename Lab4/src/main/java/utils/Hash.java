package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {	
	public String hash_password(String password) throws NoSuchAlgorithmException 
	{
		if (password.isEmpty()) {
			return "";
		}
		
		MessageDigest hash = null;
		try {
			hash = MessageDigest.getInstance("SHA-256");
			byte[] integer_hash = hash.digest(password.getBytes("UTF-8"));
			String result = "";
			for (byte b : integer_hash) {
				String tmp = Integer.toHexString(b & 0xff);
				if (tmp.length() == 1) {
						tmp = "0"+tmp;
				}
				result += tmp;
			}
			return result;
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
