
package br.com.skylon.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Crypto {

    public Crypto(){}
    
    public static String toMD5(String str){
        try{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        
        BigInteger hash = new BigInteger(1, md.digest());
        return hash.toString(16).toUpperCase();
        }catch(Exception e){
            e.printStackTrace();
            
            return null;
        }
        
    }
}
