package com.oracleui.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.oracleui.dbconnect.OracleConnect;


public class CreateTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Statement st;
    public CreateTable() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String tablename = request.getParameter("tablename");
			int cols = Integer.parseInt(request.getParameter("column"));
			System.out.println("Table Name : "+tablename);
			StringBuilder createquery;
			createquery = new StringBuilder("create table "+tablename+ "( "); 
			
			String colarray[] = new String[cols+1];
			for(int i=0; i<cols; i++) {
				// first add length of type number
				int len = (request.getParameter("length"+i)).equals("")?11 :Integer.parseInt(request.getParameter("length"+i));
				String type;
				if(! request.getParameter("type"+i).equalsIgnoreCase("date")) {
					 type =request.getParameter("type"+i)+"("+len+")";
					System.out.println("Its not date : "+type);
				}else {
					type =  request.getParameter("type"+i);
					System.out.println("Its Date : "+type);
				}
				
				String primary = request.getParameter("p"+i);
				if(primary==null) {primary=""; } // if the primary is uncheck then the value is none
			
				String unique = request.getParameter("u"+i);
				if(unique==null) {unique=""; }
				
				String notnull = request.getParameter("n"+i);
				if(notnull==null) {notnull=""; }
				
				colarray[i]= " "+request.getParameter("column"+i)+" "+type+" "+primary+" "+unique+" "+notnull+",";
			}
			// generate query
			String query=""; 
			for(int i=0; i<cols; i++) {
				query +=colarray[i];
			}
			query += " )";
			
			createquery.append(query);
			int index_of_comma = createquery.indexOf(",", createquery.length()-4);
			String cquery = createquery.deleteCharAt(index_of_comma).toString();
			System.out.println(cquery);
			HttpSession session = request.getSession();
			String uname = (String) session.getAttribute("User");
			String pass = (String) session.getAttribute("Pass");
			st = OracleConnect.getUrl(uname, pass);
			int res= st.executeUpdate(cquery);
			if(res>=0) {
				out.println("Table Created Successfully");
			}else {
				out.println("Table Not Created");
			}
			
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
