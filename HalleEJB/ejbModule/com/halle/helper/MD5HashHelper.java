package com.halle.helper;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Classe responsável por gerar o hash.
 * 
 * @author lbaiao
 * @version 1.0 (09/07/2016)
 * 
 */
public class MD5HashHelper {
	
	
    public static String generateHash(String value) throws Exception {
    	
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
     
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	
    	return hexString.toString();
    }
    
    public static String generatePassword() throws Exception {
    	final String value = String.valueOf( new Random().nextInt(8) );
    	return MD5HashHelper.generateHash(value).substring(0, 6);
    }
    
    public static String generateToken() throws Exception {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:S");
		final Random rand = new Random();
		final String data = sdf.format(new Date());
		final String md5Hash = data + (rand.nextInt(9999));
		
    	return MD5HashHelper.generateHash(md5Hash).substring(0, 7); 	
    }
    
    public static void main(String[] args) throws Exception {
    	System.out.println( MD5HashHelper.generateHash("leonardo"));
	}
}
