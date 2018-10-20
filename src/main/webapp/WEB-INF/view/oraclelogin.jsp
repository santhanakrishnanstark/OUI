<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
      <%  response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); %> 
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
 <% if(session.getAttribute("username")!=null){ %> 
<body>
     <header>
        <div class="brand">
            <h1>Oracle UI</h1>
         </div>
     </header>
     
     <div class="row login mt-5">
         <div class="col-md-4"></div>
            <div class="col-md-4"></div> 
         <div class="col-md-3">
             <!--  Login Form -->
                <% if(session.getAttribute("loginerror")==null){
					}else{	%> ${loginerror}  <% session.removeAttribute("loginerror");   
				}%>
             <div class="logindiv showlogin">
                 <h3>Oracle Login</h3><br>
                 <form action="ui" method="post">
                     <div class="form-group">
                        <label for="username" > Username : </label>
                         <input type="text" class="form-control" name="username">
                    </div>
                     <div class="form-group">
                          <label for="password" > Password : </label>
                         <input type="password" class="form-control" name="password">
                     </div> 
                     <input type="submit" value="SignIn" class="btn btn-primary">
                </form>
             </div>
         </div>
          <div class="col-md-1"></div> 
     </div>
     
       
     <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
     <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
  	<script src="${pageContext.request.contextPath}/js/script.js"></script>
 <%} else{ out.print("No Session is Created"); } %>
</body>
</html>