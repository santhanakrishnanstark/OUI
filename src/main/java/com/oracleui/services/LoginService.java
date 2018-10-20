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
			int res = st.executeUpdate("insert into ouilogs(sno,username,ldate,login) values (ouilogseq.nextval,'"+username+"',sysdate,current_timestamp)");
			OracleConnect.con.close();
			return true; 
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
}
