package com.iktpreobuka.esdnevnik.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryption {
	
	public static String getPassEncoded(String pass) {
		BCryptPasswordEncoder bCryptPasswordEncoder =
		new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(pass);
		}
	
	
	public static boolean validatePasswprd(String pass, String EncodePass) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(pass, EncodePass);
	}
	
		public static void main(String[] args) {
			System.out.println(getPassEncoded("pass"));
			}


		
}
