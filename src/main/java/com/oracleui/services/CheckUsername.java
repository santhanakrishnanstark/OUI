package com.oracleui.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracleui.dbconnect.OracleConnect;

public class CheckUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Statement st;
    public CheckUsername() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("running");
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("uname");
		st = OracleConnect.getUrl("oracleui", "oracleuipass");
		ResultSet rs = st.executeQuery("select * from register where username ='"+uname+"' ");
		if(rs.next()) {
			out.print(true);
		}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
