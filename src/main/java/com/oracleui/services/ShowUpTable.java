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
 * Servlet implementation class ShowUpTable
 */
public class ShowUpTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Statement st;
    public ShowUpTable() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String tablename = request.getParameter("table_name");
		if(tablename == null) { tablename = request.getParameter("stb"); }
		try {
		HttpSession session = request.getSession();
		session.setAttribute("current_table", tablename);
		String user = (String) session.getAttribute("User");
		String pass = (String) session.getAttribute("Pass");
		st = OracleConnect.getUrl(user, pass);
		/* to Check the table contain primary key column */
			ResultSet primarycol=st.executeQuery("SELECT cols.table_name, cols.column_name FROM all_constraints cons, all_cons_columns cols WHERE cols.table_name = '"+tablename+"' AND cons.constraint_type = 'P' AND cons.constraint_name = cols.constraint_name AND cons.owner = cols.owner ORDER BY cols.table_name, cols.position ");
			if(primarycol.next()) {
				
			
		/* end of checking primary key column*/
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
		int rowcount=1;
		 int i=1;
		 while(rs1.next()) { 
			 out.print("<tr>");
				for(int j=1; j<=fieldcount; j++) {
					String data = (rs1.getObject(j).getClass().getSimpleName().equals("Timestamp")) ?(rs1.getDate(j).toString()) : (rs1.getString(j));
					out.print(" <td><input type='text' onfocus='getTableData(this)' onfocusout='updateRecord()' class='"+i+"' id='"+i+j+"' value='"+data+"'> </td> ");
			
				}
			out.print("</tr>");
			rowcount++; i++;
		}
		 out.print("</table>");
			}else { out.print("<h6 class='m-5 text-secondary'>Sorry This table does not contain primary column</h6>");}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
