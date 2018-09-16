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


public class InsertRow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Statement st;
    public InsertRow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("User");
		String pass = (String) session.getAttribute("Pass");
		String tablename = (String) session.getAttribute("current_table");
		int row = Integer.parseInt(request.getParameter("row"));

		try {
			st = OracleConnect.getUrl(user, pass);
			ResultSet rscount = st.executeQuery("select count(column_name) from user_tab_columns where table_name = '"+tablename+"' ");
			int count=0;
			if(rscount.next()) {
				count = rscount.getInt("count(column_name)");
			}
			
			String cols[] = new String[count]; int c=0;
			ResultSet rs = st.executeQuery("select column_name from user_tab_columns where table_name = '"+tablename+"' ");
			out.print("<table id='insertb'> <tr>");
			while(rs.next()) {
				cols[c]= rs.getString("column_name");
				out.print("	<th> "+rs.getString("column_name")+" </th>	");
				c++;
			}
			out.print("<tr>");
		/*	// to check the column names within array
			for(int i=0; i<count; i++) {
				System.out.println(cols[i]);
			} */
		
			for(int i=0; i<row; i++) {
				out.print("<tr>");
					for(int j=0; j<count; j++) {
						out.print("<td> <input type='text' class='"+i+"' id='"+i+j+"' > </td>");
					}
				out.print("</tr>");
			}
			out.print("</table>");
			out.print("<br/> <input type='button' class='btn' value='Insert' onClick='  insert("+row+","+count+",\"" + tablename + "\")' />");
			
		}catch(Exception e) {
			out.print(e);
		} 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
