package br.blockchain.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ltosc
 */
public class SHAAlgorithm 
{
    public enum SHAType { SHA256 }
    
    public static String apply(String input, SHAType type) 
    {
        String algorithm;
        
        switch (type)
        {
            case SHA256:
                algorithm = "SHA-256";
                break;
            default:
                algorithm = "SHA-256";
        }
        
        try 
        {
            MessageDigest msgDigest = MessageDigest.getInstance(algorithm);
            byte[] hash = msgDigest.digest(input.getBytes("UTF-8"));
            
            StringBuilder hexString = new StringBuilder();
            
            for (int i = 0; i < hash.length; i++) 
            {
                String hex = Integer.toHexString(0xff & hash[i]);
                
                if (hex.length() == 1) 
                {
                    hexString.append('0');
                }
                
                hexString.append(hex);
            }
            
            return hexString.toString();
        } 
        catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) 
        {
            throw new RuntimeException(ex);
        }
    }
}
