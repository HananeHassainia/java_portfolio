<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" href="http://example.com/favicon.png">

<%@ include file="/template/link.html"%>

<title>Java&Rien | Produits</title>
</head>
<body style="background-color: #fafafa">
	<%@ include file="/template/headerAccueilInterface.html"%>



	<c:choose>
		<c:when test="${not empty listCatProd}">
			<section class="sectionAllProduit">

				<!-- Partie gauche contenant la liste des catégorie des produit ayant le moment clé recherché dans leur nom -->
				<article id="section1">
					<div id="categorieSearch">
						<p>CATEGORIES</p>
						<form method="post" action="submitGetProdAll">
							<input style="display: none" type="text" value="${motCle}"
								name="pMotCleProd" /> &#10094;
							<button type="submit">Tous les produits : "${motCle}"</button>
						</form>
						<c:forEach var="cat" items="${listCatProd}">
							<form class="form-horizontal" method="post"
								action="displayProdByCatKey">
								<input style="display: none" type="text" value="${motCle}"
									name="pMotCle" /> <input type="text" class="form-control"
									value="${cat.id}" name="pIdCategorie" style="display: none;" />
								&#10094;
								<button type="submit">${cat.nomCategorie}</button>
							</form>
						</c:forEach>
					</div>
				</article>

				<article id="section2">
					<c:forEach var="prod" items="${listProdKey}">
						<form name="form+${prod.id}" method="post"
							action="DisplayProductPage" class="formAff">
							<input style="display: none" type="text" value="${prod.id}"
								name="pIdProd" />
							<div class="divGauche"
								onClick="document.forms['form+${prod.id}'].submit();">
								<div class="divImage">
									<img width="50%" height="100%" src="${prod.image}"
										alt="Image not found" />
								</div>

								<p id="designationP">${prod.designation}</p>
								<p id="descriptionP">${prod.description}</p>
							</div>
						</form>

						<form class="formPrix" method="post" action="addProductToPfast">
							<c:choose>
								<c:when test="${prod.quantite == 0}">
									<div div class="divPrix">
										<p style="color: red">Article épuisé</p>
										<button type="submit" class="btn btn-success"
											disabled="disabled">Ajouter au panier</button>
									</div>
								</c:when>
								<c:otherwise>
									<input style="display: none" type="text" value="${motCle}"
										name="pMotCleProd" />
									<input style="display: none;" type="text" value="${prod.id}"
										name="pIdProd" />
									<p>${prod.prix}&euro;</p>
									<div class="divPrix">
										<button type="submit" class="btn btn-success">Ajouter
											au panier</button>
									</div>

								</c:otherwise>
							</c:choose>

						</form>
						<form id="separation"></form>

					</c:forEach>
				</article>
			</section>
		</c:when>
		<c:otherwise>
			<div class="panierVide">
				<img width="12%" src="./assets/images/logo-attention.png">
				<h1>
					Aucun produit ne correspond à "<span
						style="font-style: italic; font-weight: lighter;">${motCle}</span>"
					veuillez recommencer
				</h1>

			</div>

		</c:otherwise>
	</c:choose>
	<%@ include file="/template/footer.html"%>


</body>
</html>