<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<% response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); %>
<html>
	<head>
		<title>Oracle UI</title>
		  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Do+Hyeon|Lekton|Open+Sans" rel="stylesheet">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
     <link href="https://fonts.googleapis.com/css?family=K2D" rel="stylesheet">
     <link href="https://fonts.googleapis.com/css?family=Chakra+Petch" rel="stylesheet">
 </head>
 <body>
   
     <header>
        <div class="brand">
        <div class="baxmaxhead">
        	<div class="head">
			    <div class="left-eye eye"></div>
			    <div class="eye-connect"></div>
			    <div class="right-eye eye"></div>
		   </div>
		  </div>
            <h1>Oracle UI</h1>
         </div>
     </header>
     
     <div class="row login">
         <div class="col-md-4"></div>
            <div class="col-md-4">
                <!--  Login Form -->
                <% if(session.getAttribute("loginerror")==null){
					}else{	%>
					     ${loginerror}
					
					  <% session.removeAttribute("loginerror");   
				}%>
			<!-- Bay Max -->	
				<div class="wrapper">
				  <div class="head">
				    <div class="left-eye eye"></div>
				    <div class="eye-connect"></div>
				    <div class="right-eye eye"></div>
				  </div>
				  <div class="torso">
				    <div class="access-port">
				      <div class="access-port-seam"></div>
				    </div>
				    <div class="right-torso-pad torso-pad"></div>
				    <div class="left-torso-pad torso-pad"></div>
				  </div>
				  <div class="right-arm">
				    <div class="right-thumb"></div>
				    <div class="right-index"></div>
				    <div class="right-index right-finger-2"></div>
				    <div class="right-index right-finger-3"></div>
				  </div>
				  <div class="left-arm">
				    <div class="left-thumb"></div>
				    <div class="left-index"></div>
				    <div class="left-index left-finger-2"></div>
				    <div class="left-index left-finger-3"></div>
				  </div>
				  <div class="right-leg">
				    <div class="right-thigh-pad thigh-pad"></div>
				    <div class="foot-pad"></div>
				  </div>
				  <div class="left-leg">
				    <div class="left-thigh-pad thigh-pad"></div>
				    <div class="foot-pad"></div>
				  </div>
				</div>
			<!-- Bay Max end -->	
             <div class="logindiv mt-5">
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
                 <form id="registerfrm" method="post">
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
