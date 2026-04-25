<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="./template/link.html"%>
<link rel="stylesheet" href="./assets/css/headerClt.css">
<link rel="stylesheet" href="./assets/css/body.css">


</head>
<body>
	<%@ include file="./template/header.html"%>
	<%@ include file="./template/navCE.html"%>
	<c:choose>
		<c:when test="${not empty compteE}">
			<!-- input de type number pour récupérer les infos d'un compte via l'id d'un compte ou d'un client -->
			<section class="homeCons">
				<!-- Pour pouvoir tester les valeurs dans le script js -->
				<c:forEach var="ce" items="${listeCE}">
					<p style="display: none;" class="listeAll">${ce.id}</p>
				</c:forEach>

				<c:forEach var="ce" items="${listeCE}">
					<p style="display: none;" class="listeAllClt">${ce.clt.id}</p>
				</c:forEach>

				<!-- Formulaire pour chercher un compte -->
				<article class="containerInput">

					<form class="form-inline" method="post" action="getCE">
						<div class="invalidInput" style="display: none;">compteE</div>
						<input list="listId" type="number" class="form-control" id="idIdCe"
							placeholder="Identifiant" name="pId">
						<button type="submit" class="btn btn-primary mb-2"
							id="btnChercher">chercher</button>

						<div id="checkInput">
							<div>
								<input class="checkIn" type="checkbox" id="IdcltCheck"
									name="pIdcltCheck" checked> <label for="IdcltCheck">Id
									Client</label>
							</div>

							<div>
								<input type="checkbox" id="IdCheck" name="pIdCheck"
									class="checkIn"> <label for="IdCheck">numéro de
									compte</label>
							</div>
						</div>


					</form>
				</article>


				<article class="inputFormBtn">
					<div class="titre">
						<p>Compte épargne</p>
					</div>
					<!-- information d'un compte : sous forme d'input -->
					<form class="compteInputWi" method="post" action="updateCE">

						<span class="mb-3"> <label for="idId" class="form-label">Id</label><input
							type="number" class="form-control" id="idId" placeholder="Id"
							name="pId" value="${compteE.id}" readonly>
						</span> <span class="mb-3"> <label for="idClt" class="form-label">Identifiant
								Client</label> <input type="number" class="form-control" id="idClt"
							name="pIdClt" value="${compteE.clt.id}" readonly>
						</span> <span class="mb-3"> <label for="idSolde"
							class="form-label">Solde</label> <input type="number"
							class="form-control" id="idSolde" placeholder="Solde"
							name="pSolde" min="0" step="0.01" value="${compteE.solde}"
							required>
						</span> <span class="mb-3"> <label for="idTdr" class="form-label">taux
								de rémunération</label> <input type="number" class="form-control"
							id="idTdr" placeholder="taux de rémunération" min="0" step="0.01"
							name="pTdr" value="${compteE.tauxDeRemuneration}" required>
						</span>
						<div id="divModif" class="divConfirm">
							<img src="./assets/image/warningJaune.png">

							<p>Voulez vous modifier ce compte ?</p>
							<button type="submit" id="inputModif" class="btn btn-success">Confirmer</button>
							<button type="button" id="annulerBtn2" class="btn btn-danger">Annuler</button>
						</div>

						<div id="divSupp" class="divConfirm">
							<img src="./assets/image/warningJaune.png">
							<p>Voulez vous supprimer ce compte ?</p>
							<button type="submit" id="inputSupp" class="btn btn-success"
								onclick="form.action='deleteCE';">Confirmer</button>
							<button id="annulerBtn1" type="button" class="btn btn-danger">Annuler</button>
						</div>

						<div class="boutonOpeInput">
							<div id="btnModifFirst" class="btn btn-warning">Modifier</div>
							<div id="btnSuppFirst" class="btn btn-danger">Supprimer</div>
						</div>
					</form>

					<!-- Formulaire pour faire un virement -->
					<div>
					<hr>
						<div class="containerCheckOp">
							<div>
								<input class="opeCheck" type="checkbox" id="virCheck" checked>
								<label for="virCheck">Virement</label>
							</div>

							<div>
								<input type="checkbox" class="opeCheck" id="depCheck"> <label
									for="depCheck">dépôt</label>
							</div>
							<div>
								<input type="checkbox" class="opeCheck" id="depCheck"
									name="pIdCheck"> <label for="depCheck">retrait</label>
							</div>

						</div>

						<form class="ope" id="formVir" method="post" action="opRCE">
							<span class="mb-3"> <label for="Client" class="form-label">Compte
									à créditer</label> <select id="Client" name="pCompteE"
								class="form-control">
									<c:forEach var="compte" items="${listeCE}">
										<c:if test="${compteE.id != compte.id}">
											<option value="${compte.id}">Compte n°${compte.id} :
												${compte.clt.nom}</option>
										</c:if>
									</c:forEach>
							</select>
							</span> <span class="mb-3"> <input type="text"
								class="inputIdCache" name="pTypeOp" value="virement"> <input
								type="number" class="inputIdCache" name="pId"
								value="${compteE.id}"> <label for="idSommeV"
								class="form-label">Somme à transférer</label><input
								type="number" class="form-control" id="idSommeV"
								placeholder="Somme à virer" name="pIdSommeV"
								max="${compteE.solde}" required></span>
							<button type="submit" class="btn btn-success">Valider</button>
						</form>

						<!-- Formulaire pour faire un depot -->
						<form class="ope" id="formDepot" method="post" action="opRCE">
							<span class="mb-3"><input type="text" class="inputIdCache"
								name="pTypeOp" value="depot"> <input type="number"
								class="inputIdCache" name="pId" value="${compteE.id}"> <label
								for="idSommeV" class="form-label">Somme à Déposer</label><input
								type="number" class="form-control" id="idSommeV"
								placeholder="Somme à déposer" name="pIdSommeV" required></span>

							<button type="submit" class="btn btn-success">Valider</button>
						</form>
						<form class="ope" id="formRetrait" method="post" action="opRCE">
							<span class="mb-3"><input type="text" class="inputIdCache"
								name="pTypeOp" value="retrait"> <input type="number"
								class="inputIdCache" name="pId" value="${compteE.id}"> <label
								for="idSommeV" class="form-label">Somme à retirer</label> <input
								type="number" class="form-control" id="idSommeV"
								placeholder="Somme à retirer" name="pIdSommeV"
								max="${compteE.solde}" required></span>

							<button type="submit" class="btn btn-success">Valider</button>
						</form>

					</div>

				</article>

				<article class="compteForm">
					<div class="titre">
						<p>Transactions</p>
					</div>
					<!-- Tableau des opération associé à ce compte -->
					<c:choose>
						<c:when test="${not empty listeTr}">
							<table id="tableAll" class="table table-sm" cellspacing="0">
								<tr id="titleTable">
									<th scope="col">Id opération</th>
									<th scope="col">date de transition</th>
									<th scope="col">type</th>
									<th scope="col">somme</th>

								</tr>
								<c:forEach var="tr" items="${listeTr}">
									<tr>
										<td scope="row">${tr.id}</td>
										<td>${tr.dateTr}</td>
										<td>${tr.operation}</td>
										<td>${tr.montant}</td>

									</tr>
								</c:forEach>
							</table>
						</c:when>
						<c:otherwise>
							<div class="noC">
								<img src="./assets/image/greenWarning.png">
								<p>Aucune transaction</p>
							</div>
						</c:otherwise>
					</c:choose>
				</article>


				<!-- Tableau avec tous les informations des comptes épargnes liés au conseillé de la session -->

				<article class="tablesAll">
					<div class="titre">
						<p>Tous les comptes épargnes</p>
					</div>

					<table id="tableAll" class="table table-sm" cellspacing="0">

						<tr id="titleTable">
							<th scope="col">Id Client</th>
							<th scope="col">Nom Client</th>
							<th scope="col">Prénom Client</th>
							<th scope="col">Numéro de compte</th>
							<th scope="col" width="230">Solde</th>
							<th scope="col">Taux de Rémunération</th>
							<th scope="col">Compte Courant</th>

						</tr>
						<c:forEach var="ce" items="${listeCE}">
							<tr>
								<td scope="row">${ce.clt.id}</td>
								<td>${ce.clt.nom}</td>
								<td>${ce.clt.prenom}</td>
								<td>${ce.id}</td>
								<td>${ce.solde}</td>
								<td>${ce.tauxDeRemuneration}</td>
								<c:choose>
									<c:when test="${not empty ce.clt.cc}">
										<td>${ce.clt.cc.id}</td>
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
				<p>Aucun compte courant</p>
			</div>
		</c:otherwise>
	</c:choose>
		<%@ include file="./template/footer.html"%>
	
</body>
</html>