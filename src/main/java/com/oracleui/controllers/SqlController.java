package com.oracleui.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracleui.dbconnect.OracleConnect;

@Controller
public class SqlController {
	Statement st;
	String tablename;
	PrintWriter out;
	@RequestMapping("/showsqlcolumn")
	@ResponseBody
	public void getSqlColumn(HttpServletRequest request,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String uname = (String) session.getAttribute("User");
			String pass = (String) session.getAttribute("Pass");
			tablename = (String) session.getAttribute("current_table");
			st = OracleConnect.getUrl(uname, pass);
			
			ResultSet rscount = st.executeQuery("select count(column_name) from user_tab_columns where table_name = '"+tablename+"' ");
			int count=0;
			if(rscount.next()) {
				count = rscount.getInt("count(column_name)");
			}
			String cols[] = new String[count]; int c=0;
			ResultSet rs = st.executeQuery("select column_name from user_tab_columns where table_name = '"+tablename+"' ");
			
			while(rs.next()) {
				cols[c]= rs.getString("column_name");
				out.print(rs.getString("column_name")+"<br>");
				c++;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/executequery")
	@ResponseBody
	public void executequery(HttpServletRequest request, HttpServletResponse response) {
		try {
			out = response.getWriter();
			String query = request.getParameter("query");
			int res = st.executeUpdate(query);
			ResultSet  rs = st.executeQuery(query);
			if(res>=0) {
				out.println("query executed");
			}else {
				out.print("error with query parameter ");
			}
			
			
		} catch (IOException e) {
			out.println(e);
		} catch (SQLException e) {
			out.println(e);
		}
		
	}
	
	@RequestMapping("/generateinsertquery")
	@ResponseBody
	public void generateInsertQuery(HttpServletRequest request, HttpServletResponse response) {
		try {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("User");
		String pass = (String) session.getAttribute("Pass");
		tablename = (String) session.getAttribute("current_table");
		st = OracleConnect.getUrl(uname, pass);
		StringBuilder query = new StringBuilder();
		query.append("INSERT into "+tablename+" (");
		
		ResultSet rscount = st.executeQuery("select count(column_name) from user_tab_columns where table_name = '"+tablename+"' ");
		int count=0;
		if(rscount.next()) {
			count = rscount.getInt("count(column_name)");
		}
		String cols[] = new String[count]; int c=0;
		ResultSet rs = st.executeQuery("select column_name from user_tab_columns where table_name = '"+tablename+"' ");
		
		while(rs.next()) {
			cols[c]= rs.getString("column_name");
			c++;
		}
		for(int i=0; i<count; i++) {
			query.append(cols[i]+",");
		}
		query.append(")");
		int commaindex = query.indexOf(",", query.length()-2);
		 query.deleteCharAt(commaindex).toString();

		query.append(" VALUES ( ");
		for(int i=1; i<=count; i++) {
			query.append("value"+i+",");
		}
		query.append(" )");
		// to cut the last comma 
		int index_of_comma = query.indexOf(",", query.length()-3);
		String finalquery = query.deleteCharAt(index_of_comma).toString();
		
		out.println(finalquery.toString());
		
		}catch(Exception e) {
			out.println(e);
		}
	}
	
	@RequestMapping("/generateupdatequery")
	@ResponseBody
	public void generateUpdateQuery(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String uname = (String) session.getAttribute("User");
			String pass = (String) session.getAttribute("Pass");
			tablename = (String) session.getAttribute("current_table");
			st = OracleConnect.getUrl(uname, pass);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE "+tablename+" SET ");
			
			ResultSet rscount = st.executeQuery("select count(column_name) from user_tab_columns where table_name = '"+tablename+"' ");
			int count=0;
			if(rscount.next()) {
				count = rscount.getInt("count(column_name)");
			}
			String cols[] = new String[count]; int c=0;
			ResultSet rs = st.executeQuery("select column_name from user_tab_columns where table_name = '"+tablename+"' ");
			
			while(rs.next()) {
				cols[c]= rs.getString("column_name");
				c++;
			}
			for(int i=0; i<count; i++) {
				query.append(cols[i]+" = "+"value"+(i+1)+", ");
			}
			int index_of_comma = query.indexOf(",", query.length()-3);
			 query.deleteCharAt(index_of_comma).toString();
			query.append("WHERE column_name = value");
			String finalquery = query.toString();
			out.println(finalquery);
			
		}catch(Exception e) {
			out.println(e);
		}
	}
	
	@RequestMapping("/generatedeletequery")
	@ResponseBody
	public void generateDeleteQuery(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String uname = (String) session.getAttribute("User");
			String pass = (String) session.getAttribute("Pass");
			tablename = (String) session.getAttribute("current_table");
			st = OracleConnect.getUrl(uname, pass);
			StringBuilder query = new StringBuilder();
			query.append("DELETE "+tablename+" WHERE column_name = value ");
			
			String finalquery = query.toString();
			out.println(finalquery);
			
		}catch(Exception e) {
			out.println(e);
		}
	}
	
