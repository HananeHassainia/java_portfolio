<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Ajouter la libs core de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>|| Ajout catégorie</title>
<!-- specifier le chemin du fichier css pour le style des forms -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/formStyle.css">

<!-- specifier le chemin du fichier css pour le style de header admin -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/headerAdminStyle.css">

<!-- specifier le chemin du fichier bootstrap.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />

</head>
<body
	style="background: url(../assets/images/brownsky.jpg) no-repeat; background-height: 1vh;">

	<%@ include file="/template/headerAdmin.html"%>
	<%@ include file="/template/navCat.html"%>
	
	<h1 style="color: white; text-align: center">
		<u>Formulaire d'ajout d'une catégorie</u>
	</h1>

	<h1 style="color: red">${msg}</h1>
	<h1 style="color: green">${msg1}</h1>

	<div class="forms">
		<form:form class="form-horizontal" method="post" action="submitAddCat"
			modelAttribute="catAdd" style="margin: 15px;"
			enctype="multipart/form-data">

			<div class="form-group">
				<form:label path="nomCategorie">Catégorie</form:label>
				<form:input type="text" class="form-control"
					placeholder="Nom de la catégorie" path="nomCategorie"
					required="required" />
			</div>

			<div class="form-group">
				<form:label path="description">Description</form:label>
				<form:input type="text" class="form-control"
					placeholder="description" path="description" />
			</div>

			<div class="form-group">
				<label>Photo</label> <input style="width: 100%; padding-left: 10px;"
					type="file" class="form-control" name="pPhoto" />
			</div>

			<button type="submit" class="btn btn-success center-block">Ajouter</button>
			
		</form:form>
	</div>

</body>
</html>