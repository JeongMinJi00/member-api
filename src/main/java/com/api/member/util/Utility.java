package com.api.member.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Utility {
	
	public static String SaltPassword() { 
		String salt=""; 
		
		try { 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); 
			byte[] bytes = new byte[16]; 
			random.nextBytes(bytes); 
			salt = new String(Base64.getEncoder().encode(bytes)); 
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace(); 
		} 
		return salt; 
	}
	
	public static String SHA512Password(String saltNum, String password) { 
		String salt = saltNum + password; 
		String hex = null; 
		
		try { 
			MessageDigest msg = MessageDigest.getInstance("SHA-512"); 
			msg.update(salt.getBytes()); 
			hex = String.format("%128x", new BigInteger(1, msg.digest())); 
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace(); 
		} 
		
		return hex; 
	}
	
	public static String nvl(String str) {
		if(str == null || "".equals(str)) {
			return "";
		} else {
			return str;
		}
	}
}
