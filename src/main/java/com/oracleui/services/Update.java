package com.oracleui.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracleui.dbconnect.OracleConnect;


public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Statement st;
    public Update() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuilder query = new StringBuilder();
		String tablename;
		String current_column_name = request.getParameter("currentcolname");
		String current_column_value = request.getParameter("currentcolvalue");
		String primary_column_name = request.getParameter("primarycolname");
		String primary_column_value = request.getParameter("primarycolvalue");
		String user,pass;
		HttpSession session = request.getSession();
		tablename = (String) session.getAttribute("current_table");
		user = (String) session.getAttribute("User");
		pass = (String) session.getAttribute("Pass");
		st = OracleConnect.getUrl(user, pass);
		try {
			query.append("update "+tablename+" set ");
			String currentcolvalue = (checkInteger(current_column_value))? current_column_value:" '"+current_column_value+"'  ";
			query.append(current_column_name +" = "+currentcolvalue);
			query.append(checkInteger(primary_column_value)? " where "+primary_column_name+" = "+primary_column_value+" ":checkIsDate(primary_column_value)?" where "+primary_column_name+" = '"+primary_column_value+"' ": " where "+primary_column_name+" = '"+primary_column_value+"' " );
			System.out.println(query);
			String finalquery = query.toString();
			int updatequery = st.executeUpdate(finalquery);
			if(updatequery>=0) {
				out.println("Record Inserted");
			}else {
				out.println("ERROR in Record Insert");
			}
		}catch(Exception e) {
			out.println(e);
			System.out.println(e);
		}
	}

	private boolean checkIsDate(String string) {
		boolean result = false;
		result = Pattern.matches("[-/0-9]+", string);
	return result;
	}
	// to check it is integer or date format
			private boolean checkInteger(String string) {
				boolean result = false;
					result = Pattern.matches("[0-9]+", string);
				return result;
			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
