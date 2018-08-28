package com.oracleui.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ErrorHandler {
	
	static HttpSession session;
	
	public static void createSession(HttpServletRequest request) {
		 session = request.getSession();
	}
	
	public static void setInvalidAccountError() {
		session.setAttribute("InvalidAccount", "Invalid User/Password !");
	}
	
	public static void removeSession(String sessioname) {
		session.removeAttribute(sessioname); 
	}
	
	public static String getSession(String sessioname) {
		return (String) session.getAttribute(sessioname);
	}

}
