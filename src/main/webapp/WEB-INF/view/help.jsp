<%@page import="java.sql.ResultSet"%>
<%@page import="com.oracleui.dbconnect.OracleConnect"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
     <%  response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); 	%>
    <%!Statement st ; String tablename; 
    %>
    <% if(session.getAttribute("current_table") != null){
    	 tablename =(String) session.getAttribute("current_table");
    	  st = OracleConnect.getUrl(session.getAttribute("User").toString() , session.getAttribute("Pass").toString());
    	
    }
    	%>
<html>
	<head>
		<title>OUI Help</title>
		 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Do+Hyeon|Lekton|Open+Sans" rel="stylesheet">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
     <link href="https://fonts.googleapis.com/css?family=Cinzel" rel="stylesheet">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 </head>
 <% if(session.getAttribute("username")!=null){  %> 
<body>
    <header>
        <div class="brand">
            <h1>Oracle UI</h1>
         </div>
         <div class="profile">
            <h5> ${User} </h5> 
            <div class="usermenu">
				<div class="usermenu-icon"> 
				<i class="fa fa-user-circle usericon" aria-hidden="true"></i>
				</div>
				 <ul class="ouimenu">
				    <li onClick="showUAccount()">User Account</li>
				     <li onClick="chcurPassword()">Change Password</li>
				     <li><a href="logout">Logout</a></li>
				  </ul>
				</div>
         </div>
     </header>
     
     <div class="row">
        <div class="col-md-3"></div>
         <div class="col-md-9">
             <div class="navpan">
                <ul>
                    <li><a href="oracleuipage">Show_Table</a></li>
                    <li><a href="sql">SQL</a></li>
                    <li><a href="insertpage">Insert</a></li>
                    <li><a href="updatepage">Update</a></li>
                    <li><a href="drop">Drop</a></li>
                    <li><a href="#" class="active">Help</a></li>
                 </ul>
             </div>
         </div>
     </div>
     <div class="row mt-3">
        <div class="col-md-3">
             <div class="sidepan">
	              <div class="sidetop">
	                <h4>Tables</h4>
	                 <h6><a href="oracleuipage" id="createtablelink"> >> Create Table</a></h6>
	                 </div>
                 <div class="list">
                    <table id="tablelist">
                    	
                    </table>
                 </div>
             </div>
         </div>
         <div class="col-md-9">
           <div id="viewpan" class="p-5">
           		<div id="changepasspan" class="pt-5">
           			<div class="form-row text-center">
           				<div class="col-6">
           					<h6>Enter New Password : </h6>
           				</div>
           				<div class="col-6">
           					<input type="text" class="form-control" id="newpass">
           				</div>
           				<div class="col-3"></div>
           			</div>
           			<div class="form-row text-center mt-2">
           				<div class="col-6">
           					<h6>Confirm Password : </h6>
           				</div>
           				<div class="col-6">
           					<input type="text" class="form-control" id="confirmnewpass">
           					<span id="passerror"></span>
           				</div>
           				<div class="col-3"></div>
           			</div>
           			<div class="form-row mt-2">
           				<div class="col-6"></div>
           				<div class="col-6">
           					<input type="button" class="btn btn-primary" value="Change" id="changepassbtn">
           				</div>
           			</div>
           		</div>
           		
           		<div id="useraccount">
           			<div class="row">
           				<div class="col-4">
           					<div class="card" style="width: 18rem;">
							  <div class="card-body">
							    <h5 class="card-title">${User}</h5>
							    <h6 class="card-subtitle mb-2 text-muted">current user</h6>
							    <p class="card-text">No of Tables : ${Tables} </p>
							    <p class="card-text">No of Views : ${Views} </p>
							  </div>
							</div>
           				</div>
           				<div class="col-4">
           					<div class="card" style="width: 18rem;">
							  <div class="card-body">
							    <h5 class="card-title">User Privilages</h5>
							    <h6 class="card-subtitle mb-2 text-muted">current user</h6>
							    <% ResultSet rs = st.executeQuery("select * from user_sys_privs "); 
							    	while(rs.next()){
							    		%><p class="card-text"><%=rs.getString("PRIVILEGE") %></p>
							     <%
							    	}
							    %>
							  </div>
							</div>
           				</div>
           				<div class="col-4">
           					<div class="card" style="width: 18rem;">
							  <div class="card-body">
							    <h5 class="card-title">User Role Privilages</h5>
							    <h6 class="card-subtitle mb-2 text-muted">current user</h6>
							    <% ResultSet rs1 = st.executeQuery("select * from user_role_privs "); 
							    	while(rs1.next()){
							    		%><p class="card-text"><%=rs1.getString("GRANTED_ROLE") %></p>
							     <%
							    	}
							    %>
							  </div>
							</div>
           				</div>
           			</div>
           		</div>
           		
           </div>
         </div>
       </div>
     
     <footer class="mt-2">
     </footer>
     
     <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
     <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
  	<script src="${pageContext.request.contextPath}/js/script.js"></script>
  	<script src="${pageContext.request.contextPath}/js/script2.js"></script>
 	<script>
 	var tbname = "<%=tablename%>";
 	$('[value="<%=tablename%>"]').prop('checked', true); 
 	</script>
 	<%} else{ out.print("No Session is Created"); } %>
</body>
</html>