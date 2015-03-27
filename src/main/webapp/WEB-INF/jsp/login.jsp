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

<body>
	<form name="f" method="post"
		action="JerseyLatestWithSpring/j_spring_security_check">
		<table>
			<tr>
				<td><label style="color: red">${it}</label></td>
			</tr>
			<tr>
				<td><a href="/JerseyLatestWithSpring"> Home</a></td>
			</tr>
		</table>
		<table>
			<thead>
				<tr>
					<td><h3>Login</h3></td>
				</tr>
			</thead>
			<tr>
				<td><label>Name</label></td>
				<td><input type="text" name="j_username" required autofocus></td>
			</tr>
			<tr>
				<td><label>Password</label></td>
				<td><input type="password" name="j_password" required></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Login" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="checkbox" name="remember-me" />
					Remember me</td>
			</tr>
		</table>
	</form>


	<!-- 	<h3>Login</h3> -->
	<!-- 	<br> -->
	<%-- 	<label style="color: red">${it}</label> --%>
	<!-- 	<br> -->
	<!-- 	<a href="/JerseyLatestWithSpring"> Home</a> -->
	<!-- 	<br> -->
	<!-- 	<form name="f" method="post" -->
	<!-- 		action="JerseyLatestWithSpring/j_spring_security_check"> -->
	<!-- 		<label>Name</label><input type="text" name="j_username"><br> -->
	<!-- 		<label>Password</label><input type="password" name="j_password"><br> -->
	<!-- 		<button type="submit">Login</button> -->
	<!-- 	</form> -->

</body>
</html>