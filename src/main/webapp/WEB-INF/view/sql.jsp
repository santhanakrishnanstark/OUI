<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%!String tablename; %>
    <% if(session.getAttribute("current_table") != null){
    	 tablename =(String) session.getAttribute("current_table");
     	}
    	%>
<html>
	<head>
		<title>OUI Sql</title>
		 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Do+Hyeon|Lekton|Open+Sans" rel="stylesheet">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css">
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
                    <li><a href="#" class="active">SQL</a></li>
                    <li><a href="insertpage">Insert</a></li>
                    <li><a href="updatepage">Update</a></li>
                    <li><a href="drop">Drop</a></li>
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
         <div class="col-md-9">
           <div id="viewpan">
           	 <div class="row p-5">
         		<div id="sqlarea" class="col-md-8">
         			<h5 class="ml-2">SQL Query Area</h5>
         			<textarea id="sqltxt" rows="10" cols="" class="form-control"></textarea>
         		</div>
         		<div id="columnarea" class="col-md-1"></div>
         		<div id="columnarea" class="col-md-3">
         			<h6 class="text-center">Column Names</h6>
         			<div id="coltxt" class="form-control" style="height: 18rem;"></div>
         		</div>
         	 </div>
         	 <div class="row mt-2 pl-5">
         	     <div class="col-md-8">
		         	   <div>
		         	 	<button id="insertbtn" type="button" class="btn btn-outline-primary mr-3">Insert</button>
		         	 	<button id="updatebtn" type="button" class="btn btn-outline-primary mr-3">Update</button>
		         	 	<button id="deletebtn" type="button" class="btn btn-outline-primary mr-3">Delete</button>
		         	 	<button id="clearbtn" type="button" class="btn btn-outline-primary mr-3">Clear</button>
		         	   </div>
	         	  </div>
	         	  <div class="col-md-1"></div>
	         	  <div class="col-md-3">
	         	  	  <button id="runbtn" type="button" class="btn btn-outline-primary">RUN</button>
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
  	<script src="${pageContext.request.contextPath}/js/sql.js"></script>
 	<script>
 	var tbname = "<%=tablename%>";
 	$('[value="<%=tablename%>"]').prop('checked', true); 
 	</script>
</body>
</html>