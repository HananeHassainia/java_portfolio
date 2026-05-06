<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>modifier Admin Master</title>
<!-- specifier le chemin du fichier css pour le style des forms -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/formStyle.css">

<!-- specifier le chemin du fichier css pour le style de header admin -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/headerAdminStyle.css">
<!-- specifier le chemin du fichier bootstrap.css -->
<link rel="stylesheet" href='<c:url value="/assets/css/bootstrap.css"/>' />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />

</head>
<body
	style="background: url(../assets/images/brownsky.jpg) no-repeat; background-height: 1vh;">
	<%@ include file="/template/headerAdmin.html"%>
	<%@ include file="/template/headerAdminInfos.html"%>
	
	<h1 style="text-align: center; color: white;"><u>Formulaire de modification du
		Master Admin</u></h1>

	<h1 style="color: red">${msg}</h1>
	<h1 style="color: green">${msg1}</h1>

	<div class="forms">
		<form:form class="form-horizontal" method="post"
			action="submitAdminUpdate" modelAttribute="admUpdate">

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="id">ID</form:label>
				<form:input style="width: 60%; padding-left: 10px;" type="number" class="form-control" placeholder="idAdmin"
					path="id" readonly="true" />
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="nom">Nom </form:label>
				<form:input style="width: 60%; padding-left: 10px;" type="text" class="form-control" path="nom"
					placeholder="nom" required="required" />
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="prenom">Prénom</form:label>
				<form:input style="width: 60%; padding-left: 10px;" type="text" class="form-control" placeholder="prenom"
					path="prenom" required="required" />
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="mail">Mail</form:label>
				<form:input style="width: 60%; padding-left: 10px;" type="email" class="form-control"
					placeholder="Adresse-mail" path="mail" required="required" />
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="mdp">Mot de passe</form:label>
				<form:input style="width: 60%; padding-left: 10px;" type="password" class="form-control"
					placeholder="password" path="mdp" required="required" />
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">Active</label> <input style="width: 60%; padding-left: 10px;"
					type="text" class="form-control" name="pActive" value="true"
					readonly="readonly" />
			</div>
			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="role.id">Role Admin</form:label>
				<form:input style="width: 60%; padding-left: 10px;" type="text" class="form-control" value="3"
					readonly="true" path="role.id" placeholder="Role_name" />
			</div>

			<button type="submit" class="btn btn-primary">Modifier</button>

		</form:form>
	</div>

</body>
</html>