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
	<!-- Liens qui mènent aux headers -->
	<%@ include file="./template/header.html"%>
	<%@ include file="./template/navCC.html"%>

	<c:choose>
		<c:when test="${not empty compteC}">
			<!-- input de type number pour récupérer les infos d'un compte via l'id d'un compte ou d'un client -->
				<section class="homeCons">
				<!-- Pour pouvoir tester les valeurs dans le script js -->
				<c:forEach var="cc" items="${listeCC}">
					<p style="display: none;" class="listeAll">${cc.id}</p>
				</c:forEach>

				<c:forEach var="cc" items="${listeCC}">
					<p style="display: none;" class="listeAllClt">${cc.clt.id}</p>
				</c:forEach>

						<!-- Formulaire pour chercher un compte -->
											<article class="containerInput">
						
						<form class="form-inline" method="post" action="getCC">
							<input list="listId" type="number" class="form-control"
								id="idIdCe" placeholder="Identifiant" name="pId" required>
							<button type="submit"
								class="btn btn--primary btn--inside uppercase">chercher</button>

							<div id="checkInput">

								<div>
									<input type="checkbox" class="checkIn" id="IdcltCheck"
										name="pIdcltCheck" checked> <label for="IdcltCheck">Id
										Client</label>
								</div>

								<div>
									<input type="checkbox" class="checkIn" id="IdCheck"
										name="pIdCheck"> <label for="IdCheck">Numéro
										de compte</label>
								</div>
							</div>
						</form>

			</article>


			<article class="inputFormBtn">
				<div class="titre">
					<p>Compte courant</p>
				</div>
				<!-- information d'un compte : sous forme d'input -->
				<form class="compteInputWi" method="post" action="updateCC">

					<span class="mb-3"> <label for="idId" class="form-label">Id</label><input
						type="number" class="form-control" id="idId" placeholder="Id"
						name="pId" value="${compteC.id}" readonly>
					</span> <span class="mb-3">
						<label for="idClt" class="form-label">Identifiant Client</label> <input
						type="number" class="form-control" id="idClt" name="pIdClt"
						value="${compteC.clt.id}" readonly>
					</span><span class="mb-3"> <label for="idSolde"
						class="col-sm-2 control-label">Solde</label> <input type="number"
						class="form-control" id="idSolde" placeholder="Solde" name="pSolde"
						value="${compteC.solde}" required>


					</span> <span class="mb-3"><label for="idDecouvert"
						class="form-label">Découvert</label> <input type="number"
						class="form-control" id="idDecouvert"
						placeholder="decouvert autorisé" name="pDecouvert"
						value="${compteC.decouvert}" required> </span> 

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
							onclick="form.action='deleteCC';">Confirmer</button>
						<button id="annulerBtn1" type="button" class="btn btn-danger">Annuler</button>
					</div>

					<div class="boutonOpeInput">
						<div id="btnModifFirst" class="btn btn-warning">Modifier</div>
						<div id="btnSuppFirst" class="btn btn-danger">Supprimer</div>
					</div>
				</form>
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
					<!-- Formulaire pour faire un virement -->
					<form class="ope" id="formVir" method="post" action="opRCC">
						<span class="mb-3"><label class="form-label" for="Client">Compte
								à créditer</label> <select class="form-control" id="Client" name="pCompteC">
								<c:forEach var="compte" items="${listeCC}">
									<c:if test="${compteC.id != compte.id}">
										<option value="${compte.id}">Compte n°${compte.id} :
											${compte.clt.nom}</option>
									</c:if>
								</c:forEach>
						</select> </span> <span class="mb-3"> <input type="text"
							class="inputIdCache" name="pTypeOp" value="virement"> <input
							type="number" class="inputIdCache" name="pId"
							value="${compteC.id}"> <label for="idSommeV" class="form-label">Somme
								à transferer</label><input type="number"
							class="form-control" id="idSommeV" placeholder="Somme à virer"
							name="pIdSommeV" max="${compteC.solde+compteC.decouvert}"
							required></span> 

							<button type="submit" class="btn btn-success">Valider</button>
					</form>

					<!-- Formulaire pour faire un dépôt -->
					<form class="ope" id="formDepot" method="post" action="opRCC">
						<input type="text" class="inputIdCache" name="pTypeOp"
							value="depot"> <input type="number" class="inputIdCache"
							name="pId" value="${compteC.id}"> <input type="number"
							class="form-control" id="idSommeV" placeholder="Somme à déposer"
							name="pIdSommeV" required> <label for="idSommeV"
							class="form-label">Somme à Déposer</label>
						<button type="submit" class="btn btn-success">Valider</button>

					</form>

					<form class="ope" id="formRetrait" method="post" action="opRCC">
						<input type="text" class="inputIdCache" name="pTypeOp"
							value="retrait"> <input type="number"
							class="inputIdCache" name="pId" value="${compteC.id}"> <input
							type="number" class="form-control" id="idSommeV"
							placeholder="Somme à retirer" name="pIdSommeV"
							max="${compteC.solde+compteC.decouvert}"> <label
							for="idSommeV" class="form-label">Somme à retirer</label>
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

<article class="tablesAll">
					<div class="titre">
						<p>Tous les comptes courants</p>
					</div>
				<!-- Tableau avec tous les informations des comptes épargnes liés au conseillé de la session -->
				<table id="tableAll" class="table table-sm" cellspacing="0">
					<tr id="titleTable">
						<th scope="col">Id Client</th>
						<th scope="col">Nom Client</th>
						<th scope="col">Prénom Client</th>
						<th scope="col">Numéro de compte</th>
						<th scope="col" width="230">Solde</th>
						<th scope="col">Découvert autorisé</th>
						<th scope="col">Compte Epargne</th>

					</tr>
					<c:forEach var="cc" items="${listeCC}">
						<tr>
							<td scope="row">${cc.clt.id}</td>
							<td>${cc.clt.nom}</td>
							<td>${cc.clt.prenom}</td>
							<td>${cc.id}</td>
							<td>${cc.solde}</td>
							<td>${cc.decouvert}</td>
							<c:choose>
								<c:when test="${not empty cc.clt.ce}">
									<td>${cc.clt.ce.id}</td>
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
				<p>Aucun compte épargne</p>
			</div>
		</c:otherwise>
	</c:choose>
		<%@ include file="./template/footer.html"%>
	
</body>
</html>