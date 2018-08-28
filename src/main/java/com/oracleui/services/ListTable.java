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

import org.json.JSONObject;

import com.oracleui.dbconnect.OracleConnect;

/**
 * Servlet implementation class ListTable
 */
public class ListTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Statement st;
	ResultSet rs;
	
    public ListTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("User");
		String pass = (String) session.getAttribute("Pass");
		st = OracleConnect.getUrl(user, pass);
		
		try {
			rs = st.executeQuery("select rownum,tname from tab");
			while(rs.next()) {
				String rnum = rs.getString("rownum");
				String tname = rs.getString("tname");
				obj.put(rnum,tname);
			}
			out.println(obj);
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
