<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<%@ include file="/template/link.html"%>

<title>Java&Rien | Produits</title>
</head>
<body>
	<%@ include file="/template/headerAccueilInterface.html"%>

	<section class="sectionAllProduit">

		<!-- Partie gauche contenant la liste des catégorie des produit ayant le moment clé recherché dans leur nom -->
		<article id="section1">
			<div>
				<p>Affichage</p>
				<p>
					<input type="radio">ligne
				</p>

				<p>
					<input type="radio">box
				</p>
			</div>
			<div>
				<p>Filtrer le prix</p>
				<p>
					<input type="radio"> <1000000

				</p>

				<p>
					<input type="radio"> >1000000
				</p>
			</div>
			<div>
				<p>Filtrer par :</p>
				<p>
					<input type="radio"> du moins chère au plus chère

				</p>

				<p>
					<input type="radio"> du plus chère au moins chère
				</p>
			</div>
		</article>

		<article id="section2">

			<c:forEach var="prod" items="${listProdByCat}">
				<p id="navColor" style="display: none">${prod.categorie.nomCategorie}</p>

				<form name="form+${prod.id}" method="post"
					action="DisplayProductPage" class="formAff">
					<input style="display: none" type="text" value="${prod.id}"
						name="pIdProd" />
					<div class="divGauche"
						onClick="document.forms['form+${prod.id}'].submit();">
						<div class="divImage">
							<img src="${prod.image}" alt="Image not found" />
						</div>


						<p id="designationP">${prod.designation}</p>
						<p id="descriptionP">${prod.description}</p>
					</div>
				</form>
				<form class="formPrix" method="post" action="addProductToPfastCat">
					<input style="display: none;" type="text" value="${prod.id}"
						name="pIdProd" />
					<c:choose>
						<c:when test="${prod.quantite == 0}">
							<div class="divPrix">
								<p style="color: red">Article épuisé</p>
								<button type="submit" class="btn btn-success"
									disabled="disabled">Ajouter au panier</button>
							</div>
						</c:when>
						<c:otherwise>
							<div class="divPrix">
								<p>${prod.prix}&euro;</p>

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
	
		<%@ include file="/template/footer.html"%>
	

</body>
</html>