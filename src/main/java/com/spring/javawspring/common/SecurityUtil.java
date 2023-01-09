package com.spring.javawspring.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {  //public으로 쓰고
	public static String encryptSHA256(String str){  //static 으로 하면 class이름 쓰고 아무곳이나 이걸 쓸수 있다!!
    String sha = "";
    try{
       MessageDigest sh = MessageDigest.getInstance("SHA-256");
       sh.update(str.getBytes());
       byte byteData[] = sh.digest();
       StringBuffer sb = new StringBuffer();
       for(int i = 0 ; i < byteData.length ; i++){
           sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
       }
       sha = sb.toString();
   }catch(NoSuchAlgorithmException e){
       System.out.println("Encrypt Error - NoSuchAlgorithmException");
       sha = null;
   }
   return sha;
 } 
}
