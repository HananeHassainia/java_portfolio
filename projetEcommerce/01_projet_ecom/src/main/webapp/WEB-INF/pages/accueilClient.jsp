<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Java&Rien || Espace Client</title>
<%@ include file="/template/link.html"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/ClientnavBar.css" />

</head>
<body style="background-color: #fafafa">
	<%@ include file="/template/headerAccueilInterface.html"%>

	<%@ include file="/template/navBarClient.html"%>

	<c:choose>
		<c:when test="${not empty listCommande}">

			<form id="divCmd" method="post" action="DetailCmd">
				<select class="form-select" aria-label="Default select example"
					name="pCommande" id="commande" onchange="this.form.submit()">
					<c:forEach var="cmd" items="${listCommande}">
						<c:choose>
							<c:when test="${selectedValue == cmd.id_cmd}">
								<option value="${cmd.id_cmd}" selected="selected">Commande
									numéro : ${cmd.id_cmd}</option>
							</c:when>
							<c:otherwise>
								<option value="${cmd.id_cmd}">Commande numéro :
									${cmd.id_cmd}</option>
							</c:otherwise>
						</c:choose>

					</c:forEach>
				</select>
			</form>



			<div id="divProdHist">
				<c:forEach var="prod" items="${listPrdCmd}">

					<form name="form+${prod.id}" method="post"
						action="DisplayProductPage" class="formAff">
						<input style="display: none" type="text" value="${prod.id}"
							name="pIdProd" />
						<div class="divGauche"
							onClick="document.forms['form+${prod.idProd}'].submit();">
							<div class="divImage">
								<img src="${prod.imageProd}" alt="Image not found" />



							</div>

							<div>
								<p id="designationP">${prod.designationProd}</p>
								<p id="descriptionP">${prod.descriptionProd}</p>
							</div>
							<div class="formPrix" style="color: black !important">
								<p class="qtPrix">quantité : ${prod.quantitLc}</p>
								<p class="qtPrix">prix : ${prod.prixLc} &euro;</p>
							</div>

						</div>
					</form>

					<form id="separationhist"></form>

				</c:forEach>
				<c:choose>
					<c:when test="${not listPrdCmd[0].promo}">
						<h1>
							Total : <span style="color: red">${total} &euro;</span>
						</h1>

					</c:when>
					<c:otherwise>
						<h1>
							Total : <span style="color: gray; text-decoration: line-through;">${total}
								&euro;</span> <span style="color: red;">${promo} &euro;</span>

						</h1>

					</c:otherwise>

				</c:choose>


			</div>
		</c:when>
		<c:otherwise>
			<div class="panierVide">

				<h1>Aucune commande pour le moment</h1>
			</div>
		</c:otherwise>
	</c:choose>
	
		<%@ include file="/template/footer.html"%>
	
</body>
</html>