package com.oracleui.controllers;

import java.io.PrintWriter;
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
			return "oraclelogin";
		}else {
		HttpSession session = request.getSession();
		session.setAttribute("loginerror", "<p>INVALID LOGIN PLEASE CHECK..."+username+"</p>"); 
		return "redirect:/";
		}
	}
	
	@RequestMapping("/uiregister")
	public String register(HttpServletRequest request,HttpServletResponse response) {
		boolean isvalid;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");
		String email = request.getParameter("email");
		isvalid = RegisterService.register(username, password, confirmpassword, email);
		if(isvalid) {
			return "oraclelogin";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/ui")
	public String connectoracle(HttpServletRequest request) {
		boolean isvalid;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Statement st = OracleConnect.getUrl(username, password);
		HttpSession session = request.getSession();
		if(st != null) {
			session.setAttribute("User", username);
			session.setAttribute("Pass", password);
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
	@RequestMapping("/oracleuipage")
	public String uipage() {
		return "oracleui";
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

}
