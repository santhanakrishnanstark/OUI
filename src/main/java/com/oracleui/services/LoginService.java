package com.oracleui.services;

import java.sql.ResultSet;
import java.sql.Statement;

import com.oracleui.dbconnect.OracleConnect;

public class LoginService {

	public static boolean checkUser(String username, String password) {
		try {
		Statement st  = OracleConnect.getUrl("oracleui", "oracleuipass");
		ResultSet rs = st.executeQuery("select * from register where username='"+username+"' and password='"+password+"' ");
		if(rs.next()) {
			OracleConnect.con.close();
			return true; 
		}
		}catch(Exception e) {
			System.out.println("error");
		}
		return false;
	}
	
}
