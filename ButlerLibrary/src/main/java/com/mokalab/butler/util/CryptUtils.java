package com.mokalab.butler.util;

import android.util.Log;

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
}