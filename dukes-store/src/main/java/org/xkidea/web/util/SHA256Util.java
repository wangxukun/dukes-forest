package org.xkidea.web.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {

    public static String generateSHA256(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(value.getBytes());

            BigInteger number = new BigInteger(1, messageDigest);
            return number.toString();
        } catch (NoSuchAlgorithmException nsae) {
            return null;
        }
    }
}
