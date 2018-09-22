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


public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Statement st;  PrintWriter out;
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		int row = Integer.parseInt(request.getParameter("row"));
		int field = Integer.parseInt(request.getParameter("field"));
		HttpSession session = request.getSession();
		String tablename = (String) session.getAttribute("current_table");
		//out.println("<script>alert('Success')</script>");
		
		 String[] record = new String[field]; //int d=0;
		  for(int i=0; i<row; i++) {
			  String rowarray[] =  request.getParameterValues("records["+i+"][]");
			/* recently added*/  generateQuery(rowarray,tablename,request);
			  for(int j=0; j<field; j++) {
				  System.out.print(rowarray[j]);
			  }
			  System.out.println();
		  }
	}

	
	private void generateQuery(String[] rowarray, String tablename,HttpServletRequest request) {
		System.out.println("Inside Generate Query");
		StringBuilder query = new StringBuilder();
		query.append("insert into "+tablename+" values ( ");
		for(int i=0; i<rowarray.length; i++) {
			query.append((checkInteger(rowarray[i]))?rowarray[i]+",":(checkIsDate(rowarray[i]))?"TO_DATE(\'"+rowarray[i]+"\',\'dd/mm/yyyy\')," :"\'"+rowarray[i]+"\',");
		}
		query.append(" )");
		// to cut the last comma 
		int index_of_comma = query.indexOf(",", query.length()-3);
		String finalquery = query.deleteCharAt(index_of_comma).toString();
		
		System.out.println(finalquery.toString());
		startInsertProcess(finalquery,request);
		
	}
	private boolean checkIsDate(String string) {
		boolean result = false;
		result = Pattern.matches("[-/0-9]+", string);
	return result;
	}

	private void startInsertProcess(String finalquery, HttpServletRequest request) {
		System.out.println("Inside Start Insert process");
		int insertrecordcount=0, errorcount=0;
		try {
			HttpSession session = request.getSession();
			String uname = (String) session.getAttribute("User");
			String pass = (String) session.getAttribute("Pass");
			st = OracleConnect.getUrl(uname, pass);
			int insertquery = st.executeUpdate(finalquery);
			if(insertquery>=0) {
				insertrecordcount++;
				out.println("Record Inserted");
			}else {
				out.println("ERROR in Record Insert");
			}
		}catch(Exception e) {
			String error = e.getMessage();
			out.println(error);
		}
	}
	
	// to check it is integer or date format
		private boolean checkInteger(String string) {
			boolean result = false;
				result = Pattern.matches("[0-9]+", string);
			return result;
		}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
