<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Java&Rien | Panier</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Ajouter la lib form de spring mvc -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="/template/link.html"%>

</head>
<body id="bodyPanier">
	<%@ include file="/template/headerAccueilInterface.html"%>
	<c:choose>

		<c:when test="${not empty panier}">
			<section id="sectionPanier">

				<article>
					<c:forEach var="prod" items="${panier}" varStatus="status">
						<div id="unProduitPanier">
							<form name="form+${prod.produit.id}" method="post"
								action="DisplayProductPage" id="divImgDesign">
								<input style="display: none" type="text"
									value="${prod.produit.id}" name="pIdProd" />
								<div id="divDesImg"
									onClick="document.forms['form+${prod.produit.id}'].submit();">
									<div class="divImage">
										<img width="50%" height="100%" src="${prod.produit.image}"
											alt="Image not found" />
									</div>
									<div id="divpara">
										<p id="pDesignationPanier">${prod.produit.designation}</p>
										<p id="pDescriptionPanier">${prod.produit.description}</p>

									</div>


								</div>
							</form>

							<form method="post" action="deleteProdPanier" id="prixProdPanier">
								<input style="display: none;" type="text"
									value="${prod.produit.id}" name="pIdProd" />
								<p style="grid-row: 1; width: 20%; color: red; font-size: 30px;">${prod.prix}&euro;</p>
								<hr>

	
								
								

													<p style="grid-row: 2; width: 20%; color: black; text-align:center;font-size: 20px; display:flex; margin-left:40px; align-items: center;">Quantité: ${prod.quantite}</p>
					
								<div>
									<button type="submit" class="btn btn-success">Supprimer</button>
								</div>
							</form>
						</div>

					</c:forEach>

				</article>



				<article>
					<form style="grid-column: 3;" method="post" action="order"
						id="stickyOrder">
						<div id="totalSansReduc">
							<p style="font-size: 25px">
								Total : <span style="color: red; font-size: 25px">${total}&euro;</span>
							</p>
							<label style="margin-top: 5%;">CODE PROMO :</label> <input
								class="form-control" id="inputTestPromo" type="text"
								placeholder="CodePromo" name="pCodePromo" />

						</div>

						<div id="totalAvecReduc" style="display: none">
							<div></div>
							<p style="font-size: 25px">
								Total : <span
									style="color: gray; font-size: 25px; text-decoration: line-through;"
									id="totAvantP"><span><span id="prixT">${total}</span>&euro;</span>
							</p>

							<span style="color: red; font-size: 25px" id="totalPrix">&euro;</span>
							<div id="divNoPromo">
								<span style="font-style: italic;">Nomane2022</span>
								<p id="buttonNoPromo"
									style="margin-left: 3%; font-weight: bold;">&#9587</p>
							</div>

						</div>




						<button type="submit" class="btn btn-danger">Commander</button>
					</form>

				</article>
			</section>

		</c:when>
		<c:otherwise>
			<div class="panierVide">
				<img src="./assets/images/panier.png" />
				<h1>Panier vide</h1>

			</div>

		</c:otherwise>
	</c:choose>
	
		<%@ include file="/template/footer.html"%>
	
</body>
</html>