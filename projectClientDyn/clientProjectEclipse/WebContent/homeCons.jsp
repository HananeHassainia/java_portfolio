<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page accueil</title>
<%@ include file="./template/link.html"%>
<link rel="stylesheet" href="./assets/css/headerClt.css">
<link rel="stylesheet" href="./assets/css/body.css">

</head>
<body id="bootstrap-overrides">
	<%@ include file="./template/header.html"%>
	<%@ include file="./template/navClt.html"%>

		<c:choose>
		
			<c:when test="${not empty client}">
			<section class="homeCons">

				<article class="containerInput">
					<form class="form-inline" method="post" action="getClt">
						<input list="listId" type="number" class="form-control"
							id="idIdClt" placeholder="Identifiant" name="pId" required>
						<button type="submit" class="btn btn-primary mb-2"
							id="btnChercher">chercher</button>
						<datalist id="listId">
							<c:forEach var="clt" items="${listeClt}">
								<option value="${clt.id}">${clt.nom}${clt.prenom}</option>
							</c:forEach>
						</datalist>
						<h1 style="color: red; text-align: center;">${msg}</h1>
					</form>
				</article>

				<article class="inputFormBtn">
					<form class="formUpdateClient" method="post" action="updateClt">
						<div class="titre">
							<p>Client</p>
						</div>

						<span class="mb-3" id="row1Input"> <label for="idId"
							class="form-label">Id</label><input type="number"
							class="form-control" id="idId" placeholder="Id" name="pId"
							value="${client.id}" readonly>
						</span>
						<div class="column1Input">
							<span class="mb-3"> <label for="idNom" class="form-label">Nom</label><input
								type="text" class="form-control" id="idNom" placeholder="Nom"
								name="pNom" value="${client.nom}" required>

							</span> <span class="mb-3"><label for="idPrenom"
								class="form-label">Prenom</label> <input type="text"
								class="form-control" id="idPrenom" placeholder="prenom"
								name="pPrenom" value="${client.prenom}" required> </span> <span
								class="mb-3"><label for="idBirth" class="form-label">Date
									de naissance</label> <input type="date" class="form-control"
								id="idBirth" name="pDate" value="${client.birth}" required>
							</span> <span class="mb-3"> <label for="idAdresse"
								class="form-label">Adresse</label><input type="text"
								class="form-control" id="idAdresse" placeholder="Adresse"
								name="pAdresse" value="${client.adresse}" required>
							</span>
						</div>
						<div class="column2Input">
							<span class="mb-3"> <label for="idCp" class="form-label">Code
									Postale</label><input type="number" class="form-control" id="idCp"
								placeholder="Code Postal" name="pCp"
								value="${client.codePostal}" required class="mb-3">
							</span><span class="mb-3"> <label for="idVille"
								class="form-label">Ville</label><input type="text"
								class="form-control" id="idVille" placeholder="Ville"
								name="pVille" value="${client.ville}" required>
							</span> <span class="mb-3"><label for="idTel" class="form-label"
								class="col-sm-2 control-label">Numéro de téléphone</label> </span> <span
								class="mb-3"> <input type="tel" class="form-control"
								id="idTel" placeholder="07 58 58 58 58" pattern="[0]{1}[0-9]{9}"
								name="pTel" value="${client.telephone}" required> <label
								for="idMail" class="form-label">Adresse email</label> <input
								type="email" class="form-control" id="idMail"
								placeholder="adresse mail" name="pMail" value="${client.mail}"
								required>
							</span>
						</div>



						<div id="divModif" class="divConfirm">
							<img src="./assets/image/warningJaune.png">

							<p>Voulez vous modifier ce client ?</p>
							<button type="submit" id="inputModif" class="btn btn-success">Confirmer</button>
							<button type="button" id="annulerBtn2" class="btn btn-danger">Annuler</button>
						</div>

						<div id="divSupp" class="divConfirm">
							<img src="./assets/image/warningJaune.png">
							<p>Voulez vous supprimer ce client ?</p>
							<button type="submit" id="inputSupp" class="btn btn-success"
								onclick="form.action='deleteClt';">Confirmer</button>
							<button id="annulerBtn1" type="button" class="btn btn-danger">Annuler</button>
						</div>

						<div class="boutonOpeInput">
							<div id="btnModifFirst" class="btn btn-warning">Modifier</div>
							<div id="btnSuppFirst" class="btn btn-danger">Supprimer</div>
						</div>
					</form>
				</article>


				<article class="compteForm">
					<div class="titre">
						<p>Comptes</p>
					</div>

					<!-- CC -->
					<c:choose>
					<c:when test="${not empty client.ce}">
						<form class="CompteTabInput" method="post" action="getCE">
							<div class="soustitre">
								<p>Compte épargne</p>
							</div>

							<span class="form-group" id="compteFirstrow1"> <label
								for="idId">Id</label><input type="number"
								class="form-control" id="idId" placeholder="Id" name="pId"
								value="${client.ce.id}" readonly>
							</span> <span class="form-group" id="infoCompteIn3"><label
								for="idSolde">Solde</label> <input type="number"
								class="form-control" id="idSolde" placeholder="Solde"
								name="pSolde" min="0" step="0.01" value="${client.ce.solde}"
								readonly> </span> <span class="form-group" id="infoCompteIn4"><label
								for="idTdr">taux de rémunération</label> <input type="number"
								class="form-control" id="idTdr"
								placeholder="taux de rémunération" min="0" step="0.01"
								name="pTdr" value="${client.ce.tauxDeRemuneration}" readonly>

							</span> <span style="display: none;"> <input type="number"
								class="slide-up" id="idClt" name="pIdClt" value="${clt.id}"
								readonly> <label for="idClt"
								class="col-sm-2 control-label">Identifiant Client</label>
							</span>

							<div class="BtnVoirCompte">
								<button type="submit" class="btn btn-success">Voir</button>
							</div>
						</form>
					</c:when>
					<c:otherwise>
					<div class="noC">
												<img src="./assets/image/greenWarning.png">
					<p>Ce client ne possède aucun compte épargne</p></div>
					</c:otherwise>
					</c:choose>
					
					<hr>
					<c:choose>

					<c:when test="${not empty client.cc}">

						<form class="CompteTabInput" method="post" action="getCC">
							<div class="soustitre">
								<p>Compte courant</p>
							</div>

							<span class="form-group" id="compteFirstrow2"> <label
								for="idId">Id</label><input type="number"
								class="form-control" id="idId" placeholder="Id" name="pId"
								value="${client.cc.id}" readonly>
							</span> <span class="form-group" id="infoCompteIn1"> <label
								for="idSolde">Solde</label><input type="number"
								class="form-control" id="idSolde" placeholder="Solde"
								name="pSolde" value="${client.cc.solde}" readonly>

							</span> <span class="form-group" id="infoCompteIn2"> <label
								for="idDecouvert">Découvert</label><input type="number"
								class="form-control" id="idDecouvert"
								placeholder="decouvert autorisé" name="pDecouvert"
								value="${client.cc.decouvert}" readonly>

							</span> <span style="display: none;"> <input type="number"
								class="slide-up" id="idClt" name="pIdClt" value="${client.id}"
								readonly> <label for="idClt"
								class="col-sm-2 control-label">Identifiant Client</label>
							</span>

							<div class="BtnVoirCompte">
								<button type="submit" class="btn btn-success">voir</button>
							</div>
						</form>
					</c:when>
					<c:otherwise>
	<div class="noC"><img src="./assets/image/greenWarning.png">
					<p>Ce client ne possède aucun compte courant</p></div>
					</c:otherwise>
					</c:choose>
				</article>

				<article class="tablesAll">
