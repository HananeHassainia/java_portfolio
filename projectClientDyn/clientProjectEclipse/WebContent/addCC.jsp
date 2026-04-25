<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="./template/link.html"%>
<link rel="stylesheet" href="./assets/css/headerClt.css">
<link rel="stylesheet" href="./assets/css/body.css">

</head>
<body>
	<%@ include file="./template/header.html"%>
	<%@ include file="./template/navCC.html"%>
	<c:choose>
		<c:when test="${not empty cNoExist}">
			<form class="inputAjout" method="post" action="addCC">
			<div class="titre">
							<p>Ajouter un compte courant</p>
						</div>
				<span class="mb-3"><label for="idClt" class="form-label">numéro
						Client</label> <input list="listId" type="number" class="form-control"
					id="idClt" placeholder="numéro Client" name="pIdClt"
					value="${cNoExist.clt.id}" required> <datalist id="listId">
						<c:forEach var="clt" items="${listeCltNoCC}">
							<option value="${clt.id}">${clt.nom}${clt.prenom}</option>
						</c:forEach>
					</datalist> 
				</span> <span class="mb-3"><label for="idSolde"
					class="form-label">Solde</label> <input type="number" min="0" class="form-control"
					id="idSolde" placeholder="Solde" name="pSolde"
					value="${cNoExist.solde}" required> 
				</span> <span class="mb-3"> <label for="idDecouvert"
					class="form-label">Découvert</label><input type="number" class="form-control"
					id="idDecouvert" placeholder="decouvert autorisé" name="pDecouvert"
					value="${cNoExist.decouvert}"> 
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
		<c:when test="${not empty listeCltNoCC}">
	
				<form class="inputAjout" method="post" action="addCC">
							<div class="titre">
							<p>Ajouter un compte courant</p>
						</div>
					<span class="mb-3"> <label for="idClt" class="form-label">numéro
							client</label><input list="listId" type="number" class="form-control"
						id="idClt" placeholder="numéro Client" name="pIdClt" required>
						<datalist id="listId">
							<c:forEach var="clt" items="${listeCltNoCC}">
								<option value="${clt.id}">${clt.nom}${clt.prenom}</option>
							</c:forEach>
						</datalist> 
					</span> <span class="mb-3"> <label
						for="idSolde" class="form-label">Solde</label><input type="number" min="0" class="form-control"
						id="idSolde" placeholder="Solde" name="pSolde" required> 
					</span> <span class="mb-3"> 
					 <label for="idDecouvert"
						class="form-label">Découvert</label><input type="number" class="form-control"
						id="idDecouvert" placeholder="decouvert autorisé"
						name="pDecouvert" required>
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
					<p>Tous les clients possèdent un compte courant : ajouter un client</p>
				</div>
		</c:otherwise>
		</c:choose>
		

		</c:otherwise>
	</c:choose>
		<%@ include file="./template/footer.html"%>
	
</body>
</html>