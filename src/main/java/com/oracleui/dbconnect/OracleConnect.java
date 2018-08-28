package com.oracleui.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class OracleConnect {
	public static Connection con;
	public static Statement getUrl(String uname,String pass) {
		Statement st = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",uname,pass); 
				st = con.createStatement();
			
		}catch(Exception e) {
			st = null;
		}
		return st;
	}
}
