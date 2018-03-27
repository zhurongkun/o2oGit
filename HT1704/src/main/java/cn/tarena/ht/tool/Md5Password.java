package cn.tarena.ht.tool;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Password {
	
	public static String getMd5HashPassword(String password,String userName){
		
		Md5Hash md5Hash = new Md5Hash(password,userName , 3);
		
		return md5Hash.toString();
	}
	
	/*public static void main(String[] args) {
		String password = "admin";
		
		*//**
		 * 1.source 明文
		 * 2.salt   盐
		 * 3.hashIterations  哈希的次数
		 *//*
		Md5Hash md5Hash = new Md5Hash(password, "admin", 3);
		System.out.println(md5Hash);
	}*/
}
