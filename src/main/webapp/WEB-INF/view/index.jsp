<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); %>
<html>
	<head>
		<title>Oracle UI</title>
		  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Do+Hyeon|Lekton|Open+Sans" rel="stylesheet">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
     <link href="https://fonts.googleapis.com/css?family=Cinzel" rel="stylesheet">
 </head>
 <body>
   
     <header>
        <div class="brand">
            <h1>Oracle UI</h1>
         </div>
     </header>
     
     <div class="row login">
         <div class="col-md-4"></div>
            <div class="col-md-4">
                <!--  Login Form -->
                <% if(session.getAttribute("loginerror")==null){
					}else{	%> ${loginerror}  <% session.removeAttribute("loginerror");   
				}%>
				
             <div class="logindiv">
                 <h3>Login</h3><br>
                 <form action="uilogin" method="post">
                     <div class="form-group">
                        <label for="username" > Username : </label>
                         <input type="text" class="form-control" name="username">
                    </div>
                     <div class="form-group">
                          <label for="password" > Password : </label>
                         <input type="password" class="form-control" name="password">
                     </div> 
                     <input type="submit" id="loginbtn" value="SignIn" class="btn btn-primary">
                </form>
             </div>
                <!--  Register Form -->
                 <div class="registerdiv">
                 <h3>Register</h3><br>
                 <form action="uiregister" method="post">
                     <div class="form-group">
                        <label for="username" > Username : </label>
                         <input type="text" class="form-control" name="username">
                    </div>
                     <div class="form-group">
                          <label for="password" > Password : </label>
                         <input type="password" class="form-control" name="password">
                     </div> 
                     <div class="form-group">
                          <label for="password" >Confirm Password : </label>
                         <input type="password" class="form-control" name="confirmpassword">
                     </div>
                     <div class="form-group">
                        <label for="email" > Email ID : </label>
                         <input type="email" class="form-control" name="email">
                    </div>
                     <input type="submit" id="registerbtn" value="Register" class="btn btn-primary">
                </form>
             </div>
            </div>
         <div class="col-md-4"></div>
     </div>
     
     <footer class="mt-2">
         <div class="men">
            <div id="signin" class="menu">Sign In</div>
            <div id="signup" class="menu">Sign Up</div>
         </div>
        <div class="panel"></div>
     </footer>
     
     <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
   <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
  	<script src="${pageContext.request.contextPath}/js/script.js"></script>
  </body>
</html>
