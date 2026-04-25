<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="./template/link.html"%>
<link rel="stylesheet" href="./assets/css/headerClt.css">
<link rel="stylesheet" href="./assets/css/body.css">

</head>
<body>

	<%@ include file="./template/header.html"%>
	<%@ include file="./template/navCE.html"%>
	<c:choose>
		<c:when test="${not empty cNoExist}">
			<form class="inputAjout" method="post" action="addCE">
			<div class="titre">
							<p>Ajouter un compte épargne</p>
						</div>
				<span class="mb-3"> <label for="idClt"  class="form-label">numéro
						client</label> <input list="listId" type="number" class="form-control"
					id="idClt" placeholder="numéro Client" name="pIdClt" required
					value="${cNoExist.clt.id}"> <datalist id="listId">
						<c:forEach var="clt" items="${listeCltNoCE}">
							<option value="${clt.id}">${clt.nom}${clt.prenom}</option>
						</c:forEach>
					</datalist> 
				</span> <span class="mb-3"> <label
					for="idSolde"  class="form-label">Solde</label> <input type="number" min="0" class="form-control"
					id="idSolde" placeholder="Solde" name="pSolde"
					value="${cNoExist.solde}" step="0.01" required> 
				</span> <span class="mb-3">  <label
					for="idTde"  class="form-label">Taux de
						rémunération</label> <input type="number" class="form-control" id="idTde"
					placeholder="decouvert autorisé" name="pTde" min="0" step="0.01"
					value="${cNoExist.tauxDeRemuneration}" required>
				</span>

				<div class="containerBtnInput">
					<button type="submit" class="btnModifier">Ajouter le
						compte</button>
				</div>

				<h1 style="color: red; text-align: center;">${msg}</h1>
			</form>
		</c:when>

		<c:otherwise>
		<c:choose>
			<c:when test="${not empty listeCltNoCE}">
					<form class="inputAjout" method="post" action="addCE">
			<div class="titre">
							<p>Ajouter un compte épargne</p>
						</div>
					<span class="mb-3"> <label for="idClt"  class="form-label">numéro
							Client</label> <input list="listId" type="number" class="form-control"
						id="idClt" placeholder="numéro Client" name="pIdClt" required>
						<datalist id="listId">
							<c:forEach var="clt" items="${listeCltNoCE}">
								<option value="${clt.id}">${clt.nom}${clt.prenom}</option>
							</c:forEach>
						</datalist> 
					</span> <span class="mb-3">  <label for="idSolde"
						 class="form-label">Solde</label> <input type="number" min="0" class="form-control"
						id="idSolde" placeholder="Solde" name="pSolde" step="0.01"
						required>
					</span> <span class="mb-3"> <label for="idTde"
						 class="form-label">Taux de rémunération</label> <input type="number" class="form-control" id="idTde"
						placeholder="decouvert autorisé" name="pTde" min="0" value="0.03"
						step="0.01" required> 
					</span>

					<div class="containerBtnInput">
						<button type="submit" id="inputAjout" class="btn btn-success">Ajouter le
							compte</button>
					</div>

					<h1 style="color: red; text-align: center;">${msg}</h1>
				</form>
			</c:when>
			<c:otherwise>
				<div class="noClt">
					<img src="./assets/image/greenWarning.png"><br>
					<p>Tous les clients possèdent un compte épargne : ajouter un client</p>
				</div>
			</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
			<%@ include file="./template/footer.html"%>
	
</body>
</html>