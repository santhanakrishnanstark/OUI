<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%!String tablename; %>
    <% if(session.getAttribute("current_table") != null){
    	 tablename =(String) session.getAttribute("current_table");
     	}
    	%>
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
         <div class="profile">
            <h5> ${User} </h5>
         </div>
     </header>
     
     <div class="row">
        <div class="col-md-3"></div>
         <div class="col-md-9">
             <div class="navpan">
                <ul>
                    <li><a href="#" class="active">Show_Table</a></li>
                    <li><a href="sql">SQL</a></li>
                    <li><a href="insertpage">Insert</a></li>
                    <li><a href="updatepage">Update</a></li>
                    <li><a href="#">Drop</a></li>
                    <li><a href="#">Help</a></li>
                 </ul>
             </div>
         </div>
     </div>
     <div class="row mt-3">
        <div class="col-md-3">
             <div class="sidepan">
	              <div class="sidetop">
	                <h4>Tables</h4>
	                 <h6><a href="#" id="createtablelink"> >> Create Table</a></h6>
	                 </div>
                 <div class="list">
                    <table id="tablelist">
                    	
                    </table>
                 </div>
             </div>
         </div>
         <div class="col-md-9">
           <div id="viewpan">
         	<div id="tablepan"></div>
            <div id="tablecreationpan">
                <h5>Table Creation</h5>
                <div class="tcreationform">
                   <form>
                      <div class="form-group row">
                        <label for="tablename" class="col-sm-2 col-form-label">Table Name :</label>
                        <div class="col-sm-10">
                          <input type="text"  name="tname" id="tname">
                        </div>
                      </div>
                      <div class="form-group row">
                        <label for="tablecolumn" class="col-sm-2 col-form-label">No of Column :</label>
                        <div class="col-sm-10">
                          <input type="number" name="tcol" id="tcol">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2"></label>
                        <div class="col-sm-10">
                          <input type="button" class="btn" id="go" value="Go >>" />
                        </div>
                    </div>
                 </form>
                </div>
            </div>
            <div id="tcreationform2">
            	
            </div>
             <div class="messagebox"><p id="message"></p></div>
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
</body>
</html>