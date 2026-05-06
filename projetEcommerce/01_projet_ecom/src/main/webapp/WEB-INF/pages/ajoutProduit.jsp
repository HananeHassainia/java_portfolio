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
<title>Ajout produit</title>
<!-- specifier le chemin du fichier css pour le style des forms -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/formStyle.css">
	
<!-- specifier le chemin du fichier css pour le style de header admin -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/headerAdminStyle.css">

<!-- specifier le chemin du fichier bootstrap.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.css" />

<!-- specifier le chemin du fichier popup.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/popUpAjouot.css" />

<script type="text/javascript" src="./assets/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="./assets/js/popup.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />

</head>
<body
	style="background: url(../assets/images/brownsky.jpg) no-repeat; background-height: 1vh;">

	<!-- Inclure le header -->
	<%@ include file="/template/headerAdmin.html"%>
	<%@ include file="/template/navProd.html"%>

	<br />
	<h1 style="color: white; text-align: center;">
		<u>Formulaire d'ajout d'un produit</u>
	</h1>
	<br />

	<h1 style="color: red">${msg}</h1>
	<h1 style="color: green">${msg1}</h1>

	<div class="forms">
		<form:form class="form-horizontal" method="post"
			action="submitAddProd" modelAttribute="prodAdd" style="margin: 15px;"
			enctype="multipart/form-data">

			<div class="form-group">
				<label class="col-sm-2 control-label">Categorie</label> <select
					name="pCategorie" id="categorie">
					<c:forEach var="cat" items="${listCat}">
						<option value="${cat.id}">${cat.nomCategorie}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="designation">Designation</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control"
						placeholder="Designation produit" path="designation"
						required="required" />
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="description">Description</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control"
						placeholder="Description" path="description" />
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="prix">Prix</form:label>
				<div class="col-sm-10">
					<form:input type="number" class="form-control" placeholder="Prix"
						path="prix" required="required" />
				</div>
			</div>

			<div class="form-group">
				<form:label class="col-sm-2 control-label" path="quantite">Quantite</form:label>
				<div class="col-sm-10">
					<form:input type="text" class="form-control" placeholder="Quantité"
						path="quantite" required="required" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">Photo</label> <input
					style="width: 80%; padding-left: 10px;" type="file"
					class="form-control" name="pPhoto" />
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success center-block"
						onclick="showModal()">Ajouter</button>
				</div>
			</div>

		</form:form>

	</div>

</body>
</html>