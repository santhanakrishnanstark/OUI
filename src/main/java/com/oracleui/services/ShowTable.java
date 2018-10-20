package com.oracleui.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracleui.dbconnect.OracleConnect;

/**
 * Servlet implementation class ShowTable
 */
public class ShowTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Statement st;
    public ShowTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//to get table name from radio click
		PrintWriter out = response.getWriter();
		String tablename = request.getParameter("table_name");
		if(tablename == null) { tablename = request.getParameter("sessiontb"); }
		try {
		HttpSession session = request.getSession();
		session.setAttribute("current_table", tablename);
		String user = (String) session.getAttribute("User");
		String pass = (String) session.getAttribute("Pass");
		st = OracleConnect.getUrl(user, pass);
		String []fields;  int fieldcount=0;
		ResultSet rscount = st.executeQuery("select count(column_name) from user_tab_columns where table_name = '"+tablename+"' ");
		int count=0;
		if(rscount.next()) {
			count = rscount.getInt("count(column_name)");
		}
		out.print("<table id='dbtable'> ");
		out.print("<tr>");
		String cols[] = new String[count]; int c=0;
		ResultSet rs = st.executeQuery("select column_name from user_tab_columns where table_name = '"+tablename+"' ");
		while(rs.next()) {
			cols[c]= rs.getString("column_name");
			out.print("	<th> "+rs.getString("column_name")+" </th>	");
			fieldcount++; 
			c++;
		}
	/*	// to check the column names within array
		for(int i=0; i<fieldcount; i++) {
			System.out.println(cols[i]);
		} */
		
		out.print("</tr>");
		ResultSet rs1 = st.executeQuery("select * from "+tablename+" ");
		int rowcount=1; int i=1;
		 while(rs1.next()) {
			 out.print("<tr>");
				for(int j=1; j<=fieldcount; j++) {
					String data = (rs1.getObject(j).getClass().getSimpleName().equals("Timestamp")) ?(rs1.getDate(j).toString()) : (rs1.getString(j));
					out.print(" <td class='"+i+"' id='"+i+j+"'>" + data + "</td> ");
			
				}
			 out.print("<td class='"+i+"'><button class='btn btn-sm bg-danger deletebtn text-white' onClick='deleteRecord(this)'>X</button></td>");
			out.print("</tr>");
			rowcount++; i++;
		}
		 out.print("</table>");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
