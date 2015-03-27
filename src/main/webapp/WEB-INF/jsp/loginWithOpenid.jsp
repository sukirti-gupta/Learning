<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="login-error">${error}</div>
	<c:url var="logoUrl"
		value="/resources/images/150px-Charles_Dickens-A_Christmas_Carol-Cloth-First_Edition_1843.jpg" />
	<p>
		<img src="${logoUrl}"></img>Login with Google:
	</p>
	<c:url var="openIDLoginUrl" value="/j_spring_openid_security_check" />
	<form action="${openIDLoginUrl}" method="post">
		<input name="openid_identifier" type="hidden"
			value="https://www.google.com/accounts/o8/id" /> <input type="submit"
			value="Sign with Google" />
	</form>

</body>
</html>