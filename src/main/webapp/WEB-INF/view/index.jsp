<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Oracle UI</title>
		<link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
</head>
<body>
	<header class="container uibrand">
		<div class="d-inline brand ">
			<span class="o">O</span><span>racle</span> <span class="ui"> UI </span>
		</div>	
	</header>
	
	<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div clas="card">
					<div class="card-body">
						<ul>
							<a href="#"><span class="card-title logintitle">Login</span></a>
							<a href="#"><span class="card-title logintitle">Signup</span></a>
						</ul>
						<div id="login">
							<form action="UiLogin">
							<input name="username" class="form-control" placeholder="enter username..." type="text"/>
							<input name="password" class="form-control" placeholder="enter password..." type="password"/>
							<input type="submit" id="connect" class="form-control" value="Connect" id="submit"/>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
