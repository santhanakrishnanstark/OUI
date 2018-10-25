package com.oracleui.controllers;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracleui.dbconnect.OracleConnect;
import com.oracleui.services.ErrorHandler;
import com.oracleui.services.LoginService;
import com.oracleui.services.RegisterService;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showMyPage() {
		return "index";
	}
	
	@RequestMapping("/oui")
	public String showOuiPage() {
		return "oraclelogin";
	}
	
	@RequestMapping("/uilogin")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		boolean loginaccess = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		loginaccess = LoginService.checkUser(username,password);
		if(loginaccess) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			return "oraclelogin";
		}else {
		HttpSession session = request.getSession();
		session.setAttribute("loginerror", "<div class=\"arrow\"> < </div>\r\n" + 
				"							 <div class=\"errormessage\">\r\n" + 
				"								     <p>Invalid username/password</p>\r\n" + 
				"							  </div>"); 
		return "redirect:/";
		}
	}
	
	@RequestMapping("/uiregister")
	public void register(HttpServletRequest request,HttpServletResponse response) {
		 PrintWriter out=null;
		try { 
		boolean isvalid;  out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");
		String email = request.getParameter("email");
		isvalid = RegisterService.register(username, password, confirmpassword, email);
		if(isvalid) {
			out.println("User Registered Successful");
			HttpSession session = request.getSession();
			session.setAttribute("loginerror", "<div class=\"sarrow\"> < </div>\r\n" + 
					"							 <div class=\"successmessage\">\r\n" + 
					"								     <p style='color:green'>User Registered Successful, Login</p>\r\n" + 
					"							  </div>");
		}else {
			out.print("Error Orrcured !");
			HttpSession session = request.getSession();
			session.setAttribute("loginerror", "<div class=\"arrow\"> < </div>\r\n" + 
					"							 <div class=\"errormessage\">\r\n" + 
					"								     <p>Invalid Credential</p>\r\n" + 
					"							  </div>");
		
		}
		}catch(Exception e) {
			out.println(e);
		}
		
	}
	
	@RequestMapping("/ui")
	public String connectoracle(HttpServletRequest request) throws SQLException {
		boolean isvalid;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Statement st = OracleConnect.getUrl(username, password);
		HttpSession session = request.getSession();
		if(st != null) {
			session.setAttribute("User", username);
			session.setAttribute("Pass", password);
			ResultSet rs = st.executeQuery("select count(TNAME) from tab where tabtype='TABLE' ");
			if(rs.next()) { session.setAttribute("Tables", rs.getInt(1)); }
			ResultSet rs1 = st.executeQuery("select count(TNAME) from tab where tabtype='VIEW' ");
			if(rs.next()) { session.setAttribute("Views", rs.getInt(1)); }
			return "oracleui";
		}else {
			session.setAttribute("loginerror", "<p>INVALID LOGIN </p>"); 
		}
		
		return "redirect:oui";
	}
	
	@RequestMapping("/insertpage")
	public String insertpage() {
		return "insert";
	}
	@RequestMapping("/updatepage")
	public String updatepage() {
		return "update";
	}
	@RequestMapping("/oracleuipage")
	public String uipage() {
		return "oracleui";
	}
	@RequestMapping("/sql")
	public String getSql() {
		return "sql";
	}
	@RequestMapping("/drop")
	public String getDropage() {
		return "drop";
	}
	@RequestMapping("/help")
	public String getHelp() {
		return "help";
	}
	
	@RequestMapping("/getprimarycolumn")
	public void getPrimaryColumn(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out = null;  Statement st = null;
		try {
			out = response.getWriter();
			HttpSession session = request.getSession();
			String tablename = request.getParameter("table_name");
			if(tablename == null) { tablename = request.getParameter("stb"); }
			String user = (String) session.getAttribute("User");
			String pass = (String) session.getAttribute("Pass");
			st = OracleConnect.getUrl(user, pass);
			/* to Check the table contain primary key column */
				ResultSet primarycol=st.executeQuery("SELECT cols.table_name, cols.column_name,cols.position FROM all_constraints cons, all_cons_columns cols WHERE cols.table_name = '"+tablename+"' AND cons.constraint_type = 'P' AND cons.constraint_name = cols.constraint_name AND cons.owner = cols.owner ORDER BY cols.table_name, cols.position ");
				if(primarycol.next()) {
					out.println(primarycol.getString("position"));
					System.out.println("Primary column : "+primarycol.getString("column_name"));
				}else { System.out.println("no primary cloumn available");}
			/* end of checking primary key column*/
			
		}catch(Exception e) {
			out.println(e);
		}
	}
	
	@RequestMapping("/showquery")
	@ResponseBody
	public void showQuery(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			PrintWriter out = response.getWriter();
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
			out.println(cquery);
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		try {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
	    Statement st = OracleConnect.getUrl("oracleui", "oracleuipass");
	    st.executeUpdate("update ouilogs set logout=current_timestamp where username='"+username+"' and sno=(select max(sno) from ouilogs) ");
		 session.removeAttribute("username");
		 session.invalidate();
		 OracleConnect.con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/checkusername")
	public void checkusername(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("running");
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("uname");
		Statement st = OracleConnect.getUrl("oracleui", "oracleuipass");
		ResultSet rs = st.executeQuery("select * from register where username ='"+uname+"' ");
		if(rs.next()) {
			out.print(true);
		}else { out.print(false); }
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
}
