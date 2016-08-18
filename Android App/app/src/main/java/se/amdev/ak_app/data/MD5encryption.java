package se.amdev.ak_app.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Martin on 2016-08-17.
 */
public class MD5encryption {

    public static String encrypt(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < byteData.length; j++)
            sb.append(Integer.toString((byteData[j] & 0xff) + 0x100, 16).substring(1));

        return sb.toString();
    }
}
