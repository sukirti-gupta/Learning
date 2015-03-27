<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<%
	String errorString = (String) request.getAttribute("error");
	if (errorString != null && errorString.trim().equals("true")) {
		out.println("Incorrect login name or password. Please retry using correct login name and password.");
	}
%>

<body onload='document.f.j_username.focus();'>
	<h3>Login</h3>
	<br>
	<label>${message }</label>
	<a href="/JerseyLatestWithSpring"> Home</a>
	<br>
	<form name="f" method="post"
		action="JerseyLatestWithSpring/j_spring_security_check">
		<label style="color: red">${it}</label> <label>Name</label><input
			type="text" name="j_username"><br> <label>Password</label><input
			type="password" name="j_password"><br>
		<button type="submit">Login</button>
	</form>

	<div id="login-error">${error}</div>
	<c:url var="logoUrl"
		value="/resources/images/150px-Charles_Dickens-A_Christmas_Carol-Cloth-First_Edition_1843.jpg" />
	<p>
		<img src="${logoUrl}"></img>Login with Google:
	</p>
	<c:url var="openIDLoginUrl" value="/j_spring_openid_security_check" />
	<form action="${openIDLoginUrl}" method="post">
		<input name="openid_identifier" type="hidden"
			value="https://www.google.com/accounts/o8/ud" /> <input
			type="submit" value="Sign in with Google" />
	</form>
</body>
</html>