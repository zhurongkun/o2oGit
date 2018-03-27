package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.SQLException;

import org.junit.Test;

import com.mysql.jdbc.Driver;

public class Demo {
	@Test
	public void demo2() throws Exception{
//		String a ="";
//		System.out.println();
//		Class c =Class.forName("com.jdbc.mysql.Driver");
//		new Driver();
		String str ="";
		Class clz = str.getClass();
		 Method[] dd = clz.getDeclaredMethods();
		for (int i = 0; i < dd.length; i++) {
			System.out.println(dd);
		}
		
	}

}
