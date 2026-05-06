<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nom Site || Gestion Catégories</title>
<!-- specifier le chemin du fichier css pour le style des forms -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/accueilStyle.css">

<!-- specifier le chemin du fichier css pour le style de header admin -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/headerAdminStyle.css">

<!-- specifier le chemin du fichier bootstrap.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/popupSuppr.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />



</head>
<body
	style="background: url(../assets/images/brownsky.jpg) no-repeat; background-height: 1vh;">

	<%@ include file="/template/headerAdmin.html"%>
	<%@ include file="/template/navCat.html"%>

	<c:choose>
		<c:when test="${not empty listCat}">
			<!-- Formulaire pour rechercher une seule catégorie à partir de son id -->

			<div class="elements">
				<div id="inputSearch2">
					<form:form class="form-horizontal" method="post"
						action="submitGetCat" modelAttribute="catGet"
						style="text-align: center; width: 200px; margin: 0 auto;">
						

						<div class="form-group" style="float: left;">
							<form:input id="colorChange" type="number" class="form-control" placeholder="id"
								path="id" />

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

				<!-- Afficher les donnees de la categorie qu'on recherche -->

				<div class="formaccueil">
					<form:form class="form-horizontal" method="post"
						action="submitUpdateCat" modelAttribute="catUpdate"
						enctype="multipart/form-data">

						<div class="form-group">
							<form:label
								style="width: 40%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label" path="id">ID</form:label>
							<form:input type="text" class="form-control"
								value="${categorie.id}" path="id" readonly="true" />
						</div>

						<div class="form-group">
							<form:label
								style="width: 40%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label" path="nomCategorie">Nom </form:label>
							<form:input type="text" class="form-control" path="nomCategorie"
								value="${categorie.nomCategorie}" required="required" />
						</div>

						<div class="form-group">
							<form:label
								style="width: 40%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label" path="description">Description</form:label>
							<form:input type="text" class="form-control"
								value="${categorie.description}" path="description"
								required="required" />
						</div>

						<div class="form-group">
							<br />
							<h4>photo</h4>
							<img id="idImagemodif2" width="50%" height="30%" src="${categorie.image}"
								alt="Image not found" />
						</div>

						<div class="form-group">
							<label style="width: 20%; text-align: left; padding-left: 10px;"
								class="col-sm-2 control-label">Photo</label> <input
								style="width: 80%;" type="file" class="form-control"
								name="pPhoto" />
						</div>

						<button type="submit" class="btn btn-primary">Modifier</button>
						<a class="btn btn-danger" href="#popup1">Supprimer</a>

						<!-- 	Contenu du popUp  -->
						<div id="popup1" class="overlay">
							<div class="popup">
								<h2>Etes-vous sur?</h2>
								<button type="submit" class="retour">Retour</button>
								<button type="submit" class="suppr"
									 onclick="form.action='submitDeleteCat';">Supprimer</button>
							</div>
						</div>

						<!-- Fin du contenu du popUp -->

					</form:form>
				</div>

				<!-- Affichage du tableau contenant toutes les catégories -->
				<div class="tableGestion">
					<table id="idTable" class="table table-bordered">
						<tr
							style="top: 0; position: sticky; z-index: 11; background-color: white; border-radius: 10px; box-shadow: 0 0 3em #000000;">
							<th>ID catégorie</th>
							<th>Nom de la catégorie</th>
							<th>Description</th>
							<th>Photo</th>
						</tr>

						<c:forEach var="cat" items="${listCat}">
							<tr>
								<td>${cat.id}</td>
								<td>${cat.nomCategorie}</td>
								<td>${cat.description}</td>
								<td><img width="100%" height="30%" src="${cat.image}"
									alt="Image not found" /></td>
							</tr>

						</c:forEach>
					</table>
				</div>
			</div>
		</c:when>

		<c:otherwise>
			<h1 style="text-align: center">Aucune catégorie pour le moment</h1>
		</c:otherwise>
	</c:choose>

</body>
</html>