<div class="titre">
							<p>Tous les clients</p>
						</div>
					<!-- <div class="titreTab">Clients</div> -->

					<table id="tableAll" class="table table-sm" cellspacing="0">
						<tr id="titleTable">
							<th scope="col">ID</th>
							<th scope="col">Nom</th>
							<th scope="col">Prenom</th>
							<th scope="col">Date de naissance</th>
							<th scope="col" width="230">adresse</th>
							<th scope="col">ville</th>
							<th scope="col">code postal</th>
							<th scope="col">telephone</th>
							<th scope="col">mail</th>
							<th scope="col">Compte Courant</th>
							<th scope="col">Compte Epargne</th>
						</tr>

						<c:forEach var="c" items="${listeClt}">
							<tr>
								<td scope="row">${c.id}</td>
								<td>${c.nom}</td>
								<td>${c.prenom}</td>
								<td>${c.birth}</td>
								<td>${c.adresse}</td>
								<td>${c.ville}</td>
								<td>${c.codePostal}</td>
								<td>${c.telephone}</td>
								<td>${c.mail}</td>
								<c:choose>
									<c:when test="${not empty c.cc}">
										<td>${c.cc.id}</td>
									</c:when>
									<c:otherwise>
										<td style="text-align: center">&#x274C;</td>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${not empty c.ce}">
										<td>${c.ce.id}</td>
									</c:when>
									<c:otherwise>
										<td style="text-align: center">&#x274C;</td>
									</c:otherwise>
								</c:choose>

							</tr>
						</c:forEach>
					</table>

				</article>
				</section>

			</c:when>
			<c:otherwise>
				<div class="noClt">
					<img src="./assets/image/greenWarning.png"><br>
					<p>Aucun client n'est inscrit : Ajouter un client</p>
				</div>
			</c:otherwise>
		</c:choose>




	<%@ include file="./template/footer.html"%>


</body>
</html>