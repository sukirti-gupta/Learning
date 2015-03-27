<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>
<center><h3>Success</h3>
<c:url value="JerseyLatestWithSpring/j_spring_security_logout" var="logoutUrl" />
<a href="${logoutUrl}"><button>Logout</button></a>
</center>
</body>
</html>