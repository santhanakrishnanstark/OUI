package com.oracleui.services;

import java.sql.Statement;
import com.oracleui.dbconnect.OracleConnect;

public class RegisterService {
	
	public static boolean register(String username, String password, String confirmpassword, String email) {
		try {
			Statement st  = OracleConnect.getUrl("oracleui", "oracleuipass");
			int res = st.executeUpdate("insert into register values('"+username+"','"+password+"','"+confirmpassword+"','"+email+"') ");
			if(res>=1) {
				return true;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
