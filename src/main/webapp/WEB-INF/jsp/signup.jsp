<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign up</title>
</head>
<body>
	<center>
		<h3>Signup</h3>
	</center>
	<c:if test="${not empty it.message}">
		<div class="green">${it.message}</div>
	</c:if>
	<form method="post" action="signup">
		<label>Name</label><input type="text" name="name" value="${it.student.name}"><br>
		<label>Password</label><input
			type="password" name="password" value="${it.student.password}"><br>
		<button>Signup</button>
	</form>
</body>
</html>