<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--  Ajouter la lib core jstl  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc pour le traitement des formulaires  -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter Admin</title>
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
<%@ include file="/template/headerAdmin.html"%>
<%@ include file="/template/headerAdminInfos.html"%>
<body
	style="background: url(../assets/images/brownsky.jpg) no-repeat; background-height: 1vh;">


	<h1 style="color: red">${msg}</h1>
	<h1 style="color: green">${msg1}</h1>

	<div class="forms">
		<h1 style="text-align: center">
		<u>Formulaire d'ajout d'un admin</u>
	</h1>
		<form:form class="form-horizontal" method="post" action="submitAdd"
			modelAttribute="aAdd" style="margin: 70px"
			enctype="multipart/form-data">

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="nom">Nom</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" placeholder="Nom"
						path="nom" />
				</div>
			</div>


			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="prenom">Prenom</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" placeholder="Prenom"
						path="prenom" />
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="mail">Mail</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" placeholder="mail"
						path="mail" />
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="mdp">Mot de passe</form:label>
				<div class="col-sm-10">
					<form:input type="password" class="form-control"
						placeholder="password" path="mdp" />
				</div>
			</div>

			<div class="form-group">
				<div class="form-group">
					<label class="col-sm-2 control-label">Active</label>
					<div>
						<input type="radio" value="true" name="pActive" checked="checked" />
						true <input type="radio" value="false" name="pActive" /> false
					</div>
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="role.id">Type
				d'accès</form:label>
				<form:select path="role.id">
					<form:option value="1">ROLE_ADMIN_PROD</form:option>
					<form:option value="2">ROLE_ADMIN_CAT</form:option>
				</form:select>
			</div>

			<div class="buttonHolder">
				<button type="submit" class="btn btn-success center-block">Ajouter</button>
			</div>

		</form:form>
	</div>

</body>
</html>