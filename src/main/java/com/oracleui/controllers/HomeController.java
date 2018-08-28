package com.oracleui.controllers;

import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
