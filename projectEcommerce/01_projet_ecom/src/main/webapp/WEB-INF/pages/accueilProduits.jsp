<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nom Site || Gestion Produits</title>
<!-- specifier le chemin du fichier css pour le style des forms -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/accueilStyle.css">

<!-- specifier le chemin du fichier css pour le style de header admin -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/headerAdminStyle.css">

<!-- specifier le chemin du fichier bootstrap.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.css" />

<!-- specifier le chemin du fichier popup.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/popupSuppr.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />

</head>

<body
	style="background: url(../assets/images/brownsky.jpg) no-repeat; background-height: 1vh;">

	<%@ include file="/template/headerAdmin.html"%>
	<%@ include file="/template/navProd.html"%>

	<c:choose>
		<c:when test="${not empty listProd}">
			<!-- Formulaire pour rechercher un seul produit à partir de son id -->

			<div class="elements">
				<div id="inputSearch2">
					<form:form class="form-horizontal" method="post"
						action="submitGetProd" modelAttribute="prodGet"
						style="text-align: center; width: 200px; margin: 0 auto;">

						<div class="form-group" style="float: left;">
							<form:input type="number" class="form-control" placeholder="id"
								path="id" required="required" />

							<button id="boutonChercher2" type="submit">
								<img style="width: 25px !important;"
									src="../assets/images/loop.png" />
							</button>
						<h1 style="color: white; font-size:15px; margin-right:2%;">${msg1}</h1>
				<h1 style="color: white; font-size:15px; margin-right:2%;">${msg2}</h1>
				<h1 style="color: white; font-size:15px; margin-right:2%;">${msg3}</h1>
						</div>

					</form:form>
				</div>



				<!-- Afficher les donnees du produit qu'on recherche -->

				<div class="formaccueil">
					<form:form class="form-horizontal" method="post"
						action="submitUpdateProd" modelAttribute="prodUpdate"
						enctype="multipart/form-data">

						<div class="form-group" id="divcatProd">
							<form:label
								style="width: 40%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label" path="categorie.id">Catégorie</form:label>
							<form:select class="form-select"
								aria-label="Default select example" style="text-align: center;"
								path="categorie.id" id="categorie">
								<c:forEach var="cat" items="${listCat}">
									<form:option path="categorie.id" value="${cat.id}">${cat.nomCategorie}</form:option>
								</c:forEach>
							</form:select>
						</div>

						<div class="form-group">
							<form:label
								style="width: 40%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label" path="id">ID</form:label>
							<form:input type="number" class="form-control"
								value="${produit.id}" path="id" readonly="true" />
						</div>

						<div class="form-group">
							<form:label
								style="width: 40%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label" path="designation">Désignation</form:label>
							<form:input type="text" class="form-control" path="designation"
								value="${produit.designation}" required="required" />
						</div>

						<div class="form-group">
							<form:label
								style="width: 40%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label" path="description">Description</form:label>
							<form:input type="text" class="form-control"
								value="${produit.description}" path="description" />
						</div>

						<div class="form-group">
							<form:label
								style="width: 40%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label" path="prix">Prix</form:label>
							<form:input type="number" class="form-control" path="prix"
								required="required" value="${produit.prix}" />
						</div>

						<div class="form-group">
							<form:label
								style="width: 40%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label" path="quantite">Quantité</form:label>
							<form:input type="number" class="form-control" path="quantite"
								value="${produit.quantite}" required="required" />
						</div>

						<div class="form-group">
							<br />
							<h4>photo</h4>
							<img id="idImagemodif" width="50%"  src="${produit.image}"
								alt="Image not found" />
						</div>

						<div class="form-group">
							<label style="width: 60%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label">Photo</label> <input type="file"
								class="form-control" name="pPhoto" />
						</div>

						<button type="submit" class="btn btn-primary">Modifier</button>
						<a class="btn btn-danger" href="#popup1">Supprimer</a>

						<!-- 	Contenu du popUp  -->
						<div id="popup1" class="overlay">
							<div class="popup">
								<h2>Etes-vous sur?</h2>
								<button type="submit" class="retour">Retour</button>
								<button type="submit" class="suppr"
									onclick="form.action='submitDeleteProd';">Supprimer</button>
							</div>
						</div>

						<!-- 	Fin du contenu du popUp -->

					</form:form>
				</div>

				<!-- Affichage du tableau contenant tous les produits -->
				<div class="tableGestion">
					<table id="idTable" class="table table-bordered">
						<tr
							style="top: 0; position: sticky; z-index: 11; background-color: white; border-radius: 10px; box-shadow: 0 0 3em #000000;">
							<th>ID</th>
							<th>Catégorie</th>
							<th>Désignation</th>
							<th>Description</th>
							<th>Prix</th>
							<th>Quantité</th>
							<th>Photo</th>
						</tr>

						<c:forEach var="prod" items="${listProd}">
							<tr>
								<td>${prod.id}</td>
								<td>${prod.categorie.nomCategorie}</td>
								<td>${prod.designation}</td>
								<td>${prod.description}</td>
								<td>${prod.prix}</td>
								<td>${prod.quantite}</td>

								<td><img width="100%"  src="${prod.image}"
									alt="Image not found" /></td>
							</tr>

						</c:forEach>
					</table>
				</div>
			</div>
		</c:when>

		<c:otherwise>
			<h1 style="text-align: center">Aucun produit pour le moment</h1>
		</c:otherwise>
	</c:choose>

</body>
</html>