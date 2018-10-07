<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%!String tablename; %>
    <% if(session.getAttribute("current_table") != null){
    	 tablename =(String) session.getAttribute("current_table");
     	}
    	%>
<html>
	<head>
		<title>OUI Update</title>
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
         <div class="profile">
            <h5> ${User} </h5>
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
                    <li><a href="#" class="active">Drop</a></li>
                    <li><a href="help">Help</a></li>
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
         <div class="col-md-9 text-center">
           <div id="viewpan" class="p-5">
            <form id="dropfrm">
              <div class="form-row m-5">
	              <div class="col-1">
	              	<span></span>
	              	</div>
	              	<div class="col-4">
	              		<select id="tablenames" name="tablename" class="form-control">
	              		   <option>Select Table</option>
	           			</select>
	              	</div>
	              	<div class="col-2">
	              		<button class="btn btn-danger form-control" id="dropbtn">Drop</button>
	              	</div>
	              	<div class="col-1"> <span>or</span> </div>
	              	<div class="col-2">		
	              		<button class="btn btn-warning form-control" id="truncatebtn">Truncate</button>
	              	</div>
              </div>
             </form> 
           </div>
         </div>
       </div>
     
     <footer class="mt-2">
     </footer>
     
     <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
     <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
  	<script src="${pageContext.request.contextPath}/js/script.js"></script>
  	<script src="${pageContext.request.contextPath}/js/script2.js"></script>
  	<script src="${pageContext.request.contextPath}/js/drop.js"></script>
 	<script>
 	var tbname = "<%=tablename%>";
 	$('[value="<%=tablename%>"]').prop('checked', true); 
 	</script>
</body>
</html>