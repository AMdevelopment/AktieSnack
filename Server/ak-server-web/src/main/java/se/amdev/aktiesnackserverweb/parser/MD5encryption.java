package se.amdev.aktiesnackserverweb.parser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5encryption {

	public static String encrypt(String password) throws NoSuchAlgorithmException {

		String passwordToReturn = null;

		for (int i = 0; i < 123; i++) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (i == 0) {
				md.update(password.getBytes());
			}
			else {
				md.update(passwordToReturn.getBytes());
			}
			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < byteData.length; j++) {
				sb.append(Integer.toString((byteData[j] & 0xff) + 0x100, 16).substring(1));
			}
			passwordToReturn = sb.toString();
		}

		return passwordToReturn;
	}
}
