<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/headerAdminStyle.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/accesrefuse.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />
	

<title>Nom site</title>
</head>
<body style="background: url(../assets/images/pexels-photomix-company-96622.jpg) no-repeat; background-height: 1vh;">
<%@ include file="/template/headerAdmin.html"%>

	<!-- <h1 style="color:red; text-align: center; padding:20px; border:red solid 2px;">VOUS N'ETES PAS AUTORISE A ACCEDER A CETTE PAGE</h1> -->


	
	<a href="${pageContext.request.contextPath}/prod/prodHome"><span > VOUS N'ETES PAS AUTORISE A ACCEDER A CETTE
			PAGE </span></a>

</body>
</html>