<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--  ajouter la lib core de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--  ajouter la lib form de spring mvc  -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creer Compte Client</title>


<!--  specifier le chemin du fichier bootstrap.css -->
<link rel="stylesheet"
	href='<c:url value ="/assets/css/bootstrap.css"/>' />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />
</head>
<body>

	<h1 style="color: purple; text-align: center;">Formulaire Creation
		Compte Client</h1>

	<div style='width: 50%; text-align: center; margin-left: 25%'>
		<form:form class="form-horizontal" method="post" action="submitAdd"
			modelAttribute="cltAdd">

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="mail">Mail: </form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" placeholder="mail"
						path="mail" />
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="mdp">Password:</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" placeholder="Password"
						path="mdp" />
				</div>
			</div>



			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="nom">Nom: </form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" placeholder="Nom"
						path="nom" />
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="prenom">Prenom:</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" placeholder="Prenom"
						path="prenom" />
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="adresse">Adresse: </form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" placeholder="Adresse"
						path="adresse" />
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="tel">Tel:</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control"
						placeholder="Telephone" path="tel" />
				</div>
			</div>

			<div class="form-group" style="visibility: hidden">
				<form:label class="col-sm-2 control-label" path="active"
					style="visibility: hidden">Active: </form:label>
				<div class="col-sm-10">
					<form:input type="boolean" class="form-control" value="true"
						path="active" />
				</div>
			</div>

			<input style="display:none;"class="form-control" value="${CommandeNvxClient}" name="pCommande" />

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Ajouter</button>
				</div>
			</div>
		</form:form>
	</div>
	<h1>${msg}</h1>

</body>
</html>