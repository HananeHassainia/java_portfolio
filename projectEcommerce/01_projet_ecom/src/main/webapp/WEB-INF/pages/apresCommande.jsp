<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Commande</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="/template/link.html"%>

</head>
<body id="bodyPanier">
	<%@ include file="/template/headerAccueilInterface.html"%>
	<div class="panierVide">
		<img src="./assets/images/greenWarning.png" />
		<h1 style="color: green;  text-align: center;">${msg2}</h1>
	</div>

	<%@ include file="/template/footer.html"%>

</body>
</html>