	@RequestMapping("/deleterecord")
	@ResponseBody
	public void deleteRecord(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String fields[] = request.getParameterValues("record[]");
			String uname = (String) session.getAttribute("User");
			String pass = (String) session.getAttribute("Pass");
			tablename = (String) session.getAttribute("current_table");
			st = OracleConnect.getUrl(uname, pass);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM "+tablename+" WHERE ");
			ResultSet rscount = st.executeQuery("select count(column_name) from user_tab_columns where table_name = '"+tablename+"' ");
			int count=0;
			if(rscount.next()) {
				count = rscount.getInt("count(column_name)");
			}
			String cols[] = new String[count]; 
			String colsval[] =new String[count]; 
			int c=0;
			ResultSet rs = st.executeQuery("select column_name from user_tab_columns where table_name = '"+tablename+"' ");

			while(rs.next()) {
				cols[c]= rs.getString("column_name");
				c++;
			}       //(checkIsDate(rowarray[i]))?"TO_DATE(\'"+rowarray[i]+"\',\'dd/mm/yyyy\')," :"\'"+rowarray[i]+"\'
			for(int i=0; i<count; i++) {
			     String field =	checkInteger(fields[i])? fields[i] :(checkIsDate(fields[i]))?"TO_DATE(\'"+fields[i]+"\',\'YYYY/MM/DD\')":("\'"+fields[i]+"\'"); 
				query.append(" "+cols[i]+" = "+field+" and");
			}
			int index_of_comma = query.indexOf("and", query.length()-3);
			query.replace(query.length()-3, query.length(), "");
			 //query.deleteCharAt(index_of_comma).toString();
			String finalquery = query.toString();
			System.out.println("Final Query : "+finalquery);
			int res = st.executeUpdate(finalquery);
			//System.out.println(res);
			if(res>=0) { out.print("Deleted"); }
			else { out.println("Error Occured"); }
			
		}catch(Exception e) {
			out.println(e);
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
	
	@RequestMapping("/droptable")
	public void dropTable(HttpServletRequest request,HttpServletResponse response) {
		try {
		out = response.getWriter();
		String table_name = request.getParameter("tablename");
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("User");
		String pass = (String) session.getAttribute("Pass");
		st = OracleConnect.getUrl(uname, pass);
		
			int res = st.executeUpdate("DROP TABLE "+table_name);
			if(res>=0) {
				out.println("Table Dropped");
			}else {
				out.print("Error ! While Dropping Table");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/truncatetable")
	public void truncatetable(HttpServletRequest request,HttpServletResponse response) {
		try {
		out = response.getWriter();
		String table_name = request.getParameter("tablename");
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("User");
		String pass = (String) session.getAttribute("Pass");
		st = OracleConnect.getUrl(uname, pass);
		
			int res = st.executeUpdate("TRUNCATE TABLE "+table_name);
			if(res>=0) {
				out.println("Table Truncate Success");
			}else {
				out.print("Error ! While Truncate Table");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/changepassword")
	public void changePassword(HttpServletRequest request,HttpServletResponse response) {
		try {
			out = response.getWriter();
			String password = request.getParameter("password");
			HttpSession session = request.getSession();
			String uname = (String) session.getAttribute("User");
			String pass = (String) session.getAttribute("Pass");
			st = OracleConnect.getUrl(uname, pass);
			System.out.println(uname+" "+password);
			int res = st.executeUpdate("alter user "+uname+" identified by "+password+" ");
			
			if(res>=0) {
				out.print("Password Changed !");
			}else {
				out.println("Error Occured! ");
			}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
