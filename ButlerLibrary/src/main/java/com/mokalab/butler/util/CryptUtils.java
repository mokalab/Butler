package com.mokalab.butler.util;


import org.jetbrains.annotations.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Artur Shamsi on 2014-10-03.
 */
public class CryptUtils {

    /**
     * Returns String encrypted with XOR and given char key
     * @param input
     * @param key
     * @return
     */
    public static String encryptWithXOR(String input, char key) {
        StringBuilder encryptedString = null;

        try {
            encryptedString = new StringBuilder().append("");
            int xor;
            char temp;

            for (int i = 0; i < input.length(); i++) {
                xor = input.charAt(i) ^ key;
                temp = (char)xor;
                encryptedString = encryptedString.append(temp);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return encryptedString !=null ? encryptedString.toString() : null;
    }// end method encryptWithXOR

    /**
     * Returns String decrypted with XOR and given char key
     * @param encryptedString
     * @param key
     * @return
     */
    public static String decryptWithXOR(String encryptedString, char key) {
        StringBuilder decryptedString = null;
        if (encryptedString != null) {
            try {
                decryptedString = new StringBuilder().append("");
                int xor;
                char temp;

                for (int i = 0; i < encryptedString.length(); i++) {
                    xor = encryptedString.charAt(i) ^ key;
                    temp = (char)xor;
                    decryptedString = decryptedString.append(temp);

                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return decryptedString != null ? decryptedString.toString(): null;
    }//end method decryptWithXOR

    /**
     * Gets encoded with Base64 XOR encrypted String
     * @param input
     * @param key
     * @return
     */
    public static String encodeEncryptedString(String input, char key) {
        return input != null ? getBase64Encoded(encryptWithXOR(input, key).getBytes()) : null;
    }//end encodeEncryptedString method

    /**
     * Decodes String with Base64 and decrypts it with XOR
     * @param input
     * @param key
     * @return
     */
    public static String decodeAndDecryptString(String input, char key) {
        String base64Decoded = getBase64Decoded(input);
        return base64Decoded != null ? decryptWithXOR(base64Decoded, key) : null;
    }//end decodeAndDecryptString method

    /**
     * Returns Base64 encoded String
     * @param input
     * @return
     */
    private static String getBase64Encoded(byte [] input) {
        String encoded = null;
        try {
            if (input != null) {
                encoded = Base64.encode(input);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return encoded;
    }//end getBase64Encoded method

    /**
     * Returns Base64 decoded String
     * @param input
     * @return
     */
    private static String getBase64Decoded(String input) {
        byte [] decodedBytes = null;
        String decodedString = null;
        try {
            if (input != null) {
                decodedBytes = Base64.decode(input.getBytes());
                decodedString = new String(decodedBytes);
            }
        } catch (Base64DecoderException b64de) {
            b64de.printStackTrace();
        }
        return  decodedString;
    }//end getBase64Decoded method

    /**
     * Generates and returns an MD5 Hash of the provided input String.
     * If there were errors, it will return null.
     * @param input any String data
     * @return MD5 encoded hash
     */
    @Nullable
    public static String md5(String input) {

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}