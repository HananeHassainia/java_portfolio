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

<title>Java&Rien | ${produitCons.designation}</title>
</head>
<body>

	<%@ include file="/template/headerAccueilInterface.html"%>

<!-- 	<h1 style="text-align: center; color: green;">test panier</h1>
 -->	<%-- <h1 style="text-align: center; color: red;">${msg1}</h1> --%>
	<section id="sectionProduitUn">
		<div id="divImageUn">
			<img src="${produitCons.image}" alt="Image not found" />
		</div>


		<p id="pDesignationUn">${produitCons.designation}</p>
		<p id="pDescriptionUn">${produitCons.description}</p>

		<form method="post" action="addProductToSc" class="formPrix1">

			<c:choose>
				<c:when test="${not empty listQt}">
					<p style="grid-column: 3; grid-row: 1; width: 20%; color: red">${produitCons.prix}&euro;</p>
					<br>
					<hr>
					<input style="display: none;" type="text" value="${produitCons.id}"
						name="pIdProd" />
					<div id="quantite">
						<label>Quantité :</label> <select
							class="form-select form-select-sm"
							aria-label=".form-select-sm example" name="pProduitQuantite">
							<c:forEach var="q" items="${listQt}">
								<option value="${q}">${q}</option>
							</c:forEach>
						</select>
					</div>

					<div>
						<button type="submit" class="btn btn-success">Ajouter au
							panier</button>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<p style="color: red">Article épuisé</p>
						<hr>
						<button type="submit" class="btn btn-success" disabled="disabled">Ajouter
							au panier</button>
					</div>
				</c:otherwise>
			</c:choose>

		</form>

	</section>

	<%@ include file="/template/footer.html"%>

</body>
</html>